package interfaz;

import algoritmos.Interfaces;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuPrincipal {
    GridBagConstraints c = new GridBagConstraints();
    JFrame f = new JFrame("Inventarios Conejo");
    JLabel JLabelLogo = new JLabel();
    JPanel JPanelInventory = new JPanel();
    JLabel JLabelInventoryTitle = new JLabel("Inventario");
    JButton JButtonInventory = new JButton();
    JPanel JPanelTransactions = new JPanel();
    JLabel JLabelTransactionsTitle = new JLabel("Transacciónes");
    JButton JButtonTransaction = new JButton();
    JButton JButtonTransactionHistory = new JButton();
    JLabel JLabelTransaction = new JLabel("Transacción");
    JLabel JLabelTransactionHistory = new JLabel("Historial");
    JPanel JPanelContacts = new JPanel();
    JLabel JLabelContactsTitle = new JLabel("Contactos");
    JButton JButtonClients = new JButton();
    JButton JButtonProviders = new JButton();
    JLabel JLabelClients = new JLabel("Clientes");
    JLabel JLabelProviders = new JLabel("Proveedores");

    public MenuPrincipal(Usuario u) {
        f.setSize(950,600);
        f.getContentPane().setBackground(new java.awt.Color(171,213,217));
        f.setLayout(new GridBagLayout());
        f.setResizable(false);
        JPanelInventory.setLayout(new GridBagLayout());
        JPanelTransactions.setLayout(new GridBagLayout());
        JPanelContacts.setLayout(new GridBagLayout());
        JPanelInventory.setOpaque(false);
        JPanelTransactions.setOpaque(false);
        JPanelContacts.setOpaque(false);
        JLabelLogo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=oCmMbil5wCY"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JButtonInventory.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (u.productos.size()!=0){
                    new Inventario(f, u);
                } else {
                    JOptionPane.showMessageDialog(f, "No hay productos registrados.");
                }
            }
        });
        JButtonClients.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Clientes(f, u);
            }
        });
        JButtonProviders.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Proveedores(f, u);
            }
        });
        JButtonTransaction.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Compra", "Venta"};
                int opcion = JOptionPane.showOptionDialog(f, "Que tipo de transacción quiere realizar?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    new RegistroCompra(f, u);
                } else if (opcion == JOptionPane.NO_OPTION) {
                    if (u.productos.size()!=0){
                        new RegistroVenta(f, u);
                    } else {
                        JOptionPane.showMessageDialog(f, "No existen productos que vender!!!");
                    }
                }
            }
        });
        JButtonTransactionHistory.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] opciones = {"Compras", "Ventas", "General"};
                int opcion = JOptionPane.showOptionDialog(f, "Que tipo de transacción quiere revisar?", "Elegir una opción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
                if (opcion == JOptionPane.YES_OPTION){
                    if (u.ventas.size()!=0) {
                        new Compras(f, u);
                    } else {
                        JOptionPane.showMessageDialog(f, "No existen compras registradas.");
                    }
                } else if (opcion == JOptionPane.NO_OPTION){
                    if (u.ventas.size()!=0) {
                        new Ventas(f, u);
                    } else {
                        JOptionPane.showMessageDialog(f, "No existen ventas registradas.");
                    }
                } else if (opcion == JOptionPane.CANCEL_OPTION) {
                    if (u.compras.size()!=0&&u.ventas.size()!=0) {
                        JOptionPane.showMessageDialog(f, "No está implementado UnU");
                    } else {
                        JOptionPane.showMessageDialog(f, "No existen transacciónes registradas.");
                    }
                }
            }
        });
        Interfaces.addLabel(JPanelInventory, JLabelInventoryTitle, c, 1, 0);
        Interfaces.addImageButton(JPanelInventory, JButtonInventory, c, 1, 1, "addthis.png");
        Interfaces.addLabel(JPanelTransactions, JLabelTransactionsTitle, c, 1, 0);
        Interfaces.addImageButton(JPanelTransactions, JButtonTransaction, c, 0, 1, "shopping-cart-8.png");
        Interfaces.addImageButton(JPanelTransactions, JButtonTransactionHistory, c, 3, 1, "book-3.png");
        Interfaces.addLabel(JPanelTransactions, JLabelTransaction, c, 0, 2);
        Interfaces.addLabel(JPanelTransactions, JLabelTransactionHistory, c, 3, 2);
        Interfaces.addLabel(JPanelContacts, JLabelContactsTitle, c, 1, 0);
        Interfaces.addImageButton(JPanelContacts, JButtonClients, c, 0, 1, "add-user.png");
        Interfaces.addImageButton(JPanelContacts, JButtonProviders, c, 3, 1, "shop-5.png");
        Interfaces.addLabel(JPanelContacts, JLabelClients, c, 0, 2);
        Interfaces.addLabel(JPanelContacts, JLabelProviders, c, 3, 2);
        Interfaces.addImage(f, JLabelLogo, c, 0, 0, "image(1).png");
        Interfaces.addPanel(f, JPanelInventory, c, 1, 1);
        Interfaces.addPanel(f, JPanelTransactions, c, 0, 2);
        Interfaces.addPanel(f, JPanelContacts, c, 2, 2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}