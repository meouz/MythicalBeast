package GUI;

import java.io.*;

public class GameDataManager {

    private static final String SAVE_FILE_PREFIX = "save_slot_";
    private static final String SAVE_FILE_EXTENSION = ".dat";

    public static void saveGame(String data, int slot) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_PREFIX + slot + SAVE_FILE_EXTENSION);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        }
    }

    public static String loadGame(int slot) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_PREFIX + slot + SAVE_FILE_EXTENSION);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (String) ois.readObject();
        }
    }
}
