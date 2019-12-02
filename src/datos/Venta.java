package datos;

import algoritmos.Funciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Venta implements Serializable {
    public int id;
    public int cantidad;
    public float total;
    public String cliente;
    public String formapago;
    public String notas;
    public GregorianCalendar fecha;
    public Producto producto;
    public ArrayList<Venta> productos;

    public Venta(Producto p, int quantity, float total) {
        producto=p;
        id=p.id;
        cantidad=quantity;
        this.total=total;
    }
    public Venta(Usuario u, int client, String payment, String notes, ArrayList<Venta> sales) {
        id=u.ventas.size();
        cliente=u.clientes.get(client).nombre;
        notas=notes;
        formapago=payment;
        productos=sales;
        fecha=new GregorianCalendar();
        for (int i=0;i<sales.size();i++){
            if (sales.get(i).producto instanceof Expirable){
                u.productos.get(sales.get(i).producto.id).expirables.get(((Expirable) sales.get(i).producto).ap).cantidad-=sales.get(i).cantidad;
                if (u.productos.get(sales.get(i).producto.id).expirables.get(((Expirable) sales.get(i).producto).ap).cantidad<=0) {
                    Funciones.eliminarExpirable(u, sales.get(i).producto, ((Expirable) sales.get(i).producto).ap);
                }
            }
            u.productos.get(sales.get(i).id).cantidad-=sales.get(i).cantidad;
            u.productos.get(sales.get(i).id).ganancia+=sales.get(i).total;
            u.proveedores.get(u.productos.get(sales.get(i).id).proveedor).ganancia+=sales.get(i).total;
            total+=sales.get(i).total;
        }
        u.clientes.get(client).ganancia+=total;
        u.ganancia+=total;
        u.saldo+=total;
    }
}