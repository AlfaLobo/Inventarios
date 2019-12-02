package interfaz;

import algoritmos.Funciones;
import algoritmos.Interfaces;
import datos.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

public class Informacion {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelInfo = new JPanel();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JLabel JLabel3 = new JLabel();
    JLabel JLabel4 = new JLabel();
    JLabel JLabel5 = new JLabel();
    JLabel JLabel6 = new JLabel();
    JLabel JLabel7 = new JLabel();
    JLabel JLabel8 = new JLabel();
    JLabel JLabelNotes = new JLabel("Notas: ");
    JTextArea JTextAreaNotes = new JTextArea();
    JScrollPane JScrollPaneNotes = new JScrollPane(JTextAreaNotes);
    JButton JButtonEdit = new JButton("Modificar");
    JButton JButtonDelete = new JButton("Eliminar");

    public Informacion(JDialog j, int width, int height){
        j.setEnabled(false);
        d = new JDialog(j);
        d.setSize(width, height);
        d.setLocationRelativeTo(j);
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelInfo.setLayout(new GridBagLayout());
        JPanelInfo.setOpaque(false);
        JTextAreaNotes.setEditable(false);
        Dimension JScrollPaneNotesDimension = new Dimension(255, 100);
        JScrollPaneNotes.setPreferredSize(JScrollPaneNotesDimension);
        JScrollPaneNotes.setMinimumSize(JScrollPaneNotesDimension);
        c.weightx = 0.1;
        c.gridwidth=2;
        c.anchor = GridBagConstraints.LINE_START;
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                j.setEnabled(true);
            }
        });
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.add(JPanelInfo);
        d.setVisible(true);
    }
    public Informacion (JDialog j, Usuario u, Cliente p, DefaultTableModel model, JTable tb){
        this(j, 250, 325);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        JLabel1.setText(p.nombre+" "+p.apellidos);
        JLabel2.setText("Telefono: "+p.telefono);
        JLabel3.setText("Correo: "+p.correo);
        JLabel4.setText("Ganancia: "+p.ganancia+"$");
        JTextAreaNotes.setText(p.notas);
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Modificar(j, u, p);
                d.dispose();
            }
        });
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar este cliente?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarCliente(u, p);
                    JOptionPane.showMessageDialog(d, "Cliente eliminado.");
                    j.setEnabled(true);
                    d.dispose();
                    if (p.id!=0){
                        tb.setRowSelectionInterval(0, 0);
                    } else {
                        tb.setRowSelectionInterval(0, 1);
                    }
                    model.removeRow(p.id);
                    tb.revalidate();
                }
            }
        });
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 3);
        Interfaces.addLabel(JPanelInfo, JLabelNotes, c, 0, 4);
        Interfaces.addScrollPane(JPanelInfo, JScrollPaneNotes, c, 0, 5);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelInfo, JButtonEdit, c, 0, 6);
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 1, 6);
    }
    public Informacion (JDialog j, Usuario u, Compra p, DefaultTableModel model, JTable tb){
        this(j, 250, 325);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        JLabel1.setText("ID: "+p.id);
        JLabel2.setText("Proveedor: "+p.proveedor);
        JLabel3.setText("Total: "+p.total+"$");
        JLabel4.setText("Fecha: "+p.fecha.get(Calendar.DAY_OF_MONTH)+"/"+p.fecha.get(Calendar.MONTH)+"/"+p.fecha.get(Calendar.YEAR));
        JTextAreaNotes.setText(p.notas);
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar esta compra?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarCompra(u, p);
                    JOptionPane.showMessageDialog(d, "Compra eliminada.");
                    j.setEnabled(true);
                    d.dispose();
                    if (p.id!=0){
                        tb.setRowSelectionInterval(0, 0);
                    } else {
                        tb.setRowSelectionInterval(0, 1);
                    }
                    model.removeRow(p.id);
                    tb.revalidate();
                }
            }
        });
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 3);
        Interfaces.addLabel(JPanelInfo, JLabelNotes, c, 0, 4);
        Interfaces.addScrollPane(JPanelInfo, JScrollPaneNotes, c, 0, 5);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 1, 6);
    }
    public Informacion(JDialog j, Usuario u, Empleado p, DefaultTableModel model, JTable tb) {
        this(j, 250, 325);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        JLabel1.setText(p.nombre+" "+p.apellidos);
        JLabel2.setText("Telefono: "+p.telefono);
        JLabel3.setText("Correo: "+p.correo);
        JLabel4.setText("Fecha de Nacimiento: "+p.nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+p.nacimiento.get(Calendar.MONTH)+"/"+p.nacimiento.get(Calendar.YEAR));
        JLabel5.setText("Salario: "+p.salario+"$");
        JTextAreaNotes.setText(p.notas);
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Modificar(j, u, p);
                d.dispose();
            }
        });
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar este empleado?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarEmpleado(u, p);
                    JOptionPane.showMessageDialog(d, "Empleado eliminado.");
                    j.setEnabled(true);
                    d.dispose();
                    if (p.id!=0){
                        tb.setRowSelectionInterval(0, 0);
                    } else {
                        tb.setRowSelectionInterval(0, 1);
                    }
                    model.removeRow(p.id);
                    tb.revalidate();
                }
            }
        });
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 3);
        Interfaces.addLabel(JPanelInfo, JLabel5, c, 0, 4);
        Interfaces.addLabel(JPanelInfo, JLabelNotes, c, 0, 5);
        Interfaces.addScrollPane(JPanelInfo, JScrollPaneNotes, c, 0, 6);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelInfo, JButtonEdit, c, 0, 7);
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 1, 7);
    }
    public Informacion(JDialog j, Usuario u, Producto p, DefaultTableModel model, JTable tb) {
        this(j, 375, 450);
        d.setLocationRelativeTo(j);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(25, 90, 20, 90));
        JLabel1.setText(p.nombre);
        JLabel2.setText("Proveedor: "+u.proveedores.get(p.proveedor).nombre);
        JLabel3.setText("Total: "+p.cantidad);
        JLabel4.setText("Costo: "+p.costo+"$");
        JLabel5.setText("Precio: "+p.precio+"$");
        JLabel6.setText("Inversión: "+p.inversion+"$");
        JLabel7.setText("Ganancia: "+p.ganancia+"$");
        JLabel8.setText("Iteraciónes:");
        String col[] = {"Fecha de Expiración","Cantidad"};
        String[][] datos;
        datos = new String[p.expirables.size()][col.length];
        for (int i = 0;i<p.expirables.size();i++){
            datos[i][0]=p.expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+p.expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+p.expirables.get(i).expiracion.get(Calendar.YEAR);
            datos[i][1]=Integer.toString(p.expirables.get(i).cantidad);
        }
        JTable tb1 = new JTable(datos,col){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JScrollPane sp = new JScrollPane(tb1);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JTextAreaNotes.setText(p.notas);
        tb1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar esta iteración?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarExpirable(u, p, tb1.getSelectedRow());
                    JOptionPane.showMessageDialog(d, "Iteración eliminada.");
                    datos[tb1.getSelectedRow()][0]="Eliminado";
                    tb1.revalidate();
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Modificar(j, u, p);
                d.dispose();
            }
        });
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar este producto?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarProducto(u, p);
                    JOptionPane.showMessageDialog(d, "Producto eliminado.");
                    j.setEnabled(true);
                    d.dispose();
                    if (p.id!=0){
                        tb.setRowSelectionInterval(0, 0);
                    } else {
                        tb.setRowSelectionInterval(0, 1);
                    }
                    model.removeRow(p.id);
                    tb.revalidate();
                }
            }
        });
        Dimension d1 = new Dimension(255, 103);
        sp.setPreferredSize(d1);
        sp.setMinimumSize(d1);
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 4);
        Interfaces.addLabel(JPanelInfo, JLabel5, c, 0, 5);
        Interfaces.addLabel(JPanelInfo, JLabel6, c, 0, 6);
        Interfaces.addLabel(JPanelInfo, JLabel7, c, 0, 7);
        Interfaces.addLabel(JPanelInfo, JLabel8, c, 0, 8);
        Interfaces.addScrollPane(JPanelInfo, sp, c, 0, 9);
        Interfaces.addLabel(JPanelInfo, JLabelNotes, c, 0, 10);
        Interfaces.addScrollPane(JPanelInfo, JScrollPaneNotes, c, 0, 11);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelInfo, JButtonEdit, c, 0, 12);
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 1, 12);
        if(u.proveedores.get(p.proveedor).nombre.equals("Propio")==false&&p.cantidad==0){
            Object[] opciones = {"Mostrar", "Cancelar"};
            int opcion = JOptionPane.showOptionDialog(d, "Parece ser que no tiene stock de este producto, desea mostrar la información de contacto de este proveedor?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (opcion == JOptionPane.YES_OPTION){
                new Informacion(d, u.proveedores.get(p.proveedor));
            }
        }
    }
    public Informacion(JDialog j, Proveedor p) {
        this(j, 250, 325);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        JLabel1.setText(p.nombre);
        JLabel2.setText("Telefono: "+p.telefono);
        JLabel3.setText("Correo: "+p.correo);
        JLabel4.setText("Dirección: "+p.direccion);
        JLabel5.setText("Inversión: "+p.inversion+"$");
        JLabel6.setText("Ganancia: "+p.ganancia+"$");
        JTextAreaNotes.setText(p.notas);
        JLabel4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        String URL = "https://www.google.com/maps/search/"+p.direccion.replaceAll("\\s+","+");
                        Desktop.getDesktop().browse(new URI(URL));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 4);
        Interfaces.addLabel(JPanelInfo, JLabel5, c, 0, 5);
        Interfaces.addLabel(JPanelInfo, JLabel6, c, 0, 6);
        Interfaces.addLabel(JPanelInfo, JLabelNotes, c, 0, 7);
        Interfaces.addScrollPane(JPanelInfo, JScrollPaneNotes, c, 0, 8);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelInfo, JButtonEdit, c, 0, 9);
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 1, 9);
    }
    public Informacion(JDialog j, Usuario u, Proveedor p, DefaultTableModel model, JTable tb){
        this(j, p);
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Modificar(j, u, p);
                d.dispose();
            }
        });
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar este proveedor?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarProveedor(u, p);
                    JOptionPane.showMessageDialog(d, "Proveedor eliminado.");
                    j.setEnabled(true);
                    d.dispose();
                    if (p.id!=0){
                        tb.setRowSelectionInterval(0, 0);
                    } else {
                        tb.setRowSelectionInterval(0, 1);
                    }
                    model.removeRow(p.id);
                    tb.revalidate();
                }
            }
        });
    }
    public Informacion (JDialog j, Usuario u, Venta p, DefaultTableModel model, JTable tb){
        this(j, 250, 325);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        JLabel1.setText("ID: "+p.id);
        JLabel2.setText("Cliente: "+p.cliente);
        JLabel3.setText("Total: "+p.total+"$");
        JLabel4.setText("Fecha: "+p.fecha.get(Calendar.DAY_OF_MONTH)+"/"+p.fecha.get(Calendar.MONTH)+"/"+p.fecha.get(Calendar.YEAR));
        JTextAreaNotes.setText(p.notas);
        JButtonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(d, "Desea eliminar esta venta?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    Funciones.eliminarVenta(u, p);
                    JOptionPane.showMessageDialog(d, "Venta eliminado.");
                    j.setEnabled(true);
                    d.dispose();
                    if (p.id!=0){
                        tb.setRowSelectionInterval(0, 0);
                    } else {
                        tb.setRowSelectionInterval(0, 1);
                    }
                    model.removeRow(p.id);
                    tb.revalidate();
                }
            }
        });
        Interfaces.addLabel(JPanelInfo, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelInfo, JLabel2, c, 0, 1);
        Interfaces.addLabel(JPanelInfo, JLabel3, c, 0, 2);
        Interfaces.addLabel(JPanelInfo, JLabel4, c, 0, 3);
        Interfaces.addLabel(JPanelInfo, JLabelNotes, c, 0, 4);
        Interfaces.addScrollPane(JPanelInfo, JScrollPaneNotes, c, 0, 5);
        Interfaces.addButton(JPanelInfo, JButtonDelete, c, 0, 6);
    }
}