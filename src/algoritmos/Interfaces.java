package algoritmos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Interfaces {
    public static void addPanel(JPanel p, JPanel p1, GridBagConstraints c, int x, int y){
        p1.setOpaque(false);
        c.gridx=x;
        c.gridy=y;
        p.add(p1, c);
    }
    public static void addScrollPane(JPanel p, JScrollPane sp, GridBagConstraints c, int x, int y){
        c.gridx=x;
        c.gridy=y;
        p.add(sp, c);
    }
    public static void addComboBox(JPanel p, JComboBox cb, GridBagConstraints c, int x, int y){
        c.gridx=x;
        c.gridy=y;
        p.add(cb, c);
    }
    public static void addTextField(JPanel p, JTextField t, GridBagConstraints c, int x, int y){
        p.setBackground(new java.awt.Color(232,234,232));
        Dimension n = new Dimension(200, 30);
        t.setPreferredSize(n);
        t.setMinimumSize(n);
        t.setMaximumSize(n);
        t.setSize(n);
        c.gridx=x;
        c.gridy=y;
        p.add(t, c);
    }
    public static void addImageButton(JPanel p, JButton b, GridBagConstraints c, int x, int y, String dir){
        try {
            String directorio = System.getProperty("user.dir");
            Image img = ImageIO.read(new File(directorio+"\\resources\\"+dir));
            b.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        c.gridx=x;
        c.gridy=y;
        p.add(b, c);
    }
    public static void addButton(JPanel p, JButton b, GridBagConstraints c, int x, int y){
        b.setBackground(new java.awt.Color(232,234,232));
        c.gridx=x;
        c.gridy=y;
        p.add(b, c);
    }
    public static void addCheckBox(JPanel p, JCheckBox cb, GridBagConstraints c, int x, int y){
        cb.setContentAreaFilled(false);
        c.gridx=x;
        c.gridy=y;
        p.add(cb, c);
    }
    public static  void  addImage(JPanel p, JLabel l, GridBagConstraints c, int x, int y, String dir){
        try {
            String directorio = System.getProperty("user.dir");
            Image img = ImageIO.read(new File(directorio+"\\resources\\"+dir));
            l.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
        c.gridx=x;
        c.gridy=y;
        p.add(l, c);
    }
    public static void addTitle(JPanel p, JLabel l, GridBagConstraints c, int x, int y){
        c.gridwidth = 2;
        l.setFont(new Font("Verdana", Font.PLAIN, 20));
        c.gridx=x;
        c.gridy=y;
        p.add(l, c);
        c.gridwidth = 1;
    }
    public static void addLabel(JPanel p, JLabel l, GridBagConstraints c, int x, int y){
        c.gridx=x;
        c.gridy=y;
        p.add(l, c);
    }
}
