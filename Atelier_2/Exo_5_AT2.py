import Exo_2_AT2
def repartir_vitrines(nbEmplacements: int, lObjets: list) -> list:
    """
    Répartit les objets dans deux vitrines en respectant la contrainte de ne pas avoir deux objets identiques
    dans la même vitrine et en limitant le nombre d'emplacements par vitrine.

    Args:
        nbEmplacements (int): Le nombre d'emplacements maximum dans chaque vitrine.
        lObjets (List[int]): La liste des objets à afficher.

    Returns:
        list: Une liste contenant les deux listes d'entiers représentant les deux vitrines,
                                     ou None si aucune configuration n'est possible.
    """
    vitrine1 = []
    vitrine2 = []

    for objet in lObjets:
        # Compter le nombre d'occurrences de l'objet dans les deux vitrines
        count_vitrine1 = Exo_2_AT2.nb_occurrences(vitrine1,objet)
        count_vitrine2 = Exo_2_AT2.nb_occurrences(vitrine2,objet)


        # Si l'objet est déjà dans les deux vitrines, il est impossible de respecter la contrainte.
        if count_vitrine1 > 0 and count_vitrine2 > 0:
            return None

        # Choisir la vitrine avec le moins d'occurrences de l'objet
        if count_vitrine1 <= count_vitrine2:
            if len(vitrine1) < nbEmplacements and len(vitrine1)<len(vitrine2):
                vitrine1.append(objet)
            else:
                vitrine2.append(objet)
        elif len(vitrine2)<nbEmplacements :
            if len(vitrine2) < nbEmplacements:
                vitrine2.append(objet)
            else:
                vitrine1.append(objet)
        else :
            return None

    return [vitrine1, vitrine2]

# Exemple d'utilisation
nbEmplacements = 4
lObjets = [1, 2, 2, 3, 4, 5, 5,]

resultat = repartir_vitrines(nbEmplacements, lObjets)

if resultat:
    vitrine1, vitrine2 = resultat
    print("Vitrine 1:", vitrine1)
    print("Vitrine 2:", vitrine2)
else:
    print("Aucune configuration possible pour respecter la contrainte.")
