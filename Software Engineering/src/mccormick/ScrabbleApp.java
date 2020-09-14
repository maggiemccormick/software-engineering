package mccormick;

/**
 * Scrabble Tile Program
 * @author Maggie McCormick
 * @version 1.0
 * @since September 6, 2020
 *
 */


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ScrabbleApp {

	private JFrame frame;
	private JTextField textEntered;
	private JButton btnExecute;
	private String finalResult;
	private JScrollPane scrollPane;
	private JTextArea textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleApp window = new ScrabbleApp();
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
	public ScrabbleApp() {
		initialize();
		createEvents(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		finalResult = "";
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scrabble App");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(170, 10, 117, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please enter four letters (no spaces)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(10, 52, 167, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		textEntered = new JTextField();
		textEntered.setBounds(235, 60, 143, 19);
		frame.getContentPane().add(textEntered);
		textEntered.setColumns(10);
		
		btnExecute = new JButton("Click Here");
		btnExecute.setBounds(170, 94, 117, 21);
		frame.getContentPane().add(btnExecute);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 136, 380, 117);
		frame.getContentPane().add(scrollPane);
		
		textField = new JTextArea();
		scrollPane.setViewportView(textField);
	}
	
	/**
	 * Create the event that is triggered by the button being clicked.
	 */
	
	private void createEvents() {
		btnExecute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	/**
	 * Builds the permutations from the input. Also checks if there are errors in the input. 
	 */
	
	private void buildOutput() {
		String textIn = textEntered.getText();
		String str = textIn; 
        int n = str.length(); 
        boolean lessFour = true;
        boolean lettOnly = true; 
        
        if(n > 4) {
        	textField.setText("Please only enter four letters with no spaces between");
        	lessFour = false;
        }
        for(int i = 0; i < n; i++) {
        	Boolean flag = Character.isDigit(str.charAt(i));
        	if(flag)
        		lettOnly = false;
        }
        
        if(!lettOnly)
        	textField.setText("Please only enter four letters with no spaces between");
        
        if(lettOnly && lessFour) {
        	permute(str, 0, n-1); 
        	textField.setText(finalResult);
        }
	}
	
	/** 
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r ending index 
     */
    private void permute(String str, int l, int r) { 
        if (l == r) {	// base case - we got all the way through str
            finalResult += str;	// add the permutation to the output string
            finalResult += "  ";
        }
        else { 
            for (int i = l; i <= r; i++) { 
                str = swap(str,l,i); 
                permute(str, l+1, r); 
                str = swap(str,l,i); 
            } 
        } 
    } 
  
    /** 
     * Swap characters and return as a string
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */
    public String swap(String a, int i, int j) { 
        char temp; 
        char[] charArray = a.toCharArray(); 
        temp = charArray[i] ; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return String.valueOf(charArray); 
    } 
}

