package datos;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Cliente implements Serializable  {
    public int id;
    public String nombre;
    public String apellidos;
    public String telefono;
    public String correo;
    public String notas;
    public float ganancia=0;

    public Cliente(Usuario u, String name) {
        id=u.clientes.size();
        nombre=name;
    }
    public Cliente(Usuario u, String name, String lastname, String phone, String email, String notes) {
        id=u.clientes.size();
        nombre=name;
        apellidos=lastname;
        telefono=phone;
        correo=email;
        notas=notes;
    }
}