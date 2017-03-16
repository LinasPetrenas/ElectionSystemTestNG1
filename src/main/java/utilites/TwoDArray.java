package utilites;

public class TwoDArray {
	private int col;
	private int row;

	public TwoDArray(int row, int col) {
		
		this.row = row;
		this.col = col;
	}

	/**
	 * Dynamically create 2D string array.
	 * 
	 * @return String[][].
	 */
	public String[][] createArray() {
		
		return new String[row][col];
	}
}
