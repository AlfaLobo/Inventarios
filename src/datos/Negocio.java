package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Negocio implements Serializable {
    public int id;
    public String nombre;
    public float inversion=0;
    public float ganancia=0;
    public float saldo;
    public List<Producto> productos = new ArrayList<>();
    public List<Integer> compras = new ArrayList<>();
    public List<Integer> ventas = new ArrayList<>();

    public Negocio(Usuario u, int ID, String name, float balance){
        id=ID;
        nombre=name;
        saldo=balance;
    }
}