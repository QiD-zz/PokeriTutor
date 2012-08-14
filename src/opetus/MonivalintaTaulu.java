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
    private Skenaario skenu;
    
    public MonivalintaTaulu(MainMenu m) {
        main = m;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA/2));
        skenu = new Skenaario(0);
        KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(3));
        
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
                kasiTaulu.setKorttiTaulussa(skenu.getKortti(i), i);
            }
        
        vp = new ValintaPaneeli(this, skenu.getEkaHanska(),
                skenu.getTokaHanska(), skenu.getKolmasHanska());
        this.add(kasiTaulu);
        this.add(vp);
    }

    public Skenaario getAktiivinenSkenaario() {
        return skenu;
    }
    
    public void setAktiivinenSkenaario(Skenaario uusi) {
        skenu = uusi;
    }
    
    public ValintaPaneeli getValintaPaneeli() {
        return vp;
    }
    
    public void setValintaPaneeli(ValintaPaneeli uusi) {
        vp = uusi;
    }
    
    public void setSkenaario(Skenaario uusi) {
        skenu = uusi;
    }
    
    public Skenaario getSkenaario() {
        return skenu;
    }
    
    public void setOhjeTeksti(String teksti) {
        main.setOhjeTekstiAlue(teksti);
        main.repaint();
    }
   
}
