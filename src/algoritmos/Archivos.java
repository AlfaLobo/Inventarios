package algoritmos;

import datos.Producto;
import datos.Usuario;

import java.io.*;
import java.util.ArrayList;

public class Archivos {

    public static boolean buscarArchivo(String ID) {
        String directorio = System.getProperty("user.dir");
        directorio = directorio + "\\"+ID+".txt";
        File f = new File(directorio);
        if(f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean buscarProducto(Usuario u, String name, String brand){
        for(int i = 0; i < u.productos.size(); i++) {
            if (u.productos.get(i).nombre.equals(name) && u.productos.get(i).marca.equals(brand)){
                return true;
            }
        }
        return false;
    }

    public static void guardarArchivo(Usuario u) {
        try {
            FileOutputStream fos = new FileOutputStream(u.directorio);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Usuario cargarUsuario(String ID) {
        Usuario u = null;
        String directorio = System.getProperty("user.dir");
        directorio = directorio + "\\"+ID+".txt";
        try {
            FileInputStream fis = new FileInputStream(directorio);
            ObjectInputStream ois = new ObjectInputStream(fis);
            u = (Usuario) ois.readObject();
            fis.close();
            ois.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return u;
    }
}
