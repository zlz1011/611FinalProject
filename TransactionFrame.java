import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
 * 
 * @author lingdean
 *	The TransactionFrame
 */
public class TransactionFrame extends JFrame{

	private JPanel [][] panels;
	
	public TransactionFrame(String username) {
		this.setPanels(new JPanel[15][1]);
		this.setLabels(username);
		
		this.setTitle("Your recent transactions");
		this.setLayout(new GridLayout(15,1));
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
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
	
	public void setLabels(String username) {
		String filename = "info_"+ username + ".txt";
		ArrayList<String> lines = GetData.readlines(GetData.createFilePath(filename), false);
		
		for (int i=0; i<lines.size(); i++) {
			if (i>14) {
				break;
			}
			JLabel transaction = new JLabel(lines.get(i),SwingConstants.CENTER);
			this.panels[i][0].add(transaction);
		}
	}
	
}
