import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author lingdean
 * The CloseAccountFrame is used to let user close his/her account. 
 * User cannot close his/her account if he/she has loan.
 */
public class CloseAccountFrame extends JFrame implements ReadData{

	private JTextField username;
	private JPasswordField password;
	private JButton closeButton;
	private boolean ifsuccess;
	
	public CloseAccountFrame() {
		this.setIfsuccess(false);
		this.initcomps();
	}
	
	public void initcomps() {
		this.setUsername(new JTextField(20));
		this.setPassword(new JPasswordField(20));
		this.setCloseButton(new JButton("Close Your Account"));
		this.closeButton.addActionListener(new CloseAccountListener());
		
		JLabel user_name = new JLabel("Enter your Username: ",SwingConstants.RIGHT);
		this.add(user_name);
		this.add(this.username);
		
		JLabel pass_word = new JLabel("Enter your password: ",SwingConstants.RIGHT);
		this.add(pass_word);
		this.add(this.password);
		
		this.add(new JPanel());
		this.add(this.closeButton);
		
		this.setTitle("Close your account");
		this.setLayout(new GridLayout(3,2));
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	public JButton getCloseButton() {
		return closeButton;
	}
	public void setCloseButton(JButton closeButton) {
		this.closeButton = closeButton;
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
	
	class CloseAccountListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String user_name = username.getText();
			String pass_word = new String(password.getPassword());
			
			ArrayList<String []> read_data = GetData.read(GetData.createFilePath("namePass.txt"), false);
			boolean found = false;
			JFrame warning = new JFrame();
			
			for (int i=0; i<read_data.size(); i++) {
				String [] data = read_data.get(i);
				//check if the username/password correct
				if (user_name.equals(data[0]) && pass_word.equals(data[1])) {
					if (getLoan(user_name)>0) {
						JOptionPane.showMessageDialog(warning, "You have to pay all your loan before close your account!");
						found = true;
					}
					else {
						found = true;
						if (getDepositMoney("Saving",user_name)>0) {
							int money =  getDepositMoney("Saving",user_name);
							String currency = getDepositCurrency("Saving",user_name);
							//modify user/bank money
							DataModify.modifyMoney(GetData.createFilePath("info.txt"), user_name, "Saving", money-3);
							DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+3);
						}
						else if (getDepositMoney("Checking",user_name)>0){
							int money =  getDepositMoney("Checking",user_name);
							String currency = getDepositCurrency("Checking",user_name);
							//modify user/bank money
							DataModify.modifyMoney(GetData.createFilePath("info.txt"), user_name, "Checking", money-3);
							DataModify.modifyBankMoney(GetData.createFilePath("bank_money.txt"), currency, getBankMoney(currency)+3);
						}
						//delete user info
						DataModify.DeleteUser(GetData.createFilePath("namePass.txt"), user_name);
						DataModify.DeleteUser(GetData.createFilePath("info.txt"), user_name);
						setIfsuccess(true);
						JOptionPane.showMessageDialog(warning, "You have successfully closed your account! You will be charged 5 for closing your account!");
					}
				}
			}
			
			if(found == false) {
				JOptionPane.showMessageDialog(warning, "The username or the password is not correct!");
			}
		}
		
	}
}
