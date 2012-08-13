/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import pokeri.Tapahtumakuuntelija;

/**
 *
 * @author hurvittelu
 */
public class EtusivuPaneeli extends JPanel{
    
    private Tapahtumakuuntelija tkuuntelija;
    private MainMenu main;
    
    public EtusivuPaneeli(MainMenu m) {
        main = m;
        tkuuntelija = new Tapahtumakuuntelija(main);
        
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton opiKadet = new JButton("Opi tuntemaan pokerikädet");     
        JButton kadetJarj = new JButton("Kädet järjestykseen -peli");      
        JButton pelaaPokeri = new JButton("Käytännön pokeriopetus");
        JButton monivalintapeli = new JButton("Testaa taitosi");
    
        opiKadet.addActionListener(tkuuntelija);
        kadetJarj.addActionListener(tkuuntelija);
        pelaaPokeri.addActionListener(tkuuntelija);
        monivalintapeli.addActionListener(tkuuntelija);
        opiKadet.setActionCommand(Extern.OPETUS);
        kadetJarj.setActionCommand(Extern.RAAHAUSPELI);
        pelaaPokeri.setActionCommand(Extern.KORTTIPELI);
        monivalintapeli.setActionCommand(Extern.MONIVALINTAPELI);
        
        opiKadet.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/4, Extern.KORKEUS_IKKUNA/10));
        kadetJarj.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/4, Extern.KORKEUS_IKKUNA/10));
        pelaaPokeri.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/4, Extern.KORKEUS_IKKUNA/10));
        monivalintapeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/4, Extern.KORKEUS_IKKUNA/10));
        
        JPanel keskikaista = new JPanel(new FlowLayout(FlowLayout.CENTER));
        keskikaista.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/2, Extern.KORKEUS_IKKUNA/4));
        keskikaista.setLayout(new GridLayout(2, 2, 2, 2));
        keskikaista.add(opiKadet);
        keskikaista.add(kadetJarj);
        keskikaista.add(pelaaPokeri);
        keskikaista.add(monivalintapeli);
        this.add(keskikaista);
    }  
}
