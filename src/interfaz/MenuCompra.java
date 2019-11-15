package interfaz;

import algoritmos.Archivos;
import datos.Compra;
import datos.Expirable;
import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MenuCompra {
    JDialog d;
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JComboBox cb4 = new JComboBox();
    JComboBox cb5 = new JComboBox();
    JTextField t1 = new JTextField("0");
    JTextField t2 = new JTextField("0");
    JButton b1 = new JButton("Añadir");
    JButton b2 = new JButton("Registrar");
    JCheckBox c = new JCheckBox("Expiración:");
    JTextArea ta = new JTextArea();
    JScrollPane sp = new JScrollPane(ta);
    JLabel l1 = new JLabel("Producto:");
    JLabel l2 = new JLabel("Cantidad:");
    JLabel l3 = new JLabel("Total:");
    JLabel l4 = new JLabel("Insertar valores validos.");
    JLabel l5 = new JLabel("Ingresar al menos un producto a comprar.");
    public MenuCompra(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(600,400);
        l1.setBounds(35,30, 100,30);
        cb1.setBounds(95,30, 200,30);
        l2.setBounds(35,60, 100,30);
        t1.setBounds(95,60, 200,30);
        l3.setBounds(10,90, 100,30);
        t2.setBounds(95,90, 200,30);
        c.setBounds(5,120, 100,30);
        cb2.setBounds(95,120, 50,30);
        cb3.setBounds(145,120, 50,30);
        cb4.setBounds(195,120, 100,30);
        cb5.setBounds(325,30, 200,30);
        sp.setBounds(325, 60, 200, 90);
        b1.setBounds(95,150, 200,30);
        b2.setBounds(325,150, 200,30);
        l4.setBounds(130,175, 200,30);
        l5.setBounds(305,175, 400,30);
        d.setLayout(null);
        d.setResizable(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
        cb4.setEnabled(false);
        c.setContentAreaFilled(false);
        ta.setEditable(false);
        l4.setVisible(false);
        l5.setVisible(false);
        t1.selectAll();
        for (int i=0;i<u.productos.size();i++) {
            cb1.addItem(u.productos.get(i).nombre);
        }
        for (int i=1;i<32;i++) {
            cb2.addItem(i);
        }
        for (int i=1;i<13;i++) {
            cb3.addItem(i);
        }
        for (int i=2019;i<2100;i++) {
            cb4.addItem(i);
        }
        for (int i=0;i<u.proveedores.size();i++) {
            cb5.addItem(u.proveedores.get(i).nombre);
        }
        ArrayList<Producto> arr = new ArrayList<>();
        cb1.setSelectedIndex(u.productos.size()-1);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        sp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                cambio();
            }
            public void removeUpdate(DocumentEvent e) {
                cambio();
            }
            public void insertUpdate(DocumentEvent e) {
                cambio();
            }
            public void cambio() {
                try {
                    float cantidad = Integer.parseInt(t1.getText());
                    t2.setText(String.valueOf(u.productos.get(cb1.getSelectedIndex()).precio*cantidad));
                    l4.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l4.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        t2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                cambio();
            }
            public void removeUpdate(DocumentEvent e) {
                cambio();
            }
            public void insertUpdate(DocumentEvent e) {
                cambio();
            }
            public void cambio() {
                try {
                    float cantidad = Integer.parseInt(t1.getText());
                    float total = Float.parseFloat(t2.getText());
                    l4.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l4.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (c.isSelected()){
                    GregorianCalendar date = new GregorianCalendar((int) cb4.getItemAt(cb4.getSelectedIndex()), (int) cb3.getItemAt(cb3.getSelectedIndex()), (int) cb2.getItemAt(cb2.getSelectedIndex()));
                    arr.add(new Expirable(u.productos.get(cb1.getSelectedIndex()).id, u.productos.get(cb1.getSelectedIndex()).nombre, Integer.parseInt(t1.getText()), Float.parseFloat(t2.getText()), date));
                } else {
                    arr.add(new Producto(u.productos.get(cb1.getSelectedIndex()).id, u.productos.get(cb1.getSelectedIndex()).nombre, Integer.parseInt(t1.getText()), Float.parseFloat(t2.getText())));
                }
                l5.setVisible(false);
                ta.append(t1.getText()+" "+u.productos.get(cb1.getSelectedIndex()).nombre+" - "+t2.getText()+"$\n");
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (arr.size()>0){
                    float total=0;
                    for(int i=0;i<arr.size();i++){
                        if (arr.get(i) instanceof Expirable){
                            u.productos.get(arr.get(i).id).expirables.add((Expirable) arr.get(i));
                        }
                        u.productos.get(arr.get(i).id).cantidad=u.productos.get(cb1.getSelectedIndex()).cantidad+arr.get(i).cantidad;
                        u.productos.get(arr.get(i).id).ganancia=u.productos.get(cb1.getSelectedIndex()).ganancia-arr.get(i).precio;
                        total=total+arr.get(i).precio;
                    }
                    u.compras.add(new Compra(u.compras.size(), total, u.proveedores.get(cb5.getSelectedIndex()).nombre, arr));
                    u.saldo=u.saldo-total;
                    Archivos.guardarArchivo(u, "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    d.dispose();
                    f.setEnabled(true);
                } else {
                    l5.setVisible(true);
                }
            }
        });
        c.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                cb2.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                cb3.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                cb4.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        d.add(cb1);
        d.add(cb2);
        d.add(cb3);
        d.add(cb4);
        d.add(cb5);
        d.add(t1);
        d.add(t2);
        d.add(b1);
        d.add(b2);
        d.add(c);
        d.add(sp);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
