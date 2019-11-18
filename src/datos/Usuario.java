package datos;

import algoritmos.Archivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Usuario extends Empleado implements Serializable {
    public int empresa=0;
    public int negocio=0;
    public boolean permisos;
    public String usuario;
    public String contraseña;
    public int director;
    public float saldo;
    public ArrayList<Empresa> empresas;
    public ArrayList<Proveedor> proveedores;
    public ArrayList<Empleado> empleados;
    public ArrayList<Cliente> clientes;
    public ArrayList<Servicio> servicios;
    public ArrayList<Producto> productos;
    public ArrayList<Compra> compras;
    public ArrayList<Venta> ventas;
    public ArrayList<Usuario> usuarios;

    public Usuario(String user, String password, String name, String lastname, float balance){
        Archivos.crearDirectorio("\\Usuarios\\"+user);
        permisos=true;
        usuario=user;
        contraseña=password;
        nombre=name;
        apellidos=lastname;
        saldo=balance;
        empresas = new ArrayList<>();
        proveedores = new ArrayList<>();
        empleados = new ArrayList<>();
        clientes = new ArrayList<>();
        servicios = new ArrayList<>();
        productos = new ArrayList<>();
        compras = new ArrayList<>();
        ventas = new ArrayList<>();
        usuarios = new ArrayList<>();
        clientes.add(new Cliente(this, "Generico "+user));
        empleados.add(this);
        proveedores.add(new Proveedor(this, "Generico", null, null, null));
        Archivos.guardarArchivo(this, "\\Usuarios\\"+user+"\\datos.txt");
    }
    public Usuario(Usuario u, String user, String password, int ceo, String name, String lastname, float salary, String phone, String email, GregorianCalendar birthday){
        super(u, name, lastname, salary, phone, email, birthday);
        permisos=false;
        usuario=user;
        contraseña=password;
        director=ceo;
    }
}