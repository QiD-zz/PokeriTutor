
package raahauspeli;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class PokeriHanskaTransferable implements Transferable{

     public PokeriHanskaTransferable(PokeriHanska hanska) {
            data = hanska;
        }       
        
        private PokeriHanska data;


        @Override
        public DataFlavor[] getTransferDataFlavors() {
            DataFlavor tuettu = null;
            try {
               
               tuettu = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType +
               ";class=raahauspeli.PokeriHanska");
                
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
            if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
                return data;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }
        
    
    
}
