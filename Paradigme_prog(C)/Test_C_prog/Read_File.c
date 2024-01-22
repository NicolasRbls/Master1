#include <stdio.h>
#include <stdlib.h>

int main() {
    char fileName[100];
    FILE *file;
    int count = 0;
    char ch;

    // Demande à l'utilisateur de saisir le nom du fichier
    printf("Entrez le nom du fichier : ");
    scanf("%s", fileName);

    // Ouvrir le fichier en mode lecture
    file = fopen(fileName, "r");

    // Vérifier si le fichier est ouvert avec succès
    if (file == NULL) {
        printf("Impossible d'ouvrir le fichier %s\n", fileName);
        exit(1);
    }

    // Lire le fichier caractère par caractère
    while ((ch = fgetc(file)) != EOF) {
        if (ch != '\n' && ch != '\r') {
            count++;
        }
    }

    // Fermer le fichier
    fclose(file);

    // Afficher le nombre de caractères
    printf("Le nombre total de caractères est : %d\n", count);

    return 0;
}
