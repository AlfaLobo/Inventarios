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
    public String director;
    public float saldo;
    public ArrayList<Empresa> empresas;
    public ArrayList<Negocio> negocios;
    public ArrayList<Proveedor> proveedores;
    public ArrayList<Empleado> empleados;
    public ArrayList<Cliente> clientes;
    public ArrayList<Servicio> servicios;
    public ArrayList<Producto> productos;
    public ArrayList<Compra> compras;
    public ArrayList<Venta> ventas;
    public ArrayList<Usuario> usuarios;

    public Usuario(String user, String password, String name, String lastname, float balance){
        permisos=true;
        usuario=user;
        contraseña=password;
        nombre=name;
        apellidos=lastname;
        saldo=balance;
        empresas = new ArrayList<>();
        negocios = new ArrayList<>();
        proveedores = new ArrayList<>();
        empleados = new ArrayList<>();
        clientes = new ArrayList<>();
        servicios = new ArrayList<>();
        productos = new ArrayList<>();
        compras = new ArrayList<>();
        ventas = new ArrayList<>();
        usuarios = new ArrayList<>();
        empleados.add(this);
        clientes.add(new Cliente(0, "Generico", null, null, null, null));
        empleados.add(new Empleado(0, name, lastname, 0.0f, null, null, null));
        proveedores.add(new Proveedor(0, "Generico", null, null, null));
        Archivos.crearDirectorio("\\Usuarios\\"+user);
        Archivos.guardarArchivo(this, "\\Usuarios\\"+user+"\\datos.txt");
    }
    public Usuario(int ID, String user, String password, String ceo, String name, String lastname, float salary, String phone, String email, GregorianCalendar birthday){
        super(ID, name, lastname, salary, phone, email, birthday);
        permisos=false;
        usuario=user;
        contraseña=password;
        director=ceo;
    }
}