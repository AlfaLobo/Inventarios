package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Venta implements Serializable {
    public int id;
    public String cliente;
    public GregorianCalendar fecha;
    public String formapago;
    public float total;
    public class Articulo implements Serializable{
        public String producto;
        public int cantidad;
        public Articulo(String product, int quantity){
            producto=product;
            cantidad=quantity;
        }
    }
    public List<Articulo> articulos = new ArrayList<>();
    public Venta() {

    }
}
