package Model.gamesettings;

import javax.swing.JOptionPane;

public class ExceptionHandler extends Exception {
    public ExceptionHandler(Exception e) {
        JOptionPane.showConfirmDialog(null, "Data not available", "Error",
                JOptionPane.CLOSED_OPTION);
    }
}