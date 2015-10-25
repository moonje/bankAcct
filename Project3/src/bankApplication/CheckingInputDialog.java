package bankApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class CheckingInputDialog extends JFrame{

	/** JTextFields for gathering user input **/
	private JTextField accNum = new JTextField();
	private JTextField accOwn = new JTextField();
	private JTextField date = new JTextField();
	private JTextField accBalance = new JTextField();
	private JTextField fee = new JTextField();
	
	private JLabel accountNum;
	private JLabel accountOwn;
	private JLabel dateOpened;
	private JLabel accountBalance;
	private JLabel monthFee;
	
	private JButton create;
	private JButton cancel;
	
	private CheckingAccount check;

	
	public CheckingInputDialog(CheckingAccount c){
		
		super("Create A Checking Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(400,300);
		
		check = c;
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		p.add(createLabels(), BorderLayout.CENTER);
		p.add(createButtons(), BorderLayout.SOUTH);
		
		add(p);
		
		setVisible(true);
		
	}
	
	private JPanel createButtons(){
		
		JPanel buttons = new JPanel();
		ButtonListener listen = new ButtonListener();
		
		create = new JButton("Create");
		create.addActionListener(listen);
		buttons.add(create);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(listen);
		buttons.add(cancel);
		
		return buttons;
		
	}
	
	private JPanel createLabels(){
		
		JPanel labels = new JPanel(new BorderLayout());
		
		JPanel labelPane = new JPanel(new GridLayout(0,1));
		JPanel fieldPane = new JPanel(new GridLayout(0,1));
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		accNum = new JTextField();
		accNum.setBorder(border);
		fieldPane.add(accNum);
		fieldPane.add(Box.createRigidArea(new Dimension(5,5)));
		accOwn = new JTextField();
		accOwn.setBorder(border);
		fieldPane.add(accOwn);
		fieldPane.add(Box.createRigidArea(new Dimension(5,5)));
		date = new JTextField();
		date.setBorder(border);
		fieldPane.add(date);
		fieldPane.add(Box.createRigidArea(new Dimension(5,5)));
		accBalance = new JTextField();
		accBalance.setBorder(border);
		fieldPane.add(accBalance);
		fieldPane.add(Box.createRigidArea(new Dimension(5,5)));
		fee = new JTextField();
		fee.setBorder(border);
		fieldPane.add(fee);

		
		accountNum = new JLabel("Account Number: ");
		accountNum.setLabelFor(accNum);
		labelPane.add(accountNum);
		labelPane.add(Box.createRigidArea(new Dimension(5,5)));
		accountOwn = new JLabel("Account Owner: ");
		accountOwn.setLabelFor(accOwn);
		labelPane.add(accountOwn);
		labelPane.add(Box.createRigidArea(new Dimension(5,5)));
		dateOpened = new JLabel("Date Opened: ");
		dateOpened.setLabelFor(date);
		labelPane.add(dateOpened);
		labelPane.add(Box.createRigidArea(new Dimension(5,5)));
		accountBalance = new JLabel("Account Balance: ");
		accountBalance.setLabelFor(accBalance);
		labelPane.add(accountBalance);
		labelPane.add(Box.createRigidArea(new Dimension(5,5)));
		monthFee = new JLabel("Monthly Fee: ");
		monthFee.setLabelFor(fee);
		labelPane.add(monthFee);

		
		labels.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		labels.add(labelPane, BorderLayout.WEST);
		labels.add(fieldPane, BorderLayout.CENTER);
		
		return labels;
		
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == create){
				
				newChecking();
				
			}
			
			if(e.getSource() == cancel){
				
				dispose();
			}
		}
	}
	
	/******************************************************************
	 * Checks the user's input for correct formatting and inputs and 
	 * creates a new checking account if all user inputs are 
	 * correctly formatted and with acceptable inputs. 
	 * 
	 * @throws IllegalArgumentException if incorrect user input
	 *****************************************************************/
	public void newChecking() throws IllegalArgumentException{
		
		try {
			//Gets the account number
			int acctNum = Integer.parseInt(accNum.getText());
			
			//Gets the date opened 
			String dateString = date.getText();
			String [] part = dateString.split("/"); 
			
			int month; 
			int day; 
			int year; 
			GregorianCalendar greg; 
			
			month = Integer.parseInt(part[0]);
			day = Integer.parseInt(part[1]); 
			year = Integer.parseInt(part[2]);
			greg = new GregorianCalendar(year, month - 1, day);
			
			//Gets the balance 
			double accBal = Double.parseDouble(accBalance.getText());
			
			//Gets the monthly fee
			double accFee = Double.parseDouble(fee.getText());
			
			//Creates a new account
			CheckingAccount cnew = new CheckingAccount(acctNum, 
					accOwn.getText(), greg, accBal, accFee);
			
			//Sends the account to the model 
			//bank.add(cnew);
			
			//Print statement used for checking
			//System.out.println(cnew.toString());
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error in input!");
		}
	}

	public static void main(String[] args) {
		//CheckingInputDialog c = new CheckingInputDialog();
	}
}
