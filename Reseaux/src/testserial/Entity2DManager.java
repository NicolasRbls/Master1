package testserial;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Entity2DManager {
    
    // Génère une liste d'entités
    public static List<Entity2D> generateEntities(int count) {
        List<Entity2D> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add(new Entity2D("Entity" + i, i * 1.0f, i * 2.0f));
        }
        return entities;
    }

    // Sérialise la liste d'entités vers un fichier
    public static void serializeEntitiesToFile(List<Entity2D> entities, String filename) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filename))) {
            // Write the number of entities first
            dataOutputStream.writeInt(entities.size());
            for (Entity2D entity : entities) {
                entity.toBytes(dataOutputStream);
            }
        }
    }

    // Désérialise une liste d'entités depuis un fichier
    public static List<Entity2D> deserializeEntitiesFromFile(String filename) throws IOException {
        List<Entity2D> entities = new ArrayList<>();

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filename))) {
            int count = dataInputStream.readInt();  // Read the number of entities
            for (int i = 0; i < count; i++) {
                entities.add(Entity2D.fromBytes(dataInputStream));
            }
        }

        return entities;
    }

    // Affiche toutes les entités de la liste
    public static void printAllEntities(List<Entity2D> entities) {
        for (Entity2D entity : entities) {
            System.out.println(entity.toString());
        }
    }

    public static long getFileSizeInBytes(String filename) throws IOException {
        return Files.size(Paths.get(filename));
    }


    public static void main(String[] args) {
        try {
            // Génère 1000 entités
            List<Entity2D> entities = generateEntities(1000);

            // Affiche toutes les entités
            //printAllEntities(entities);

            // Sérialise les entités vers un fichier
            String filename = "entities.dat";
            serializeEntitiesToFile(entities, filename);
            System.out.println("Entités sérialisées dans un fichier : " + filename);

            // Affiche la taille du fichier en octets
            long sizeInBytes = getFileSizeInBytes(filename);
            System.out.println("Taille du fichier: " + sizeInBytes/8 + " octet");

            // Désérialise le fichier en entités
            List<Entity2D> deserializedEntities = deserializeEntitiesFromFile(filename);
            System.out.println("Entités désérialisées du fichier. Nombre : " + deserializedEntities.size());

            // Affiche toutes les entités désérialisées (optionnel, pour vérifier)
            //printAllEntities(deserializedEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

