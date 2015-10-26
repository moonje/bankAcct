package bankApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

	//add method to find

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

	//add methods to load/save accounts from/to a binary file	
	public void saveDatabase(String filename){
		
		try{
			FileOutputStream fo = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(acts);
			os.close();
		}
		catch(IOException e){
			
			
		}
		
		//load: fireIntervalAdded(this 0, listAuto.size()-1);
		
	}
}