public class TestClass {
	// a comment
	public TestClass() {
		// a constructor
	}
	public void hello() {
		System.out.println("hello");
	}
	private boolean isHappy() {
		int y = 1;
		int x = 3;
		/* add variables
		 */
		int z = y+x;
		for(int i = 0; i < 3; i++)
			System.out.print(i);
		if(y == x)
			return false;
		return true;
	}
}
