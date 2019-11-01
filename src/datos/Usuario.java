package datos;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    public String id;
    public String nombre;
    public String directorio;
    public float totalcompra;
    public float totalventa;
    public float ganancias;
    public List<Cliente> clientes = new ArrayList<>();
    public List<Producto> productos = new ArrayList<>();
    public List<Proveedor> proveedores = new ArrayList<>();

    public Usuario(String ID, String name){
        id = ID;
        nombre = name;
        String directorio = System.getProperty("user.dir");
        directorio = directorio + "\\usuario"+ID+".txt";
        this.directorio = directorio;
        try {
            File myObj = new File(directorio);
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
