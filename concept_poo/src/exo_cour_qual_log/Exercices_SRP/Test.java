package exo_cour_qual_log.Exercices_SRP;

public class Test {

	public static void main(String[] args) {
		Jeu j=new Jeu();
		j.ajouterJoueur("Pierre");
		j.ajouterJoueur("Marie");
		j.ajouterJoueur("Jean");
		j.bataille();
	}

}
