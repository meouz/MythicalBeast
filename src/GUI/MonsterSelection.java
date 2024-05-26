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

import Model.Main;
import Model.entity.Player;
import Model.homebase.HomeBase;

public class MonsterSelection {
    public JFrame jframe;
    private String current;
    public static Player player = Main.player;
    private JButton trainButton, evolveButton, reviveButton, mainMonsterButton;

    public MonsterSelection(JFrame frame, String current) {
        this.current = current;
        frame.getContentPane().removeAll();
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

        for (int i = 0; i < player.getMonsterSize(); i++) {
            JPanel createMonsterJPanel = createMonsterPanel("Monster 1", player.getMonsters()[i].getImage(), i);
            mainPanel.add(createMonsterJPanel); // Ganti dengan path gambar monster
        }

        // JPanel createMonsterJPanel = createMonsterPanel("Monster 1",
        // player.getMonsters()[0].getImage(), 0);
        // JPanel createMonsterJPanel = createMonsterPanel("Monster 1",
        // player.getMonsters()[0].getImage(), 0);

        // mainPanel.add(createMonsterPanel("Monster 2",
        // "src/resources/images/monsterAngin.jpg",1));
        // mainPanel.add(createMonsterPanel("Monster 3",
        // "src/resources/images/monsterAir.jpg",2));

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
                // monsterPanel.removeAll();
                // monsterPanel.repaint();
                switch (current) {
                    case "Homebase":
                        new Homebase(frame);
                        break;
                    case "Dungeon":
                        new DungeonGUI(frame);
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

    private JPanel createMonsterPanel(String monsterName, String imagePath, int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

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

        if (index == 0) {
            mainMonsterButton = new JButton("<html>Main<br>Monster</html>");
            mainMonsterButton.setBackground(new Color(64, 69, 19)); // Set background color to brown
            mainMonsterButton.setForeground(new Color(255, 255, 0));
            mainMonsterButton.setEnabled(false);
            buttonPanel.add(mainMonsterButton);
        }

        if (current.equalsIgnoreCase("homebase") & player.getMonsters()[index].getHp() <= 0) {
            reviveButton = createButton("Revive");
            panel.add(reviveButton, BorderLayout.NORTH);
            
            reviveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    HomeBase home = new HomeBase();
                    home.revive(player.getMonsters()[index]);
                    new MonsterSelection(jframe, current);
                }
            });
        }

        if (current.equalsIgnoreCase("homebase")) {
            trainButton = createButton("Train");
            evolveButton = createButton("Evolve");
            buttonPanel.add(trainButton);
            buttonPanel.add(evolveButton);

            trainButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showTrainDetail(jframe, index);
                }
            });
    
            evolveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showEvolveDetail(jframe, index);
                    new MonsterSelection(jframe, current);
                }
            });
        }
        buttonPanel.add(statButton);
        buttonPanel.add(mainButton);

        buttonPanel.setBackground(new Color(64, 69, 19)); // Set background color to brown
        buttonPanel.setForeground(new Color(255, 255, 0)); // Set text color to white

        panel.add(buttonPanel, BorderLayout.SOUTH);

        statButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new instance of SplashScreenDemo
                showStats(jframe, index);

                // String monsterStat = "HP: 100\n";
                // monsterStat += "Level: 1";
                // JOptionPane.showConfirmDialog(jframe, monsterStat, "Monster Status",
                // JOptionPane.CLOSED_OPTION);
            }
        });

        mainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.changeMonster(index);
                new MonsterSelection(jframe, current);
            }
        });

        



        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(64, 69, 19)); // Set background color to brown
        button.setForeground(new Color(255, 255, 0)); // Set text color to white
        return button;
    }

    private static void showStats(JFrame frame, int index) {
        JLabel label = new JLabel(player.getMonsters()[index].getMonsterDetail());
        JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Monster Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showTrainDetail(JFrame frame, int index) {
        JLabel label = new JLabel("<html>EP: " + player.getEp() + "<br>EP yang dibutuhkan:"
                + player.getMonsters()[index].getMaxEP() + "</html>");
        int result = JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Train Monster",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            player.getMonsters()[index].levelUp();
        }
    }

    private static void showEvolveDetail(JFrame frame, int index) {
        String message = "<html>Element: " + player.getMonsters()[index].getElement().getClass().getSimpleName() + "</html>";
        // int result = showCustomButtonDialog("Custom Button Dialog", "Do you want to
        // proceed?", "Yes", "No", "Cancel");
        HomeBase home = new HomeBase();
        String option = home.getElementEvolve(player.getMonsters()[index]) + "-Cancel";
        String[] options = option.split("-");
        int result = showCustomButtonDialog("Evolve Monster", message, options);

        // Display the result
        if (result == JOptionPane.YES_OPTION) {
            home.evolve(player.getMonsters()[index], 1);
        } else if (result == JOptionPane.NO_OPTION) {
            home.evolve(player.getMonsters()[index], 2);
        }
    }

    public static int showCustomButtonDialog(String title, String message, String... options) {
        return JOptionPane.showOptionDialog(
                null, // Use default parent component
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, // Use default icon
                options, // Custom button text
                options[0]); // Default selection
    }

}
