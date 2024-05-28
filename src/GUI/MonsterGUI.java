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

public class MonsterGUI {
    public JFrame jframe;
    private String current;
    public Player player = Main.player;
    private HomeBase homeBase = new HomeBase(player);
    private JButton trainButton, evolveButton, reviveButton, mainMonsterButton;

    public MonsterGUI(JFrame frame, String current) {
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

        for (int i = 0; i < player.getMonsterSize(); i++) {
            JPanel createMonsterJPanel = createMonsterPanel("Monster", player.getMonsters()[i].getImage(), i);
            mainPanel.add(createMonsterJPanel);
        }

        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.setBorder(BorderFactory.createEmptyBorder(100, 580, 0, 580));
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(1, 20));
        backPanel.add(backButton, BorderLayout.SOUTH);

        monsterPanel.add(mainPanel, BorderLayout.CENTER);
        monsterPanel.add(backPanel, BorderLayout.SOUTH);

        frame.add(monsterPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (current) {
                    case "Homebase":
                        new HomeBaseGUI(frame);
                        break;
                    case "Dungeon":
                        new DungeonGUI(frame);
                        break;
                    case "BattleGUI":
                        if (player.getMonsters()[0].isAlive()) {
                            new BattleGUI(frame);
                        }
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
            monsterImage.setIcon(icon);
            monsterImage.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            monsterImage = new JLabel(monsterName, SwingConstants.CENTER);
            monsterImage.setForeground(Color.WHITE);
        }
        panel.add(monsterImage, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        JButton statButton = createButton("Stats");
        JButton mainButton = createButton("Main");

        if (index == 0) {
            mainMonsterButton = new JButton("<html>Main<br>Monster</html>");
            mainMonsterButton.setBackground(new Color(64, 69, 19));
            mainMonsterButton.setForeground(new Color(255, 255, 0));
            mainMonsterButton.setEnabled(false);
            buttonPanel.add(mainMonsterButton);
        }

        if (current.equalsIgnoreCase("homebase") & player.getMonsters()[index].getHp() <= 0) {
            reviveButton = createButton("Revive");
            panel.add(reviveButton, BorderLayout.NORTH);

            reviveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    homeBase.revive(player.getMonsters()[index]);
                    new MonsterGUI(jframe, current);
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
                    showTrainDetail(jframe, index, player, homeBase);
                }
            });

            evolveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showEvolveDetail(jframe, index, player, homeBase);
                    new MonsterGUI(jframe, current);
                }
            });
        }
        buttonPanel.add(statButton);
        buttonPanel.add(mainButton);

        buttonPanel.setBackground(new Color(64, 69, 19));
        buttonPanel.setForeground(new Color(255, 255, 0));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        statButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStats(jframe, index, player);
            }
        });

        mainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.changeMonster(index);
                new MonsterGUI(jframe, current);
            }
        });

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(64, 69, 19));
        button.setForeground(new Color(255, 255, 0));
        return button;
    }

    private static void showStats(JFrame frame, int index, Player player) {
        JLabel label = new JLabel(player.getMonsters()[index].getMonsterDetail());
        JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Monster Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showTrainDetail(JFrame frame, int index, Player player, HomeBase homeBase) {
        JLabel label = new JLabel("<html>EP: " + player.getEp() + "<br>EP yang dibutuhkan:"
                + player.getMonsters()[index].getMaxEP() + "</html>");
        int result = JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Train Monster",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            homeBase.train(player.getMonsters()[index]);
        }
    }

    private static void showEvolveDetail(JFrame frame, int index, Player player, HomeBase homeBase) {
        String message = "<html>Element: " + player.getMonsters()[index].getElement().getClass().getSimpleName()
                + "</html>";
        String option = homeBase.getElementEvolve(player.getMonsters()[index]) + "-Cancel";
        String[] options = option.split("-");
        int result = showCustomButtonDialog("Evolve Monster", message, options);

        if (result == JOptionPane.YES_OPTION) {
            homeBase.evolve(player.getMonsters()[index], 1);
        } else if (result == JOptionPane.NO_OPTION) {
            homeBase.evolve(player.getMonsters()[index], 2);
        }
    }

    public static int showCustomButtonDialog(String title, String message, String... options) {
        return JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
    }

}
