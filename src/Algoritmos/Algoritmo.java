package Algoritmos;

import java.io.File;
import java.io.IOException;

public class Algoritmo {
    public static void ordenarInventario() {

    }

    public static void buscarArchivo() {

    }

    public static void editarArchivo() {

    }

    public static void imprimirRecibo() {

    }

    public static void cargarArchivos() {
        String directorio = System.getProperty("user.dir");
        System.out.println("El directorio actual es: " + directorio);
        directorio = directorio+"\\archivo.txt";
        try {
            File myObj = new File(directorio);
            if (myObj.createNewFile()) {
                System.out.println("El archivo " + myObj.getName()+"ha sido creado en "+directorio);
            } else {
                System.out.println("El archivo ya existe en "+directorio);
            }
        } catch (IOException e) {
            System.out.println("Sucedió un error.");
            e.printStackTrace();
        }
    }
}
