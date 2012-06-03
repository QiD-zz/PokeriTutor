/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri;

import javax.swing.*;

/**
 *
 * @author QiD
 */
public class MainMenu extends JFrame{
    
    private JPanel vasenPaneeli;
    private JButton raahauspeli;
    private JPanel oikea;
    private DefaultListModel listamalli;
    private JList lista;
    
    public MainMenu() {
        super("TaoTao menee metsään!");
        vasenPaneeli = new JPanel();
        raahauspeli = new JButton("Raahauspeli");
                
        KasiTransferHandler handleri = new KasiTransferHandler();
        oikea = new JPanel();
        listamalli = new DefaultListModel();
        lista = new JList(listamalli);
        listamalli.addElement("Pari");
        listamalli.addElement("Neloset");
        listamalli.addElement("Suora");
        listamalli.addElement("Väri");
        vasenPaneeli.add(raahauspeli);
        oikea.add(lista);
        lista.setDragEnabled(true);
        lista.setTransferHandler(handleri);
        this.getContentPane().add(vasenPaneeli);
        this.getContentPane().add(oikea);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        
    }
    
}
