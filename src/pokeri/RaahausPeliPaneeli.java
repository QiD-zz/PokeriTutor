/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author QiD
 */
public class RaahausPeliPaneeli extends JPanel
{
    private JPanel oikea;
    private DefaultListModel listamalli;
    private JList lista;
    
    public RaahausPeliPaneeli()
    {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        KasiTransferHandler handleri = new KasiTransferHandler();
        oikea = new JPanel();
        listamalli = new DefaultListModel();
        lista = new JList(listamalli);
        listamalli.addElement("Pari");
        listamalli.addElement("Neloset");
        listamalli.addElement("Suora");
        listamalli.addElement("Väri");
        oikea.add(lista);
        lista.setDragEnabled(true);
        lista.setTransferHandler(handleri);
        lista.setPreferredSize(new Dimension(200, 400));
        lista.setFixedCellHeight(50);
        lista.setFont(new Font("Arial",Font.BOLD, 29));
        this.add(oikea);
        this.setPreferredSize(new Dimension(600, 600));
    }
    
}
