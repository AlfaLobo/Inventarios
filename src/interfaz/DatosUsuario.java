package interfaz;

import datos.Usuario;

import javax.swing.*;

public class DatosUsuario {
    JDialog d;
    JLabel JLabelName = new JLabel();
    JLabel JLabelLastName = new JLabel();
    JLabel JLabelInvestment = new JLabel();
    JLabel JLabelProfit = new JLabel();
    JLabel JLabelBalance = new JLabel();
    public DatosUsuario(JFrame f, Usuario u){
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(375,400);
        JLabelName.setBounds(50, 30, 200, 30);
        JLabelLastName.setBounds(50, 60, 200, 30);
        JLabelInvestment.setBounds(50, 90, 200, 30);
        JLabelProfit.setBounds(50, 120, 200, 30);
        JLabelBalance.setBounds(50, 150, 200, 30);
        d.setLayout(null);
        d.setResizable(false);
        JLabelName.setText("Nombre: "+u.nombre);
        JLabelLastName.setText("Apellidos: "+u.apellidos);
        JLabelInvestment.setText("Inversión: "+u.inversion);
        JLabelProfit.setText("Ganancias: "+u.ganancia);
        JLabelBalance.setText("Saldo: "+u.saldo);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        d.add(JLabelName);
        d.add(JLabelLastName);
        d.add(JLabelInvestment);
        d.add(JLabelProfit);
        d.add(JLabelBalance);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
