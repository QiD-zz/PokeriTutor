/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pokeri.Extern;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class MonivalinnanKuuntelija implements ActionListener{
    
    private MonivalintaTaulu taulu;
    
    public MonivalinnanKuuntelija(MonivalintaTaulu mvt){
        taulu = mvt;
    }


   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Extern.UUSIMONIVALINTATEHTAVA)) {
            taulu.removeAll();
            KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(6));
            taulu.add(kasiTaulu);
            taulu.add(new ValintaPaneeli(taulu, new PokeriHanska(PokeriHanska.Arvo.KOLMOSET),
                new PokeriHanska(PokeriHanska.Arvo.SUORA), new PokeriHanska(PokeriHanska.Arvo.VARISUORA)));
            taulu.validate();
        }
    }
    
}
