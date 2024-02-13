#include <stdio.h>

void swap_int(int *ptr1, int *ptr2) {
    int temp = *ptr1;
    *ptr1 = *ptr2;
    *ptr2 = temp;
}

int main() {
    int num1 = 5, num2 = 10;

    printf("Avant l'échange : num1 = %d, num2 = %d\n", num1, num2);

    swap_int(&num1, &num2);

    printf("Après l'échange : num1 = %d, num2 = %d\n", num1, num2);

    return 0;
}
