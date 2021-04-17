import java.io.*;
import java.util.ArrayList;

public class FavoritesList implements Serializable {

    public FavoritesList(String userID){
        this.userid = userID;
    }

    public void addFave(Product p) throws IOException {
        try {
            this.getFaves();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Product i : faves){
            if (p.getProductID().equals(i.getProductID()))
            {
                System.out.println("Product already in favorites");
                return;
            }
        }

        faves.add(p);
        this.saveFaveList();
    }

    public void removeFave(Product p) throws IOException {
        try {
            this.getFaves();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < faves.size(); i++)
        {
            if (p.getProductID().equals(faves.get(i).getProductID()))
            {
                System.out.println(p.getProductName() + " removed from favorites");
                faves.remove(i);
                this.saveFaveList();
                return;
            }
        }
    }

    public void saveFaveList() throws IOException {
        String fileName = userid +".txt";
        File file = new File(fileName);
        if (file.exists())
        {
            FileOutputStream fos = new FileOutputStream("3b.txt");//fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.faves);
            oos.flush();
            oos.close();
            fos.close();
        }
        else
        {
            file.createNewFile();
        }

    }

    public ArrayList<Product> getFaves() throws IOException, ClassNotFoundException {
        try{
            String fileName=userid+".txt";
            File file=new File(fileName);
            System.out.println(fileName+"????");
            if(file.exists()) {
                FileInputStream fis = new FileInputStream("3b.txt");//fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.faves = (ArrayList<Product>) ois.readObject();
            }}
        catch (EOFException e)
        {
            System.out.println("No products in favorites list.");
        }
        return faves;
    }

    private String userid;
    private ArrayList<Product> faves = new ArrayList<>();
}

