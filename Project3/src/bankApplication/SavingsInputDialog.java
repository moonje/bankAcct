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
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SavingsInputDialog extends JFrame{

	/** JTextFields for gathering user input **/
	private JTextField accNum = new JTextField();
	private JTextField accOwn = new JTextField();
	private JTextField date = new JTextField();
	private JTextField accBalance = new JTextField();
	private JTextField interest = new JTextField();
	private JTextField minimum = new JTextField();
	
	private JLabel accountNum;
	private JLabel accountOwn;
	private JLabel dateOpened;
	private JLabel accountBalance;
	private JLabel intRate;
	private JLabel minBalance;
	
	private JButton create;
	private JButton cancel;
	
	private SavingsAccount save;
	
	public SavingsInputDialog(SavingsAccount s){
		
		super("Create A Savings Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(400,300);
		
		save = s;
		
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
		interest = new JTextField();
		interest.setBorder(border);
		fieldPane.add(interest);
		fieldPane.add(Box.createRigidArea(new Dimension(5,5)));
		minimum = new JTextField();
		minimum.setBorder(border);
		fieldPane.add(minimum);

		
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
		intRate = new JLabel("Interest Rate: ");
		intRate.setLabelFor(interest);
		labelPane.add(intRate);
		labelPane.add(Box.createRigidArea(new Dimension(5,5)));
		minBalance = new JLabel("Minimum Balance: ");
		minBalance.setLabelFor(minimum);
		labelPane.add(minBalance);

		
		labels.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		labels.add(labelPane, BorderLayout.WEST);
		labels.add(fieldPane, BorderLayout.CENTER);
		
		return labels;
		
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == create){
			
				newSavings();
				
			}
		
			if(e.getSource() == cancel){
			
				dispose();
				
			}
		}
	}

/******************************************************************
 * Checks the user's input for correct formatting and inputs and 
 * creates a new savings account if all user inputs are 
 * correctly formatted and with acceptable inputs. 
 * 
 * @throws IllegalArgumentException if incorrect user input
 *****************************************************************/
public void newSavings() throws IllegalArgumentException{
	
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
		greg = new GregorianCalendar();
		greg.setLenient(false);
		greg.set(year, month - 1, day);
		
		//Gets the balance 
		double accBal = Double.parseDouble(accBalance.getText());
		
		//Gets the minimum balance
		double minBal = Double.parseDouble(minimum.getText());
		
		//Gets the interest rate 
		double intRate = Double.parseDouble(interest.getText());
		
		//Creates a new account
		SavingsAccount snew = new SavingsAccount(acctNum, 
				accOwn.getText(), greg, accBal, minBal, intRate);
		
		//Sends the account to the model 
		//bank.newAccount(snew);
		
		//Print statement used for checking
		//System.out.println(cnew.toString());
		
	} catch (Exception e){
		JOptionPane.showMessageDialog(null, "Error in input!");
	}
}

	public static void main(String[] args) {
		//SavingsInputDialog s = new SavingsInputDialog();
	}
	
}

