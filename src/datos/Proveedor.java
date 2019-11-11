package datos;

import java.io.Serializable;

public class Proveedor implements Serializable {
    public int id;
    public String nombre;
    public String telefono;
    public String correo;
    public String direccion;
    public  float inversion;
    public float ganancias;
    public Proveedor(int ID, String name, String telephone, String email, String address) {
        id=ID;
        nombre=name;
        telefono=telephone;
        correo=email;
        direccion=address;
    }
}