package myPackage;

import interfaz.Inicio;

import javax.swing.*;

public class MyClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Inicio();
            }
        });
    }
}