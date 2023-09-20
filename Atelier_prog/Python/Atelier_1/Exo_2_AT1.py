def est_bissextile(annee):
    """
       Vérifie si une année est bissextile ou non.

       Une année bissextile est définie par les règles suivantes :
       - Elle est divisible par 4.
       - Elle n'est pas divisible par 100, sauf si elle est également divisible par 400.

       Args:
           annee (int): L'année à vérifier.

       Returns:
           bool: True si l'année est bissextile, False sinon.
    """
    if (annee % 4 == 0 and annee % 100 != 0) or annee % 400 == 0:
        return True
    else:
        return False

# Fonction de test
def tester_est_bissextile():
    """
        Effectue un test de la fonction est_bissextile en utilisant différentes années
        et affiche les résultats des tests.
    """
    test_cases = {
        2000: True,  # Bissextile car divisible par 400
        2020: True,  # Bissextile car divisible par 4 et non par 100
        2022: False,  # Non bissextile car ni divisible par 4, ni par 400
        2100: False,  # Non bissextile car divisible par 100 mais pas par 400
    }

    for annee, resultat_attendu in test_cases.items():
        resultat = est_bissextile(annee)
        if resultat == resultat_attendu:
            print(f"{annee} est bissextile : {resultat} (Correct)")
        else:
            print(f"{annee} est bissextile : {resultat} (Incorrect, attendu : {resultat_attendu})")

# Appeler la fonction de test
tester_est_bissextile()
