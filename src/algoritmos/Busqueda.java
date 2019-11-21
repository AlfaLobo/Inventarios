package algoritmos;

import datos.Usuario;

public class Busqueda {
    public static boolean buscarProducto(Usuario u, int provider, String name){
        for(int i = 0; i < u.proveedores.get(provider).productos.size(); i++) {
            if (u.productos.get(u.proveedores.get(provider).productos.get(i)).nombre.equals(name)){
                return true;
            }
        }
        return false;
    }
    public static boolean buscarProveedor(Usuario u, String name){
        for(int i = 0; i < u.proveedores.size(); i++) {
            if (u.proveedores.get(i).nombre.equals(name)){
                return true;
            }
        }
        return false;
    }
}