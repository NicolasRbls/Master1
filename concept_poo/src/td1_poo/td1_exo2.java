package td1_poo;

import java.util.Scanner;

public class td1_exo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Veuillez entrer un nombre : ");
        int number = scanner.nextInt();
        
        printMultiplicationTable(number);
    }

    
        
public static void printMultiplicationTable(int number) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }
}


