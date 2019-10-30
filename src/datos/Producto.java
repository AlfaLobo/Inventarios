package datos;
import java.io.Serializable;

public class Producto implements Serializable {
    String id;
    String Nombre;
    String Marca;
    int cantidad;
    float precioc;
    float preciov;

    public Producto(String ID, String name, String brand, int quantity, float cprice, float sprice) {
        id=ID;
        Nombre=name;
        Marca=brand;
        cantidad=quantity;
        precioc=cprice;
        preciov=sprice;
    }
}