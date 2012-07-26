package pokeri;

import raahauspeli.RaahausPeliPaneeli;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


/*
 *  +---------+-------------+
 *  |[menu]                 |
 *  +-----------------------+
 *  |      paaPaneeli       |
 *  |                       | }--.
 *  |                       |     `-   `wrapper` sitoo paaPaneeli
 *  |                       |      .´¨  ja ohjePaneelin yhteen
 *  +-----------------------+    .´
 *  |      ohjePaneeli      | }-´
 *  +-----------------------+
 */
public class MainMenu extends JFrame
{
    private JMenuBar menubar;
    private JPanel paaPaneeli;
    private JPanel ohjePaneeli;
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
    }

    public void piirraKortit()
    {
        Point sijainti = paaPaneeli.getLocation();

        sijainti.setLocation(100, 100);
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            poytakortit[i] = new Kortti(Extern.MAAT[i % 4], i + 1, sijainti);
            long vanhasijainti = sijainti.x + 120;
            sijainti.setLocation(vanhasijainti, 100);
            this.add(poytakortit[i]);
        }
        for (Kortti k : poytakortit) {
            k.paint(super.getGraphics());
        }
    }

    private void alustaMenu()
    {
        JMenu menu;
        JMenuItem mitem;

        menu = new JMenu("Tiedosto");

        mitem = new JMenuItem("Raahauspeli");
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem("Korttitesti");
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem("Lopeta");
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        menubar = new JMenuBar();
        menubar.add(menu);
    }

    private void alustaElementit()
    {
        super.setLayout(new GridLayout(2, 1, 0, 0));
        paaPaneeli = new JPanel();
        ohjePaneeli = new JPanel();
        raahausPeli = new RaahausPeliPaneeli();

        alustaMenu();
        // Kuuntelijat

        // Varsinaiset asettelut
        paaPaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                                    Extern.KORKEUS_IKKUNA / 3));
        paaPaneeli.setBackground(Color.lightGray);
        ohjePaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                                     Extern.KORKEUS_IKKUNA / 4));
        ohjePaneeli.setBackground(Color.white);

     /*   super.add(paaPaneeli);
        super.add(ohjePaneeli);

        super.setJMenuBar(menubar);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.pack();*/
        this.add(paaPaneeli);
        this.add(ohjePaneeli);

        this.setJMenuBar(menubar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    public class Tapahtumakuuntelija implements ActionListener, ItemListener,
                                                MouseListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().equals("Raahauspeli")) 
            {
                 System.out.println("Raahauspeli..");
                paaPaneeli.add(new RaahausPeliPaneeli());
                paaPaneeli.validate();
            }
               
            else if (ae.getActionCommand().equals("Korttitesti")) 
            {
                System.out.println("Korttitesti..");
                piirraKortit();
            } else if (ae.getActionCommand().equals("Lopeta")) {
                dispose();
            }
                
        }

        @Override
        public void itemStateChanged(ItemEvent ie)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseClicked(MouseEvent me)
        {
        }

        @Override
        public void mousePressed(MouseEvent me)
        {
            System.out.println(String.format("MouseEvent: %s", me.getSource()));
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
