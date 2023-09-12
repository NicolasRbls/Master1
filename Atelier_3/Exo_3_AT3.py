import random
def places_lettre(ch: str, mot: str) -> list:
    """
    Recherche si le caractère ch est présent dans la chaîne mot et renvoie une liste
    des indices désignant sa place dans le mot. Si le caractère n'est pas présent,
    renvoie une liste vide.

    Args:
        ch (str): Le caractère à rechercher.
        mot (str): La chaîne de caractères dans laquelle rechercher.

    Returns:
        list: Une liste d'indices où le caractère ch est présent dans le mot.
    """
    indices = []
    for i, lettre in enumerate(mot):
        if lettre == ch:
            indices.append(i)
    return indices

def outputStr(mot: str, lpos: list) -> str:
    """
    Renvoie une chaîne de caractères comprenant tous les caractères de la chaîne mot
    ou certains caractères remplacés par des tirets. Les positions des caractères à afficher
    sont spécifiées dans la liste lpos.

    Args:
        mot (str): La chaîne de caractères d'origine.
        lpos (list): Une liste d'entiers représentant les indices des caractères de mot à afficher.

    Returns:
        str: Une chaîne de caractères avec des tirets et les caractères à afficher.
    """
    resultat = []
    for i in range(len(mot)):
        if i not in lpos:
            resultat.append('_')
        else:
            resultat.append(mot[i])
    return ' '.join(resultat)



def runGame():
    """
        Joue au jeu du pendu.

        Le jeu choisit aléatoirement un mot parmi une liste prédéfinie et demande à l'utilisateur de deviner
        les lettres du mot. L'utilisateur a un certain nombre d'erreurs avant de perdre la partie.

        Returns:
            None
    """
    lst = ['paris', 'londres', 'madrid', 'berlin', 'new-york']
    sertif_indices=[]
    mot_secret = random.choice(lst)
    print(mot_secret)
    erreurs = 0
    erreurs_max = 5

    while erreurs < erreurs_max:
        lettre = input("Devinez une lettre : ")
        indices = places_lettre(lettre, mot_secret)

        if indices:
            for indice in indices:
                sertif_indices.append(indice)
        else:
            erreurs += 1

        print(outputStr(mot_secret, sertif_indices))
        print("Erreurs :", erreurs, "/", erreurs_max)

        # Dessin du pendu en fonction du nombre d'erreurs
        if erreurs == 1:
            print("|______")
        elif erreurs == 2:
            print("|/ \\")
        elif erreurs == 3:
            print("| T")
        elif erreurs == 4:
            print("| O")
        elif erreurs == 5:
            print("|---]")

        if '_' not in outputStr(mot_secret, sertif_indices):
            print("Félicitations, vous avez trouvé le mot :", mot_secret," en ",erreurs," erreurs")
            break

    if erreurs == erreurs_max:
        print("Désolé, vous avez atteint le nombre maximum d'erreurs. Le mot était :", mot_secret)




#-------------------------------TEST----------------------------

def test_places_lettre():
    lettre = input("Entrez une lettre : ")
    mot = input("Entrez un mot : ")
    resultat = places_lettre(lettre, mot)

    if resultat: #resultat est une liste , equivalent resultat != []
        print(f"La lettre '{lettre}' se trouve aux positions : {resultat}")
    else:
        print(f"La lettre '{lettre}' n'est pas présente dans le mot '{mot}'.")

"""
# Appeler la fonction de test
test_places_lettre()
# Exemples d'utilisation
print(outputStr('bonjour', []))       # '_ _ _ _ _ _ _'
print(outputStr('bonjour', [0]))      # 'b _ _ _ _ _ _'
print(outputStr('bonjour', [0, 1, 4])) # 'b o _ _ o _ _'
print(outputStr('bon', [0, 1, 2]))    # 'b o n'
print(outputStr('maman', [1, 3]))     # '_ a _ a _'
"""

runGame()


