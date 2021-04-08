import java.io.Serializable;
import java.util.ArrayList;

public class Buyer implements Serializable{

    public Buyer(String idIn, String userIn, String passwordIn) {
        id=idIn;
        username=userIn;
        password=passwordIn;
    }

    public  String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public boolean checkPassword(String input) {
        System.out.println(input+password);
        return (password.equals(input));
    }


    private String id;
    private String username;
    private String password;
    private ShoppingCart myCart;
    private ArrayList<Product> myFavorites;
}
