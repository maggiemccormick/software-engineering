package mccormick;

/**
* Sales List Program
* @author Maggie McCormick
* @version 1.0
* @since September 12, 2020
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
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;

import java.util.*;

public class SalesList {

	private JFrame frame;
	private JTextField itemField;
	private JTextField costField;
	private JTextField quantityField;
	private JTextArea totalArea;
	private JButton addBtn;
	private JTextArea textArea;
	private SalesSlip salesSlip = new SalesSlip();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesList window = new SalesList();
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
	public SalesList() {
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
		
		JLabel lblNewLabel = new JLabel("Sales List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(168, 10, 105, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 48, 85, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cost: $");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 82, 116, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 111, 85, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		itemField = new JTextField();
		itemField.setBounds(107, 47, 256, 19);
		frame.getContentPane().add(itemField);
		itemField.setColumns(10);
		
		costField = new JTextField();
		costField.setBounds(107, 76, 134, 19);
		frame.getContentPane().add(costField);
		costField.setColumns(10);
		
		quantityField = new JTextField();
		quantityField.setBounds(105, 105, 136, 19);
		frame.getContentPane().add(quantityField);
		quantityField.setColumns(10);
		
		addBtn = new JButton("Add Item to List");
		addBtn.setBounds(107, 134, 182, 21);
		frame.getContentPane().add(addBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 178, 361, 56);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel_4 = new JLabel("Total Sales:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 244, 85, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		totalArea = new JTextArea();
		totalArea.setBounds(90, 244, 199, 19);
		frame.getContentPane().add(totalArea);
		
	}
	
	/**
	 * Create a listener for the button
	 */
	private void createEvents() {
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	/**
	 * Build the output of the sales list
	 */
	private void buildOutput() {
		salesSlip.addName(itemField.getText());
		salesSlip.addPrice(Double.parseDouble(costField.getText()), Integer.parseInt(quantityField.getText()));
		salesSlip.addQuantity(Integer.parseInt(quantityField.getText()));
		salesSlip.updatePrice();
		
		textArea.append(salesSlip.generateOutput(salesSlip.numItems-1));
		textArea.append("\n");
		
		totalArea.setText("$" + salesSlip.totalPrice);
	}
}
