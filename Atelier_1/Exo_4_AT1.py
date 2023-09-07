from datetime import date
import Exo_2_AT1

def date_est_valide(jour: int,mois: int,annee: int)->bool:
    """
        Vérifie si une date est valide.

        Args:
            jour (int): Le jour de la date.
            mois (int): Le mois de la date.
            annee (int): L'année de la date.

        Returns:
            bool: True si la date est valide, False sinon.
    """
    # Vérification du mois
    if mois < 1 or mois > 12:
        return False

    # Liste des jours par mois
    jours_par_mois = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    # Vérification pour février en cas d'année bissextile
    if mois == 2:
        if Exo_2_AT1.est_bissextile(annee):
            jours_par_mois[1] = 29

    # Vérification du jour
    if jour < 1 or jour > jours_par_mois[mois - 1]:
        return False

    # Si toutes les vérifications ont réussi, la date est valide
    return True

def saisie_date_naissance():
    """
        Saisit une date de naissance au clavier.

        Returns:
            date: L'objet date correspondant à la date de naissance.
    """
    while True:
        try:
            # Saisie de l'année, du mois et du jour
            annee = int(input("Entrez l'année de naissance : "))
            mois = int(input("Entrez le mois de naissance : "))
            jour = int(input("Entrez le jour de naissance : "))

            # Création de l'objet date
            date_naissance = date(annee, mois, jour)

            # Vérification si la date est valide
            if date_est_valide(jour, mois, annee):
                return date_naissance
            else:
                print("Date de naissance invalide. Veuillez réessayer.")
        except ValueError:
            print("Format de date invalide. Veuillez réessayer.")

def age(date_naissance: date)-> int:
    """
        Calcule l'âge en années à partir de la date de naissance.

        Args:
            date_naissance (date): La date de naissance.

        Returns:
            int: L'âge en années.
    """
    # Obtenez la date du jour
    date_actuelle = date.today()

    # Calculez la différence entre la date actuelle et la date de naissance
    difference = date_actuelle - date_naissance

    # Obtenez l'âge en années (partie entière de la différence en jours/365.25)
    age_en_annees = difference.days // 365.25

    return age_en_annees

def est_majeur(date_naissance: date)->bool:
    """
        Vérifie si une personne est majeure à partir de sa date de naissance.

        Args:
            date_naissance (date): La date de naissance de la personne.

        Returns:
            bool: True si la personne est majeure, False sinon.
    """
    # Obtenez l'âge de la personne à partir de la date de naissance
    age_personne = age(date_naissance)

    # Vérifiez si la personne est majeure (âge >= 18)
    return age_personne >= 18


def test_acces():
    """
        Effectue un test d'accès en saisissant la date de naissance et affiche le statut d'accès.
    """
    # Vérification si la date est valide
    date_naissance = saisie_date_naissance()
    if est_majeur(date_naissance):
        print(f"Bonjour, vous avez {age(date_naissance)} ans, Accès autorisé.")
    else:
        print(f"Désolé, vous avez {age(date_naissance)} ans, Accès interdit.")

def test_acces_personnes():
    """
       Effectue une batterie de test d'accès affiche les résultats.
    """
    personnes = {
        date(2000, 5, 15): True,
        date(1995, 10, 30): True,
        date(2010, 3, 8): False,
    }

    for date_test, result_att in personnes.items():
        if result_att == est_majeur(date_test):
            print(f"Test Réussie : Vous avez {age(date_test)} ans, Accès autorisé.\n")
        else:
            print(f"Test échoué : Vous avez {age(date_test)} ans, Accès interdit.\n")

# Exécutez la fonction de test
test_acces_personnes()
#Appel fonction de saisie
test_acces()