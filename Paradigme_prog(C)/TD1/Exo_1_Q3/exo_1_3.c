#include <stdio.h>

int main() {
    // Déclaration de la variable pour stocker l'âge
    int age;

    // Demande à l'utilisateur d'entrer son âge
    printf("Veuillez entrer votre age : ");

    // Utilisation de scanf pour lire l'âge entré par l'utilisateur
    scanf("%d", &age);

    // Affichage de l'âge saisi par l'utilisateur
    printf("Vous avez %d ans.\n", age);

    return 0;
}
