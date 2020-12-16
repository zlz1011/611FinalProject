import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayLoanFrame extends JFrame implements ReadData, CheckInput{
		
	private String username;
	private JTextField money;
	private JButton paybutton;
	private boolean ifsuccess;
	private JComboBox<String> account;
	
	public PayLoanFrame(String username) {
		this.setUsername(username);
		this.setIfsuccess(false);
		this.initcomps();
	}

	public void initcomps() {
		this.setMoney(new JTextField(20));
		this.setPaybutton(new JButton("Pay"));
		this.paybutton.addActionListener(new PayButtonListener());
		String [] accountName = new String[] {"Saving","Checking"};
		this.setAccount(new JComboBox<String>(accountName));
		
		String loan_currency = this.getLoanCurrency(this.username);
		String content = "Your current loan("+loan_currency+"):";
		JLabel message = new JLabel(content,SwingConstants.RIGHT);
		JLabel loan_message = new JLabel(String.valueOf(this.getLoan(this.username)),SwingConstants.CENTER);
		this.add(message);
		this.add(loan_message);
		JLabel input = new JLabel("Enter your payment amount:",SwingConstants.RIGHT);
		this.add(input);
		this.add(this.money);
		this.add(new JPanel());
		this.add(this.account);
		this.add(new JPanel());
		this.add(this.paybutton);
		
		this.setTitle("Pay your loan");
		this.setLayout(new GridLayout(4,2));
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JTextField getMoney() {
		return money;
	}

	public void setMoney(JTextField money) {
		this.money = money;
	}

	public JButton getPaybutton() {
		return paybutton;
	}

	public void setPaybutton(JButton paybutton) {
		this.paybutton = paybutton;
	}

	public boolean isIfsuccess() {
		return ifsuccess;
	}

	public void setIfsuccess(boolean ifsuccess) {
		this.ifsuccess = ifsuccess;
		if(this.ifsuccess) {
			this.dispose();
		}
	}

	public JComboBox<String> getAccount() {
		return account;
	}

	public void setAccount(JComboBox<String> account) {
		this.account = account;
	}
		
	class PayButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int money_input =0;
			JFrame warning = new JFrame();
			String account_type = account.getItemAt(account.getSelectedIndex());
			String currency = getDepositCurrency(account_type,username);
			String loan_currency = getLoanCurrency(username);
			
			if (!currency.equals(loan_currency)) {
				JOptionPane.showMessageDialog(warning, "You must choose your corresponding currency account!");
			}
			else if (checkInt(money.getText())==false) {
				JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
			}
			else {
				money_input = Integer.parseInt(money.getText());
				if(money_input > getDepositMoney(account_type,username)) {
					JOptionPane.showMessageDialog(warning, "You cannot pay over your current account balance!");
				}
				else if(money_input > getLoan(username)){
					JOptionPane.showMessageDialog(warning, "You cannot pay over your current loan!");
				}
				else {
					int old_loan = getLoan(username);
					int new_loan = old_loan - money_input;
					int old_money = getDepositMoney(account_type,username);
					int new_money = old_money - money_input;
					DataModify.modifyLoan(GetData.createFilePath("info.txt"), username, new_loan);
					DataModify.modifyMoney(GetData.createFilePath("info.txt"), username, account_type, new_money);
					String content = GetDate.currentDate() +":"+ username + " pays " + money_input + " USD loans to the bank.";
					WriteData.writeData(username, content);
					WriteData.writeTransaction(GetDate.currentDate(), content);
					setIfsuccess(true);
				}
			}
			
		}
		
	}
}
