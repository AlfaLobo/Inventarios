package datos;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Empleado implements Serializable {
    public int id;
    public String nombre;
    public String apellidos;
    public float salario;
    public String telefono;
    public String correo;
    public GregorianCalendar nacimiento;
    public float inversion=0;
    public float ganancia=0;

    public Empleado(){

    }
    public Empleado(Usuario u){
        id=u.empleados.size();
        nombre=u.nombre;
        apellidos=u.apellidos;
    }
    public Empleado(Usuario u, String name, String lastname, float salary, String phone, String email, GregorianCalendar birthday) {
        id=u.empleados.size();
        nombre=name;
        apellidos=lastname;
        salario=salary;
        telefono=phone;
        correo=email;
        nacimiento=birthday;
    }
}