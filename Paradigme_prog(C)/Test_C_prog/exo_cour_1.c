#include <stdio.h>
#include <stdlib.h>

int main() {
    int a = 10;
    int b = 20;
    void *ptr1 = &a;
    void *ptr2 = &b;
    void *temp = malloc(sizeof(int)); 

    printf("Avant :\n");
    printf("a = %d\n", *((int *)ptr1));
    printf("b = %d\n", *((int *)ptr2));


    *((int *)temp) = *((int *)ptr1);

    *((int *)ptr1) = *((int *)ptr2);

    *((int *)ptr2) = *((int *)temp);

    free(temp);

    printf("\nApres :\n");
    printf("a = %d\n", *((int *)ptr1));
    printf("b = %d\n", *((int *)ptr2));

    return 0;
}
