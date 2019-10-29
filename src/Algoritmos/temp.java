package Algoritmos;

import Main.MyClass;
import java.util.Scanner;

public class temp {
    public static void menuInventario() {
        int op=-1;
        Scanner sc = new Scanner(System.in);
        while (op != 3){
            System.out.println("Favor de elegir una opción.\n1.- Revisar inventario.\n2.- Añadir producto nuevo.\n3.-Regresar al menú.\n");
            op = sc.nextInt();
            while (op<1 || op>3){
                System.out.println("Opción no valida.");
                op = sc.nextInt();
            }
            if (op==1){
                System.out.println("Mostrando inventario.");
            }
            else if (op==2){
                System.out.println("Añadiendo producto.");
            }
        }
    }

    public static void menuVenta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Añadiendo producto.");
    }

    public static void menu() {
        int op = -1;
        Scanner sc = new Scanner(System.in);
        while (op != 3){
            System.out.println("Favor de elegir una opción.\n1.- Administar inventario.\n2.- Nueva venta\n3.- Cerrar programa.\n");
            op = sc.nextInt();
            while (op<1 || op>3){
                System.out.println("Opción no valida.");
                op = sc.nextInt();
            }
            if (op==1){
                menuInventario();
            }
            else if (op==2){
                System.out.println("Opción 2");
            }
        }
        System.out.println("Cerrando programa.");
    }
}
