def somme_v1(L: list)->int:
    """
        Calcule la somme des éléments d'une liste en utilisant une boucle for basée sur les indices.

        Args:
            L (list): La liste d'entiers à sommer.

        Returns:
            int: La somme des éléments de la liste.
    """
    somme_totale = 0
    for i in range(len(L)):
        somme_totale += L[i]
    return somme_totale

def somme_v2(L: list)->int:
    """
        Calcule la somme des éléments d'une liste en utilisant une boucle for basée sur les éléments.

        Args:
            L (list): La liste d'entiers à sommer.

        Returns:
            int: La somme des éléments de la liste.
    """
    somme_totale = 0
    for e in L:
        somme_totale += e
    return somme_totale

def somme_v3(L: list)->int:
    """
        Calcule la somme des éléments d'une liste en utilisant une boucle while.

        Args:
            L (list): La liste d'entiers à sommer.

        Returns:
            int: La somme des éléments de la liste.
    """
    somme_totale = 0
    i = 0
    while i < len(L):
        somme_totale += L[i]
        i += 1
    return somme_totale
""""
La version la plus adaptée est la version 1 car dans les autres language elle est plus adapté pour les longue liste
"""

def test_exercice1():
    """
        Fonction de test pour les fonctions somme_v1, somme_v2 et somme_v3.
    """
    print("TEST SOMME")
    print("TEST Version 1 :")
    # Test avec une liste vide
    print("Test liste vide : ", somme_v1([]))  # Devrait afficher 0

    # Test avec une liste de nombres positifs
    lst2test1 = [1, 10, 100, 1000, 10000]
    print("Test somme 1111 : ", somme_v1(lst2test1))  # Devrait afficher 11111

    # Test avec une liste de nombres négatifs
    lst2test2 = [-1, -2, -3, -4, -5]
    print("Test somme -1 à -5 : ", somme_v1(lst2test2))  # Devrait afficher -15

    print("TEST Version 2 :")

    # Test avec une liste vide
    print("Test liste vide : ", somme_v2([]))  # Devrait afficher 0

    # Test avec une liste de nombres positifs
    lst2test1 = [1, 10, 100, 1000, 10000]
    print("Test somme 1111 : ", somme_v2(lst2test1))  # Devrait afficher 11111

    # Test avec une liste de nombres négatifs
    lst2test2 = [-1, -2, -3, -4, -5]
    print("Test somme -1 à -5 : ", somme_v2(lst2test2))  # Devrait afficher -15

    print("TEST Version 3 :")

    # Test avec une liste vide
    print("Test liste vide : ", somme_v3([]))  # Devrait afficher 0

    # Test avec une liste de nombres positifs
    lst2test1 = [1, 10, 100, 1000, 10000]
    print("Test somme 1111 : ", somme_v3(lst2test1))  # Devrait afficher 11111

    # Test avec une liste de nombres négatifs
    lst2test2 = [-1, -2, -3, -4, -5]
    print("Test somme -1 à -5 : ", somme_v3(lst2test2))  # Devrait afficher -15

def moyenne(L: list)->float:
    """
        Calcule la moyenne des éléments d'une liste.

        Args:
            L (list): La liste d'entiers.

        Returns:
            float: La moyenne des éléments de la liste.
    """
    if len(L) == 0:
        return 0  # Si la liste est vide, renvoyer 0

    somme_totale = somme_v2(L)

    moyenne = somme_totale / len(L)
    return moyenne

def nb_sup_v1(L: list, e: int)->int:
    """
        Compte le nombre d'éléments dans une liste strictement supérieurs à un entier donné.

        Args:
            L (list): La liste d'entiers à analyser.
            e (int): L'entier de référence.

        Returns:
            int: Le nombre d'éléments strictement supérieurs à e dans la liste.
    """
    count = 0
    for i in range(len(L)):
        if L[i] > e:
            count += 1
    return count

def nb_sup_v2(L: list, e: int)->int:
    """
       Compte le nombre d'éléments dans une liste strictement supérieurs à un entier donné.

       Args:
           L (list): La liste d'entiers à analyser.
           e (int): L'entier de référence.

       Returns:
           int: Le nombre d'éléments strictement supérieurs à e dans la liste.
    """
    count = 0
    for element in L:
        if element > e:
            count += 1
    return count

def moy_sup(L: list, e: int)->float:
    """
        Calcule la moyenne des éléments dans une liste strictement supérieurs à un entier donné.

        Args:
            L (list): La liste d'entiers à analyser.
            e (int): L'entier de référence.

        Returns:
            float: La moyenne des éléments strictement supérieurs à e dans la liste.
    """
    if not nb_sup_v1(L,e):
        return 0
    elements_sup_e = []  # Initialiser une liste vide pour stocker les éléments supérieurs à e

    for element in L:
        if element > e:
            elements_sup_e.append(element)

    moy = moyenne(elements_sup_e)
    return moy

def val_max(L: list)->int:
    """
        Trouve la valeur maximale dans une liste d'entiers.

        Args:
            L (list): La liste d'entiers à analyser.

        Returns:
            int: La valeur maximale de la liste.
    """
    if not L:  # Vérifier si la liste est vide
        return None  # Si la liste est vide, retourner None ou une autre valeur par défaut

    maximum = L[0]  # Initialiser la valeur maximale avec le premier élément de la liste

    for element in L:
        if element > maximum:
            maximum = element  # Mettre à jour la valeur maximale si un élément plus grand est trouvé
    return maximum

def ind_max(L: list)->int:
    """
       Trouve l'indice de la valeur maximale dans une liste d'entiers.

       Args:
           L (list): La liste d'entiers à analyser.

       Returns:
           int: L'indice de la valeur maximale dans la liste.
    """
    max_element = val_max(L)  # Utiliser la fonction val_max pour obtenir la valeur maximale
    max_index = None  # Initialiser l'indice du maximum avec None

    for i in range(len(L)):
        if L[i] == max_element:
            max_index = i  # Mettre à jour l'indice du maximum

    return max_index

#test_exercice1()
