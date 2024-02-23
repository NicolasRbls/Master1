#ifndef UTIL_RAND_H
#define UTIL_RAND_H

// Génère un nombre entier aléatoire selon la plage maximum du générateur.
int randMax();

// Génère un nombre entier aléatoire entre 0 et une valeur seuil haut.
int randUpTo(int high);

// Génère un nombre entier aléatoire entre un seuil bas et un seuil haut.
int randBetween(int low, int high);

// Génère un nombre réel aléatoire entre 0 et 1.
double randReal();

// Génère un nombre réel aléatoire à deux décimales entre un seuil bas et un seuil haut.
double randRealPrecise(double low, double high);

#endif
