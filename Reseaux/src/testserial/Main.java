package testserial;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Entity2D ent_1 = new Entity2D("test1", 0.0f, 0.0f);
        ent_1.putItem(5);
        ent_1.putItem(7);
        ent_1.putItem(-1);
        
        ObjectOutputStream oos = null;

        // Writing into a file
        try {
            FileOutputStream fichier = new FileOutputStream("donnees.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(ent_1);
            oos.flush();
            oos.close();
            fichier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // How long is the file
        File saved = new File("donnees.ser");
        System.out.println("Taille du fichier: " + saved.length() + " octets");

        // Deserializing the object
        Entity2D deserializedEntity = null;
        try {
            FileInputStream fichierIn = new FileInputStream("donnees.ser");
            ObjectInputStream ois = new ObjectInputStream(fichierIn);
            deserializedEntity = (Entity2D) ois.readObject();
            ois.close();
            fichierIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Just to check if the deserialization worked:
        if (deserializedEntity != null) {
            System.out.println("Deserialized Entity Name: " + deserializedEntity.getName());
        }
        System.out.println(deserializedEntity);
    }
}