package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Compra;
import datos.Expirable;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Compras {
    String notas = "Sin notas.";
    ArrayList<Compra> compras = new ArrayList<>();
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelPurchases = new JPanel();
    JPanel JPanelNewPurchase = new JPanel();
    JLabel JLabelProvider = new JLabel("Proveedor:");
    JComboBox JComboBoxProviders = new JComboBox();
    JLabel JLabelProduct = new JLabel("Producto:");
    JComboBox JComboBoxProducts = new JComboBox();
    JLabel JLabelQuantity = new JLabel("Cantidad:");
    JTextField JTextFieldQuantity = new JTextField("0");
    JLabel JLabelTotal = new JLabel("Total:");
    JTextField JTextFieldTotal = new JTextField("0");
    JCheckBox JCheckBoxExpiration = new JCheckBox("Expiración:");
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JTextArea ta = new JTextArea();
    JScrollPane JScrollPanePurchases = new JScrollPane(ta);
    JButton JButtonAdd = new JButton("Añadir");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Compras(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setVisible(false);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelPurchases.setLayout(new GridBagLayout());
        JPanelPurchases.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Compra");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelPurchases.setBorder(title);
        JPanelNewPurchase.setLayout(new GridBagLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        JComboBoxDay.setEnabled(false);
        JComboBoxMonth.setEnabled(false);
        JComboBoxYear.setEnabled(false);
        JCheckBoxExpiration.setContentAreaFilled(false);
        model.addColumn("ID");
        model.addColumn("Proveedor");
        model.addColumn("Total");
        model.addColumn("Fecha");
        for (int i = 0;i<u.compras.size();i++){
            String fecha = "";
            try {
                fecha = u.compras.get(i).fecha.get(Calendar.DAY_OF_MONTH)+"/"+u.compras.get(i).fecha.get(Calendar.MONTH)+"/"+u.compras.get(i).fecha.get(Calendar.YEAR);
            } catch (NullPointerException e) {

            }
            model.addRow(new Object[]{u.compras.get(i).id, u.clientes.get(u.compras.get(i).proveedor).nombre, u.compras.get(i).total +"$", fecha});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i=0;i<u.proveedores.size();i++) {
            JComboBoxProviders.addItem(u.proveedores.get(i).nombre);
        }
        for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
            JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
        }
        for (int i=1;i<32;i++) {
            JComboBoxDay.addItem(i);
        }
        for (int i=1;i<13;i++) {
            JComboBoxMonth.addItem(i);
        }
        for (int i=2019;i<2100;i++) {
            JComboBoxYear.addItem(i);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.dispose();
            }
        });
        JComboBoxProviders.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBoxProducts.removeAllItems();
                for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
                    JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
                }
            }
        });
        JTextFieldQuantity.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(JTextFieldQuantity.getText());
                    float total = Float.parseFloat(JTextFieldTotal.getText());
                    JTextFieldTotal.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).costo*cantidad));
                    JButtonAdd.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonAdd.setEnabled(false);
                }
            }
        });
        JTextFieldQuantity.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldQuantity.selectAll();
            }
        });
        JTextFieldTotal.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(JTextFieldQuantity.getText());
                    float total = Float.parseFloat(JTextFieldTotal.getText());
                    JButtonAdd.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonAdd.setEnabled(false);
                }
            }
        });
        JTextFieldTotal.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldTotal.selectAll();
            }
        });
        JButtonAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JComboBoxProducts.getItemCount()!=0) {
                    JComboBoxProviders.setEnabled(false);
                    if (JCheckBoxExpiration.isSelected()){
                        compras.add(new Compra(new Expirable(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())), new GregorianCalendar((Integer) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), JComboBoxMonth.getSelectedIndex(), JComboBoxDay.getSelectedIndex())), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                    } else {
                        compras.add(new Compra(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                    }
                    ta.append(JTextFieldQuantity.getText()+" "+u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).nombre+" - "+JTextFieldTotal.getText()+"$\n");
                } else {
                    JOptionPane.showMessageDialog(d, "Elegir un producto valido.");
                }
            }
        });
        JCheckBoxExpiration.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                JComboBoxDay.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                JComboBoxMonth.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                JComboBoxYear.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (compras.size()!=0){
                    u.compras.add(new Compra(u, JComboBoxProviders.getSelectedIndex(), notas, compras));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    JOptionPane.showMessageDialog(d, "La compra ha sido registrada.");
                    compras.clear();
                    JComboBoxProviders.setEnabled(true);
                    JComboBoxProviders.setSelectedIndex(0);
                    JComboBoxProducts.setSelectedIndex(0);
                    JTextFieldQuantity.setText("0");
                    JTextFieldTotal.setText("0");
                    ta.setText("");
                    model.addRow(new Object[]{u.ventas.get(u.ventas.size()-1).id, u.clientes.get(u.ventas.get(u.ventas.size()-1).cliente).nombre, u.ventas.get(u.ventas.size()-1).formapago, u.ventas.get(u.ventas.size()-1).total +"$", u.ventas.get(u.ventas.size()-1).fecha.get(Calendar.DAY_OF_MONTH)+"-"+u.ventas.get(u.ventas.size()-1).fecha.get(Calendar.MONTH)+"-"+u.ventas.get(u.ventas.size()-1).fecha.get(Calendar.YEAR)});
                    sp.repaint();
                } else {
                    JOptionPane.showMessageDialog(d, "Añadir al menos un producto a comprar.");
                }
            }
        });
        JButtonReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setVisible(true);
                f.setLocationRelativeTo(d);
                d.dispose();
            }
        });
        JPanelNewPurchase.setPreferredSize(new Dimension(175, 420));
        Dimension d1 = new Dimension(60, 25);
        Dimension d2 = new Dimension(80, 25);
        Dimension d3 = new Dimension(200, 100);
        JComboBoxDay.setPreferredSize(d1);
        JComboBoxDay.setMinimumSize(d1);
        JComboBoxMonth.setPreferredSize(d1);
        JComboBoxMonth.setMinimumSize(d1);
        JComboBoxYear.setPreferredSize(d2);
        JComboBoxYear.setMinimumSize(d2);
        JScrollPanePurchases.setPreferredSize(d3);
        JScrollPanePurchases.setMinimumSize(d3);
        c.weighty = 0.1;
        c.weightx = 0.1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        Interfaces.addLabel(JPanelNewPurchase, JLabelProvider, c, 0, 0);
        Interfaces.addComboBox(JPanelNewPurchase, JComboBoxProviders, c, 0, 1);
        Interfaces.addLabel(JPanelNewPurchase, JLabelProduct, c, 0, 2);
        Interfaces.addComboBox(JPanelNewPurchase, JComboBoxProducts, c, 0, 3);
        Interfaces.addLabel(JPanelNewPurchase, JLabelQuantity, c, 0, 4);
        Interfaces.addTextField(JPanelNewPurchase, JTextFieldQuantity, c, 0, 5);
        Interfaces.addLabel(JPanelNewPurchase, JLabelTotal, c, 0, 6);
        Interfaces.addTextField(JPanelNewPurchase, JTextFieldTotal, c, 0, 7);
        Interfaces.addCheckBox(JPanelNewPurchase, JCheckBoxExpiration, c, 0, 8);
        Interfaces.addButton(JPanelNewPurchase, JButtonAdd, c, 0, 10);
        Interfaces.addScrollPane(JPanelNewPurchase, JScrollPanePurchases, c, 0, 11);
        Interfaces.addButton(JPanelNewPurchase, JButtonRegister, c, 0, 12);
        c.gridwidth=1;
        Interfaces.addComboBox(JPanelNewPurchase, JComboBoxDay, c, 0, 9);
        Interfaces.addComboBox(JPanelNewPurchase, JComboBoxMonth, c, 1, 9);
        Interfaces.addComboBox(JPanelNewPurchase, JComboBoxYear, c, 2, 9);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addPanel(JPanelPurchases, JPanelNewPurchase, c, 0, 0);
        Interfaces.addScrollPane(JPanelPurchases, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelPurchases, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelPurchases);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
    public Compras(JFrame f, Usuario u, int proveedor){
        this(f, u);
        JComboBoxProviders.setSelectedIndex(proveedor);
        JComboBoxProducts.setSelectedIndex(u.proveedores.get(proveedor).productos.size()-1);
        JComboBoxProviders.setEnabled(false);
    }
}