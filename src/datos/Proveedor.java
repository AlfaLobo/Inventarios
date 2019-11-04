package datos;

import java.io.Serializable;

public class Proveedor implements Serializable {
    public String nombre;
    public String telefono;
    public String correo;
    public String direccion;
    public  float inversion;
    public float ganancias;
    public Proveedor(String name, String telephone, String email, String address) {
        nombre=name;
        telefono=telephone;
        correo=email;
        direccion=address;
    }
}