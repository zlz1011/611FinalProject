import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.*;

public class SignOnFrame extends JFrame{
	
	private JTextField personName;
	private JTextField username;
	private JTextField password;
	private JTextField deposit;
	private JButton SignOnButton;
	
	public SignOnFrame() {
		this.initcomp();
	}

	public void initcomp() {
		
		this.setTitle("Register your account here");
		this.setLayout(null);
		// Set frame size to fit the screen
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	public JTextField getPersonname() {
		return personName;
	}

	public void setName(JTextField name) {
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

	public JTextField getDeposit() {
		return deposit;
	}

	public void setDeposit(JTextField deposit) {
		this.deposit = deposit;
	}

	public JButton getSignOnButton() {
		return SignOnButton;
	}

	public void setSignOnButton(JButton signOnButton) {
		SignOnButton = signOnButton;
	}
	
	
	
}
