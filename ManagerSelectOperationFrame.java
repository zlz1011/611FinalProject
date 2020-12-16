/**
 * once manager login, create a frame contains buttons for manager to chose an operation from
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ManagerSelectOperationFrame extends JFrame {
    private String managerID;
    private JButton checkCustomerButton,checkTransButton,memberButton,stockButton,nextDayButton,logOutButton,bankMoneyButton,exchangeRateButton;
    private JLabel dateLabel;
    public ManagerSelectOperationFrame(String managerID){
        this.managerID=managerID;

        this.setTitle("Manager operation interface");
        this.setLayout(new GridLayout(4,3));
        this.setSize(800,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(new JPanel());
        JLabel titleLabel = new JLabel("Hello! Manager "+managerID,SwingConstants.CENTER);
        this.add(titleLabel);
        dateLabel= new JLabel("Date: "+GetDate.currentDate(),SwingConstants.CENTER);
        this.add(dateLabel);

        this.checkCustomerButton=new JButton("Check a Customer");
        this.add(this.checkCustomerButton);
        this.bankMoneyButton=new JButton("total money of bank");
        this.add(this.bankMoneyButton);
        this.checkTransButton=new JButton("Get Daily Report");
        this.add(this.checkTransButton);

        this.memberButton=new JButton("Add/Delete managers");
        this.add(this.memberButton);
        this.exchangeRateButton=new JButton("set exchange rate");
        this.add(this.exchangeRateButton);
        this.stockButton=new JButton("Stock Market");
        this.add(this.stockButton);

        this.nextDayButton=new JButton("Go to next day");
        this.add(this.nextDayButton);
        this.add(new JPanel());
        this.logOutButton=new JButton("Log out");
        this.add(this.logOutButton);

        this.checkCustomerButton.addActionListener(new checkCustomerListener());
        this.checkTransButton.addActionListener(new checkTransListener());
        this.memberButton.addActionListener(new memberListener());
        this.stockButton.addActionListener(new stockListener());
        this.nextDayButton.addActionListener(new nextDayListener());
        this.logOutButton.addActionListener(new logOutListener());
        this.bankMoneyButton.addActionListener(new bankMoneyListener());
        this.exchangeRateButton.addActionListener(new exchangeRateListener());
        this.setVisible(true);
    }
    class bankMoneyListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ManagerSelectOperationFrame.this.setEnabled(false);
            ManagerBankMoneyFrame tem=new ManagerBankMoneyFrame(ManagerSelectOperationFrame.this);
        }
    }
    class exchangeRateListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ManagerSelectOperationFrame.this.setEnabled(false);
            ManagerExchangeRateFrame tem=new ManagerExchangeRateFrame(ManagerSelectOperationFrame.this);
        }
    }
    class checkCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ManagerSelectOperationFrame.this.setEnabled(false);
            ManagerCheckCustomerFrame tem=new ManagerCheckCustomerFrame(ManagerSelectOperationFrame.this);
        }
    }
    class memberListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ManagerSelectOperationFrame.this.setEnabled(false);
            ManagerEditMemberFrame tem=new ManagerEditMemberFrame(ManagerSelectOperationFrame.this, managerID);
        }
    }
    class checkTransListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ManagerSelectOperationFrame.this.setEnabled(false);
            ManagerDailyReportFrame tem=new ManagerDailyReportFrame(ManagerSelectOperationFrame.this);
        }
    }
    class stockListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JOptionPane tem=new JOptionPane();
            tem.showMessageDialog(null,"Stock market not implemented!");
        }
    }
    class nextDayListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            GetDate.changeDay(1);
            dateLabel.setText("Date: "+GetDate.currentDate());
        }
    }
    class logOutListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ManagerSelectOperationFrame.this.dispose();
            ManagerLoginFrame frame = new ManagerLoginFrame();
        }
    }
}
