package accountdetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ConstantClass;
import validation.InputValidation;

public class SavingAccount extends InputValidation {
	public static final Logger LOGGER=LoggerFactory.getLogger(SavingAccount.class);
	
	String savingAccount() {
		do {
			LOGGER.info("Enter Your Choice\n1. Normal Saving\n2. Salary Saving");
			int choice;
			do {
				choice=inputValidation();
			}while(choice<=0);
			if(choice==1) {
				return ConstantClass.NORMAL_SAVING;
			}else if(choice==2) {
				return ConstantClass.PRIVILEGE_CURRENT;
			}
			else {
				LOGGER.error(ConstantClass.ENTER_VALID_CHOICE);
			}
		}while(true);
	}
}
