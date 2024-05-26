package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        Dimension buttonSize = new Dimension(250, 60);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setMaximumSize(buttonSize);
        newGameButton.setMinimumSize(buttonSize);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame, "New Game button pressed.");
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

                JOptionPane.showMessageDialog(frame, "Load Game button pressed.");
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

        JPanel verticalCenterPanel = new JPanel();
        verticalCenterPanel.setLayout(new BoxLayout(verticalCenterPanel, BoxLayout.Y_AXIS));
        verticalCenterPanel.add(Box.createVerticalGlue());
        verticalCenterPanel.add(buttonPanel);
        verticalCenterPanel.add(Box.createVerticalGlue());

        mainPanel.add(verticalCenterPanel, BorderLayout.WEST);
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}