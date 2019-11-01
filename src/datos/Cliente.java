package datos;

import java.io.Serializable;

public class Cliente implements Serializable  {
    public String id;
    public String nombre;
    public String fechanacimiento;
    public float totalcompras;

    public Cliente(String ID, String name, String date, float purchases) {
        id=ID;
        nombre=name;
        fechanacimiento=date;
        totalcompras=purchases;
    }
}
