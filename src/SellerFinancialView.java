import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;


    public class SellerFinancialView extends JPanel implements ChangeListener {

        //public SellerFinancialView(SellerInventory s, double cost, Color color)
        public SellerFinancialView(double cost, double revenue, double profit, Color color) {
            this.cost = cost;
            this.revenue = revenue;
            this.profit = profit;

            JPanel financePanel = new JPanel();
            financePanel.setPreferredSize(new Dimension(375, 100));

            JLabel titleLabel = new JLabel("Finances");
            financePanel.add(titleLabel);

            JLabel costLabel = new JLabel("Costs: " + cost);
            this.costLabel = costLabel;
            financePanel.add(costLabel);

            JLabel revLabel = new JLabel("Revenue: " + revenue);
            this.revLabel = revLabel;
            financePanel.add(revLabel);



            JLabel profLabel = new JLabel("Profits: " + profit);
            this.profitLabel = profLabel;
            financePanel.add(profLabel);

            add(financePanel);
        }


        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //JLabel costLabel = new JLabel("Costs: " + cost);
            costLabel.setText("Costs: " + String.valueOf(cost));
            revLabel.setText("Revenue: " + String.valueOf(revenue));
            //System.out.println(profit);
            profitLabel.setText("Profits: " + String.valueOf(profit));

            costLabel.setForeground(this.color);
            g.setColor(this.color);
        }


        @Override
        public void stateChanged(ChangeEvent c) {
            SellerInventory source = (SellerInventory) c.getSource();
            this.cost = source.getCosts();
            this.revenue = source.getRevenue();
            this.profit = source.getProfit();
            this.color = java.awt.Color.RED;
            this.repaint();
        }

        private Color color;
        private double cost;
        private JLabel costLabel;
        private double revenue;
        private double profit;
        private JLabel profitLabel;
        private JLabel revLabel;
    }