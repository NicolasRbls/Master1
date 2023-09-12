def separer(L: list) -> list:
    """
    Classe les nombres d'une liste en plaçant ensemble les négatifs, les zéros et les positifs.

    Args:
        L (List[int]): La liste d'entiers à classer.

    Returns:
        List[int]: La liste LSEP avec les nombres triés par catégorie.
    """
    negatifs = []  # Initialisation de la liste pour les nombres négatifs
    positifs = []  # Initialisation de la liste pour les nombres positifs

    # Séparation des nombres en positifs, négatifs et zéros
    for num in L:
        if num < 0:
            negatifs.append(num)
        elif num > 0:
            positifs.append(num)

    # Concaténation des listes : négatifs, zéros, positifs
    LSEP = negatifs + [0] * L.count(0) + positifs

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