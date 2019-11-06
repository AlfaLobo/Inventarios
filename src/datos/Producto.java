package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Producto implements Serializable {
    public int id;
    public String nombre;
    public String marca;
    public int cantidad;
    public float costo;
    public float precio;
    public float ganancia;
    public String proveedor;
    class Expirable implements Serializable{
        int cantidad;
        GregorianCalendar expiracion;
    }
    public List<Expirable> expirables = new ArrayList<>();

    public Producto(int ID, String name, String brand, float cost, float price, String provider) {
        id=ID;
        nombre=name;
        marca=brand;
        costo=cost;
        precio=price;
        proveedor=provider;
    }
}