/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.Kortti;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class OpiTuntemaanHanskat extends JPanel implements ActionListener{
    
    private JButton oikealle;
    private JButton vasemmalle;
    private JPanel kasiTaulunPaikka;
    private JEditorPane ohjeTeksti;
    private JPanel ohjeTekstinPaikka;
    private KasiTaulu kasiTaulu;
    
    private String[] kuvaukset = {"Hai, eli high card, on nimensä mukaisesti käsi,"
            + " jossa ei ole mitään kombinaatioita vaan käden arvon määrittää sen \n"
            + "korkein kortti. Esimerkkikädessä korkein kortti on ässä, joten käden"
            + " nimi on \"ässä-hai\".","LOOOOLL"};
    
    public OpiTuntemaanHanskat() {
        
        this.setPreferredSize(new Dimension(800, 500));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));   
        kasiTaulu = new KasiTaulu(new PokeriHanska(PokeriHanska.Arvo.HAI));
        kasiTaulunPaikka = new JPanel();
        kasiTaulunPaikka.add(kasiTaulu);
        this.add(kasiTaulunPaikka);
        ohjeTeksti = new JEditorPane();
        vasemmalle = new JButton("Edellinen käsi");
        oikealle = new JButton("Seuraava käsi");
        vasemmalle.addActionListener(this);
        vasemmalle.setEnabled(false);
        oikealle.addActionListener(this);
        JPanel tyhja = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tyhja.setPreferredSize(new Dimension(125, 250));
        JPanel tyhja2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tyhja2.setPreferredSize(new Dimension(125, 250));
        tyhja.add(vasemmalle);
        tyhja2.add(oikealle);
        this.add(tyhja); //this.add(vasemmalle);
        ohjeTeksti.setPreferredSize(new Dimension(540, 250));
        ohjeTeksti.setBackground(this.getBackground());
        ohjeTeksti.setText(kuvaukset[0]);
        ohjeTekstinPaikka = new JPanel();
        ohjeTekstinPaikka.add(ohjeTeksti);
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
            PokeriHanska tempHanska = kasiTaulu.getHanska();
            kasiTaulu = new KasiTaulu(new PokeriHanska(tempHanska.arvo.ordinal()-1));
            kasiTaulunPaikka.removeAll();
            ohjeTeksti.setText(kuvaukset[1]);
            kasiTaulunPaikka.add(kasiTaulu);
            kasiTaulunPaikka.validate();
        } else {
            
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
            kortit = teePariKasi();
            switch(hanska.arvo.ordinal()) {
                case 8:
                {
                    System.out.println("TÄÄLLÄ");
                    kortit = teeHaiKasi();
                    break;
                }
                case 7:
                {
                    System.out.println("TÄÄLLÄ");
                    kortit = teePariKasi();
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

        private Kortti[] teePariKasi() {
            Kortti[] kortit = new Kortti[5];
    
        kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));
        kortit[1] = new Kortti("pata", 13, new Point(10, 10));
        kortit[2] = new Kortti("hertta", 5, new Point(10, 10));
        kortit[3] = new Kortti("ruutu", 14, new Point(10, 10));
        kortit[4] = new Kortti("risti", 9, new Point(10, 10));
        return kortit;
        }
    }
    
    
    
    public Kortti[] teeHaiKasi() {
        Kortti[] kortit = new Kortti[5];
    
        kortit[0] = new Kortti("ruutu", 13, new Point(10, 10));
        kortit[1] = new Kortti("pata", 11, new Point(10, 10));
        kortit[2] = new Kortti("hertta", 5, new Point(10, 10));
        kortit[3] = new Kortti("ruutu", 14, new Point(10, 10));
        kortit[4] = new Kortti("risti", 9, new Point(10, 10));
        return kortit;
    }
    
}
