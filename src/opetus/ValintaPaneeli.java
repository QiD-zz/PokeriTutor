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
 public class ValintaPaneeli extends JPanel
 {
    private JRadioButton ekaNappi;
    private JRadioButton tokaNappi;
    private JRadioButton kolmasNappi;
    private ButtonGroup ryhma;
    private JButton vastaa;

    public ValintaPaneeli(MonivalintaTaulu taulu, PokeriHanska eka,
                          PokeriHanska toka, PokeriHanska kolmas)
    {
        setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/2, Extern.KORKEUS_IKKUNA/3));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new GridLayout(3, 1));
        this.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);

        ekaNappi = new JRadioButton(eka.getHanskaName());   
        ekaNappi.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);
        tokaNappi = new JRadioButton(toka.getHanskaName());
        tokaNappi.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);
        kolmasNappi = new JRadioButton(kolmas.getHanskaName());
        kolmasNappi.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);

        ekaNappi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        tokaNappi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        kolmasNappi.setFont(new Font(Font.SERIF, Font.BOLD, 25));

        System.out.println(ekaNappi.getText());
        System.out.println(tokaNappi.getText());
        System.out.println(kolmasNappi.getText());

        ryhma = new ButtonGroup();
        ryhma.add(ekaNappi);
        ryhma.add(tokaNappi);
        ryhma.add(kolmasNappi);

        vastaa = new JButton("Vastaa");
        JButton uusi = new JButton("Uusi tehtävä");
        MonivalinnanKuuntelija kuuntelija = new MonivalinnanKuuntelija(taulu);
        uusi.addActionListener(kuuntelija);
        uusi.setActionCommand(Extern.UUSIMONIVALINTATEHTAVA);
        
        vastaa.addActionListener(kuuntelija);
        vastaa.setActionCommand(Extern.MONIVALINTAVASTAUS);
            
        JPanel paneeli2 = new JPanel();
        paneeli2.setLayout(new FlowLayout(FlowLayout.CENTER));
            
        paneeli.add(ekaNappi);
        paneeli.add(tokaNappi);
        paneeli.add(kolmasNappi);

        paneeli2.add(vastaa);
        paneeli2.add(uusi);

        paneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA / 5, Extern.KORKEUS_IKKUNA / 9));
        paneeli2.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA / 5, Extern.KORKEUS_IKKUNA / 9));

        paneeli.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);
        paneeli2.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);
        this.add(paneeli);      
        this.add(paneeli2);
    }

    public void setVastaaDisabled()
    {
        vastaa.setEnabled(false);
    }
    
    public String getVastaus()
    {
        String vastaus = "";

        if (ekaNappi.isSelected()) {
            vastaus = ekaNappi.getText();         
        } else if (tokaNappi.isSelected()) {
            vastaus = tokaNappi.getText();
        } else if (kolmasNappi.isSelected())  {
            vastaus = kolmasNappi.getText();
        }

        return vastaus;
    }

}
    