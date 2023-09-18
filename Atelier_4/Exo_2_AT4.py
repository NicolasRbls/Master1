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

    for i in range(n - 1, 0, -1):
        # Sélectionne un indice aléatoire entre 0 et i inclus.
        j = random.randint(0, i)

        # Échange les éléments aux indices i et j.
        mixed_list[i], mixed_list[j] = mixed_list[j], mixed_list[i]

    return mixed_list
