import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
/**
 * 
 * @author lingdean
 * WelcomeFrame is the main frame and used when start the program.
 * If has the signin panel to let the user signin.
 */
public class WelcomeFrame extends JFrame {

	private JTextField userText;
	private JPasswordField passwordText;
	private JPanel SignInPanel;
	private JPanel SignUpPanel;
	private JButton SignInButton;
	private JButton SignUpButton;
	private JButton exitButton;
	private JButton managerButton;
	private JButton closeAccountButton;
	private boolean ifenrolled;

	public WelcomeFrame() {
		this.initComponents();
	}

	public void initComponents() {
		this.setUserText(new JTextField(30));
		this.setPasswordText(new JPasswordField(30));
		this.setSignInButton(new JButton("Sign In"));
		this.setSignOnButton(new JButton("Sign Up"));
		this.setExitButton(new JButton("Exit"));
		this.setManagerButton(new JButton("Manager Click Here"));
		this.setCloseAccountButton(new JButton("Close Your Account"));
		this.setSignInPanel(new JPanel());
		this.setSignUpPanel(new JPanel());
		this.setIfenrolled(false);

		this.setTitle("Welcome to our CS611 Bank!");
		this.setLayout(null);
		// Set frame size to fit the screen
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(this.SignInPanel);
		this.add(this.SignUpPanel);
		this.add(this.managerButton);
		this.add(this.closeAccountButton);
		// add listenners
		this.SignInButton.addActionListener(new SignInListener());
		this.SignUpButton.addActionListener(new SignOnListener());
		this.exitButton.addActionListener(new ExitListener());
		this.managerButton.addActionListener(new ManagerListener());
		this.closeAccountButton.addActionListener(new CloseListener());
	}

	public JPanel getSignInPanel() {
		return this.SignInPanel;
	}

	public void setSignInPanel(JPanel signInPanel) {
		this.SignInPanel = signInPanel;
		this.SignInPanel.setLayout(new BoxLayout(this.SignInPanel, BoxLayout.Y_AXIS));
		this.SignInPanel.setBounds(450, 250, 350, 150);
		this.SignInPanel.setBackground(Color.LIGHT_GRAY);

		JLabel username = new JLabel("Username:");
		this.SignInPanel.add(username);

		this.SignInPanel.add(this.userText);

		JLabel password = new JLabel("Password:");
		this.SignInPanel.add(password);

		this.SignInPanel.add(this.passwordText);

		this.SignInPanel.add(this.SignInButton);
		this.SignInPanel.add(this.exitButton);
	}

	public JPanel getSignUpPanel() {
		return SignUpPanel;
	}

	public void setSignUpPanel(JPanel SignUpPanel) {
		this.SignUpPanel = SignUpPanel;
		this.SignUpPanel.setBounds(420, 500, 400, 100);

		JLabel remindText = new JLabel("Not Enrolled?  Sign Up and Open Your Account Now: ");
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

	public JTextField getUserText() {
		return userText;
	}
	
	public String getUsername() {
		return this.userText.getText();
	}

	public void setUserText(JTextField userText) {
		this.userText = userText;
	}

	public JPasswordField getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(JPasswordField passwordText) {
		this.passwordText = passwordText;
	}

	public JButton getManagerButton() {
		return managerButton;
	}

	public void setManagerButton(JButton managerButton) {
		this.managerButton = managerButton;
		this.managerButton.setBounds(20, 20, 200, 40);
	}

	public boolean isIfenrolled() {
		return ifenrolled;
	}

	public void setIfenrolled(boolean ifenrolled) {
		this.ifenrolled = ifenrolled;
		if(this.ifenrolled) {
			this.dispose();
		}
	}
	
	public JButton getCloseAccountButton() {
		return closeAccountButton;
	}

	public void setCloseAccountButton(JButton closeAccountButton) {
		this.closeAccountButton = closeAccountButton;
		this.closeAccountButton.setBounds(20, 80, 200, 40);
	}



	class SignInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user_name = userText.getText();
			String password = new String(passwordText.getPassword());
			
			ArrayList<String []> read_data = GetData.read(GetData.createFilePath("namePass.txt"), false);
			boolean found = false;
			JFrame warning = new JFrame();
			
			for (int i=0; i<read_data.size(); i++) {
				String [] data = read_data.get(i);
				
				if (user_name.equals(data[0]) && password.equals(data[1])) {
					setIfenrolled(true);
					EnrolledFrame enrolled= new EnrolledFrame(user_name);
					found = true;
				}
			}
			
			if(found == false) {
				JOptionPane.showMessageDialog(warning, "The username or the password is not correct!");
			}
			
		}
	}
	
	class ManagerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ManagerLoginFrame manager_frame = new ManagerLoginFrame();
		}
		
	}
	
	class CloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CloseAccountFrame close = new CloseAccountFrame();
		}
		
	}
}


class SignOnListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SignOnFrame frame = new SignOnFrame();
	}

}

class ExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		System.exit(0);

	}

}
