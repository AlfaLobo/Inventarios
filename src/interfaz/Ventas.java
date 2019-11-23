package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Usuario;
import datos.Venta;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class Ventas {
    String notes = "";
    ArrayList<Venta> ventas = new ArrayList<>();
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelSales = new JPanel();
    JPanel JPanelNewSale = new JPanel();
    JLabel JLabelClient = new JLabel("Cliente:");
    JComboBox JComboBoxClients = new JComboBox();
    JLabel JLabelProduct = new JLabel("Producto:");
    JComboBox JComboBoxProducts = new JComboBox();
    JLabel JLabelIteration = new JLabel("Iteración:");
    JComboBox JComboBoxIterations = new JComboBox();
    JLabel JLabelQuantity = new JLabel("Cantidad:");
    JTextField JTextFieldQuantity = new JTextField("0");
    JLabel JLabelTotal = new JLabel("Total:");
    JTextField JTextFieldTotal = new JTextField("0");
    JButton JButtonAdd = new JButton("Añadir");
    JTextArea ta = new JTextArea();
    JScrollPane JScrollPaneSales = new JScrollPane(ta);
    JLabel JLabelPaymentMethod = new JLabel("Metodo de Pago:");
    JTextField JTextFieldPaymentMethod = new JTextField();
    JButton JButtonNotes = new JButton("Notas");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Ventas(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setEnabled(false);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelSales.setLayout(new GridBagLayout());
        JPanelSales.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Venta");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelSales.setBorder(title);
        JPanelNewSale.setLayout(new GridBagLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        model.addColumn("ID");
        model.addColumn("Cliente");
        model.addColumn("Forma de Pago");
        model.addColumn("Total");
        model.addColumn("Fecha");
        for (int i = 0;i<u.ventas.size();i++){
            String fecha = "";
            try {
                fecha = u.ventas.get(i).fecha.get(Calendar.DAY_OF_MONTH)+"-"+u.ventas.get(i).fecha.get(Calendar.MONTH)+"-"+u.ventas.get(i).fecha.get(Calendar.YEAR);
            } catch (NullPointerException e) {

            }
            model.addRow(new Object[]{u.ventas.get(i).id, u.clientes.get(u.ventas.get(i).cliente).nombre, u.ventas.get(i).formapago, u.ventas.get(i).total +"$", fecha});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i=0;i<u.clientes.size();i++) {
            JComboBoxClients.addItem(u.clientes.get(i).nombre);
        }
        for (int i=0;i<u.productos.size();i++) {
            JComboBoxProducts.addItem(u.productos.get(i).nombre);
        }
        if (u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size()>0){
            JComboBoxIterations.setEnabled(true);
            for (int i=0;i<u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size();i++) {
                JComboBoxIterations.addItem(u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).nombre+" "+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.YEAR));
            }
        } else {
            JComboBoxIterations.setEnabled(false);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JComboBoxProducts.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBoxIterations.removeAllItems();
                if (u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size()>0){
                    JComboBoxIterations.setEnabled(true);
                    for (int i=0;i<u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size();i++) {
                        JComboBoxIterations.addItem(u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).nombre+" "+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.YEAR));
                    }
                } else {
                    JComboBoxIterations.setEnabled(false);
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
                    JButtonAdd.setEnabled(true);
                    if (cantidad>0||total>0) {
                        JButtonAdd.setEnabled(true);
                        JTextFieldTotal.setText(String.valueOf(u.productos.get(JComboBoxProducts.getSelectedIndex()).precio*cantidad));
                    } else {
                        JButtonAdd.setEnabled(false);
                    }
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
                    float cantidad = Float.parseFloat(JTextFieldQuantity.getText());
                    float total = Float.parseFloat(JTextFieldTotal.getText());
                    JButtonAdd.setEnabled(true);
                    if (cantidad<0||total<0) {
                        JButtonAdd.setEnabled(false);
                    } else  {
                        JButtonAdd.setEnabled(true);
                    }
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
                JComboBoxClients.setEnabled(false);
                if (JComboBoxIterations.isEnabled()){
                    ventas.add(new Venta(u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(JComboBoxIterations.getSelectedIndex()), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                } else {
                    ventas.add(new Venta(u.productos.get(JComboBoxProducts.getSelectedIndex()), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                }
                ta.append(JTextFieldQuantity.getText()+" "+u.productos.get(JComboBoxProducts.getSelectedIndex()).nombre+" - "+JTextFieldTotal.getText()+"$\n");
            }
        });
        JButtonNotes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                UIManager.put("OptionPane.cancelButtonText", "Cancelar");
                notes = (String) JOptionPane.showInputDialog(d, "Nota:", "Notas", JOptionPane.PLAIN_MESSAGE, null, null, null);
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (ventas.size()!=0){
                    u.ventas.add(new Venta(u, JComboBoxClients.getSelectedIndex(), JTextFieldPaymentMethod.getText(), notes, ventas));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    Object[] opciones = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(d, "Venta registrada, desea imprimir el recibo?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (opcion == JOptionPane.YES_OPTION){

                    }
                    ventas.clear();
                    JComboBoxClients.setEnabled(true);
                    JComboBoxClients.setSelectedIndex(0);
                    JComboBoxProducts.setSelectedIndex(0);
                    JTextFieldQuantity.setText("0");
                    JTextFieldTotal.setText("0");
                    ta.setText("");
                    JTextFieldPaymentMethod.setText("");
                    model.addRow(new Object[]{u.ventas.get(u.ventas.size()-1).id, u.clientes.get(u.ventas.get(u.ventas.size()-1).cliente).nombre, u.ventas.get(u.ventas.size()-1).formapago, u.ventas.get(u.ventas.size()-1).total +"$", u.ventas.get(u.ventas.size()-1).fecha.get(Calendar.DAY_OF_MONTH)+"-"+u.ventas.get(u.ventas.size()-1).fecha.get(Calendar.MONTH)+"-"+u.ventas.get(u.ventas.size()-1).fecha.get(Calendar.YEAR)});
                    sp.repaint();
                } else {
                    JOptionPane.showMessageDialog(d, "Añadir al menos un producto a vender.");
                }
            }
        });
        JButtonReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        Dimension d1 = new Dimension(200, 100);
        JScrollPaneSales.setPreferredSize(d1);
        JScrollPaneSales.setMinimumSize(d1);
        JPanelNewSale.setPreferredSize(new Dimension(175, 450));
        c.weighty = 0.1;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        Interfaces.addLabel(JPanelNewSale, JLabelClient, c, 0, 0);
        Interfaces.addComboBox(JPanelNewSale, JComboBoxClients, c, 0, 1);
        Interfaces.addLabel(JPanelNewSale, JLabelProduct, c, 0, 2);
        Interfaces.addComboBox(JPanelNewSale, JComboBoxProducts, c, 0, 3);
        Interfaces.addLabel(JPanelNewSale, JLabelIteration, c, 0, 4);
        Interfaces.addComboBox(JPanelNewSale, JComboBoxIterations, c, 0, 5);
        Interfaces.addLabel(JPanelNewSale, JLabelQuantity, c, 0, 6);
        Interfaces.addTextField(JPanelNewSale, JTextFieldQuantity, c, 0, 7);
        Interfaces.addLabel(JPanelNewSale, JLabelTotal, c, 0, 8);
        Interfaces.addTextField(JPanelNewSale, JTextFieldTotal, c, 0, 9);
        Interfaces.addButton(JPanelNewSale, JButtonAdd, c, 0, 10);
        Interfaces.addScrollPane(JPanelNewSale, JScrollPaneSales, c, 0, 11);
        Interfaces.addLabel(JPanelNewSale, JLabelPaymentMethod, c, 0, 12);
        Interfaces.addTextField(JPanelNewSale, JTextFieldPaymentMethod, c, 0, 13);
        Interfaces.addButton(JPanelNewSale, JButtonNotes, c, 0, 14);
        c.anchor = GridBagConstraints.LINE_END;
        Interfaces.addButton(JPanelNewSale, JButtonRegister, c, 0, 14);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addPanel(JPanelSales, JPanelNewSale, c, 0, 0);
        Interfaces.addScrollPane(JPanelSales, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelSales, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelSales);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}