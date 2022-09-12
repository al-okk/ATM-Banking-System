package accountdetail;

public class CreateAccount {
	private String name;
	private int accountNumber;
	private String password;
	private String accountType;
	private int availableMoney;
	
	public CreateAccount(String name, int accountNumber, String password, String accountType, int availableMoney) {
		this.name = name;
		this.accountNumber = accountNumber;
		this.password = password;
		this.accountType = accountType;
		this.availableMoney = availableMoney;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAvailableMoney() {
		return availableMoney;
	}
	public void setAvailableMoney(int availableMoney) {
		this.availableMoney = availableMoney;
	}
	
	

}
