import java.text.DecimalFormat;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class CheckoutWindow {
    public CheckoutWindow(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
        this.myCart = new ShoppingCart(userid);
        this.myCartContents = myCart.getCartContents();

        for (Product p : myCartContents) {
            System.out.println("cart: " + p.getProductName());
        }
        myCart.calculateTotalPrice();
        System.out.println("tot price: " + myCart.getTotalPrice());

        final JFrame checkoutWindow = new JFrame("Checkout");
        checkoutWindow.setBounds(100, 100, 800, 550);
        checkoutWindow.setLayout(null);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(0, 0, 150, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                       BrowseWindow browseWindow = new BrowseWindow(userID);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                        
                    checkoutWindow.dispose();
                }
            });
        checkoutWindow.add(cancelButton);

        DecimalFormat df = new DecimalFormat("#.00");
        JLabel cartTotal = new JLabel("Total : $" + df.format(myCart.getTotalPrice()));
        cartTotal.setBounds(640, 25, 150, 25);
        checkoutWindow.add(cartTotal);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 760, 400);
        checkoutWindow.add(scrollPane);

        JPanel borderLayout = new JPanel();
        scrollPane.setViewportView(borderLayout);
        borderLayout.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        borderLayout.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        for (int i = 0; i < myCartContents.size(); i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 200));
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);

            JLabel productQuantity = new JLabel("In Cart: " + String.valueOf(myCart.getQuantity(myCartContents.get(i))));
            productQuantity.setBounds(50, 40, 200, 100);
            rowPanel.add(productQuantity);

            JButton addProductButton = new JButton("+");
            addProductButton.setBounds(700, 100, 25, 25);
            final int j = i;
            addProductButton.addActionListener(e -> {
                try {
                    myCart.addItem(myCartContents.get(j));
                    int currentQuantity = myCart.getQuantity(myCartContents.get(j));
                    productQuantity.setText("In Cart: " + String.valueOf(currentQuantity++));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            });
            rowPanel.add(addProductButton);

            JButton subtractProductButton = new JButton("-");
            subtractProductButton.setBounds(675, 100, 25, 25);
            rowPanel.add(subtractProductButton);
            final int k = i;
            subtractProductButton.addActionListener(e -> {

            });
            rowPanel.add(addProductButton);


            // Add To Cart Button

            //rowPanel.add(confirmButton);

            // JLabel For Price
            JLabel productPrice = new JLabel("Price : $" + String.valueOf(myCartContents.get(i).getSellPrice()));//productList.get(i).getPrice()));
            productPrice.setBounds(50, 40, 100, 50);
            rowPanel.add(productPrice);


            // JLabel For Product Description
            JLabel productName = new JLabel("<html>" + myCartContents.get(i).getProductName() + "</html>");//productList.get(i).getProductDescription()
            productName.setBounds(50, 40, 500, 200);
            rowPanel.add(productName);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(600, 0, 100, 50);
        confirmButton.addActionListener(e -> {

        	for (int i = 0; i < myCartContents.size(); i++) 
                {
        		try {
					SingletonProductList list = SingletonProductList.getInstance();
					list.sellQuantity(myCartContents.get(i).getProductID(), myCartContents.get(i).getAvailableQuantity());
                                        
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		
        	}
                
                try {
                    // Creating And Adding Order And Emptying The Cart | Def Needs To Be Moved Somewhere Else 
                    //Since User Still Needs To Enter Payment Info
                    Order newOrder = new Order(myCart);
                    OrderList myOrders = new OrderList(userID);
                    myOrders.addOrder(newOrder);
                    myCart.emptyCart();
                    BrowseWindow buyerWindow = new BrowseWindow(userID);
                } catch (Exception e2) { 
                    e2.printStackTrace(); }
     
                checkoutWindow.dispose();
        	
        });
        checkoutWindow.add(confirmButton);
        checkoutWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkoutWindow.setVisible(true);
    }

    private String userid;
    private ArrayList<Product> productList = new ArrayList<>();
    private ShoppingCart myCart;
    private ArrayList<Product> myCartContents = new ArrayList<>();
}