package datos;

import java.io.Serializable;

public class Servicio implements Serializable {
    public String nombre;
    public float costo;
    public float precio;
    public float inversion;
    public float ganancias;
    public Servicio(String name, float cost, float price) {
        nombre=name;
        costo=cost;
        precio=price;
    }
}