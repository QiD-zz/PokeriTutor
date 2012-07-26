/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raahauspeli;

/**
 *
 * @author hurvittelu
 */
public class PeliMekaniikka {
    
    private static Object[] hanskat;
    
    public static boolean testaaVoitto() 
    {
        hanskat = RaahausPeliPaneeli.haeHanskat();
        return true;
    }
    
}
