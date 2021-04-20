import java.io.*;
import java.util.ArrayList;

/**
 * a class that constructs and manipulates a favorites list of a user.
 */
public class FavoritesList implements Serializable {

    /**
     * constructs a favorites list
     * @param userID, the userid of whom to create a favorite list for.
     */
    public FavoritesList(String userID){
        this.userid = userID;
    }

    /**
     * adds a product to the favorites list.
     * @param p the product to add to the favorites list.
     * @throws IOException
     */
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

    /**
     * removes a product from the favorites list.
     * @precondition favoriteslist size > 0
     * @param p the product to remove
     * @throws IOException
     */
    public void removeFave(Product p) throws IOException {
        try {
            this.getFaves();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (faves.size() == 0)
        {
            System.out.println("Favorites list is empty.");
            return;
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

    /**
     * serializes the favoriteslist
     * @throws IOException
     */
    public void saveFaveList() throws IOException {
        String fileName = userid +".txt";
        File file = new File(fileName);
        if (file.exists())
        {
            FileOutputStream fos = new FileOutputStream(fileName);//fileName);
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

    /**
     * deserializes the favoriteslist.
     * @return faves, the users favoriteslist
     * @throws IOException
     * @throws ClassNotFoundException
     * @precondition: the file exists.
     */
    public ArrayList<Product> getFaves() throws IOException, ClassNotFoundException {
        try{
            String fileName=userid+".txt";
            File file=new File(fileName);
            if(file.exists()) {

                FileInputStream fis = new FileInputStream(fileName);
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

