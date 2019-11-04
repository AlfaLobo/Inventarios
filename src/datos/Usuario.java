package datos;

import algoritmos.Archivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    public String id;
    public String contraseña;
    public String nombre;
    public String apellidos;
    public String empresa;
    public String directorio;
    public float totalcompra;
    public float totalventa;
    public float saldo;
    public List<Cliente> clientes = new ArrayList<>();
    public List<Empleado> empleados = new ArrayList<>();
    public List<Producto> productos = new ArrayList<>();
    public List<Proveedor> proveedores = new ArrayList<>();
    public List<Servicio> servicios = new ArrayList<>();

    public Usuario(String ID, String password, String name, String lastname, String enterprise, float balance){
        id = ID;
        contraseña = password;
        nombre = name;
        apellidos = lastname;
        empresa = enterprise;
        saldo = balance;
        clientes.add(new Cliente(0, "Generico", null, null, null));
        empleados.add(new Empleado(0, name, lastname, 0.0f, null, null, null));
        proveedores.add(new Proveedor("Generico", null, null, null));
        String directorio = System.getProperty("user.dir");
        directorio = directorio + "\\"+ID+".txt";
        this.directorio = directorio;
        Archivos.guardarArchivo(this);
    }
}