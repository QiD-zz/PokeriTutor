/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raahauspeli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author QiD
 */
public class RaahausPeliPaneeli extends JPanel implements ActionListener
{
    private JPanel vasen;
    private JPanel oikea;
    private JButton uusiPeli;
    private JLabel tulos;
    private Timer kello;
    private Date startTime;
    private Date endTime;
    
    private DefaultListModel listamalli;
    private JEditorPane selitys;
    private JList lista;
    
    public RaahausPeliPaneeli()
    {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        KasiTransferHandler handleri = new KasiTransferHandler();
        vasen = new JPanel();
        oikea = new JPanel();
        
        uusiPeli = new JButton("Uusi peli");
        uusiPeli.addActionListener(this);
        tulos = new JLabel();
        tulos.setBorder(BorderFactory.createTitledBorder("Tulos"));
        tulos.setPreferredSize(new Dimension(70, 50));
        kello = new Timer(1000, this);       
        
        oikea.add(uusiPeli);
        oikea.add(tulos);
        
        listamalli = new DefaultListModel();
        lista = new JList(listamalli);
        listamalli.addElement("Pari");
        listamalli.addElement("Neloset");
        listamalli.addElement("Suora");
        listamalli.addElement("Väri");
        
        lista.setDragEnabled(true);
        lista.setTransferHandler(handleri);
        lista.setPreferredSize(new Dimension(200, 400));
        lista.setFixedCellHeight(50);
        lista.setFont(new Font("Arial",Font.BOLD, 20));
     //   lista.setLocation(0, 0);
        selitys = new JEditorPane();
        selitys.setPreferredSize(new Dimension(250, 200));
        selitys.setText("Tehtävänäsi on raahat kädet oikeaan \n"
                + "järjestykseen mahdollisimman nopeasti. \n"
                + "Järjestämissuunta vaihtuu ja se ilmoitetaan aina uudestaan "
                + "uuden pelin alkaessa.");
        selitys.setBackground(this.getBackground());
        vasen.add(selitys);
        vasen.add(lista);
     //  selitys.setLocation(200, 200);
        this.add(vasen);
        this.add(oikea);
        
        this.setPreferredSize(new Dimension(800, 400));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(uusiPeli)) {
            startTime = Calendar.getInstance().getTime();
            kello.start();
        }
        if (e.getSource().equals(kello)) {
            endTime = Calendar.getInstance().getTime();
            kello.stop();
            long delay = endTime.getTime()-startTime.getTime();
            tulos.setText(String.valueOf(delay));
       //     System.out.print(delay);
         //   tulos.validate();
            
        }
    }
    
}
