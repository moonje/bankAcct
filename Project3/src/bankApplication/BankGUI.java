package bankApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;

public class BankGUI extends JFrame {

	private BankModel bank;
	private Account acc;

	private Object[][] data = {{"test","test","test","test"},
			{"test","test","test","test"},{"test","test","test","test"}};

	private String[] columnNames = { "Number", "Date Opened", "Account Owner", "Current Balance" };

	private JMenuItem textLoad;
	private JMenuItem textSave;
	private JMenuItem binaryLoad;
	private JMenuItem binarySave;
	private JMenuItem xmlLoad;
	private JMenuItem xmlSave;
	private JMenuItem quit;
	private JMenuItem numberSort;
	private JMenuItem nameSort;
	private JMenuItem dateSort;
	private JMenuItem checking;
	private JMenuItem savings;
	private JMenuItem update;
	private JMenuItem delete;
	
	private JTextArea accNum = new JTextArea();
	private JTextArea accOwn = new JTextArea();
	private JTextArea date = new JTextArea();
	private JTextArea accBalance = new JTextArea();
	private JTextArea fee = new JTextArea();
	private JTextArea interest = new JTextArea();
	private JTextArea minimum = new JTextArea();
	
	private Object[] checkingMessage = 
		{"Account Number: ", accNum, "Account Owner: ",
				accOwn, "Date Opened: ", date,
				"Account Balance: ", accBalance,
				"Monthly Fee: ", fee
		};
	
	private Object[] savingsMessage = 
		{"Account Number: ", accNum, "Account Owner: ",
				accOwn, "Date Opened: ", date,
				"Account Balance: ", accBalance,
				"Interest: ", interest,
				"Minimum Balance: ", minimum
		};

	public BankGUI() {
		
		super("Bank Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(createMenus());
		pack();
		setSize(500,500);
		
		bank = new BankModel();

		JPanel j = new JPanel();
		j.setLayout(new BorderLayout());

		j.add(createTable(), BorderLayout.NORTH);

		add(j);
		
		setVisible(true);

	}
	

	private JPanel createTable() {

		JPanel tables = new JPanel();
		
		/*ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = bank.getAccounts();
		
		for(int i = 0; i < accounts.size(); i++){
			for(int j = 0; j < columnNames.length; j++){
				
				//data[i][j] =
				
			}
			
		}*/

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		tables.add(scrollPane);

		return tables;
	}

	public JMenuBar createMenus() {

		JMenuBar menuBar;
		JMenu file, account, sort;
		// JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		ButtonListener listen = new ButtonListener();

		// Build the first menu.
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().setAccessibleDescription("Save, Load, Quit");
		menuBar.add(file);
		/*
		 * //a group of JMenuItems for(int i = 0; i < fileLabels.length; i++){
		 * menuItem = new JMenuItem(fileLabels[i]); file.add(menuItem); if(i % 2
		 * == 1) file.addSeparator(); }
		 */

		binaryLoad = new JMenuItem("Load from Binary...");
		binaryLoad.getAccessibleContext().setAccessibleDescription("Load from a binary file");
		binaryLoad.addActionListener(listen);
		file.add(binaryLoad);

		binarySave = new JMenuItem("Save to Binary...");
		binarySave.getAccessibleContext().setAccessibleDescription("Save to a binary file");
		binarySave.addActionListener(listen);
		file.add(binarySave);

		file.addSeparator();

		textLoad = new JMenuItem("Load from Text...");
		textLoad.getAccessibleContext().setAccessibleDescription("Load a binary file");
		textLoad.addActionListener(listen);
		file.add(textLoad);

		textSave = new JMenuItem("Save to Text...");
		textSave.getAccessibleContext().setAccessibleDescription("Save to a binary file");
		textSave.addActionListener(listen);
		file.add(textSave);

		file.addSeparator();

		xmlLoad = new JMenuItem("Load from XML...");
		xmlLoad.getAccessibleContext().setAccessibleDescription("Load a binary file");
		xmlLoad.addActionListener(listen);
		file.add(xmlLoad);

		xmlSave = new JMenuItem("Save to XML...");
		xmlSave.getAccessibleContext().setAccessibleDescription("Save to a binary file");
		xmlSave.addActionListener(listen);
		file.add(xmlSave);

		file.addSeparator();

		quit = new JMenuItem("Quit");
		quit.getAccessibleContext().setAccessibleDescription("Quit the program");
		quit.addActionListener(listen);
		file.add(quit);

		
		account = new JMenu("Account");
		account.setMnemonic(KeyEvent.VK_A);
		account.getAccessibleContext().setAccessibleDescription("Account Options");
		menuBar.add(account);
		
		checking = new JMenuItem("New Checking Account");
		checking.getAccessibleContext().setAccessibleDescription("Create a New Checking Account");
		checking.addActionListener(listen);
		account.add(checking);
		
		savings = new JMenuItem("New Savings Account");
		savings.getAccessibleContext().setAccessibleDescription("Create a New Savings Account");
		savings.addActionListener(listen);
		account.add(savings);
		
		update = new JMenuItem("Update Account");
		update.getAccessibleContext().setAccessibleDescription("Update account");
		update.addActionListener(listen);
		account.add(update);
		
		delete = new JMenuItem("Delete Account");
		delete.getAccessibleContext().setAccessibleDescription("Delete account");
		delete.addActionListener(listen);
		account.add(delete);
		
		
		sort = new JMenu("Sort");
		sort.setMnemonic(KeyEvent.VK_S);
		sort.getAccessibleContext().setAccessibleDescription("Sort by various criteria");
		menuBar.add(sort);

		numberSort = new JMenuItem("By Account Number");
		numberSort.getAccessibleContext().setAccessibleDescription("Sort by Account Number");
		numberSort.addActionListener(listen);
		sort.add(numberSort);

		nameSort = new JMenuItem("By Account Owner");
		nameSort.getAccessibleContext().setAccessibleDescription("Sort by Account Owner");
		nameSort.addActionListener(listen);
		sort.add(nameSort);

		dateSort = new JMenuItem("By Date Opened");
		dateSort.getAccessibleContext().setAccessibleDescription("Sort by Date Opened");
		dateSort.addActionListener(listen);
		sort.add(dateSort);

		/*
		 * for(int i = 0; i < sorting.length; i++){ menuItem = new
		 * JMenuItem(sorting[i]); sort.add(menuItem); }
		 */

		return menuBar;

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == textLoad) {

			}

			if (e.getSource() == textSave) {

			}

			if (e.getSource() == binaryLoad) {

			}

			if (e.getSource() == binarySave) {

			}

			if (e.getSource() == xmlLoad) {

			}

			if (e.getSource() == xmlSave) {

			}

			if (e.getSource() == quit) {

				System.exit(0);

			}

			if (e.getSource() == numberSort) {

			}

			if (e.getSource() == nameSort) {

			}

			if (e.getSource() == dateSort) {

			}
			
			if (e.getSource() == checking) {
				
				int option = JOptionPane.showConfirmDialog(null, checkingMessage, 
						"Create a New Checking Account", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					
					
					/*checking(Integer.parseInt(accNum.getText()), 
							accOwn.getText(), date, Double.parseDouble(accBalance.getText()), 
							Double.parseDouble(fee.getText()));*/
					
					/*bank.newCheckingAccount(Integer.parseInt(accNum.getText()), 
							accOwn.getText(), date, Double.parseDouble(accBalance.getText()), 
							Double.parseDouble(fee.getText()));*/


				}
				
				
			}

			if (e.getSource() == savings) {
				
				int option = JOptionPane.showConfirmDialog(null, savingsMessage, 
						"Create a New Savings Account", JOptionPane.OK_CANCEL_OPTION);
				
				try{
				
					if (option == JOptionPane.OK_OPTION)
					{
						/*savings(Integer.parseInt(accNum.getText()), 
							accOwn.getText(), date, Double.parseDouble(accBalance.getText()), 
							Double.parseDouble(minimum.getText()), Double.parseDouble(interest.getText()));*/
					
						/*bank.newSavingsAccount(Integer.parseInt(accNum.getText()), 
								accOwn.getText(), date, Double.parseDouble(accBalance.getText()), 
								Double.parseDouble(minimum.getText()), Double.parseDouble(interest.getText()));*/


					}
				}
				catch(IllegalArgumentException err){
					
					JOptionPane.showMessageDialog(null, "Input values are not valid.");
					
				}
				
			}

			if (e.getSource() == update) {

				//bank.updateAccount(acct, accNum, accOwn, date, accBalance, fee, minimum, interest);
				
			}
			
			if (e.getSource() == delete) {

				//bank.deleteAccount(account);
				
			}
		}
	}
	
	/*******************************************************************
	 * First, checks user inputs; then, if all inputs in correct format, 
	 * creates a new Checking Account through the Bank Model. 
	 * 
	 * @throws IllegalArgumentException if incorrect input 
	 ******************************************************************/
	private void checking(int number, String owner, 
			GregorianCalendar greg, double balance, double fee) 
					throws IllegalArgumentException{
 
		double acctFee;
		
		try {
			//acctFee = Double.parseDouble(fee.getText());

		} catch (RuntimeException e) {
			throw new IllegalArgumentException();
		}
		
		//bank.newCheckingAccount(number, owner, 
		//	greg, balance, acctFee);

	}
	
	/*******************************************************************
	 * First, checks user inputs; then, if all inputs in correct format, 
	 * creates a new Savings Account through the Bank Model. 
	 * 
	 * @throws IllegalArgumentException if incorrect input 
	 ******************************************************************/
	private void savings(int number, String owner, 
			GregorianCalendar greg, double balance, double minimum, double interest) 
					throws IllegalArgumentException{

		double acctInterest; 
		double min = 0; 
		
		try {
			//acctInterest = Double.parseDouble(interest.getText());
			//min = Double.parseDouble(minimum.getText());

		} catch (RuntimeException e) {
			throw new IllegalArgumentException();
		}

		if (min > balance) {
			throw new IllegalArgumentException();
		}
		
		//bank.newSavingsAccount(number, owner, greg, balance, min, 
		//		acctInterest);
	}
	
	/*******************************************************************
	 * Checks user inputs and calls the appropriate method to check 
	 * pieces unique to the certain type of account. 
	 * 0 indicates a checking account and 1 indicates a savings account.
	 * 
	 * @param type, number representing the type of account 
	 * @throws IllegalArgumentException if incorrect input 
	 ******************************************************************/
	private void newAccount(int type) throws IllegalArgumentException{

		String accountOwner = accOwn.getText();

		String dateString = date.getText();
		String [] part = dateString.split("/"); 
		
		int accountNumber; 
		int month; 
		int day; 
		int year; 
		GregorianCalendar greg; 
		double balance; 

		try {
			accountNumber = Integer.parseInt(accNum.getText());	
			
			month = Integer.parseInt(part[0]);
			day = Integer.parseInt(part[1]); 
			year = Integer.parseInt(part[2]);
			greg = new GregorianCalendar(year, month, day);

			balance = Double.parseDouble(accBalance.getText());

		} catch (RuntimeException e) {
			throw new IllegalArgumentException();
		}
		
		/*if (type == 0){
			checking(accountNumber, accountOwner, greg, balance);
		} else if (type == 1){
			savings(accountNumber, accountOwner, greg, balance);
		}*/
	}

	public static void main(String[] args) {
		
		BankGUI gui = new BankGUI();

	}

}



