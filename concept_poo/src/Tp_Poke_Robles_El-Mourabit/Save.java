import java.io.*;
import java.util.Scanner;

public class Save {

    public static void serialiserDresseur(Dresseur dresseur) {
        String cheminFichier = "C:\\Users\\n" + //
                "icol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\Tp_Poke_Robles_El-Mourabit\\"+dresseur.getNom() + ".ser"; // Crée un nom de fichier basé sur le nom du dresseur
        File fichier = new File(cheminFichier);

        // Vérifie si le fichier existe déjà
        if (fichier.exists()) {
            System.out.println("Un dresseur nommé " + dresseur.getNom() + " existe déjà. Voulez-vous écraser la sauvegarde ? (oui/non)");
            Scanner scanner = new Scanner(System.in);
            String reponse = scanner.nextLine();

            if (!reponse.equalsIgnoreCase("oui")) {
                System.out.println("Sauvegarde annulée.");
                return;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(dresseur);
            System.out.println("Le dresseur " + dresseur.getNom() + " a été sérialisé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dresseur deserialiserDresseur(String nomDresseur) {
        String cheminFichier ="C:\\Users\\n" + //
                "icol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\Tp_Poke_Robles_El-Mourabit\\"+ nomDresseur + ".ser"; // Utilise le nom du dresseur pour trouver le fichier
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cheminFichier))) {
            Object objetDeserialise = ois.readObject();
            if (objetDeserialise instanceof Dresseur) {
                System.out.println("Le dresseur a été désérialisé avec succès !");
                return (Dresseur) objetDeserialise;
            } else {
                System.out.println("Le fichier ne contient pas un objet de type Dresseur.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Aucune sauvegarde trouvée pour " + cheminFichier);
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
