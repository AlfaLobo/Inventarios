package algoritmos;

import javax.print.*; 
//doc
//DocFlavor
//PrintException
//printService
//              Lookup
//SimpleDoc


public class Funciones {
    public static void imprimirRecibo() {
        /*
         *PARA IMPRIMIR EN IMPRESORA
         */
        //String texto = "Texto";
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob docPintJob = printService.createPrintJob();
        Doc doc = new SimpleDoc(text.getBytes(), flavor, null);
        try{
            docPrintJob.print(doc, null); 
        }
        catch (Esxception e){
           e.printStackTrace();
        }
        

    }
}
