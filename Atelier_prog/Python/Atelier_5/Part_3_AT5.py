import numpy as np
def matriceAdjacence(S: list, A: list)->object:
    # Créez une matrice carrée de dimensions len(S) x len(S) remplie de zéros.
    matrice = np.zeros((len(S), len(S)), dtype=int)
    for arc in A:
        i, j = arc
        if i in S and j in S:
            matrice[S.index(i)][S.index(j)] = 1
    return matrice
"""
# Exemple d'utilisation de la procédure matriceAdjacence
S = [1,2,3,4]
A = [(1, 1), (2, 3), (3, 4), (4, 1), (2, 4)]
matrice_adjacence = matriceAdjacence(S, A)
print("Matrice d'adjacence :")
print(matrice_adjacence)

"""
def matriceAdjacencePond(S: list, A: list)->object:
    # Créez une matrice carrée de dimensions len(S) x len(S) remplie de zéros.
    matrice = np.zeros((len(S), len(S)), dtype=int)
    for arc in A:
        i, j , poid = arc
        if i in S and j in S:
            matrice[S.index(i)][S.index(j)] = poid
    return matrice
"""
# Exemple d'utilisation de la procédure matriceAdjacence
S = [1,2,3,4]
A = [(1, 1 , 5), (2, 3 , 1), (3, 4, 4), (4, 1, 6), (2, 4, 2)]
matrice_adjacence = matriceAdjacencePond(S, A)
print("Matrice d'adjacence :")
print(matrice_adjacence)
"""

def lireMatriceFichier(nomfichier: str) -> object:
    with open(nomfichier, "r") as fichier:
        lignes = fichier.readlines()

        # Lire la première ligne pour déterminer la dimension de la matrice
        premiere_ligne = lignes[0].strip().split()
        dimension = len(premiere_ligne)

        # Initialiser la matrice numpy avec des zéros
        matrice = np.zeros((dimension, dimension), dtype=int)

        # Remplir la première ligne de la matrice
        for j, chiffre_str in enumerate(premiere_ligne):
            matrice[0, j] = int(chiffre_str)

        # Remplir le reste des lignes de la matrice
        for i in range(1, dimension):
            nombres_str = lignes[i].strip().split()
            for j, chiffre_str in enumerate(nombres_str):
                matrice[i, j] = int(chiffre_str)

    return matrice

"""
print(lireMatriceFichier("graph0.txt"))
print(lireMatriceFichier("graph1.txt"))
print(lireMatriceFichier("graph2.txt"))
print(lireMatriceFichier("graph3.txt"))
print(lireMatriceFichier("graph4.txt"))

"""
def tousLesSommets(mat: object)->list:
    """
    Retourne une liste contenant tous les indices des sommets du graphe G défini par la matrice d'adjacence mat.

    Args:
        mat : La matrice d'adjacence du graphe G.

    Returns:
        list: Une liste contenant les indices de tous les sommets du graphe.
    """
    # Récupérez le nombre de sommets (taille de la matrice)
    nb_sommets = mat.shape[0]

    # Créez une liste contenant tous les indices de sommets
    indices_sommets = list(range(nb_sommets))

    return indices_sommets
"""
# Exemple d'utilisation avec une matrice d'adjacence
S = [1,2,3,4]
A = [(1, 1), (2, 3), (3, 4), (4, 1), (2, 4)]
matrice_adjacence = matriceAdjacence(S, A)
sommets = tousLesSommets(matrice_adjacence)
print("Indices de tous les sommets :", sommets)
"""

def listeArcs(mat: object)->list:
    result = []
    taille = mat.shape[0]
    for i in range(taille):
        for y in range(taille):
            if mat[i,y]!=0:
                result.append((i,y))
    return result
"""
# Exemple d'utilisation avec une matrice d'adjacence
S = [1,2,3,4]
A = [(1, 1), (2, 3), (3, 4), (4, 1), (2, 4)]
matrice_adjacence = matriceAdjacence(S, A)
arcs = listeArcs(matrice_adjacence)
print("Liste des arcs :", arcs)
"""

def matriceIncidence(mat):
    """
    Retourne la matrice d'incidence associée au graphe défini par la matrice d'adjacence mat.

    Args:
        matrice: La matrice d'adjacence du graphe G.

    Returns:
        matrice: La matrice d'incidence du graphe.
    """

    #une liste pour stocker les arêtes
    arretes = listeArcs(mat)

    nb_sommets = len(tousLesSommets(mat))
    nb_arcs = len(arretes) #Comptez le nombre d'arcs dans la matrice

    # Construisez la matrice d'incidence
    matrice_incidence = np.zeros((nb_sommets, nb_arcs), dtype=int)

    for idx_arrete, arrete in enumerate(arretes):
        i, j = arrete
        matrice_incidence[i][idx_arrete] = 1
        matrice_incidence[j][idx_arrete] = -1

    return matrice_incidence

"""
# Exemple d'utilisation avec une matrice d'adjacence
S = [0,1,2,3,4]
A = [(0, 1), (0, 2), (1, 2), (1, 4), (2, 3), (3,4),(4,2)]
matrice_adjacence = matriceAdjacence(S, A)
matrice_incidence = matriceIncidence(matrice_adjacence)
print("Matrice d'incidence :\n", matrice_incidence)
"""

def est_voisin(matrice_adjacence, S, V):
    """
    Vérifie si deux sommets S et V sont voisins dans un graphe défini par la matrice d'adjacence.

    Args:
        matrice_adjacence (numpy.ndarray): La matrice d'adjacence du graphe.
        S (int): L'indice du sommet S.
        V (int): L'indice du sommet V.

    Returns:
        bool: True si les sommets S et V sont voisins, False sinon.
    """
    # Vérifiez si la valeur de la matrice d'adjacence à l'emplacement (S, V) est différente de zéro
    if matrice_adjacence[S][V] != 0:
        return True
    else:
        return False
"""
S = [1,2,3,4]
A = [(1, 1), (2, 3), (3, 4), (4, 1), (2, 4)]
matrice_adjacence = matriceAdjacence(S, A)
S2 = 0
V = 0

if est_voisin(matrice_adjacence, S2, V):
    print(f"Les sommets {S2} et {V} sont voisins.")
else:
    print(f"Les sommets {S2} et {V} ne sont pas voisins.")
"""





