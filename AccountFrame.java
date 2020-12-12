import javax.swing.*;
import java.awt.*;

public class AccountFrame extends JFrame{

	private JPanel [][] panels;
	private JButton withdraw;
	private JButton deposit;
	
	/*transfer button is only used for Saving account
	  Since saving account could transfer money to checking account in reality
	*/
	private JButton transfer;
	
	public AccountFrame(String accountType) {
		
		String title = accountType + " Account Operations";
		this.setTitle(title);
		this.setLayout(new GridLayout(7,4));
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initcomp();
		
		if (accountType.equals("Saving")) {
			this.panels[5][0].add(this.transfer);
		}
		
		this.setVisible(true);
	}
	
	public void initcomp() {
		this.setPanels(new JPanel[7][4]);
		this.setWithdraw(new JButton("Withdraw"));
		this.setDeposit(new JButton("Deposit"));
		this.setTransfer(new JButton("Transfer"));
		
		JLabel balance = new JLabel("Current money: ",SwingConstants.RIGHT);
		balance.setFont(new Font("Verdana",Font.PLAIN,22));
		this.panels[2][1].add(balance);
		
		this.panels[3][0].add(this.withdraw);
		this.panels[3][3].add(this.deposit);
		
	}

	public JPanel[][] getPanels() {
		return panels;
	}

	public void setPanels(JPanel[][] panels) {
		this.panels = panels;
		for (int i=0; i<this.panels.length; i++) {
			for (int j=0; j<this.panels[0].length; j++) {
				this.panels[i][j] = new JPanel();
				this.panels[i][j].setLayout(new GridLayout(1,1));
				this.add(this.panels[i][j]);
			}
		}
	}

	public JButton getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(JButton withdraw) {
		this.withdraw = withdraw;
	}

	public JButton getDeposit() {
		return deposit;
	}

	public void setDeposit(JButton deposit) {
		this.deposit = deposit;
	}

	public JButton getTransfer() {
		return transfer;
	}

	public void setTransfer(JButton transfer) {
		this.transfer = transfer;
	}
	
	
}
