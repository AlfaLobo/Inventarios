package datos;

import java.io.Serializable;

public class Cliente implements Serializable  {
    public int id;
    public String nombre;
    public String apellidos;
    public String telefono;
    public String correo;
    public float ganancia=0;
    public String notas;

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