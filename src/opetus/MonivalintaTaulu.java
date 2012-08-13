/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import pokeri.Extern;
import pokeri.MainMenu;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class MonivalintaTaulu extends JPanel {
    
    MainMenu main;
    private ValintaPaneeli vp;
    
    public MonivalintaTaulu(MainMenu m) {
        main = m;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA/2));
        KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(3));
        vp = new ValintaPaneeli(this, new PokeriHanska(PokeriHanska.Arvo.KAKSIPARIA),
                new PokeriHanska(PokeriHanska.Arvo.HAI), new PokeriHanska(PokeriHanska.Arvo.VARISUORA));
        this.add(kasiTaulu);
        this.add(vp);
    }

    
    public ValintaPaneeli getValintaPaneeli() {
        return vp;
    }
    
    public void setValintaPaneeli(ValintaPaneeli uusi) {
        vp = uusi;
    }
    
    public void setOhjeTeksti(String teksti) {
        main.setOhjeTekstiAlue(teksti);
    }
   
}
