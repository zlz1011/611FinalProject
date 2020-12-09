import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomeFrame extends JFrame{
	
	JPanel SignInPanel;
	JPanel SignUpPanel;
	JButton SignInButton;
	JButton SignUpButton;
	JButton exitButton;

	public WelcomeFrame() {
		this.initComponents();
	}

	public void initComponents() {
		this.setSignInButton(new JButton("Sign In"));
		this.setSignOnButton(new JButton("Sign Up"));
		this.setExitButton(new JButton("Exit"));
		this.setSignInPanel(new JPanel());
		this.setSignUpPanel(new JPanel());
		
		this.setTitle("Welcome to our CS611 Bank!");
		this.setLayout(null);
		//Set frame size to fit the screen
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(this.SignInPanel);
		this.add(this.SignUpPanel);
		//add listenners
		this.SignInButton.addActionListener(new SignInListenner());
		
	}
	
	public JPanel getSignInPanel() {
		return this.SignInPanel;
	}

	public void setSignInPanel(JPanel signInPanel) {
		this.SignInPanel = signInPanel;
		this.SignInPanel.setLayout(new BoxLayout(this.SignInPanel,BoxLayout.Y_AXIS));
		this.SignInPanel.setBounds(450,250,350,150);
		this.SignInPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel username = new JLabel("Username:");
		this.SignInPanel.add(username);
		
		JTextField userText = new JTextField(20);
		//userText.setBounds(100,20,165,25);
		this.SignInPanel.add(userText);
		
		JLabel password = new JLabel("Password:");
		//password.setBounds(10,50,80,25);
		this.SignInPanel.add(password);
		
		JPasswordField passwordText = new JPasswordField(20);
		//passwordText.setBounds(100,50,165,25);
		this.SignInPanel.add(passwordText);
		
		this.SignInPanel.add(this.SignInButton);
		this.SignInPanel.add(this.exitButton);
	}

	public JPanel getSignUpPanel() {
		return SignUpPanel;
	}

	public void setSignUpPanel(JPanel SignUpPanel) {
		this.SignUpPanel = SignUpPanel;
		this.SignUpPanel.setBounds(480,500,200,100);
		
		JLabel remindText = new JLabel("Not Enrolled?  Sign Up Now: ");
		remindText.setForeground(Color.blue);
		this.SignUpPanel.add(remindText);
		this.SignUpPanel.add(this.SignUpButton);
	}

	public JButton getSignInButton() {
		return SignInButton;
	}


	public void setSignInButton(JButton signInButton) {
		SignInButton = signInButton;
	}


	public JButton getSignOnButton() {
		return this.SignUpButton;
	}


	public void setSignOnButton(JButton signOnButton) {
		this.SignUpButton = signOnButton;
	}


	public JButton getExitButton() {
		return exitButton;
	}


	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}
}

class SignInListenner implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Sign in button");
		
	}
	
}
