package datos;

import java.io.Serializable;

public class Producto implements Serializable {
    public int id;
    public String nombre;
    public String marca;
    public int cantidad;
    public float costo;
    public float precio;
    public float ganancia;
    public String proveedor;

    public Producto(int ID, String name, String brand, int quantity, float cost, float price, String provider) {
        id=ID;
        nombre=name;
        marca=brand;
        cantidad=quantity;
        costo=cost;
        precio=price;
        proveedor=provider;
    }
}