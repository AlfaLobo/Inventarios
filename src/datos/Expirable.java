package datos;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Expirable extends Producto implements Serializable {
    public int ap;
    public GregorianCalendar expiracion;

    public Expirable(Producto p, GregorianCalendar expiration){
        id=p.id;
        nombre=p.nombre;
        expiracion=expiration;
    }
    public Expirable(Usuario u, Producto p, int quantity, GregorianCalendar expiration){
        ap=u.productos.get(p.id).expirables.size();
        id=p.id;
        nombre=p.nombre;
        cantidad=quantity;
        expiracion=expiration;
    }
}