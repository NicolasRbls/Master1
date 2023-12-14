package td6_poo.exo_1_2_td6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialiser {

    public void serializePersonneToText(Personne personne, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(personne);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

