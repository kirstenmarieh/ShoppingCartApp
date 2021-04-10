import java.io.*;
import java.util.ArrayList;

public class FavoritesList implements Serializable {

    public FavoritesList(String userID){
        this.userid = userID;
    }

    public void addFave(Product p) throws IOException {
        faves.add(p);
        this.saveFaveList();
    }

    public void saveFaveList() throws IOException {
        //String fileName = userid + ".txt";
        FileOutputStream fos = new FileOutputStream("3b.txt");//fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.faves);
        oos.flush();
        oos.close();
        fos.close();

    }

    public ArrayList<Product> getFaves() throws IOException, ClassNotFoundException {
        String fileName=userid+".txt";
    	File file=new File(fileName);
    	System.out.println(fileName+"????");
    	if(file.exists()) {
	    	FileInputStream fis = new FileInputStream("3b.txt");//fileName);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        this.faves = (ArrayList<Product>) ois.readObject();
	    }
        return faves;
    }

    private String userid;
    private ArrayList<Product> faves = new ArrayList<>();
}
