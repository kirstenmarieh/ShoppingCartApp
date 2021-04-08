
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;


public class BrowseWindow extends Observable
{

    public BrowseWindow(String buyerID) throws IOException, ClassNotFoundException {
        this.buyerid = buyerID;

        final JFrame browseWindow = new JFrame("Browse Products");
        browseWindow.setBounds(100, 100, 800, 550);
        browseWindow.setLayout(null);

        // Order Button - Needs Event Listener
        JButton orderButton = new JButton("View Orders");
        orderButton.setBounds(0, 0, 150, 25);
        browseWindow.add(orderButton);

        // Checkout Button - Needs Event Listener
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(640, 0, 150, 25);
        browseWindow.add(checkoutButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 760, 400);
        browseWindow.add(scrollPane);

        JPanel borderLayout = new JPanel();
        scrollPane.setViewportView(borderLayout);
        borderLayout.setLayout(new BorderLayout(0,0));

        JPanel columnPanel = new JPanel();
        borderLayout.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        SingletonProductList singleton = SingletonProductList.getInstance();
        //productList = singleton.getList();
        Iterator<Product> listIter = singleton.getWholeList();

        while(listIter.hasNext())
        {
            productList.add(listIter.next());
        }

        for(Product p : productList)
        {
            System.out.println(p.getProductName());
        }



        for (int i = 0; i < productList.size(); i++)
        {

            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 200));
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);

            JButton viewProductButton = new JButton(productList.get(i).getProductName());
            viewProductButton.setBounds(50, 0, 150, 50);
            rowPanel.add(viewProductButton);

            // Add To Cart Button - Needs Event Listener
            JButton addToCartButton = new JButton("Add To Cart");
            addToCartButton.setBounds(600, 0, 100, 50);
            rowPanel.add(addToCartButton);
            addListenerForAddToCart(addToCartButton, productList.get(i));//productList.get(i));

            // Add To Favorite List - Needs Event Listener
            JButton addToFavButton = new JButton("Add To Favorites");
            addToFavButton.setBounds(575, 100, 150, 50);
            rowPanel.add(addToFavButton);
            //addListenerForAddToFav(addToFavButton, listIter.next());//productList.get(i));

            // JLabel For Price
            JLabel productPrice = new JLabel("Price : $" + String.valueOf(productList.get(i).getSellPrice()));//productList.get(i).getPrice()));
            productPrice.setBounds(50, 40, 100, 50);
            rowPanel.add(productPrice);

            // JLabel For Product Description
            JLabel productDescription = new JLabel("<html>" + productList.get(i).getProductDescription() + "</html>");//productList.get(i).getProductDescription()
            productDescription.setBounds(50, 40, 500, 200);
            rowPanel.add(productDescription);

        }

        browseWindow.setVisible(true);
    }


    public void addListenerForAddToCart(JButton button, Product product)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Adding To Cart: " + product.getProductName());
            }
        });
    }

    public void addListenerForAddToFav(JButton button, Product product)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Adding To Favorites: " + product.getProductName());
            }
        });
    }
    private String buyerid;
    private ArrayList<Product> productList = new ArrayList<>();
}
