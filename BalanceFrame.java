import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.*;

public class BalanceFrame extends JFrame{
	
	private JPanel [][] panels;
	
	public BalanceFrame() {
		this.setPanels(new JPanel[7][4]);
		
		this.setTitle("Account Current Balance");
		this.setLayout(new GridLayout(7,4));
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initcomp();
		this.setVisible(true);
	}
	
	public void initcomp() {
		
		JLabel saving= new JLabel("Current Saving Account Balance:",SwingConstants.RIGHT);
		saving.setFont(new Font("Verdana",Font.PLAIN,18));
		this.panels[2][1].add(saving);
		
		JLabel checking = new JLabel("Current Checking Account Balance:",SwingConstants.RIGHT);
		checking.setFont(new Font("Verdana",Font.PLAIN,18));
		this.panels[3][1].add(checking);
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

	
}
