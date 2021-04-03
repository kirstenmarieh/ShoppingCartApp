
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class SellerFinancialView extends JPanel implements Observer{

    public SellerFinancialView(SellerInventory s, double cost, Color color)
    {
        //JPanel financePanel = new JPanel();
        //financePanel.setPreferredSize(new Dimension(375, 100));
        this.color = color;
        this.costs = cost;
        JLabel costLabel = new JLabel("Costs: " + costs);
        //financePanel.add(costLabel);
       // add(financePanel);
        add(costLabel);
        s.addObserver(this);

      //  super.add(costLabel);
    }
    /*@Override
    public void stateChanged(ChangeEvent e) {
        SellerInventory source = (SellerInventory) e.getSource();
        this.costs = source.getCosts();
        this.repaint();

    }*/


    private double profit;
    private double cost;
    private double revenue;

   /* @Override
    public void paint(Graphics g){
        super.paint(g);
        JLabel costLabel = new JLabel("Costs: " + costs);
        g.setColor(this.color);
    }*/

    @Override
    public void update(Observable sellerInventory, Object finances) {
        SellerInventory model = (SellerInventory) sellerInventory;
        costLabel.setText(Double.toString(model.getCosts()));
        this.repaint();
    }

    private Color color;
    private double costs;
    private JLabel costLabel;
}
