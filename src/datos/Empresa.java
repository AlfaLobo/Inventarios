package datos;

import algoritmos.Archivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empresa implements Serializable {
    public int id;
    public String nombre;
    public float inversion=0;
    public float ganancio=0;
    public float saldo;
    public List<Integer> clientes = new ArrayList<>();
    public List<Empleado> empleados = new ArrayList<>();
    public List<Negocio> negocios = new ArrayList<>();
    public List<Producto> productos = new ArrayList<>();
    public List<Integer> servicios = new ArrayList<>();
    public List<Integer> compras = new ArrayList<>();
    public List<Integer> ventas = new ArrayList<>();

    public Empresa(Usuario u, String name, float balance){
        id=u.empresas.size();
        nombre=name;
        saldo=balance;
        u.clientes.add(new Cliente(u, "Generico "+name));
        Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
    }
}