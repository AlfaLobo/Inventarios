package interfaz;

import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

public class CompraProducto {
    JFrame f = new JFrame("Compra de producto");
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JSpinner s1 = new JSpinner(new SpinnerNumberModel(0,0,2147483647,1));
    JTextField t1 = new JTextField("0");
    JTextField t2 = new JTextField();
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JCheckBox c = new JCheckBox("Expiración:");
    JLabel l1 = new JLabel("Cantidad:");
    JLabel l2 = new JLabel("Total Compra:");
    JLabel l3 = new JLabel("Insertar valores validos");
    public CompraProducto(Usuario u, JFrame m, Producto p){
        f.setSize(390,400);
        l1.setBounds(35,60, 100,30);
        t1.setBounds(95,60, 200,30);
        l2.setBounds(10,90, 100,30);
        t2.setBounds(95,90, 200,30);
        c.setBounds(5,120, 100,30);
        cb1.setBounds(95,120, 50,30);
        cb2.setBounds(145,120, 50,30);
        cb3.setBounds(195,120, 100,30);
        b1.setBounds(95,150, 200,30);
        b2.setBounds(95,175, 200,30);
        l3.setBounds(95,200, 200,30);
        f.setLayout(null);
        f.setResizable(false);
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        c.setContentAreaFilled(false);
        l3.setVisible(false);
        t2.setText(String.valueOf(p.precio));
        for (int i=1;i<32;i++) {
            cb1.addItem(i);
        }
        for (int i=1;i<13;i++) {
            cb2.addItem(i);
        }
        for (int i=2019;i<2099;i++) {
            cb3.addItem(i);
        }
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                m.setEnabled(true);
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int quantity = 0;
                int total = 0;
                try {
                    quantity=Integer.parseInt(t1.getText());
                    total=Integer.parseInt(t2.getText());
                    if (c.isSelected()){

                    } else {
                        p.cantidad=p.cantidad+quantity;
                        p.ganancia=p.ganancia-total;
                        f.dispose();
                        m.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    l3.setVisible(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                m.setEnabled(true);
            }
        });
        c.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                cb1.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                cb2.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                cb3.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        //int day= (int) cb1.getItemAt(cb1.getSelectedIndex());
        //int month= (int) cb2.getItemAt(cb2.getSelectedIndex());
        //int year=Integer.parseInt(t2.getText());;
        f.add(cb1);
        f.add(cb2);
        f.add(cb3);
        f.add(s1);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(b2);
        f.add(c);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}
