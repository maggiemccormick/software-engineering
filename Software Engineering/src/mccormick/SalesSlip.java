package mccormick;

/**
* Sales Slip Class
* @author Maggie McCormick
* @version 1.0
* @since September 13, 2020
*
*/

import java.util.*;

public class SalesSlip {
	
	private ArrayList<String> itemNameList;
	private ArrayList<Double> itemPriceList;
	private ArrayList<Integer> itemQuantList;
	public Double totalPrice; 
	public int numItems;
	
	public SalesSlip() {
		itemNameList = new ArrayList<String>();
		itemPriceList = new ArrayList<Double>();
		itemQuantList = new ArrayList<Integer>();
		totalPrice = 0.00;
		numItems = 0;
	}
	
	/** 
     * Add name of an item to the list of items
     * @param nameIn the name of the item
     */
	public void addName(String nameIn) {
		itemNameList.add(nameIn);
		numItems++;
		System.out.println(itemNameList.get(itemNameList.size()-1));
	}
	/** 
     * Add total price of an item to the list of prices
     * @param priceIn the price of a single item
     * @param quantIn the quantity of that item 
     */
	public void addPrice(Double priceIn, Integer quantIn) {
		itemPriceList.add(priceIn*quantIn);
		System.out.println(itemPriceList.get(itemPriceList.size()-1));
	}
	/** 
     * Add quantity of an item to the list of quantities
     * @param quantIn the quantity of the item
     */
	public void addQuantity(Integer quantIn) {
		itemQuantList.add(quantIn);
		System.out.println(itemQuantList.get(itemQuantList.size()-1));
	}
	/**
	 * Update the total cost of the shopping list
	 */
	public void updatePrice() {
		totalPrice += itemPriceList.get(itemPriceList.size()-1);
		System.out.println(totalPrice);
	}
	/** 
     * Generate the string for each line of output
     * @param index the current index for the item being printed
     * @return		the final string with all the information (name, price, and quantity)
     */
	public String generateOutput(int index) {
		int diff = 20 - itemNameList.get(index).length();
		String spaces = "";
		for(int y = 0; y < diff; y++)
			spaces += " ";
		return itemNameList.get(index) + spaces + "$" + itemPriceList.get(index) + "     " + itemQuantList.get(index);
	}
}
