package bankApplication;

import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**********************************************************************
 * Bank Model Description 
 * 
 * @author Kelsey Brennan
 * @author Jennifer Moon
 * @author Molly Alger
 * @version 10/17/2015
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
		
		fireTableRowsInserted(0, acts.size());
	}

	/******************************************************************
	 * Changes the properties for the given Account object.
	 * 
	 * @param index, the index of the account being updated
	 * @param acct, the updated account 
	 *****************************************************************/
	public void updateAccount(int index, Account acct) {
		//removes the old version of the account
		deleteAccount(index);
		
		//creates an account with the updated information 
		newAccount(acct);
	}
	
	/******************************************************************
	 * Removes the given account from the ArrayList.
	 * 
	 * @param account the index of the Account object to be removed 
	 * from the ArrayList
	 *****************************************************************/
	public void deleteAccount(int account) {
		acts.remove(account);
		fireTableRowsDeleted(0, acts.size());
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
	 * @param
	 * @param
	 *****************************************************************/
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/******************************************************************
	 * Returns the account at the specified position 
	 * 
	 * @return account at specified position 
	 * @param index, the index of the account in the arraylist
	 *****************************************************************/
//	public Account getAccountAt(int index){
//		try {
//			returns accts(index);
//		} catch (IndexOutOfBounds e){
//
//		}
//	}
	
	//add method to find
	
	//add methods to sort accounts on required fields
	
	//add methods to load/save accounts from/to a binary file	
}

