import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class SingletonProductList implements Serializable{

    private SingletonProductList() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("globalproductlist.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        productList = (ArrayList<Product>) ois.readObject();
    }

    public static SingletonProductList getInstance() throws IOException, ClassNotFoundException {//SingletonProductList
        if (instance == null)
        {
            instance = new SingletonProductList(); //ArrayList<>();
        }
        return instance;
    }

    public void add(Product p){
        productList.add(p);
    }

    public ArrayList<Product> getList(String userID){ //use for shopping cart and seller inventory and favorite's list!?
        ArrayList<Product> personalProductList = new ArrayList<>();
        for (Product p : productList)
        {
            if (p.getID() == userID)
            {
                personalProductList.add(p);
            }
        }
        return personalProductList;
    }

    private static SingletonProductList instance = null;
    private ArrayList<Product> productList = new ArrayList<>();
}
