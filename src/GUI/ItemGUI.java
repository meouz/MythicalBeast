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

public class ItemGUI {
    public JFrame frame;
    private String current;
    private Player player;
    private JButton buyButton;
    private Item[] items = Main.items;

    public ItemGUI(JFrame frame, String current) {
        this.current = current;
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        this.frame = frame;
        player = Main.player;

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3, 100, 10));

        for (int i = 0; i < items.length; i++) {
            JPanel createItemPanel = createItemPanel("Item", items[i].getImagePath(), i);
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
                        new HomeBaseGUI(frame);
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
                    showBuyDetail(frame, index, player, items);
                }
            });
        }

        buttonPanel.add(infoButton);
        buttonPanel.add(useButton);

        buttonPanel.setBackground(new Color(64, 69, 19));
        buttonPanel.setForeground(new Color(255, 255, 0));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInfo(frame, index, player);
            }
        });

        useButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.usePotion(player.getMonsters()[0], index);
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

    private static void showInfo(JFrame frame, int index, Player player) {
        JLabel label = new JLabel(((PotionHP) player.getItems()[index]).printDetail());
        JOptionPane.showConfirmDialog(frame, new Object[] { label }, "Potion Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showBuyDetail(JFrame frame, int index, Player player, Item[] items) {
        String message = "<html>Price: " + items[index].getPrice() + "<br>EP: " + player.getEp() + "<br>Have: "
                + player.getItems()[index].getQty() + "</html>";
        String option = "Buy-Cancel";
        String[] options = option.split("-");
        int result = showCustomButtonDialog("Buy Potion", message, options);

        if (result == JOptionPane.YES_OPTION && player.getEp() >= items[index].getPrice()) {
            player.getItems()[index].setQty(player.getItems()[index].getQty() + 1);
            player.setEp(player.getEp() - items[index].getPrice());
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