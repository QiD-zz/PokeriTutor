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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
    private static JLabel tulos;
    private Timer kello;
    private static Date startTime;
    private static Date endTime;
    
    private static DefaultListModel listamalli;
    private JEditorPane selitys;
    private static JList lista;
    
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
        tulos.setPreferredSize(new Dimension(150, 50));
        kello = new Timer(1000, this);       
        
        oikea.add(uusiPeli);
        oikea.add(tulos);
        
        listamalli = new DefaultListModel();
        lista = new JList(listamalli);
        lista.setEnabled(false);
        PokeriHanska hanska = new PokeriHanska(PokeriHanska.Arvo.VARI);
        PokeriHanska hanska2 = new PokeriHanska(PokeriHanska.Arvo.SUORA);
        PokeriHanska hanska3 = new PokeriHanska(PokeriHanska.Arvo.VARISUORA);
        PokeriHanska hanska4 = new PokeriHanska(PokeriHanska.Arvo.HAI);
        listamalli.addElement(hanska2);
        listamalli.addElement(hanska4);
        listamalli.addElement(hanska3);
        listamalli.addElement(hanska);
        
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
            listamalli.removeAllElements();
            for (int i = 0; i < 4; i++) {
                Random rnd = new Random();
                listamalli.addElement(new PokeriHanska(rnd.nextInt(9)));
            }
            lista.setEnabled(true);
            startTime = Calendar.getInstance().getTime();
          //  kello.start();
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
    
    public static Object[] haeHanskat() {
        
        Object[] list = listamalli.toArray();
        
        return list;
    }
    
    public static boolean testaaVoitto() 
    {
        boolean voitto = true;
        PokeriHanska hanskat[] = new PokeriHanska[4];
       int vertailu[] = new int[3];
        for (int i = 0; i < hanskat.length; i++) {
            hanskat[i] = (PokeriHanska) listamalli.get(i);
            
        }
        for (int i = 0; i < hanskat.length-1; i++) {
            vertailu[i] = hanskat[i].arvo.compareTo(hanskat[i+1].arvo);
            
            if (vertailu[i] > 0) {
                voitto = false;
            }
        }
        
        if (voitto)
        {
           endTime = Calendar.getInstance().getTime();          
           long delay = endTime.getTime()-startTime.getTime();
           String tulosteksti = String.valueOf((double)delay/1000)+" sekuntia";
           tulos.setText(String.valueOf(tulosteksti));
           lista.setEnabled(false);
           return true;  
        } else
        {
            return false;
        }
       
    }
}