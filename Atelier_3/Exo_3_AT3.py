import random
URL_CAPITALES = "../../capitale.txt"
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


def build_list(fileName: str) -> list:
    """
       Construit une liste de mots à partir d'un fichier texte.

       Args:
           fileName (str): Le nom du fichier à partir duquel les mots seront extraits.

       Returns:
           list: Une liste de mots extraits du fichier, convertis en minuscules.
    """
    # Crée une liste vide pour stocker les capitales
    capitales = []

    # Ouvre le fichier en mode lecture
    with open(fileName, 'r') as file:
        # Lit chaque ligne du fichier
        for line in file:
            # Supprime les caractères de saut de ligne et divise la ligne en fonction des tabulations
            mots = line.strip().split('\t')

            # Traitement : Mise en minuscules et ajout à la liste des capitales
            for mot in mots:
                capitales.append(mot.lower())

    return capitales

def build_dict(lst):
    """
       Construit un dictionnaire de mots basé sur leur longueur.

       Args:
           lst (list): La liste de mots à partir de laquelle le dictionnaire sera construit.

       Returns:
           dict: Un dictionnaire où les clés sont les longueurs de mots et les valeurs sont des listes de mots
           ayant la même longueur.
    """
    dictionnaire_mots = {}  # Crée un dictionnaire vide

    for mot in lst:
        longueur = len(mot)
        if longueur not in dictionnaire_mots:
            dictionnaire_mots[longueur] = []  # Initialise la liste si elle n'existe pas encore
        dictionnaire_mots[longueur].append(mot)  # Ajoute le mot à la liste correspondante

    return dictionnaire_mots

def select_word(sorted_words, word_len):
    """
       Sélectionne un mot aléatoire d'une certaine longueur à partir d'un dictionnaire de mots.

       Args:
           sorted_words (dict): Le dictionnaire de mots triés par longueur.
           word_len (int): La longueur du mot recherché.

       Returns:
           str: Un mot aléatoire de la longueur spécifiée ou None si la longueur n'est pas présente dans le
           dictionnaire.
    """
    retour = None
    if word_len in sorted_words:
        mots_de_la_taille = sorted_words[word_len]
        retour= random.choice(mots_de_la_taille)
    return retour # Retourne None si la longueur n'est pas présente dans le dictionnaire


def runGame():
    """
        Joue au jeu du pendu.

        Le jeu choisit aléatoirement un mot parmi une liste prédéfinie en fonction du niveau de difficulté
        sélectionné par l'utilisateur et demande à l'utilisateur de deviner les lettres du mot. L'utilisateur a un
        certain nombre d'erreurs avant de perdre la partie.

        Returns:
            None
    """
    niveaux = {
        'easy': (1, 6),  # Niveau facile : mots de 1 à 6 lettres
        'normal': (7, 8),  # Niveau normal : mots de 7 à 8 lettres
        'hard': (9, 19)  # Niveau difficile : mots de 9 lettres a 19 qui est le plus grand mot de ma liste
    }

    while True:
        niveau = input("Choisissez un niveau (easy, normal, hard) ou quit pour quitter : ").lower()
        if niveau == 'quit':
            break
        elif niveau in niveaux:
            niv_choisi = random.randint(niveaux[niveau][0],niveaux[niveau][1])
            liste_mot = build_list(URL_CAPITALES)
            dict_mot = build_dict(liste_mot)
            mot_secret = select_word(dict_mot,niv_choisi)
            if not mot_secret:
                print("Aucun mot disponible pour ce niveau.")
                continue

            erreurs = 0
            erreurs_max = 5
            sertif_indices = []
            sortie = True

            # Affichage du mot pour la première fois
            print(outputStr(mot_secret, sertif_indices))

            while erreurs < erreurs_max and sortie:
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
                    print("|______")
                elif erreurs == 3:
                    print("| T")
                    print("|/ \\")
                    print("|______")
                elif erreurs == 4:
                    print("| O")
                    print("| T")
                    print("|/ \\")
                    print("|______")
                elif erreurs == 5:
                    print("|---]")
                    print("| O")
                    print("| T")
                    print("|/ \\")
                    print("|______")

                if '_' not in outputStr(mot_secret, sertif_indices):
                    print("Félicitations, vous avez trouvé le mot :", mot_secret, " en ", erreurs, " erreurs")
                    sortie = False

            if erreurs == erreurs_max:
                print("Désolé, vous avez atteint le nombre maximum d'erreurs. Le mot était :", mot_secret)
        else:
            print("Niveau invalide. Veuillez choisir parmi les niveaux disponibles (easy, normal, hard) ou quit.")


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


if __name__ == "__main__":
    runGame()
