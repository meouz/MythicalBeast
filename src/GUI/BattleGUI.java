package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class BattleGUI {

    public BattleGUI(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // Player panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        JLabel playerLabel = new JLabel("Player", SwingConstants.CENTER);
        playerLabel.setVerticalAlignment(SwingConstants.TOP);
        JLabel playerImageLabel = new JLabel(new ImageIcon("src/resource/monster2.jpg"));
        JLabel playerHPLabel = new JLabel("HP: 100 / 100", SwingConstants.CENTER);

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
        JLabel enemyImageLabel = new JLabel(new ImageIcon("src/resource/monster1.jpg"));
        JLabel enemyHPLabel = new JLabel("HP: 100 / 100", SwingConstants.CENTER);

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
        JTextArea enemyTextArea = new JTextArea(5, 20);
        enemyTextArea.setText("TEXTTEXTTEXTTEXTTEXT");
        enemyTextArea.setLineWrap(true);
        enemyTextArea.setWrapStyleWord(true);
        JScrollPane enemyScrollPane = new JScrollPane(enemyTextArea);
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

        frame.setVisible(true);
    }
}
