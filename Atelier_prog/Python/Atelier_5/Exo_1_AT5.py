import numpy as np
def somme_recursive(liste: list) -> int:
    # Cas de base : si la liste est vide, la somme est 0
    if not liste:
        return 0
    else:
        # Récursivement, ajoute le premier élément à la somme des éléments restants
        return liste[0] + somme_recursive(liste[1:])
"""
# Test de la fonction
liste1 = [1, 2, 3, 4, 5]
resultat1 = somme_recursive(liste1)
print("La somme de la liste est :", resultat1)

liste2 = []
resultat2 = somme_recursive(liste2)
print("La somme de la liste est :", resultat2)
"""

def factorielle_recursive(nombre: int)->int:
    # Cas de base : si n est 0 ou 1, le factoriel est 1
    if nombre == 0 or nombre == 1:
        return 1
    else:
        # Récursivement, le factoriel de n est n multiplié par le factoriel de (n-1)
        return nombre * factorielle_recursive(nombre - 1)
"""
# Test de la fonction
nombre = 5  # Vous pouvez changer la valeur de n selon vos besoins
resultat = factorielle_recursive(nombre)
print(f"Le factoriel de {nombre} est : {resultat}")
"""

def longueur(lst : list)->int:
    if not lst:
        return 0
    else :
        return 1+longueur(lst[1:])

"""    
#Test
test_list = [1,2,8,3,4]
print(longueur(test_list))
"""

def findMin(lst : list)->int:
    # Cas de base : si la liste est vide, retourner None
    if not lst:
        return None
    # Cas de base : si la liste a un seul élément, retourner cet élément
    if len(lst) == 1:
        return lst[0]

    # Comparer le premier élément avec le reste de la liste
    if lst[0] < findMin(lst[1:]):
        return lst[0]
    else:
        return findMin(lst[1:])
"""
# Test de la fonction
lst = [5, 3, 8, 1, 7, 2, 6]
minimum = findMin(lst)
print("La valeur minimale dans la liste est :", minimum)
"""

def listPairs(lst: list)->list:
    if not lst:
        return []

    if lst[0]%2==0:
        return [lst[0]]+listPairs(lst[1:])
    else:
        return listPairs(lst[1:])
"""
# Test de la fonction
liste = [1, 2, 3, 4, 5, 6, 7, 8]
resultat = listPairs(liste)
print("Les éléments pairs de la liste sont :", resultat)
"""

def concat_list(lst: list)->list:
    if not lst:
        return []
    else:
        return lst[0]+concat_list(lst[1:])
"""
#Test de la fonction
listes =[[1,2],[3,4],[5,6]]
print(concat_list(listes))
"""




