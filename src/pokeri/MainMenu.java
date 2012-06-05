package pokeri;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class MainMenu extends JFrame implements ActionListener
{
    private JPanel vasenPaneeli;
    private JPanel paaPaneeli;
    private JPanel ohjePaneeli;
    private JPanel wrapper;
    private JButton raahauspeliNappi;
    private JButton testikorttibutton;
    private RaahausPeliPaneeli raahausPeli;
    private Kortti kortti;
    
    public MainMenu()
    {
        super("TaoTao menee metsään!");
        vasenPaneeli = new JPanel();
        paaPaneeli = new JPanel();
        ohjePaneeli = new JPanel();
        wrapper = new JPanel(new GridLayout(2, 1, 4, 4));
        raahauspeliNappi = new JButton("Raahauspeli");
        testikorttibutton = new JButton("testikorttibutton");
        raahausPeli = new RaahausPeliPaneeli();

        testikorttibutton.addActionListener(this);
        raahauspeliNappi.addActionListener(this);

        vasenPaneeli.setPreferredSize(new Dimension(450, 400));
        vasenPaneeli.setBackground(Color.red);
        paaPaneeli.setPreferredSize(new Dimension(40, 200));
        paaPaneeli.setBackground(Color.lightGray);
        ohjePaneeli.setPreferredSize(new Dimension(450, 300));
        ohjePaneeli.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 2, 4, 4));
        Point sijainti = paaPaneeli.getLocation();
        kortti = new Kortti("ruutu", 3, sijainti);

        vasenPaneeli.add(raahauspeliNappi);
        vasenPaneeli.add(testikorttibutton);
        //paaPaneeli.add(raahausPeli);
        paaPaneeli.add(kortti);
        wrapper.add(paaPaneeli);
        wrapper.add(ohjePaneeli);

        this.getContentPane().add(vasenPaneeli);
        this.getContentPane().add(wrapper);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == testikorttibutton) {
            System.out.println("yritetään..");
            kortti.repaint();
        } else if (ae.getSource() == raahauspeliNappi) {
            System.out.println("raahauspeliNapin toiminnallisuus uupuu, korjaa se hyvä rouva.");
        }
    }

}
