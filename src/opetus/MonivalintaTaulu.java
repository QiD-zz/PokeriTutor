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
    
    public MonivalintaTaulu(MainMenu m) {
        main = m;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA/2));
        KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(3));
        this.add(kasiTaulu);
        this.add(new ValintaPaneeli(this, new PokeriHanska(PokeriHanska.Arvo.KAKSIPARIA),
                new PokeriHanska(PokeriHanska.Arvo.HAI), new PokeriHanska(PokeriHanska.Arvo.VARISUORA)));
    }

    
    
   
}
