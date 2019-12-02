package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import algoritmos.Ordenamiento;
import datos.Cliente;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes {
    String notes = "";
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelClients = new JPanel();
    JPanel JPanelAddClientBorder = new JPanel();
    JPanel JPanelAddClient = new JPanel();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JTextField JTextFieldLastName = new JTextField();
    JLabel JLabelPhone = new JLabel("Telefono:");
    JTextField JTextFieldPhone = new JTextField();
    JLabel JLabelEmail = new JLabel("Correo:");
    JTextField JTextFieldEmail = new JTextField();
    JButton JButtonNotes = new JButton("Notas");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Clientes(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setEnabled(false);
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
        JPanelAddClient.setLayout(new GridBagLayout());
        JPanelAddClient.setOpaque(false);
        TitledBorder title1 = BorderFactory.createTitledBorder(border, "Registrar Cliente");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelAddClientBorder.setBorder(title1);
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Teléfono");
        model.addColumn("Correo");
        model.addColumn("Ganancias");
        for (int i = 0;i<u.clientes.size();i++){
            model.addRow(new Object[]{u.clientes.get(i).id, u.clientes.get(i).nombre, u.clientes.get(i).apellidos, u.clientes.get(i).telefono, u.clientes.get(i).correo, u.clientes.get(i).ganancia});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        tb.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Ordenamiento.ordenarTabla(tb, model, tb.columnAtPoint(e.getPoint()));
            }
        });
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                for(int i=0;i<tb.getColumnCount();i++){
                    if(tb.getColumnName(i).equals("ID")){
                        new Informacion(d, u, u.clientes.get((Integer) model.getValueAt(tb.rowAtPoint(evt.getPoint()), i)), model, tb);
                    }
                }
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
                } else {
                    u.clientes.add(new Cliente(u, JTextFieldName.getText(), JTextFieldLastName.getText(), JTextFieldPhone.getText(), JTextFieldEmail.getText(), notes));
                    Archivos.guardarArchivo(u);
                    JOptionPane.showMessageDialog(d, "El cliente ha sido añadido.");
                    JTextFieldName.setText("");
                    JTextFieldLastName.setText("");
                    JTextFieldPhone.setText("");
                    JTextFieldEmail.setText("");
                    model.addRow(new Object[]{u.clientes.get(u.clientes.size()-1).id, u.clientes.get(u.clientes.size()-1).nombre, u.clientes.get(u.clientes.size()-1).apellidos, u.clientes.get(u.clientes.size()-1).telefono, u.clientes.get(u.clientes.size()-1).correo, Float.toString(u.clientes.get(u.clientes.size()-1).ganancia)});
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
        JPanelAddClient.setPreferredSize(new Dimension(175, 250));
        c.weighty = 0.1;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        Interfaces.addLabel(JPanelAddClient, JLabelName, c, 0, 0);
        Interfaces.addTextField(JPanelAddClient, JTextFieldName, c, 0, 1);
        Interfaces.addLabel(JPanelAddClient, JLabelLastName, c, 0, 2);
        Interfaces.addTextField(JPanelAddClient, JTextFieldLastName, c, 0, 3);
        Interfaces.addLabel(JPanelAddClient, JLabelPhone, c, 0, 4);
        Interfaces.addTextField(JPanelAddClient, JTextFieldPhone, c, 0, 5);
        Interfaces.addLabel(JPanelAddClient, JLabelEmail, c, 0, 6);
        Interfaces.addTextField(JPanelAddClient, JTextFieldEmail, c, 0, 7);
        Interfaces.addButton(JPanelAddClient, JButtonNotes, c, 0, 8);
        c.anchor = GridBagConstraints.LINE_END;
        Interfaces.addButton(JPanelAddClient, JButtonRegister, c, 0, 8);
        c.anchor = GridBagConstraints.CENTER;
        JPanelAddClientBorder.add(JPanelAddClient);
        Interfaces.addPanel(JPanelClients, JPanelAddClientBorder, c, 0, 0);
        Interfaces.addScrollPane(JPanelClients, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelClients, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelClients);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}