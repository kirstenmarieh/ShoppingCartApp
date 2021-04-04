
public class Buyer {

	public Buyer(int idIn, String userIn, String passwordIn) {
		id=idIn;
		username=userIn;
		password=passwordIn;
	}
	
	public  int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public boolean checkPassword(String input) {
		System.out.println(input+password);
		return (password.equals(input));
	}
	
	
	private int id;
	private String username;
	private String password;
}
