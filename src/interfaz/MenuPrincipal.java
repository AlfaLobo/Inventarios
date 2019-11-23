package interfaz;

import algoritmos.Interfaces;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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
    JPanel JPanelMenu = new JPanel();
    JLabel JLabelLogo = new JLabel();
    JPanel JPanelAdminister = new JPanel();
    JLabel JLabelAdministerTitle = new JLabel("Administrar");
    JButton JButtonInventory = new JButton();
    JLabel JLabelInventory = new JLabel("Inventario");
    JPanel JPanelTransactions = new JPanel();
    JLabel JLabelTransactionsTitle = new JLabel("Transacciónes");
    JButton JButtonTransaction = new JButton();
    JButton JButtonTransactionHistory = new JButton();
    JLabel JLabelTransaction = new JLabel("Nueva Transacción");
    JLabel JLabelTransactionHistory = new JLabel("Historial");
    JPanel JPanelContacts = new JPanel();
    JLabel JLabelContactsTitle = new JLabel("Contactos");
    JButton JButtonClients = new JButton();
    JButton JButtonProviders = new JButton();
    JLabel JLabelClients = new JLabel("Clientes");
    JLabel JLabelProviders = new JLabel("Proveedores");
    JButton JButtonExit = new JButton();

    public MenuPrincipal(Usuario u) {
        f.setSize(850,600);
        f.setLocationRelativeTo(null);
        f.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        f.getRootPane().setBackground(new java.awt.Color(171,213,217));
        f.getContentPane().setBackground(new java.awt.Color(171,213,217));
        f.setResizable(false);
        JPanelMenu.setLayout(new GridBagLayout());
        JPanelMenu.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Principal");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelMenu.setBorder(title);
        JPanelAdminister.setLayout(new GridBagLayout());
        JPanelTransactions.setLayout(new GridBagLayout());
        JPanelContacts.setLayout(new GridBagLayout());
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
                new Inventario(f, u);
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
                Object[] opciones = {"Venta", "Compra"};
                int opcion = JOptionPane.showOptionDialog(f, "Que tipo de transacción quiere realizar?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == JOptionPane.YES_OPTION){
                    if (u.productos.size()!=0){
                        new Ventas(f, u);
                    } else {
                        JOptionPane.showMessageDialog(f, "No existen productos que vender.");
                    }
                } else if (opcion == JOptionPane.NO_OPTION) {
                    if (u.productos.size()!=0){
                        new Compras(f, u);
                    } else {
                        JOptionPane.showMessageDialog(f, "No existen productos que comprar.");
                    }
                }
            }
        });
        JButtonTransactionHistory.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(f, "No está implementado UnU");
            }
        });
        JButtonExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        c.weighty = 1;
        c.weightx = 0.5;
        Interfaces.addTitle(JPanelAdminister, JLabelAdministerTitle, c, 0, 0);
        Interfaces.addImageButton(JPanelAdminister, JButtonTransaction, c, 0, 1, "shopping-cart-8.png");
        Interfaces.addImageButton(JPanelAdminister, JButtonInventory, c, 1, 1, "book-3.png");
        Interfaces.addLabel(JPanelAdminister, JLabelTransaction, c, 0, 2);
        Interfaces.addLabel(JPanelAdminister, JLabelInventory, c, 1, 2);
        Interfaces.addTitle(JPanelTransactions, JLabelTransactionsTitle, c, 0, 0);
        Interfaces.addImageButton(JPanelTransactions, JButtonTransactionHistory, c, 0, 1, "receipt-5.png");
        Interfaces.addLabel(JPanelTransactions, JLabelTransactionHistory, c, 0, 2);
        Interfaces.addTitle(JPanelContacts, JLabelContactsTitle, c, 0, 0);
        Interfaces.addImageButton(JPanelContacts, JButtonClients, c, 0, 1, "add-user.png");
        Interfaces.addImageButton(JPanelContacts, JButtonProviders, c, 1, 1, "shop-5.png");
        Interfaces.addLabel(JPanelContacts, JLabelClients, c, 0, 2);
        Interfaces.addLabel(JPanelContacts, JLabelProviders, c, 1, 2);
        c.gridwidth=2;
        Interfaces.addPanel(JPanelMenu, JPanelAdminister, c, 0, 0);
        c.gridwidth=1;
        c.anchor = GridBagConstraints.PAGE_START;
        Interfaces.addPanel(JPanelMenu, JPanelTransactions, c, 0, 1);
        Interfaces.addPanel(JPanelMenu, JPanelContacts, c, 1, 1);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        Interfaces.addImage(JPanelMenu, JLabelLogo, c, 0, 0, "image(1).png");
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelMenu, JButtonExit, c, 0, 1, "arrow-34.png");
        f.add(JPanelMenu);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}