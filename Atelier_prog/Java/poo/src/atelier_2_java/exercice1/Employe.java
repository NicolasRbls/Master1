import java.util.*;

public class Employe extends Personne {
    private double salaire;
    private GregorianCalendar dateEmbauche;

    protected Employe(String leNom, String lePrenom, GregorianCalendar laDate, Adresse lAdresse, double salaire, GregorianCalendar dateEmbauche) {
        super(leNom, lePrenom, laDate, lAdresse);
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    public static Employe createEmploye(String leNom, String lePrenom, GregorianCalendar laDate, Adresse lAdresse, double salaire, GregorianCalendar dateEmbauche) {
        GregorianCalendar today = new GregorianCalendar();
        int age = today.get(Calendar.YEAR) - laDate.get(Calendar.YEAR);
        if (age >= 16 && age <= 65) {
            return new Employe(leNom, lePrenom, laDate, lAdresse, salaire, dateEmbauche);
        }
        return null;
    }

    public void augmenterLeSalaire(double pourcentage) {
        if (pourcentage > 0) {
            salaire += salaire * (pourcentage / 100.0);
        }
    }

    public int calculAnnuite() {
        GregorianCalendar today = new GregorianCalendar();
        int years = today.get(Calendar.YEAR) - dateEmbauche.get(Calendar.YEAR);
        return years;
    }

    public double getSalaire(){
        return this.salaire;
    }

    public String toString() {
        return "Employe {" +
               "\nNom: " + getNom() +
               "\nPrenom: " + getPrenom() +
               "\nDate de Naissance: " + getDateNaissance().get(Calendar.DAY_OF_MONTH) + "-" + (getDateNaissance().get(Calendar.MONTH) + 1) + "-" + getDateNaissance().get(Calendar.YEAR) +
               "\nAdresse: " + getAdresse().toString() + 
               "\nSalaire: " + salaire +
               "\nDate d'Embauche: " + dateEmbauche.get(Calendar.DAY_OF_MONTH) + "-" + (dateEmbauche.get(Calendar.MONTH) + 1) + "-" + dateEmbauche.get(Calendar.YEAR) +"}";
    }
}
