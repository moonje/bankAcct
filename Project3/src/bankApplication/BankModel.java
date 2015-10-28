package bankApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**********************************************************************
 * Bank Model Description 
 * 
 * @author Kelsey Brennan
 * @author Jennifer Moon
 * @author Molly Alger
 * @version 10/24/2015
 *********************************************************************/
public class BankModel extends AbstractTableModel {

	/** ArrayList of Accounts **/
	private ArrayList<Account> acts; 

	/** Names of the Columns **/
	private String[] colNames = {"Account Number", "Account Owner", 
			"Date Opened", "Account Balance", "Account Info"};	

	/*******************************************************************
	 * Constructor
	 ******************************************************************/
	public BankModel(){
		acts = new ArrayList<Account>();
	}

	/*******************************************************************
	 * Creates a new account
	 * 
	 * @param acct, an Account
	 ******************************************************************/
	public void newAccount(Account acct){
		acts.add(acct);

		this.fireTableDataChanged();
	}

	/******************************************************************
	 * Removes the given account from the ArrayList.
	 * 
	 * @param account the index of the Account object to be removed 
	 * from the ArrayList
	 *****************************************************************/
	public void deleteAccount(int account) {
		acts.remove(account);
		this.fireTableDataChanged();
	}

	/******************************************************************
	 * Returns the number of columns 
	 * 
	 * @return the number of columns 
	 *****************************************************************/
	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	/******************************************************************
	 * Returns the the number of rows 
	 * 
	 * @return the number of rows 
	 *****************************************************************/
	@Override
	public int getRowCount() {
		return acts.size();
	}

	/******************************************************************
	 * Returns the object at a specified position 
	 * 
	 * @param the column the object is at
	 * @param the row the column is at 
	 *****************************************************************/
	@Override
	public Object getValueAt(int row, int column) {

		//Making a change so it will push through. 
		//Should be row and then column. 

		switch(column){

		case 0: 
			return "" + acts.get(row).getAccountNumber();

		case 1:
			return acts.get(row).calendarToString(acts.get(row).
					getDateOpened());

		case 2:
			return acts.get(row).getAccountOwner();

		case 3:
			NumberFormat fmt = NumberFormat.getCurrencyInstance();
			return fmt.format(acts.get(row).getAccountBalance());
			
		case 4:
			NumberFormat f = NumberFormat.getCurrencyInstance();
			NumberFormat n = NumberFormat.getPercentInstance();
			f.setMinimumFractionDigits(2);
			if(acts.get(row) instanceof CheckingAccount){
				
				return "Monthly Fee: " + 
						f.format(((CheckingAccount) acts.get(row)).
						getMonthlyFee());
				
			}
			else{
				return "Interest: " + n.format(
						(((SavingsAccount)acts.get(row)).
						getInterestRate())/100) +
						"  Minimum Balance: " + 
						f.format(((SavingsAccount)acts.get(row)).
						getMinBalance());
			}
		}

		return null;
	}

	/******************************************************************
	 * Returns an account in the arraylist
	 * 
	 * @param index, the index of the account
	 *****************************************************************/
	public Account getAccountAt(int index){
		return acts.get(index);
	}

	/******************************************************************
	 *
	 *@return String of the column name
	 *@param column the desired column
	 *****************************************************************/
	@Override
	public String getColumnName(int column){
		String[] columnNames = { "Number", "Date Opened", 
				"Account Owner", "Current Balance", "Account Info" };
		return columnNames[column];
	}


	/******************************************************************
	 * Sorts the accounts in increasing order by the account number.
	 *****************************************************************/
	public void sortByAccountNumber() {
		if (acts.size() > 1) {
			Collections.sort(acts, new AccountNumberComparator());
			fireTableRowsUpdated(0, acts.size()-1);
		}
	}
	
	/******************************************************************
	 * Sorts the accounts in alphabetical order by the account owner's
	 * name.
	 *****************************************************************/
	public void sortByAccountName() {
		if (acts.size() > 1) {
			Collections.sort(acts, new AccountOwnerComparator());
			fireTableRowsUpdated(0, acts.size()-1);
		}
	}
	
	/******************************************************************
	 * Sorts the accounts by the date the account was opened.
	 *****************************************************************/
	public void sortByAccountDateOpened() {
		if (acts.size() > 1) {
			Collections.sort(acts, new AccountDateOpenedComparator());
			fireTableRowsUpdated(0, acts.size()-1);
		}
	}

	/******************************************************************
	 * Saves the array list of accounts to a serialized binary file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			binary file
	 *****************************************************************/
	public void saveBinary(String filename){
		
		try{
			FileOutputStream fo = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(acts);
			os.close();
		}
		catch(IOException e){
			
			e.printStackTrace();
			
		}
	}
	
	/******************************************************************
	 * Loads the array list of accounts from a serialized binary file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			binary file
	 *****************************************************************/
	public void loadBinary(String filename){
		
		try{
			FileInputStream fi = new FileInputStream(filename);
			ObjectInputStream is = new ObjectInputStream(fi);
			acts = (ArrayList<Account>)is.readObject();
			fireTableRowsUpdated(0, acts.size()-1);
			is.close();
		}
		catch(IOException e){
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
			
	}
	
	/******************************************************************
	 * Saves the array list of accounts to a text file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			text file
	 *****************************************************************/
	public void saveText(String filename){
		
		PrintWriter out = null;
		try {
			
			//open and read file
			out = new PrintWriter(new BufferedWriter(new FileWriter
					(filename)));
			
			//print to file
			for (int i = 0; i < acts.size(); i++){
				
				Account account = acts.get(i);
				
				if (account instanceof CheckingAccount){
					
					CheckingAccount cacct = (CheckingAccount) account; 
					
					out.print("Checking," + 
							cacct.getAccountNumber() + "," + 
							cacct.getAccountOwner() + "," + 
							cacct.calendarToString(cacct.
									getDateOpened()) + 
							","  + cacct.getAccountBalance() + "," +
							cacct.getMonthlyFee() + "\n");
					
				} else if (account instanceof SavingsAccount){
					
					SavingsAccount sacct = (SavingsAccount) account; 
					
					out.print("Savings," + 
							sacct.getAccountNumber() + "," + 
							sacct.getAccountOwner() + "," + 
							sacct.calendarToString(sacct.
									getDateOpened()) + 
							","  + sacct.getAccountBalance() + "," +
							sacct.getMinBalance() + "," + 
							sacct.getInterestRate() + "\n");
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			//close file
			out.close();
		}	
	}
	
	/******************************************************************
	 * Loads the array list of accounts from a text file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			text file
	 *****************************************************************/
	public void loadText(String filename){
		
		try {
			// open the data file
			Scanner fileReader = new Scanner(new File(filename));
			
			acts.clear();

			//While there is a next line
            while (fileReader.hasNextLine()) {
            	
            	String text = fileReader.nextLine();
            	
    			String [] part = text.split(",");
    			
    			if (part[0].equals("Checking")){
    				
    				int accountNumber = Integer.parseInt(part[1]);
    				
    				//part[2] is the account owner 
    				
    				String [] date = part[3].split("/");
    				int month = Integer.parseInt(date[0]);
    				int day = Integer.parseInt(date[1]);
    				int year = Integer.parseInt(date[2]);
    				GregorianCalendar greg = new GregorianCalendar(year, 
    						month - 1, day);
    				
    				double balance = Double.parseDouble(part[4]);
    				
    				double fee = Double.parseDouble(part[5]);
    				
    				CheckingAccount check = new CheckingAccount(
    						accountNumber, part[2], greg, balance, fee);
    						
    				newAccount(check);
    				
    			} else if (part[0].equals("Savings")){
    				
  				int accountNumber = Integer.parseInt(part[1]);
    				
    				//part[2] is the account owner 
    				
    				String [] date = part[3].split("/");
    				int month = Integer.parseInt(date[0]);
    				int day = Integer.parseInt(date[1]);
    				int year = Integer.parseInt(date[2]);
    				GregorianCalendar greg = new GregorianCalendar(year, 
    						month - 1, day);
    				
    				double balance = Double.parseDouble(part[4]);
    				
    				double minBal = Double.parseDouble(part[5]);
    				
    				double interest = Double.parseDouble(part[6]);
    				
    				SavingsAccount save = new SavingsAccount(
    						accountNumber, part[2], greg, balance,
    						minBal, interest);
    				
    				newAccount(save);
    			}
            	
            }

			//close file
			fileReader.close();

		}

		// could not find file
		catch (FileNotFoundException error) {
			System.out.println("File not found ");
		}

		// problem reading the file
		catch (IOException error) {
			System.out.println("Oops!  Something went wrong.");
		}
	}
	
	/******************************************************************
	 * Saves the array list of accounts to an XML file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			xml file
	 *****************************************************************/
	public void saveXML(String filename){
		
		
		
	}
	
	/******************************************************************
	 * Loads the array list of accounts from an xml file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			xml file
	 *****************************************************************/
	public void loadXML(String filename){
		
		
		
	}
}