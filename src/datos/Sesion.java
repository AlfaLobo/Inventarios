package datos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sesion implements Serializable {
    public String id;
    public String contraseña;
    public Sesion(String ID, String password) {
        id=ID;
        contraseña=password;
        String directorio = System.getProperty("user.home");
        directorio = directorio + "/Documents/AlfaLoboAdministrador/sesion.txt";
        try {
            FileOutputStream fos = new FileOutputStream(directorio);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}