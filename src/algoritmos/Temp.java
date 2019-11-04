package algoritmos;

import interfaz.Inicio;

import java.util.Scanner;

public class Temp {

    public static void menu() {
        int nop = 12;
        int op = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("1.- Keter\n2.- Binah\n3.- Chokhmah\n4.- Da'at\n5.- Gevurah\n6.- Chesed\n7.- Tiferet\n8.- Hod\n9.- Netzach\n10.- Yesod\n11.- Malkuth\n" + nop + ".- Gehinnom\n");
        while (op != nop) {
            op = sc.nextInt();
            while (op < 1 || op > nop) {
                System.out.println("Qliphoth");
                op = sc.nextInt();
            }
            if (op == 1) {
                System.out.println("Test 1");
                new Inicio();
            } else if (op == 2) {
                System.out.println("Test 2");
            } else if (op == 3) {
                System.out.println("Test 3");
            } else if (op == 4) {
                System.out.println("Test 4");
            } else if (op == 5) {
                System.out.println("Test 5");
            } else if (op == 6) {
                System.out.println("Test 6");
            } else if (op == 7) {
                System.out.println("Test 7");
            } else if (op == 8) {
                System.out.println("Test 8");
            } else if (op == 9) {
                System.out.println("Test 9");
            } else if (op == 10) {
                System.out.println("Test 10");
            } else if (op == 11) {
                System.out.println("Test 11");
            }
        }
        sc.close();
    }
}