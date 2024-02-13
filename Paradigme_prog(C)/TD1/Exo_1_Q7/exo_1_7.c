#include <stdio.h>

// Fonction pour convertir un chiffre en chiffre romain pour un rang donné
void convertToRoman(int digit, char one, char five, char ten, char roman[]) {
    switch (digit) {
        case 1:
        case 2:
        case 3:
            for (int i = 0; i < digit; i++) {
                sprintf(roman, "%s%c", roman, one);
            }
            break;
        case 4:
            sprintf(roman, "%s%c", roman, one);
            sprintf(roman, "%s%c", roman, five);
            break;
        case 5:
            sprintf(roman, "%s%c", roman, five);
            break;
        case 6:
        case 7:
        case 8:
            sprintf(roman, "%s%c", roman, five);
            for (int i = 0; i < digit - 5; i++) {
                sprintf(roman, "%s%c", roman, one);
            }
            break;
        case 9:
            sprintf(roman, "%s%c", roman, one);
            sprintf(roman, "%s%c", roman, ten);
            break;
    }
}

// Fonction pour convertir un nombre entier en chiffres romains
void integerToRoman(int num, char roman[]) {
    while (num >= 1000) {
        sprintf(roman, "%sM", roman);
        num -= 1000;
    }
    convertToRoman(num / 100, 'C', 'D', 'M', roman);
    num %= 100;
    convertToRoman(num / 10, 'X', 'L', 'C', roman);
    num %= 10;
    convertToRoman(num, 'I', 'V', 'X', roman);
}

int main() {
    int num;
    char roman[20] = ""; // Tableau pour stocker le chiffre romain

    // Demander à l'utilisateur de saisir le nombre entier
    printf("Entrez un nombre entier : ");
    scanf("%d", &num);

    // Convertir le nombre entier en chiffre romain
    integerToRoman(num, roman);

    // Afficher le résultat
    printf("Le nombre %d en chiffres romains est : %s\n", num, roman);

    return 0;
}
