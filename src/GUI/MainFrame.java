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

public class MainFrame extends JFrame {
    private BufferedImage splashImage;
    public JPanel buttonPanel;
    private JLabel messageLabel;
    private JLayeredPane layeredPane;
    public static JFrame frame;

    public MainFrame(JFrame frame) {
        layeredPane = new JLayeredPane();
        // Set title of the JFrame
        setTitle("Mythical Beast");

        // Set default close operation
        // Set the JFrame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(new BorderLayout());

        // Load the image
        try {
            File imgFile = new File("src/resource/mainmenu.jpg");
            if (imgFile.exists()) {
                splashImage = ImageIO.read(imgFile);
            } else {
                System.err.println("Image file not found: " + imgFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create layered pane
        add(layeredPane, BorderLayout.CENTER);

        // Add image background panel to layered pane
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

        // Initialize "Press any key" message label
        messageLabel = new JLabel("Press any key", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setForeground(new Color(255, 255, 255)); // Transparent white
        layeredPane.add(messageLabel, JLayeredPane.PALETTE_LAYER);

        // Add key listener to detect any key press
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Remove key listener to prevent further key presses
                removeKeyListener(this);

                // Remove "Press any key" message
                layeredPane.remove(messageLabel);

                // Show the main menu buttons
                showMainMenu();
            }
        });

        // Make sure the window is focusable to receive key events
        // setFocusable(true);
        // requestFocusInWindow();
        // doLayout();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        // Ensure the layered pane and its components are properly resized
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
        buttonPanel.setOpaque(false); // Make panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Margin between buttons
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ensure buttons are the same width

        JButton newGameButton = createButton("New Game");
        JButton loadGameButton = createButton("Load Game");
        JButton exitButton = createButton("Exit");

        newGameButton.addActionListener(e -> {
            // Code to start a new game
            layeredPane.removeAll();
            // layeredPane.repaint();
            // layeredPane.revalidate();

            // buttonPanel.removeAll();
            // frame.getContentPane().removeAll();
            // frame.getContentPane().repaint();

            // Homebase.showImage(frame);

            new Homebase(this);
        });

        loadGameButton.addActionListener(e -> showLoadGameMenu());

        exitButton.addActionListener(e -> {
            // Exit the application
            System.exit(0);
        });

        buttonPanel.add(newGameButton, gbc);
        buttonPanel.add(loadGameButton, gbc);
        buttonPanel.add(exitButton, gbc);

        layeredPane.add(buttonPanel, JLayeredPane.MODAL_LAYER);

        validate();
        // repaint();
    }

    public void showLoadGameMenu() {
        buttonPanel.removeAll();
        buttonPanel.repaint();

        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Make panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0); // Margin between buttons
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ensure buttons are the same width

        JButton loadGame1 = createButton("Load Game 1");
        JButton loadGame2 = createButton("Load Game 2");
        JButton loadGame3 = createButton("Load Game 3");
        JButton backButton = createButton("Back");

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
        // repaint();
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 50)); // Set preferred size to ensure uniform size
        button.setBackground(new Color(64, 69, 19)); // Set background color to brown
        button.setForeground(new Color(255, 255, 0)); // Set text color to white
        return button;
    }

    public static void main(String[] args) {
        // Create and show the splash screen
        frame = new JFrame();
        MainFrame splashScreen = new MainFrame(frame);
        splashScreen.setVisible(true);
    }
}
