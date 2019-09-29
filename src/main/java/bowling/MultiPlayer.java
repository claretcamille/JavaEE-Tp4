
package bowling;

import java.util.Stack; // importation de la classe pile en java

/**
 * Implémentation de la classe MultiPlayerGame
 * Ici je pense a une pile pour géré 
 * @author camilleclaret
 */
public class MultiPlayer implements MultiPlayerGame {
    
    Stack<SinglePlayerGame> jeu;// Pile qui actualise la partie
    Stack<String> joueur;// Pile qui permet de suivre le joeur qui joue
    // Varriables permettant de suivre ou on en ait dans le jeu
    SinglePlayerGame actuJeu;
    String actuJoueur;
    int mancheJ [] ;
    int numJ;
    
    
    public MultiPlayer(){
        this. jeu = new Stack<>(); 
        this.joueur = new Stack<>(); 
        this.numJ = 0;
    }
    
    @Override
    public String startNewGame(String[] playerName) throws Exception {
        // si la liste de joueur est vide ou qu'elle n'existe pas = pas de partie possible.
        if(playerName.length == 0 || playerName == null){
            System.out.print("pb");
            throw new Exception("Aucun joueur, il faut des joueurs pour pouvoir commencé une partie.");
        } else {
            // Démarage de la nouvelle partie :  Innitialisation des pile
            this. jeu.clear();
            this.joueur .clear(); 
            this.mancheJ = new int[ playerName.length];
            // On part de la fin de la liste pour que l'ajout de la pile se passe dans le bon ordre
             for(int i = playerName.length-1  ; i > -1 ;i--){
                this. jeu.add(new SinglePlayerGame());
                 this.joueur.add(playerName[i]);
                 this.mancheJ[i] = 1;
             }
        }
        // Initialisation des joueurActuelle -> On enleve de la pile celui qui doit commencer
        this.actuJeu = this.jeu.pop();
        this.actuJoueur = this.joueur.pop();
        return "Départ de la partie";
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        // Cas ou le jeux est fini:
       if(this.jeu.isEmpty()){
           throw new Exception("Le jeu est fini.");
       } else {
           // Lancer des quilles
           actuJeu.lancer(nombreDeQuillesAbattues);
           this.mancheJ[this.numJ] = this.actuJeu.getCurrentFrame().frameNumber;
           // Regarde si la manche est fini pour le joueur
           if(this.mancheJ[this.numJ] != this.mancheJ[this.mancheJ.length-1]){ 
               
               // On remet la manche fini dans la fille pour acceder a la suivante
               this.jeu.push(actuJeu);
               this.joueur.push(actuJoueur);
               
               // On change le joueur
               this.actuJeu = this.jeu.firstElement();
               this.actuJoueur =  this.joueur.firstElement();
               this.jeu.pop();
               this.joueur.pop();
               this.numJ = (this.numJ+1)%this.mancheJ.length;
           }
       }
        return this.actuJoueur;
   }

    @Override
    public int scoreFor(String playerName) throws Exception {
        
        // Joueur qui n'est pas dans la partie :
        if(this.joueur.contains(playerName) || this.actuJoueur == playerName){
            int index = this.joueur.indexOf(playerName);
            return this.jeu.elementAt(index).score();
        } else {
            // Joueur qui n'est pas dans la partie :
            throw new Exception("Ce joueur n'est pas de la partie");
        }
        
    }
    
    public String donneJoueur(){
        return this.actuJoueur;
    }
 
}
