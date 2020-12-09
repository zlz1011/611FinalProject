import javax.swing.*;

public class EnrolledFrame extends JFrame{

	private JButton CheckingButton;
	private JButton SavingButton;
	private JButton LoansButton;
	private JButton ExitButton;
	private JButton BalanceButton;
	private JButton TransactionButton;
	private JButton StockButton;
	
	
	public EnrolledFrame() {
		this.initcomp();
	}
	
	public void initcomp() {
		this.setCheckingButton(new JButton("Checking Account"));
		this.setSavingButton(new JButton("Saving Account"));
		this.setLoansButton(new JButton("Request Loan"));
		this.setBalanceButton(new JButton("Current Balance"));
		this.setTransactionButton(new JButton("Transactions"));
		this.setStockButton(new JButton("StockButton"));
	}

	public JButton getCheckingButton() {
		return CheckingButton;
	}

	public void setCheckingButton(JButton checkingButton) {
		CheckingButton = checkingButton;
	}

	public JButton getSavingButton() {
		return SavingButton;
	}

	public void setSavingButton(JButton savingButton) {
		SavingButton = savingButton;
	}

	public JButton getLoansButton() {
		return LoansButton;
	}

	public void setLoansButton(JButton loansButton) {
		LoansButton = loansButton;
	}

	public JButton getExitButton() {
		return ExitButton;
	}

	public void setExitButton(JButton exitButton) {
		ExitButton = exitButton;
	}

	public JButton getBalanceButton() {
		return BalanceButton;
	}

	public void setBalanceButton(JButton balanceButton) {
		BalanceButton = balanceButton;
	}

	public JButton getTransactionButton() {
		return TransactionButton;
	}

	public void setTransactionButton(JButton transactionButton) {
		TransactionButton = transactionButton;
	}

	public JButton getStockButton() {
		return StockButton;
	}

	public void setStockButton(JButton stockButton) {
		StockButton = stockButton;
	}
	
}
