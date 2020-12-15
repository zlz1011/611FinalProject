
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class WithdrawDepositFrame extends JFrame implements ReadData, CheckInput{

	private String username;
	private JTextField money;
	private JButton button;
	private String operation;
	private boolean ifsuccess;
	private String accountType;
	
	public WithdrawDepositFrame(String operation, String username, String accountType) {
		this.setUsername(username);
		this.setAccountType(accountType);
		this.initcomps(operation);
		this.setOperation(operation);;
		this.setIfsuccess(false);
	}

	public void initcomps(String operation) {
		this.setMoney(new JTextField(20));
		this.setButton(new JButton(operation));
		this.button.addActionListener(new ButtonListener());
		
		JLabel input = new JLabel("Enter your number:",SwingConstants.RIGHT);
		this.add(input);
		this.add(this.money);
		this.add(new JPanel());
		this.add(this.button);
		
		String title = operation + " your money here.";
		this.setTitle(title);
		this.setLayout(new GridLayout(2,2));
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

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int money_input =0;
			JFrame warning = new JFrame();
			
			if(operation.equals("Withdraw")) {
				if (checkInt(money.getText()) == false) {
					JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
				}
				else {
					money_input = Integer.parseInt(money.getText());
					if(accountType.equals("Saving")) {
						int old_money = getDepositMoney("Saving");
						if(money_input > old_money) {
							JOptionPane.showMessageDialog(warning, "You cannot withdraw over your current account balance!");
						}
						else {
							int new_money = old_money - money_input;
							DataModify.modifyData( GetData.createFilePath("info.txt"),username, "Saving", new_money);
							setIfsuccess(true);
						}
					}
					else {
						int old_money = getDepositMoney("Checking");
						if(money_input > old_money) {
							JOptionPane.showMessageDialog(warning, "You cannot withdraw over your current account balance!");
						}
						else {
							int new_money = old_money - money_input;
							DataModify.modifyData( GetData.createFilePath("info.txt"),username, "Checking", new_money);
							setIfsuccess(true);
						}
					}
				}
				
			}
			else {
				if (checkInt(money.getText()) == false) {
					JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
				}
				else {
					money_input = Integer.parseInt(money.getText());
					if(accountType.equals("Saving")) {
						int old_money = getDepositMoney("Saving");
						int new_money = old_money + money_input;
						DataModify.modifyData( GetData.createFilePath("info.txt"),username, "Saving", new_money);
						setIfsuccess(true);
					}
					else {
						int old_money = getDepositMoney("Checking");
						int new_money = old_money + money_input;
						DataModify.modifyData( GetData.createFilePath("info.txt"),username, "Checking", new_money);
						setIfsuccess(true);
					}
				}
				
			}
				
		}
		
	}
	
	public int getDepositMoney(String accountType) {
		ArrayList<String []> read_data = GetData.read(GetData.createFilePath("info.txt"), false);
		int money_num = 0;
		for (int i=0; i<read_data.size(); i++) {
			String [] data = read_data.get(i);
			for (int j=0; j<data.length; j++) {
				if(this.username.equals(data[j])) {
					if (accountType.equals("Saving")) {
						money_num = Integer.parseInt(data[j+3]);
					}
					else {
						money_num = Integer.parseInt(data[j+5]);
					}
				}
			}
		}
		return money_num;
	}

	@Override
	public String getDepositCurrency(String accountType) {
		
		return null;
	}
	
	
	
}
