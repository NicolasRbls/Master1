package td6_poo.exo_1_2_td6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Deserialiser {

    public Personne deserializePersonneFromText(String filePath) {
        Personne personne = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            personne = (Personne) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personne;
    }
}
