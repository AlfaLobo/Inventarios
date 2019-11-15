package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Compra implements Serializable {
    public int id;
    public float total;
    public String proveedor;
    public GregorianCalendar fecha;
    public ArrayList<Producto> productos;
    public Compra(int ID, float total, String provider, ArrayList<Producto> products) {
        id=ID;
        this.total=total;
        proveedor=provider;
        fecha=new GregorianCalendar();
        productos=products;
    }
}
