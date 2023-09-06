import random

def get_valid_choice(player_name):
    while True:
        choice = input(f"{player_name}, faites votre choix parmi (pierre, papier, ciseaux, puit): ").lower()
        if choice in ['pierre', 'papier', 'ciseaux','puit']:
            return choice
        else:
            print("Je n'ai pas compris votre réponse. Veuillez choisir entre pierre, papier et ciseaux.")

def play_game():
    ChoixJoueur = input("Voulez-vous jouer contre l'ordinateur (Max 5 parties) O/N ? ").upper()
    while ChoixJoueur not in ['O', 'N']:
        print("Je n'ai pas compris votre réponse.")
        ChoixJoueur = input("Voulez-vous jouer contre l'ordinateur (Max 5 parties) O/N ? ").upper()

    NomJoueur1 = input("Quel est votre nom ? ")
    print(f"Bienvenu {NomJoueur1}, nous allons jouer ensemble")

    if ChoixJoueur == 'N':
        NomJoueur2 = input("Quel est le nom du deuxième joueur ? ")
        print(f"Bienvenu {NomJoueur2}, nous allons jouer ensemble")
    else:
        NomJoueur2 = 'Machine'

    ScoreJoueur1, ScoreJoueur2 = 0, 0
    NombrePartie = 0
    play_again = True

    while NombrePartie < 5 and play_again:
        NombrePartie += 1

        ChoixJoueur1 = get_valid_choice(NomJoueur1)

        if NomJoueur2 == 'Machine':
            ChoixJoueur2 = random.choice(['pierre', 'papier', 'ciseaux','puit'])
        else:
            ChoixJoueur2 = get_valid_choice(NomJoueur2)

        print(f"Récapitulatif : {NomJoueur1} choisit {ChoixJoueur1} et {NomJoueur2} choisit {ChoixJoueur2}\n")

        if ChoixJoueur1 == ChoixJoueur2:
            winner = "aucun de vous, vous êtes ex æquo"
        elif ((ChoixJoueur1 == 'pierre' and (ChoixJoueur2 == 'ciseaux' or ChoixJoueur2 == 'puit')) or
              (ChoixJoueur1 == 'papier' and (ChoixJoueur2 == 'pierre' or ChoixJoueur2 == 'puit')) or
              (ChoixJoueur1 == 'ciseaux' and (ChoixJoueur2 == 'papier' or ChoixJoueur2 == 'puit')) or
              (ChoixJoueur1 == 'puit' and (ChoixJoueur2 == 'pierre' or ChoixJoueur2 == 'ciseaux'))):
            winner = NomJoueur1
            ScoreJoueur1 += 1
        else:
            winner = NomJoueur2
            ScoreJoueur2 += 1

        print(f"Le gagnant est {winner}")
        print(f"Les scores à l'issue de cette manche sont donc {NomJoueur1}: {ScoreJoueur1} et {NomJoueur2}: {ScoreJoueur2}\n")

        if NombrePartie < 5:
            play_again = input(f"Souhaitez-vous refaire une partie {NomJoueur1} contre {NomJoueur2} ? (O/N) ").upper() == 'O'
            if not play_again:
                print("Merci d'avoir joué ! A bientôt")

def main():
    play_game()
if __name__ == '__main__':
    main()


