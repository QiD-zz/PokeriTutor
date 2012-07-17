package pokeri;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author QiD
 */

/*
 *  +---------+-------------+
 *  |         | paaPaneeli  |
 *  |         |             | }--.
 *  | vasen-  |             |     `-   `wrapper` sitoo paaPaneeli
 *  | Paneeli |             |      .´¨  ja ohjePaneelin yhteen
 *  | (menu)  |-------------+    .´
 *  |         | ohjePaneeli | }-´
 *  +---------+-------------+
 */
public class MainMenu extends JFrame
{
    private JPanel vasenPaneeli;
    private JPanel paaPaneeli;
    private JPanel ohjePaneeli;
    private JPanel wrapper;
    private JButton raahauspeliNappi;
    private JButton testikorttibutton;
    private RaahausPeliPaneeli raahausPeli;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
    private Tapahtumakuuntelija tkuuntelija;
    private final String leiska[] = { BorderLayout.EAST, BorderLayout.WEST,
                                      BorderLayout.SOUTH, BorderLayout.NORTH,
                                      BorderLayout.CENTER };

    public MainMenu()
    {
        super("TaoTao menee metsään!");
        tkuuntelija = new Tapahtumakuuntelija();

        alustaElementit();

        /*** KORTTIEN NÄKYVYYDEN TESTAUSTA **/
        Point sijainti = paaPaneeli.getLocation();
        sijainti.setLocation(500, 100);
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            poytakortit[i] = new Kortti(Extern.MAAT[i % 4], i + 1, sijainti);
            long vanhasijainti = sijainti.x + 60;
            sijainti.setLocation(vanhasijainti, 100);
            paaPaneeli.add(poytakortit[i]);
        }
        /*** KORTTIEN NÄKYVYYDEN TESTAUSTA **/
    }


    public void alustaElementit()
    {
        this.setLayout(new GridLayout(1, 2, 4, 4));
        vasenPaneeli = new JPanel();
        paaPaneeli = new JPanel();
        ohjePaneeli = new JPanel();
        wrapper = new JPanel(new GridLayout(2, 1));
        raahauspeliNappi = new JButton("Raahauspeli");
        testikorttibutton = new JButton("testikorttibutton");
        raahausPeli = new RaahausPeliPaneeli();

        // Kuuntelijat
        testikorttibutton.addMouseListener(tkuuntelija);
        raahauspeliNappi.addMouseListener(tkuuntelija);

        // Varsinaiset asettelut
        vasenPaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA));
        vasenPaneeli.setBackground(Color.darkGray);
        vasenPaneeli.add(raahauspeliNappi);
        vasenPaneeli.add(testikorttibutton);
        paaPaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA / 3));
        paaPaneeli.setBackground(Color.lightGray);
        ohjePaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA / 4));
        ohjePaneeli.setBackground(Color.white);

        wrapper.add(paaPaneeli);
        wrapper.add(ohjePaneeli);

        this.getContentPane().add(vasenPaneeli);
        this.getContentPane().add(wrapper);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }


    @Override
    public void paint(Graphics g)
    {
        for (Kortti k : poytakortit)
            k.paint(g);
    }


    public class Tapahtumakuuntelija implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent me)
        {
        }

        @Override
        public void mousePressed(MouseEvent me)
        {
            if (me.getSource() == testikorttibutton) {
                System.out.println("yritetään..");
                repaint();
            } else if (me.getSource() == raahauspeliNappi) {
                System.out.println("raahauspeliNapin toiminnallisuus uupuu, korjaa se hyvä rouva.");
                //paaPaneeli.add(raahausPeli);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me)
        {
        }

        @Override
        public void mouseEntered(MouseEvent me)
        {
        }

        @Override
        public void mouseExited(MouseEvent me)
        {
        }
    }

}
