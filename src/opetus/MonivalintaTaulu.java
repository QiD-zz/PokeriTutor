package opetus;

import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.MainMenu;
import raahauspeli.PokeriHanska;

/**
 *
 * @author Kuisma Kuusniemi 81197
 */
public class MonivalintaTaulu extends JPanel
{
    MainMenu main;
    private ValintaPaneeli vp;
    private Skenaario skenu;
    private int edellisenSkenaarionNro;

    public MonivalintaTaulu(MainMenu m)
    {
        main = m;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA/2));
        this.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);
        Random rnd = new Random();
        edellisenSkenaarionNro = rnd.nextInt(Extern.SKENAARIOIDEN_LKM);
        skenu = new Skenaario(edellisenSkenaarionNro);
        KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(3));
        kasiTaulu.setBackground(Extern.SKENAARIOPELINTAUSTAVARI);

        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            kasiTaulu.setKorttiTaulussa(skenu.getKortti(i), i);
        }

        kasiTaulu.setValintaPoisPaalta();
        vp = new ValintaPaneeli(this, skenu.getEkaHanska(),
                skenu.getTokaHanska(), skenu.getKolmasHanska());

        this.add(kasiTaulu);
        this.add(vp);
        main.setOhjeTekstiAlue("Tässä pelissä on tarkoitus valita kolmesta vaihtoehtoisesta "
                + "vedosta kaikkein paras erilaisissa tilanteissa. Jokaisessa tehtävässä on vain "
                + "yksi mahdollisuus vastata oikein. Kokeile onnistutko saamaan kaikki oikein. :)");
    }

    public int getEdellinenSkenaarionNro()
    {
        return edellisenSkenaarionNro;
    }

    public void setEdellinenSkenaarionNro(int nro)
    {
        edellisenSkenaarionNro = nro;
    }

    public Skenaario getAktiivinenSkenaario()
    {
        return skenu;
    }

    public void setAktiivinenSkenaario(Skenaario uusi)
    {
        skenu = uusi;
    }

    public ValintaPaneeli getValintaPaneeli()
    {
        return vp;
    }

    public void setValintaPaneeli(ValintaPaneeli uusi)
    {
        vp = uusi;
    }

    public void setSkenaario(Skenaario uusi)
    {
        skenu = uusi;
    }

    public Skenaario getSkenaario()
    {
        return skenu;
    }

    public void setOhjeTeksti(String teksti)
    {
        main.setOhjeTekstiAlue(teksti);
        main.repaint();
    }

}
