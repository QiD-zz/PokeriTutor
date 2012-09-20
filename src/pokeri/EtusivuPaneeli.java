package pokeri;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

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
        setBackground(Extern.PASTELLITAUSTA);

        opiKadet = new JButton("1. Opi tuntemaan pokerikädet");
        opiKadet.setBackground(Extern.POKERIKADETTAUSTA);
        opiKadet.setOpaque(true);
        opiKadet.setFocusPainted(false);
        kadetJarj = new JButton("2. Kädet järjestykseen -peli");
        kadetJarj.setBackground(Extern.KADETJARJTAUSTA);
        kadetJarj.setOpaque(true);
        pelaaPokeri = new JButton("4. Käytännön pokeripeli");
        pelaaPokeri.setBackground(Extern.POKERIPELITAUSTA);
        pelaaPokeri.setOpaque(true);
        monivalintapeli = new JButton("3. Testaa taitosi");
        monivalintapeli.setBackground(Extern.TAIDOTTAUSTA);
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
        keskikaista.setBackground(Extern.PASTELLITAUSTA);
        keskikaista.add(opiKadet);
        keskikaista.add(kadetJarj);
        keskikaista.add(monivalintapeli);
        keskikaista.add(pelaaPokeri);
        this.add(keskikaista);
    }  
}
