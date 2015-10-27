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
			"Date Opened", "Account Balance"};		

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
				"Account Owner", "Current Balance" };
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
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//print to file
		out.println(this.toString());
		
		//close file
		out.close();

		
		
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

			//CountDownTimer timer = new CountDownTimer(fileReader.nextLine());
			

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