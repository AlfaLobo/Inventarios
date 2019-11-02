package algoritmos;

import datos.Producto;
import datos.Usuario;
import interfaz.Inicio;

import java.util.Scanner;

public class Temp {

    public static void menu() {
        new Inicio();
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
                Usuario u = new Usuario("solecito","tyty", "Conejo", "uwu", "Conejo");
                Usuario k = new Usuario("pequeñaTierra","tyty", "UvU", "owo", "Topo");
                u.productos.add(new Producto("22124124", "Conejo", "UvU", 1000, 5, 10));
                u.productos.add(new Producto("22124125", "Conejo ty", "UvUvU", 1000, 6, 12));
                k.productos.add(new Producto("534631463", "Conejo1", "UvU1", 1000, 5, 10));
                k.productos.add(new Producto("5346314635", "Conejo ty1", "UvUvU1", 1000, 6, 12));
                for(int i = 0; i < u.productos.size(); i++) {
                    System.out.println("Mi nombre es "+u.productos.get(i).nombre+" y soy el elemento "+i+" de la ArrayList de productos.");
                }
                System.out.println("Mi contraseña es "+u.contraseña);
                for(int i = 0; i < k.productos.size(); i++) {
                    System.out.println("Mi nombre es "+k.productos.get(i).nombre+" y soy el elemento "+i+" de la ArrayList de productos.");
                }
                System.out.println("Mi contraseña es "+k.contraseña);
                Archivos.guardarArchivo(u);
                Archivos.guardarArchivo(k);
            } else if (op == 3) {
                System.out.println("Test 3");
                Usuario u = Archivos.cargarArchivos("solecito");
                for(int i = 0; i < u.productos.size(); i++) {
                    System.out.println("Mi nombre es "+u.productos.get(i).nombre+" y soy el elemento "+i+" de la ArrayList de productos.");
                }
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