package mccormick;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class IterationApp {

	private JFrame frame;
	private JTextField txtFieldCount;
	private JTextField txtFieldText;
	private JButton btnBuild;
	private JTextArea txtAreaOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IterationApp window = new IterationApp();
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
	public IterationApp() {
		initialize();
		createEvents(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 413, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Iteration App");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(131, 10, 127, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Count:");
		lblNewLabel_1.setBounds(105, 90, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtFieldCount = new JTextField();
		txtFieldCount.setBounds(199, 87, 96, 19);
		frame.getContentPane().add(txtFieldCount);
		txtFieldCount.setColumns(10);
		
		txtFieldText = new JTextField();
		txtFieldText.setBounds(183, 138, 170, 19);
		frame.getContentPane().add(txtFieldText);
		txtFieldText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Text to iterate:");
		lblNewLabel_2.setBounds(45, 141, 105, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnBuild = new JButton("Build Output");
		btnBuild.setBounds(131, 203, 127, 21);
		frame.getContentPane().add(btnBuild);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 242, 333, 43);
		frame.getContentPane().add(scrollPane);
		
		txtAreaOutput = new JTextArea();
		scrollPane.setViewportView(txtAreaOutput);
	}
	
	private void createEvents() {
		btnBuild.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	private void buildOutput() {
		// TASK 1: get count
		Integer cnt = Integer.parseInt(txtFieldCount.getText());
		
		// TASK 2: build the output string
		String outString = "";
		for(int i = 1; i <= cnt; i++) {
			outString += txtFieldText.getText();
		}
		
		// TASK 3: display final output string
		txtAreaOutput.setText(outString);
	}
}
