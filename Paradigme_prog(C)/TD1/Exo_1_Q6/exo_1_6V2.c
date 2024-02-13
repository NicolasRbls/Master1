#include <stdio.h>
#include <ncurses.h>

// Fonction pour chiffrer un caractère avec une clé donnée
char encryptChar(char character, int key) {
    // Chiffrement des lettres majuscules
    if (character >= 'A' && character <= 'Z') {
        return ((character - 'A') + key) % 26 + 'A';
    }
    // Chiffrement des lettres minuscules
    else if (character >= 'a' && character <= 'z') {
        return ((character - 'a') + key) % 26 + 'a';
    }
    // Ne chiffre pas les autres caractères
    else {
        return character;
    }
}

int main() {
    int character;
    int key;

    // Initialisation de ncurses
    initscr();

    // Désactive l'affichage des caractères saisis
    noecho();

    // Demande à l'utilisateur de saisir la clé de chiffrement
    printw("Entrez la cle de chiffrement : ");
    scanw("%d", &key);

    // Affiche le message d'invite pour saisir le mot
    printw("Entrez le mot a chiffrer (appuyez sur Enter pour terminer) : ");
    refresh();

    // Chiffre et affiche chaque caractère du mot au fur et à mesure de la saisie
    while ((character = getch()) != '\n' && character != EOF) {
        printw("%c", encryptChar(character, key));
        refresh();
    }

    // Restaure les paramètres de la console
    endwin();

    return 0;
}
