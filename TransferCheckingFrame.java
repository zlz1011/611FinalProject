import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferCheckingFrame extends JFrame implements ReadData,CheckInput{
	
	private String username;
	private JTextField money;
	private JButton button;
	private boolean ifsuccess;
	
	public TransferCheckingFrame(String username) {
		this.setUsername(username);
		this.setIfsuccess(false);
		this.initcomps();
	}
	
	public void initcomps() {
		this.setMoney(new JTextField(20));
		this.setButton(new JButton("Transfer"));
		this.button.addActionListener(new ButtonListener());
		
		JLabel input = new JLabel("Enter your number:",SwingConstants.RIGHT);
		this.add(input);
		this.add(this.money);
		this.add(new JPanel());
		this.add(this.button);
		
		this.setTitle("Tranfer money from saving account to checking account");
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
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int money_input =0;
			JFrame warning = new JFrame();
			
			if (checkInt(money.getText()) == false) {
				JOptionPane.showMessageDialog(warning, "You must enter a positive integer!");
			}
			else {
				money_input = Integer.parseInt(money.getText());
				int old_savingmoney = getDepositMoney("Saving");
				int old_checkingmoney = getDepositMoney("Checking");
				if(money_input > old_savingmoney) {
					JOptionPane.showMessageDialog(warning, "You cannot transfer over your current account balance!");
				}
				else {
					int new_savingmoney = old_savingmoney - money_input;
					int new_checkingmoney = old_checkingmoney + money_input;
					DataModify.modifyData(GetData.createFilePath("info.txt"),username, "Saving", new_savingmoney);
					DataModify.modifyData(GetData.createFilePath("info.txt"),username, "Checking", new_checkingmoney);
					setIfsuccess(true);
				}
			}
			
		}
	
	}

	@Override
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

	@Override
	public String getDepositCurrency(String accountType) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
