package datos;

import java.io.Serializable;

public class Producto implements Serializable {
    public String id;
    public String nombre;
    public String marca;
    public int cantidad;
    public float precioc;
    public float preciov;

    public Producto(String ID, String name, String brand, int quantity, float cprice, float sprice) {
        id=ID;
        nombre=name;
        marca=brand;
        cantidad=quantity;
        precioc=cprice;
        preciov=sprice;
    }
}