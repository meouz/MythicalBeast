package GUI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.Main;
import Model.dungeon.Dungeon;

public class DungeonGUI {
    private static JTextArea textArea;
    private Dungeon dungeon = new Dungeon(Main.player.getMonsters()[0]);
    private JButton monsterButton, homeBaseButton, itemButton, exploreButton;

    public DungeonGUI(JFrame frame) {
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());

        monsterButton = new JButton("Monster");
        homeBaseButton = new JButton("Home Base");
        itemButton = new JButton("Item");
        exploreButton = new JButton("Explore");
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(monsterButton, BorderLayout.WEST);
        topPanel.add(homeBaseButton, BorderLayout.CENTER);
        topPanel.add(itemButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(exploreButton, BorderLayout.CENTER);
        if (textArea == null) {
            Font font = new Font("Arial", Font.PLAIN, 30);
            textArea = new JTextArea();
            textArea.setFont(font);
            textArea.setText("Anda memasuki Dungeon");
        }
        textArea.setEditable(false);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        monsterButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new MonsterGUI(frame, "Dungeon");
        });

        homeBaseButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new HomeBaseGUI(frame);
            textArea = null;
        });

        itemButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new ItemGUI(frame, "Dungeon");
        });

        exploreButton.addActionListener(e -> {
            textArea.setText(textArea.getText() + "\nExploring...");
            String result = dungeon.explore();
            textArea.append(result);
            if (result.equalsIgnoreCase("\nbertemu monster")) {
                frame.getContentPane().removeAll();
                new BattleGUI(frame);
            }
        });
    }
}
