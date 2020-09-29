/**
 * Node class
 * @author Maggie McCormick
 * @version 1.0
 * @since September 29, 2020
 *
 */

public class Node {	
	public int lines;
	public String name;
	public Node next;
	
	public Node (int element, String n){
		this.lines = element;
		this.name = n;
		next = null; 
	}
}