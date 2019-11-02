package interfaz;

import datos.Usuario;

import javax.swing.*;

public class RegistroProducto {
    JFrame f = new JFrame("Registro de producto");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    public RegistroProducto(Usuario u) {
        f.setSize(400,500);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}
