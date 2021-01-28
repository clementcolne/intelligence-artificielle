import java.util.Scanner;

public class Game {

    private Plateau plateau;
    private final InputJoueur inputJoueur;
    private boolean tourJoueur;

    public Game(Plateau p, InputJoueur inputJoueur, int joueur){
        this.inputJoueur = inputJoueur;
        plateau = p;
        if (joueur == 0){
            tourJoueur = true;
        }else{
            tourJoueur = false;
        }
    }

    public void changeTourJoueur(){
        tourJoueur = !tourJoueur;
    }

    public InputJoueur getInputJoueur() {
        return inputJoueur;
    }

    public int jouerCoup() {
        return inputJoueur.demanderCoup();
    }

    public boolean isTourJoueur() {
        return tourJoueur;
    }

    public int jouerCoupIA() {
        return inputJoueur.IA();
    }

    /**
     * Vérifie si il y a une suite de 4 jeton de même couleur dans le plateau
     * @return vrai si il existe une suite de 4 jetons dans le plateau, faux sinon
     */
    public FinDePartie estVictoire() {
        int i, j, k, l, n = 0;
        for(i = 0 ; i < plateau.getColonnes() ; i++) {
            for(j = 0 ; j < plateau.getLignes() ; j++) {
                if (plateau.getCase(i, j) != ' ') {
                    n++;	// nb coups joués

                    // lignes
                    k = 0;
                    while(k < plateau.getColonnes() && i + k < plateau.getColonnes() && plateau.getCase(i+k, j) == plateau.getCase(i, j))
                        k++;
                    if(k == 4)
                        return plateau.getCase(i, j) == 'O'? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

                    // colonnes
                    k = 0;
                    while(k < plateau.getLignes() && j + k < plateau.getLignes() && plateau.getCase(i, j+k) == plateau.getCase(i, j))
                        k++;
                    if(k == 4)
                        return plateau.getCase(i, j) == 'O'? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

                    // diagonales
                    k = 0;
                    l = 0;
                    while(k < plateau.getColonnes() && l < plateau.getLignes() && i + k < plateau.getColonnes() && j + l < plateau.getLignes() && plateau.getCase(i+k, j+l) == plateau.getCase(i, j))
                        k++;
                    if(k == 4)
                        return plateau.getCase(i, j) == 'O'? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

                    k = 0;
                    l = 0;
                    while(k < plateau.getColonnes() && l < plateau.getLignes() && i + k < plateau.getColonnes() && j - l >= 0 && plateau.getCase(i+k, j-l) == plateau.getCase(i, j))
                        k++;
                    if(k == 4)
                        return plateau.getCase(i, j) == 'O'? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;
                }
            }
        }

        // et sinon tester le match nul
        if (n == plateau.getColonnes()* plateau.getLignes())
            return FinDePartie.MATCHNUL;

        return FinDePartie.NON;
    }
}