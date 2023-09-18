import random

def extract_elements_list(list_in_which_to_choose, int_nbr_of_element_to_extract):
    """
    Extrait int_nbr_of_element_to_extract éléments aléatoires de la liste list_in_which_to_choose.

    :param list_in_which_to_choose: La liste à partir de laquelle extraire les éléments.
    :param int_nbr_of_element_to_extract: Le nombre d'éléments à extraire.
    :return: Une liste composée d'éléments extraits aléatoirement.
    """
    if not list_in_which_to_choose:
        raise ValueError("La liste ne doit pas être vide.")
    if int_nbr_of_element_to_extract > len(list_in_which_to_choose):
        raise ValueError("Le nombre d'éléments à extraire est supérieur à la taille de la liste.")

    # Créez une copie de la liste pour ne pas la modifier.
    list_copy = list(list_in_which_to_choose)
    extracted_elements = []

    for i in range(int_nbr_of_element_to_extract):
        # Sélectionnez un indice aléatoire.
        random_index = random.randint(0, len(list_copy) - 1)

        # Ajoutez l'élément correspondant à l'indice aléatoire à la liste extraite.
        extracted_elements.append(list_copy.pop(random_index))

    return extracted_elements


# Test de votre code
lst_sorted = [i for i in range(10)]
print('Liste de départ', lst_sorted)
sublist = extract_elements_list(lst_sorted, 6)
print('La sous liste extraite est', sublist)
print('Liste de départ après appel de la fonction est', lst_sorted)
# Utilisation de assert pour vérifier que les listes sont différentes.
assert lst_sorted != sublist, "Les deux listes doivent être différentes après l'appel à mixList."
