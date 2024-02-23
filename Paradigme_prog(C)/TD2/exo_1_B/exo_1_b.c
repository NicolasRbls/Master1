#include "util_rand.h"
#include <stdlib.h>
#include <time.h>

// Initialisation du générateur aléatoire.
void initRand() {
    srand(time(NULL));
}

int randMax() {
    return rand();
}

int randUpTo(int high) {
    return rand() % (high + 1);
}

int randBetween(int low, int high) {
    return low + rand() % (high - low + 1);
}

double randReal() {
    return (double)rand() / (double)RAND_MAX;
}

double randRealPrecise(double low, double high) {
    double scale = rand() / (double)RAND_MAX;
    return low + scale * (high - low);
}

// N'oubliez pas d'appeler initRand() au début de votre programme principal pour initialiser le générateur de nombres aléatoires.
