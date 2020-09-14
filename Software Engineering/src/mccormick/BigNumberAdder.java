package mccormick;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class BigNumberAdder {

	private JFrame frame;
	private JTextField xField;
	private JTextField yField;
	private JButton btnAdd;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BigNumberAdder window = new BigNumberAdder();
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
	public BigNumberAdder() {
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
		
		JLabel lblNewLabel = new JLabel("Enter ONLY positive integers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(76, 10, 268, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("X:");
		lblNewLabel_1.setBounds(58, 80, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Y:");
		lblNewLabel_2.setBounds(58, 117, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		xField = new JTextField();
		xField.setBounds(113, 80, 231, 19);
		frame.getContentPane().add(xField);
		xField.setColumns(10);
		
		yField = new JTextField();
		yField.setBounds(113, 114, 231, 19);
		frame.getContentPane().add(yField);
		yField.setColumns(10);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(168, 158, 85, 21);
		frame.getContentPane().add(btnAdd);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 192, 416, 61);
		frame.getContentPane().add(textArea);
	}
	
	private void createEvents() {
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	private void buildOutput() {
		String xIn = xField.getText();	// user input for x variable
		String yIn = yField.getText();	// user input for y variable
		boolean xGood = true;	// for testing x input
		boolean yGood = true;	// for testing y input
		String outString = "";	// final output
		
		// check input for errors
		for(int i=0; i < xIn.length() && xGood; i++) {
			Boolean flag = Character.isDigit(xIn.charAt(i));
	        if(!flag) 
	        	xGood = false;
	    }
		for(int i=0; i < yIn.length() && yGood; i++) {
			Boolean flag = Character.isDigit(yIn.charAt(i));
	        if(!flag) 
	        	yGood = false;
	    }
		if(xGood && yGood) {
			// get values for x and y 
			long numX = Long.parseLong(xIn);
			long numY = Long.parseLong(yIn);
			
			// sum x and y
			long theSum = 0;
			theSum = Long.sum(numX, numY);
			outString = outString + theSum;
		}
		else
			outString = "Please enter only positive integers.";
		
		// display final output number
		textArea.setText(outString);
	}
}
