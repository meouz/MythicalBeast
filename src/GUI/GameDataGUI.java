package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Main;
import Model.entity.Player;
import Model.gamedata.GameData;

public class GameDataGUI {
    private static Player player = Main.player;
    private GameData gameData = new GameData("Save Game ");
    JButton saveButton1, saveButton2, saveButton3, loadButton1, loadButton2, loadButton3, backButton;

    public GameDataGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        saveButton1 = createSaveButton("Save Game 1", "1.txt");
        loadButton1 = createLoadButton("Load Game 1", "1.txt");
        saveButton2 = createSaveButton("Save Game 2", "2.txt");
        loadButton2 = createLoadButton("Load Game 2", "2.txt");
        saveButton3 = createSaveButton("Save Game 3", "3.txt");
        loadButton3 = createLoadButton("Load Game 3", "3.txt");
        backButton = new JButton("Back");

        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int buttonWidth = 250;
        int buttonHeight = 60;
        int margin = 220;

        saveButton1.setBounds((frameWidth / 2) - 400, frameHeight / 6, buttonWidth, buttonHeight);
        loadButton1.setBounds((frameWidth / 2) + 180, frameHeight / 6, buttonWidth, buttonHeight);
        saveButton2.setBounds((frameWidth / 2) - 400, 2 * (frameHeight / 6), buttonWidth, buttonHeight);
        loadButton2.setBounds((frameWidth / 2) + 180, 2 * (frameHeight / 6), buttonWidth, buttonHeight);
        saveButton3.setBounds((frameWidth / 2) - 400, 3 * (frameHeight / 6), buttonWidth, buttonHeight);
        loadButton3.setBounds((frameWidth / 2) + 180, 3 * (frameHeight / 6), buttonWidth, buttonHeight);
        backButton.setBounds((frameWidth / 2) - 120, frameHeight - margin, buttonWidth, buttonHeight);

        frame.add(saveButton1);
        frame.add(loadButton1);
        frame.add(saveButton2);
        frame.add(loadButton2);
        frame.add(saveButton3);
        frame.add(loadButton3);
        frame.add(backButton);

        saveButton1.setBackground(Color.WHITE);
        loadButton1.setBackground(Color.WHITE);
        saveButton2.setBackground(Color.WHITE);
        loadButton2.setBackground(Color.WHITE);
        saveButton3.setBackground(Color.WHITE);
        loadButton3.setBackground(Color.WHITE);
        backButton.setBackground(Color.WHITE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeBaseGUI(frame);
            }
        });
    }

    private JButton createSaveButton(String name, String eFile) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameData.saveGame(player, eFile);
                JOptionPane.showConfirmDialog(null, "Data saved: " + name, eFile,
                        JOptionPane.CLOSED_OPTION);
            }
        });
        return button;
    }

    private JButton createLoadButton(String name, String eFile) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameData.loadGame(eFile)) {
                    JOptionPane.showConfirmDialog(null, "Data loaded: " + name, eFile,
                            JOptionPane.CLOSED_OPTION);
                }
            }
        });
        return button;
    }
}
