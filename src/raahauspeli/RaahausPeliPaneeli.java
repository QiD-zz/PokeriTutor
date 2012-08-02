package raahauspeli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author QiD
 */
public class RaahausPeliPaneeli extends JPanel implements ActionListener
{
    private JPanel vasen;
    private JPanel oikea;
    private JButton uusiPeli;
    private static JLabel tulos;
    private Timer kello;
    private static Date startTime;
    private static Date endTime;
    private JTextArea peliSuunta;
    
    private static DefaultListModel parhaatTulokset;
    
    private static DefaultListModel listamalli;
    private JEditorPane selitys;
    private static JList lista;
    
    private static JList HoF;
    
    private static final int KORTTEJAPELISSA = 6;
    
     private final String[] pelimuodotLyhyesti = {"Parhaimmasta huonoimpaan.",
         "Huonoimmasta parhaimpaan."};
    
    private final String[] pelimuodot = {"Järjestä lista parhaimmasta kädestä huonoimpaan. \n"
            + "Ajanlasku alkaa kun painat OK.", "Järjestä lista huonoimmasta kädestä parhaimpaan. \n"
            + "Ajanlasku alkaa kun painat OK."};
    
    private static boolean parhaimmastaHuonoimpaan;
    
    public RaahausPeliPaneeli()
    {

        parhaatTulokset = new DefaultListModel();
        HoF = new JList(parhaatTulokset);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(Color.lightGray);
        KasiTransferHandler handleri = new KasiTransferHandler();
        vasen = new JPanel();
        vasen.setBackground(Color.lightGray);
        oikea = new JPanel();
        oikea.setBackground(Color.lightGray);
        oikea.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        uusiPeli = new JButton("Uusi peli");
        uusiPeli.addActionListener(this);
        tulos = new JLabel();
        tulos.setBorder(BorderFactory.createTitledBorder("Tulos"));
        tulos.setPreferredSize(new Dimension(150, 50));
        kello = new Timer(1000, this);       
        
        peliSuunta = new JTextArea("lol");
        peliSuunta.setWrapStyleWord(true);
        peliSuunta.setLineWrap(true);
     //   peliSuunta.setBackground(this.getBackground());
        peliSuunta.setPreferredSize(new Dimension(125, 70));
        peliSuunta.setFont(new Font(this.getFont().getName(), Font.PLAIN, 17));
        peliSuunta.setBorder(BorderFactory.createTitledBorder("Pelin suunta"));
        oikea.add(peliSuunta);
        oikea.add(uusiPeli);
        oikea.add(tulos);
        oikea.setPreferredSize(new Dimension(150, 250));
        JPanel oikeanPohja = new JPanel(new FlowLayout(FlowLayout.LEADING));
        oikeanPohja.setBackground(Color.lightGray);
        oikeanPohja.add(oikea);
        
        listamalli = new DefaultListModel();
        lista = new JList(listamalli);
        lista.setBackground(Color.black);
        lista.setEnabled(false);
        for (int i = 0; i < KORTTEJAPELISSA; i++) {
                Random rnd = new Random();
                listamalli.addElement(new PokeriHanska(rnd.nextInt(9)));
            }
        lista.setCellRenderer(new DefaultListCellRenderer(){

       public int getHorizontalAlignment() {
                return CENTER;
       }
});
        
        lista.setDragEnabled(true);
        lista.setTransferHandler(handleri);
        lista.setPreferredSize(new Dimension(200, 400));
        lista.setFixedCellHeight(50);
        lista.setFont(new Font("Arial",Font.BOLD, 20));
     //   lista.setLocation(0, 0);
        selitys = new JEditorPane();
        selitys.setPreferredSize(new Dimension(250, 200));
        selitys.setText("Tehtävänäsi on raahat kädet oikeaan \n"
                + "järjestykseen mahdollisimman nopeasti. \n"
                + "Järjestämissuunta vaihtuu ja se ilmoitetaan aina uudestaan "
                + "uuden pelin alkaessa.");
        selitys.setBackground(this.getBackground());
        vasen.add(selitys);
        vasen.add(lista);
     //  selitys.setLocation(200, 200);
        this.add(vasen);
        this.add(oikeanPohja);
        
        JScrollPane jscroll = new JScrollPane(HoF);
        jscroll.setPreferredSize(new Dimension(125, 350));
        jscroll.setBorder(BorderFactory.createTitledBorder("Parhaat tulokset"));
        this.add(jscroll);
        
        this.setPreferredSize(new Dimension(800, 400));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(uusiPeli)) {
            listamalli.removeAllElements();
            Random rnd = new Random();
            for (int i = 0; i < KORTTEJAPELISSA; i++) {
                
                listamalli.addElement(new PokeriHanska(rnd.nextInt(9)));
            }
            parhaimmastaHuonoimpaan = rnd.nextBoolean();
            
            if (parhaimmastaHuonoimpaan) {
                JOptionPane.showMessageDialog(this, pelimuodot[0]);
                peliSuunta.setText(pelimuodotLyhyesti[0]);
                
            } else {
                JOptionPane.showMessageDialog(this, pelimuodot[1]);
                peliSuunta.setText(pelimuodotLyhyesti[1]);
                
            }
            lista.setEnabled(true);
            lista.setForeground(Color.ORANGE);
            startTime = Calendar.getInstance().getTime();
          //  kello.start();
        }
        if (e.getSource().equals(kello)) {
            endTime = Calendar.getInstance().getTime();
           // kello.stop();
            long delay = endTime.getTime()-startTime.getTime();
            tulos.setText(String.valueOf(delay));
       //     System.out.print(delay);
         //   tulos.validate();
            
        }
    }
    
    public static Object[] haeHanskat() {
        
        Object[] list = listamalli.toArray();
        
        return list;
    }
    
    public static boolean testaaVoitto() 
    {
        boolean voitto = true;
        PokeriHanska hanskat[] = new PokeriHanska[KORTTEJAPELISSA];
        int vertailu[] = new int[KORTTEJAPELISSA];

        for (int i = 0; i < hanskat.length; i++) {
            hanskat[i] = (PokeriHanska) listamalli.get(i);
            
        }
        for (int i = 0; i < hanskat.length-1; i++) {
            vertailu[i] = hanskat[i].arvo.compareTo(hanskat[i+1].arvo);
            if (parhaimmastaHuonoimpaan) {
                if (vertailu[i] > 0) {
                    voitto = false;
                }
            } else {
                if (vertailu[i] < 0) {
                    voitto = false;
                }
            }
            
        }
        
        if (voitto) {
           endTime = Calendar.getInstance().getTime();          
           long delay = endTime.getTime()-startTime.getTime();
           String tulosteksti = String.valueOf((double)delay/1000)+" sekuntia";
           tulos.setText(String.valueOf(tulosteksti));
           parhaatTulokset.addElement(tulosteksti);
           Object[] temp = parhaatTulokset.toArray();
           Arrays.sort(temp);
           
           parhaatTulokset.clear();
            for (int i = 0; i < temp.length; i++) {
                parhaatTulokset.addElement(temp[i]);
                
            }
           lista.setEnabled(false);
           return true;  
        } else {
            return false;
        }
       
    }
}
