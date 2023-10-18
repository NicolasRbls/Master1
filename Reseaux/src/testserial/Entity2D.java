package testserial;

import java.util.ArrayList;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;


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

    @Override
    public String toString() {
        return "Entity2D [id=" + id + ", name=" + name + ", x=" + x + ", y=" + y + ", items=" + items + "]";
    }
    
}
