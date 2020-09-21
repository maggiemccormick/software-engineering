/**
 * Binary Tree
 * @author Maggie McCormick
 * @version 1.0
 * @since September 18, 2020
 *
 */

import java.util.ArrayList;
import java.util.Collections;

public class BinaryTree {
	public Node root;						// first node in binary tree
	public int count; 						// node counter
	public int singleParents; 				// number of single parents (nodes with one child)
	public ArrayList<Integer> valueList;	// holds values of all nodes in the tree
	public int indx; 					// for display purposes
	
	/**
	 * Constructor
	 */
	public BinaryTree() {
		root = null;
		valueList = new ArrayList<Integer>();
	}

	/** 
     * Adds a node
     * @param n		value of node to be created
     */
	public void addNode(int n) {
		// Pre-condition: check if a node with this value already exists
		assert !found(n) : "A node with the value of " + n + " already exists";

		Node temp = new Node(n);
		valueList.add(n);
		Collections.sort(valueList);
		//System.out.println("Node values: " + getList());
		count++;

		// Scenario 1: If tree is empty, node created will be the root
		if (root == null) {
			root = temp;
		}

		// Scenario 2: Tree is not empty. Must find correction location to place new node.
		else { 
			Node travel = root; 
			while (true) { 
				// First: travel to the left
				if (temp.value < travel.value) { 
					if (travel.left != null) { 
						travel = travel.left;
					}
					else {
						travel.left = temp; 
						break;
					}
				}

				// Second: travel to the right
				else { 
					if (travel.right != null) { 
						travel = travel.right;
					} else {
						travel.right = temp; 
						break;
					}
				}
			}
		}

		// Post-condition
		assert found(n) && !isEmpty() && isBST(): "A node with a value of " + n + " wasn't added to the tree.";

	}
	
	/** 
     * Deletes a node
     * @param n		value of node to be deleted
     */
	public void deleteNode(int n) {
		// Pre-condition
		assert found(n) && !isEmpty() : "A node with a value of " + n + " doesn't exist";
		
		valueList.remove(Integer.valueOf(n)); // removes value of n from the list of values
		Collections.sort(valueList);

		Node removeNode = root;
		Node parent = null;
		boolean found = false;
		count--;

		while (!found && removeNode != null) {
			if (n == removeNode.value) {
				found = true;
			} else {
				parent = removeNode;
				if (n > removeNode.value) {
					removeNode = removeNode.right;
				} else {
					removeNode = removeNode.left;
				}
			}
		}

		if (!found) {
			count++;
			System.out.println("Node with value " + n + " does not exist and cannot be removed.");
			return;
		}

		// Scenario 1: subtree is empty
		if (removeNode.left == null || removeNode.right == null) {
			Node child;
			if (removeNode.left == null) {
				child = removeNode.right;
			} else {
				child = removeNode.left;
			}

			if (parent == null) {
				root = child;
			} else if (parent.left == removeNode) {
				parent.left = child;
			} else {
				parent.right = child;
			}
			return;
		}

		// Scenario 2: subtrees are full
		Node smallestParent = removeNode;
		Node smallest = removeNode.right;
		while (smallest.left != null) {
			smallestParent = smallest;
			smallest = smallest.left;
		}
		removeNode.value = smallest.value;
		if (smallestParent == removeNode) {
			smallestParent.right = smallest.right;
		} else {
			smallestParent.left = smallest.right;
		}

		// Post-condition
		assert !found(n) && isBST(): "A node with a value of " + n + " was found, meaning node was not properly deleted.";

	}

	/** 
     * Shows nodes in order
     */
	public void displayInOrder() { 
		assert !isEmpty() : "The tree is empty.";
		indx = 1;
		inOrderRecursive(root); 
	}
	
	/** 
     * Recurisve method to help display nodes in order
     * @param nodeIn		the node to start recursion from
     */
	public void inOrderRecursive(Node nodeIn) { 

		if (nodeIn != null) {
			inOrderRecursive(nodeIn.left);
			System.out.println(indx + ". " + nodeIn.toString());
			indx++;
			inOrderRecursive(nodeIn.right);
		}
	}

	/* Helper Methods */
	
	/**
	 * Checks if tree is empty
	 * @return		true if tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Checks to see if a node is found within the tree
	 * @param n		value of node to be found
	 * @return		true if node is found, false otherwise
	 */
	public boolean found(int n) {
		Node node = root;
		Node parent = null;
		boolean found = false;

		while (!found && node != null) {
			if (n == node.value) {
				found = true; // found is now equal to true
			} else {
				parent = node;
				if (n > node.value) {
					node = node.right;
				} else {
					node = node.left;
				}
			}
		}

		return found;
	}
	
	/**
	 * Prints out the number of nodes
	 */
	public void CountNodes() {
		System.out.println("Number of nodes: " + count);
	}

	public void countsingleParent(Node travel) {

		if (travel != null) {

			countsingleParent(travel.left); // goes all the way to the left until is equal to null

			if ((travel.left == null && travel.right != null) || (travel.right == null && travel.left != null)) {
				singleParents++;
				System.out.println("singles parent node is: " + travel.toString());
			}
			countsingleParent(travel.right); // goes all the way to the right until is equal to null
		}
	}
	
	/**
	 * Verifies that we do indeed have a binary search tree
	 * @return		true or false ^
	 */
	public boolean isBST() {
		return isBSTTraverse(root, Collections.min(valueList), Collections.max(valueList));
	}

	/**
	 * Recursive helper method to determine if tree is a binary search tree
	 * @param travel 	current node
	 * @param min		smallest node on the current side
	 * @param max		largest node on the current side
	 * @return			true if is a binary search tree, false otherwise
	 */
	public boolean isBSTTraverse(Node travel, int min, int max) {	
		if (travel == null) {
			return true;
		}

		if (travel.value < min || travel.value > max) {
			return false;
		}

		return (isBSTTraverse(travel.left, min, travel.value - 1) && isBSTTraverse(travel.right, travel.value + 1, max));
	}
	
	/* Setters and getters */

	/**
	 * Gets the list 
	 * @return the list
	 */
	public ArrayList<Integer> getList() {
		return valueList;
	}

	/**
	 * Set the list of node values
	 * @param list 	the value list 
	 */
	public void setList(ArrayList<Integer> valueList) { 
		this.valueList = valueList;
	}
}