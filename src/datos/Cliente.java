package datos;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Cliente implements Serializable  {
    public int id;
    public String nombre;
    public String apellidos;
    public String telefono;
    public String correo;
    public GregorianCalendar nacimiento;
    public float ganancia=0;

    public Cliente(Usuario u, String name) {
        id=u.clientes.size();
        nombre=name;
    }
    public Cliente(Usuario u, String name, String lastname, String phone, String email, GregorianCalendar date) {
        id=u.clientes.size();
        nombre=name;
        apellidos=lastname;
        telefono=phone;
        correo=email;
        nacimiento=date;
    }
}