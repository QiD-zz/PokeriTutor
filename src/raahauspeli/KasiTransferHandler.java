package raahauspeli;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.*;

/**
 *
 * @author Kuisma Kuusniemi
 */
public class KasiTransferHandler extends TransferHandler
{
     private int index = -1;
     private boolean alkuunPain = false;
        public KasiTransferHandler()
        {
            super();
        }

            /**
         * We only support importing strings.
         */
        public boolean canImport(TransferHandler.TransferSupport info) {
            info.setShowDropLocation(false); // no visual feedback
            // Check for String flavor
            if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }
            return true;
           }

        /**
         * Bundle up the selected item in a single list for export.
         */
        protected Transferable createTransferable(JComponent c) {           
            // this handler is bound on a JList
            JList list = (JList)c;
            index = list.getSelectedIndex();
            String txt = (String)list.getSelectedValue();
                       
            return new StringSelection(txt);
        }


         /**
         * We support copy and move actions.
         */
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
            String data;
            // get data
            try {
                data = (String)t.getTransferData(DataFlavor.stringFlavor);
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
                    System.out.println(list.getFixedCellHeight()*i);
                }
            }
            if (index > osumaIndeksi) {
                alkuunPain = true;
            }
            
            /*
             * Tämä on vaihtoehtoinen tapa ylemmästä asiasta. 
            for (int i = 0; i<listModel.getSize(); i++) {
                if (( i*list.getCellBounds(0, 0).getHeight()) < tiputusPaikka.getDropPoint().getY() && 
                   tiputusPaikka.getDropPoint().getY() < ( (i+1)*list.getCellBounds(0, 0).getHeight())) {
                    osumaIndeksi = i;
                }
            }*/
            
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
            
            // note that we still do not distinguish between copy and move
            
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
            System.out.println(voititko);
        }  
}
