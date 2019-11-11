package interfaz;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public class Test extends JFrame {
    private Button button1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField jTextField;
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;
    private TextField textField;

    public Test() {
        this.initComponents();
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.label1 = new Label();
        this.label2 = new Label();
        this.label3 = new Label();
        this.label4 = new Label();
        this.textField = new TextField();
        this.button1 = new Button();
        this.label5 = new Label();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.jTextField = new JTextField();
        this.setDefaultCloseOperation(3);
        this.label1.setFont(new Font("Dialog", 0, 24));
        this.label1.setText("CLIENTES");
        this.label2.setText("Agregar nuevo cliente");
        this.label3.setText("Ingrese el nombre");
        this.label4.setText("El ID se genera automáticamente");
        this.button1.setLabel("Agregar");
        this.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Test.this.button1ActionPerformed(evt);
            }
        });
        this.label5.setText("Nombre:");
        this.jTable1.setModel(new DefaultTableModel(new Object[][]{{null, null, null}, {null, null, null}, {null, null, null}, {null, null, null}}, new String[]{"Nombre", "ID", "Número de compras"}) {
            boolean[] canEdit = new boolean[]{true, false, true};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane1.setViewportView(this.jTable1);
        this.jTextField.setText("jTextField1");
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(309, 309, 309).addComponent(this.label1, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGap(296, 296, 296).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.label3, -2, -1, -2)).addComponent(this.label2, -2, -1, -2))).addGroup(jPanel1Layout.createSequentialGroup().addGap(327, 327, 327).addComponent(this.button1, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGap(262, 262, 262).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.label5, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.textField, -2, 154, -2)).addComponent(this.label4, -2, -1, -2)).addGap(23, 23, 23).addComponent(this.jTextField, -2, -1, -2))).addContainerGap(149, 32767)).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jScrollPane1, -2, -1, -2).addGap(137, 137, 137)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(this.label1, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.label2, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.label3, -2, 19, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.label4, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.label5, -1, -1, 32767).addGap(61, 61, 61)).addGroup(jPanel1Layout.createSequentialGroup().addGap(141, 141, 141).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.textField, -2, -1, -2).addComponent(this.jTextField, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.button1, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767))).addComponent(this.jScrollPane1, -2, 113, -2).addGap(57, 57, 57)));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        this.pack();
    }

    private void button1ActionPerformed(ActionEvent evt) {
        this.jTextField.setText(this.textField.getText());
    }

    public static void main(String[] args) {
        try {
            LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new Test()).setVisible(true);
            }
        });
    }
}