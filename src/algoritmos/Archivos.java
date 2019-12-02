package algoritmos;

import datos.Usuario;

import java.io.*;

public class Archivos {

    public static void eliminarUsusario(String dir1, String dir2){
        String directorio1 = System.getProperty("user.home");
        String directorio2 = System.getProperty("user.home");
        directorio1+="/Documents/AlfaLoboAdministrador/Usuarios/"+dir1;
        directorio2+="/Documents/AlfaLoboAdministrador/Usuarios/"+dir2;
        File file1 = new File(directorio1);
        File file2 = new File(directorio2);
        file1.delete();
        file2.delete();
    }
    public static void eliminarArchivo(String dir){
        String directorio = System.getProperty("user.home");
        directorio+="/Documents/AlfaLoboAdministrador/"+dir;
        File f = new File(directorio);
        f.delete();
    }
    public static void guardarArchivo(Usuario u) {
        String directorio = System.getProperty("user.home");
        try {
            FileOutputStream fos = new FileOutputStream(directorio+"/Documents/AlfaLoboAdministrador/Usuarios/"+u.usuario+"/datos.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Usuario cargarUsuario(String dir) {
        Usuario u = null;
        String directorio = System.getProperty("user.home");
        try {
            FileInputStream fis = new FileInputStream(directorio+"/Documents/AlfaLoboAdministrador/Usuarios/"+dir);
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
    public static boolean buscarArchivo(String dir) {
        String directorio = System.getProperty("user.home");
        File f = new File(directorio+"/Documents/AlfaLoboAdministrador/"+dir);
        if(f.exists()) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean buscarDirectorio(String ID) {
        String directorio = System.getProperty("user.home");
        File f = new File(directorio+"/Documents/"+ID);
        if(f.exists()) {
            return true;
        } else {
            return false;
        }
    }
    public static void crearDirectorio(String dir){
        String directorio = System.getProperty("user.home");
        File fl = new File(directorio+"/Documents/"+dir);
        fl.mkdir();
    }
}