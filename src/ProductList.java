
import java.util.ArrayList;

public class ProductList {
    public ProductList(){
        itemList = new ArrayList<Product>();
    }

    public void addItem(Product p){
        itemList.add(p);
    }

    public void removeItem(int index)
    {
        itemList.remove(itemList.get(index));
    }

    public void editItem(int index){
        productToEdit = itemList.get(index); //when editing a product, we are editing the PRODUCT then the LIST... this needs to be in PRODUCT
    }

    public ArrayList<Product> getList(){
        return itemList;
    }

    //get quantity??

    private ArrayList<Product> itemList;
    private Product productToEdit;
}
