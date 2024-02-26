#include <stdio.h>
#include "util_rand.h" // Assurez-vous que ce fichier est dans le même répertoire ou correctement référencé


int main() {
    // Etape 2: Initialiser le générateur de nombres aléatoires
    initRand(); 

    // Générer et afficher un nombre entier aléatoire maximal
    printf("Nombre entier aleatoire maximal: %d\n", randMax());

    // Générer et afficher un nombre entier aléatoire entre 0 et 100
    printf("Nombre entier aleatoire entre 0 et 100: %d\n", randUpTo(100));

    // Générer et afficher un nombre entier aléatoire entre 50 et 150
    printf("Nombre entier aleatoire entre 50 et 150: %d\n", randBetween(50, 150));

    // Générer et afficher un nombre réel aléatoire entre 0 et 1
    printf("Nombre reel aleatoire entre 0 et 1: %f\n", randReal());

    // Générer et afficher un nombre réel aléatoire à deux décimales entre 1.00 et 2.00
    printf("Nombre reel aleatoire à deux decimales entre 1.00 et 2.00: %.2f\n", randRealPrecise(1.00, 2.00));

    return 0;
}
