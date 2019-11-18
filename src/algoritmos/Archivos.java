package algoritmos;

import datos.Empleado;
import datos.Usuario;

import java.io.*;

public class Archivos {

    public static void crearDirectorio(String dir){
        String directorio = System.getProperty("user.dir");
        File fl = new File(directorio+dir);
        fl.mkdir();
    }

    public static boolean buscarArchivo(String dir) {
        String directorio = System.getProperty("user.dir");
        File f = new File(directorio+dir);
        if(f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean buscarDirectorio(String ID) {
        String directorio = System.getProperty("user.dir");
        File f = new File(directorio+ "\\"+ID);
        if(f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean buscarProducto(Usuario u, int provider, String name){
        for(int i = 0; i < u.proveedores.get(provider).productos.size(); i++) {
            if (u.productos.get(u.proveedores.get(provider).productos.get(i)).nombre.equals(name)){
                return true;
            }
        }
        return false;
    }
    public static boolean buscarProveedor(Usuario u, String name){
        for(int i = 0; i < u.proveedores.size(); i++) {
            if (u.proveedores.get(i).nombre.equals(name)){
                return true;
            }
        }
        return false;
    }

    public static void guardarArchivo(Usuario u, String dir) {
        String directorio = System.getProperty("user.dir");
        try {
            FileOutputStream fos = new FileOutputStream(directorio+dir);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Empleado cargarUsuario(String dir) {
        Usuario u = null;
        String directorio = System.getProperty("user.dir");
        try {
            FileInputStream fis = new FileInputStream(directorio+dir);
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