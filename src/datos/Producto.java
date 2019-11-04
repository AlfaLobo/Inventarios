package datos;

import java.io.Serializable;

public class Producto implements Serializable {
    public int id;
    public String nombre;
    public String marca;
    public int cantidad;
    public float precioc;
    public float preciov;
    public Proveedor proveedor;

    public Producto(int ID, String name, String brand, int quantity, float cprice, float sprice, Proveedor provider) {
        id=ID;
        nombre=name;
        marca=brand;
        cantidad=quantity;
        precioc=cprice;
        preciov=sprice;
        proveedor=provider;
    }
}