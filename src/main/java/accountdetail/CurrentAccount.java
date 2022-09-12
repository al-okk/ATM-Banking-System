package accountdetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ConstantClass;
import validation.InputValidation;

public class CurrentAccount extends InputValidation {
	static final Logger LOGGER=LoggerFactory.getLogger(CurrentAccount.class);
	 String currentAccount() {
		do {
			LOGGER.info("Enter Your Choice\n1. Normal Current\n2. Privilege Saving");
			int choice;
			do {
				choice=inputValidation();
			}while(choice<=0);
			if(choice==1) {
				return ConstantClass.NORMAL_CURRENT;
			}else if(choice==2) {
				return ConstantClass.PRIVILEGE_CURRENT;
			}
			else {
				LOGGER.error(ConstantClass.ENTER_VALID_CHOICE);
			}
		}while(true);
	}

}
