package transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accountdetail.TakingAccountDetail;
import constant.ConstantClass;
import exception.TransferException;
import exception.WithdrawException;
import interfacepack.TransactionInterface;
import validation.InputValidation;
public class Transaction extends InputValidation implements TransactionInterface{
	
	static SimpleDateFormat date=new SimpleDateFormat("hh:mm:ss dd/MM");
	static Date date1=new Date();
	
	TakingAccountDetail listData=new TakingAccountDetail();
	
	Scanner sc=new Scanner(System.in);
	
	static final Logger LOGGER=LoggerFactory.getLogger(Transaction.class);
	
	@Override
	public void depositAmount(){ 	
		int i=0;
		do {
			try {
				int depoAmount=0;
				int accNumber=0;
				LOGGER.info(ConstantClass.ENTER_BANK_ACCOUNT);
				do {	
					accNumber=inputValidation();	
				}while(accNumber<=0);
				int check=checkDetails(TakingAccountDetail.getAllList(),accNumber);		
				if(check!=-1) {
					String accType;
					accType=TakingAccountDetail.getAllList().get(check).getAccountType();
					LOGGER.info("Enter Deposit amount");
					do {
						depoAmount=inputValidation();
					}while(depoAmount<=0);
					int currentBalance=TakingAccountDetail.getAllList().get(check).getAvailableMoney();
					currentBalance+=depoAmount;
					TakingAccountDetail.getAllList().get(check).setAvailableMoney(currentBalance);
					String fg=date.format(new Date());
					transactionDetail(accNumber, depoAmount, accType, fg,"Credit", currentBalance); 
					LOGGER.info("Transaction is successful\nYour account is credited with "+depoAmount);
					break;
				}
				else {
					throw new Exception("Account not found");
				}
			}
			catch(Exception e) {
				i=1;
				LOGGER.info(e.getMessage());
			}
		}while(i==1);

	}
	
	@Override
	public void transferMethod() {

		int i=0;
		do {
			try {
				LOGGER.info("Enter Account number from which you want to transfer amount");
				int accountNumber1=0;
				int accountNumber2=0;
				do {
					accountNumber1=inputValidation();
				}while(accountNumber1<=0);
				LOGGER.info("Enter password from which you want to transfer amount");
				String password1=sc.next();
				LOGGER.info("Enter Account number in which you want to transfer the amount");
				do {
					accountNumber2=inputValidation();
				}
				while(accountNumber2<=0);
				int check1=checkDetails(TakingAccountDetail.getAllList(),accountNumber1);
				int check2=checkDetails(TakingAccountDetail.getAllList(), accountNumber2);
				transferCondition(accountNumber1, accountNumber2, password1, check1, check2);
				break;
			}
			catch(TransferException e) {
				LOGGER.error(e.getMessage());
			}
			catch(Exception e) {
				i=1;
				LOGGER.error("Invalid Input "+e.getMessage());
			}
		}while(i==1);
	}
	
	
	private void transferCondition(int accountNumber1, int accountNumber2, String password1, int check1, int check2)
			throws TransferException {
		int transferAmount;
		if(!(check1!=-1 && check2!=-1)) {
			throw new TransferException("One/both of the Account is/are not found");
		}

		if(!(TakingAccountDetail.getAllList().get(check1).getPassword().equals(password1))) {
			throw new TransferException("Enter correct password");
		}
		LOGGER.info("Enter amount for transfer");

		do {
			transferAmount=inputValidation();
		}while(transferAmount<=0);

		if(check1==check2) {
			throw new TransferException("Both account can not be same");
		}
		int currentAmount1=TakingAccountDetail.getAllList().get(check1).getAvailableMoney();
		int currentAmount2=TakingAccountDetail.getAllList().get(check2).getAvailableMoney();
		if(currentAmount1<=transferAmount) {
			throw new TransferException(ConstantClass.LOW_BALANCE);
		}
		currentAmount1 -=transferAmount;
		currentAmount2 +=transferAmount;
		String accType1;
		String accType2;
		TakingAccountDetail.getAllList().get(check1).setAvailableMoney(currentAmount1);
		TakingAccountDetail.getAllList().get(check2).setAvailableMoney(currentAmount2);
		accType1=TakingAccountDetail.getAllList().get(check1).getAccountType();
		accType2=TakingAccountDetail.getAllList().get(check2).getAccountType();
		String fg=date.format(new Date());
		transactionDetail(accountNumber1, transferAmount, accType1, fg,"Debit", currentAmount1);
		transactionDetail(accountNumber2, transferAmount, accType2, fg,"Credit", currentAmount2);
		LOGGER.info("Transaction is successful");
	}

	@Override
	public void withdrawMoney() {
		int i=0;
		do {
			try {
				int accountNumber=0;
				LOGGER.info(ConstantClass.ENTER_BANK_ACCOUNT);
				do {
					accountNumber=inputValidation();
				}while(accountNumber<=0);
				LOGGER.info("Enter account password");
				String password=sc.next();
				int check=checkDetails(TakingAccountDetail.getAllList(),accountNumber);
				if(check!=-1 ) {
					if(TakingAccountDetail.getAllList().get(check).getPassword().equals(password)) {
						withdrawPrivilegeCurrent(accountNumber, check);
						withdrawNormalCurrent(accountNumber, check);
						withdrawSalarySaving(accountNumber, check);
						withdrawNormalSaving(accountNumber, check);
						break;
					}
					else {
						throw new WithdrawException("Enter correct password");
					}
				}
				else {
					throw new WithdrawException("Account not found");
				}
			}
			catch(WithdrawException e) {
				i=1;
				System.out.println(e.getMessage());
			}
			catch(Exception e) {
				i=1;
				System.out.println("Invalid Input"+e.getMessage());
			}
		}while(i==1);
	}

	private void withdrawNormalSaving(int accountNumber, int check) throws WithdrawException {
		int withdrawAmount;
		if(TakingAccountDetail.getAllList().get(check).getAccountType().equals(ConstantClass.NORMAL_SAVING)) {		
			System.out.println("Enter withdraw amount");
			do {
				withdrawAmount=inputValidation();
			}while(withdrawAmount<=0);
			int currentAmount=TakingAccountDetail.getAllList().get(check).getAvailableMoney();
			if(withdrawAmount<=1000) {
				if(currentAmount>=withdrawAmount+1000) {
					currentAmount -=withdrawAmount;
					String accType;
					accType=TakingAccountDetail.getAllList().get(check).getAccountType();
					String fg=date.format(new Date());
					TakingAccountDetail.getAllList().get(check).setAvailableMoney(currentAmount);
					transactionDetail(accountNumber, withdrawAmount, accType, fg,"Debit", currentAmount);
					System.out.println("Transaction is successful\nYour account is debited with "+withdrawAmount);
				}
				else {
					throw new WithdrawException(ConstantClass.LOW_BALANCE);
				}
			}else {
				throw new WithdrawException("Amount can not be more than 1000");
			}
		}
	}

	private void withdrawSalarySaving(int accountNumber, int check) throws WithdrawException {
		int withdrawAmount;
		if(TakingAccountDetail.getAllList().get(check).getAccountType().equals(ConstantClass.SALARY_SAVING)) {		
			LOGGER.info("Enter withdraw amount");
			do {
				withdrawAmount=inputValidation();
			}while(withdrawAmount<=0);
			if(withdrawAmount<=1000) {
				int currentAmount=TakingAccountDetail.getAllList().get(check).getAvailableMoney();
				if(currentAmount >=withdrawAmount) {
					currentAmount -=withdrawAmount;
					String accType;
					accType=TakingAccountDetail.getAllList().get(check).getAccountType();
					TakingAccountDetail.getAllList().get(check).setAvailableMoney(currentAmount);
					String fg=date.format(new Date());
					transactionDetail(accountNumber, withdrawAmount, accType, fg,"Debit", currentAmount);
					System.out.println("Transaction is successful\nYour account is debited with "+withdrawAmount);
				}
				else {
					throw new WithdrawException(ConstantClass.LOW_BALANCE);
				}
			}else {
				throw new WithdrawException("Amount can not be more than 1000");
			}
		}
	}

	private void withdrawNormalCurrent(int accountNumber, int check) throws WithdrawException {
		int withdrawAmount;
		if(TakingAccountDetail.getAllList().get(check).getAccountType().equals(ConstantClass.NORMAL_CURRENT)) {	
			System.out.println("Enter withdraw amount");
			do {
				withdrawAmount=inputValidation();
			}while(withdrawAmount<=0);
			if(withdrawAmount<=2000) {
				int currentAmount=TakingAccountDetail.getAllList().get(check).getAvailableMoney();
				if(currentAmount >=withdrawAmount) {
					currentAmount -=withdrawAmount;
					String accType;
					accType=TakingAccountDetail.getAllList().get(check).getAccountType();
					TakingAccountDetail.getAllList().get(check).setAvailableMoney(currentAmount);
					String fg=date.format(new Date());
					transactionDetail(accountNumber, withdrawAmount, accType, fg,"Debit", currentAmount);
					LOGGER.info("Transaction is successful\nYour account is debited with "+withdrawAmount);
				}
				else {
					throw new WithdrawException(ConstantClass.LOW_BALANCE);
				}
			}else {
				throw new WithdrawException("Amount can not be more than 1000");
			}
		}
	}

	private void withdrawPrivilegeCurrent(int accountNumber, int check) throws WithdrawException{
		int withdrawAmount;
		if(TakingAccountDetail.getAllList().get(check).getAccountType().equals(ConstantClass.PRIVILEGE_CURRENT)) {		
			LOGGER.info("Enter withdraw amount");
			do {
				withdrawAmount=inputValidation();
			}while(withdrawAmount<=0);
			int currentAmount=TakingAccountDetail.getAllList().get(check).getAvailableMoney();
			if(currentAmount>=withdrawAmount) {
				currentAmount-=withdrawAmount;
				String accType;
				TakingAccountDetail.getAllList().get(check).setAvailableMoney(currentAmount);
				accType=TakingAccountDetail.getAllList().get(check).getAccountType();
				String fg=date.format(new Date());
				transactionDetail(accountNumber, withdrawAmount, accType, fg,"Debit", currentAmount);
				LOGGER.info("Transaction is successful\nYour account is debited with "+withdrawAmount);
			}
			else {
				throw new WithdrawException(ConstantClass.LOW_BALANCE);
			}
		}
	}

}
