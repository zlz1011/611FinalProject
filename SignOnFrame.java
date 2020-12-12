import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.*;

public class SignOnFrame extends JFrame{
	
	private JTextField personName;
	private JTextField username;
	private JTextField password;
	private JTextField SavingDeposit;
	private JTextField CheckingDeposit;
	private JCheckBox Saving;
	private JCheckBox Checking;
	private JButton SignOnButton;
	private JComboBox<String> Savingcurrency;
	private JComboBox<String> Checkingcurrency;
	
	public SignOnFrame() {
		this.initcomp();
	}

	public void initcomp() {
		this.setPersonName(new JTextField(20));
		this.setUsername(new JTextField(20));
		this.setPassword(new JTextField(20));
		this.setSavingDeposit(new JTextField(20));
		this.setCheckingDeposit(new JTextField(20));
		this.setSaving(new JCheckBox("Saving Account"));
		this.setChecking(new JCheckBox("Checking Account"));
		this.setSignOnButton(new JButton("Open Your New Account"));
		
		this.setTitle("Register your account here");
		this.setLayout(new GridLayout(9,2));
		this.setSize(400,350);
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

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
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

	public JCheckBox getSaving() {
		return Saving;
	}

	public void setSaving(JCheckBox saving) {
		Saving = saving;
	}

	public JCheckBox getChecking() {
		return Checking;
	}

	public void setChecking(JCheckBox checking) {
		Checking = checking;
	}

	public JButton getSignOnButton() {
		return SignOnButton;
	}

	public void setSignOnButton(JButton signOnButton) {
		SignOnButton = signOnButton;
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

	public void setComponents() {
		
		JLabel name = new JLabel("Name: ",SwingConstants.RIGHT);
		this.add(name);
		this.add(this.personName);
		
		JLabel username = new JLabel("Username: ",SwingConstants.RIGHT);
		this.add(username);
		this.add(this.username);
		
		JLabel password = new JLabel("Set your password: ",SwingConstants.RIGHT);
		this.add(password);
		this.add(this.password);
		
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
		
		this.add(new JPanel());
		this.SignOnButton.setForeground(Color.blue);
		this.add(this.SignOnButton);
		
	}
	
}
