package datos;
import java.io.Serializable;

public class Cliente implements Serializable {
    String id;
    String nombre;
    String fechanacimiento;
    float totalcompras;

    public Cliente(String ID, String name, String date, float purchases) {
        id=ID;
        nombre=name;
        fechanacimiento=date;
        totalcompras=purchases;
    }
}
