def separer(L: list) -> list:
    """
    Classe les nombres d'une liste en plaçant ensemble les négatifs, les zéros et les positifs.

    Args:
        L (List[int]): La liste d'entiers à classer.

    Returns:
        List[int]: La liste LSEP avec les nombres triés par catégorie.
    """
    LSEP = []  # Initialisation de la liste LSEP

    # Ajouter les nombres négatifs à gauche de LSEP
    for num in L:
        if num < 0:
            LSEP.insert(0, num)

    # Ajouter les zéros au centre de LSEP
    for num in L:
        if num == 0:
            LSEP.append(num)

    # Ajouter les nombres positifs à droite de LSEP
    for num in L:
        if num > 0:
            LSEP.append(num)

    return LSEP

test = True
while test:
    try:
        L = list(map(int, input("Entrez la liste à trier (séparez les éléments par des espaces) : ").split()))
        test = False  # Sortir de la boucle si l'entrée est valide
    except ValueError:
        print("Format de l'entrée invalide. Veuillez réessayer.")

LSEP = separer(L)
print(LSEP)