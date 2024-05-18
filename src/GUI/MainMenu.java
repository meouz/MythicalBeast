package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class MainMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mythical Beast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        
        ImageIcon mainMenuImageIcon = new ImageIcon("src/resources/images/mainmenu.jpg");
        JLabel imageLabel = new JLabel(mainMenuImageIcon);
        imageLabel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        Dimension buttonSize = new Dimension(200, 35);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setMaximumSize(buttonSize);
        newGameButton.setMinimumSize(buttonSize);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loadGameButton.setPreferredSize(buttonSize);
        loadGameButton.setMaximumSize(buttonSize);
        loadGameButton.setMinimumSize(buttonSize);
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 170)));

        JPanel verticalCenterPanel = new JPanel();
        verticalCenterPanel.setLayout(new BoxLayout(verticalCenterPanel, BoxLayout.Y_AXIS));
        verticalCenterPanel.add(Box.createVerticalGlue());
        verticalCenterPanel.add(buttonPanel);
        verticalCenterPanel.add(Box.createVerticalGlue());
        verticalCenterPanel.setOpaque(false);

        imageLabel.add(verticalCenterPanel, BorderLayout.SOUTH);
        frame.add(imageLabel);
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Image mainMenuImage = mainMenuImageIcon.getImage();
                Image scaledMainMenuImage = mainMenuImage.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledMainMenuImage));
            }
        });
    }
}