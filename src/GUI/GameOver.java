package GUI;

import javax.swing.*;
import java.awt.*;

public class GameOver {
    public GameOver(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 48));
        
        JButton mainMenuButton = new JButton("Exit Game😇");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.addActionListener(e -> {
            // Logic untuk kembali ke menu utama
            System.exit(0);
        });

        panel.add(Box.createVerticalGlue());
        panel.add(gameOverLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(mainMenuButton);
        panel.add(Box.createVerticalGlue());

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
