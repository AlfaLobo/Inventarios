package algoritmos;

import datos.Usuario;
import java.io.*;

public class Archivos {

    public static void buscarArchivo() {

    }

    public static void editarArchivo(Usuario u) {
        try {
            System.out.println("Mi directorio es: " + u.directorio);
            FileOutputStream fos = new FileOutputStream(u.directorio);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Usuario cargarArchivos(Usuario u, String ID) {
        String directorio = System.getProperty("user.dir");
        directorio = directorio + "\\usuario"+ID+".txt";
        try {
            FileInputStream fis = new FileInputStream(directorio);
            ObjectInputStream ois = new ObjectInputStream(fis);
            u = (Usuario) ois.readObject();
            fis.close();
            ois.close();
        } catch (IOException i) {
            System.out.println("Algo salió mal.");
        } catch (ClassNotFoundException c) {
            System.out.println("Clase no encontrada.");
        }
        return u;
    }
}
