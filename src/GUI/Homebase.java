package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Homebase {

    private BufferedImage splashImage;
    private JLayeredPane layeredPane;
    private JPanel imagePanel;
    private JPanel centerPanel;
    private CardLayout cardLayout;

    private JButton dungeonButton, monsterButton, itemButton, gameDataButton, exitButton;

    public Homebase(JFrame frame) {
        // frame.setLayout(new BorderLayout());

        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Load the image
        loadSplashImage();

        // Create layered pane and add it to the frame
        layeredPane = new JLayeredPane();
        frame.getContentPane().add(layeredPane);

        // Add image background panel to layered pane
        createImagePanel();
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

        // Set up the center panel with CardLayout
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        centerPanel.setOpaque(false);
        layeredPane.add(centerPanel, JLayeredPane.PALETTE_LAYER);

        // Create the home panel with buttons
        createHomePanel(frame);

        // Add component listener for resizing
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                resizeComponents();
            }

            @Override
            public void componentShown(ComponentEvent evt) {
                resizeComponents();
            }
        });

        // frame.pack();
        frame.setVisible(true);
    }

    private void loadSplashImage() {
        try {
            File imgFile = new File("src/resource/homebase.jpg");
            if (imgFile.exists()) {
                splashImage = ImageIO.read(imgFile);
            } else {
                System.err.println("Image file not found: " + imgFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createImagePanel() {
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (splashImage != null) {
                    g.drawImage(splashImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        imagePanel.setBounds(0, 0, 1920, 1080);
    }

    private void createHomePanel(JFrame frame) {
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 40, 50, 40);

        // Add Monster button
        monsterButton = addButton(homePanel, "Monster", gbc, 0, 0, GridBagConstraints.NORTHWEST, null);
        dungeonButton = addButton(homePanel, "Dungeon", gbc, 1, 0, GridBagConstraints.NORTH, null);
        itemButton = addButton(homePanel, "Item", gbc, 2, 0, GridBagConstraints.NORTHEAST, null);
        // saveButton = addButton(homePanel, "Save Game", gbc, 0, 1,
        // GridBagConstraints.SOUTHWEST, new Dimension(120, 60));
        exitButton = addButton(homePanel, "Exit Game", gbc, 1, 1, GridBagConstraints.SOUTH, new Dimension(150, 28));
        gameDataButton = addButton(homePanel, "Game Data", gbc, 2, 1, GridBagConstraints.SOUTHEAST,
                new Dimension(120, 35));

        monsterButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new MonsterSelection(frame, "Homebase");
        });

        dungeonButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new Dungeon(frame);
        });

        itemButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new ItemSelection(frame, "Homebase");
        });

        exitButton.addActionListener(e -> {
            exitOption();
        });

        gameDataButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new GameData(frame);
        });

        centerPanel.add(homePanel, "home");
        cardLayout.show(centerPanel, "home");
    }

    private JButton addButton(JPanel panel, String text, GridBagConstraints gbc, int x, int y, int anchor,
            Dimension size) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton button = new JButton(text);
        if (size != null) {
            button.setPreferredSize(size);
        }
        buttonPanel.add(button);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        panel.add(buttonPanel, gbc);

        return button;
    }

    private void resizeComponents() {
        Dimension newSize = layeredPane.getSize();
        imagePanel.setBounds(0, 0, newSize.width, newSize.height);
        centerPanel.setBounds(0, 0, newSize.width, newSize.height);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    private void exitOption() {
        int result = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Exit Option",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
