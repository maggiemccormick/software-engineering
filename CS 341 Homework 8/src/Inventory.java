import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	// Data Members 
	public ArrayList<Video> v;
	public HashMap<String, Video> hashVideoMap;
	
	// Constructor
	
	public Inventory () {
		v = new ArrayList<Video>();
		hashVideoMap = new HashMap<String, Video>();
	}
	
	public void addVideo (Video vSelection) {
		v.add(vSelection);
		hashVideoMap.put(vSelection.getSKU(), vSelection); 
	}

	public void removeVideo (String videoSKU) { // removes by SKU
		// removes from array list
		for (int i = 0; i<v.size(); i++) {
			if (videoSKU.equals(v.get(i).getSKU())) {
				v.remove(i);
			}
		}
		
		// removes from hashmap
		hashVideoMap.remove(videoSKU); // removes the video
		
	}
	
	public String display () {
		//displays as array list
		return v.toString().replace("[", "").replace("]", "");
	}
	
	public String displayHashMap () {
		return hashVideoMap.toString();
	}
	
	// Setters and Getters

	/**
	 * @return the v
	 */
	public ArrayList<Video> getV() {
		return v;
	}

	/**
	 * @param v the v to set
	 */
	public void setV(ArrayList<Video> v) {
		this.v = v;
	}
	
	/**
	 * @return the hashVideoMap
	 */
	public HashMap<String, Video> getHashVideoMap() {
		return hashVideoMap;
	}

	/**
	 * @param hashVideoMap the hashVideoMap to set
	 */
	public void setHashVideoMap(HashMap<String, Video> hashVideoMap) {
		this.hashVideoMap = hashVideoMap;
	}
}