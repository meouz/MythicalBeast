package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.gamedata.GameData;

public class MainGUI extends JFrame {
    private BufferedImage splashImage;
    public JPanel buttonPanel;
    private JLabel messageLabel;
    private JLayeredPane layeredPane;
    public JFrame frame;
    private GameData gameData = new GameData("Save Game ");

    public MainGUI(JFrame frame) {
        layeredPane = new JLayeredPane();
        setTitle("Mythical Beast");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            File imgFile = new File("src/resources/images/mainmenu.jpg");
            if (imgFile.exists()) {
                splashImage = ImageIO.read(imgFile);
            } else {
                System.err.println("Image file not found: " + imgFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(layeredPane, BorderLayout.CENTER);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (splashImage != null) {
                    g.drawImage(splashImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        imagePanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

        messageLabel = new JLabel("Press any key", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setForeground(new Color(255, 255, 255)); // Transparent white
        layeredPane.add(messageLabel, JLayeredPane.PALETTE_LAYER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                removeKeyListener(this);
                layeredPane.remove(messageLabel);
                showMainMenu();
            }
        });
    }

    @Override
    public void doLayout() {
        super.doLayout();
        layeredPane.setBounds(0, 0, getWidth(), getHeight());
        for (Component component : layeredPane.getComponents()) {
            component.setBounds(0, 0, getWidth(), getHeight());
        }
        if (messageLabel != null) {
            messageLabel.setBounds(0, getHeight() - 200, getWidth(), 50);
        }
        if (buttonPanel != null) {
            buttonPanel.setBounds((getWidth() - 200) / 2, (getHeight() - 200) / 2 + 200, 200, 200);
        }
    }

    public void showMainMenu() {
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton newGameButton = createButton("New Game");
        JButton loadGameButton = createButton("Load Game");
        JButton exitButton = createButton("Exit");

        newGameButton.addActionListener(e -> {
            layeredPane.removeAll();
            new HomeBaseGUI(this);
        });

        loadGameButton.addActionListener(e -> showLoadGameMenu());

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        buttonPanel.add(newGameButton, gbc);
        buttonPanel.add(loadGameButton, gbc);
        buttonPanel.add(exitButton, gbc);

        layeredPane.add(buttonPanel, JLayeredPane.MODAL_LAYER);

        validate();
    }

    public void showLoadGameMenu() {
        buttonPanel.removeAll();
        buttonPanel.repaint();

        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton loadGame1 = createButton("Load Game 1");
        JButton loadGame2 = createButton("Load Game 2");
        JButton loadGame3 = createButton("Load Game 3");
        JButton backButton = createButton("Back");

        loadGame1.addActionListener(e -> {
            if (gameData.loadGame("1.txt")) {
                buttonPanel.removeAll();
                buttonPanel.repaint();
                new HomeBaseGUI(this);
                
            }
        });

        loadGame2.addActionListener(e -> {
            if (gameData.loadGame("2.txt")) {
                buttonPanel.removeAll();
                buttonPanel.repaint();
                new HomeBaseGUI(this);
            }
        });

        loadGame3.addActionListener(e -> {
            if (gameData.loadGame("3.txt")) {
                buttonPanel.removeAll();
                buttonPanel.repaint();
                new HomeBaseGUI(this);
            }
        });

        backButton.addActionListener(e -> {
            buttonPanel.removeAll();
            buttonPanel.repaint();
            showMainMenu();
        });

        buttonPanel.add(loadGame1, gbc);
        buttonPanel.add(loadGame2, gbc);
        buttonPanel.add(loadGame3, gbc);
        buttonPanel.add(backButton, gbc);

        layeredPane.add(buttonPanel, JLayeredPane.MODAL_LAYER);

        validate();
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(64, 69, 19));
        button.setForeground(new Color(255, 255, 0));
        return button;
    }
}
