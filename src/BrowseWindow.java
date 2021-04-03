
package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;


public class BrowseWindow extends Observable 
{  
    public static void main(String[] args)
    {
        ArrayList<Product> productList = new ArrayList<Product>();
        
        Product one = new Product("Banana", "01", "Fruit", 5.99, "A Yellow Fruit", 15);
        Product two = new Product("Keyboard", "01", "Electronics", 55.99, "The incomparable Corsair K100 RGB "
                + "Mechanical Gaming Keyboard combines stunning aluminum design with per-key RGB lighting "
                + "and a 44-zone LightEdge. Powerful Corsair AXON Hyper-Processing Technology enables "
                + "unparalleled capabilities such as 4,000Hz polling. Cherry MX Speed RGB Silver mechanical "
                + "keyswitches, guaranteed for 100 million keypresses, offer ultra-fast 1.2mm actuation "
                + "while registering keypresses up to 4x faster than standard mechanical gaming keyboards "
                + "thanks to AXON, housed in durable double-shot PBT keycaps.", 15);
        Product three = new Product("Dell Monitor", "01", "Electronics", 500.99, "The HP 24mh FHD Monitor has "
                + "all the style and performance you need in a monitor without breaking the bank. Experience "
                + "more desk space than you thought possible with this height-adjustable ultra-slim HP monitor. "
                + "The attractive, micro-edge design adds stylish beauty to any environment, be it your desk or "
                + "living room. Seeing is believing as this Full HD display gives you IPS technology and ultra-wide "
                + "viewing angles that combine to give you an amazing view at up to 178°. Conveniently connect your "
                + "pc and consoles with HDMI and DisplayPort™ ports.", 15);
        
        productList.add(one);
        productList.add(two);
        productList.add(three);
        
        
        
        BrowseWindow test = new BrowseWindow(productList);
    }
    
    public BrowseWindow(ArrayList<Product> productList)
    {
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
        
        for(int i = 0; i < productList.size(); i++)
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
            addListenerForAddToCart(addToCartButton, productList.get(i));
            
            // Add To Favorite List - Needs Event Listener
            JButton addToFavButton = new JButton("Add To Favorites");
            addToFavButton.setBounds(575, 100, 150, 50);
            rowPanel.add(addToFavButton);
            addListenerForAddToFav(addToFavButton, productList.get(i));
            
            // JLabel For Price
            JLabel productPrice = new JLabel("Price : $" + String.valueOf(productList.get(i).getPrice()));
            productPrice.setBounds(50, 40, 100, 50);
            rowPanel.add(productPrice);
            
            // JLabel For Product Description
            JLabel productDescription = new JLabel("<html>" + productList.get(i).getProductDescription() + "</html>"); 
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
}
