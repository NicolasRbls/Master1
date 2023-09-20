import random

def choose_element_list(list_in_which_to_choose):
    """
    Choisi un élément aléatoire dans la liste list_in_which_to_choose.

    :param list_in_which_to_choose: La liste à partir de laquelle choisir.
    :return: Un élément aléatoire de la liste.
    """
    if not list_in_which_to_choose:
        raise ValueError("La liste ne doit pas être vide.")

    # Utilisez random.randint() pour obtenir un indice aléatoire.
    random_index = random.randint(0, len(list_in_which_to_choose) - 1)

    # Retournez l'élément correspondant à l'indice aléatoire.
    return list_in_which_to_choose[random_index]


# Test de votre code
lst_sorted = [i for i in range(10)]
print('Liste triée de départ', lst_sorted)
e1 = choose_element_list(lst_sorted)
print('A la première exécution', e1, 'a été sélectionné')
e2 = choose_element_list(lst_sorted)
print('A la deuxième exécution', e2, 'a été sélectionné')
assert e1 != e2, "Attention vérifiez votre code, pour deux sélections de suite l'élément sélectionné est le même !"
