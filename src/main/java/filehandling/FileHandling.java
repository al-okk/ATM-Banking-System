package filehandling;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import accountdetail.CreateAccount;
import accountdetail.TakingAccountDetail;
import constant.ConstantClass;
import transaction.StatementList;
import validation.InputValidation;

public class FileHandling extends InputValidation{

	public static void createFile(List<CreateAccount> list) {

		String filePath=ConstantClass.ACCOUNT_FILE;
		try(CSVWriter writer=new CSVWriter(new FileWriter(filePath));){
			String[] header= {"Name", "Account Number", "Password", "Account Type", "Available Money"};
			writer.writeNext(header);
			for(CreateAccount data:list) {
				String name=data.getName();
				int accountNumber=data.getAccountNumber();
				String password=data.getPassword();
				String accountType=data.getAccountType();
				int availableMoney=data.getAvailableMoney();
				String[] detail= {name, String.valueOf(accountNumber), password, accountType, String.valueOf(availableMoney)};
				writer.writeNext(detail);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void readFile() {
		String filePath=ConstantClass.ACCOUNT_FILE;
		File accountFile=new File(ConstantClass.ACCOUNT_FILE);
		if(accountFile.exists()) {
			try(CSVReader csvReader=new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()){
				List<String[]> allData=csvReader.readAll();
				for (String[] data: allData) {
					String name=data[0];
					int accountNumber=Integer.parseInt(data[1]);
					String password=data[2];
					String accountType=data[3];
					int availableMoney=Integer.parseInt(data[4]);
					TakingAccountDetail.getAllList().add(new CreateAccount(name, accountNumber, password, accountType, availableMoney));				
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void createFileTransaction(Map<Integer, ArrayList<StatementList>> map) {
		String filePath=ConstantClass.TRANSACTION_FILE;
		try(CSVWriter writer=new CSVWriter(new FileWriter(filePath));){
			String[] header= {"Account Number", "amount", "Account Type", "Time","Transaction Status", "Total Amount"};
			writer.writeNext(header);

			for(Map.Entry<Integer, ArrayList<StatementList>> e:map.entrySet()) {
				ArrayList<StatementList> aa=e.getValue();
				Iterator<StatementList> i = aa.iterator();
				while(i.hasNext()) {
					StatementList obj= i.next();
					int accountNumber=e.getKey();
					int amount=obj.getAmount();
					String accountType=obj.getAccountType();
					String time=obj.getTime();
					String transactionStatus=obj.getTransactionStatus();
					int totalAmount=obj.getTotalAmount();
					String[] input= {String.valueOf(accountNumber), String.valueOf(amount), accountType, time,transactionStatus, String.valueOf(totalAmount)};
					writer.writeNext(input);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public static void readFileTransaction() {
		String filePath=ConstantClass.TRANSACTION_FILE;
		File transactionFile=new File(ConstantClass.TRANSACTION_FILE);
		if(transactionFile.exists()) {
			try(CSVReader csvReader=new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()){
				List<String[]> allData=csvReader.readAll();
				for(String[] data: allData) {
					int accountNumber=Integer.parseInt(data[0]);
					int amount=Integer.parseInt(data[1]);
					String accountType=data[2];
					String time=data[3];
					String transactionStatus=data[4];
					int totalAmount=Integer.parseInt(data[5]);
					ArrayList<StatementList> arr1;
					ArrayList<StatementList> arr;
					if(m.containsKey(accountNumber)) {
						m.get(accountNumber).add(new StatementList( amount,  accountType,  time, transactionStatus, totalAmount));
					}
					else {
						arr=new ArrayList<>();
						arr1=new ArrayList<>();
						arr1.add(new StatementList( amount,  accountType,  time, transactionStatus, totalAmount));
						arr1.addAll(arr);
						m.put(accountNumber, arr1);

					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		} 

	}


}

