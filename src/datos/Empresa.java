package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empresa implements Serializable {
    public int id;
    public String nombre;
    public float saldo;
    public List<Cliente> clientes = new ArrayList<>();
    public List<Empleado> empleados = new ArrayList<>();
    public List<Negocio> negocios = new ArrayList<>();
    public List<Producto> productos = new ArrayList<>();
    public List<Proveedor> proveedores = new ArrayList<>();
    public List<Servicio> servicios = new ArrayList<>();
    public List<Compra> compras = new ArrayList<>();
    public List<Venta> ventas = new ArrayList<>();

    public Empresa(int ID, String name, float balance){
        id=ID;
        nombre=name;
        saldo=balance;
    }
}