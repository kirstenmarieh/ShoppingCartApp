import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class SellerFinancialView extends JPanel implements Observer{
    public class SellerFinancialView extends JPanel implements ChangeListener{

        public SellerFinancialView(SellerInventory s, double cost, Color color)
        public SellerFinancialView(double cost, Color color)
        {
            //JPanel financePanel = new JPanel();
            //financePanel.setPreferredSize(new Dimension(375, 100));
            @ -19,10 +17,10 @@ public class SellerFinancialView extends JPanel implements Observer{
            JLabel costLabel = new JLabel("Costs: " + costs);
            //financePanel.add(costLabel);
            // add(financePanel);
            add(costLabel);
        s.addObserver(this);
            // add(costLabel);
            // s.addObserver(this);

            //  super.add(costLabel);
            add(costLabel);
        }
    /*@Override
    public void stateChanged(ChangeEvent e) {
@ -37,21 +35,52 @@ public class SellerFinancialView extends JPanel implements Observer{
    private double cost;
    private double revenue;

   /* @Override
    @Override
    public void paint(Graphics g){
        super.paint(g);
        JLabel costLabel = new JLabel("Costs: " + costs);
        costLabel.setForeground(Color.RED);
        g.setColor(this.color);
    }*/
        }

        @Override
  /*  @Override
    public void update(Observable sellerInventory, Object finances) {
        SellerInventory model = (SellerInventory) sellerInventory;
        costLabel.setText(Double.toString(model.getCosts()));
        this.repaint();
    }
    }*/

        private Color color;
        private double costs;
        private JLabel costLabel;

   /* @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g1 = (Graphics2D) g;
        JLabel costLabel = new JLabel();
        g1.setColor(color);
        costLabel.setForeground(color);
    }*/

    /*@Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }*/

        @Override
        public void stateChanged(ChangeEvent c)
        {
            SellerInventory source = (SellerInventory) c.getSource();
            //source.calculateCosts();
            //this.cost = source.getCosts();
            this.cost = source.getChangeCosts();
            System.out.println("Costs: " + this.cost);
            this.color = java.awt.Color.RED;
            this.repaint();
        }
    }