import java.util.ArrayList;

public class UserDB {

    public UserDB() {
        sellers=new ArrayList<Seller>();
        buyers=new ArrayList<Buyer>();
        sellers.add(new Seller("1s","abcde","12345"));
        sellers.add(new Seller("2s","RonaldMcDonald","lovinit"));
        buyers.add(new Buyer("3b","randomdave","12345"));
        buyers.add(new Buyer("4b","dave2.0","arealpassword2.0"));
    }

    public String verifyLogin(String userIn, String passIn) {
        //System.out.println(userIn+passIn);
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