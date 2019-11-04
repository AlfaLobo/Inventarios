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
    public Proveedor proveedor;

    public Producto(int ID, String name, String brand, int quantity, float cost, float price, Proveedor provider) {
        id=ID;
        nombre=name;
        marca=brand;
        cantidad=quantity;
        costo=cost;
        precio=price;
        proveedor=provider;
    }
}