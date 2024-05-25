package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MonsterSelection {
    public JFrame jframe;

    public MonsterSelection(JFrame frame, String current) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        jframe = frame;

        JPanel monsterPanel = new JPanel();
        monsterPanel.setLayout(new BorderLayout());
        monsterPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3, 100, 10));
        // mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 30));

        mainPanel.add(createMonsterPanel("Monster 1", "src/resource/monster1.jpg")); // Ganti dengan path gambar monster
        mainPanel.add(createMonsterPanel("Monster 2", "src/resource/monster2.jpg"));
        mainPanel.add(createMonsterPanel("Monster 3", "src/resource/monster3.jpg"));

        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.setBorder(BorderFactory.createEmptyBorder(100, 580, 0, 580));
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(1, 20));
        backPanel.add(backButton, BorderLayout.SOUTH);

        monsterPanel.add(mainPanel, BorderLayout.CENTER);
        monsterPanel.add(backPanel, BorderLayout.SOUTH);

        frame.add(monsterPanel, BorderLayout.CENTER);
        // frame.getContentPane().add(backPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new instance of SplashScreenDemo
                frame.getContentPane().removeAll();
                ;
                frame.getContentPane().repaint();
                // monsterPanel.removeAll();
                // monsterPanel.repaint();
                switch (current) {
                    case "Homebase":
                        new Homebase(frame);
                        break;
                    case "Dungeon":
                        new Dungeon(frame);
                        break;
                    case "BattleGUI":
                        new BattleGUI(frame);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private JPanel createMonsterPanel(String monsterName, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton reviveButton = createButton("Revive");
        panel.add(reviveButton, BorderLayout.NORTH);

        JLabel monsterImage;
        if (imagePath != null) {
            ImageIcon icon = new ImageIcon(imagePath);
            monsterImage = new JLabel();
            // icon = resizeImage(imagePath, 560, 720);
            monsterImage.setIcon(icon);
            monsterImage.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            monsterImage = new JLabel(monsterName, SwingConstants.CENTER);
            monsterImage.setForeground(Color.WHITE);
        }
        panel.add(monsterImage, BorderLayout.CENTER);

        JLabel statsLabel = new JLabel("Stats (Nama, Level, HP, Element, EP)", SwingConstants.CENTER);
        statsLabel.setForeground(Color.WHITE);
        panel.add(statsLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        JButton statButton = createButton("Stats");
        JButton mainButton = createButton("Main");

        buttonPanel.add(createButton("Train"));
        buttonPanel.add(statButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(createButton("Evolve"));

        buttonPanel.setBackground(new Color(64, 69, 19)); // Set background color to brown
        buttonPanel.setForeground(new Color(255, 255, 0)); // Set text color to white

        panel.add(buttonPanel, BorderLayout.SOUTH);

        statButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new instance of SplashScreenDemo
                showStats(jframe);
                
                // String monsterStat = "HP: 100\n";
                // monsterStat += "Level: 1";
                // JOptionPane.showConfirmDialog(jframe, monsterStat, "Monster Status",
                // JOptionPane.CLOSED_OPTION);
            }
        });

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        // button.setPreferredSize(new Dimension(200, 50)); // Set preferred size to
        // ensure uniform size
        button.setBackground(new Color(64, 69, 19)); // Set background color to brown
        button.setForeground(new Color(255, 255, 0)); // Set text color to white
        return button;
    }

    private static void showStats(JFrame frame) {
        ImageIcon icon = new ImageIcon("src/resource/monster1.jpg"); // Ganti dengan path ikon Anda
        JLabel label = new JLabel("HP: 100\nLevel: 1");
        label.setIcon(icon);
        JOptionPane.showConfirmDialog(frame, new Object[] { "HP: 100", "Level: 1" }, "Exit Option",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        // Tambahkan logika tambahan di sini berdasarkan respons pengguna (result)
    }

}
