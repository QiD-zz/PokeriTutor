package pokeri;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.JPanel;

public class Tapahtumakuuntelija implements ActionListener, ItemListener,
                                                MouseListener
{
    private MainMenu main;
    private EtusivuPaneeli etusivu;

    public Tapahtumakuuntelija(MainMenu m)
    {
            main = m;
    }
 
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getActionCommand().equals(Extern.RAAHAUSPELI)) {
            main.setRaahauspeli();
        } else if (ae.getActionCommand().equals(Extern.KORTTIPELI)) {
            main.setPokeripeli();
        } else if (ae.getActionCommand().equals(Extern.OPETUS))  {
            main.setOpetuspaneeli();
        } else if (ae.getActionCommand().equals(Extern.ETUSIVU)) {
            main.setEtusivu();
        } else if (ae.getActionCommand().equals("Lopeta")) {
            main.dispose();
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
        JPanel paaPaneeli = (JPanel) main.getPaapaneeli();
        Component cmpntn = paaPaneeli.findComponentAt(me.getPoint());

        // Tarkastetaan onko klikattu komponentti Kortti
        if (cmpntn instanceof Kortti) {
            Kortti k = (Kortti) cmpntn;

            //System.out.println("Ennen: " + k);
            k.toggleValinta();
            //System.out.println("Jälk : " + k);
            //System.out.println("--");
        }
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        //System.out.println(String.format("MouseEvent: %s", me.getSource()));
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
