/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import pokeri.Extern;
import pokeri.Kortti;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class MonivalinnanKuuntelija implements ActionListener{
    
    private MonivalintaTaulu taulu;
    private Skenaario skenu;
    
    public MonivalinnanKuuntelija(MonivalintaTaulu mvt){
        taulu = mvt;
    }


   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Extern.UUSIMONIVALINTATEHTAVA)) {
            taulu.removeAll();
            KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(6));
            Random rnd = new Random();
            skenu = new Skenaario(rnd.nextInt(Extern.SKENAARIOIDEN_LKM));
            taulu.setSkenaario(skenu);
            Kortti[] kortit = new Kortti[5];
            
            for (int i = 0; i < kortit.length; i++) {
               // kortit[i] = ;
                kasiTaulu.setKorttiTaulussa(skenu.getKortti(i), i);
            }
            taulu.add(kasiTaulu);
            ValintaPaneeli vp = new ValintaPaneeli(taulu, skenu.getEkaHanska(),
                skenu.getTokaHanska(), skenu.getKolmasHanska());
            taulu.setValintaPaneeli(vp);
            taulu.add(vp);
          /*
           * taulu.add(new ValintaPaneeli(taulu, new PokeriHanska(PokeriHanska.Arvo.KOLMOSET),
                new PokeriHanska(PokeriHanska.Arvo.SUORA), new PokeriHanska(PokeriHanska.Arvo.VARISUORA)));*/
            taulu.validate();
        } else if (e.getActionCommand().equals(Extern.MONIVALINTAVASTAUS)) {
            ValintaPaneeli vp = taulu.getValintaPaneeli();
            
            String vastaus = vp.getVastaus();
            if (skenu == null) {
                
                skenu = taulu.getSkenaario();
            }
            String oikea = skenu.getOikeaVastaus().getHanskaName();
            if (vastaus.equals(oikea)) {
                System.out.println("OIKEIN ON");
                taulu.setOhjeTeksti("Oikea vastaus");
            }
        }
    }
    
}
