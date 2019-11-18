package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Compra implements Serializable {
    public int id;
    public int cantidad;
    public float total;
    public int proveedor;
    public GregorianCalendar fecha;
    public Producto producto;
    public ArrayList<Compra> productos;

    public Compra(Producto p, int quantity, float total) {
        producto=p;
        id=p.id;
        cantidad=quantity;
        this.total=total;
    }
    public Compra(Usuario u, int provider, ArrayList<Compra> purchases) {
        id=u.compras.size();
        proveedor=provider;
        fecha=new GregorianCalendar();
        productos=purchases;
        for (int i=0;i<purchases.size();i++){
            if (purchases.get(i).producto instanceof Expirable){
                u.productos.get(purchases.get(i).id).expirables.add(new Expirable(purchases.get(i).producto, purchases.get(i).cantidad, ((Expirable) purchases.get(i).producto).expiracion));
                u.empresas.get(u.empresa).productos.get(purchases.get(i).id).expirables.add(new Expirable(purchases.get(i).producto, purchases.get(i).cantidad, ((Expirable) purchases.get(i).producto).expiracion));
                u.empresas.get(u.empresa).negocios.get(u.negocio).productos.get(purchases.get(i).id).expirables.add(new Expirable(purchases.get(i).producto, purchases.get(i).cantidad, ((Expirable) purchases.get(i).producto).expiracion));
            }
            u.productos.get(purchases.get(i).id).cantidad+=purchases.get(i).cantidad;
            u.productos.get(purchases.get(i).id).inversion+=purchases.get(i).total;
            total+=purchases.get(i).total;
        }
        u.saldo-=total;
        u.proveedores.get(provider).inversion+=total;
        u.empresas.get(u.empresa).compras.add(id);
        u.empresas.get(u.empresa).negocios.get(u.negocio).compras.add(id);
    }
}