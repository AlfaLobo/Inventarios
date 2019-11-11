package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Compra implements Serializable {
    public int id;
    public String proveedor;
    public GregorianCalendar fecha;
    public float total;
    public class Beneficio implements Serializable {
        String producto;
        public int cantidad;
        public Beneficio(String product, int quantity){
            producto=product;
            cantidad=quantity;
        }
        public List<Beneficio> beneficios = new ArrayList<>();
    }
    public Compra(int ID, String provider, GregorianCalendar date, float total) {
        id=ID;
        proveedor=provider;
        fecha=date;
        this.total=total;
    }
}
