package transaction;

public class StatementList {
	private String accountType;
	private int amount;
	private String time;
	private String transactionStatus;
	private int totalAmount;
	
	public StatementList(int amount, String accountType,  String time, String transactionStatus, int totalAmount) {
		super();
		this.accountType = accountType;
		this.amount = amount;
		this.time = time;
		this.transactionStatus = transactionStatus;
		this.totalAmount = totalAmount;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
