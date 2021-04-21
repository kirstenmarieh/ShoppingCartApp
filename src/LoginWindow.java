import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * @author James Jenson
 * constructs and displays a login window.
 */
public class LoginWindow{

    public LoginWindow() {

        final JFrame loginWindow = new JFrame("Login");
        loginWindow.getContentPane().setLayout(new BoxLayout(loginWindow.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField(0);
        usernameField.setPreferredSize(new Dimension(400,20));
        usernameField.setMaximumSize( usernameField.getPreferredSize() );

        JLabel passwordLabel = new JLabel("Password: ");
        JTextField passwordField = new JTextField(0);
        passwordField.setPreferredSize(new Dimension(400,20));
        passwordField.setMaximumSize(passwordField.getPreferredSize() );

        JPanel usernamePanel= new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        
        JPanel passwordPanel= new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        
        loginWindow.add(usernamePanel);
        JPanel buffer= new JPanel();
        buffer.setPreferredSize(new Dimension(4,2));
        loginWindow.add(buffer);
        loginWindow.add(passwordPanel);
        loginWindow.getContentPane().add(panel);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UserDB verify = new UserDB();
                String b = verify.verifyLogin(usernameField.getText(), passwordField.getText());
                System.out.println("b" + b);
                if (b!=null)
                {
                    if (b.endsWith("s"))
                    {System.out.println("successful login");
                    try {
                        SellerInventoryWindow sPage = new SellerInventoryWindow(b);
                        loginWindow.dispose();
                        
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
                        loginWindow.dispose();
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
        loginWindow.setSize(455, 150);
        loginWindow.setResizable(false);
        loginWindow.setVisible(true);
    }

    private String userID;
}
