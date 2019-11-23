package datos;

import algoritmos.Archivos;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    public String usuario;
    public String contraseña;
    public String nombre;
    public String apellidos;
    public float saldo;
    public float inversion;
    public float ganancia;
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
        usuario=user;
        contraseña=password;
        nombre=name;
        apellidos=lastname;
        saldo=balance;
        proveedores = new ArrayList<>();
        empleados = new ArrayList<>();
        clientes = new ArrayList<>();
        servicios = new ArrayList<>();
        productos = new ArrayList<>();
        compras = new ArrayList<>();
        ventas = new ArrayList<>();
        usuarios = new ArrayList<>();
        clientes.add(new Cliente(this, "Generico"));
        proveedores.add(new Proveedor(this, "Generico", null, null, null));
        Archivos.guardarArchivo(this, "\\Usuarios\\"+user+"\\datos.txt");
    }
}