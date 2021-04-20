import java.io.Serializable;
import java.util.ArrayList;

/**
 * a class that creates a user of type buyer.
 */
public class Buyer implements Serializable{

    /**
     * creates a new buyer
     * @param idIn , the id number of the buyer.
     * @param userIn , the username of the buyer.
     * @param passwordIn , the password of the buyer.
     */
    public Buyer(String idIn, String userIn, String passwordIn) {
        id=idIn;
        username=userIn;
        password=passwordIn;
    }

    /**
     * retrieves the id of the buyer.
     * @return id, the id of the buyer.
     */
    public String getId() {
        return id;
    }

    /**
     * gets the buyers username.
     * @return username, the username of the buyer.
     */
    public String getUsername() {
        return username;
    }

    /**
     * validates the password of the buyer.
     * @param input , the string to verify against the password.
     * @return true or false, depending on validity of password.
     */
    public boolean checkPassword(String input) {
        return (password.equals(input));
    }

    private String id;
    private String username;
    private String password;
    private ShoppingCart myCart;
    private ArrayList<Product> myFavorites;
}
