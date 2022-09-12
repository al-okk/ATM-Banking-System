package accountdetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ConstantClass;
import filehandling.FileHandling;
import validation.InputValidation;

public class TakingAccountDetail extends InputValidation {

	static final Logger LOGGER=LoggerFactory.getLogger(TakingAccountDetail.class);
	Scanner scanner=new Scanner(System.in);

	static SimpleDateFormat date=new SimpleDateFormat("hh:mm:ss  dd/MM");
	static Date date1=new Date();

	SavingAccount savingAccount= new SavingAccount();
	CurrentAccount currentAccount= new CurrentAccount();
	private static List<CreateAccount> allList=new ArrayList<>();

	public static List<CreateAccount> getAllList() {
		return allList;
	}

	public void takingAccountDetail() {
		String name;
		do {
			LOGGER.info("Enter Your Name");
			name=scanner.nextLine();
		}while(name.matches("(.*)[0-9]+(.*)"));
		int accountNumber;
		int length;
		int check;
		do {
			LOGGER.info("Enter account number length greater than 5 and less than 10");
			accountNumber=inputValidation();
			
			check=checkDetails(allList,accountNumber);
			if(check!=-1) {
				LOGGER.info("Bank Account already exists");
			}
			length= (String.valueOf(accountNumber).length());
		}while(accountNumber<=0 || length<6 || length>9 || check!=-1 );

		String password;
		do {
			LOGGER.info("Enter Your Bank Account Password. Password length Should be Greater than 4 ");
			password=scanner.next();
			scanner.nextLine();
		}while(password.length()<=4 || password.isBlank());
		try {
			String accountType="";
			int availableMoney=0;
			boolean flag=true;
			do {
				LOGGER.info("Enter Your choice\n1. Saving\n2. Current");
				int choice;
				do {
					choice=inputValidation();
				}while(choice<=0);
				if(choice==1) {
					accountType=savingAccount.savingAccount();
					if(accountType.equals(ConstantClass.NORMAL_SAVING)) {
						do {
							LOGGER.info("Enter Initial Amount. Initial Amount should be Greater than 1000");
							availableMoney=inputValidation();
						}while(availableMoney<=999);
						flag=false;
					}else {
						availableMoney=takingInitialMoney();
						flag=false;
					}
				}else if(choice==2) {
					accountType=currentAccount.currentAccount();
					availableMoney=takingInitialMoney();
					flag=false;
				}
				else {
					LOGGER.error(ConstantClass.ENTER_VALID_CHOICE);
				}
			}while(flag);
			allList.add(new CreateAccount(name, accountNumber, password, accountType, availableMoney));
			String fg=date.format(new Date());
			transactionDetail(accountNumber, availableMoney, accountType, fg,"Initial Amount", availableMoney); 
			
			LOGGER.info("Your account is created successfully");
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	private int takingInitialMoney() {
		int availableMoney;
		do {
			LOGGER.info("Enter Initial Money. Initial Amount should be Greater than 0");
			availableMoney=inputValidation();
		}while(availableMoney<=0);
		return availableMoney;
	}
	

}
