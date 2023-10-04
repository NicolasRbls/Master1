import java.util.*;

public class Personne{
    private static final Adresse ADRESSE_INCONNUE = null;
    private String nom;
    private String prenom;
    private final GregorianCalendar dateNaissance;
    private Adresse adresse=ADRESSE_INCONNUE;
	// Compteur statique pour le nombre d'instances de Personne
    private static int nombreInstances = 0;
	
	/**
	 * Constructeur de Personne
	 * @param leNom le nom de la personne
	 * @param lePrenom le pr�nom de la personne
	 * @param laDate la date de naissance de la personne
	 * @param lAdresse l'adresse de la personne
	 */
	public Personne(String leNom,String lePrenom, GregorianCalendar laDate, Adresse lAdresse){
		nom = leNom.toUpperCase();
		prenom=lePrenom;
		dateNaissance=laDate;
		adresse=lAdresse;
		// Incrémentation du compteur d'instances à chaque nouvelle création
        nombreInstances++;
	}
	
	/** 
	 * Constructeur de Personne
	 * @param leNom le nom de la personne
	 * @param lePrenom le pr�nom de la personne
	 * @param j le jour de naissance
	 * @param m le mois de naissance
	 * @param a l'ann�e de naissance
	 * @param numero le n� de la rue
	 * @param rue la rue
	 * @param code_postal le code postal de l'adresse
	 * @param ville la ville ou la personne habite
	 */
	public Personne(String leNom,String lePrenom, int j, int m, int a, int numero, String rue, String code_postal, String ville){
		this(leNom, lePrenom, new GregorianCalendar(a,m,j),new Adresse(numero,rue,code_postal,ville));
	}

	/**
	 * Accesseur
	 * @return retourne le nom
	 */
	public String getNom(){
		return nom;
	}
	/**
	 * Accesseur
	 * @return retourne le pr�nom
	 */
	public String getPrenom(){
		return prenom;
	}
	/**
	 * Accesseur
	 * @return retourne la date de naissance	 
	 */
	public GregorianCalendar getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * Accesseur
	 * @return retourne l'adresse	 
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * Modificateur
	 * @param retourne l'adresse	 
	 */
	public void setAdresse(Adresse a) {
		adresse=a;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String result="\nNom : "+nom+"\n"
		+"Pr�nom : "+prenom+"\n"+
		"N�(e) le : "+dateNaissance.get(Calendar.DAY_OF_MONTH)+
		"-"+dateNaissance.get(Calendar.MONTH)+
		"-"+dateNaissance.get(Calendar.YEAR)+"\n"+
		"Adresse : "+
		adresse.toString();
		return result;
	}

	/**
     * Méthode statique pour obtenir le nombre d'instances de Personne créées.
     * @return le nombre d'instances créées jusqu'à présent.
     */
    public static int getNombreInstances() {
        return nombreInstances;
    }

	public static boolean plusAgee(Personne pers1 , Personne pers2) {
        // Comparez les dates de naissance pour déterminer l'âge
        return pers1.getDateNaissance().compareTo(pers2.getDateNaissance()) < 0;
    }

	/**
     * Méthode pour comparer l'âge de deux personnes.
     * @param pers1
	  la personne à comparer
     * @return vrai si la première personne est plus âgée que l'autre, sinon faux.
     */
    public boolean plusAgeeQue(Personne personne) {
        // Comparez les dates de naissance pour déterminer l'âge
        return this.dateNaissance.compareTo(personne.getDateNaissance()) < 0;
    }

	/**
     * Redéfinition de la méthode equals pour comparer les personnes par nom, prénom et date de naissance.
     * @param autrePersonne l'autre personne à comparer
     * @return vrai si les personnes sont égales (même nom, même prénom et même date de naissance), sinon faux.
     */
    public boolean equals(Object autrePersonne) {
        if (this == autrePersonne) {
            return true;
        }
        if (autrePersonne == null || getClass() != autrePersonne.getClass()) {
            return false;
        }

        Personne personne = (Personne) autrePersonne;

        // Comparaison du nom, prénom et date de naissance et adresse 
        return (nom.equals(personne.nom)) &&
        (prenom.equals(personne.prenom)) &&
        dateNaissance.equals(personne.dateNaissance)&&
		adresse.equals(personne.getAdresse());
    }
}

    
   
   