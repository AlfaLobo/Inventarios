package datos;

import java.io.Serializable;
import java.util.ArrayList;

public class Proveedor implements Serializable {
    public int id;
    public String nombre;
    public String telefono;
    public String correo;
    public String direccion;
    public  float inversion=0;
    public float ganancia=0;
    public String notas;
    public ArrayList<Integer> productos = new ArrayList();
    public Proveedor(Usuario u, String name, String telephone, String email, String address, String notes) {
        id=u.proveedores.size();
        nombre=name;
        telefono=telephone;
        correo=email;
        direccion=address;
        notas=notes;
    }
}