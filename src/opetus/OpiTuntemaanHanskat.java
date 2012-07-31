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
public class OpiTuntemaanHanskat extends JPanel {
    
    private String[] kuvaukset = {"Hai, eli high card, on nimensä mukaisesti käsi,"
            + " jossa ei ole mitään kombinaatioita vaan käden arvon määrittää sen \n"
            + "korkein kortti. Esimerkkikädessä korkein kortti on ässä, joten käden"
            + " nimi on \"ässä-hai\"."};
    
    public OpiTuntemaanHanskat() {
        
        this.setPreferredSize(new Dimension(640, 350));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));   
        this.add(new KasiTaulu(new PokeriHanska(PokeriHanska.Arvo.VARI)));
        JEditorPane pane = new JEditorPane();
        pane.setPreferredSize(new Dimension(640, 250));
        pane.setBackground(this.getBackground());
        pane.setText(kuvaukset[0]);
        this.add(pane);
        
      /*  for (int i = 0; i < kortit.length; i++) {
            JButton button = new JButton("Lukitse");
            button.setPreferredSize(new Dimension(120, 50));
            this.add(button);
            
        }*/
    }
    
    private class KasiTaulu extends JPanel {
        
        public KasiTaulu(PokeriHanska hanska) {
            Kortti[] kortit = new Kortti[5];
            kortit = teeHaiKasi();
        
            for (int i = 0; i < kortit.length; i++) {
                
                this.add(kortit[i]);
            }
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
