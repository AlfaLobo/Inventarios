package interfaz;

import algoritmos.Archivos;
import algoritmos.Busqueda;
import algoritmos.Interfaces;
import datos.Proveedor;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Proveedores {
    String notes = "";
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelProviders = new JPanel();
    JPanel JPanelAddProviderBorder = new JPanel();
    JPanel JPanelAddProvider = new JPanel();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelPhone = new JLabel("Telefono:");
    JTextField JTextFieldPhone = new JTextField();
    JLabel JLabelEmail = new JLabel("Correo:");
    JTextField JTextFieldEmail = new JTextField();
    JLabel JLabelAddress = new JLabel("Dirección:");
    JTextField JTextFieldAddress = new JTextField();
    JButton JButtonNotes = new JButton("Notas");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Proveedores(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setEnabled(false);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelProviders.setLayout(new GridBagLayout());
        JPanelProviders.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Proveedores");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelProviders.setBorder(title);
        TitledBorder title1 = BorderFactory.createTitledBorder(border, "Registrar Proveedor");
        title1.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelAddProviderBorder.setBorder(title1);
        JPanelAddProvider.setLayout(new GridBagLayout());
        JPanelAddProvider.setOpaque(false);
        Dimension d1 = new Dimension(175, 30);
        JButtonRegister.setPreferredSize(d1);
        JButtonRegister.setMaximumSize(d1);
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("Correo");
        model.addColumn("Dirección");
        model.addColumn("Total Invertido");
        model.addColumn("Ganancias Generadas");
        for (int i = 0;i<u.proveedores.size();i++){
            model.addRow(new Object[]{u.proveedores.get(i).nombre, u.proveedores.get(i).telefono, u.proveedores.get(i).correo, u.proveedores.get(i).direccion, Float.toString(u.proveedores.get(i).inversion), Float.toString(u.proveedores.get(i).ganancia)});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
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
                if (JTextFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(d, "Nombre no valido.");
                } else if (Busqueda.buscarProveedor(u, JTextFieldName.getText())) {
                    JOptionPane.showMessageDialog(d, "El proveedor ya existe.");
                } else {
                    u.proveedores.add(new Proveedor(u, JTextFieldName.getText(), JTextFieldPhone.getText(), JTextFieldEmail.getText(), JTextFieldAddress.getText(), notes));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    Object[] opciones = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(d, "Registro exitoso, desea añadir un producto a este proveedor?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (opcion == JOptionPane.YES_OPTION){
                        new Inventario(f, u, u.proveedores.size()-1);
                        d.dispose();
                    }
                    JTextFieldName.setText("");
                    JTextFieldPhone.setText("");
                    JTextFieldEmail.setText("");
                    JTextFieldAddress.setText("");
                    model.addRow(new Object[]{u.proveedores.get(u.proveedores.size()-1).nombre, u.proveedores.get(u.proveedores.size()-1).telefono, u.proveedores.get(u.proveedores.size()-1).correo, u.proveedores.get(u.proveedores.size()-1).direccion, Float.toString(u.proveedores.get(u.proveedores.size()-1).inversion), Float.toString(u.proveedores.get(u.proveedores.size()-1).ganancia)});
                    sp.repaint();
                }
            }
        });
        JButtonReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        JPanelAddProvider.setPreferredSize(new Dimension(175, 250));
        c.weighty = 0.1;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        Interfaces.addLabel(JPanelAddProvider, JLabelName, c, 0, 0);
        Interfaces.addTextField(JPanelAddProvider, JTextFieldName, c, 0, 1);
        Interfaces.addLabel(JPanelAddProvider, JLabelPhone, c, 0, 2);
        Interfaces.addTextField(JPanelAddProvider, JTextFieldPhone, c, 0, 3);
        Interfaces.addLabel(JPanelAddProvider, JLabelEmail, c, 0, 4);
        Interfaces.addTextField(JPanelAddProvider, JTextFieldEmail, c, 0, 5);
        Interfaces.addLabel(JPanelAddProvider, JLabelAddress, c, 0, 6);
        Interfaces.addTextField(JPanelAddProvider, JTextFieldAddress, c, 0, 7);
        Interfaces.addButton(JPanelAddProvider, JButtonNotes, c, 0, 8);
        c.anchor = GridBagConstraints.LINE_END;
        Interfaces.addButton(JPanelAddProvider, JButtonRegister, c, 0, 8);
        c.anchor = GridBagConstraints.CENTER;
        JPanelAddProviderBorder.add(JPanelAddProvider);
        Interfaces.addPanel(JPanelProviders, JPanelAddProviderBorder, c, 0, 0);
        Interfaces.addScrollPane(JPanelProviders, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelProviders, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelProviders);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}