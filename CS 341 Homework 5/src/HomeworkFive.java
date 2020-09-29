import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;


/**
 * Main class to run Linked List and find standard deviation.
 * @author Maggie McCormick
 * @version 1.0
 * @since September 25, 2020
 *
 */

public class HomeworkFive {
	public LinkedList list;
	public File inputFile;
	public Scanner fileScanner;
	public String temp = "";
	public ArrayList<Integer> values;
	public String readfromInput = "";
	public String filePath = "";
	public JFrame frame;
	public JButton btnBuild;
	public JTextField readFileTextInput;
	public JButton open;
	public JFileChooser fileChooser;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private JTextArea output_text;
	private JButton btnFileOpener;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeworkFive window = new HomeworkFive();
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
	public HomeworkFive() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Mean & Standard Deviation");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(142, 6, 173, 16);
		frame.getContentPane().add(titleLabel);

		JLabel inputFileLabel = new JLabel("Input from file:");
		inputFileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		inputFileLabel.setBounds(6, 43, 106, 16);
		frame.getContentPane().add(inputFileLabel);

		btnBuild = new JButton("Calculate");
		btnBuild.setBounds(142, 112, 117, 29);
		frame.getContentPane().add(btnBuild);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 43, 230, 61);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea(readfromInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 153, 341, 104);
		frame.getContentPane().add(scrollPane_1);

		output_text = new JTextArea();
		scrollPane_1.setViewportView(output_text);

		btnFileOpener = new JButton("Select a file");
		btnFileOpener.setBounds(-5, 61, 117, 29);
		frame.getContentPane().add(btnFileOpener);

		// Linked List and Array List
		list = new LinkedList();
		values = new ArrayList<Integer>();

	}

	/**
	 * Run this method when the file is opened.
	 */

	public void createEvents() {

		// Select the file and store its path

		btnFileOpener.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				
				// Use button to select file
				
				filePath = chooseFile(); 
				
				// Was file selected or canceled
				
				if (filePath.equals("cancelled")) {
					textArea.setText("cancel button selected");
					output_text.setText("cancel button selected");
				}

				else {

					System.out.println("File path: " + filePath);

					// Store the text that was in the file
					String readfromInput = "";

					try {

						inputFile = new File(filePath); 
						fileScanner = new Scanner(inputFile);

						readfromInput = readFile();

					} catch (FileNotFoundException a) {
						System.out.println("Error - This file could not be found.");
					} finally {
						fileScanner.close();
					}

					// Show the text of the file in the window
					textArea.setText(readfromInput);
				}
			}
		});

		// Find the statistics of the input when this button is clicked

		btnBuild.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}

	/**
	 * Displays GUI output
	 */

	public void buildOutput() {

		if (!temp.equals("invalid")) {

			// Find the sum 
			int sum = list.LinkedListSum();
			int avg = sum / list.count();

			// Find the standard deviation
			double stdDev = list.standardDeviation();

			output_text.setLineWrap(true); // wraps onto same line
			output_text.setWrapStyleWord(true); // wraps onto same line
			output_text.setText("values: " + list.displayFormatted() + "\nmean: " + avg + "\n" + "standard deviation: "
					+ stdDev);
		}

		else {
			output_text.setText("invalid input");
		}
	}

	/**
	 * Read the file selected by the user
	 *  
	 * @return the string of the file's content
	 */

	public String readFile() {
		String sText = ""; 
		String reader = ""; 

		while (fileScanner.hasNext()) { 
			// store variables and add to linked list
			reader = fileScanner.next();
			if (isNumeric(reader)) {
				int element = Integer.parseInt(reader);
				values.add(element); 
				list.addNode(element); 
				sText = sText + reader + "\n";
			} else {
				temp = "invalid"; // stored as invalid as soon as you have an invalid input
				sText = sText + reader + "\n";
			}
		}
		return sText;
	}

	/**
	 * Checks if string contains a number
	 * 
	 * @param str	String to be tested for numeric values
	 * @return 		Returns true if the string contains a number, false otherwise
	 */

	public boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Selects the file to read
	 * 
	 * @return 	The path of the file in a string
	 */

	public String chooseFile() {

		open = new JButton();
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\Maggie\\Desktop"));
		fileChooser.setDialogTitle("Select a File: ");
		
		if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath(); 
		} 
		else {
			fileChooser.hide();
			return "cancelled";
		}
	}
}
