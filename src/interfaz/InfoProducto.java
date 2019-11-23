package interfaz;

import algoritmos.Interfaces;
import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class InfoProducto {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelProductInfo = new JPanel();
    JLabel JLabelName = new JLabel();
    JLabel JLabelProvider = new JLabel();
    JLabel JLabelQuantity = new JLabel();
    JLabel JLabelNotes = new JLabel();
    JLabel JLabelIterations = new JLabel("Iteraciónes:");
    JButton JButtonEdit = new JButton("Modificar");
    JButton JButtonDelete = new JButton("Eliminar");

    public InfoProducto(JFrame f, JDialog j, Usuario u, Producto p) {
        j.setEnabled(false);
        d = new JDialog(f);
        d.setSize(375,400);
        d.setLocationRelativeTo(j);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(25, 90, 20, 90));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelProductInfo.setLayout(new GridBagLayout());
        JPanelProductInfo.setOpaque(false);
        JLabelName.setText("Nombre: "+p.nombre);
        JLabelProvider.setText("Proveedor: "+u.proveedores.get(p.proveedor).nombre);
        JLabelQuantity.setText("Total: "+p.cantidad);
        JLabelNotes.setText("Notas: "+p.notas);
        String col[] = {"Fecha de Expiración","Cantidad"};
        String[][] datos;
        if (p.expirables.size()==0) {
            datos = new String[10][col.length];
        } else if (p.expirables.size()<5) {
            datos = new String[10][col.length];
        } else {
            datos = new String[p.expirables.size()][col.length];
        }
        for (int i = 0;i<p.expirables.size();i++){
            datos[i][0]=p.expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+p.expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+p.expirables.get(i).expiracion.get(Calendar.YEAR);
            datos[i][1]=Integer.toString(p.expirables.get(i).cantidad);
        }
        JTable tb = new JTable(datos,col){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                j.setEnabled(true);
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ModificarProducto(u, d, p);
            }
        });
        Dimension d1 = new Dimension(255, 183);
        sp.setPreferredSize(d1);
        sp.setMinimumSize(d1);
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth=2;
        Interfaces.addLabel(JPanelProductInfo, JLabelName, c, 0, 0);
        Interfaces.addLabel(JPanelProductInfo, JLabelProvider, c, 0, 1);
        Interfaces.addLabel(JPanelProductInfo, JLabelQuantity, c, 0, 2);
        Interfaces.addLabel(JPanelProductInfo, JLabelIterations, c, 0, 3);
        Interfaces.addLabel(JPanelProductInfo, JLabelNotes, c, 0, 4);
        Interfaces.addScrollPane(JPanelProductInfo, sp, c, 0, 5);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelProductInfo, JButtonEdit, c, 0, 6);
        Interfaces.addButton(JPanelProductInfo, JButtonDelete, c, 1, 6);
        d.add(JPanelProductInfo);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}