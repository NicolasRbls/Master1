#include <stdio.h>

int main() {
    int n, i;
    float num, somme = 0.0;

    printf("Entrez le nombre d'elements: ");
    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        printf("Entrez un nombre: ");
        scanf("%f", &num);
        somme += num;
    }

    printf("La somme des nombres est: %.2f\n", somme);

    // Attendre une entrée clavier avant de fermer le programme
    getchar(); // pour consommer le '\n' du dernier scanf
    getchar(); // pour attendre une nouvelle entrée

    return 0;
}
