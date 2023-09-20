def mots_Nlettres(lst_mot: list, n: int)->list:
    """
    Renvoie la liste des mots de lst_mot qui contiennent exactement n lettres.

    Args:
        lst_mot (list): La liste de mots à filtrer.
        n (int): Le nombre de lettres recherché dans les mots.

    Returns:
        list: La liste des mots contenant exactement n lettres.
    """
    mots_filtrés = []
    for mot in lst_mot:
        if len(mot) == n:
            mots_filtrés.append(mot)
    return mots_filtrés

def commence_par(mot: str, prefixe: str) -> bool:
    """
    Vérifie si le mot commence par le préfixe spécifié.

    Args:
        mot (str): Le mot à vérifier.
        prefixe (str): Le préfixe à rechercher au début du mot.

    Returns:
        bool: True si le mot commence par le préfixe, False sinon.
    """
    return len(mot) >= len(prefixe) and all(mot[i] == prefixe[i] for i in range(len(prefixe)))


def fini_par(mot: str, suffixe: str) -> bool:
    """
    Vérifie si le mot fini par le suffixe spécifié.

    Args:
        mot (str): Le mot à vérifier.
        suffixe (str): Le suffixe à rechercher a la fin du mot.

    Returns:
        bool: True si le mot fini par le suffixe, False sinon.
    """
    return len(mot) >= len(suffixe) and all(mot[-i] == suffixe[-i] for i in range(1, len(suffixe) + 1))

def finissent_par(lst_mot: list, suffixe: str)->list:
    """
    Renvoie la liste des mots de lst_mot qui se terminent par le suffixe spécifié.

    Args:
        lst_mot (list): La liste de mots à filtrer.
        suffixe (str): Le suffixe à rechercher à la fin des mots.

    Returns:
        list: La liste des mots se terminant par le suffixe.
    """
    mots_filtrés = []
    for mot in lst_mot:
        if fini_par(mot,suffixe):
            mots_filtrés.append(mot)
    return mots_filtrés

def commencent_par(lst_mot: list, prefixe: str)->list:
    """
    Renvoie la liste des mots de lst_mot qui commencent par le prefixe spécifié.

    Args:
        lst_mot (list): La liste de mots à filtrer.
        prefixe (str): Le prefixe à rechercher au debut des mots.

    Returns:
        list: La liste des mots commencant par le prefixe.
    """
    mots_filtrés = []
    for mot in lst_mot:
        if commence_par(mot, prefixe):
            mots_filtrés.append(mot)
    return mots_filtrés


def liste_mots(lst_mot: list, prefixe: str, suffixe: str, n: int)->list:
    """
    Renvoie la liste des mots présents dans lst_mot qui commencent par prefixe, se terminent par suffixe,
    et contiennent exactement n lettres.

    Args:
        lst_mot (list): La liste de mots à filtrer.
        prefixe (str): Le préfixe à rechercher au début des mots.
        suffixe (str): Le suffixe à rechercher à la fin des mots.
        n (int): Le nombre de lettres requis dans les mots.

    Returns:
        list: La liste des mots filtrés.
    """
    mots_commencent_par_prefixe = commencent_par(lst_mot, prefixe)
    mots_se_terminent_par_suffixe = finissent_par(mots_commencent_par_prefixe, suffixe)
    mots_exactement_n_lettres = mots_Nlettres(mots_se_terminent_par_suffixe, n)

    return mots_exactement_n_lettres

def dictionnaire(fichier):
    """
    Renvoie la liste des mots présents dans un fichier texte.

    Args:
        fichier (str): Le nom du fichier texte.

    Returns:
        list: La liste des mots lus depuis le fichier.
    """
    mots = []
    try:
        with open(fichier, "r", encoding="utf-8") as file:
            for line in file:
                mot = line.strip()
                mots.append(mot)
        return mots
    except FileNotFoundError:
        print("Le fichier n'a pas été trouvé.")
        return []
    except Exception as e:
        print("Une erreur s'est produite lors de la lecture du fichier :", e)
        return []


#----------------------------TEST-----------------------------

def test_commence_par():
    mot1 = "bonjour"
    prefixe1 = "bon"
    resultat1 = commence_par(mot1, prefixe1)
    if resultat1 == True:
        print("Test commence_par 1 réussi.")
    else:
        print("Échec du test commence_par 1")

    mot2 = "python"
    prefixe2 = "java"
    resultat2 = commence_par(mot2, prefixe2)
    if resultat2 == False:
        print("Test commence_par 2 réussi.")
    else:
        print("Échec du test commence_par 2")


def test_mots_Nlettres():
    lst_mot = ["jouer", "bonjour", "punir", "jour", "aurevoir", "revoir", "pouvoir", "cour", "abajour", "finir",
               "aimer"]

    n1 = 5
    resultat1 = mots_Nlettres(lst_mot, n1)
    if resultat1 == ["jouer", "punir", "finir", "aimer"]:
        print("Test mots_Nlettres 1 réussi.")
    else:
        print("Échec du test mots_Nlettres 1")

    n2 = 6
    resultat2 = mots_Nlettres(lst_mot, n2)
    if resultat2 == ["revoir"]:
        print("Test mots_Nlettres 2 réussi.")
    else:
        print("Échec du test mots_Nlettres 2")

    n3 = 4
    resultat3 = mots_Nlettres(lst_mot, n3)
    if resultat3 == ["jour","cour"]:
        print("Test mots_Nlettres 3 réussi.")
    else:
        print("Échec du test mots_Nlettres 3")

def test_fini_par():
    mot1 = "bonjour"
    suffixe1 = "jour"
    resultat1 = fini_par(mot1, suffixe1)
    if resultat1 == True:
        print("Test fini_par 1 réussi.")
    else:
        print("Échec du test fini_par 1")

    mot2 = "python"
    suffixe2 = "java"
    resultat2 = fini_par(mot2, suffixe2)
    if resultat2 == False:
        print("Test fini_par 2 réussi.")
    else:
        print("Échec du test fini_par 2")

    mot3 = "programmation"
    suffixe3 = "tion"
    resultat3 = fini_par(mot3, suffixe3)
    if resultat3 == True:
        print("Test fini_par 3 réussi.")
    else:
        print("Échec du test fini_par 3")

def test_finissent_par():
    lst_mot1 = ["bonjour", "python", "programmation", "motivation", "pythonic"]
    suffixe1 = "on"
    resultat1 = finissent_par(lst_mot1, suffixe1)
    if resultat1 == ["python", "programmation" ,"motivation"]:
        print("Test finissent_par 1 réussi.")
    else:
        print("Échec du test finissent_par 1")

    lst_mot2 = ["apple", "banana", "cherry", "date", "grape"]
    suffixe2 = "e"
    resultat2 = finissent_par(lst_mot2, suffixe2)
    if resultat2 == ["apple", "date" , "grape"]:
        print("Test finissent_par 2 réussi.")
    else:
        print("Échec du test finissent_par 2")

    lst_mot3 = ["programming", "coding", "debugging", "testing"]
    suffixe3 = "ing"
    resultat3 = finissent_par(lst_mot3, suffixe3)
    if resultat3 == ["programming", "coding", "debugging", "testing"]:
        print("Test finissent_par 3 réussi.")
    else:
        print("Échec du test finissent_par 3")

def test_commencent_par():
    lst_mot1 = ["bonjour", "python", "programmation", "motivation", "pythonic"]
    prefixe1 = "bon"
    resultat1 = commencent_par(lst_mot1, prefixe1)
    if resultat1 == ["bonjour"]:
        print("Test commencent_par 1 réussi.")
    else:
        print("Échec du test commencent_par 1")

    lst_mot2 = ["apple", "banana", "cherry", "date", "grape"]
    prefixe2 = "ban"
    resultat2 = commencent_par(lst_mot2, prefixe2)
    if resultat2 == ["banana"]:
        print("Test commencent_par 2 réussi.")
    else:
        print("Échec du test commencent_par 2")

    lst_mot3 = ["programming", "coding", "debugging", "testing"]
    prefixe3 = "pro"
    resultat3 = commencent_par(lst_mot3, prefixe3)
    if resultat3 == ["programming"]:
        print("Test commencent_par 3 réussi.")
    else:
        print("Échec du test commencent_par 3")

def test_liste_mots():
    lst_mot1 = ["bonjour", "python", "programmation", "motivation", "pythonic"]
    prefixe1 = "bon"
    suffixe1 = "our"
    n1 = 7
    resultat1 = liste_mots(lst_mot1, prefixe1, suffixe1, n1)
    if resultat1 == ["bonjour"]:
        print("Test liste_mots 1 réussi.")
    else:
        print("Échec du test liste_mots 1")

    lst_mot2 = ["apple", "banana", "cherry", "date", "grape"]
    prefixe2 = "ban"
    suffixe2 = "a"
    n2 = 6
    resultat2 = liste_mots(lst_mot2, prefixe2, suffixe2, n2)
    if resultat2 == ["banana"]:
        print("Test liste_mots 2 réussi.")
    else:
        print("Échec du test liste_mots 2")

    lst_mot3 = ["programming", "coding", "debugging", "testing"]
    prefixe3 = "pro"
    suffixe3 = "ing"
    n3 = 11
    resultat3 = liste_mots(lst_mot3, prefixe3, suffixe3, n3)
    if resultat3 == ["programming"]:
        print("Test liste_mots 3 réussi.")
    else:
        print("Échec du test liste_mots 3")



"""
# Appel des fonctions de test
test_commence_par()
test_mots_Nlettres()
test_fini_par()
test_finissent_par()
test_commencent_par()
test_liste_mots()
"""

#utilisation avec un chemin relatif
nom_fichier = "../../littre.txt"
liste_de_mots = dictionnaire(nom_fichier)
print(liste_mots(liste_de_mots,"tra","re",7))

