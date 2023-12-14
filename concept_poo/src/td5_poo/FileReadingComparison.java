package td5_poo;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileReadingComparison {
    public static void main(String[] args) throws IOException {
        // Exercice 1 : Lecture avec FileReader
        /*System.out.println("Lecture avec FileReader:");
        String fileContent = readFileWithFileReader("C:\\Users\\n" + //
                "icol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\td5_poo\\alice29.txt");
         System.out.println(fileContent);*/

        // Exercice 2 : Lecture avec BufferedReader
        /*System.out.println("\nLecture avec BufferedReader:");
        String bufferedContent = readFileWithBufferedReader("C:\\Users\\n" + //
                "icol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\td5_poo\\alice29.txt");
        System.out.println(bufferedContent);*/

        // Exercice 3 : Comparaison des temps d'exécution
        compareExecutionTime("C:\\Users\\nicol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\td5_poo\\alice29.txt");
    }

    private static String readFileWithFileReader(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName)) {
            int character;
            while ((character = fileReader.read()) != -1) {
                content.append((char) character);
            }
        }
        return content.toString();
    }
    
    private static String readFileWithBufferedReader(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    

    private static void compareExecutionTime(String fileName) throws IOException {
        long startTime, endTime;
    
        // Mesure avec FileReader
        startTime = System.currentTimeMillis();
        String contentFileReader = readFileWithFileReader(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("Temps d'exécution avec FileReader: " + (endTime - startTime) + " ms");
    
        // Mesure avec BufferedReader
        startTime = System.currentTimeMillis();
        String contentBufferedReader = readFileWithBufferedReader(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("Temps d'exécution avec BufferedReader: " + (endTime - startTime) + " ms");
    
        // Mesure avec java.nio
        startTime = System.currentTimeMillis();
        String contentNIO = readFileWithNIO(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("Temps d'exécution avec java.nio: " + (endTime - startTime) + " ms");
    }    

    private static String readFileWithNIO(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            content.append(line).append("\n");
        }
        return content.toString();
    }
    
}
