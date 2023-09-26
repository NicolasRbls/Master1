def listeMultiples(binf: int, bsup: int, nb: int) -> list[int]:
    """
    Retourne une liste des entiers multiples de nb compris entre binf et bsup, inclus.

    Args:
        binf (int): Le nombre entier inférieur de l'intervalle (inclus).
        bsup (int): Le nombre entier supérieur de l'intervalle (inclus).
        nb (int): Le nombre entier dont les multiples sont recherchés.

    Returns:
        list[int]: Une liste des entiers multiples de nb dans l'intervalle [binf, bsup].
    """
    multiples = [x for x in range(binf, bsup + 1) if x % nb == 0]
    return multiples

def ajouter(lst: list[int], nb: int) -> list[int]:
    """
    Retourne une nouvelle liste en ajoutant l'entier nb à chaque élément de la liste lst.

    Args:
        lst (List[int]): La liste d'entiers.
        nb (int): L'entier à ajouter à chaque élément de la liste.

    Returns:
        List[int]: Une nouvelle liste contenant les éléments de lst augmentés de nb.
    """
    nouvelle_liste = [x + nb for x in lst]
    return nouvelle_liste


def ajouterSiSup(lst: list[int], val: int, nb: int) -> list[int]:
    """
    Retourne une nouvelle liste en ajoutant l'entier nb à chaque élément de la liste lst
    qui est supérieur ou égal à l'entier val.

    Args:
        lst (List[int]): La liste d'entiers.
        val (int): L'entier seuil, les éléments de lst supérieurs ou égaux à val seront modifiés.
        nb (int): L'entier à ajouter aux éléments de lst.

    Returns:
        List[int]: Une nouvelle liste contenant les éléments de lst, modifiés pour les valeurs
        supérieures ou égales à val.
    """
    nouvelle_liste = [x + nb if x >= val else x for x in lst]
    return nouvelle_liste


def bissextiles(adeb: int, afin: int) -> list[int]:
    """
    Retourne la liste des années bissextiles comprises entre l'année adeb et l'année afin.

    Args:
        adeb (int): L'année de début de la recherche.
        afin (int): L'année de fin de la recherche.

    Returns:
        List[int]: Une liste des années bissextiles entre adeb et afin (inclus).
    """
    annees_bissextiles = [annee for annee in range(adeb, afin + 1) if (annee % 4 == 0 and annee % 100 != 0) or (annee % 400 == 0)]
    return annees_bissextiles

def premiers(binf: int, bsup: int) -> list[int]:
    """
    Retourne la liste des nombres premiers strictement compris entre les nombres binf et bsup.

    Args:
        binf (int): Le nombre entier inférieur de l'intervalle (inclus).
        bsup (int): Le nombre entier supérieur de l'intervalle (inclus).

    Returns:
        List[int]: Une liste des nombres premiers entre binf et bsup.
    """
    lst = list(range(binf + 1, bsup))

    for n in range(2, 10):
        lst = [x for x in lst if x <= n or x % n != 0]

    return lst



