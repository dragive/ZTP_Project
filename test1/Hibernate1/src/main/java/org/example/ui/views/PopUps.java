package org.example.ui.views;

import javax.swing.*;
import java.awt.*;

public class PopUps {

    public static boolean OkCancel(String message) {
        int result = JOptionPane.showConfirmDialog((Component) null, message,
                "Wymagane potwierdzenie", JOptionPane.OK_CANCEL_OPTION);
        return result==0;
    }

    public static void Error(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Wystąpił Błąd!",
                JOptionPane.ERROR_MESSAGE);
    }
}
