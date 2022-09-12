package transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accountdetail.TakingAccountDetail;
import constant.ConstantClass;
import filehandling.FileHandling;
import interfacepack.TransactionInterface;
import validation.InputValidation;

public class TransactionHomePage extends InputValidation{
	
	static final Logger LOGGER=LoggerFactory.getLogger(TransactionHomePage.class);

	public void transactionHomePage() {
		
		TransactionInterface transaction=new Transaction();
		StatementHomePage statment=new StatementHomePage();
		
		
		int choice;
		do {
			LOGGER.info("Enter Your Choice\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Bank Statement\n5. Exit");
			do {
				choice=inputValidation();
			}while(choice<=0);

			if(choice==1) {
				transaction.depositAmount();
				FileHandling.createFile(TakingAccountDetail.getAllList());
				FileHandling.createFileTransaction(m);
			}
			else if(choice==2) {
				transaction.withdrawMoney();
				FileHandling.createFile(TakingAccountDetail.getAllList());
				FileHandling.createFileTransaction(m);
			}
			else if(choice==3) {
				transaction.transferMethod();
				FileHandling.createFile(TakingAccountDetail.getAllList());
				FileHandling.createFileTransaction(m);
			}
			else if(choice==4) {
				statment.statementHomePage();
			}
			else if(choice==5) {
				break;
			}
			else {
				LOGGER.error(ConstantClass.ENTER_VALID_CHOICE);
			}
		}while(true);
	}
}
