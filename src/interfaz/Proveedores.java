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
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelProviders = new JPanel();
    JPanel JPanelAddProvider = new JPanel();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelPhone = new JLabel("Telefono:");
    JTextField JTextFieldPhone = new JTextField();
    JLabel JLabelEmail = new JLabel("Correo:");
    JTextField JTextFieldEmail = new JTextField();
    JLabel JLabelAddress = new JLabel("Dirección:");
    JTextField JTextFieldAddress = new JTextField();
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Proveedores(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setVisible(false);
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
        JPanelAddProvider.setLayout(new BoxLayout(JPanelAddProvider, BoxLayout.Y_AXIS));
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
                f.dispose();
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(d, "Nombre no valido.");
                } else if (Busqueda.buscarProveedor(u, JTextFieldName.getText())) {
                    JOptionPane.showMessageDialog(d, "El proveedor ya existe.");
                } else {
                    u.proveedores.add(new Proveedor(u, JTextFieldName.getText(), JTextFieldPhone.getText(), JTextFieldEmail.getText(), JTextFieldAddress.getText()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    JOptionPane.showMessageDialog(d, "El proveedor ha sido añadido.");
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
                f.setVisible(true);
                f.setLocationRelativeTo(d);
                d.dispose();
            }
        });
        JPanelAddProvider.add(JLabelName);
        JPanelAddProvider.add(JTextFieldName);
        JPanelAddProvider.add(JLabelPhone);
        JPanelAddProvider.add(JTextFieldPhone);
        JPanelAddProvider.add(JLabelEmail);
        JPanelAddProvider.add(JTextFieldEmail);
        JPanelAddProvider.add(JLabelAddress);
        JPanelAddProvider.add(JTextFieldAddress);
        JPanelAddProvider.add(JButtonRegister);
        JPanelAddProvider.setPreferredSize(new Dimension(150, 300));
        c.weightx = 0.5;
        Interfaces.addPanel(JPanelProviders, JPanelAddProvider, c, 0, 0);
        Interfaces.addScrollPane(JPanelProviders, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelProviders, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelProviders);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}