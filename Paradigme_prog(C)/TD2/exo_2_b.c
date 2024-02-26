#include "util_rand.h"
#include <stdio.h>

// Déclaration des fonctions
int afficherMenuEtObtenirChoix();
void lancerDes(int nombreDes, int *resultats);
void relancerDesIdentiques(int nombreDes, int *resultats);
int calculerSeuil(int nombreDes);
void afficherResultat(int somme, int seuil);

int main() {
    // Initialisation du générateur aléatoire
    initRand();

    int choix = afficherMenuEtObtenirChoix();
    int resultats[4] = {0}; // Puisque le maximum de dés est 4
    lancerDes(choix, resultats);
    relancerDesIdentiques(choix, resultats);
    int somme = 0;
    for (int i = 0; i < choix; i++) {
        somme += resultats[i];
    }
    int seuil = calculerSeuil(choix);
    afficherResultat(somme, seuil);
    return 0;
}

int afficherMenuEtObtenirChoix() {
    int choix;
    printf("Avec combien de dés voulez vous jouer ?\n");
    printf("Tapez 1, 2, 3 ou 4 ? ");
    scanf("%d", &choix);
    while (choix < 1 || choix > 4) {
        printf("Choix invalide. Veuillez choisir entre 1 et 4 dés.\n");
        scanf("%d", &choix);
    }
    return choix;
}

void lancerDes(int nombreDes, int *resultats) {
    for (int i = 0; i < nombreDes; i++) {
        resultats[i] = randUpTo(6); // Des 1 à 6
        printf("Dé %d : %d\n", i, resultats[i]);
    }
}

void relancerDesIdentiques(int nombreDes, int *resultats) {
    int identiques;
    do {
        identiques = 0;
        for (int i = 0; i < nombreDes - 1; i++) {
            for (int j = i + 1; j < nombreDes; j++) {
                if (resultats[i] == resultats[j]) {
                    printf("Dés identiques relancés...\n");
                    resultats[i] = randUpTo(6);
                    printf("Dé %d : %d\n", i, resultats[i]);
                    identiques = 1;
                }
            }
        }
    } while (identiques);
}

int calculerSeuil(int nombreDes) {
    return (2 * nombreDes * 6) / 3; // Deux tiers du maximum possible
}

void afficherResultat(int somme, int seuil) {
    printf("La somme des dés lancés est de %d, le seuil était de %d\n", somme, seuil);
    if (somme >= seuil) {
        printf("Bravo, vous avez gagné avec un score supérieur de %d au seuil!\n", somme - seuil);
    } else {
        printf("Désolé, vous avez perdu ! Il vous manque %d pour atteindre le seuil.\n", seuil - somme);
    }
}
