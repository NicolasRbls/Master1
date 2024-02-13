#include <stdio.h>

// Fonction pour chiffrer un mot avec une clé donnée
void encrypt(char *word, int key) {
    for (int i = 0; word[i] != '\0'; i++) {
        // Ne chiffre que les lettres majuscules
        if (word[i] >= 'A' && word[i] <= 'Z') {
            word[i] = ((word[i] - 'A') + key) % 26 + 'A';
        }
        // Ne chiffre que les lettres minuscules
        else if (word[i] >= 'a' && word[i] <= 'z') {
            word[i] = ((word[i] - 'a') + key) % 26 + 'a';
        }
    }
}

// Fonction pour déchiffrer un mot chiffré avec une clé donnée
void decrypt(char *word, int key) {
    // Pour déchiffrer, on utilise une clé négative
    key = -key;
    encrypt(word, key);
}

int main() {
    char word[100];
    int key;

    // Demande à l'utilisateur de saisir le mot et la clé de chiffrement
    printf("Entrez le mot à chiffrer : ");
    scanf("%s", word);
    printf("Entrez la clé de chiffrement : ");
    scanf("%d", &key);

    // Chiffre le mot saisi par l'utilisateur
    encrypt(word, key);

    // Affiche le mot chiffré
    printf("Mot chiffré : %s\n", word);

    // Déchiffre le mot chiffré
    decrypt(word, key);

    // Affiche le mot déchiffré
    printf("Mot déchiffré : %s\n", word);

    return 0;
}
