package Model.gamesettings;

import javax.swing.JOptionPane;

public class ExceptionHandler extends Exception {
    public static void Error(Exception e) {
        System.out.println(e.getMessage());
        JOptionPane.showConfirmDialog(null, "Data not available", "Error",
                JOptionPane.CLOSED_OPTION);
    }
}