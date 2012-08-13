/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import pokeri.Extern;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
 public class ValintaPaneeli extends JPanel {
        
        public ValintaPaneeli(MonivalintaTaulu taulu, PokeriHanska eka, PokeriHanska toka, PokeriHanska kolmas) {
            
            this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/2, Extern.KORKEUS_IKKUNA/3));
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel paneeli = new JPanel();
            paneeli.setLayout(new GridLayout(3, 1));
            
            JRadioButton ekaNappi = new JRadioButton(eka.getHanskaName());           
            JRadioButton tokaNappi = new JRadioButton(toka.getHanskaName());
            JRadioButton kolmasNappi = new JRadioButton(kolmas.getHanskaName());
            
            ekaNappi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
            tokaNappi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
            kolmasNappi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
             
            ButtonGroup ryhma = new ButtonGroup();
            ryhma.add(ekaNappi);
            ryhma.add(tokaNappi);
            ryhma.add(kolmasNappi);
            
            JButton vastaa = new JButton("Vastaa");
            JButton uusi = new JButton("Uusi tehtävä");
            MonivalinnanKuuntelija kuuntelija = new MonivalinnanKuuntelija(taulu);
            uusi.addActionListener(kuuntelija);
            uusi.setActionCommand(Extern.UUSIMONIVALINTATEHTAVA);
            
            JPanel paneeli2 = new JPanel();
            paneeli2.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            paneeli.add(ekaNappi);
            paneeli.add(tokaNappi);
            paneeli.add(kolmasNappi);
            
            paneeli2.add(vastaa);
            paneeli2.add(uusi);

            this.add(paneeli);      
            this.add(paneeli2);
        }
    }
    