import random

def gen_list_random_int(int_nbr=10, int_binf=0, int_bsup=10):
    """
    Génère et retourne une liste de nombres aléatoires entre int_binf (inclus) et int_bsup (exclus).

    :param int_nbr: Le nombre de nombres aléatoires à générer (par défaut 10).
    :param int_binf: La limite inférieure de la plage (par défaut 0).
    :param int_bsup: La limite supérieure de la plage (par défaut 10).
    :return: Une liste de nombres aléatoires.
    """
    if int_bsup <= int_binf:
        raise ValueError("La limite supérieure doit être strictement supérieure à la limite inférieure.")

    random_numbers = []
    for i in range(int_nbr):
        random_numbers.append(random.randint(int_binf, int_bsup - 1))

    return random_numbers

# Exemple d'utilisation avec des paramètres personnalisés :
ma_liste = gen_list_random_int(15, 20, 30)
print(ma_liste)

# Exemple d'utilisation avec les valeurs par défaut :
liste_par_defaut = gen_list_random_int()
print(liste_par_defaut)