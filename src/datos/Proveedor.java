package datos;
import java.io.Serializable;

public class Proveedor implements Serializable {
    String nombre;
    String telefono;
    String direccion;
    public Proveedor(String name, String telephone, String address) {
        nombre=name;
        telefono=telephone;
        direccion=address;
    }
}
