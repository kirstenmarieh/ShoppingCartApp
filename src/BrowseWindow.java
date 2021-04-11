import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class BrowseWindow
{
        public BrowseWindow(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
        this.myFavorites = new FavoritesList(userid);
        this.myCart = new ShoppingCart(userid);
        this.myFaveList = myFavorites.getFaves();
        this.myCartContents = myCart.getCartContents();

        for(Product p : myCartContents)
        {
            System.out.println("cart: " + p.getProductName());
        }
        myCart.calculateTotalPrice();
        System.out.println("tot price: " + myCart.getTotalPrice());

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
        
        DecimalFormat df = new DecimalFormat("#.00");
        JLabel cartTotal = new JLabel("Total : $" + df.format(myCart.getTotalPrice()));
        cartTotal.setBounds(640, 25, 150, 25);
        browseWindow.add(cartTotal);

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
        Iterator<Product> listIter = singleton.getProductList().iterator();

        while(listIter.hasNext())
        {
            productList.add(listIter.next());
        }

        for (int i = 0; i < productList.size(); i++)
        {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 200));
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);

            JButton viewProductButton = new JButton(productList.get(i).getProductName());
            viewProductButton.setBounds(50, 0, 150, 50);
            final int j=i; //I feel dirty for doing this but I don't want to deal with it
            viewProductButton.addActionListener(e -> {
                new ProductPopupWindow(productList.get(j));
            });
            rowPanel.add(viewProductButton);

            // Add To Cart Button
            JButton addToCartButton = new JButton("Add To Cart");
            addToCartButton.setBounds(600, 0, 100, 50);
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        myCart.addItem(productList.get(j));
                        myCart.calculateTotalPrice();
                        System.out.println("price: " + myCart.getTotalPrice());
                        cartTotal.setText("Total: $" + df.format(myCart.getTotalPrice()));
                        
                        for(Product p: myCart.getCartContents())
                        {
                            System.out.println("in cart: " + p.getProductName());
                        }
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            rowPanel.add(addToCartButton);

            //addListenerForAddToCart(addToCartButton, productList.get(i));//productList.get(i));

            // Add To Favorite List - Needs Event Listener
            JButton addToFavButton = new JButton("Add To Favorites");
            addToFavButton.setBounds(575, 100, 150, 50);
            addToFavButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        myFavorites.addFave(productList.get(j));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    try {
                        for(Product p: myFavorites.getFaves())
                        {
                            System.out.println("in faves: " + p.getProductName());
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
            });
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

    private String userid;
    private ArrayList<Product> productList = new ArrayList<>();
    private ShoppingCart myCart;
    private FavoritesList myFavorites;
    private ArrayList<Product> myCartContents = new ArrayList<>();
    private ArrayList<Product> myFaveList = new ArrayList<>();
}
