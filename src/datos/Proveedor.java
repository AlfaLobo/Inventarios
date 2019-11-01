package datos;

import java.io.Serializable;

public class Proveedor implements Serializable {
    public String nombre;
    public String telefono;
    public String direccion;
    public Proveedor(String name, String telephone, String address) {
        nombre=name;
        telefono=telephone;
        direccion=address;
    }
}
