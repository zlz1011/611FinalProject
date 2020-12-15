import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferFrame extends JFrame{
	
	private String username;
	private JTextField transfer_username;
	private JTextField money;
	private JButton button;
	//private String operation;
	private boolean ifsuccess;
	//private String accountType;
	
	public TransferFrame(String username) {
		this.setUsername(username);
		this.setIfsuccess(false);
		this.initcomps();
	}
	
	public void initcomps() {
		this.setTransfer_username(new JTextField(20));
		this.setMoney(new JTextField(20));
		this.setButton(new JButton("Transfer"));
		this.button.addActionListener(new ButtonListener());
		
		this.setTitle("Tranfer your money");
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

	public JTextField getTransfer_username() {
		return transfer_username;
	}

	public void setTransfer_username(JTextField transfer_username) {
		this.transfer_username = transfer_username;
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
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
		}
	
	}
	

}
