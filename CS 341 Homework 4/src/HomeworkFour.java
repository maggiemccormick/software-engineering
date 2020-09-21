/**
 * Main class to run Binary Tree
 * @author Maggie McCormick
 * @version 1.0
 * @since September 18, 2020
 *
 */

public class HomeworkFour {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();	//binary tree

		// add nodes to the tree
		tree.addNode(7);
		tree.addNode(12);
		tree.addNode(8);
		tree.addNode(41);
		tree.addNode(16);
		tree.addNode(50);
		tree.addNode(22);
		tree.addNode(19);
		tree.addNode(1);
		tree.addNode(4);
		tree.addNode(35);
		tree.addNode(11);
		tree.addNode(74);
		tree.addNode(39);
		
		/* check assertion messages */
		// tree.displayInOrder();
		// tree.addNode(8);

		// show the nodes in numerical order
		System.out.println("Display the nodes in order: ");
		tree.displayInOrder();

		// test delete node method
		tree.deleteNode(1);
		// tree.deleteNode(81); // when attempting to delete a node that doesn't exist
		System.out.println("Nodes in order:");
		tree.displayInOrder();
		tree.deleteNode(22);
		System.out.println("Nodes in order:");
		tree.displayInOrder();

		// if code runs without triggering assertions, print success
		System.out.println("No assertions were triggered. Success :)");
	}
}