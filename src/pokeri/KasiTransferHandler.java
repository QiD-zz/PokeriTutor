package pokeri;

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
                }
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
                listModel.add(osumaIndeksi, data);
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

            if (action == TransferHandler.MOVE) {
                listModel.remove(index);
            }

            index = -1;
        }
    /*
        private int index = -1;
        private int osumaIndeksi = -1;
        private int rivienLkm;
        private boolean siirretty = false;
        private JList lahtoTaulu;
        private JList tuloTaulu;
        
        public KasiTransferHandler()
        {
            super();
        }

    
        public boolean canImport(TransferHandler.TransferSupport info) {
            info.setShowDropLocation(false); // no visual feedback
          
            if (info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
            } else {
                return true;
            }
         
            
        }

    
        protected Transferable createTransferable(JComponent c) {           
            // this handler is bound on a JList
            lahtoTaulu = (JList)c;            
            index = lahtoTaulu.getSelectedIndex();
            rivienLkm = lahtoTaulu.getVisibleRowCount();
            String kasi = (String)lahtoTaulu.getSelectedValue();
            
         /*   for (int i = 0; i < itemit.length; i++) {
                itemit[i] = (Task)lahtoTaulu.getValueAt(index+i, 0);               
            }
            
                       
            return new StringSelection(kasi);
        }


  
        public int getSourceActions(JComponent c) {
       
                return TransferHandler.MOVE;
            
           // return TransferHandler.COPY_OR_MOVE;
        }

        public boolean importData(TransferHandler.TransferSupport info) {
            if (!info.isDrop()) {
                return false;
            }

            tuloTaulu = (JList)info.getComponent(); // get target list
            DefaultListModel listModel = (DefaultListModel)tuloTaulu.getModel();

            // Get the string that is being dropped.
            Transferable t = info.getTransferable();
            String data;
            // get data
            try {
              //  DataFlavor objectFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
                 data = (String)t.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception e) { return false; }

               
            TransferHandler.DropLocation tiputusPaikka = info.getDropLocation();
        //    osumaIndeksi = -1;
            System.out.println("Osumapaikka x: "+tiputusPaikka.getDropPoint().getX()+" Osumapaikka y: "+tiputusPaikka.getDropPoint().getY());

            for (int i = 0; i<listModel.getSize(); i++) {
                if (( i*tuloTaulu.getFixedCellHeight()) < tiputusPaikka.getDropPoint().getY() && 
                   tiputusPaikka.getDropPoint().getY() < ( (i+1)*tuloTaulu.getFixedCellHeight())) {
                    osumaIndeksi = i;
                }
            }
           System.out.println("Rivien korkeus: "+tuloTaulu.getFixedCellHeight()*tuloTaulu.getFixedCellHeight()); 
            if (tiputusPaikka.getDropPoint().getY()>listModel.getSize()*tuloTaulu.getFixedCellHeight()) {
                listModel.addElement(data);
            } else {
                listModel.add(osumaIndeksi, data);
            } 
            
                  
            
            // note that we still do not distinguish between copy and move
            
            return true;
        }

 
        protected void exportDone(JComponent c, Transferable data, int action) {
            // drop successful
            JList source = (JList)c;
            DefaultListModel tableModel  = (DefaultListModel)source.getModel();

           // if (action == TransferHandler.MOVE) {
         //   if (osumaIndeksi < index) {
        
            
            for (int i = 0; i < rivienLkm; i++) {
                tableModel.removeRow(index);
                
            }
                
                if (!siirretty ) {
                //    for (int i = 0; i < rivienLkm; i++) {
                        tableModel.moveRow(source.getRowCount()-rivienLkm, source.getRowCount()-1, osumaIndeksi);
                        
               //     }
                    
                    // && tuloTaulu.equals(lahtoTaulu)
                            
                }

                siirretty = false;
            index = -1;
        }
        
        @Override
        public Icon getVisualRepresentation(Transferable t) {
              
            return super.getVisualRepresentation(t);
        }

       
      */
    
}
