package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DungeonGUI {
    public DungeonGUI(JFrame frame) {
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().removeAll();

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the buttons
        JButton monsterButton = new JButton("Monster");
        JButton homeBaseButton = new JButton("Home Base");
        JButton itemButton = new JButton("Item");
        JButton exploreButton = new JButton("Explore");

        // Create a panel for the top buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(monsterButton, BorderLayout.WEST);
        topPanel.add(homeBaseButton, BorderLayout.CENTER);
        topPanel.add(itemButton, BorderLayout.EAST);

        // Create a panel for the bottom button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(exploreButton, BorderLayout.CENTER);

        // Create the text area
        JTextArea textArea = new JTextArea();
        textArea.setText("TEXT\n".repeat(25)); // Dummy text to fill the area
        textArea.setEditable(false);

        // Add components to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);

        monsterButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new MonsterSelection(frame, "Dungeon");
        });

        homeBaseButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new Homebase(frame);
        });

        itemButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new ItemSelection(frame, "Dungeon");
        });

        exploreButton.addActionListener(e -> {
            if (Math.random() < 0.5) {
                frame.getContentPane().removeAll();
                new BattleGUI(frame);
            }
        });
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // DungeonInterface frame = new DungeonInterface();
    // });
    // }
}
