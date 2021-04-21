import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.io.IOException;
import java.text.DecimalFormat;

import java.util.ArrayList;


public class OrderWindow
{
    /**
     * Creates And Displays A Window Containing All Of A User's 
     * Previous Orders
     * @param userID ID of Buyer Account Currently Signed In
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public OrderWindow(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;  
        this.myOrders = new OrderList(userid);
        this.myOrderList = myOrders.getOrders();
        
        DecimalFormat df = new DecimalFormat("#.00");

        final JFrame orderWindow = new JFrame("Past Orders");
        orderWindow.setBounds(100, 100, 800, 550);
        orderWindow.setLayout(null);

        /**
         * Creates And Adds A Back Button With An ActionListener
         * To Return Back To The BrowseWindow
         */
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 150, 25);
        orderWindow.add(backButton);
        
        backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        BrowseWindow buyerWindow = new BrowseWindow(userID);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    
                    orderWindow.dispose();
                }
        });

        /**
         * Creates And Adds A Scroll-able UI To Contain The Orders
         * In Order To Account For Any Number Of Orders To Be Displayed
         */
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 760, 400);
        orderWindow.add(scrollPane);

        JPanel borderLayout = new JPanel();
        scrollPane.setViewportView(borderLayout);
        borderLayout.setLayout(new BorderLayout(0,0));

        JPanel columnPanel = new JPanel();
        borderLayout.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        /**
         * Iterates Through The User's Previous Orders 
         * Creating A JPanel TO Display The Information For Each One
         */
        for (int i = 0; i < myOrderList.size(); i++)
        {          
            ArrayList<Product> orderProducts = myOrderList.get(i).getOrderProducts();
            
            JPanel rowPanel = new JPanel();
            if (orderProducts.size() == 1)
            {
                rowPanel.setPreferredSize(new Dimension(300, 60 * 3));
            }
            else
            {
                rowPanel.setPreferredSize(new Dimension(300, 60 * (orderProducts.size())));
            }
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);
            
            
            JLabel itemText = new JLabel("Order Items : ");
            Font font = itemText.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            itemText.setFont(font.deriveFont(attributes));
            itemText.setBounds(50, 20, 200, 50);
            rowPanel.add(itemText);

            JLabel productText;
            
            /**
             * Iterates Through Each Product Within An Order
             * Adding JLabels Containing The Product Name And Price 
             * To The JPanel
             */
            for (int j = 0; j < orderProducts.size(); j++)
            {
                productText = new JLabel(orderProducts.get(j).getProductName() + 
                        " | $" + df.format(orderProducts.get(j).getSellPrice()));
                productText.setBounds(50, 50 + (40 * j), 250, 50);
                rowPanel.add(productText);
            }

            // JLabel For Total Price Of An Order
            JLabel orderPrice = new JLabel("Order Total : $" + 
                    df.format(myOrderList.get(i).getOrderPrice()));
            orderPrice.setBounds(400, 50, 200, 50);
            rowPanel.add(orderPrice);
        }

        orderWindow.setVisible(true);
    }

    private String userid;
    private OrderList myOrders;
    private ArrayList<Order> myOrderList = new ArrayList<>();
}