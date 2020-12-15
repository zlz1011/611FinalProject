import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountFrame extends JFrame implements ReadData{

	private String username;
	private JPanel [][] panels;
	private JButton withdraw;
	private JButton deposit;
	/*transfer button is only used for Saving account
	  Since saving account could transfer money to checking account in reality
	*/
	private JButton transfer;
	private int money;
	private String currency;
	private String accountType;
	
	public AccountFrame(String accountType, String username) {
		
		this.setUsername(username);
		this.setAccountType(accountType);
		this.setMoney(this.getDepositMoney(accountType));
		this.setCurrency(this.getDepositCurrency(accountType));
		
		String title = accountType + " Account Operations";
		this.setTitle(title);
		this.setLayout(new GridLayout(7,4));
		this.setSize(1000,800);
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
		this.withdraw.addActionListener(new WithdrawListener());
		this.setDeposit(new JButton("Deposit"));
		this.deposit.addActionListener(new DepositListener());
		this.setTransfer(new JButton("Transfer to Checking"));
		this.transfer.addActionListener(new TransToCheckingListener());
		
		JLabel balance = new JLabel("Current money: ",SwingConstants.RIGHT);
		balance.setFont(new Font("Verdana",Font.PLAIN,22));
		this.panels[2][1].add(balance);
		
		JLabel money_num = new JLabel(String.valueOf(this.money),SwingConstants.CENTER);
		money_num.setFont(new Font("Verdana",Font.PLAIN,22));
		this.panels[2][2].add(money_num);
		
		JLabel money_currency = new JLabel(this.currency,SwingConstants.LEFT);
		money_currency.setFont(new Font("Verdana",Font.PLAIN,22));
		this.panels[2][3].add(money_currency);
		
		if(this.money == 0) {
			JLabel remind1 = new JLabel("You have not opened ",SwingConstants.RIGHT);
			remind1.setForeground(Color.red);
			JLabel remind2 = new JLabel("this type of account.",SwingConstants.LEFT);
			remind2.setForeground(Color.red);
			this.panels[1][1].add(remind1);
			this.panels[1][2].add(remind2);
		}
		
		this.panels[3][0].add(this.withdraw);
		this.panels[3][3].add(this.deposit);
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getDepositMoney(String accountType) {
		ArrayList<String []> read_data = GetData.read(GetData.createFilePath("info.txt"), false);
		int money_num = 0;
		for (int i=0; i<read_data.size(); i++) {
			String [] data = read_data.get(i);
			for (int j=0; j<data.length; j++) {
				if(this.username.equals(data[j])) {
					if (accountType.equals("Saving")) {
						money_num = Integer.parseInt(data[j+3]);
					}
					else {
						money_num = Integer.parseInt(data[j+5]);
					}
				}
			}
		}
		return money_num;
	}
	
	public String getDepositCurrency(String accountType) {
		ArrayList<String []> read_data = GetData.read(GetData.createFilePath("info.txt"), false);
		String currency_type = "USD";
		
		for (int i=0; i<read_data.size(); i++) {
			String [] data = read_data.get(i);
			for (int j=0; j<data.length; j++) {
				if(this.username.equals(data[j])) {
					if (accountType.equals("Saving")) {
						currency_type = data[j+4];
					}
					else {
						currency_type =data[j+6];
					}
				}
			}
		}
		return currency_type;
	}
	
	class WithdrawListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			WithdrawDepositFrame wd_frame = new WithdrawDepositFrame("Withdraw",username,accountType);
		}
		
	}
	
	class DepositListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			WithdrawDepositFrame wd_frame = new WithdrawDepositFrame("Deposit",username, accountType);
		}
		
	}
	
	class TransToCheckingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			TransferCheckingFrame tranCheck =new TransferCheckingFrame(username);
			
		}
		
	}
	
	
}
