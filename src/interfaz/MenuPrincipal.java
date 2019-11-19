package interfaz;

import datos.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuPrincipal {
    JFrame f = new JFrame("temp");
    JButton b = new JButton("OvO");
    JButton b1 = new JButton("Registrar Cliente");
    JButton b2 = new JButton("Registrar Empleado");
    JButton b3 = new JButton("Registrar Producto");
    JButton b4 = new JButton("Registrar Proveedor");
    JButton b5 = new JButton("Registrar Servicio");
    JButton b6 = new JButton("Revisar Clientes");
    JButton b7 = new JButton("Revisar Empleados");
    JButton b8 = new JButton("Revisar Inventario");
    JButton b9 = new JButton("Revisar Proveedores");
    JButton b10 = new JButton("Revisar Servicios");
    JButton b11 = new JButton("Nueva Venta");
    JButton b12 = new JButton("Historial de Compras");
    JButton b13 = new JButton("Nueva Compra");
    JButton b14 = new JButton("Mostrar datos de usuario");
    JButton b15 = new JButton("");
    JButton b16 = new JButton();
    JLabel l = new JLabel("Bienvenido OvO");
    JLabel l1 = new JLabel();

    public MenuPrincipal(Usuario u) {
        f.setSize(1280,720);
        l.setBounds(70,145,1280, 40);
        l1.setBounds(620,70,1280, 40);
        b.setBounds(50,175,100, 40);
        b1.setBounds(200,175,160, 40);
        b2.setBounds(410,175,160, 40);
        b3.setBounds(620,175,160, 40);
        b4.setBounds(830,175,160, 40);
        b5.setBounds(1050,175,160, 40);
        b6.setBounds(200,245,160, 40);
        b7.setBounds(410,245,160, 40);
        b8.setBounds(620,245,160, 40);
        b9.setBounds(830,245,160, 40);
        b10.setBounds(1050,245,160, 40);
        b11.setBounds(200,315,160, 40);
        b12.setBounds(410,315,160, 40);
        b13.setBounds(620,315,160, 40);
        b14.setBounds(830,315,160, 40);
        b15.setBounds(1050,315,160, 40);
        b16.setBounds(200,385,160, 40);
        f.setLayout(null);
        f.setResizable(false);

        try {
            String directorio = System.getProperty("user.dir");
            Image img = ImageIO.read(new File(directorio+"\\resources\\arrow-34.png"));
            b16.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        b.addActionListener(new ActionListener(){
            String UvU = "UvU";
            int i = 70;
            int j = 145;
            public void actionPerformed(ActionEvent e){
                l.setText(UvU);
                l.setBounds(i,j,1280, 40);
                UvU=UvU+"vU";
                i++;
                j--;
            }
        });
        l1.setText("ty");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new RegistroCliente(f, u);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new RegistroEmpleado(f, u);
            }
        });
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new RegistroProducto(f, u);
            }
        });
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new RegistroProveedor(f, u);
            }
        });
        b5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new RegistroServicio(f, u);
            }
        });
        b6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Clientes(f, u);
            }
        });
        b7.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Empleados(f, u);
            }
        });
        b8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Inventario(f, u);
            }
        });
        b9.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Proveedores(f, u);
            }
        });
        b10.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Servicios(f, u);
            }
        });
        b11.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (u.productos.size()==0){
                    l.setText("No existen productos que vender.");
                } else {
                    new RegistroVenta(f, u);
                }
            }
        });
        b12.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Compras(f, u);
            }
        });
        b13.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (u.productos.size()==0){
                    l.setText("No existen productos que comprar.");
                } else {
                    new RegistroCompra(f, u);
                }
            }
        });
        b14.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new DatosUsuario(f, u);
            }
        });
        b15.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            }
        });
        b16.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        f.add(b);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(b7);
        f.add(b8);
        f.add(b9);
        f.add(b10);
        f.add(b11);
        f.add(b12);
        f.add(b13);
        f.add(b14);
        f.add(b15);
        f.add(b16);
        f.add(l1);
        f.add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}