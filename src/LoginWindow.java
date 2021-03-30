import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class LoginWindow implements Observer{

    public LoginWindow() {

        final JFrame loginWindow = new JFrame("Login");
        loginWindow.setLayout(new GridLayout());
        JLabel label = new JLabel("I'm a...");
        JPanel panel = new JPanel(new BorderLayout());

        JButton submit = new JButton("Submit");
        JLabel nameLabel = new JLabel("Username: ");
        JTextField userName = new JTextField(1);

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
                System.out.println(userName.getText());
                System.out.println(passwordField.getText());
                System.out.println(passwordField.getText().getClass());
                boolean b = verify.verifyLogin(userName.getText(), passwordField.getText());
                System.out.println(b);
                if (b)
                {
                    System.out.println("hello");
                    SellerInventoryWindow sPage = new SellerInventoryWindow();
                }
                //else pop up window error or something
            }
        });
        loginWindow.add(submit);
        loginWindow.setSize(300, 300);
        loginWindow.getContentPane().add(panel);
        loginWindow.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
