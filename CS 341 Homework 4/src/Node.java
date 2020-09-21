/**
 * Node
 * @author Maggie McCormick
 * @version 1.0
 * @since September 18, 2020
 *
 */
public class Node {
	public Integer value;	// value node carries
	public Node left;		// node in front of current node
	public Node right;		// node behind current node

	public Node(int n) {
		value = n;
		left = null;
		right = null;
	}

	public String toString() {
		return "Value: " + value;
	}
}