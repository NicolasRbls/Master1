import Exo_1_AT2
def histo(F: list) -> list:
    """
    Calcule l'histogramme de la liste F en comptabilisant le nombre d'apparitions de chaque valeur.

    Args:
        F (List[int]): La liste d'entiers définissant une fonction.

    Returns:
        List[int]: Une liste d'entiers représentant l'histogramme de F.
                   Chaque case de la liste représente le nombre d'apparitions de la valeur correspondante.
    """

    # Trouver la valeur maximale dans F pour déterminer la taille de la liste H

    if Exo_1_AT2.val_max(F) ==None:
        max_valeur = 0
    else:
        max_valeur = Exo_1_AT2.val_max(F)

    # Initialiser la liste H avec des zéros
    H = [0] * (max_valeur + 1)

    # Parcourir la liste F et incrémenter les cases correspondantes de la liste H
    for valeur in F:
        H[valeur] += 1

    return H

def test_histo():
    """
    Fonction de test pour la fonction histo(F) qui calcule l'histogramme d'une liste d'entiers F.
    Vérifie le résultat de la fonction histo pour différents cas de test.

    """
    # Cas de test 1
    F1 = [6, 5, 6, 8, 4, 2, 1, 5]
    expected_result1 = [0, 1, 1, 0, 1, 2, 2, 0, 1]
    result1 = histo(F1)
    if result1 == expected_result1:
        print("Test 1 réussi.")
    else:
        print(f"Erreur pour F1: attendu {expected_result1}, obtenu {result1}")

    # Cas de test 2
    F2 = [1, 2, 3, 4, 5]
    expected_result2 = [0, 1, 1, 1, 1, 1]
    result2 = histo(F2)
    if result2 == expected_result2:
        print("Test 2 réussi.")
    else:
        print(f"Erreur pour F2: attendu {expected_result2}, obtenu {result2}")

    # Cas de test 3
    F3 = [0, 0, 0, 0, 0]
    expected_result3 = [5]
    result3 = histo(F3)
    if result3 == expected_result3:
        print("Test 3 réussi.")
    else:
        print(f"Erreur pour F3: attendu {expected_result3}, obtenu {result3}")

def est_injective(F:list) -> bool:
    """
    Vérifie si la fonction représentée par la liste F est injective.

    Args:
        F (List[int]): La liste d'entiers définissant une fonction.

    Returns:
        bool: True si la fonction est injective, False sinon.
    """
    # Calcul de l'histogramme H de F
    H = histo(F)

    # Vérification de l'injectivité
    for valeur in H:
        if valeur > 1:
            return False

    return True

def test_est_injective():
    """
        Fonction de test pour la fonction est_injective(F).
        Vérifie le résultat de la fonction est_injective pour différents cas de test.
    """
    # Cas de test 1
    F1 = [6, 5, 6, 7, 4, 2, 1, 5]
    expected_result1 = False
    result1 = est_injective(F1)
    if result1 == expected_result1:
        print("Test 1 réussi.")
    else:
        print(f"Erreur pour F1: attendu {expected_result1}, obtenu {result1}")

    # Cas de test 2
    F2 = [3, 0, 6, 7, 4, 2, 1, 5]
    expected_result2 = True
    result2 = est_injective(F2)
    if result2 == expected_result2:
        print("Test 2 réussi.")
    else:
        print(f"Erreur pour F2: attendu {expected_result2}, obtenu {result2}")

    # Cas de test 3 (liste vide)
    F3 = []
    expected_result3 = True  # Une liste vide est considérée comme injective
    result3 = est_injective(F3)
    if result3 == expected_result3:
        print("Test 3 réussi.")
    else:
        print(f"Erreur pour F3: attendu {expected_result3}, obtenu {result3}")

def est_surjective(F: list) -> bool:
    """
    Vérifie si la fonction représentée par la liste F est surjective.

    Args:
        F (List[int]): La liste d'entiers définissant une fonction.

    Returns:
        bool: True si la fonction est surjective, False sinon.
    """
    # Calcul de l'histogramme H de F
    H = histo(F)

    # Vérification de la surjectivité
    for valeur in H:
        if valeur < 1:
            return False

    return True

def test_est_surjective():
    """
            Fonction de test pour la fonction est_surjective(F).
            Vérifie le résultat de la fonction est_surjective pour différents cas de test.
    """
    # Cas de test 1
    F1 = [6, 5, 6, 7, 4, 2, 1, 5]
    expected_result1 = False
    result1 = est_surjective(F1)
    if result1 == expected_result1:
        print("Test 1 réussi.")
    else:
        print(f"Erreur pour F1: attendu {expected_result1}, obtenu {result1}")

    # Cas de test 2
    F2 = [3, 0, 6, 7, 4, 2, 1, 5]
    expected_result2 = True
    result2 = est_surjective(F2)
    if result2 == expected_result2:
        print("Test 2 réussi.")
    else:
        print(f"Erreur pour F2: attendu {expected_result2}, obtenu {result2}")

    # Cas de test 3 (liste vide)
    F3 = []
    expected_result3 = False  # Une liste vide n'est pas surjective
    result3 = est_surjective(F3)
    if result3 == expected_result3:
        print("Test 3 réussi.")
    else:
        print(f"Erreur pour F3: attendu {expected_result3}, obtenu {result3}")


def est_bijective(F: list) -> bool:
    """
    Vérifie si la fonction représentée par la liste F est bijective.

    Args:
        F (List[int]): La liste d'entiers définissant une fonction.

    Returns:
        bool: True si la fonction est bijective, False sinon.
    """
    return est_injective(F) and est_surjective(F)

def test_est_bijective():
    """
                Fonction de test pour la fonction est_bijective(F).
                Vérifie le résultat de la fonction est_bijective pour différents cas de test.
    """
    # Cas de test 1 (Non bijective)
    F1 = [6, 5, 6, 7, 4, 2, 1, 5]
    expected_result1 = False
    result1 = est_bijective(F1)
    if result1 == expected_result1:
        print("Test 1 réussi.")
    else:
        print(f"Erreur pour F1: attendu {expected_result1}, obtenu {result1}")

    # Cas de test 2 (Bijective)
    F2 = [3, 0, 6, 7, 4, 2, 1, 5]
    expected_result2 = True
    result2 = est_bijective(F2)
    if result2 == expected_result2:
        print("Test 2 réussi.")
    else:
        print(f"Erreur pour F2: attendu {expected_result2}, obtenu {result2}")

    # Cas de test 3 (liste vide)
    F3 = []
    expected_result3 = False  # Une liste vide n'est pas bijective
    result3 = est_bijective(F3)
    if result3 == expected_result3:
        print("Test 3 réussi.")
    else:
        print(f"Erreur pour F3: attendu {expected_result3}, obtenu {result3}")


import matplotlib.pyplot as plt


def afficheHisto(F: list) -> None:
    """
    Affiche une représentation graphique de l'histogramme associé à la liste d'entiers F.

    Args:
        F (List[int]): La liste d'entiers définissant une fonction.

    Returns:
        None
    """

    # Affichage graphique de l'histogramme
    plt.hist(F, bins=range(max(F) + 2), rwidth=0.8, align='left', alpha=0.75, edgecolor='black')

    # Étiquetage des axes
    plt.xlabel('Valeurs')
    plt.ylabel('Occurrences')

    # Titre du graphique
    plt.title('Histogramme')

    # Affichage du graphique
    plt.show()




#------------APPEL TEST------------------
test_histo()
test_est_injective()
test_est_surjective()
test_est_bijective()

F = [1, 5, 5, 5, 9, 11, 11, 15 , 15 , 15]
afficheHisto(F)