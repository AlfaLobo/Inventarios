package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Compra implements Serializable {
    public int id;
    public int cantidad;
    public float total;
    public String proveedor;
    public GregorianCalendar fecha;
    public ArrayList<Compra> productos;
    public String notas;
    public Producto producto;

    public Compra(Producto p, int quantity, float total) {
        producto=p;
        id=p.id;
        cantidad=quantity;
        this.total=total;
    }
    public Compra(Usuario u, int provider, String notes, ArrayList<Compra> purchases) {
        id=u.compras.size();
        proveedor=u.proveedores.get(provider).nombre;
        notas=notes;
        fecha=new GregorianCalendar();
        productos=purchases;
        for (int i=0;i<purchases.size();i++){
            if (purchases.get(i).producto instanceof Expirable){
                if (buscarFecha(u, purchases, i)==false){
                    u.productos.get(purchases.get(i).id).expirables.add(new Expirable(u, purchases.get(i).producto, purchases.get(i).cantidad, ((Expirable) purchases.get(i).producto).expiracion));
                }
            }
            u.productos.get(purchases.get(i).id).cantidad+=purchases.get(i).cantidad;
            u.productos.get(purchases.get(i).id).inversion+=purchases.get(i).total;
            total+=purchases.get(i).total;
        }
        u.proveedores.get(provider).inversion+=total;
        u.inversion+=total;
        u.saldo-=total;
    }
    boolean buscarFecha(Usuario u, ArrayList<Compra> purchases, int i){
        for (int j=0;j<u.productos.get(purchases.get(i).producto.id).expirables.size();j++){
            if (u.productos.get(purchases.get(i).producto.id).expirables.get(j).expiracion.get(Calendar.DAY_OF_YEAR)==((Expirable) purchases.get(i).producto).expiracion.get(Calendar.DAY_OF_YEAR)&&u.productos.get(purchases.get(i).producto.id).expirables.get(j).expiracion.get(Calendar.YEAR)==((Expirable) purchases.get(i).producto).expiracion.get(Calendar.YEAR)){
                u.productos.get(purchases.get(i).producto.id).expirables.get(j).cantidad+=purchases.get(i).cantidad;
                return true;
            }
        }
        return false;
    }
}