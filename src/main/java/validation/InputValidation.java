package validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accountdetail.CreateAccount;
import transaction.StatementList;

public class InputValidation {

	protected static Map<Integer, ArrayList<StatementList>> m=new HashMap<>();

	static final Logger LOGGER=LoggerFactory.getLogger(InputValidation.class);

	static Scanner scanner=new Scanner(System.in);

	public static int inputValidation() {
		try {
			int input=scanner.nextInt();
			scanner.nextLine();
			if(input<0) {
				LOGGER.error("Enter Positive Value");
				return input;
			}
			else {
				return input;
			}
		}catch(Exception e) {
			LOGGER.error("Enter Number in Digit Only");
			scanner.nextLine();
			return 0;
		}
	}

	public int checkDetails(List<CreateAccount> arr, int accountNumber) {
		Iterator<CreateAccount> it=arr.iterator();
		int temp=-1;
		while(it.hasNext()) {
			CreateAccount c=it.next();
			if(c.getAccountNumber()==accountNumber) {
				temp=arr.indexOf(c);
				return temp;
			}
		}
		return temp;
	}

	public void transactionDetail(int accountnumber, int amount, String accountType, String time, String crdr, int total) {
		ArrayList<StatementList> arr1;
		ArrayList<StatementList> arr;
		if(m.containsKey(accountnumber)) {
			m.get(accountnumber).add(new StatementList( amount,  accountType,  time, crdr, total));
		}
		else {
			arr=new ArrayList<>();
			arr1=new ArrayList<>();
			arr1.add(new StatementList( amount,  accountType,  time, crdr, total));
			arr1.addAll(arr);
			m.put(accountnumber, arr1);

		}
	}
}

