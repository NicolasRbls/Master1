#include <stdio.h>
#include <math.h>

// Prototype de la fonction que nous allons chercher à zéroter
typedef double (*Function)(double);

// Fonction pour trouver le zéro d'une fonction croissante
double findZero(Function f, double a, double b, double epsilon) {
    double mid;
    while ((b - a) > epsilon) {
        mid = (a + b) / 2.0;
        if (f(mid) == 0.0) {
            // Le zéro exact a été trouvé
            return mid;
        } else if (f(mid) < 0.0) {
            // Le zéro est dans la moitié droite
            a = mid;
        } else {
            // Le zéro est dans la moitié gauche
            b = mid;
        }
    }
    // Retourne la valeur médiane lorsque la différence est inférieure à epsilon
    return (a + b) / 2.0;
}

double exampleFunction(double x) {
    // Fonction complexe combinant des termes polynomiaux, exponentiels et trigonométriques
    return x*x - 4*x + exp(-x) - cos(x);
}


int main() {
    double a = 4.0, b = 6.0, epsilon = 0.001;
    double zero = findZero(exampleFunction, a, b, epsilon);
    printf("Le zero de la fonction est approximativement: %f\n", zero);
    return 0;
}
