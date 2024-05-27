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
import Model.item.Item;
import Model.item.PotionHP;

public class ItemSelection {
    public JFrame jframe;
    private String current;
    private static Player player = Main.player;
    private JButton buyButton;
    private static Item[] item = Main.item;

    public ItemSelection(JFrame frame, String current) {
        this.current = current;
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        jframe = frame;

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3, 100, 10));
        // mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 30));

        for (int i = 0; i < item.length; i++) {
            JPanel createItemPanel = createItemPanel("Item", item[i].getImagePath(), i);
            mainPanel.add(createItemPanel);
        }

        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.setBorder(BorderFactory.createEmptyBorder(100, 580, 0, 580));
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(1, 20));
        backPanel.add(backButton, BorderLayout.SOUTH);

        itemPanel.add(mainPanel, BorderLayout.CENTER);
        itemPanel.add(backPanel, BorderLayout.SOUTH);

        frame.add(itemPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

    private JPanel createItemPanel(String itemName, String imagePath, int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel itemImage;
        if (imagePath != null) {
            ImageIcon icon = new ImageIcon(imagePath);
            itemImage = new JLabel();
            // icon = resizeImage(imagePath, 560, 720);
            itemImage.setIcon(icon);
            itemImage.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            itemImage = new JLabel(itemName, SwingConstants.CENTER);
            itemImage.setForeground(Color.WHITE);
        }
        panel.add(itemImage, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        JButton infoButton = createButton("Info");
        JButton useButton = createButton("Use");

        if (current.equalsIgnoreCase("homebase")) {
            buyButton = createButton("Buy");
            panel.add(buyButton, BorderLayout.NORTH);
            buyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showBuyDetail(jframe, index);
                }
            });
        }

        buttonPanel.add(infoButton);
        buttonPanel.add(useButton);

        buttonPanel.setBackground(new Color(64, 69, 19)); // Set background color to brown
        buttonPanel.setForeground(new Color(255, 255, 0)); // Set text color to white

        panel.add(buttonPanel, BorderLayout.SOUTH);

        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInfo(jframe, index);
            }
        });

        useButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (player.getItems()[index].getQty() <= 0 || player.getMonsters()[0].getHp() == player.getMonsters()[0].getMaxHP()) {
                    return;
                }
                player.getItems()[index].setQty(player.getItems()[index].getQty() - 1);
                ((PotionHP)player.getItems()[index]).effect(player.getMonsters()[0]);
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

    private static void showInfo(JFrame frame, int index) {
        JLabel label = new JLabel(((PotionHP) player.getItems()[index]).printDetail());
        JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Potion Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    // private static void showTrainDetail(JFrame frame, int index) {
    //     JLabel label = new JLabel("<html>EP: " + player.getEp() + "<br>EP yang dibutuhkan:"
    //             + player.getMonsters()[index].getMaxEP() + "</html>");
    //     int result = JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Train Monster",
    //             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    //     if (result == JOptionPane.YES_OPTION) {
    //         player.getMonsters()[index].levelUp();
    //     }
    // }

    private static void showBuyDetail(JFrame frame, int index) {
        String message = "<html>Price: " + item[index].getPrice() + "<br>Have: " + player.getItems()[index].getQty() + "</html>";
        // int result = showCustomButtonDialog("Custom Button Dialog", "Do you want to
        // proceed?", "Yes", "No", "Cancel");
        String option = "Buy-Cancel";
        String[] options = option.split("-");
        int result = showCustomButtonDialog("Buy Potion", message, options);

        // Display the result
        if (result == JOptionPane.YES_OPTION && player.getEp() >= item[index].getPrice()) {
            player.getItems()[index].setQty(player.getItems()[index].getQty() + 1);
            player.setEp(player.getEp() - item[index].getPrice());
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

// package GUI;

// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.BorderFactory;
// import javax.swing.ImageIcon;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.SwingConstants;

// import Model.Main;
// import Model.entity.Player;
// import Model.item.PotionHP;

// public class ItemSelection {
// public JFrame jframe;
// public static Player player = Main.player;

// public ItemSelection(JFrame frame, String current) {
// frame.getContentPane().removeAll();
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
// frame.setLayout(new BorderLayout());
// jframe = frame;

// JPanel itemPanel = new JPanel();
// itemPanel.setLayout(new BorderLayout());
// itemPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

// JPanel mainPanel = new JPanel();
// mainPanel.setLayout(new GridLayout(1, 3, 100, 10));
// // mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 30));

// mainPanel.add(createItemPanel("Heal Small",
// "src/resources/images/HealSmall.jpg")); // Ganti dengan path
// // gambar monster
// mainPanel.add(createItemPanel("Heal Medium",
// "src/resources/images/HealMedium.jpg"));
// mainPanel.add(createItemPanel("Heal Large",
// "src/resources/images/HealLarge.jpg"));

// JPanel backPanel = new JPanel(new BorderLayout());
// backPanel.setBorder(BorderFactory.createEmptyBorder(100, 580, 0, 580));
// JButton backButton = new JButton("Back");
// backButton.setPreferredSize(new Dimension(1, 20));
// backPanel.add(backButton, BorderLayout.SOUTH);

// itemPanel.add(mainPanel, BorderLayout.CENTER);
// itemPanel.add(backPanel, BorderLayout.SOUTH);

// frame.add(itemPanel, BorderLayout.CENTER);
// // frame.getContentPane().add(backPanel, BorderLayout.SOUTH);

// frame.setVisible(true);

// backButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// // Create a new instance of SplashScreenDemo
// frame.getContentPane().removeAll();
// frame.getContentPane().repaint();
// // itemPanel.removeAll();
// // itemPanel.repaint();
// switch (current) {
// case "Homebase":
// new Homebase(frame);
// break;
// case "Dungeon":
// new DungeonGUI(frame);
// break;
// case "BattleGUI":
// new BattleGUI(frame);
// break;
// default:
// break;
// }
// }
// });
// }

// private JPanel createItemPanel(String itemName, String imagePath) {
// JPanel panel = new JPanel();
// panel.setLayout(new BorderLayout());

// JButton buyButton = createButton("Buy");
// panel.add(buyButton, BorderLayout.NORTH);

// JLabel monsterImage;
// if (imagePath != null) {
// ImageIcon icon = new ImageIcon(imagePath);
// monsterImage = new JLabel();
// // icon = resizeImage(imagePath, 560, 720);
// monsterImage.setIcon(icon);
// monsterImage.setHorizontalAlignment(SwingConstants.CENTER);
// } else {
// monsterImage = new JLabel(itemName, SwingConstants.CENTER);
// monsterImage.setForeground(Color.WHITE);
// }
// panel.add(monsterImage, BorderLayout.CENTER);

// JLabel statsLabel = new JLabel("Stats (Nama, Level, HP, Element, EP)",
// SwingConstants.CENTER);
// statsLabel.setForeground(Color.WHITE);
// panel.add(statsLabel, BorderLayout.SOUTH);

// JPanel buttonPanel = new JPanel();
// buttonPanel.setLayout(new GridLayout(1, 4));
// JButton infoButton = createButton("Stats");
// JButton useButton = createButton("Use");
// buttonPanel.add(infoButton);
// buttonPanel.add(useButton);

// buttonPanel.setBackground(new Color(64, 69, 19)); // Set background color to
// brown
// buttonPanel.setForeground(new Color(255, 255, 0)); // Set text color to white

// panel.add(buttonPanel, BorderLayout.SOUTH);

// infoButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// // Create a new instance of SplashScreenDemo
// showInfo(jframe, int);
// }
// });

// useButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {

// }
// });

// return panel;
// }

// private JButton createButton(String text) {
// JButton button = new JButton(text);
// button.setFont(new Font("Arial", Font.BOLD, 18));
// // button.setPreferredSize(new Dimension(200, 50)); // Set preferred size to
// // ensure uniform size
// button.setBackground(new Color(64, 69, 19)); // Set background color to brown
// button.setForeground(new Color(255, 255, 0)); // Set text color to white
// return button;
// }

// private static void showInfo(JFrame frame, int index) {
// JLabel label = new JLabel(((PotionHP)
// player.getItems()[index]).printDetail());
// JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Potion Status",
// JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
// }

// }
