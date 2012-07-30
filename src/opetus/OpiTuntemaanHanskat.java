/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.io.EOFException;
import javax.swing.JButton;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.Kortti;

/**
 *
 * @author hurvittelu
 */
public class OpiTuntemaanHanskat extends JPanel {
    
    public OpiTuntemaanHanskat() {
        
        this.setPreferredSize(new Dimension(600, 400));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));   
        Kortti[] kortit = new Kortti[5];
        
        for (int i = 0; i < kortit.length; i++) {
            int y = 10;
            Point piste = new Point(10, y);
            kortit[i] = new Kortti(Extern.MAAT[0], 4+i, piste);
            this.add(kortit[i]);
           // kortit[i].paint(this.getGraphics());
        }
        for (int i = 0; i < kortit.length; i++) {
            JButton button = new JButton("Lukitse");
            button.setPreferredSize(new Dimension(120, 50));
            this.add(button);
            
        }
    }
    
}
