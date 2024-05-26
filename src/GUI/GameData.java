package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameData {
    JButton saveButton1, saveButton2, saveButton3, loadButton1, loadButton2, loadButton3, backButton;
    
    public GameData(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Create the buttons
        saveButton1 = createSaveButton("Save Game 1");
        loadButton1 = createLoadButton("Load Game 1");
        saveButton2 = createSaveButton("Save Game 2");
        loadButton2 = createLoadButton("Load Game 2");
        saveButton3 = createSaveButton("Save Game 3");
        loadButton3 = createLoadButton("Load Game 3");
        backButton = new JButton("Back");

        // Set initial bounds for buttons (x, y, width, height)
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

        // Add buttons to the frame
        frame.add(saveButton1);
        frame.add(loadButton1);
        frame.add(saveButton2);
        frame.add(loadButton2);
        frame.add(saveButton3);
        frame.add(loadButton3);
        frame.add(backButton);

        // Set background color

        // Set button background color
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
                new Homebase(frame);
            }
        });
    }

    private JButton createSaveButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(name);
            }
        });
        return button;
    }

    private JButton createLoadButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame(name);
            }
        });
        return button;
    }

    private void saveGame(String gameName) {
        String fileName = gameName + ".txt";
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            out.println("Game data for " + gameName);
            JOptionPane.showInputDialog(this, "Game saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGame(String gameName) {
        String fileName = gameName + ".txt";
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String gameData = in.readLine();
            JOptionPane.showInputDialog(this, "Loaded data: " + gameData);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showInputDialog(this, "Failed to load game: " + fileName);
        }
    }
}
