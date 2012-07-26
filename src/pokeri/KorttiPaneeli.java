package pokeri;

import java.awt.Point;
import javax.swing.JPanel;


public class KorttiPaneeli extends JPanel
{
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli(JPanel panel)
    {
        Point sijainti = panel.getLocation();

        Kortti kk = new Kortti(Extern.MAAT[0], 1, new Point(50, 50));
        sijainti.setLocation(50, 50); // XXX kun toimii, poista tämä
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            poytakortit[i] = new Kortti(Extern.MAAT[i % 4], i + 1, sijainti);
            sijainti.setLocation(sijainti.x + 120, 50);
            this.add(poytakortit[i]);
        }
        this.add(kk);
        this.setPreferredSize(panel.getPreferredSize());
        this.setVisible(true);
    }
}
