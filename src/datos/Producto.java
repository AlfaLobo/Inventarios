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
    public float inversion=0;
    public float ganancia=0;
    public int proveedor;
    public List<Expirable> expirables = new ArrayList<>();
    public String notas;

    public Producto(){

    }
    public Producto(Usuario u, String name, float cost, float price, int provider, String notes) {
        id=u.productos.size();
        nombre=name;
        precio=price;
        costo=cost;
        proveedor=provider;
        u.proveedores.get(provider).productos.add(id);
        notas=notes;
    }
    public Producto(int ID) {
        id=ID;
    }
}