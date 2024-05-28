package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Model.Main;
import Model.dungeon.Arena;
import Model.entity.Monster;
import Model.entity.Player;
import Model.dungeon.Dungeon;

public class BattleGUI {
    private static Player player = Main.player;
    private static Dungeon dungeon = new Dungeon(player.getMonsters()[0]);
    private static Monster enemy = dungeon.genMonster();
    private Arena arena = new Arena(player, enemy);
    private static JTextArea textArea;
    private JFrame frame;
    private JLabel playerHPLabel, enemyHPLabel, playerImageLabel, playerLabel, vsLabel, enemyLabel, enemyImageLabel;
    private JButton monsterButton, runButton, itemButton, basicAttackButton, specialAttackButton, elementAttackButton,
            catchEnemyButton;

    public BattleGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        this.frame = frame;

        if (enemy.getHp() <= 0) {
            enemy = dungeon.genMonster();
        }
        if (!player.getMonsters()[0].isAlive()) {
            new MonsterGUI(frame, "BattleGUI");
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        monsterButton = new JButton("Monster");
        runButton = new JButton("Run");
        itemButton = new JButton("Item");

        topPanel.add(monsterButton, BorderLayout.WEST);
        topPanel.add(runButton, BorderLayout.CENTER);
        topPanel.add(itemButton, BorderLayout.EAST);

        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 10, 0));

        JPanel playerPanel = new JPanel(new BorderLayout());
        playerLabel = new JLabel("Player", SwingConstants.CENTER);
        playerLabel.setVerticalAlignment(SwingConstants.TOP);
        playerImageLabel = new JLabel(new ImageIcon(player.getMonsters()[0].getImage()));
        playerHPLabel = new JLabel("HP: " + player.getMonsters()[0].getHp() + "/" + player.getMonsters()[0].getMaxHP(),
                SwingConstants.CENTER);

        playerPanel.add(playerLabel, BorderLayout.NORTH);
        playerPanel.add(playerImageLabel, BorderLayout.CENTER);
        playerPanel.add(playerHPLabel, BorderLayout.SOUTH);

        vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        vsLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyLabel = new JLabel("Enemy", SwingConstants.CENTER);
        enemyLabel.setVerticalAlignment(SwingConstants.TOP);
        enemyImageLabel = new JLabel(new ImageIcon(enemy.getImage()));
        enemyHPLabel = new JLabel("HP: " + enemy.getHp() + "/" + enemy.getMaxHP(), SwingConstants.CENTER);

        enemyPanel.add(enemyLabel, BorderLayout.NORTH);
        enemyPanel.add(enemyImageLabel, BorderLayout.CENTER);
        enemyPanel.add(enemyHPLabel, BorderLayout.SOUTH);

        centerPanel.add(playerPanel);
        centerPanel.add(vsLabel);
        centerPanel.add(enemyPanel);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        JPanel playerActionsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        basicAttackButton = new JButton("Basic Attack");
        specialAttackButton = new JButton("Special Attack");
        elementAttackButton = new JButton("Element Attack");

        playerActionsPanel.add(basicAttackButton);
        playerActionsPanel.add(specialAttackButton);
        playerActionsPanel.add(elementAttackButton);

        JPanel enemyActionsPanel = new JPanel(new BorderLayout());
        if (textArea == null) {
            Font font = new Font("Arial", Font.PLAIN, 15);
            textArea = new JTextArea();
            textArea.setFont(font);
            textArea.setEditable(false);
            textArea.setText("Memasuki Pertarungan");
        }
        JScrollPane enemyScrollPane = new JScrollPane(textArea);
        enemyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        enemyScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        enemyScrollPane.setPreferredSize(new Dimension(200, 100));
        catchEnemyButton = new JButton("Catch Enemy");

        enemyActionsPanel.add(enemyScrollPane, BorderLayout.CENTER);
        enemyActionsPanel.add(catchEnemyButton, BorderLayout.SOUTH);

        bottomPanel.add(playerActionsPanel);
        bottomPanel.add(enemyActionsPanel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        enemyScrollPane.setBackground(Color.magenta);

        monsterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MonsterGUI(frame, "BattleGUI");
            }
        });

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean run = arena.run();
                if (run) {
                    new DungeonGUI(frame);
                    textArea = null;
                } else {
                    updateGUI(player.getMonsters()[0], enemy);
                }
            }
        });

        itemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ItemGUI(frame, "BattleGUI");
            }
        });

        basicAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append(player.getMonsters()[0].basicAttack(enemy, "Anda"));
                updateGUI(player.getMonsters()[0], enemy);
            }
        });

        specialAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append(player.getMonsters()[0].specialAttack(enemy, "Anda"));
                updateGUI(player.getMonsters()[0], enemy);
            }
        });

        elementAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append(player.getMonsters()[0].elementAttack(enemy, "Anda"));
                updateGUI(player.getMonsters()[0], enemy);
            }
        });

        catchEnemyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (arena.catchMonster()) {
                    new DungeonGUI(frame);
                    textArea = null;
                }
            }
        });

        frame.setVisible(true);
    }

    public void updateGUI(Monster player, Monster enemy) {
        if (!enemy.isAlive()) {
            BattleGUI.player.setEp(BattleGUI.player.getEp() + 100);
            new DungeonGUI(frame);
            textArea = null;
            return;
        }
        enemyAction();
        if (!player.isAlive()) {
            int temp = 0;
            for (int i = 0; i < Main.player.getMonsterSize(); i++) {
                if (!Main.player.getMonsters()[i].isAlive()) {
                    temp++;
                }
            }
            if (temp == Main.player.getMonsterSize()) {
                textArea = null;
                new GameOverGUI(frame);
            } else {
                new MonsterGUI(frame, "BattleGUI");
            }
            return;
        }

        enemyHPLabel.setText("HP: " + enemy.getHp() + "/" + enemy.getMaxHP());
        playerHPLabel.setText("HP: " + player.getHp() + "/" + player.getMaxHP());
    }

    public void enemyAction() {
        switch ((int) (Math.random() * 3) + 1) {
            case 1:
                textArea.append(enemy.basicAttack(player.getMonsters()[0], "Musuh"));
                break;
            case 2:
                textArea.append(enemy.specialAttack(player.getMonsters()[0], "Musuh"));
                break;
            case 3:
                textArea.append(enemy.elementAttack(player.getMonsters()[0], "Musuh"));
                break;
        }
    }
}
