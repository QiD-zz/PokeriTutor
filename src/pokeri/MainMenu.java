/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author QiD
 */
public class MainMenu extends JFrame{
    
    private JPanel vasenPaneeli;
    private JButton raahauspeliNappi;
    private JButton testikorttibutton;
    private RaahausPeliPaneeli raahausPeli;
    
    public MainMenu() {
        super("TaoTao menee metsään!");
        vasenPaneeli = new JPanel();
        vasenPaneeli.setPreferredSize(new Dimension(200, 600));
        vasenPaneeli.setBackground(Color.red);
        raahauspeliNappi = new JButton("Raahauspeli");
        testikorttibutton = new JButton("testikorttibutton");
        raahausPeli = new RaahausPeliPaneeli();        
        vasenPaneeli.add(raahauspeliNappi);
        vasenPaneeli.add(testikorttibutton);
        this.setLayout(new FlowLayout());
        this.getContentPane().add(vasenPaneeli);
        this.getContentPane().add(raahausPeli);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        
    }
    
}
