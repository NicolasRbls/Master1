import numpy as np
def my_searchsorted(table: object, indice: int)->list:
    indices = []  # Une liste pour stocker les indices correspondants aux éléments recherchés

    for i in range(len(table)):
        if table[i] == indice:
            indices.append(i)

    return indices
"""
# Exemple d'utilisation de my_searchsorted
arr = np.array([1, 2, 3, 4, 5, 4, 4])
search_value = 4
indices = my_searchsorted(arr, search_value)
print("Indices des éléments égaux à", search_value, ":", indices)
"""

def my_add(tableA: object, tableB: object)-> object:
    # Vérifier si les dimensions des tableaux sont les mêmes
    if tableA.shape != tableB.shape:
        raise ValueError("Les dimensions des tableaux ne correspondent pas.")

    lignes, colonnes = tableA.shape

    # Initialiser un tableau vide pour le résultat
    resultat = np.zeros((lignes, colonnes))

    # Parcourir les indices des lignes et des colonnes et effectuer l'addition
    for i in range(lignes):
        for j in range(colonnes):
            resultat[i, j] = tableA[i, j] + tableB[i, j]

    return resultat
"""
# Exemple d'utilisation de la fonction my_add
tableau1 = np.array([[1, 2], [3, 4]])
tableau2 = np.array([[5, 6], [7, 8]])

try:
    resultat = my_add(tableau1, tableau2)
    print("Résultat de l'addition des tableaux :")
    print(resultat)
except ValueError as e:
    print(e)
"""

#-------------------------Exo bases Matrice-------------------------------------------

# 1. Initialisation et affichage
M = np.array([[1, 2, 3],
              [4, 5, 6],
              [7, 8, 9]])

print("Matrice M:")
print(M)

# 2. Opérations élémentaires
# Ajoutez 10 à chaque élément de la matrice M
M += 10
print("\nMatrice M après avoir ajouté 10 à chaque élément:")
print(M)

# Réinitialisez M à sa valeur d'origine pour effectuer la multiplication par 2
M = np.array([[1, 2, 3],
              [4, 5, 6],
              [7, 8, 9]])

# Multipliez chaque élément de la matrice M par 2
M *= 2
print("\nMatrice M après avoir multiplié chaque élément par 2:")
print(M)

# 3. Slicing et indexation
# Affichez la deuxième ligne de la matrice M
deuxieme_ligne = M[1, :]
print("\nDeuxième ligne de la matrice M:")
print(deuxieme_ligne)

# Affichez la troisième colonne de la matrice M
troisieme_colonne = M[:, 2]
print("\nTroisième colonne de la matrice M:")
print(troisieme_colonne)

# Extrayez une sous-matrice 2x2 du coin supérieur gauche de la matrice M
sous_matrice = M[:2, :2]
print("\nSous-matrice 2x2 du coin supérieur gauche de la matrice M:")
print(sous_matrice)

#-------------------------------------------------------------------------------------

#----------------------------Exo Matrices Avancee-------------------------------------

# 1. Création de matrices
# Créez une matrice A de dimensions 4x4 avec des valeurs aléatoires entre 0 et 10.
A = np.random.randint(0, 11, (4, 4))

# Créez une matrice identité I de dimensions 4x4.
I = np.eye(4)

# 2. Fonctions à définir
def matrice_trace(matrice: object)-> int:
    if matrice.shape[0] != matrice.shape[1]:
        raise ValueError("La matrice n'est pas carrée.")

    trace = 0
    for i in range(matrice.shape[0]):
        trace += matrice[i, i]

    return trace

def est_symetrique(matrice: object)->bool:
    if matrice.shape[0] != matrice.shape[1]:
        return False  # Une matrice non carrée ne peut pas être symétrique

    n = matrice.shape[0]
    for i in range(n):
        for j in range(i + 1, n):  # Ne comparer que les éléments au-dessus de la diagonale
            if matrice[i, j] != matrice[j, i]:
                return False
    return True


def produit_diagonal(matrice):
    if matrice.shape[0] != matrice.shape[1]:
        raise ValueError("La matrice n'est pas carrée.")

    n = matrice.shape[0]
    produit = 1

    for i in range(n):
        produit *= matrice[i, i]

    return produit

# 3. Application des fonctions
trace_A = matrice_trace(A)
print("Trace de la matrice A :", trace_A)

A_symetrique = (A + A.T) / 2
est_sym_A_symetrique = est_symetrique(A_symetrique)
print("La matrice (A + A.T)/2 est symétrique :", est_sym_A_symetrique)

produit_diagonal_I = produit_diagonal(I)
print("Produit des éléments de la diagonale de la matrice I :", produit_diagonal_I)

# 4. Manipulation supplémentaire

def produit_matrices(matrice1, matrice2):
    if matrice1.shape[1] != matrice2.shape[0]:
        raise ValueError("Les dimensions des matrices ne sont pas compatibles pour la multiplication.")

    lignes1, colonnes1 = matrice1.shape
    colonnes2 = matrice2.shape[1]
    resultat = np.zeros((lignes1, colonnes2))

    for i in range(lignes1):
        for j in range(colonnes2):
            for k in range(colonnes1):
                resultat[i, j] += matrice1[i, k] * matrice2[k, j]
    return resultat

A_inverse = np.linalg.inv(A)
resultat_multiplication = produit_matrices(A, A_inverse)
print("Résultat de la multiplication A * A_inverse :")
print(resultat_multiplication)