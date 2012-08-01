/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.Kortti;
import pokeri.MainMenu;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class OpiTuntemaanHanskat extends JPanel implements ActionListener{
    
    private JButton oikealle;
    private JButton vasemmalle;
    private JPanel kasiTaulunPaikka;
    private JLabel nykyinenKasi;
    private JPanel nimenPaneeli;
    private JPanel ohjeTekstinPaikka;
    private KasiTaulu kasiTaulu;
    private JLabel seuraavaParempi;
    private JLabel edellinenHuonompi;
    private MainMenu main;
    
    private int kuvausLaskuri;
    
    private String[] kuvaukset = {"Hai, eli high card, on nimensä mukaisesti käsi,"
            + " jossa ei ole mitään kombinaatioita vaan käden arvon määrittää sen "
            + "korkein kortti. Esimerkkikädessä korkein kortti on ässä, joten käden"
            + " nimi on \"ässä-hai\".","Pari on käsi, jossa on kaksi samaa korttia."
            + " Pari on pokerikäsistä toiseksi huonoin. Kahden pari-käden vahvuus "
            + "määritellään niiden parin suuruudesta. Esimerkkikätemme on kuningaspari,"
            + " joka on toiseksi paras pari heti ässäparin jälkeen. Huonoin pari on kakkospari.",
    "Kaksi paria kolmanneksi huonoin pokerikäsi. Käden vahvuuden määrittää ensisijaisesti"
            + " isompi pari ja toissijaisesti pienempi pari. Eli jos vastakkain on kaksi"
            + "kahden parin kättä ensin verrataan keskenään suurempia pareja ja jos"
            + " ne on samoja verrataan pienempiä pareja. Esimerkiksi kasit ja kakkoset voittaa "
            + "kutoset ja seiskat, vaikka jälkimmäisen käden yhteissumma on suurempi, koska"
            + " ensimmäisen käden toinen pari on suurempi.",
    "Kolme samaa eli kolmoset on neljänneksi paras käsi pokerissa. Kahden kolmoskäden "
            + "vahvuuden vertailu on helppoa, koska jokerittomassa pakassa voi olla vain "
            + "yhdet kolmoset, joka arvolla. Isoimmat kolmoset ovat parhaat, eli esimerkkikätemme "
            + "ässäkolmoset.","Suora on käsi, jossa on viisi peräkkäistä korttia eri maista."
            + " Suoran arvo suhteessa toiseen suoraan määritellään sen korkeimman tai"
            + " matalimman kortin mukaan.","Väri on käsi, jossa kaikki viisi korttia "
            + "on samasta maasta, mutta ne eivät ole peräkkäisiä keskenään.","Täyskädessä on"
            + " kolmoset ja pari samassa kädessä. Täyskäden arvon määrittää kolmosten"
            + " koko eli käsi QQQAA (kolme rouvaa ja kaksi ässää) on huonompi kuin"
            + " KKK22 (kolme kuningasta ja kakkospari).","Neljä samaa eli neloset"
            + " on pokerin toiseksi paras käsi. Nelosissa pelaajalla on kädessään"
            + " jokaisen maan kortti samalla arvolla eli esimerkiksi neljä ässää "
            + "kuten esimerkkikädessä.","Värisuora on pokerin paras käsi ja "
            + "sen erästä versioita, kuninkaallista (suora kympistä ässään) herttavärisuoraa"
            + " eli herttareetiä pidetään kaikkein parhaimpana kätenä. Todellisuudessa"
            + " maalla ei useimmissa pokeriversioissa ole väliä, jolloin patavärisuora"
            + " samoilla numeroilla"
            + " olisi aivan yhtä hyvä käsi kuin herttavärisuora. "};
    
    public OpiTuntemaanHanskat(MainMenu m) {
        main = m;
        
        kuvausLaskuri = 0;
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); 
        PokeriHanska alkuHanska = new PokeriHanska(PokeriHanska.Arvo.HAI);
        kasiTaulu = new KasiTaulu(alkuHanska);
        kasiTaulunPaikka = new JPanel();
        kasiTaulunPaikka.add(kasiTaulu);
        this.add(kasiTaulunPaikka);
        nykyinenKasi = new JLabel();
        nykyinenKasi.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        vasemmalle = new JButton("Edellinen käsi");
        edellinenHuonompi = new JLabel();
        seuraavaParempi = new JLabel(PokeriHanska.PARI);
        oikealle = new JButton("Seuraava käsi");
        vasemmalle.addActionListener(this);
        vasemmalle.setEnabled(false);
        oikealle.addActionListener(this);
        JPanel tyhja = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tyhja.setPreferredSize(new Dimension(125, 250));
        JPanel tyhja2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tyhja2.setPreferredSize(new Dimension(125, 250));
        tyhja.add(vasemmalle);
        tyhja.add(edellinenHuonompi);
        tyhja2.add(oikealle);
        tyhja2.add(seuraavaParempi);
        this.add(tyhja); //this.add(vasemmalle);
        nimenPaneeli = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nimenPaneeli.setPreferredSize(new Dimension(540, 250));
       // nykyinenKasi.setPreferredSize(new Dimension(540, 250));
        nykyinenKasi.setBackground(this.getBackground());
      //  ohjeTeksti.setText(kuvaukset[kuvausLaskuri]);
        nykyinenKasi.setLayout(new FlowLayout(FlowLayout.CENTER));
        nykyinenKasi.setText(alkuHanska.getHanskaName());
        nimenPaneeli.add(nykyinenKasi);
        main.setOhjeTekstiAlue(kuvaukset[kuvausLaskuri]);
        ohjeTekstinPaikka = new JPanel();
        ohjeTekstinPaikka.add(nimenPaneeli);
        this.add(ohjeTekstinPaikka);
        this.add(tyhja2); //this.add(oikealle);
        
      /*  for (int i = 0; i < kortit.length; i++) {
            JButton button = new JButton("Lukitse");
            button.setPreferredSize(new Dimension(120, 50));
            this.add(button);
            
        }*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(oikealle)) {
            
            vasemmalle.setEnabled(true);
            PokeriHanska vanhaHanska = kasiTaulu.getHanska();
            PokeriHanska uusHanska = new PokeriHanska(vanhaHanska.arvo.ordinal()-1);
            kasiTaulu = new KasiTaulu(uusHanska);
            kasiTaulunPaikka.removeAll();
                       
            edellinenHuonompi.setText(vanhaHanska.getHanskaName());
            kuvausLaskuri++;
            nykyinenKasi.setText(uusHanska.getHanskaName());
            main.setOhjeTekstiAlue(kuvaukset[kuvausLaskuri]);
            kasiTaulunPaikka.add(kasiTaulu);
            kasiTaulunPaikka.validate();
            
            if (kuvausLaskuri == 8) {
                oikealle.setEnabled(false);
                seuraavaParempi.setText("");
            } else {
                PokeriHanska seuraavaHanska = new PokeriHanska(uusHanska.arvo.ordinal()-1);
                seuraavaParempi.setText(seuraavaHanska.getHanskaName());
            }
        } else {
            
            oikealle.setEnabled(true);
            PokeriHanska vanhaHanska = kasiTaulu.getHanska();
            PokeriHanska uusHanska = new PokeriHanska(vanhaHanska.arvo.ordinal()+1);
            kasiTaulu = new KasiTaulu(uusHanska);
            kasiTaulunPaikka.removeAll();
            
            seuraavaParempi.setText(vanhaHanska.getHanskaName());
           
            
            kuvausLaskuri--;
            nykyinenKasi.setText(uusHanska.getHanskaName());
            main.setOhjeTekstiAlue(kuvaukset[kuvausLaskuri]);
            kasiTaulunPaikka.add(kasiTaulu);
            kasiTaulunPaikka.validate();
            
            if (kuvausLaskuri == 0) {
                vasemmalle.setEnabled(false);
                 edellinenHuonompi.setText("");
            } else {
                PokeriHanska seuraavaHanska = new PokeriHanska(uusHanska.arvo.ordinal()+1);
                edellinenHuonompi.setText(seuraavaHanska.getHanskaName());
            }
        }
    }
    
    private class KasiTaulu extends JPanel {
        
        private PokeriHanska hanska;
        
        public KasiTaulu(PokeriHanska hanska_) {
            hanska = hanska_;
            int hanskanLuku = hanska.arvo.ordinal();
            System.out.println(hanskanLuku);
            this.setPreferredSize(new Dimension(800, 170));
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            Kortti[] kortit = new Kortti[5];
            kortit = teeKasi(PokeriHanska.HAI);
            switch(hanska.arvo.ordinal()) {
                case 8:
                {
                    System.out.println("TÄÄLLÄ");
                    kortit = teeKasi(PokeriHanska.HAI);
                    break;
                }
                case 7:
                {
                    System.out.println("TÄÄLLÄ");
                    kortit = teeKasi(PokeriHanska.PARI);
                    break;
                }
                 case 6:
                {
                    kortit = teeKasi(PokeriHanska.KAKSIPARIA);;
                    break;
                }
                case 5:
                {
                    kortit = teeKasi(PokeriHanska.KOLMOSET);
                    break;
                }   
                  case 4:
                {
                    kortit = teeKasi(PokeriHanska.SUORA);
                    break;
                }
                case 3:
                {
                    kortit = teeKasi(PokeriHanska.VARI);
                    break;
                }
                 case 2:
                {
                    kortit = teeKasi(PokeriHanska.TAYSKASI);
                    break;
                }
                case 1:
                {
                    kortit = teeKasi(PokeriHanska.NELOSET);
                    break;
                }   
                case 0:
                {
                    kortit = teeKasi(PokeriHanska.VARISUORA);
                    break;
                }  
            }
            
        
            for (int i = 0; i < kortit.length; i++) {
                kortit[i].setPreferredSize(new Dimension(120, 170));
                this.add(kortit[i]);
            }
        }
        
        public PokeriHanska getHanska() {
            return hanska;
        }
        
        private Kortti[] teeKasi(String kasi) {
            Kortti[] kortit = new Kortti[5];
            
            if (kasi.equals(PokeriHanska.HAI)) {
                kortit[0] = new Kortti("ruutu", 4, new Point(10, 10));       
                kortit[1] = new Kortti("risti", 9, new Point(10, 10));
                kortit[2] = new Kortti("risti", 13, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 5, new Point(10, 10));
                kortit[4] = new Kortti("ruutu", 14, new Point(10, 10));
                return kortit;           
            } else if (kasi.equals(PokeriHanska.PARI))
            {
                kortit[0] = new Kortti("risti", 14, new Point(10, 10));       
                kortit[1] = new Kortti("ruutu", 14, new Point(10, 10));        
                kortit[2] = new Kortti("risti", 9, new Point(10, 10));        
                kortit[3] = new Kortti("ruutu", 10, new Point(10, 10));      
                kortit[4] = new Kortti("pata", 13, new Point(10, 10));      
                return kortit;
            } else if (kasi.equals(PokeriHanska.KAKSIPARIA))
            {  
                kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));           
                kortit[1] = new Kortti("ruutu", 14, new Point(10, 10));     
                kortit[2] = new Kortti("risti", 13, new Point(10, 10));       
                kortit[3] = new Kortti("pata", 11, new Point(10, 10));       
                kortit[4] = new Kortti("hertta", 14, new Point(10, 10));     
                return kortit;
            } if (kasi.equals(PokeriHanska.KOLMOSET)) {
                kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));       
                kortit[1] = new Kortti("risti", 9, new Point(10, 10));
                kortit[2] = new Kortti("risti", 14, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 14, new Point(10, 10));
                kortit[4] = new Kortti("ruutu", 14, new Point(10, 10));
                return kortit;           
            } else if (kasi.equals(PokeriHanska.SUORA))
            {
                kortit[0] = new Kortti("risti", 7, new Point(10, 10));       
                kortit[1] = new Kortti("ruutu", 8, new Point(10, 10));        
                kortit[2] = new Kortti("risti", 9, new Point(10, 10));        
                kortit[3] = new Kortti("ruutu", 10, new Point(10, 10));      
                kortit[4] = new Kortti("pata", 11, new Point(10, 10));      
                return kortit;
            } else if (kasi.equals(PokeriHanska.VARI))
            {  
                kortit[0] = new Kortti("pata", 13, new Point(10, 10));           
                kortit[1] = new Kortti("pata", 8, new Point(10, 10));     
                kortit[2] = new Kortti("pata", 5, new Point(10, 10));       
                kortit[3] = new Kortti("pata", 2, new Point(10, 10));       
                kortit[4] = new Kortti("pata", 14, new Point(10, 10));     
                return kortit;
            } if (kasi.equals(PokeriHanska.TAYSKASI)) {
                kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));       
                kortit[1] = new Kortti("risti", 5, new Point(10, 10));
                kortit[2] = new Kortti("risti", 13, new Point(10, 10));
                kortit[3] = new Kortti("hertta", 5, new Point(10, 10));
                kortit[4] = new Kortti("ruutu", 5, new Point(10, 10));
                return kortit;           
            } else if (kasi.equals(PokeriHanska.NELOSET))
            {
                kortit[0] = new Kortti("risti", 14, new Point(10, 10));       
                kortit[1] = new Kortti("ruutu", 14, new Point(10, 10));        
                kortit[2] = new Kortti("pata", 14, new Point(10, 10));        
                kortit[3] = new Kortti("hertta", 14, new Point(10, 10));      
                kortit[4] = new Kortti("pata", 13, new Point(10, 10));      
                return kortit;
            } else if (kasi.equals(PokeriHanska.VARISUORA))
            {  
                kortit[0] = new Kortti("hertta", 10, new Point(10, 10));           
                kortit[1] = new Kortti("hertta", 11, new Point(10, 10));     
                kortit[2] = new Kortti("hertta", 12, new Point(10, 10));       
                kortit[3] = new Kortti("hertta", 13, new Point(10, 10));       
                kortit[4] = new Kortti("hertta", 14, new Point(10, 10));     
                return kortit;
            }
              else
            {
                return null;
            }
        }

       
    }
    
    
    
    
    
}
