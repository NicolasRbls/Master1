#-----------------------PARTIE1------------------------
def present(L, e):
    return e in L

def present1(L, e):
    """
    Question 3: La fonction present1 a un problème dans la partie else. Actuellement, elle contient False,
    mais cela ne change pas la valeur de retour de la fonction.
    Pour corriger la fonction, vous devez retourner False lorsque l'élément n'est pas trouvé dans la liste.
    (même correction que pour present 2 regarde (present2_corriger)
    """
    for i in range(0, len(L), 1):
        if L[i] == e:
            return True
        else:
            False
    return False

def present2(L, e):
    """
        Question 3: Cette fonction ne passe pas le test donc je la reécrit en version corriger(present2_corriger) !
    """
    b = True
    for i in range(0, len(L), 1):
        if L[i] == e:
            b = True
        else:
            b = False
    return b

def present3(L, e):
    """
        Question 3: Cette fonction ne passe pas le test donc je la reécrit en version corriger(present3_corriger) !
    """
    b =True
    for i in range(0, len(L), 1):
        return L[i] == e


def present4(L, e):
    b = False
    i = 0
    while i < len(L) and b:
        if L[i] == e:
            b = True
    return b


def present2_corriger(L, e):
    """
     La fonction originale a un problème car elle réinitialise la variable b à True
     à chaque itération de la boucle, ce qui la rend inutile.

    Args:
        L (list): La liste à vérifier.
        e: L'élément à rechercher dans la liste.

    Returns:
        bool: True si e est présent dans la liste, False sinon.
    """
    for i in range(len(L)):
        if L[i] == e:
            return True
    return False

def present3_corriger(L, e):
    """
    La fonction originale a un problème car elle renvoie la valeur de la comparaison dès la première itération de la boucle,
    ce qui peut ne pas donner le résultat souhaité.

    Args:
        L (list): La liste à vérifier.
        e: L'élément à rechercher dans la liste.

    Returns:
        bool: True si e est présent dans la liste, False sinon.
    """
    b = False  # Initialiser b à False
    for i in range(len(L)):
        if L[i] == e:
            b = True  # Définir b à True si l'élément est trouvé, mais continuer à parcourir la liste
    return b


def present4_corriger(L, e):
    """
    La fonction present4 a un problème dans sa boucle while. La variable b est initialisée à False,
    et la condition while vérifie à la fois i < len(L) et b. Cela signifie que si b est False dès le début,
    la boucle ne s'exécute pas du tout, ce qui n'est probablement pas l'intention. De plus,
    il manque l'incrémentation de i,
    ce qui peut entraîner une boucle infinie si l'élément n'est pas trouvé.

    Args:
        L (list): La liste à vérifier.
        e: L'élément à rechercher dans la liste.

    Returns:
        bool: True si e est présent dans la liste, False sinon.
    """
    b = False
    i = 0
    while i < len(L) and not b:  # Utilisation de "not" pour éviter une boucle infinie
        if L[i] == e:
            b = True
        i += 1  # Incrémentation de i pour passer à l'élément suivant
    return b


#----------------------TEST-------------------------------
def test_present(present):
    # Test avec une liste vide
    if not present([], 42):
        print("SUCCES : test liste vide")
    else:
        print("ECHEC : test liste vide")

    # Test avec une liste de 10 valeurs
    L = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    # Test avec un entier situé en début de liste
    if present(L, 1):
        print("SUCCES : test debut")
    else:
        print("ECHEC : test debut")

    # Test avec un entier situé en fin de liste
    if present(L, 10):
        print("SUCCES : test fin")
    else:
        print("ECHEC : test fin")

    # Test avec un entier situé en milieu de liste
    if present(L, 5):
        print("SUCCES : test milieu")
    else:
        print("ECHEC : test milieu")

    # Test avec un entier non présent dans la liste
    if not present(L, 42):
        print("SUCCES : test absence")
    else:
        print("ECHEC : test absence")


test_present(present)
#test_present(present1)
#test_present(present2)
#test_present(present3)
#test_present(present4)

#test_present(present2_corriger)
#test_present(present3_corriger)
#test_present(present4_corriger)

#---------------------------PARTIE2----------------------------------

def pos(L, e):
    positions = []
    for i in range(len(L)):
        if L[i] == e:
            positions.append(i)
    return positions

def pos1(L, e):
    """
    Erreur : Cette fonction renvoie les indices sous forme d'une liste, mais elle renvoie également
    l'ensemble des éléments de la liste L. De plus, elle ne renvoie pas les positions dans l'ordre.

    Args:
        L (list): La liste dans laquelle rechercher l'élément.
        e: L'élément à rechercher dans la liste.

    Returns:
        list: Une liste contenant les indices des occurrences de l'élément e dans L.
    """
    Lres = list(L)
    for i in range(len(L)):
        if L[i] == e:
            Lres += [i]
    return Lres

def pos2(L, e):
    """
    Erreur : Cette fonction modifie la liste Lres en remplaçant les éléments non trouvés par leurs indices,
    ce qui n'est pas le comportement attendu.

    Args:
        L (list): La liste dans laquelle rechercher l'élément.
        e: L'élément à rechercher dans la liste.

    Returns:
        list: Une liste contenant les indices des occurrences de l'élément e dans L.
    """
    Lres = list(L)
    for i in range(len(L)):
        if L[i] == e:
            Lres[i] = i
    return Lres

def pos3(L, e):
    """
    Erreur : Cette fonction crée une liste de zéros de longueur égale au nombre d'occurrences de l'élément,
    puis ajoute les indices des occurrences à cette liste. Cependant, elle ne renvoie pas les positions dans l'ordre.

    Args:
        L (list): La liste dans laquelle rechercher l'élément.
        e: L'élément à rechercher dans la liste.

    Returns:
        list: Une liste contenant les indices des occurrences de l'élément e dans L.
    """
    nb = L.count(e)
    Lres = [0] * nb
    for i in range(len(L)):
        if L[i] == e:
            Lres.append(i)
    return Lres

def pos4(L, e):
    """
    Erreur : Cette fonction crée une liste de zéros de longueur égale au nombre d'occurrences de l'élément,
    puis remplit cette liste avec les indices des occurrences. Cependant, elle ne renvoie pas les positions dans l'ordre.

    Args:
        L (list): La liste dans laquelle rechercher l'élément.
        e: L'élément à rechercher dans la liste.

    Returns:
        list: Une liste contenant les indices des occurrences de l'élément e dans L.
    """
    nb = L.count(e)
    Lres = [0] * nb
    j = 0
    for i in range(len(L)):
        if L[i] == e:
            Lres[j] = i
    return Lres


#----------------------TEST-------------------------------
def test_pos(fonctionPos):
    # Test avec une liste vide
    L = []
    resultat = fonctionPos(L, 42)
    if resultat == []:
        print("SUCCES : test liste vide")
    else:
        print("ECHEC : test liste vide")

    # Test avec une liste contenant des occurrences de l'élément
    L = [3, 4, 5, 7, 2, 7]
    resultat = fonctionPos(L, 7)
    if resultat == [3, 5]:
        print("SUCCES : test présence multiple")
    else:
        print("ECHEC : test présence multiple")

    # Test avec une liste ne contenant pas l'élément
    resultat = fonctionPos(L, 42)
    if resultat == []:
        print("SUCCES : test absence")
    else:
        print("ECHEC : test absence")


test_pos(pos)
#test_pos(pos1)
#test_pos(pos2)
#test_pos(pos3)
#test_pos(pos4)


