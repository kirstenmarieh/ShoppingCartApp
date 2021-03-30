import java.io.*;
import java.util.ArrayList;

public class SingletonProductList implements Serializable{

    private SingletonProductList() throws IOException, ClassNotFoundException {
        FileOutputStream  fos = new FileOutputStream ("globalproductlist.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.productList);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("globalproductlist.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        productList = (ArrayList<Product>) ois.readObject();
    }

    public static SingletonProductList getInstance() throws IOException, ClassNotFoundException {//SingletonProductList
        if (instance == null)
        {
            instance = new SingletonProductList();
        }
        return instance;
    }

    public void add(Product p) throws IOException {
        productList.add(p);
        this.saveList(this.productList);
    }

    public void getItem(int index){ //for editing and removing... need just index or product ID?
        productList.get(index); //get product at specific index
    }

    public void editItem(){

    }

    public void saveList(ArrayList<Product> productList) throws IOException {
        FileOutputStream fos = new FileOutputStream ("globalproductlist.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public ArrayList<Product> getList(String userID) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("globalproductlist.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SingletonProductList singleton = (SingletonProductList) ois.readObject();

        System.out.println(singleton.productList);

        ArrayList<Product> personalProductList = new ArrayList<>();
        for (Product p : singleton.productList)
        {
            if (p.getSellerID().equals(userID))
            {
                personalProductList.add(p);
            }
        }
        System.out.println(personalProductList);
        return personalProductList;
    }

    private static SingletonProductList instance = null;
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Product> pList = new ArrayList<>();
}
