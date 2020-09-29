
/**
 * Linked List class
 * @author Maggie McCormick
 * @version 1.0
 * @since September 29, 2020
 *
 */

public class LinkedList {
	private Node first; 
	private Node last;
	public int count;

	public LinkedList() {
		first = null;
		last = null;
		count = 0;
	}
	
	/**
	 * Adds a node to the linked list
	 * @param element	The value of the node
	 */
	public void addNode(int element, String name) { 
		Node nodeAdd = new Node(element, name);

		if(first == null) { 
			first = nodeAdd;
			last = nodeAdd;
		} 
		else {
			last.next = nodeAdd; 
			last = nodeAdd; 
		}
		count++;
	}
	
	/**
	 * Displays the linked list
	 * @return	A string of the formatted elements of the Linked List
	 */
	public String display() {
		Node travel = first;
		String result = "";
		
		if(travel == null)
			return "list is empty"; 
		
		while(travel!= null) {
			result = result + travel.name + " contains " + travel.lines +" line(s)\n";
			travel = travel.next;
		}
		
		return result;
	}	
}