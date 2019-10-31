package algoritmos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import algoritmos.*;
import interfaz.*;
import datos.*;

public class Temp {

    public static void crearArchivo() {
        String directorio = System.getProperty("user.dir");
        System.out.println("El directorio actual es: " + directorio);
        directorio = directorio + "\\archivo.txt";
        try {
            File myObj = new File(directorio);
            if (myObj.createNewFile()) {
                System.out.println("El archivo " + myObj.getName() + " ha sido creado en " + directorio);
            } else {
                System.out.println("El archivo ya existe en: " + directorio);
            }
        } catch (IOException e) {
            System.out.println("Sucedió un error.");
            e.printStackTrace();
        }
    }

    public static void menu() {
        int op = -1;
        Scanner sc = new Scanner(System.in);
        int nop = 12;
        System.out.println("1.- Test 1\n2.- Test 2\n3.- Test 3\n4.- Test 4\n5.- Test 5\n6.- Test 6\n7.- Test 7\n8.- Test 8\n9.- Test 9\n10.- Test 10\n11.- Test 11\n" + nop + ".- Fin\n");
        while (op != nop) {
            op = sc.nextInt();
            while (op < 1 || op > nop) {
                System.out.println("Qliphoth");
                op = sc.nextInt();
            }
            if (op == 1) {
                System.out.println("Keter");
                new Interfaz();
            } else if (op == 2) {
                System.out.println("Binah");
                crearArchivo();
            } else if (op == 3) {
                System.out.println("Chokhmah");
                Lista l = new Lista();
                l.productos.add(new Producto("22124124", "Conejo", "UvU", 1000, 5, 10));
                l.productos.add(new Producto("22124125", "Conejo ty", "UvUvU", 1000, 6, 12));
                for(int i = 0; i < l.productos.size(); i++) {
                    System.out.println("Mi nombre es "+l.productos.get(i).nombre+" y soy el elemento "+i+" de la ArrayList.");
                }
            } else if (op == 4) {
                System.out.println("Da'at");
            } else if (op == 5) {
                System.out.println("Gevurah");
            } else if (op == 6) {
                System.out.println("Chesed");
            } else if (op == 7) {
                System.out.println("Tiferet");
            } else if (op == 8) {
                System.out.println("Hod");
            } else if (op == 9) {
                System.out.println("Netzach");
            } else if (op == 10) {
                System.out.println("Yesod");
            } else if (op == 11) {
                System.out.println("Malkuth");
            }
        }
        System.out.println("Gehinnom");
    }
}
