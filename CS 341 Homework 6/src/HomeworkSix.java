import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class finds the LOC in a chosen Java file, as well as 
 * generates a list of method names and the LOC in each method 
 * and a list of control structures used in the code. 
 * 
 * Notes: 
 * Lines with a single bracket are counted as one line.
 * Number of lines in a method include method declaration and closing bracket.
 * 
 * @author Maggie McCormick
 * @version 1.0
 * @since September 29, 2020
 *
 */

public class HomeworkSix {
	
	// WINDOW ATTRIBUTES
	private JFrame frame;
	private JButton btnFileSelect;			// select file button
	private JButton btnGenerate;			// generate data button
	private JTextArea locTextArea;			// output area for LOC
	private JTextArea structureTextArea;	// output area for control structures
	private JTextArea methodTextArea;		// output area for methods
	private JTextArea textArea;				// display the file location
	public JButton open;					// open the selected file
	public JFileChooser fileChooser;
	private JScrollPane scrollPane_2;
	
	// CASE INDICATORS
	private boolean inComment = false;		// when in comment
	private boolean inMethod = false;		// when in a method
	
	// STATIC VARIABLES
	private static final String COM_START = "//"; 		// single-line comment indicator
	private static final String COM_MULT_START = "/*";	// multiple-line comment start
	private static final String COM_MULT_END = "*/";	// multiple-line comment end
	private static final String WHILE_LOOP = "while(";	// while-loop indicator
	private static final String FOR_LOOP = "for("; 		// for-loop indicator
	private static final String IF_STATE = "if(";		// if-statement indicator 
	
	// DATA CONTAINERS
	private LinkedList methodList;	// linked list to hold method information
	private int forLoops;			// number of for-loops
	private int whileLoops;			// number of while-loops
	private int ifStates;			// number of if-statements
	private int loc;				// total number of lines of code
	private String nameOfDoc;		// name of the class
	
	// FILE VARIABLES
	private String filePath = "";	// the path of the file
	public File inputFile;			// the file
	public Scanner fileScanner;		// file scanner

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeworkSix window = new HomeworkSix();
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
	public HomeworkSix() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame and the global variables.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Java File Information Generator");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(104, 10, 285, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Java File:");
		lblNewLabel_1.setBounds(10, 42, 121, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnFileSelect = new JButton("Select file");
		btnFileSelect.setBounds(318, 38, 108, 21);
		frame.getContentPane().add(btnFileSelect);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(145, 93, 146, 21);
		frame.getContentPane().add(btnGenerate);
		
		JLabel lblNewLabel_2 = new JLabel("Total Lines of Code:");
		lblNewLabel_2.setBounds(10, 124, 202, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Control Structures");
		lblNewLabel_3.setBounds(10, 152, 130, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Methods");
		lblNewLabel_4.setBounds(220, 152, 88, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		locTextArea = new JTextArea();
		locTextArea.setEditable(false);
		locTextArea.setBounds(141, 124, 167, 22);
		frame.getContentPane().add(locTextArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 175, 158, 78);
		frame.getContentPane().add(scrollPane);
		
		structureTextArea = new JTextArea();
		scrollPane.setViewportView(structureTextArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(220, 175, 206, 78);
		frame.getContentPane().add(scrollPane_1);
		
		methodTextArea = new JTextArea();
		scrollPane_1.setViewportView(methodTextArea);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(94, 40, 214, 41);
		frame.getContentPane().add(scrollPane_2);
		
		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);
		textArea.setEditable(false);
		
		methodList = new LinkedList();
		forLoops = 0;
		whileLoops = 0;
		ifStates = 0;
		loc = 0;
		nameOfDoc = "";
	}
	
	/**
	 * Run this method when the file is opened.
	 */
	public void createEvents() {

		btnFileSelect.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				
				filePath = chooseFile(); 
				
				// Was file selected or canceled
				if(filePath.equals("Cancelled")) {
					textArea.setText("Cancelled");
				}

				else {
					textArea.setText(filePath);

					try {
						inputFile = new File(filePath); 
						fileScanner = new Scanner(inputFile);
						readFile();
					} catch (FileNotFoundException a) {
						System.out.println("Error - This file could not be found.");
					} finally {
						fileScanner.close();
					}
				}
			}
		});

		// Find the information of the Java file
		btnGenerate.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	/**
	 * Builds output 
	 */
	public void buildOutput() {
		// DISPLAY LOC
		locTextArea.setText(""+loc);
		
		// DISPLAY METHOD INFORMATION
		methodTextArea.setText(methodList.display());
		
		// DISPLAY STRUCTURE INFORMATION
		structureTextArea.setText(structureInfo());
	}
	
	/**
	 * Read the file selected by the user
	 *  
	 * @return the string of the file's content
	 */
	public String readFile() {
		String nextLine = ""; 
		int methodCount = 0;
		String methodLine = "";
		int startBrack = 0;
		int endBrack = 0;

		while(fileScanner.hasNext()) { 
			nextLine = fileScanner.nextLine().trim();	// trim all excess whitespace from front and end
			System.out.println("current line: " + nextLine);
			isComment(nextLine);
			if(inComment) {
				if(nextLine.contains("*/")) {
					inComment = false;
				}
			}
		
			// if the line is not empty and we are not in a multi-line comment
			else if(!nextLine.isEmpty() && !isComment(nextLine)) {
				loc++;	// increase number of lines of code
				isControl(nextLine);	// if it is a control structure proper incrementation will occur
				if(isMethod(nextLine)) {	// if the line is a method
					//System.out.println("is method");
					methodLine = nextLine;	// set the method line as this line
					methodCount = 0;		// reset the method count
				}
					
				if(inMethod) {	// if in a method
					if(nextLine.contains("{")) {	// increase number of {
						startBrack++;
					}
					if(nextLine.contains("}")) {	// increase number of }
						endBrack++;
					}
					if(startBrack != endBrack) {	
						methodCount++;	// increase the number of lines within this method
						//System.out.println("method count: " +methodCount);
					}
					// if { = } we have reached the end of the method
					else if(startBrack > 0 && startBrack == endBrack) {
						methodReader(methodLine, methodCount);	// add the method to the list
						startBrack = 0;		// reset the bracket and line counters
						endBrack = 0;
						methodCount = 0;
						inMethod = false;	// no longer in a method
					}
				}
			}
		}
		return "File read successfully";
	}
	
	// HELPER METHODS
	
	/**
	 * Helper method to take information on taking information on methods within the file
	 * @param title		Name of the method
	 * @param count		Number of lines in the method
	 * 
	 */
	public void methodReader(String title, int count) {
		// FIND THE NAME OF THE METHOD
		String temp = title.substring(title.indexOf(' ')+1);
		System.out.println(temp);
		String temp2 = temp.substring(temp.indexOf(' '), temp.indexOf('('));
		System.out.println(temp2);
		String name = temp2 + "()";
		System.out.println(name);
		
		// CREATE NODE FOR THIS METHOD AND ADD TO LINKED LIST
		methodList.addNode(count, name);
		System.out.println(methodList.display());
	}
	
	/**
	 * Determines if the line is a comment
	 * @param lineIn	line to test
	 * @return			true if comment
	 */
	public boolean isComment(String lineIn) {
		if(lineIn.startsWith(COM_START)) {
			return true;
		}
		if(lineIn.startsWith(COM_MULT_START)) {
			inComment = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the line is a control structure
	 * @param lineIn	line to test
	 * @return			true if a control structure
	 * 
	 */
	public boolean isControl(String lineIn) {
		if(lineIn.startsWith(WHILE_LOOP)) {
			whileLoops++;
			return true;
		}
		if(lineIn.startsWith(FOR_LOOP)) {
			forLoops++;
			return true;
		}
		if(lineIn.startsWith(IF_STATE)) {
			ifStates++;
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the line is the start of a new method
	 * @param lineIn	the line to test
	 * @return			true if a new method
	 */
	public boolean isMethod(String lineIn) {
		
		if(lineIn.startsWith("public class")) {
			int start = lineIn.indexOf(' ', lineIn.indexOf(' ') + 1);
			nameOfDoc = lineIn.substring(start+1, lineIn.indexOf('{'));
			nameOfDoc = nameOfDoc.trim();
			//System.out.println(nameOfDoc);
			return false;
		}
		if(lineIn.startsWith("public " + nameOfDoc.trim())) {
			return false;
		}
		if(lineIn.startsWith("public") || lineIn.startsWith("private")) {
			if(lineIn.endsWith("{")) {
				inMethod = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Generates results for structure
	 * @return	String of structure information
	 */
	public String structureInfo() {
		return "While Loops:        " + whileLoops + "\nFor Loops:          " + forLoops +
				"\nIf Statements:      " + ifStates;
	}
	
	/**
	 * Selects the file to read
	 * 
	 * @return 	The path of the file in a string
	 */
	public String chooseFile() {
		open = new JButton();
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA FILES", "java");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\Maggie\\eclipse-workspace"));
		fileChooser.setDialogTitle("Select a File: ");
		
		if(fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath(); 
		} 
		else {
			fileChooser.hide();
			return "Cancelled";
		}
	}
}
