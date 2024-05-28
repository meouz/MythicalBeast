package Model;

import javax.swing.JFrame;

import GUI.MainGUI;
import Model.element.components.*;
import Model.entity.Monster;
import Model.entity.Player;
import Model.item.Item;
import Model.item.PotionHP;
import Model.element.Element;

public class Main {
    public static Player player;
    public static String[] monsterImages = {
            "src/resources/images/monsterApi.jpg",
            "src/resources/images/monsterAngin.jpg",
            "src/resources/images/monsterAir.jpg",
            "src/resources/images/monsterEs.jpg",
            "src/resources/images/monsterTanah.jpg"
    };
    public static Item[] items = {
            new PotionHP("Small Heal Potion", "src/resources/images/HealSmall.jpg", 10, 10),
            new PotionHP("Medium Heal Potion", "src/resources/images/HealMedium.jpg", 40, 50),
            new PotionHP("Large Heal Potion", "src/resources/images/HealLarge.jpg", 100, 100)
    };

    public static Element[] element = { new Api(), new Angin(), new Air(), new Es(), new Tanah() };

    public static void main(String[] args) {
        player = new Player();

        int random = (int) (Math.random() * 5);
        player.setMonsters(new Monster(random, element[random], 7, monsterImages[random]));

        MainGUI mainGUI = new MainGUI(new JFrame());
        mainGUI.setVisible(true);
    }
}
