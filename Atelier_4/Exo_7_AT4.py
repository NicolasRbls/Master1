import random

def is_sorted(lst: list) -> bool:
    """
    Vérifie si une liste d'entiers est triée en ordre croissant.

    :param lst: La liste à vérifier.
    :return: True si la liste est triée en ordre croissant, False sinon.
    """
    for i in range(len(lst) - 1):
        if lst[i] > lst[i + 1]:
            return False
    return True

def stupid_sort(lst_to_sort: list) -> list:
    """
    Trie une liste d'entiers en utilisant l'algorithme de tri stupide.

    :param lst_to_sort: La liste à trier.
    :return: Une nouvelle liste contenant les éléments triés.
    """
    return_list = list(lst_to_sort)
    while not is_sorted(return_list):
        random.shuffle(return_list)
    return return_list

"""
my_lst_to_sort = [170, 45, 75, 90, 2, 24, 802, 66]
sorted_lst = stupid_sort(my_lst_to_sort)
print('La liste avant tri', my_lst_to_sort)
print('Le tri par insertion donne', sorted_lst)
"""

def insertion_sort(list_to_sort: list)->list:
    """
       Trie une liste de nombres en utilisant l'algorithme de tri par insertion.

       :param list_to_sort: La liste de nombres à trier.
       :return: Une nouvelle liste contenant les mêmes éléments triés.
    """
    sorted_list = list_to_sort.copy()  # Créez une copie de la liste d'origine pour ne pas la modifier

    for i in range(1, len(sorted_list)):
        x = sorted_list[i]  # Mémoriser l'élément à insérer

        j = i
        while j > 0 and sorted_list[j - 1] > x:
            sorted_list[j] = sorted_list[j - 1]  # Décaler vers la droite les éléments plus grands
            j -= 1

        sorted_list[j] = x  # Insérer l'élément dans le "trou" laissé par le décalage

    return sorted_list

"""
my_lst_to_sort = [170, 45, 75, 90, 2, 24, 802, 66]
sorted_lst = insertion_sort(my_lst_to_sort)
print('La liste avant tri', my_lst_to_sort)
print('Le tri par insertion donne', sorted_lst)
"""

def selection_sort(lst_to_sort: list)->list:
    """
        Trie une liste de nombres en utilisant l'algorithme de tri par sélection.

        :param lst_to_sort: La liste de nombres à trier.
        :return: Une nouvelle liste contenant les mêmes éléments triés.
    """
    sorted_list = lst_to_sort.copy()  # Créez une copie de la liste d'origine pour ne pas la modifier

    for i in range(len(sorted_list) - 1):
        min_index = i

        for j in range(i + 1, len(sorted_list)):
            if sorted_list[j] < sorted_list[min_index]:
                min_index = j

        if min_index != i:
            sorted_list[i], sorted_list[min_index] = sorted_list[min_index], sorted_list[i]  # Échangez les éléments

    return sorted_list

"""
my_lst_to_sort = [170, 45, 75, 90, 2, 24, 802, 66]
sorted_lst = selection_sort(my_lst_to_sort)
print('La liste avant tri', my_lst_to_sort)
print('Le tri par sélection donne', sorted_lst)
"""


def bubble_sort(lst_to_sort: list) -> list:
    """
    Trie une liste de nombres en utilisant l'algorithme de tri à bulles.

    :param lst_to_sort: La liste de nombres à trier.
    :return: Une nouvelle liste contenant les mêmes éléments triés.
    """
    sorted_list = lst_to_sort.copy()  # Créez une copie de la liste d'origine pour ne pas la modifier
    taille = len(sorted_list)
    for i in range(taille - 1):
        tableau_trié = True
        for j in range(0, taille - i - 1):
            if sorted_list[j] > sorted_list[j + 1]:
                sorted_list[j], sorted_list[j + 1] = sorted_list[j + 1], sorted_list[j]  # Échangez les éléments
                tableau_trié = False
        if tableau_trié:
            return sorted_list  # Si le tableau est déjà trié, nous pouvons sortir plus tôt

    return sorted_list
"""
my_lst_to_sort = [170, 45, 75, 90, 2, 24, 802, 66]
sorted_lst = bubble_sort(my_lst_to_sort)
print('Avant tri :', my_lst_to_sort)
print('Résultat du tri :', sorted_lst)
print('Après le tri, la liste d\'origine n\'a pas été modifiée:', my_lst_to_sort)
"""

def tri_fusion(liste_a_trier: list) -> list:
    """
    Trie une liste d'entiers en utilisant l'algorithme de tri fusion (merge sort).

    :param liste_a_trier: La liste d'entiers à trier.
    :return: Une nouvelle liste contenant les mêmes éléments triés dans l'ordre croissant.
    """
    if len(liste_a_trier) <= 1:
        return liste_a_trier  # Si la liste a un élément ou moins, elle est déjà triée

    # Séparez la liste en deux moitiés
    milieu = len(liste_a_trier) // 2
    moitie_gauche = liste_a_trier[:milieu]
    moitie_droite = liste_a_trier[milieu:]

    # Triez récursivement les deux moitiés
    moitie_gauche_triee = tri_fusion(moitie_gauche)
    moitie_droite_triee = tri_fusion(moitie_droite)

    # Fusionnez les deux listes triées
    return fusion(moitie_gauche_triee, moitie_droite_triee)

def fusion(gauche: list, droite: list) -> list:
    """
    Fusionne deux listes triées en une seule liste triée.

    :param gauche: La première liste triée.
    :param droite: La deuxième liste triée.
    :return: Une nouvelle liste triée résultant de la fusion des deux listes d'entrée.
    """
    fusionnee = []
    index_gauche, index_droite = 0, 0

    while index_gauche < len(gauche) and index_droite < len(droite):
        if gauche[index_gauche] <= droite[index_droite]:
            fusionnee.append(gauche[index_gauche])
            index_gauche += 1
        else:
            fusionnee.append(droite[index_droite])
            index_droite += 1

    # Ajoutez les éléments restants, s'il y en a
    fusionnee.extend(gauche[index_gauche:])
    fusionnee.extend(droite[index_droite:])

    return fusionnee
"""
my_lst_to_sort = [170, 45, 75, 90, 2, 24, 802, 66]
sorted_lst = tri_fusion(my_lst_to_sort)
print('Avant tri :', my_lst_to_sort)
print('Résultat du tri :', sorted_lst)
print('Après le tri, la liste d\'origine n\'a pas été modifiée:', my_lst_to_sort)
"""

