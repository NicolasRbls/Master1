#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int key;
    char value[256];
} KeyValuePair;

void addKeyValuePair(KeyValuePair dictionary[], const char* value, int *nextKey, int maxSize) {
    if (*nextKey < maxSize) {
        dictionary[*nextKey].key = *nextKey;
        strcpy(dictionary[*nextKey].value, value);
        (*nextKey)++;
    } else {
        printf("Dictionnaire plein!\n");
    }
}

void printDictionary(KeyValuePair dictionary[], int size) {
    for (int i = 0; i < size; i++) {
        printf("Cle: %d, Valeur: %s\n", dictionary[i].key, dictionary[i].value);
    }
}

int main() {
    int maxSize, nextKey = 0, choice;
    char input[256];

    printf("Entrez la taille maximale du dictionnaire: ");
    scanf("%d", &maxSize);
    getchar(); // pour capturer le caractère newline après la saisie du nombre

    KeyValuePair *dictionary = malloc(maxSize * sizeof(KeyValuePair));

    if (dictionary == NULL) {
        printf("Erreur d'allocation de mémoire.\n");
        return 1;
    }

    while (1) {
        printf("\nChoisissez une option :\n");
        printf("1. Ajouter une valeur\n");
        printf("2. Afficher\n");
        printf("3. Quitter\n");
        printf("Option: ");
        scanf("%d", &choice);
        getchar(); // pour capturer le caractère newline après la saisie du nombre

        switch (choice) {
            case 1:
                printf("Entrez une valeur: ");
                fgets(input, 256, stdin);
                input[strcspn(input, "\n")] = 0; // enlever le newline à la fin
                addKeyValuePair(dictionary, input, &nextKey, maxSize);
                break;
            case 2:
                printDictionary(dictionary, nextKey);
                break; // Ajoutez ce break ici
            case 3:
                free(dictionary);
                return 0;
            default:
                printf("Option invalide. Reessayez.\n");
        }
    }

    free(dictionary); // au cas où on sortirait de la boucle par un autre moyen
    return 0;
}
