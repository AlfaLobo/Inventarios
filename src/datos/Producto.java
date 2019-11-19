package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Producto extends Servicio implements Serializable {
    public int id;
    public String nombre;
    public int cantidad;
    public float costo;
    public float precio;
    public float inversion=0;
    public float ganancia=0;
    public int proveedor;
    public List<Expirable> expirables = new ArrayList<>();

    public Producto(){

    }
    public Producto(Usuario u, String name, float cost, float price, int provider) {
        id=u.productos.size();
        nombre=name;
        precio=price;
        costo=cost;
        proveedor=provider;
        u.proveedores.get(provider).productos.add(id);
    }
    public Producto(int ID) {
        id=ID;
    }
}