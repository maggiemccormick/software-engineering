package mccormick;

/**
 * Password Strength Program
 * @author Maggie McCormick
 * @version 1.0
 * @since September 6, 2020
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PasswordApp {

	private JFrame frame;
	private JTextField textField;
	private JButton btnEnter;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordApp window = new PasswordApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PasswordApp() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password App");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(148, 10, 117, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Password:");
		lblNewLabel_1.setBounds(10, 59, 128, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(192, 56, 176, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnEnter = new JButton("ENTER");
		btnEnter.setBounds(148, 103, 85, 21);
		frame.getContentPane().add(btnEnter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 416, 108);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	/**
	 * Create the event that is triggered by the button being clicked.
	 */
	
	private void createEvents() {
		btnEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	/**
	 * Method retrieves the input, checks if password follows length and space requirements, 
	 * and checks if blocks of characters exceed the limit. 
	 */
	private void buildOutput() {
		// get input password
		String passIn = textField.getText();
		// output to be printed
		String printThis = "";
		boolean lengthOk = true;
		boolean spaceOk = true; 
		
		if(passIn.length() < 8 || passIn.length() > 12) {
			lengthOk = false;
			printThis += "Length of password must be between 8 and 12 characters long. The length of this password is " + passIn.length() + " .";
		}
		
		if(passIn.contains(" ")) {
			spaceOk = false;
			printThis += "Password may not contain spaces. ";
		}
		
		if(lengthOk && spaceOk)
			printThis += findBlocks(passIn);
		
		textArea.setText(printThis);
		
	}
	
	/**
	 * Finds the blocks of matching characters in the inputted password.
	 * @param pass The string the find the blocks in 
	 * @return		The desired statement to give to the user based on their inputted password
	 */
	private String findBlocks(String pass) {
		String sendBack = "";
		int count = 1;
		int counter[] = new int[12];
		int n = 0;
		
		for(int i = 0; i < pass.length()-1; i++) {
			if(pass.charAt(i) == pass.charAt(i+1))
				count++;
			else {
				counter[n] = count;
				n++;
				count = 1;
			}				
		}
		counter[n] = count;
		
		int highBlock = counter[0]; 
		for(int y = 0; y < counter.length; y++) 
			if(counter[y] > highBlock)
				highBlock = counter[y];
		
		sendBack += "The largest block in the password is " + highBlock;
		
		if(highBlock > 2)
			sendBack += ". This password can be made stronger by reducing this block by " + (highBlock - 2);
		else
			sendBack += ". This is a decent password.";
		
		return sendBack;
	}
}
