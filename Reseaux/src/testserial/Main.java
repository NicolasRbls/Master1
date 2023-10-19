package testserial;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Entity2D ent_1 = new Entity2D("test1", 0.0f, 0.0f);
        ent_1.putItem(5);
        ent_1.putItem(7);
        ent_1.putItem(-1);
        
        // Serialization using DataOutputStream
        try {
            FileOutputStream fos = new FileOutputStream("donnees_data.ser");
            DataOutputStream dos = new DataOutputStream(fos);
            ent_1.toBytes(dos);
            dos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Check the file size
        File savedData = new File("donnees_data.ser");
        System.out.println("Taille du fichier (DataOutputStream): " + savedData.length() + " octets");

        // Deserialization using DataInputStream
        Entity2D deserializedEntityData = null;
        try {
            FileInputStream fis = new FileInputStream("donnees_data.ser");
            DataInputStream dis = new DataInputStream(fis);
            deserializedEntityData = Entity2D.fromBytes(dis);
            dis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the deserialized object
        System.out.println("Deserialized Entity (DataInputStream): " + deserializedEntityData);
    }
}
