package transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accountdetail.TakingAccountDetail;
import constant.ConstantClass;
import exception.StatementException;
import interfacepack.StatementInterface;
import validation.InputValidation;

public class Statement extends InputValidation implements StatementInterface{

	static final Logger LOGGER=LoggerFactory.getLogger(Statement.class);
	Scanner sc=new Scanner(System.in);
	@Override
	public void accountDetail() {
		try {
			LOGGER.info(ConstantClass.ENTER_BANK_ACCOUNT);
			int accNumber=0;
			do {
				accNumber=inputValidation();
			}while(accNumber<=0);
			LOGGER.info("Enter account password");
			String password=sc.next();
			int check=checkDetails(TakingAccountDetail.getAllList(), accNumber);
			if(check==-1) {
				throw new StatementException("Enter correct password");
			}
			if(TakingAccountDetail.getAllList().get(check).getPassword().equals(password)) {
				LOGGER.info("Name= "+TakingAccountDetail.getAllList().get(check).getName()+"\nAccount Number= "+TakingAccountDetail.getAllList().get(check).getAccountNumber()+"\nAccount Type= "+TakingAccountDetail.getAllList().get(check).getAccountType()+"\nAvailable Money= "+TakingAccountDetail.getAllList().get(check).getAvailableMoney());
			}
		}
		catch(StatementException e) {
			LOGGER.error(e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Invalid Input"+e.getMessage());
		}
	}
	@Override
	public void fullStatement() {
		LOGGER.info(ConstantClass.ENTER_BANK_ACCOUNT);
		int accNum;
		do {
			accNum=inputValidation();
		}while(accNum<=0);
		for(Map.Entry<Integer, ArrayList<StatementList>> e:m.entrySet()) {
			if(e.getKey()==accNum) {
				ArrayList<StatementList> aa=e.getValue();
				Iterator<StatementList> i = aa.iterator();
				while(i.hasNext()) {
					StatementList obj= i.next();
					LOGGER.info("\n"+e.getKey()+" "+obj.getAccountType()+" "+obj.getAmount()+" "+ obj.getTransactionStatus()+" "+obj.getTime()+" "+ obj.getTotalAmount());
				}
			}
		}
	}
	@Override
	public void miniStatement() {
		LOGGER.info(ConstantClass.ENTER_BANK_ACCOUNT);
		int accNum;
		do {
			accNum=inputValidation();
		}while(accNum<=0);
		for(Map.Entry<Integer, ArrayList<StatementList>> e:m.entrySet()) {
			if(e.getKey()==accNum) {
				ArrayList<StatementList> aa=e.getValue();
				int count=0;
				int sizes=aa.size()-10;
				Iterator<StatementList> i = aa.iterator();
				while(i.hasNext()  ) {
					count++;
					StatementList obj=i.next();
					if(count>sizes) {
						LOGGER.info("\n"+e.getKey()+" "+obj.getAccountType()+" "+obj.getAmount()+" "+ obj.getTransactionStatus()+" "+obj.getTime()+" "+ obj.getTotalAmount());
					}
				}
			}
		}
	}
	@Override
	public void rangeStatement() {
		LOGGER.info(ConstantClass.ENTER_BANK_ACCOUNT);
		int accNum;
		do {
			accNum=inputValidation();
		}while(accNum<=0);
		LOGGER.info("Enter the range of amount");
		int range1;
		int range2;
		do {
			range1=inputValidation();
		}while(range1<=0);
		do {
			range2=inputValidation();
		}while(range2<=0);
		for(Map.Entry<Integer, ArrayList<StatementList>> e:m.entrySet()) {
			if(e.getKey()==accNum) {
				ArrayList<StatementList> aa=e.getValue();
				Iterator<StatementList> i = aa.iterator();
				while(i.hasNext()) {
					StatementList obj=i.next();
					if((obj.getAmount()>range1 && obj.getAmount()<=range2) || (obj.getAmount()>range2 && obj.getAmount()<=range1)) {
						LOGGER.info("\n"+e.getKey()+" "+obj.getAccountType()+" "+obj.getAmount()+" "+ obj.getTransactionStatus()+" "+obj.getTime()+" "+ obj.getTotalAmount());
					}
				}
			}

		}
	}

}
