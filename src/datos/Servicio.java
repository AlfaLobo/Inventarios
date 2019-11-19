package datos;

import java.io.Serializable;

public class Servicio implements Serializable {
    public int id;
    public String nombre;
    public float precio;
    public float ganancia=0;
    public Servicio(){

    }
    public Servicio(Usuario u, String name, float price) {
        id=u.servicios.size();
        nombre=name;
        precio=price;
    }
}