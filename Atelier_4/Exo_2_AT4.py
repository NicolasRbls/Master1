import random
def mix_list(list_to_mix):
    """
    Mélange la liste list_to_mix en utilisant l'algorithme de Fisher-Yates.

    :param list_to_mix: La liste à mélanger.
    :return: Une nouvelle liste mélangée.
    """
    # Crée une copie de la liste pour éviter de modifier la liste d'origine.
    mixed_list = list(list_to_mix)
    n = len(mixed_list)

    for i in range(n):
        # Sélectionne un indice aléatoire entre 0 et i inclus.
        j = random.randint(0, i)

        # Échange les éléments aux indices i et j.
        mixed_list[i], mixed_list[j] = mixed_list[j], mixed_list[i]

    return mixed_list

# Test de votre code
lst_sorted = [i for i in range(10)]
print('Liste triée de départ', lst_sorted)
mixed_list = mix_list(lst_sorted)
print('Liste mélangée obtenue', mixed_list)
print('Liste triée de départ après appel à mixList, elle doit être inchangée', lst_sorted)

# Utilisation de assert pour vérifier que les listes sont différentes.
assert lst_sorted != mixed_list, "Les deux listes doivent être différentes après l'appel à mixList."