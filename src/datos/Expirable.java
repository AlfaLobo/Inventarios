package datos;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Expirable extends Producto implements Serializable {
    public GregorianCalendar expiracion;
    public Expirable(int ID, String name, int quantity, float price, GregorianCalendar date){
        super(ID, name, quantity, price);
        expiracion=date;
    }
}
