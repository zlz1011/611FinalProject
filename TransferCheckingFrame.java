import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferCheckingFrame extends JFrame implements ReadData,CheckInput{
	
	private String username;
	private JTextField money;
	private JButton button;
	private boolean ifsuccess;
	
	public TransferCheckingFrame(String username) {
		this.setUsername(username);
		this.setIfsuccess(false);
		this.initcomps();
	}
	
	public void initcomps() {
		this.setMoney(new JTextField(20));
		this.setButton(new JButton("Transfer"));
		this.button.addActionListener(new ButtonListener());
		
		JLabel input = new JLabel("Enter your number:",SwingConstants.RIGHT);
		this.add(input);
		this.add(this.money);
		this.add(new JPanel());
		this.add(this.button);
		
		this.setTitle("Tranfer money from saving account to checking account");
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

	public boolean isIfsuccess() {
		return ifsuccess;
	}

	public void setIfsuccess(boolean ifsuccess) {
		this.ifsuccess = ifsuccess;
		if(this.ifsuccess) {
			this.dispose();
		}
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int money_input =0;
			JFrame warning = new JFrame();
			
			if (checkInt(money.getText()) == false) {
				JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
			}
			else if (!getDepositCurrency("Saving",username).equals(getDepositCurrency("Checking",username))) {
				JOptionPane.showMessageDialog(warning, "Your checkng account has different currency! You cannot transfer from saving to checking!");
			}
			else {
				money_input = Integer.parseInt(money.getText());
				int old_savingmoney = getDepositMoney("Saving",username);
				int old_checkingmoney = getDepositMoney("Checking",username);
				if(money_input > old_savingmoney) {
					JOptionPane.showMessageDialog(warning, "You cannot transfer over your current account balance!");
				}
				else {
					int new_savingmoney = old_savingmoney - money_input;
					int new_checkingmoney = old_checkingmoney + money_input -2;
					String currency = getDepositCurrency(username, "Saving");
					
					DataModify.modifyMoney(GetData.createFilePath("info.txt"),username, "Saving", new_savingmoney);
					DataModify.modifyMoney(GetData.createFilePath("info.txt"),username, "Checking", new_checkingmoney);
					DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+2);
					
					String content = GetDate.currentDate() +":"+ username + " transfers " + money_input + " from saving account to checking account.";
					WriteData.writeData(username, content);
					WriteData.writeTransaction(GetDate.currentDate(), content);
					
					setIfsuccess(true);
					JOptionPane.showMessageDialog(warning, "You will be charged 2 for transfering money from saving to checking!");
				}
			}
			
		}
	
	}

}
