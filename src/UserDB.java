import java.util.ArrayList;

/**
 *a class that constructs and populates a user database.
 */
public class UserDB {

    /**
     * constructs a user database with buyers and sellers.
     */
    public UserDB() {
        sellers=new ArrayList<Seller>();
        buyers=new ArrayList<Buyer>();
        sellers.add(new Seller("1s","abcde","12345"));
        sellers.add(new Seller("2s","RonaldMcDonald","lovinit"));
        buyers.add(new Buyer("3b","randomdave","12345"));
        buyers.add(new Buyer("4b","dave2.0","arealpassword2.0"));
    }

    /**
     * verifies the login information of a buyer and seller.
     * @param userIn the username of a user
     * @param passIn the password of a user
     * @return the id number of a user
     */
    public String verifyLogin(String userIn, String passIn) {
        for (Seller s:sellers) {
            if(s.getUsername().equals(userIn)&&s.checkPassword(passIn)) {
                return s.getId();
            }
        }
        for (Buyer b:buyers) {
            if(b.getUsername().equals(userIn)&&b.checkPassword(passIn)) {
                return b.getId();
            }
        }
        return null;
    }

    private ArrayList<Seller> sellers; //need to return some value that differentiates between buyers and sellers AND returns the userid
    private ArrayList<Buyer> buyers;
}