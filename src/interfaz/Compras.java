package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import algoritmos.Ordenamiento;
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
    String notas = "";
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
    JButton JButtonNotes = new JButton("Notas");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Compras(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setEnabled(false);
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
        for (int i = 0; i<u.compras.size(); i++){
            model.addRow(new Object[]{u.compras.get(i).id, u.compras.get(i).proveedor, u.compras.get(i).total +"$", u.compras.get(i).fecha.get(Calendar.DAY_OF_MONTH)+"/"+u.compras.get(i).fecha.get(Calendar.MONTH)+"/"+u.compras.get(i).fecha.get(Calendar.YEAR)});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i=0;i<u.proveedores.size();i++) {
            JComboBoxProviders.addItem(u.proveedores.get(i).nombre);
        }
        for (int i=0;i<u.proveedores.size();i++) {
            if (u.proveedores.get(i).productos.size()>0){
                JComboBoxProviders.setSelectedIndex(i);
                break;
            }
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
                f.setEnabled(true);
            }
        });
        tb.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tb.getColumnName(tb.columnAtPoint(e.getPoint())).equals("Nacimiento")){
                    ArrayList<GregorianCalendar> arr = new ArrayList<>();
                    for(int i=0;i<tb.getColumnCount();i++){
                        if(tb.getColumnName(i).equals("ID")){
                            for (int j=0;j<u.compras.size();j++){
                                arr.add(u.compras.get((Integer) tb.getValueAt(j, i)).fecha);
                            }
                            Ordenamiento.ordenarFechas(tb, model, arr);
                        }
                    }
                } else {
                    Ordenamiento.ordenarTabla(tb, model, tb.columnAtPoint(e.getPoint()));
                }
            }
        });
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                for(int i=0;i<tb.getColumnCount();i++){
                    if(tb.getColumnName(i).equals("ID")){
                        new Informacion(d, u, u.compras.get((Integer) model.getValueAt(tb.rowAtPoint(evt.getPoint()), i)), model, tb);
                    }
                }
            }
        });
        JComboBoxProviders.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBoxProducts.removeAllItems();
                for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
                    JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
                }
                if(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size()==0){
                    JComboBoxProducts.addItem("Sin productos");
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
                try {
                    if (JCheckBoxExpiration.isSelected()){
                        compras.add(new Compra(new Expirable(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())), new GregorianCalendar((Integer) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (Integer) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (Integer) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex()))), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                    } else {
                        compras.add(new Compra(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                    }
                    ta.append(JTextFieldQuantity.getText()+" "+u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).nombre+" - "+JTextFieldTotal.getText()+"$\n");
                    JComboBoxProviders.setEnabled(false);
                } catch (IndexOutOfBoundsException ie) {
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
        JButtonNotes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                UIManager.put("OptionPane.cancelButtonText", "Cancelar");
                notas = JOptionPane.showInputDialog(d, "Nota:");
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (compras.size()!=0){
                    u.compras.add(new Compra(u, JComboBoxProviders.getSelectedIndex(), notas, compras));
                    Archivos.guardarArchivo(u);
                    JOptionPane.showMessageDialog(d, "La compra ha sido registrada.");
                    compras.clear();
                    for (int i=0;i<u.proveedores.size();i++) {
                        if (u.proveedores.get(i).productos.size()>0){
                            JComboBoxProviders.setSelectedIndex(i);
                            break;
                        }
                    }
                    JComboBoxProviders.setEnabled(true);
                    JTextFieldQuantity.setText("0");
                    JTextFieldTotal.setText("0");
                    ta.setText("");
                    model.addRow(new Object[]{u.compras.get(u.compras.size()-1).id, u.compras.get(u.compras.size()-1).proveedor, u.compras.get(u.compras.size()-1).total +"$", u.compras.get(u.compras.size()-1).fecha.get(Calendar.DAY_OF_MONTH)+"/"+u.compras.get(u.compras.size()-1).fecha.get(Calendar.MONTH)+"/"+u.compras.get(u.compras.size()-1).fecha.get(Calendar.YEAR)});
                    sp.repaint();
                } else {
                    JOptionPane.showMessageDialog(d, "Añadir al menos un producto a comprar.");
                }
            }
        });
        JButtonReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
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
        Interfaces.addButton(JPanelNewPurchase, JButtonNotes, c, 0, 12);
        c.anchor = GridBagConstraints.LINE_END;
        Interfaces.addButton(JPanelNewPurchase, JButtonRegister, c, 0, 12);
        c.anchor = GridBagConstraints.LINE_START;
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