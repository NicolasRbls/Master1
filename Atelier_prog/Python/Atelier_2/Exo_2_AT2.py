def position_v1(lst: list, elt: int)->int:
    """
       Recherche la position d'un élément dans une liste en utilisant une boucle for.

       Args:
           lst (list): La liste d'entiers à analyser.
           elt (int): L'entier dont on veut trouver la position.

       Returns:
           int: L'indice de l'élément e dans la liste, ou -1 s'il n'est pas présent.
    """
    for i in range(len(lst)):
        if lst[i] == elt:
            return i  # Renvoie l'indice lorsque l'élément est trouvé
    return -1  # Renvoie -1 si l'élément n'est pas présent dans la liste

def position_v2(lst: list, elt: int)->int:
    """
      Recherche la position d'un élément dans une liste en utilisant une boucle while.

      Args:
          lst (list): La liste d'entiers à analyser.
          elt (int): L'entier dont on veut trouver la position.

      Returns:
          int: L'indice de l'élément e dans la liste, ou -1 s'il n'est pas présent.
    """
    i = 0
    while i < len(lst):
        if lst[i] == elt:
            return i  # Renvoie l'indice lorsque l'élément est trouvé
        i += 1
    return -1  # Renvoie -1 si l'élément n'est pas présent dans la liste

def nb_occurrences(lst: list, e: int)->int:
    """
       Compte le nombre d'occurrences d'un entier dans une liste.

       Args:
           lst (List[int]): La liste d'entiers à analyser.
           e (int): L'entier dont on veut compter les occurrences.

       Returns:
           int: Le nombre d'occurrences de l'entier e dans la liste.
    """
    count = 0
    for element in lst:
        if element == e:
            count += 1
    return count

def est_triee_v1(lst: list) -> bool:
    """
    Vérifie si une liste est triée par ordre croissant en utilisant une boucle for.

    Args:
        lst (List[int]): La liste d'entiers à vérifier.

    Returns:
        bool: True si la liste est triée, False sinon.
    """
    for i in range(len(lst) - 1):
        if lst[i] > lst[i + 1]:
            return False
    return True

def est_triee_v2(lst: list) -> bool:
    """
    Vérifie si une liste est triée par ordre croissant en utilisant une boucle while.

    Args:
        lst (List[int]): La liste d'entiers à vérifier.

    Returns:
        bool: True si la liste est triée, False sinon.
    """
    i = 0
    while i < len(lst) - 1:
        if lst[i] > lst[i + 1]:
            return False
        i += 1
    return True

"""
 La version 1 avec la boucle for peut être plus concise, 
 tandis que la version 2 avec la boucle while peut être utile pour une approche itérative différente.
"""

def position_tri(lst: list, e: int) -> int:
    """
    Recherche la position d'un élément dans une liste triée en utilisant la recherche dichotomique.

    Args:
        lst (List[int]): La liste triée d'entiers à analyser.
        e (int): L'entier dont on veut trouver la position.

    Returns:
        int: L'indice de l'élément e dans la liste, ou -1 s'il n'est pas présent.
    """
    left = 0
    right = len(lst) - 1

    while left <= right:
        mid = left + (right - left) // 2

        if lst[mid] == e:
            return mid  # L'élément a été trouvé, renvoie son indice
        elif lst[mid] < e:
            left = mid + 1
        else:
            right = mid - 1

    return -1  # L'élément n'est pas présent dans la liste, renvoie -1

def a_repetitions(lst: list) -> bool:
    """
    Vérifie si une liste comporte des répétitions de valeurs en utilisant une liste auxiliaire T.

    Args:
        lst (List[int]): La liste d'entiers à vérifier.

    Returns:
        bool: True si des répétitions sont présentes, False sinon.
    """
    T = []  # Initialisation de la liste auxiliaire T à vide

    i = 0
    while i < len(lst):
        if lst[i] not in T:
            T.append(lst[i])  # Ajoute l'élément à la liste auxiliaire T s'il n'est pas déjà présent
        else:
            return True  # Il y a une répétition, renvoie True
        i += 1

    return False  # Aucune répétition trouvée, renvoie False
