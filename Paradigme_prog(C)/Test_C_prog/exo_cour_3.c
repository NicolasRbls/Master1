#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void empoisonner(int *points_de_vie, int *nocivite_poison) {
    *points_de_vie -= *nocivite_poison;

    (*nocivite_poison)--;

    if (*nocivite_poison <= 0) {
        *nocivite_poison = rand() % 20 + 1;
    }
}

int main() {
    srand(time(NULL));

    int points_de_vie = 100;
    int nocivite_poison = rand() % 20 + 1; 

    printf("Points de vie initiaux du joueur : %d\n", points_de_vie);
    printf("Nocivite du poison : %d\n", nocivite_poison);

    while (points_de_vie > 0) {
        printf("Le joueur est empoisonne !\n");
        empoisonner(&points_de_vie, &nocivite_poison);
        printf("Points de vie restants du joueur : %d\n", points_de_vie);
        printf("Nocivite du poison : %d\n", nocivite_poison);


        getchar();
    }

    printf("Le joueur est mort");

    return 0;
}