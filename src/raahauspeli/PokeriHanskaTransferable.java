/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raahauspeli;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author hurvittelu
 */
public class PokeriHanskaTransferable implements Transferable{

     public PokeriHanskaTransferable(PokeriHanska hanska) {
            data = hanska;
        }       
        
        private PokeriHanska data;


        @Override
        public DataFlavor[] getTransferDataFlavors() {
            DataFlavor tuettu = null;
            try {
               //tuettu = new DataFlavor(Class.forName("datamodels.Task"), "Task");
               tuettu = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType +
               ";class=raahauspeli.PokeriHanska");
                // tuettu =  new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace(System.err);
            }
            
            DataFlavor[] temp = {tuettu};
            return temp;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            if (flavor.getPrimaryType().equals(DataFlavor.javaJVMLocalObjectMimeType)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            //System.out.println("Getting transfer data");
            //System.out.println("flavor:" + flavor.toString());
            if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
                //System.out.println("Wut?");
                return data;
            } else {
                //System.out.println("And we haaave aaan errooor!");
                throw new UnsupportedFlavorException(flavor);
            }
        }
        
    
    
}
