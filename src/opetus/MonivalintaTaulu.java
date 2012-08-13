/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import javax.swing.JPanel;
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
        
        KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(3));
        this.add(kasiTaulu);
    }
    
}
