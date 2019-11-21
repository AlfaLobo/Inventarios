package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Venta implements Serializable {
    public int id;
    public int cantidad;
    public float total;
    public int cliente;
    public String notas;
    public GregorianCalendar fecha;
    public String formapago;
    public Producto producto;
    public ArrayList<Venta> productos;

    public Venta(Producto p, int quantity, float total) {
        producto=p;
        id=p.id;
        cantidad=quantity;
        this.total=total;
    }
    public Venta(Usuario u, int client, ArrayList<Venta> sales) {
        id=u.ventas.size();
        cliente=client;
        productos=sales;
        fecha=new GregorianCalendar();
        for (int i=0;i<sales.size();i++){
            if (sales.get(i).producto instanceof Expirable){
                u.productos.get(sales.get(i).producto.id).expirables.get(((Expirable) sales.get(i).producto).ap).cantidad-=sales.get(i).cantidad;
            }
            u.productos.get(sales.get(i).id).cantidad-=sales.get(i).cantidad;
            u.productos.get(sales.get(i).id).ganancia+=sales.get(i).total;
            u.proveedores.get(u.productos.get(sales.get(i).id).proveedor).ganancia+=sales.get(i).total;
            total+=sales.get(i).total;
        }
        u.clientes.get(client).ganancia+=total;
        u.saldo+=total;
    }
}