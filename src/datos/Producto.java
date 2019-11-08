package datos;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import javafx.scene.Parent;

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
    public class Expirable implements Serializable{
        public int cantidad;
        public GregorianCalendar expiracion;
        public Expirable(int quantity, GregorianCalendar date){
            cantidad=quantity;
            expiracion=date;
        }
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