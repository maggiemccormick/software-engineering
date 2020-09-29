import java.util.ArrayList;

/**
 * Linked List class
 * @author Maggie McCormick
 * @version 1.0
 * @since September 25, 2020
 *
 */

public class LinkedList {
	private Node first; 
	private Node last;

	public LinkedList() {
		first = null;
		last = null;
	}
	
	/**
	 * Adds a node to the linked list
	 * @param element	The value of the node
	 */
	public void addNode(int element) { 
		Node nodeAdd = new Node(element);

		if (first == null) { 
			first = nodeAdd;
			last = nodeAdd;
		} 
		else {
			last.next = nodeAdd; 
			last = nodeAdd; 
		}
	}
	
	/**
	 * Displays the linked list
	 */
	public void display() {
		Node travel = first;
		while (travel != null) {
			System.out.println(travel.element);
			travel = travel.next;
		}
	}
	
	/**
	 * Displays the linked list
	 * @return	A string of the formatted elements of the Linked List
	 */
	public String displayFormatted() {
		Node travel = first;
		String formatted = "";
		
		while (travel!= null) {
			formatted = formatted + travel.element + " ";
			travel = travel.next;
		}
		
		return formatted;
	}
	
	/**
	 * Finds the sum of the linked list values
	 * @return	The sum of the values
	 */
	public int LinkedListSum () {
		Node travel = first;
		int sum = 0;
		
		while (travel!= null) {
			sum+= travel.element;
			travel = travel.next;
		}
		
		return sum;
	}
	
	/**
	 * Finds the standard deviation of the values in the linked list
	 * @return	standard deviation
	 */
	public double standardDeviation () {
		
		ArrayList<Integer> values = new ArrayList <Integer> ();
		
		Node travel = first;
		int sum = 0;
		
		while (travel!= null) {
			values.add(travel.element);
			sum+= travel.element;
			travel = travel.next;
		}
		
		int average = sum/values.size();
		int denom = values.size()-1;
		int num = 0;
		for (int i = 0; i<values.size(); i++) {
			num += Math.pow((values.get(i)-average), 2);
		}
		
		return Math.sqrt((num)/(denom));
	}
	
	/**
	 * Finds the total number of nodes in the linked list
	 * @return	number of nodes 
	 */
	public int count () { 
		int counter = 0;
		Node travel = first;
		while (travel!= null) {
			counter++;
			travel = travel.next;
		}
		return counter;
	}
	
	/**
	 * Sorts the linked list values
	 * @param list	The list to sort values of 
	 */
	public void sort (LinkedList list ) {
		for (int i = 0; i< (list.count())-1; i++) {
			Node temp = first; 
			Node nodeBefore = first;
			
			while (temp.next != null) { 
				Node nodeAfter = temp.next; 
				if (temp == first) { 
					if (temp.element > nodeAfter.element) { 
						temp.next = nodeAfter.next;
						first = nodeAfter;
						first.next = temp;
						nodeBefore = first;
					} else {
						temp = temp.next; 
					}
				}
				else {
					if (temp.element > nodeAfter.element) {
						temp.next = nodeAfter.next; 
						nodeBefore.next = nodeAfter;
						nodeAfter.next = temp;
						nodeBefore = nodeBefore.next; 
					}
					else { 
						nodeBefore = nodeBefore.next;
						temp = temp.next;
					}
				}
			}
		}
	}
}