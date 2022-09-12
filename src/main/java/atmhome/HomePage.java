package atmhome;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accountdetail.TakingAccountDetail;
import constant.ConstantClass;
import filehandling.FileHandling;
import transaction.TransactionHomePage;
import validation.InputValidation;

public class HomePage extends InputValidation {

	static final Logger LOGGER=LoggerFactory.getLogger(HomePage.class);

	public static void main(String[] args) {

		TakingAccountDetail accountDetail=new TakingAccountDetail();
		TransactionHomePage transactionHomePage=new TransactionHomePage();
		FileHandling.readFile();
		FileHandling.readFileTransaction();
		int choice;
		do {
			LOGGER.info("Enter Your Choice\n1. Create Account\n2. Transaction\n3. Exit");
			do {
				choice=inputValidation();

			}while(choice<0);
			if(choice==1) {

				accountDetail.takingAccountDetail();
				FileHandling.createFile(TakingAccountDetail.getAllList());
				FileHandling.createFileTransaction(m);
			}
			else if(choice==2) {

				if(TakingAccountDetail.getAllList().isEmpty()) {
					LOGGER.error("Create Account first ");
				}
				else {
					transactionHomePage.transactionHomePage();
				}
			}
			else if(choice==3) {
				break;
			}
			else {
				LOGGER.error(ConstantClass.ENTER_VALID_CHOICE);
			}
		}while(true);

	}

}
