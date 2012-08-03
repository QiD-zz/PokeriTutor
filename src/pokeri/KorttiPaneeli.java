package pokeri;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class KorttiPaneeli extends JPanel
{
    private JButton pelaaKasi;
    private JButton uusipeli;
    private JPanel  kortitPane;
    private JPanel  toiminnotPane;
    private JTextArea tilastot;
    private KorttipaneelinKuuntelija kuuntelija;
    private Pakka    pakka;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli(JTextArea til)
    {
        kortitPane = new JPanel();
        toiminnotPane = new JPanel();
        tilastot = til;

        // Layout määrittelyt
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kortitPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        toiminnotPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        pakka = Pakka.getPakka();
        alustaKortit();

        // Toiminnot
        pelaaKasi = new JButton("Pelaa käsi");
        uusipeli = new JButton("Uusi peli");
        kuuntelija = new KorttipaneelinKuuntelija();
        pelaaKasi.addActionListener(kuuntelija);
        uusipeli.addActionListener(kuuntelija);
        toiminnotPane.add(pelaaKasi);
        toiminnotPane.add(uusipeli);

        add(kortitPane);
        add(toiminnotPane);
        kortitPane.setSize(getMaximumSize());
        toiminnotPane.setSize(getMaximumSize());

        setVisible(true);
    }

    public void alustaKortit()
    {
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            Kortti k = pakka.otaKortti();

            k.setLocation(0, 25);
            poytakortit[i] = k;
            kortitPane.add(poytakortit[i]);
        }
        revalidate();
    }

    public void merkkaaValituksi(Kortti k)
    {
        if (k.getValinta() == false) {
            k.toggleValinta();
            k.repaint();
        }
    }

    public class KorttipaneelinKuuntelija implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().equals("Uusi peli")) {
                kortitPane.removeAll();
                poytakortit = null;
                poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
                pakka = Pakka.uusiPakka();
                pakka.sekoita();
                alustaKortit();
            } else if (ae.getActionCommand().equals("Pelaa käsi")) {
                tilastot.setText(String.format("Pakassa: %d\nNostettu: %d",
                                 pakka.jaljella(), pakka.nostettu()));
            }
        }
    }

}
