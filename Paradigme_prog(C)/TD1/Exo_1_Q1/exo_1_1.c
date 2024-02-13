#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    // Vérification du nombre d'arguments
    if (argc < 3) {
        printf("Wrong usage, at least 2 parameters expected:\n%s param1 param2\n", argv[0]);
        return 1;
    }

    // Initialisation de la somme
    int sum = 0;

    // Parcours des arguments à partir du deuxième (argv[0] est le nom du programme)
    for (int i = 1; i < argc; i++) {
        // Conversion de chaque argument en entier
        int num = atoi(argv[i]);

        // Vérification si la conversion a réussi
        if (num == 0 && argv[i][0] != '0') {
            printf("There is a problem with args %d, %s. It could not be transformed in int. Please retry !\n", i, argv[i]);
            return 1;
        }

        // Addition du nombre à la somme
        sum += num;
    }

    // Affichage de la somme obtenue
    printf("%d\n", sum);

    return 0;
}
