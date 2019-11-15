package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Producto implements Serializable {
    public int id;
    public String nombre;
    public int cantidad;
    public float costo;
    public float precio;
    public float ganancia;
    public String proveedor;
    public List<Expirable> expirables = new ArrayList<>();

    public Producto(int ID, String name, int quantity, float price) {
        id=ID;
        nombre=name;
        cantidad=quantity;
        precio=price;
    }
    public Producto(Usuario u, String name, float cost, float price, String provider) {
        id=u.productos.size();
        nombre=name;
        costo=cost;
        precio=price;
        proveedor=provider;
    }
}