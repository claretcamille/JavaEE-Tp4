
package bowling;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camilleclaret
 */
public class MultiPlayerTest {
    
    String joueur [] = {"Nicolas","Manon"};
    MultiPlayer jeu ;
    
    @Before
    public void setUp() throws Exception {
        jeu = new MultiPlayer();
        jeu.startNewGame(joueur);
     }
    
    @Test 
    public  void LancerPartie() {
       assertEquals(jeu.joueur.size(), jeu.jeu.size());
    }
    
    @Test 
    public void creationExceptionNewJeu(){
        try{
            jeu.startNewGame(null);
            String vide[] = new String[0];
            jeu.startNewGame(vide);
         }catch (Exception ex) {
            Logger.getLogger(MultiPlayerTest.class.getName()).log(Level.SEVERE, null, ex);
            assertEquals(jeu.joueur.size(), jeu.jeu.size());
        }
    }
    
    @Test
    public void changementJoueur() throws Exception{
     
     assertEquals(joueur[0],jeu.donneJoueur());
     jeu.lancer(3);
     jeu.lancer(4);
     assertEquals(joueur[1],jeu.donneJoueur());
     }
 
    
    @Test
    public void Score() throws Exception{
        
        assertEquals(jeu.scoreFor(joueur[0]),0);
        assertEquals(jeu.scoreFor(joueur[1]),0);
        jeu.lancer(3);
        assertEquals(jeu.scoreFor(joueur[0]),3);

    }
    
    @Test
    public void ScoreJoueurNonExist() throws Exception{
        try{
            jeu.scoreFor("Camille");
        } catch (Exception ex) {
            Logger.getLogger(MultiPlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
    
   