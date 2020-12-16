import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;
/**
 * 
 * @author lingdean
 *	SignOnFrame is used when a user opens a new account. 
 *	It asks the basic info of the user.
 *
 */
public class SignOnFrame extends JFrame implements ReadData, CheckInput{
	
	private JTextField personName;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField rePassword;
	private JTextField SavingDeposit;
	private JTextField CheckingDeposit;
	private JRadioButton Saving;
	private JRadioButton Checking;
	private JButton SignOnButton;
	private JComboBox<String> Savingcurrency;
	private JComboBox<String> Checkingcurrency;
	private boolean ifsigned;
	
	public SignOnFrame() {
		this.initcomp();
	}

	public void initcomp() {
		this.setPersonName(new JTextField(20));
		this.setUsername(new JTextField(20));
		this.setPassword(new JPasswordField(20));
		this.setRePassword(new JPasswordField(20));
		this.setSavingDeposit(new JTextField(20));
		this.setCheckingDeposit(new JTextField(20));
		this.setSaving(new JRadioButton("Saving Account"));
		this.setChecking(new JRadioButton("Checking Account"));
		this.setSignOnButton(new JButton("Open Your New Account"));
		this.setIfsigned(false);
		
		this.setTitle("Register your account here");
		this.setLayout(new GridLayout(10,2));
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setComponents();
		this.setVisible(true);
		
	}

	public JTextField getPersonName() {
		return personName;
	}

	public void setPersonName(JTextField name) {
		this.personName = name;
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JPasswordField getRePassword() {
		return rePassword;
	}

	public void setRePassword(JPasswordField rePassword) {
		this.rePassword = rePassword;
	}

	public JTextField getSavingDeposit() {
		return SavingDeposit;
	}

	public void setSavingDeposit(JTextField savingDeposit) {
		SavingDeposit = savingDeposit;
	}

	public JTextField getCheckingDeposit() {
		return CheckingDeposit;
	}

	public void setCheckingDeposit(JTextField checkingDeposit) {
		CheckingDeposit = checkingDeposit;
	}

	public JRadioButton getSaving() {
		return Saving;
	}

	public void setSaving(JRadioButton saving) {
		Saving = saving;
	}

	public JRadioButton getChecking() {
		return Checking;
	}

	public void setChecking(JRadioButton checking) {
		Checking = checking;
	}

	public JButton getSignOnButton() {
		return SignOnButton;
	}

	public void setSignOnButton(JButton signOnButton) {
		SignOnButton = signOnButton;
		this.SignOnButton.addActionListener(new OpenAccountListener());
	}
	
	public JComboBox<String> getSavingCurrency() {
		return this.Savingcurrency;
	}

	public void setSavingCurrency(JComboBox<String> currency) {
		this.Savingcurrency = currency;
	}
	
	public JComboBox<String> getCheckingcurrency() {
		return Checkingcurrency;
	}

	public void setCheckingcurrency(JComboBox<String> checkingcurrency) {
		Checkingcurrency = checkingcurrency;
	}

	public boolean isIfsigned() {
		return ifsigned;
	}

	public void setIfsigned(boolean ifsigned) {
		this.ifsigned = ifsigned;
		
		if (this.ifsigned==true) {
			this.dispose();
		}
	}

	public void setComponents() {
		
		JLabel name = new JLabel("Full Name: ",SwingConstants.RIGHT);
		this.add(name);
		this.add(this.personName);
		
		JLabel username = new JLabel("Username: ",SwingConstants.RIGHT);
		this.add(username);
		this.add(this.username);
		
		JLabel password = new JLabel("Set your password: ",SwingConstants.RIGHT);
		this.add(password);
		this.add(this.password);
		
		JLabel re_password = new JLabel("Re-enter your password: ",SwingConstants.RIGHT);
		this.add(re_password);
		this.add(this.rePassword);
		
		this.add(this.Saving);
		this.add(this.Checking);
		
		JLabel savingMoney = new JLabel("Saving Deposit Money:",SwingConstants.RIGHT);
		this.add(savingMoney);
		this.add(this.SavingDeposit);
		
		JLabel checkingMoney = new JLabel("Checking Deposit Money:",SwingConstants.RIGHT);
		this.add(checkingMoney);
		this.add(this.CheckingDeposit);
		
		String [] currencyName = new String[] {"USD","EUR", "GBP", "CNY", "AUD"};
		this.setSavingCurrency(new JComboBox<String>(currencyName));
		this.setCheckingcurrency(new JComboBox<String>(currencyName));
		
		JLabel savingCurrency = new JLabel("Saving Deposit Money Type:",SwingConstants.RIGHT);
		this.add(savingCurrency);
		this.add(this.Savingcurrency);
		
		JLabel checkingCurrency = new JLabel("Checking Deposit Money Type:",SwingConstants.RIGHT);
		this.add(checkingCurrency);
		this.add(this.Checkingcurrency);
		
		JLabel reminder = new JLabel("At Least 1 Account Type and Enter Deposit!",SwingConstants.RIGHT);
		reminder.setForeground(Color.red);
		this.add(reminder);
		this.SignOnButton.setForeground(Color.blue);
		this.add(this.SignOnButton);
		
	}
	
	class OpenAccountListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = personName.getText();
			String user_name = username.getText();
			String pass = new String(password.getPassword());
			String repass = new String(rePassword.getPassword());
			String saving_deposit = SavingDeposit.getText();
			String checking_deposit = CheckingDeposit.getText();
			String saving_currency = Savingcurrency.getItemAt(Savingcurrency.getSelectedIndex());
			String checking_currency = Checkingcurrency.getItemAt(Checkingcurrency.getSelectedIndex());
			
			JFrame warning = new JFrame();
			
			if(name.contains(" ")) {
				JOptionPane.showMessageDialog(warning, "Your name cannot have any space!");
			}
			else if(user_name.contains(" ")) {
				JOptionPane.showMessageDialog(warning, "Your username cannot have any space!");
			}
			else if (!pass.equals(repass)) {
				JOptionPane.showMessageDialog(warning, "The passwords you entered are not same!");
			}
			else if (!Saving.isSelected() && !Checking.isSelected()) {
				JOptionPane.showMessageDialog(warning, "You must selected at least one account type!");
			}
			else if ((saving_deposit.length()==0 && Saving.isSelected()) || (checking_deposit.length()==0 && Checking.isSelected())) {
				JOptionPane.showMessageDialog(warning, "You have to deposit at least 5 to your specific account!");
			}
			else if (checkFilled()==false) {
				JOptionPane.showMessageDialog(warning, "You must fill the required fields!");
			}
			else if (checkNum()==false) {
				JOptionPane.showMessageDialog(warning, "You must enter a positive number(>5) for your deposit money!");
			}
			else if(checkUsername()==true) {
				JOptionPane.showMessageDialog(warning, "This username has been used! Please enter another one!");
			}
			else {
				String nameAndpass = user_name + " " + pass + "\n";
				String allinfo;
				if (Saving.isSelected() && !Checking.isSelected()) {
					allinfo = user_name + " " + name + " "+ pass + " "+ saving_deposit+ " " + 
							saving_currency + " " + "0" + " "+ checking_currency + " " +"null" +" "
							+ "USD" +" "+ GetDate.currentDate()+"\n";
				}
				else if (!Saving.isSelected() && Checking.isSelected()) {
					allinfo =user_name + " " + name + " "+ pass + " "+ "0"+ " " + 
							saving_currency + " " + checking_deposit + " "+ checking_currency + " " +"null" + " "
							+ "USD" +" "+ GetDate.currentDate()+"\n";
				}
				else {
					allinfo = user_name + " " + name + " "+ pass + " "+ saving_deposit+ " " + 
							saving_currency + " " + checking_deposit + " "+ checking_currency + " " +"null" + " "
							+ "USD" +" "+ GetDate.currentDate()+"\n";
				}
				try {
					FileWriter writer1 = new FileWriter(BankSystem.getNpPath(),true);
					writer1.write(nameAndpass);
					writer1.close();
					FileWriter writer2 = new FileWriter(BankSystem.getInfoPath(),true);
					writer2.write(allinfo);
					writer2.close();
					setIfsigned(true);
					JOptionPane.showMessageDialog(warning, "You have successfully opened a new account! You will be charged 3 for opening a new account");
					if (getDepositMoney("Saving",user_name)>0) {
						int money =  getDepositMoney("Saving",user_name);
						String currency = getDepositCurrency("Saving",user_name);
						//charge fee
						DataModify.modifyMoney(GetData.createFilePath("info.txt"), user_name, "Saving", money-3);
						DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+3);
					}
					else {
						int money =  getDepositMoney("Checking",user_name);
						String currency = getDepositCurrency("Checking",user_name);
						//charge fee
						DataModify.modifyMoney(GetData.createFilePath("info.txt"), user_name, "Checking", money-3);
						DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+3);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(warning, "Erro with file system!");
				}
				
			}
			
			String filename = "info_"+user_name + ".txt";
			String path = GetData.createFilePath(filename);
			
			try {
				File userfile = new File(path);
				if (!userfile.exists()) {
					userfile.createNewFile();
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public boolean checkFilled() {
		String name = this.personName.getText();
		String user_name = this.username.getText();
		String pass = new String(this.password.getPassword());
		String repass = new String(this.rePassword.getPassword());
		String saving_deposit = this.SavingDeposit.getText();
		String checking_deposit = this.CheckingDeposit.getText();
		String saving_currency = this.Savingcurrency.getItemAt(Savingcurrency.getSelectedIndex());
		String checking_currency = this.Checkingcurrency.getItemAt(Checkingcurrency.getSelectedIndex());
		
		if (name.length()==0 || user_name.length()==0 || pass.length()==0
				|| saving_currency.length()==0 || checking_currency.length()==0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkNum() {
		String saving_deposit = this.SavingDeposit.getText();
		String checking_deposit = this.CheckingDeposit.getText();
		int s=0;
		int c=0;
		
		boolean flag = true;
		
		try {
			if (this.Saving.isSelected()) {
				s=Integer.parseInt(saving_deposit);
			}
			if (this.Checking.isSelected()) {
				c=Integer.parseInt(checking_deposit);
			}
		}catch(NumberFormatException e){
			flag = false;
		}
		
		if ((this.Saving.isSelected() && s<=5) || (this.Checking.isSelected() && c<=5)) {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean checkUsername() {
		boolean flag = false;
		String user_name = this.username.getText();
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

