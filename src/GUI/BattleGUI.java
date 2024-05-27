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
    private static Dungeon dungeon = new Dungeon();
    private static Monster enemy = dungeon.genMonster();
    private Arena arena = new Arena();
    private JLabel playerHPLabel, enemyHPLabel;
    private JFrame frame;
    private static JTextArea textArea;

    public BattleGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        this.frame = frame;
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (enemy.getHp() <= 0) {
            enemy = dungeon.genMonster();
        }
        if (!player.getMonsters()[0].isAlive()) {
            new MonsterSelection(frame, "BattleGUI");
        }

        // Top buttons panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton monsterButton = new JButton("Monster");
        JButton runButton = new JButton("Run");
        JButton itemButton = new JButton("Item");

        topPanel.add(monsterButton, BorderLayout.WEST);
        topPanel.add(runButton, BorderLayout.CENTER);
        topPanel.add(itemButton, BorderLayout.EAST);

        // Center area with player, VS label, and enemy
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 10, 0));

        // Generate enemy monster

        // Player panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        JLabel playerLabel = new JLabel("Player", SwingConstants.CENTER);
        playerLabel.setVerticalAlignment(SwingConstants.TOP);
        JLabel playerImageLabel = new JLabel(new ImageIcon(player.getMonsters()[0].getImage()));
        playerHPLabel = new JLabel("HP: " + player.getMonsters()[0].getHp() + "/" + player.getMonsters()[0].getMaxHP(),
                SwingConstants.CENTER);

        playerPanel.add(playerLabel, BorderLayout.NORTH);
        playerPanel.add(playerImageLabel, BorderLayout.CENTER);
        playerPanel.add(playerHPLabel, BorderLayout.SOUTH);

        // VS label
        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        vsLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Enemy panel
        JPanel enemyPanel = new JPanel(new BorderLayout());
        JLabel enemyLabel = new JLabel("Enemy", SwingConstants.CENTER);
        enemyLabel.setVerticalAlignment(SwingConstants.TOP);
        JLabel enemyImageLabel = new JLabel(new ImageIcon(enemy.getImage()));
        enemyHPLabel = new JLabel("HP: " + enemy.getHp() + "/" + enemy.getMaxHP(), SwingConstants.CENTER);

        enemyPanel.add(enemyLabel, BorderLayout.NORTH);
        enemyPanel.add(enemyImageLabel, BorderLayout.CENTER);
        enemyPanel.add(enemyHPLabel, BorderLayout.SOUTH);

        centerPanel.add(playerPanel);
        centerPanel.add(vsLabel);
        centerPanel.add(enemyPanel);

        // Bottom area with player actions and enemy actions
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        // Player actions
        JPanel playerActionsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton basicAttackButton = new JButton("Basic Attack");
        JButton specialAttackButton = new JButton("Special Attack");
        JButton elementAttackButton = new JButton("Element Attack");

        playerActionsPanel.add(basicAttackButton);
        playerActionsPanel.add(specialAttackButton);
        playerActionsPanel.add(elementAttackButton);

        // Enemy actions
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
        JButton catchEnemyButton = new JButton("Catch Enemy");

        enemyActionsPanel.add(enemyScrollPane, BorderLayout.CENTER);
        enemyActionsPanel.add(catchEnemyButton, BorderLayout.SOUTH);

        bottomPanel.add(playerActionsPanel);
        bottomPanel.add(enemyActionsPanel);

        // Setting up the layout for the main frame
        // frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        enemyScrollPane.setBackground(Color.magenta);

        catchEnemyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (arena.catchMonster(player, enemy)) {
                    new DungeonGUI(frame);
                    textArea = null;
                }
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

        itemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ItemSelection(frame, "BattleGUI");
            }
        });

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Arena arena = new Arena();
                boolean run = arena.run(player.getMonsters()[0], enemy);
                if (run) {
                    new DungeonGUI(frame);
                } else {
                    updateGUI(player.getMonsters()[0], enemy);
                    textArea = null;
                }
            }
        });

        monsterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MonsterSelection(frame, "BattleGUI");
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
                new GameOver(frame);
            } else {
                new MonsterSelection(frame, "BattleGUI");
            }
            return;
        }

        enemyHPLabel.setText("HP: " + enemy.getHp() + "/" + enemy.getMaxHP());
        playerHPLabel.setText("HP: " + player.getHp() + "/" + player.getMaxHP());
    }

    public void enemyAction() {
        switch ((int) (Math.random() * 3) + 1) {
            case 1:
                textArea.setText(textArea.getText() + enemy.basicAttack(player.getMonsters()[0], "Musuh"));
                break;
            case 2:
                textArea.setText(textArea.getText() + enemy.specialAttack(player.getMonsters()[0], "Musuh"));
                break;
            case 3:
                textArea.setText(textArea.getText() + enemy.elementAttack(player.getMonsters()[0], "Musuh"));
                break;
        }
    }
}
