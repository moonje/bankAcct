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
		
		fireTableDataChanged();
	}
	
	/******************************************************************
	 * Removes the given account from the ArrayList.
	 * 
	 * @param account the index of the Account object to be removed 
	 * from the ArrayList
	 *****************************************************************/
	public void deleteAccount(int account) {
		acts.remove(account);
		fireTableDataChanged();
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
	public Object getValueAt(int column, int row) {
		
		switch(column){
		case 0: 
			return acts.get(row).getAccountNumber();
		case 1:
			return acts.get(row).getDateOpened();
		case 2:
			return acts.get(row).getAccountOwner();
		case 3:
			return acts.get(row).getAccountBalance();
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
	
	
	//add method to find
	
	//add methods to sort accounts on required fields
	
	//add methods to load/save accounts from/to a binary file	
}

