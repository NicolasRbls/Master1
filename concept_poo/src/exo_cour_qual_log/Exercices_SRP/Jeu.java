package exo_cour_qual_log.Exercices_SRP;
import java.util.ArrayList;

class Jeu
{
	private ArrayList<String> lesJoueurs;
	private  int valeur;     

	public Jeu() {
		lesJoueurs=new ArrayList<String>();	
		this.valeur=0;
	}
	public void ajouterJoueur(String nom) {
		lesJoueurs.add(nom);
	}
	public int lancerDe() {
    	this.valeur = (int) (6*Math.random()) + 1;
    	return this.valeur;
    }
	public int jouerUnTour() {
		int total=0;
		for( int i=0;i<2; i++) {
			total+=lancerDe();
		}
		return total;
	}
	
	public void bataille() {
		int nbGagnants=0,score,max=0;
		String nomsGagnants="";
		String res;
		for (String nom:lesJoueurs) {
			score=jouerUnTour();
			if (score>max) {
				max=score;
				nbGagnants=1;
				nomsGagnants=nom;
			}
			else if (score==max) {
				nbGagnants+=1;
				nomsGagnants+="-"+nom;
			}
			System.out.println( nom+ " : "+score);
		}
		if (nbGagnants==1)
			res="Le gagnant est ";
		else
			res= "Il y a "+nbGagnants+ " exaequo :";
		res+=nomsGagnants;
		System.out.println(res);
	}
    
}
