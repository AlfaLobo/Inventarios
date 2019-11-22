package interfaz;

import algoritmos.Archivos;
import algoritmos.Busqueda;
import algoritmos.Interfaces;
import datos.Cliente;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clientes {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelClients = new JPanel();
    JPanel JPanelAddClient = new JPanel();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JTextField JTextFieldLastName = new JTextField();
    JLabel JLabelPhone = new JLabel("Telefono:");
    JTextField JTextFieldPhone = new JTextField();
    JLabel JLabelEmail = new JLabel("Correo:");
    JTextField JTextFieldEmail = new JTextField();
    JLabel JLabelBirthday = new JLabel("Fecha de Nacimiento:");
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Clientes(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setVisible(false);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelClients.setLayout(new GridBagLayout());
        JPanelClients.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Clientes");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelClients.setBorder(title);
        JPanelAddClient.setLayout(new BoxLayout(JPanelAddClient, BoxLayout.Y_AXIS));
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Telefono");
        model.addColumn("Correo");
        model.addColumn("Nacimiento");
        model.addColumn("Ganancias");
        for (int i = 0;i<u.clientes.size();i++){
            String fecha = "";
            try {
                fecha = u.clientes.get(i).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.clientes.get(i).nacimiento.get(Calendar.MONTH)+"/"+u.clientes.get(i).nacimiento.get(Calendar.YEAR);
            } catch (NullPointerException e) {

            }
            model.addRow(new Object[]{u.clientes.get(i).nombre, u.clientes.get(i).apellidos, u.clientes.get(i).telefono, u.clientes.get(i).correo, fecha, Float.toString(u.clientes.get(i).ganancia)});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i=1;i<32;i++) {
            JComboBoxDay.addItem(i);
        }
        for (int i=1;i<13;i++) {
            JComboBoxMonth.addItem(i);
        }
        for (int i=2019;i>1899;i--) {
            JComboBoxYear.addItem(i);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.dispose();
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(d, "Nombre no valido.");
                } else {
                    u.clientes.add(new Cliente(u, JTextFieldName.getText(), JTextFieldLastName.getText(), JTextFieldPhone.getText(), JTextFieldEmail.getText(), new GregorianCalendar((int) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (int) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (int) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex()))));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    JOptionPane.showMessageDialog(d, "El cliente ha sido añadido.");
                    JTextFieldName.setText("");
                    JTextFieldLastName.setText("");
                    JTextFieldPhone.setText("");
                    JTextFieldEmail.setText("");
                    JComboBoxDay.setSelectedIndex(0);
                    JComboBoxMonth.setSelectedIndex(0);
                    JComboBoxYear.setSelectedIndex(0);
                    model.addRow(new Object[]{u.clientes.get(u.clientes.size()-1).nombre, u.clientes.get(u.clientes.size()-1).apellidos, u.clientes.get(u.clientes.size()-1).telefono, u.clientes.get(u.clientes.size()-1).correo, u.clientes.get(u.clientes.size()-1).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.clientes.get(u.clientes.size()-1).nacimiento.get(Calendar.MONTH)+"/"+u.clientes.get(u.clientes.size()-1).nacimiento.get(Calendar.YEAR), Float.toString(u.clientes.get(u.clientes.size()-1).ganancia)});
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
        JPanelAddClient.add(JLabelName);
        JPanelAddClient.add(JTextFieldName);
        JPanelAddClient.add(JLabelLastName);
        JPanelAddClient.add(JTextFieldLastName);
        JPanelAddClient.add(JLabelPhone);
        JPanelAddClient.add(JTextFieldPhone);
        JPanelAddClient.add(JLabelEmail);
        JPanelAddClient.add(JTextFieldEmail);
        JPanelAddClient.add(JLabelBirthday);
        JPanelAddClient.add(JComboBoxDay);
        JPanelAddClient.add(JComboBoxMonth);
        JPanelAddClient.add(JComboBoxYear);
        JPanelAddClient.add(JButtonRegister);
        JPanelAddClient.setPreferredSize(new Dimension(150, 300));
        c.weightx = 0.5;
        Interfaces.addPanel(JPanelClients, JPanelAddClient, c, 0, 0);
        Interfaces.addScrollPane(JPanelClients, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelClients, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelClients);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}