#include <stdio.h>

void saisirTableau(float tableau[], int taille) {
    for (int i = 0; i < taille; i++) {
        printf("Entrez l'element %d : ", i + 1);
        scanf("%f", &tableau[i]);
    }
}

float sommeTableau(float tableau[], int taille) {
    float somme = 0;
    for (int i = 0; i < taille; i++) {
        somme += tableau[i];
    }
    return somme;
}

float moyenneTableau(float tableau[], int taille) {
    return sommeTableau(tableau, taille) / taille;
}

void triTableau(float tableau[], int taille) {
    for (int i = 0; i < taille - 1; i++) {
        for (int j = i + 1; j < taille; j++) {
            if (tableau[i] > tableau[j]) {
                float temp = tableau[i];
                tableau[i] = tableau[j];
                tableau[j] = temp;
            }
        }
    }
}

void afficherTableau(float tableau[], int taille) {
    printf("Tableau : ");
    for (int i = 0; i < taille; i++) {
        printf("%.2f ", tableau[i]);
    }
    printf("\n");
}

int main() {
    int choix, taille;
    printf("Entrez la taille du tableau : ");
    scanf("%d", &taille);

    float tableau[taille];
    saisirTableau(tableau, taille);

    do {
        printf("\n---- Menu ----\n");
        printf("1. Somme du tableau\n");
        printf("2. Moyenne du tableau\n");
        printf("3. Tri du tableau\n");
        printf("4. Quitter\n");
        printf("----------------\n");
        printf("Entrez votre choix : ");
        scanf("%d", &choix);

        switch (choix) {
            case 1:
                printf("\nLa somme des elements du tableau est : %.2f\n", sommeTableau(tableau, taille));
                break;
            case 2:
                printf("\nLa moyenne des elements du tableau est : %.2f\n", moyenneTableau(tableau, taille));
                break;
            case 3:
                triTableau(tableau, taille);
                printf("\nTableau trie en ordre croissant : ");
                afficherTableau(tableau, taille);
                break;
            case 4:
                printf("\nFin du programme.\n");
                break;
            default:
                printf("\nChoix invalide. Veuillez réessayer.\n");
        }
    } while (choix != 4);

    return 0;
}
