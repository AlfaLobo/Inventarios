package datos;

import java.io.Serializable;

public class Cliente implements Serializable  {
    public int id;
    public String nombre;
    public String telefono;
    public String correo;
    public String nacimiento;
    public float ganancias;

    public Cliente(int ID, String name, String phone, String email, String date) {
        id=ID;
        nombre=name;
        telefono=phone;
        correo=email;
        nacimiento=date;
    }
}