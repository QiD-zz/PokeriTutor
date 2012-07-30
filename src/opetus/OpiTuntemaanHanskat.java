/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.EOFException;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.Kortti;

/**
 *
 * @author hurvittelu
 */
public class OpiTuntemaanHanskat extends JPanel {
    
    public OpiTuntemaanHanskat() {
        
        this.setPreferredSize(new Dimension(550, 200));
                
        Kortti[] kortit = new Kortti[5];
        
        for (int i = 0; i < kortit.length; i++) {
            int x =10+i*100;
            int y = 10;
            Point piste = new Point(x, y);
            kortit[i] = new Kortti(Extern.MAAT[0], 4+i, piste);
            this.add(kortit[i]);
            kortit[i].paint(this.getGraphics());
        }
        
    }
    
}
