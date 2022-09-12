package transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constant.ConstantClass;
import interfacepack.StatementInterface;
import validation.InputValidation;

public class StatementHomePage extends InputValidation {
	
	static final Logger LOGGER=LoggerFactory.getLogger(StatementHomePage.class);
	
	public void statementHomePage(){
		
		StatementInterface statement=new Statement();
		
		int choice;
		do {
			LOGGER.info("Enter Your Choice\n1. Account Detail\n2. Mini Statement\n3. Full Statement\n4. Range Statement\n5. Exit");
			do {
				choice=inputValidation();
			}while(choice<=0);

			if(choice==1) {
				statement.accountDetail();
			}
			else if(choice==2) {
				statement.miniStatement();
			}
			else if(choice==3) {
				statement.fullStatement();
			}
			else if(choice==4) {
				statement.rangeStatement();
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
