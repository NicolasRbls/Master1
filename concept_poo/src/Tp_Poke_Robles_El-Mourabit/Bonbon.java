import java.io.Serializable;

public class Bonbon implements Serializable {
    private Type type;
    private static final long serialVersionUID = 1L;


    public Bonbon(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
 
