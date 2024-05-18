package GUI;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MenuScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Start Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Press any key to play", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(label);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                JOptionPane.showMessageDialog(frame, "Game is starting...");
            }
        });
        frame.setVisible(true);
        
        frame.requestFocusInWindow();
    }
}
