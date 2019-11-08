package interfaz;

import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class CompraProducto {
    JFrame f = new JFrame("Compra de producto");
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JTextField t1 = new JTextField("0");
    JTextField t2 = new JTextField("0");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JCheckBox c = new JCheckBox("Expiración:");
    JLabel l1 = new JLabel("Cantidad:");
    JLabel l2 = new JLabel("Total Compra:");
    JLabel l3 = new JLabel("Insertar valores validos.");
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
        l3.setBounds(130,190, 200,30);
        f.setLayout(null);
        f.setResizable(false);
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        c.setContentAreaFilled(false);
        l3.setVisible(false);
        t1.selectAll();
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
                    float total = Float.parseFloat(t1.getText());
                    total = total*p.costo;
                    t2.setText(String.valueOf(total));
                    l3.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l3.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t1.selectAll();
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
                    float total = Float.parseFloat(t2.getText());
                    l3.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l3.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int quantity=Integer.parseInt(t1.getText());
                float total=Float.parseFloat(t2.getText());
                p.cantidad=p.cantidad+quantity;
                p.ganancia=p.ganancia-total;
                u.saldo=u.saldo-total;
                if (c.isSelected()){
                    GregorianCalendar date = new GregorianCalendar((int) cb3.getItemAt(cb3.getSelectedIndex()), (int) cb2.getItemAt(cb2.getSelectedIndex()), (int) cb1.getItemAt(cb1.getSelectedIndex()));
                    p.expirables.add(p.new Expirable(quantity, date));
                    f.dispose();
                    m.setEnabled(true);
                } else {
                    f.dispose();
                    m.setEnabled(true);
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
        f.add(cb1);
        f.add(cb2);
        f.add(cb3);
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
