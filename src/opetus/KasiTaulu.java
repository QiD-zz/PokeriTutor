package opetus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.Kortti;
import raahauspeli.PokeriHanska;

public class KasiTaulu extends JPanel
    {
    
    private Kortti[] kortit;
        private PokeriHanska hanska;

        public KasiTaulu(PokeriHanska hanska_)
        {
            hanska = hanska_;
            int hanskanLuku = hanska.arvo.ordinal();
            System.out.println(hanskanLuku);

            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.lightGray);
            Kortti[] kortit = new Kortti[5];
            kortit = teeKasi(PokeriHanska.HAI);
            this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, 170));
            switch(hanska.arvo.ordinal()) {
                case 8:
                    kortit = teeKasi(PokeriHanska.HAI);
                    break;
                case 7:
                    kortit = teeKasi(PokeriHanska.PARI);
                    break;
                 case 6:
                    kortit = teeKasi(PokeriHanska.KAKSIPARIA);
                    break;
                case 5:
                    kortit = teeKasi(PokeriHanska.KOLMOSET);
                    break;
                case 4:
                    kortit = teeKasi(PokeriHanska.SUORA);
                    break;
                case 3:
                    kortit = teeKasi(PokeriHanska.VARI);
                    break;
                 case 2:
                    kortit = teeKasi(PokeriHanska.TAYSKASI);
                    break;
                case 1:
                    kortit = teeKasi(PokeriHanska.NELOSET);
                    break;
                case 0:
                    kortit = teeKasi(PokeriHanska.VARISUORA);
                    break;
            }

            for (int i = 0; i < kortit.length; i++) {
                kortit[i].setPreferredSize(new Dimension(120, 170));
                this.add(kortit[i]);
            }
        }
        
        public void setKorttiTaulussa(Kortti kortti, int paikka) {
            kortit[paikka] = kortti;
            this.remove(paikka);
            this.add(kortti, paikka);
        }

        public PokeriHanska getHanska()
        {
            return hanska;
        }

        private Kortti[] teeKasi(String kasi)
        {
            kortit = new Kortti[Extern.KORTTEJA_POYDALLA];

            if (kasi.equals(PokeriHanska.HAI)) {
                kortit[0] = new Kortti("ruutu", 4, Extern.PERUSPISTE10X10Y);
                kortit[1] = new Kortti("risti", 9, Extern.PERUSPISTE10X10Y);
                kortit[2] = new Kortti("risti", 13, Extern.PERUSPISTE10X10Y);
                kortit[3] = new Kortti("hertta", 5, Extern.PERUSPISTE10X10Y);
                kortit[4] = new Kortti("ruutu", 14, Extern.PERUSPISTE10X10Y);
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.PARI)) {
                kortit[0] = new Kortti("risti", 14, new Point(10, 10));
                kortit[1] = new Kortti("ruutu", 14, new Point(10, 10));
                kortit[2] = new Kortti("risti", 9, new Point(10, 10));
                kortit[3] = new Kortti("ruutu", 10, new Point(10, 10));
                kortit[4] = new Kortti("pata", 13, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.KAKSIPARIA)) {
                kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));
                kortit[1] = new Kortti("ruutu", 14, new Point(10, 10));
                kortit[2] = new Kortti("risti", 13, new Point(10, 10));
                kortit[3] = new Kortti("pata", 11, new Point(10, 10));
                kortit[4] = new Kortti("hertta", 14, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.KOLMOSET)) {
                kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));
                kortit[1] = new Kortti("risti", 9, new Point(10, 10));
                kortit[2] = new Kortti("risti", 14, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 14, new Point(10, 10));
                kortit[4] = new Kortti("ruutu", 14, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.SUORA)) {
                kortit[0] = new Kortti("risti", 7, new Point(10, 10));
                kortit[1] = new Kortti("ruutu", 8, new Point(10, 10));
                kortit[2] = new Kortti("risti", 9, new Point(10, 10));
                kortit[3] = new Kortti("ruutu", 10, new Point(10, 10));
                kortit[4] = new Kortti("pata", 11, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.VARI)) {
                kortit[0] = new Kortti("pata", 13, new Point(10, 10));
                kortit[1] = new Kortti("pata", 8, new Point(10, 10));
                kortit[2] = new Kortti("pata", 5, new Point(10, 10));
                kortit[3] = new Kortti("pata", 2, new Point(10, 10));
                kortit[4] = new Kortti("pata", 14, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.TAYSKASI)) {
                kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));
                kortit[1] = new Kortti("risti", 14, new Point(10, 10));
                kortit[2] = new Kortti("risti", 13, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 13, new Point(10, 10));
                kortit[4] = new Kortti("ruutu", 14, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.NELOSET)) {
                kortit[0] = new Kortti("risti", 14, new Point(10, 10));
                kortit[1] = new Kortti("ruutu", 14, new Point(10, 10));
                kortit[2] = new Kortti("pata", 14, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 14, new Point(10, 10));
                kortit[4] = new Kortti("pata", 13, new Point(10, 10));
                setValintaPoisPaalta();
            } else if (kasi.equals(PokeriHanska.VARISUORA)) {
                kortit[0] = new Kortti("hertta", 10, new Point(10, 10));
                kortit[1] = new Kortti("hertta", 11, new Point(10, 10));
                kortit[2] = new Kortti("hertta", 12, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 13, new Point(10, 10));
                kortit[4] = new Kortti("hertta", 14, new Point(10, 10));
                setValintaPoisPaalta();
            } else
                return null;

            setValintaPoisPaalta();
            return kortit;
        }
        
        public void setValintaPoisPaalta()
    {
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++)
            kortit[i].valintaPaalleTaiPois(false);
        repaint();
    }
    }
    

