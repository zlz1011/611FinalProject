import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author lingdean
 * TransferFrame is used when user want to transfer money from his/her account to other user's account.
 */
public class TransferFrame extends JFrame implements ReadData, CheckInput{
	
	private String username;
	private String object_user;
	private JTextField transfer_username;
	private JTextField money;
	private JButton button;
	private boolean ifsuccess;
	private JComboBox<String> account;
	
	public TransferFrame(String username) {
		this.setUsername(username);
		this.setIfsuccess(false);
		this.initcomps();
	}
	
	public void initcomps() {
		this.setTransfer_username(new JTextField(20));
		this.setMoney(new JTextField(20));
		this.setButton(new JButton("Transfer"));
		this.button.addActionListener(new ButtonListener());
		
		String [] accountName = new String[] {"Saving","Checking"};
		this.setAccount(new JComboBox<String>(accountName));
		
		JLabel input1 = new JLabel("Enter others' username:",SwingConstants.RIGHT);
		JLabel input2 = new JLabel("Enter your number:",SwingConstants.RIGHT);
		JLabel input3 = new JLabel("Choose your account:",SwingConstants.RIGHT);
		
		this.add(input1);
		this.add(this.transfer_username);
		this.add(input2);
		this.add(this.money);
		this.add(input3);
		this.add(this.account);
		this.add(new JPanel());
		this.add(this.button);
		
		this.setTitle("Tranfer your money");
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

	public JTextField getTransfer_username() {
		return transfer_username;
	}

	public void setTransfer_username(JTextField transfer_username) {
		this.transfer_username = transfer_username;
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
	
	public String getObject_user() {
		return object_user;
	}

	public void setObject_user(String object_user) {
		this.object_user = object_user;
	}

	public JComboBox<String> getAccount() {
		return account;
	}

	public void setAccount(JComboBox<String> account) {
		this.account = account;
	}

	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int money_input =0;
			JFrame warning = new JFrame();
			setObject_user(transfer_username.getText());
			String account_type = account.getItemAt(account.getSelectedIndex());
			String currency = getDepositCurrency(account_type,username);
			
			if (checkUsername(object_user)==false) {
				JOptionPane.showMessageDialog(warning, "There is no such account username you want to transfer!");
			}
			else if (currency.equals(getDepositCurrency("Saving",object_user))==false &&
					currency.equals(getDepositCurrency("Checking",object_user))==false) {
				JOptionPane.showMessageDialog(warning, "Your and your object's currency unit are not corresponding! Cannot Transfer!");
			}
			else {
				if (checkInt(money.getText()) == false) {
					JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
				}
				else {
					money_input = Integer.parseInt(money.getText());
					int old_money = getDepositMoney(account_type,username);			
					if(money_input > old_money) {
						JOptionPane.showMessageDialog(warning, "You cannot transfer over your current account balance!");
					}
					else {
						int new_money = old_money - money_input;
						DataModify.modifyMoney(GetData.createFilePath("info.txt"),username, account_type, new_money);
						
						if(currency.equals(getDepositCurrency("Saving",object_user)) && getDepositMoney("Saving",object_user)>0) {
							int get_money = getDepositMoney("Saving",object_user) + money_input - 5;
							DataModify.modifyMoney(GetData.createFilePath("info.txt"),object_user, "Saving", get_money);
							String content = GetDate.currentDate() +":"+ username + " transfers " + money_input + " to " + object_user+".";
							//record
							WriteData.writeData(username, content);
							WriteData.writeTransaction(GetDate.currentDate(), content);
							
							String content2 = GetDate.currentDate() +":"+ object_user + " receives " + money_input + " from " + username+".";
							WriteData.writeData(object_user, content2);
							WriteData.writeTransaction(GetDate.currentDate(), content2);
							//charge money
							DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+5);
							JOptionPane.showMessageDialog(warning, "You will be charged 5 for transfering money to other user!");
						}
						else {
							int get_money = getDepositMoney("Checking",object_user) + money_input - 5;
							DataModify.modifyMoney(GetData.createFilePath("info.txt"),object_user, "Checking", get_money);
							String content = GetDate.currentDate() +":"+ username + " transfers " + money_input + " to " + object_user+".";
							//record 
							WriteData.writeData(username, content);
							WriteData.writeTransaction(GetDate.currentDate(), content);
							
							String content2 = GetDate.currentDate() +":"+ object_user + " receives " + money_input + " from " + username+".";
							WriteData.writeData(object_user, content2);
							WriteData.writeTransaction(GetDate.currentDate(), content2);
							//charge money
							DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+5);
							JOptionPane.showMessageDialog(warning, "You will be charged 5 for transfering money to other user!");
						}
						setIfsuccess(true);
					}
				}
			}
			
		}
	
	}

	//check if has the user
	public boolean checkUsername(String username) {
		boolean flag = false;
		String user_name = username;
		ArrayList<String []> read_data = GetData.read(GetData.createFilePath("namePass.txt"), false);
		
		for (int i=0; i<read_data.size(); i++) {
			String [] data = read_data.get(i);
			if(data[0].equals(user_name)) {
				flag = true;
			}
		}
		
		return flag;
	}


}
