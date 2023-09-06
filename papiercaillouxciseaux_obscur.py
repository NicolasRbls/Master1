import random

ChoixJoueur = input("Voulez-vous jouer contre l'ordinateur (Max 5 parties) O/N ? ")
if ChoixJoueur != 'O' :
    if ChoixJoueur != 'N' :
        print("Je n'ai pas compris votre réponse")
if ChoixJoueur == 'O':
    NomJoueur1 = input("Quel est votre nom ? ")
    print("Bienvenu ", NomJoueur1, " nous allons jouer ensemble \n")
    NomJoueur2 = 'Machine'
ScoreJoueur1 = 0
NombrePartie = 0
if ChoixJoueur == 'N':
    NomJoueur1 = input("Quel est votre nom ? ")
    print("Bienvenu ", NomJoueur1, " nous allons jouer ensemble")
    NomJoueur2 = input("Quel est le nom du deuxième joueur ?")
    print("Bienvenu ", NomJoueur2, " nous allons jouer ensemble \n")

StatutJeux = True
ScoreJoueur2 = 0
while StatutJeux == True:
    NombrePartie += 1
    ChoixJoueur1 = input("{nom} faîtes votre choix parmi (pierre, papier, ciseaux): ".format(nom=NomJoueur1))
    #fait 1 if au lieux de 3
    if ChoixJoueur1 != 'pierre' and ChoixJoueur1 != 'papier' and ChoixJoueur1 != 'ciseaux':
        Choix_1_Valide = False
        print("Je n'ai pas compris votre réponse")
        while Choix_1_Valide == False :
            print("Joueur ", NomJoueur1)
            ChoixJoueur1 = input(" faîtes votre choix parmi (pierre, papier, ciseaux): ")
            Choix_1_Valide = True
            if ChoixJoueur1 != 'pierre':
                if ChoixJoueur1 != 'papier':
                    if ChoixJoueur1 != 'ciseaux':
                        Choix_1_Valide = False

    if NomJoueur2 == 'Machine':
        #Ici il faudrait plutôt vérifier que cpo == 'O' autrement
        #il y a un problème si le joueur 2 s'appelle Machine !
        ChoixJoueur2 = ['papier', 'pierre', 'ciseaux'][random.randint(0, 2)]


    if NomJoueur2 != 'Machine':
        print("Joueur", NomJoueur2)
        ChoixJoueur2 = input("faîtes votre choix parmi (pierre, papier, ciseaux): ")
        if ChoixJoueur2 != 'pierre'and ChoixJoueur2 != 'papier' and ChoixJoueur2 != 'ciseaux':
            Choix_2_Valide = False
            print("Je n'ai pas compris votre réponse")
            while not Choix_2_Valide:
                print("Joueur ", NomJoueur2)
                ChoixJoueur2 = input(" faîtes votre choix parmi (pierre, papier, ciseaux): ")
                Choix_2_Valide = True
                if ChoixJoueur2 != 'pierre':
                    if ChoixJoueur2 != 'papier':
                        if ChoixJoueur2 != 'ciseaux':
                            Choix_2_Valide = False

    #On affiche les choix de chacun
    print("Si on récapitule :", NomJoueur1, ChoixJoueur1, "et", NomJoueur2, ChoixJoueur2, "\n")


    #On regarde qui a gagné cette manche on calcule les points et on affiche le résultat
    if ChoixJoueur1 == ChoixJoueur2 :
        Gagnant = "aucun de vous, vous être ex æquo"
        ScoreJoueur1 = ScoreJoueur1 + 0
        ScoreJoueur2 = ScoreJoueur2 + 0
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")

    if ChoixJoueur1 == 'pierre' and ChoixJoueur2 == 'papier' :
        Gagnant = NomJoueur2
        ScoreJoueur1 = ScoreJoueur1 + 0
        ScoreJoueur2 = ScoreJoueur2 + 1
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")


    if ChoixJoueur1 == 'pierre' and ChoixJoueur2 == 'ciseaux' :
        Gagnant = NomJoueur1
        ScoreJoueur1 = ScoreJoueur1 + 1
        ScoreJoueur2 = ScoreJoueur2 + 0
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")

    if ChoixJoueur1 == 'papier' and ChoixJoueur2 == 'ciseaux' :
        Gagnant = NomJoueur2
        ScoreJoueur1 = ScoreJoueur1 + 0
        ScoreJoueur2 = ScoreJoueur2 + 1
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")

    if ChoixJoueur1 == 'papier' and ChoixJoueur2 == 'pierre' :
        Gagnant = NomJoueur1
        ScoreJoueur1 = ScoreJoueur1 + 1
        ScoreJoueur2 = ScoreJoueur2 + 0
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")

    if ChoixJoueur1 == 'ciseaux' and ChoixJoueur2 == 'pierre' :
        Gagnant = NomJoueur2
        ScoreJoueur1 = ScoreJoueur1 + 0
        ScoreJoueur2 = ScoreJoueur2 + 1
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")


    if ChoixJoueur1 == 'ciseaux' and ChoixJoueur2 == 'papier' :
        Gagnant = NomJoueur1
        ScoreJoueur1 = ScoreJoueur1 + 1
        ScoreJoueur2 = ScoreJoueur2 + 0
        print("le gagnant est", Gagnant)
        print("Les scores à l'issue de cette manche sont donc", NomJoueur1, ScoreJoueur1, "et", NomJoueur2, ScoreJoueur2, "\n")

    if NombrePartie ==1 or NombrePartie ==2 or NombrePartie==3 or NombrePartie==4:
        StatutJeux = True
    if NombrePartie ==5:
        StatutJeux = False

     #on peut faire un if sur la var c
    if NombrePartie ==1 or NombrePartie ==2 or NombrePartie==3 or NombrePartie==4:
        #On propose de c ou de s'arrêter 
        Continue = input("Souhaitez vous refaire une partie {} contre {} ? (O/N) ".format(NomJoueur1, NomJoueur2))
        if Continue == 'O':
            StatutJeux = True
        if Continue == 'N':
            StatutJeux = False
        if Continue != 'O' and Continue != 'N':
            StatutJeux = True
            print("Vous ne répondez pas à la question, on continue ")
  
        
if StatutJeux == False :
    print("Merci d'avoir joué ! A bientôt")