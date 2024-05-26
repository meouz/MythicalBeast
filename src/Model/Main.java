package Model;

import javax.swing.JFrame;

import GUI.MainFrame;
import Model.element.components.*;
import Model.entity.Monster;
import Model.entity.Player;

public class Main {
    public static Player player;
    public static String[] monsterImages = new String[5];

    public static void main(String[] args) {
        monsterImages[0] = "src/resources/images/monsterApi.jpg";
        monsterImages[1] = "src/resources/images/monsterAngin.jpg";
        monsterImages[2] = "src/resources/images/monsterAir.jpg";
        monsterImages[3] = "src/resources/images/monsterEs.jpg";
        monsterImages[4] = "src/resources/images/monsterTanah.jpg";

        // New Game
        Monster[] monsters = new Monster[5];
        monsters[0] = new Monster(null, new Air(), 10, monsterImages[0]);
        monsters[1] = new Monster(null, new Angin(), 10, monsterImages[1]);
        monsters[2] = new Monster(null, new Api(), 10, monsterImages[2]);
        monsters[3] = new Monster(null, new Es(), 10, monsterImages[3]);
        monsters[4] = new Monster(null, new Tanah(), 10, monsterImages[4]);

        player = new Player();
        player.setMonsters(monsters[(int) (Math.random() * 5)]);
        player.setMonsters(monsters[(int) (Math.random() * 5)]);
        player.setMonsters(monsters[(int) (Math.random() * 5)]);

        // Create and show the splash screen
        MainFrame splashScreen = new MainFrame(new JFrame());
        splashScreen.setVisible(true);
    }
}
