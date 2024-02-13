#include <stdio.h>

// Définition de la macro pscanf()
#define pscanf(message, format, data) \
    do { \
        printf("%s", message); \
        scanf(format, data); \
    } while (0)

int main() {
    int age;
    float poids;

    // Utilisation de la macro pscanf() pour demander l'âge de l'utilisateur
    pscanf("Veuillez entrer votre âge : ", "%d", &age);

    // Utilisation de la macro pscanf() pour demander le poids de l'utilisateur
    pscanf("Veuillez entrer votre poids en kg : ", "%f", &poids);

    // Affichage des données saisies par l'utilisateur
    printf("Vous avez %d ans et votre poids est %.2f kg.\n", age, poids);

    return 0;
}
