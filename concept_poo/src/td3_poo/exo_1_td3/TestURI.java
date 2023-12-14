package td3_poo.exo_1_td3;
import java.net.URI;
import java.net.URISyntaxException;

public class TestURI {
    public static void main(String[] args) {
        String uriString = "https://www.universita.corsica/";

        try {
            URI uri = new URI(uriString);
            System.out.println("La création de l'URI s'est bien passée");
        } catch (URISyntaxException e) {
            System.out.println("Problème lors de la création, il semble que " + uriString + " ne soit pas un URI correct");
        }
    }
}

