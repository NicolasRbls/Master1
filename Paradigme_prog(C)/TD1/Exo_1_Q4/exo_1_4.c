#include <stdio.h>

int main() {
    char char1, char2, char3;

    // Demande à l'utilisateur de saisir trois caractères
    printf("Veuillez saisir trois caractères : ");

    // Utilisation de scanf pour lire les trois caractères entrés par l'utilisateur
    scanf(" %c %c %c", &char1, &char2, &char3);

    // Affichage des caractères saisis par l'utilisateur
    printf("Les caractères saisis sont : %c, %c et %c.\n", char1, char2, char3);

    return 0;
}
