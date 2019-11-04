package datos;

import java.io.Serializable;

public class Empleado implements Serializable {
    public int id;
    public String nombre;
    public String apellidos;
    public float salario;
    public String telefono;
    public String correo;
    public String nacimiento;
    public float inversion;
    public float ganancias;

    public Empleado(int ID, String name, String lastname, float salary, String phone, String email, String birthday) {
        id=ID;
        nombre=name;
        apellidos=lastname;
        salario=salary;
        telefono=phone;
        correo=email;
        nacimiento=birthday;
    }
}