package Main;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.io.File;

class Interfaz {

}

class Producto {
    String id;
    String Nombre;
    String Marca;
    int cantidad;
    float precio;
}

public class MyClass {
    public static void ordenarInventario(){

    }

    public static void buscarArchivo(){

    }

    public static void editarArchivo(){

    }

    public static void imprimirRecibo{

    }

    public static void cargarArchivos(){
        String directorio = System.getProperty("user.dir");
        System.out.println("El directorio actual es: " + directorio);
        directorio = directorio+"\archivo.txt"
        try {
            File myObj = new File(directorio);
            if (myObj.createNewFile()) {
                System.out.println("El archivo ha sido creado: " + myObj.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Sucedió un error.");
            e.printStackTrace();
        }
    }

    public static void menuInventario(){
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
    public static void menuVenta(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Añadiendo producto.");
    }

    public static void menu(){
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
                MyClass.menuInventario();
            }
            else if (op==2){
                System.out.println("Opción 2");
            }
        }
        System.out.println("Cerrando programa.");
    }
    public static void main(String[] args) {
        //JFrame f=new JFrame();
        //JButton b=new JButton("click");
        //b.setBounds(130,100,100, 40);
        //f.add(b);
        //f.setSize(400,500);
        //f.setLayout(null);
        //f.setVisible(true);
        MyClass.menu();
        MyClass.cargarArchivos();
    }
}