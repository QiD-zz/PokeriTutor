package pokeri;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

/**
 *
 * @author weezel
 */
public final class Top5 extends JDialog
{
    private DefaultListModel tulokset;
    private JButton          sulje;
    private JList            hiscore;
    private static Top5      _instance;

    private Top5()
    {
        sulje    = new JButton("Sulje");
        tulokset = new DefaultListModel();
        hiscore  = new JList(tulokset);

        sulje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                dispose();
            }
        });
        for (int i = 0; i < 5; i++)
            tulokset.addElement(String.format("%d  --TYHJÄ--", i));
        hiscore.setPreferredSize(new Dimension(250, 120));

        setLayout(new BorderLayout());
        add(hiscore, BorderLayout.NORTH);
        add(sulje, BorderLayout.SOUTH);
        pack();
    }

    public static synchronized Top5 getTop5()
    {
        if (_instance == null)
            _instance = new Top5();
        return _instance;
    }

    public synchronized void luePisteetTiedostosta()
    {
        File f = new File(Extern.PISTEET_TIEDOSTO);

        tulokset.clear();
        if (!f.exists() && f.length() < 0) {
            tulokset.addElement("Ei pisteitä tai tiedosto uupuu");
            return;
        }
        // Voisihan tämän hieman elegantimminkin hoitaa...
        try {
            BufferedReader      bfr;
            SortedSet<String>   keys;
            Map<String, String> pistMap = new HashMap<String, String>();
            String              st = "";

            bfr = new BufferedReader(new FileReader(f));
            while ((st = bfr.readLine()) != null) {
                String[] splt = st.split("\t");
                pistMap.put(splt[1], splt[0]);
            }
            keys = new TreeSet<String>(new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    Integer a1 = Integer.parseInt(a);
                    Integer b1 = Integer.parseInt(b);
                    if (a1.equals(b1))
                        return 0;
                    return (a1 < b1) ? 1 : -1;
                }
            });
            keys.addAll(pistMap.keySet());

            int i = 0;
            for (String s : keys) {
                tulokset.addElement(String.format("%-19s %15s",
                                    pistMap.get(s), s));
                i++;
                if (i >= 5)
                    break;
            }
        } catch (IOException ioe) {
            tulokset.addElement("Ei pisteitä, tai");
            tulokset.addElement("pistetiedosto uupuu");
            return;
        }
    }

    public boolean isShown()
    {
        return isVisible();
    }

    public synchronized void nayta()
    {
        setVisible(true);
    }

    public synchronized void sulje()
    {
        dispose();
    }

}
