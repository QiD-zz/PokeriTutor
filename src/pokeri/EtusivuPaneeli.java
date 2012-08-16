package pokeri;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author hurvittelu
 */
public class EtusivuPaneeli extends JPanel
{
    private Tapahtumakuuntelija tkuuntelija;
    private MainMenu main;
    
    public EtusivuPaneeli(MainMenu m)
    {
        main = m;
        tkuuntelija = new Tapahtumakuuntelija(main);
        JButton opiKadet;
        JButton kadetJarj;
        JButton pelaaPokeri;
        JButton monivalintapeli;

        setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(238, 232, 170));

        opiKadet = new JButton("Opi tuntemaan pokerikädet");
        opiKadet.setBackground(new Color(107, 142, 35));
        opiKadet.setOpaque(true);
        opiKadet.setFocusPainted(false);
        kadetJarj = new JButton("Kädet järjestykseen -peli");
        kadetJarj.setBackground(new Color(154, 205, 50));
        kadetJarj.setOpaque(true);
        pelaaPokeri = new JButton("Käytännön pokeripeli");
        pelaaPokeri.setBackground(new Color(240, 230, 140));
        pelaaPokeri.setOpaque(true);
        monivalintapeli = new JButton("Testaa taitosi");
        monivalintapeli.setBackground(new Color(189, 183, 107));
        monivalintapeli.setOpaque(true);

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
        keskikaista.add(monivalintapeli);
        keskikaista.add(pelaaPokeri);
        this.add(keskikaista);
    }  
}
