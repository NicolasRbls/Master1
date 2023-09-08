def position_v1(lst: list, elt: int)->int:
    for i in range(len(lst)):
        if lst[i] == elt:
            return i  # Renvoie l'indice lorsque l'élément est trouvé
    return -1  # Renvoie -1 si l'élément n'est pas présent dans la liste

def position_v2(lst: list, elt: int)->int:
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
 tandis que la version 2 avec la boucle while peut être utile si vous préférez une approche itérative différente.
"""
