package Model;

import javax.swing.JFrame;

import GUI.MainFrame;
import Model.element.components.*;
import Model.entity.Monster;
import Model.entity.Player;
import Model.item.Item;
import Model.item.PotionHP;
import Model.element.Element;

public class Main {
    public static Player player;
    public static String[] monsterImages = new String[5];
    public static Item[] item = new Item[3];

    public static void main(String[] args) {
        player = new Player();
        monsterImages[0] = "src/resources/images/monsterApi.jpg";
        monsterImages[1] = "src/resources/images/monsterAngin.jpg";
        monsterImages[2] = "src/resources/images/monsterAir.jpg";
        monsterImages[3] = "src/resources/images/monsterEs.jpg";
        monsterImages[4] = "src/resources/images/monsterTanah.jpg";

        item[0] = new PotionHP("Small Heal Potion", "src/resources/images/HealSmall.jpg", 10, 10);
        item[1] = new PotionHP("Medium Heal Potion", "src/resources/images/HealMedium.jpg", 40, 50);
        item[2] = new PotionHP("Large Heal Potion", "src/resources/images/HealLarge.jpg", 100, 100);

        player.getItems()[0] = new PotionHP("Small Heal Potion", "src/resources/images/HealSmall.jpg", 0, 99, 10, 10);
        player.getItems()[1] = new PotionHP("Medium Heal Potion", "src/resources/images/HealMedium.jpg", 0, 99, 40, 50);
        player.getItems()[2] = new PotionHP("Large Heal Potion", "src/resources/images/HealLarge.jpg", 0, 99, 100, 100);

        // ((PotionHP)item[0]).effect(player.getMonsters()[0]);

        Element[] element = new Element[5];
        element[0] = new Api();
        element[1] = new Angin();
        element[2] = new Air();
        element[3] = new Es();
        element[4] = new Tanah();
        // New Game
        // Monster[] monsters = new Monster[5];

        int random = (int) (Math.random() * 5);
        player.setMonsters(new Monster(null, element[random], 10, monsterImages[random]));
        // random = (int) (Math.random() * 5);
        // player.setMonsters(new Monster(null, element[random], 10, monsterImages[random]));
        // random = (int) (Math.random() * 5);
        // player.setMonsters(new Monster(null, element[random], 10,
        // monsterImages[random]));
        // player.setMonsters(monsters[(int) (Math.random() * 5)]);
        // player.setMonsters(monsters[(int) (Math.random() * 5)]);

        // Create and show the splash screen
        MainFrame splashScreen = new MainFrame(new JFrame());
        splashScreen.setVisible(true);
    }
}
