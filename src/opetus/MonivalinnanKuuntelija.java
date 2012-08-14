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
            taulu.setAktiivinenSkenaario(skenu);
            for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
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
            skenu = taulu.getAktiivinenSkenaario();
            System.out.println(vp.getVastaus());
            PokeriHanska vastaus = new PokeriHanska(vp.getVastaus());
            
            if (vastaus.equals(skenu.getOikeaVastaus())) {
                taulu.setOhjeTeksti(skenu.getOhjeTeksti());
            }
        }
    }
    
}
