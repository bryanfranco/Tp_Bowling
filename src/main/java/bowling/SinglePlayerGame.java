package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

    private int scoreTotal = 0;
    private int tour = 1;
    private int tourLimite = 12;
    private int lastScore = 0;
    private boolean spare = false;
    private int strike = 0;
	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
	}

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            if(this.tour <= this.tourLimite){
                if(this.strike >= 6){
                    this.scoreTotal += nombreDeQuillesAbattues*2;
                    this.strike-=2;
                }
                else if(this.strike > 0){ //Si un strike a été fait lors des deux derniers lancers, on double le score de ce lancer
                    this.scoreTotal += nombreDeQuillesAbattues;
                    this.strike--;
                }
                if(nombreDeQuillesAbattues == 10){ //Le joueur fait un strike
                    this.strike += 2;
                    if(this.tour < 19){
                       this.scoreTotal+=nombreDeQuillesAbattues; 
                    }
                }
                else{
                    this.tourLimite++;
                    if(this.tour%2==1){ // S'il s'agit du 1er lancer de ce tour
                        this.lastScore = nombreDeQuillesAbattues;
                        this.scoreTotal += nombreDeQuillesAbattues;
                        if(this.spare){ //Si un spare a été fait le tour précédent, on double le score de ce lancer
                            this.scoreTotal += nombreDeQuillesAbattues;
                            this.spare=false;
                        }
                    }
                    else{ //S'il s'agit du second lancer de ce tour
                        if(this.lastScore + nombreDeQuillesAbattues == 10){ //Le joueur fait un spare
                            this.spare = true;
                        }
                        this.scoreTotal += nombreDeQuillesAbattues;
                        this.lastScore = 0;
                    }
                }
            this.tour++;
            }
	}

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
            return this.scoreTotal;
	}
}
