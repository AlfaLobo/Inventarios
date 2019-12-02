package algoritmos;

import datos.*;

public class Funciones {

    public static void eliminarCliente(Usuario u, Cliente p){
        u.clientes.remove(p.id);
        for(int i=p.id;i<u.clientes.size();i++){
            u.clientes.get(i).id-=1;
        }
        Archivos.guardarArchivo(u);
    }
    public static void eliminarCompra(Usuario u, Compra p){
        u.compras.remove(p.id);
        for(int i=p.id;i<u.compras.size();i++){
            u.compras.get(i).id-=1;
        }
        Archivos.guardarArchivo(u);
    }
    public static void eliminarEmpleado(Usuario u, Empleado p){
        u.empleados.remove(p.id);
        for(int i=p.id;i<u.empleados.size();i++){
            u.empleados.get(i).id-=1;
        }
        Archivos.guardarArchivo(u);
    }
    public static void eliminarProducto(Usuario u, Producto p){
        u.productos.remove(p.id);
        u.proveedores.get(p.proveedor).productos.remove(u.proveedores.get(p.proveedor).productos.indexOf(p.id));
        for(int i=p.id;i<u.productos.size();i++){
            u.productos.get(i).id-=1;
        }
        for(int j=0;j<u.proveedores.size();j++){
            for(int k=0;k<u.proveedores.get(j).productos.size();k++)
                if(u.proveedores.get(j).productos.get(k)>p.id){
                    u.proveedores.get(j).productos.set(k, u.proveedores.get(j).productos.get(k)-1);
                }
        }
        Archivos.guardarArchivo(u);
    }
    public static void eliminarProveedor(Usuario u, Proveedor p){
        for (int i=p.productos.size()-1;i>=0;i--){
            Funciones.eliminarProducto(u, u.productos.get(p.productos.get(i)));
        }
        u.proveedores.remove(p.id);
        for(int i=p.id;i<u.proveedores.size();i++){
            u.proveedores.get(i).id-=1;
        }
        Archivos.guardarArchivo(u);
    }
    public static void eliminarVenta(Usuario u, Venta p){
        u.ventas.remove(p.id);
        for(int i=p.id;i<u.ventas.size();i++){
            u.ventas.get(i).id-=1;
        }
        Archivos.guardarArchivo(u);
    }
    public static void eliminarExpirable(Usuario u, Producto p, int ap){
        u.productos.get(p.id).expirables.remove(ap);
        for(int i=ap;i<u.productos.get(p.id).expirables.size();i++){
            u.productos.get(p.id).expirables.get(i).ap-=1;
        }
        Archivos.guardarArchivo(u);
    }
}