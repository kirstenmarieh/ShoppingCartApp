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
    public OrderWindow(String userID) throws IOException, ClassNotFoundException {
        //TestOrder(userID);
        this.userid = userID;  
        this.myOrders = new OrderList(userid);
        this.myOrderList = myOrders.getOrders();
        
        DecimalFormat df = new DecimalFormat("#.00");

        final JFrame orderWindow = new JFrame("Past Orders");
        orderWindow.setBounds(100, 100, 800, 550);
        orderWindow.setLayout(null);

        // Back Button - Needs Event Listener To Return To Browse Window
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 150, 25);
        orderWindow.add(backButton);

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

        
        for (int i = 0; i < myOrderList.size(); i++)
        { 
            ArrayList<Product> orderProducts = myOrderList.get(i).getOrderProducts();
            
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 60 * (orderProducts.size())));
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
            
            for (int j = 0; j < orderProducts.size(); j++)
            {
                productText = new JLabel(orderProducts.get(j).getProductName());
                productText.setBounds(50, 50 + (40 * j), 100, 50);
                rowPanel.add(productText);
            }

            // JLabel For Price
            JLabel orderPrice = new JLabel("Total Price : $" + df.format(myOrderList.get(i).getOrderPrice()));
            orderPrice.setBounds(400, 50, 200, 50);
            rowPanel.add(orderPrice);
        }

        orderWindow.setVisible(true);
    }

    private String userid;
    //private ShoppingCart myCart;
    private OrderList myOrders;
    private ArrayList<Order> myOrderList = new ArrayList<>();
}