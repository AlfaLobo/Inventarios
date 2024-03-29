package datos;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Empleado implements Serializable {
    public int id;
    public String nombre;
    public String apellidos;
    public String telefono;
    public String correo;
    public GregorianCalendar nacimiento;
    public float salario;
    public float inversion=0;
    public String notas;

    public Empleado(Usuario u, String name, String lastname, float salary, String phone, String email, GregorianCalendar birthday, String notes) {
        id=u.empleados.size();
        nombre=name;
        apellidos=lastname;
        salario=salary;
        telefono=phone;
        correo=email;
        nacimiento=birthday;
        notas=notes;
    }
}