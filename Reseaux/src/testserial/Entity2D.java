package testserial;

import java.util.ArrayList;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
//import com.fasterxml.jackson.databind.ObjectMapper;


/*
 int id: Les int en Java ont une taille de 4 octets.
String name: Si nous supposons en moyenne 10 caractères pour la chaîne et sachant qu'un charen Java utilise 2 octets, cela fait 20 octets pour name.
float xet float y: Les float en Java ont une taille de 4 octets chacun.
ArrayList<Integer> items: Pour l'ArrayList, nous considérons que cela coûte 4 octets pour compter le nombre d'éléments. Chaque Integerà l'intérieur coûte 4 octets.
Faisons le total:
id = 4 octets
name = 20 octets
x = 4 octets
y = 4 octets
items = 4 octets (pour le nombre d'éléments) + 4n octets (où n est le nombre d'éléments dans l'ArrayList)
La taille totale en octets pour une instance de Entity2Dserait donc :
T = 36 + 4n octets
 */

public class Entity2D implements Externalizable  {
    private static final long serialVersionUID = 1L;
    public static final int MAX_ITEMS = 10;
    public static int nb_generated = 0;

    private int id;
    private String name;
    private float x;
    private float y;
    private ArrayList<Integer> items;

    // Constructeur sans arguments (nécessaire pour Externalizable)
    public Entity2D() {}

    public Entity2D(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.id = nb_generated;
        nb_generated++;
        this.items = new ArrayList<Integer>();
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    // We don't provide a setter for ID as it is auto-generated

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        if (items.size() <= MAX_ITEMS) {
            this.items = items;
        } else {
            // Throw an exception or handle the case where there are too many items
            System.out.println("Error: Too many items provided. Maximum allowed is " + MAX_ITEMS);
        }
    }

    /*private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }*/

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);  // Utiliser writeUTF pour les chaînes
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeObject(items);  // Écrire l'ArrayList car elle est sérialisable
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        name = in.readUTF();  // Utiliser readUTF pour les chaînes
        x = in.readFloat();
        y = in.readFloat();
        items = (ArrayList<Integer>) in.readObject();  // Lire l'ArrayList
    }
    
    public void putItem(int value) {
        if (items.size() < MAX_ITEMS) {
            items.add(value);
        } else {
            System.out.println("Maximum items reached, cannot add more.");
        }
    }

    public void toBytes(DataOutputStream data) throws IOException {
        // Écrire l'ID
        data.writeInt(id);
    
        // Écrire le nom (avec gestion de la longueur maximale pour être sûr de ne pas dépasser)
        data.writeUTF(name.length() > 10 ? name.substring(0, 10) : name);
    
        // Écrire les coordonnées x et y
        data.writeFloat(x);
        data.writeFloat(y);
    
        // Écrire les éléments de l'ArrayList
        // Commencez par écrire la taille de l'ArrayList
        data.writeInt(items.size());
    
        // Écrire chaque élément de l'ArrayList
        for (int item : items) {
            if (item >= -128 && item <= 127) {
                data.writeByte(item);
            } else {
                throw new IOException("Valeur d'item hors de la plage autorisée (-128 à 127) : " + item);
            }
        }
    }

    public static Entity2D fromBytes(DataInputStream data) throws IOException {
        Entity2D entity = new Entity2D();
        
        // Lire et définir l'ID
        entity.id = data.readInt();
        
        // Lire et définir le nom
        entity.name = data.readUTF();
        
        // Lire et définir les coordonnées x et y
        entity.x = data.readFloat();
        entity.y = data.readFloat();
    
        // Lire la taille de l'ArrayList
        int size = data.readInt();
        entity.items = new ArrayList<>(size);
    
        // Lire chaque élément de l'ArrayList et l'ajouter à `items`
        for (int i = 0; i < size; i++) {
            entity.items.add((int) data.readByte());  // convertir byte en int
        }
    
        return entity;
    }
    
    /**
     * Elle crée une instance de Entity2D à partir d'une chaîne au format JSON.
     * @param json la chaîne au format JSON
     * @return une nouvelle instance de Entity2D
     * @throws IOException si une erreur se produit lors de la désérialisation
    
    public static Entity2D fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Entity2D.class);
    }*/

    /**
     * Elle convertit l'instance actuelle en une chaîne au format JSON.
     * @return une chaîne représentant l'instance actuelle au format JSON
     * @throws IOException si une erreur se produit lors de la sérialisation
     
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }*/


    @Override
    public String toString() {
        return "Entity2D [id=" + id + ", name=" + name + ", x=" + x + ", y=" + y + ", items=" + items + "]";
    }
    
}
