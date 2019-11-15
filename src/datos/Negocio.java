package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Negocio implements Serializable {
    int id;
    String nombre;
    float saldo;
    int empresa;
    public List<Producto> productos = new ArrayList<>();
    public List<Servicio> servicios = new ArrayList<>();
    public List<Compra> compras = new ArrayList<>();
    public List<Venta> ventas = new ArrayList<>();
    public Negocio(int ID, String name, float balance, int enterprise){
        id=ID;
        nombre=name;
        saldo=balance;
        empresa=enterprise;
    }
}