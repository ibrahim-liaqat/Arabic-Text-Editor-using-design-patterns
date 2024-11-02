package PL;

import javax.swing.JOptionPane;
import BL.filemaininterface;

public class Delete {
    public Delete(filemaininterface o, String name) {
        int choice = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete this file?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            boolean success = o.deleteFile(name);
            if (success) {
                JOptionPane.showMessageDialog(null, "File deleted successfully.", "Delete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "File could not be deleted. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File deletion canceled.", "Delete", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
