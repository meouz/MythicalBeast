package Model.gamedata;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import Model.Main;
import Model.entity.Player;
import Model.gamesettings.ExceptionHandler;

public class GameData {
    private String filePath;

    public GameData(String filePath) {
        this.filePath = filePath;
    }

    public void saveGame(Player player, String eFile) {
        try {
            FileOutputStream fileOut = new FileOutputStream("client/" + filePath + eFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            System.out.println("Game saved successfully.");
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public boolean loadGame(String eFile) {
        try {
            FileInputStream fileIn = new FileInputStream("client/" + filePath + eFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Main.player = (Player) in.readObject();
            System.out.println("Game loaded successfully.");
            in.close();
            return true;
        } catch (Exception i) {
            ExceptionHandler eh = new ExceptionHandler(i);
            System.out.println(eh.getMessage());
        }
        return false;
    }
}
