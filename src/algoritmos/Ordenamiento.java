package algoritmos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Ordenamiento {
    public static void ordenarTabla(JTable tb, DefaultTableModel model, int col){
        if (String.valueOf(tb.getValueAt(0, col)).compareTo(String.valueOf(tb.getValueAt(model.getRowCount()-1, col)))>0){
            for (int i=0;i<model.getRowCount()-1;i++){
                for (int j=0;j<model.getRowCount()-1;j++){
                    if(String.valueOf(tb.getValueAt(j, col)).compareTo(String.valueOf(tb.getValueAt(j+1, col)))>0){
                        model.moveRow(j, j, j+1);
                    }
                }
            }
        } else {
            for (int i=0;i<model.getRowCount()-1;i++){
                for (int j=0;j<model.getRowCount()-1;j++){
                    if(String.valueOf(tb.getValueAt(j, col)).compareTo(String.valueOf(tb.getValueAt(j+1, col)))<0){
                        model.moveRow(j, j, j+1);
                    }
                }
            }
        }
        tb.revalidate();
    }
    public static void ordenarFechas(JTable tb, DefaultTableModel model, ArrayList<GregorianCalendar> arr){
        GregorianCalendar temp;
        if (arr.get(0).compareTo(arr.get(arr.size()-1))>0){
            for (int i=0;i<model.getRowCount()-1;i++){
                for (int j=0;j<model.getRowCount()-1;j++){
                    if(arr.get(j).compareTo(arr.get(j+1))>0){
                        model.moveRow(j, j, j+1);
                        temp=arr.get(j);
                        arr.set(j, arr.get(j+1));
                        arr.set(j+1, temp);
                    }
                }
            }
        } else {
            for (int i=0;i<model.getRowCount()-1;i++){
                for (int j=0;j<model.getRowCount()-1;j++){
                    if(arr.get(j).compareTo(arr.get(j+1))<0){
                        model.moveRow(j, j, j+1);
                        temp=arr.get(j);
                        arr.set(j, arr.get(j+1));
                        arr.set(j+1, temp);
                    }
                }
            }
        }
        tb.revalidate();
    }
}