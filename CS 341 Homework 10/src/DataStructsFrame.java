import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DataStructsFrame extends JFrame {
	public DataStructsFrame(String title, int[] numbers1, int[] numbers2) {
		super(title);

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// Creates ArrayList that holds two values
		final ArrayList<ListItem> list = arrayToList(numbers1, numbers2);

		final ListPanel unorderedList = new ListPanel("Unordered List");
		unorderedList.setDiameter(75);
		unorderedList.addItems(list);

		final ListPanel orderedList = new ListPanel("Ordered List");
		orderedList.setDiameter(100);

		JButton sortButton = new JButton("Sort List");
		sortButton.setSize(30, 10);
		sortButton.setAlignmentX(CENTER_ALIGNMENT);

		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("The pre-list: " + list);
				Collections.sort(list);
				System.out.println();
				System.out.println("The post-list for sorting (a) values or the first values in the pair " + list);
				System.out.println();
				orderedList.addItems(list);
				System.out.println("The pre-list: " + list);
				System.out.println();
				System.out.print("The post-list for sorting (b) values or the second values in the pair: ");
				sortingBvalues(list);

				panel.add(orderedList);
				pack();
			}
		});

		panel.add(unorderedList);
		panel.add(sortButton);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	// Bubble sort 
	public void sortingBvalues(ArrayList<ListItem> list) {
		for (int i = 0; i < list.size() - 1; i++)
			for (int j = 0; j < list.size() - i - 1; j++)
				if (list.get(j).getValB() < list.get(j + 1).getValB()) {
					// swap arr[j+1] and arr[j]
					ListItem temp = list.get(j);
					list.set(j, list.get(j + 1));
					// numbers2[j+1] = temp;
					list.set(j + 1, temp);

				}
		System.out.println(list.toString());
	}

// Add the pairs of arrays into the array list
	private ArrayList<ListItem> arrayToList(int[] numbers1, int[] numbers2) {
		ArrayList<ListItem> list = new ArrayList<ListItem>();

		for (int i = 0; i < numbers1.length; i++) {
			ListItem item = new ListItem(numbers1[i], numbers2[i]);
			list.add(item);
		}

		return list;
	}
}