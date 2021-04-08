import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginWindow{

    public LoginWindow() {

        final JFrame loginWindow = new JFrame("Login");
        loginWindow.setLayout(new GridLayout());
        JLabel label = new JLabel("I'm a...");
        JPanel panel = new JPanel(new BorderLayout());

        JButton submit = new JButton("Submit");
        JLabel nameLabel = new JLabel("Username: ");
        JTextField userName = new JTextField(1);
       // /userName.setBounds(0,0,50,50);
        JLabel pWordLabel = new JLabel("Password: ");
        JTextField passwordField = new JTextField(1);


        loginWindow.add(nameLabel);
        loginWindow.add(userName);
        loginWindow.add(pWordLabel);
        loginWindow.add(passwordField);

        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UserDB verify = new UserDB();
                //System.out.println(userName.getText());
               // System.out.println(passwordField.getText());
               // System.out.println(passwordField.getText().getClass());
                String b = verify.verifyLogin(userName.getText(), passwordField.getText());
                System.out.println("b" + b);
                if (b!=null)
                {
                    if (b.endsWith("s"))
                    {System.out.println("successful login");
                    try {
                        SellerInventoryWindow sPage = new SellerInventoryWindow(b);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
                else if (b.endsWith("b"))
                {
                    System.out.println("ends with b");
                    try {
                        BrowseWindow buyerWindow = new BrowseWindow(b);
                        ShoppingCart myCart = new ShoppingCart(b);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }}
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Login");
                }
            }
        });
        loginWindow.add(submit);
        loginWindow.setSize(300, 300);
        loginWindow.getContentPane().add(panel);
        loginWindow.setVisible(true);
    }



    private String userID;

}
