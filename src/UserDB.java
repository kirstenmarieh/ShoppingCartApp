import java.util.ArrayList;

public class UserDB {

	public UserDB() {
		sellers=new ArrayList<Seller>();
		buyers=new ArrayList<Buyer>();
		sellers.add(new Seller(1,"abcde","12345"));
		sellers.add(new Seller(2,"RonaldMcDonald","lovinit"));
		buyers.add(new Buyer(3,"randomdave","12345"));
		buyers.add(new Buyer(3,"dave2.0","arealpassword2.0"));
	}
	
	public int verifyLogin(String userIn, String passIn) {
		System.out.println(userIn+passIn);
		for (Seller s:sellers) {
			if(s.getUsername().equals(userIn)&&s.checkPassword(passIn)) {
				return 1;
			}
		}
		for (Buyer b:buyers) {
			if(b.getUsername().equals(userIn)&&b.checkPassword(passIn)) {
				return 2;
			}
		}
		return 0;
	}
	
	private ArrayList<Seller> sellers;
	private ArrayList<Buyer> buyers;
}
