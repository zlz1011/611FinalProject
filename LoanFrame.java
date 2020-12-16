import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanFrame extends JFrame implements ReadData,CheckInput{
	
	private String username;
	private JTextField value;
	private JButton loanButton;
	private boolean exit;

	public LoanFrame(String username) {
		this.setUsername(username);
		this.setExit(false);
		this.initcomps();
	}
	
	public void initcomps() {
		this.setValue(new JTextField(20));
		this.setLoanButton(new JButton("Get Loans"));
		this.loanButton.addActionListener(new LoanButtonListener());
		
		JLabel input = new JLabel("Enter collateral's value:",SwingConstants.RIGHT);
		this.add(input);
		this.add(this.value);
		this.add(new JPanel());
		this.add(this.loanButton);
		
		this.setTitle("Request Loan");
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

	public JTextField getValue() {
		return value;
	}

	public void setValue(JTextField value) {
		this.value = value;
	}

	public JButton getLoanButton() {
		return loanButton;
	}

	public void setLoanButton(JButton loanButton) {
		this.loanButton = loanButton;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
		if(this.exit) {
			this.dispose();
		}
	}
	
	class LoanButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String money_input = value.getText();
			JFrame warning = new JFrame();
			
			if (checkInt(money_input)==false) {
				JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
			}
			else if (!getDepositCurrency("Saving",username).equals("USD") &&
					!getDepositCurrency("Checking",username).equals("USD")) {
				JOptionPane.showMessageDialog(warning, "You must have a USD account to request a loan!");
			}
			else {
				int money = Integer.parseInt(money_input);
				DataModify.modifyLoan(GetData.createFilePath("info.txt"), username, money);
				String content = GetDate.currentDate() +":"+ username + " receives " + money + " USD loans from our bank.";
				WriteData.writeData(username, content);
				WriteData.writeTransaction(GetDate.currentDate(), content);
				String remind = "You have successfully took out loan: " + money;
				JOptionPane.showMessageDialog(warning, remind);
				setExit(true);
			}
		}
		
	}

}
