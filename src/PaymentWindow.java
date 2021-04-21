import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Kirsten Hernquist, James Jenson, Jared Usher
 * Constructs and displays a payment window for the user to enter payment information.
 */
public class PaymentWindow {

    public PaymentWindow(String userid) throws IOException, ClassNotFoundException {

        this.userID = userid;
        this.myCart = new ShoppingCart(userID);
        this.myCartContents = myCart.getCartContents();
        SingletonProductList products = SingletonProductList.getInstance();
        productList = products.getProductList();

        for(Product c: myCart.getCartContents())
        {
            System.out.println("in cart " + c.getProductName() + " " + myCart.getQuantity(c));
        }

        final JFrame paymentWindow = new JFrame("Checkout");
        paymentWindow.getContentPane().setLayout(new BoxLayout(paymentWindow.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();

        JLabel ccNumberLabel = new JLabel("Credit Card Number: ");
        JTextField ccNumberField = new JTextField(0);
        ccNumberField.setPreferredSize(new Dimension(400,20));
        ccNumberField.setMaximumSize( ccNumberField.getPreferredSize() );

        JLabel secNumLabel = new JLabel("CVV: ");
        JTextField secNumField = new JTextField(0);
        secNumField.setPreferredSize(new Dimension(400,20));
        secNumField.setMaximumSize(secNumField.getPreferredSize() );

        JPanel ccNumPanel= new JPanel();
        ccNumPanel.setLayout(new BoxLayout(ccNumPanel, BoxLayout.X_AXIS));
        ccNumPanel.add(ccNumberLabel);
        ccNumPanel.add(ccNumberField);

        JPanel secNumPanel= new JPanel();
        secNumPanel.setLayout(new BoxLayout(secNumPanel, BoxLayout.X_AXIS));
        secNumPanel.add(secNumLabel);
        secNumPanel.add(secNumField);

        paymentWindow.add(ccNumPanel);
        JPanel buffer= new JPanel();
        buffer.setPreferredSize(new Dimension(4,2));
        paymentWindow.add(buffer);
        paymentWindow.add(secNumPanel);
        paymentWindow.getContentPane().add(panel);

        JButton submit = new JButton("Confirm");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                	System.out.println("Mycartcontents size: "+ myCart.getCartContents().size());
                        
                    for (int k = 0; k < myCartContents.size(); k++)
                    {
                        
                        SingletonProductList si = SingletonProductList.getInstance();
                        myCartContents.get(k).sell(1);
                        //si.sellQuantity(myCartContents.get(k).getProductID(), 1);
                        
                        
                        
                    }
                    try {
                        Order newOrder = new Order(myCart);
                        OrderList myOrders = new OrderList(userID);
                        myOrders.addOrder(newOrder);
                        SingletonProductList singleton = SingletonProductList.getInstance();
                        
                        for (Product p : myCart.getCartContents())
                        {
                            singleton.updateAvailableQuantity(p.getProductID(), p.getAvailableQuantity()- myCart.getQuantity(p));
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                
                try {
                myCart.emptyCart();
                BrowseWindow buyerWindow = new BrowseWindow(userID);
                paymentWindow.dispose();
                }
                catch (Exception e3) { e3.printStackTrace();}
            }});

        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BrowseWindow bw = new BrowseWindow(userID);
                    paymentWindow.setVisible(false);
                    paymentWindow.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }});

        paymentWindow.add(cancel);
        paymentWindow.add(submit);
        paymentWindow.setSize(455, 150);
        paymentWindow.setResizable(false);
        paymentWindow.setVisible(true);
        paymentWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private ArrayList<Product> myCartContents=  new ArrayList<>();
    private String userID;
    private ShoppingCart myCart;
    private ArrayList<Product> productList = new ArrayList<>();
}


