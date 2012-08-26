package raahauspeli;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class KasiTransferHandler extends TransferHandler
{
     private int index = -1;
     private boolean alkuunPain = false;
        public KasiTransferHandler()
        {
            super();
        }

 
        public boolean canImport(TransferHandler.TransferSupport info) {
            info.setShowDropLocation(false); // no visual feedback
        try {
            // Check for String flavor
            if (!info.isDataFlavorSupported(new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType +
               ";class=raahauspeli.PokeriHanska")))
                 {
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KasiTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            return true;
           }

        protected Transferable createTransferable(JComponent c) {           
            // this handler is bound on a JList
            JList list = (JList)c;
            index = list.getSelectedIndex();
            PokeriHanska hanska = (PokeriHanska)list.getSelectedValue();
                       
            return new PokeriHanskaTransferable(hanska);
        }

        public int getSourceActions(JComponent c) {
       
            return TransferHandler.MOVE;

        }

        /**
         * Perform the actual import.  
         */
        public boolean importData(TransferHandler.TransferSupport info) {
            if (!info.isDrop()) {
                return false;
            }

            JList list = (JList)info.getComponent(); // get target list
            DefaultListModel listModel = (DefaultListModel)list.getModel();

            // Get the string that is being dropped.
            Transferable t = info.getTransferable();
            PokeriHanska data;
            // get data
            try {
                data = (PokeriHanska)t.getTransferData(
                        new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType)
                        );
            }
            catch (Exception e) { return false; }

            /*
             * Katsotaan mihin kohtaa listaa halutaan tiputtaa. Ensin etsitään
             * info-oliosta pudotuspaikka. Sitten tutkitaan mille riville pudotuspaikka osui.
             * Tässä käytetään listan getFixedCellHeight(), joka on määritely ikkunan rakentajassa.
            */               
            DropLocation tiputusPaikka = info.getDropLocation();
            int osumaIndeksi = 0;

            for (int i = 0; i<listModel.getSize(); i++) {
                if (( i*list.getFixedCellHeight()) < tiputusPaikka.getDropPoint().getY() && 
                   tiputusPaikka.getDropPoint().getY() < ( (i+1)*list.getFixedCellHeight())) {
                    osumaIndeksi = i;
                }
            }
            if (index > osumaIndeksi) {
                alkuunPain = true;
            }
            
            //Tarkistetaan vielä onko tiputuspaikka listan indeksien ulkopuolella, jolloin tiputetaan
            //uusi elementti listan viimeiseksi. Muussa tapauksessa elementti lisätään indeksin määrittämään kohtaan.
            if (tiputusPaikka.getDropPoint().getY()>listModel.getSize()*list.getFixedCellHeight()) {
                listModel.addElement(data);
            } else {
                if (alkuunPain) 
                {
                    listModel.add(osumaIndeksi, data);
                } 
                else
                {
                    listModel.add(osumaIndeksi+1, data);
                }
                
            }          
        
            return true;
        }

        /**
         * Remove the items moved from the list.
         */
        protected void exportDone(JComponent c, Transferable data, int action) {
            // drop successful
            JList source = (JList)c;
            DefaultListModel listModel  = (DefaultListModel)source.getModel();

            if (alkuunPain) {
                index++;
            }
            System.out.println(listModel.size());
            if (action == TransferHandler.MOVE) {
                listModel.remove(index);
            }

            alkuunPain = false;
            index = -1;
            boolean voititko = RaahausPeliPaneeli.testaaVoitto();
        }  
}
