package interfaz;

import algoritmos.Archivos;
import algoritmos.Busqueda;
import algoritmos.Interfaces;
import datos.Producto;
import datos.Usuario;

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

public class Inventario {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelProducts = new JPanel();
    JPanel JPanelAddProduct = new JPanel();
    JLabel JLabelProvider = new JLabel("Proveedor:");
    JComboBox JComboBoxProviders = new JComboBox();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelCost = new JLabel("Costo:");
    JTextField JTextFieldCost = new JTextField("0");
    JLabel JLabelPrice = new JLabel("Precio:");
    JTextField JTextFieldPrice = new JTextField("0");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Inventario(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setVisible(false);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelProducts.setLayout(new GridBagLayout());
        JPanelProducts.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Inventario");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelProducts.setBorder(title);
        JPanelAddProduct.setLayout(new BoxLayout(JPanelAddProduct, BoxLayout.Y_AXIS));
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        model.addColumn("Nombre");
        model.addColumn("Cantidad");
        model.addColumn("Costo");
        model.addColumn("Precio");
        model.addColumn("Inversión");
        model.addColumn("Ganancias");
        model.addColumn("Proveedor");
        for (int i = 0;i<u.productos.size();i++){
            model.addRow(new Object[]{u.productos.get(i).nombre, u.productos.get(i).cantidad, u.productos.get(i).costo, u.productos.get(i).precio, u.productos.get(i).inversion,  u.productos.get(i).ganancia, u.proveedores.get(u.productos.get(i).proveedor).nombre});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i=0;i<u.proveedores.size();i++) {
            JComboBoxProviders.addItem(u.proveedores.get(i).nombre);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.dispose();
            }
        });
        JTextFieldCost.getDocument().addDocumentListener(new DocumentListener() {
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
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    JButtonRegister.setEnabled(true);
                    if (costo<0||precio<0) {
                        JButtonRegister.setEnabled(false);
                    } else {
                        JButtonRegister.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JTextFieldCost.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldCost.selectAll();
            }
        });
        JTextFieldPrice.getDocument().addDocumentListener(new DocumentListener() {
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
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    JButtonRegister.setEnabled(true);
                    if (costo<0||precio<0) {
                        JButtonRegister.setEnabled(false);
                    } else  {
                        JButtonRegister.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JTextFieldPrice.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldPrice.selectAll();
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(d, "Nombre no valido.");
                } else if (Busqueda.buscarProducto(u, JComboBoxProviders.getSelectedIndex(), JTextFieldName.getText())) {
                    JOptionPane.showMessageDialog(d, "El producto ya existe.");
                } else {
                    u.productos.add(new Producto(u, JTextFieldName.getText(), Float.parseFloat(JTextFieldCost.getText()), Float.parseFloat(JTextFieldPrice.getText()), JComboBoxProviders.getSelectedIndex()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    JOptionPane.showMessageDialog(d, "El producto ha sido añadido.");
                    JComboBoxProviders.setSelectedIndex(0);
                    JTextFieldName.setText("");
                    JTextFieldCost.setText("0");
                    JTextFieldPrice.setText("0");
                    model.addRow(new Object[]{u.productos.get(u.productos.size()-1).nombre, u.productos.get(u.productos.size()-1).cantidad, u.productos.get(u.productos.size()-1).costo, u.productos.get(u.productos.size()-1).precio, u.productos.get(u.productos.size()-1).inversion,  u.productos.get(u.productos.size()-1).ganancia, u.proveedores.get(u.productos.get(u.productos.size()-1).proveedor).nombre});
                    sp.repaint();
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
        JPanelAddProduct.add(JLabelProvider);
        JPanelAddProduct.add(JComboBoxProviders);
        JPanelAddProduct.add(JLabelName);
        JPanelAddProduct.add(JTextFieldName);
        JPanelAddProduct.add(JLabelCost);
        JPanelAddProduct.add(JTextFieldCost);
        JPanelAddProduct.add(JLabelPrice);
        JPanelAddProduct.add(JTextFieldPrice);
        JPanelAddProduct.add(JButtonRegister);
        JPanelAddProduct.setPreferredSize(new Dimension(150, 300));
        c.weightx = 0.5;
        Interfaces.addPanel(JPanelProducts, JPanelAddProduct, c, 0, 0);
        Interfaces.addScrollPane(JPanelProducts, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelProducts, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelProducts);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}