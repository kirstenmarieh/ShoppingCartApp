/**
 * a class that constructs a seller.
 */
public class Seller {
    public Seller(String idIn, String userIn, String passwordIn) {
        id=idIn;
        username=userIn;
        password=passwordIn;
    }

    /**
     * gets the seller's id.
     * @return the seller's id.
     */
    public String getId() {
        return id;
    }

    /**
     * gets the seller's username.
     * @return username, the seller's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * validates the password of the seller.
     * @param input the string to verify.
     * @return true or false, depending on whether the input was correct.
     */
    public boolean checkPassword(String input) {
        System.out.println(input+password);
        return (password.equals(input));
    }

    private String id;
    private String username;
    private String password;
}