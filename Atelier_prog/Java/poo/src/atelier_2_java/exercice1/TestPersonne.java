
import java.util.GregorianCalendar;

public class TestPersonne {
    public static void main(String[] args) {
        
        Personne personne1 = new Personne("Nom1", "Prenom1", new GregorianCalendar(1990, 5, 15), new Adresse("75000", "Paris"));
        Personne personne2 = new Personne("Nom2", "Prenom2", new GregorianCalendar(1985, 3, 10), new Adresse("69000", "Lyon"));
        Personne personne3 = new Personne("Nom2", "Prenom2", new GregorianCalendar(1985, 3, 10), new Adresse("69000", "Lyon"));

        System.out.println(personne1);
        System.out.println(personne2);

        boolean estPlusAgee = Personne.plusAgee(personne1, personne2);

        if (estPlusAgee) {
            System.out.println(personne1.getNom() + " est plus âgée que " + personne2.getNom());
        } else {
            System.out.println(personne1.getNom() + " n'est pas plus âgée que " + personne2.getNom());
        }

        boolean estPlusAgee2 = personne1.plusAgeeQue(personne2);

        if (estPlusAgee2) {
            System.out.println(personne1.getNom() + " est plus âgée que " + personne2.getNom());
        } else {
            System.out.println(personne1.getNom() + " n'est pas plus âgée que " + personne2.getNom());
        }


        // Création de deux adresses avec les mêmes valeurs
        Adresse adresse1 = new Adresse(123, "Rue de la Ville", "75000", "Paris");
        Adresse adresse2 = new Adresse(123, "Rue de la Ville", "75000", "Paris");

        // Test d'égalité entre les deux adresses
        boolean sontEgales3 = adresse1.equals(adresse2);

        if (sontEgales3) {
            System.out.println("Les adresses sont égales.");
        } else {
            System.out.println("Les adresses ne sont pas égales.");
        }


        boolean sontEgales = personne1.equals(personne2);

        if (sontEgales) {
            System.out.println("Les personnes sont égales.");
        } else {
            System.out.println("Les personnes ne sont pas égales.");
        }


        boolean sontEgales2 = personne2.equals(personne3);

        if (sontEgales2) {
            System.out.println("Les personnes sont égales.");
        } else {
            System.out.println("Les personnes ne sont pas égales.");
        }

        // Création d'une adresse pour le test
        Adresse adresseTest = new Adresse(123, "Rue de la Paix", "75000", "Paris");

        // Création d'un employé
        Employe employeTest = Employe.createEmploye("Dupont", "Pierre", new GregorianCalendar(1990, 5, 15), adresseTest, 2000, new GregorianCalendar(2015, 0, 1));

        if (employeTest != null) {
            System.out.println(employeTest);
            // Augmentation du salaire
            employeTest.augmenterLeSalaire(10);

            // Calcul de l'ancienneté
            int annuite = employeTest.calculAnnuite();

            // Affichage des résultats
            System.out.println("Salaire après augmentation : " + employeTest.getSalaire());
            System.out.println("Ancienneté de l'employé : " + annuite + " années");
        } else {
            System.out.println("Erreur lors de la création de l'employé.");
        }

    }

}
