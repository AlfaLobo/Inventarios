package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoUsuario {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelInfo = new JPanel();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JLabel JLabel3 = new JLabel();
    JLabel JLabel4 = new JLabel();
    JLabel JLabel5 = new JLabel();
    JLabel JLabel6 = new JLabel();
    JButton JButtonEdit = new JButton("Modificar");
    JButton JButtonDelete = new JButton("Eliminar");
    public InfoUsuario(JFrame f, Usuario u) {
        f.setEnabled(false);
        d = new JDialog(f);
        d.setSize(225, 225);
        d.setLocationRelativeTo(f);
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        d.setResizable(false);
        JPanelInfo.setLayout(new GridBagLayout());
        JPanelInfo.setOpaque(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JLabel1.setText(u.nombre+" "+u.apellidos);
        JLabel2.setText("Usuario: "+u.usuario);
        JLabel3.setText("Negocio: "+u.negocio);
        JLabel4.setText("Saldo: "+u.saldo+"$");
        JLabel5.setText("Inversión total: "+u.inversion+"$");
        JLabel6.setText("Ganancias: "+u.ganancia+"$");
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ModificarUsuario(f, u);
                d.dispose();
            }
        });
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar esta cuenta?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Archivos.eliminarUsusario(u.usuario+"/datos.txt", u.usuario);
                    JOptionPane.showMessageDialog(d, "Cuenta eliminada.");
                    f.dispose();
                }
            }
        });
        c.anchor = GridBagConstraints.LINE_START;
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 3);
        Interfaces.addLabel(JPanelInfo, JLabel5, c, 0, 4);
        Interfaces.addLabel(JPanelInfo, JLabel6, c, 0, 5);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelInfo, JButtonEdit, c, 0, 6);
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 0, 7);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.add(JPanelInfo);
        d.setVisible(true);
    }
}
