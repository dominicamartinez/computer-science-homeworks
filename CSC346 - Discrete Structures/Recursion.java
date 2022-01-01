// Dominic Martinez
// 3/1/03

public class Recursion {

	// consturcts the object Recursion
	public Recursion(int n) {
		size = (int)Math.pow(2, n);
		array = new char[size][size];
	}

	// this will take the missing coordinates and call helper
	public void solve(int missingX, int missingY) {
		array[missingX][missingY] = 'O';
		solveHelp(size, 0, 0, missingX, missingY);
	}

	// solves the board problem by using a helper
	public void solveHelp(int size, int x, int y,
	                      int missingX, int missingY) {
		int tempSize = size/2;
		// base case
		if (tempSize == 1) {
			decide(tempSize, x, y, missingX, missingY);
			return;
		}
		char tempChar = myChar++;

		// this is for the ones in the middle of the
		// four quadrants also the recursive calls
		if (quadrant(tempSize, x, y, missingX, missingY) == 2) {
			solveHelp(tempSize, x, y+tempSize, missingX, missingY);
		}
		else {
			array[x+tempSize-1][y+tempSize] = tempChar;
			solveHelp(tempSize, x, y+tempSize, x+tempSize-1, y+tempSize);
		}
		if (quadrant(tempSize, x, y, missingX, missingY) == 1) {
			solveHelp(tempSize, x, y, missingX, missingY);
		}
		else {
			array[x+tempSize-1][y+tempSize-1] = tempChar;
			solveHelp(tempSize, x, y, x+tempSize-1, y+tempSize-1);
		}
		if (quadrant(tempSize, x, y, missingX, missingY) == 4) {
			solveHelp(tempSize, x+tempSize, y+tempSize, missingX, missingY);
		}
		else {
			array[x+tempSize][y+tempSize] = tempChar;
			solveHelp(tempSize, x+tempSize, y+tempSize, x+tempSize, y+tempSize);
		}
		if (quadrant(tempSize, x, y, missingX, missingY) == 3) {
			solveHelp(tempSize, x+tempSize, y, missingX, missingY);
		}
		else {
			array[x+tempSize][y+tempSize-1] = tempChar;
			solveHelp(tempSize, x+tempSize, y, x+tempSize, y+tempSize-1);
		}
	}

	// this decides which L shape to put
	public void decide(int size, int x, int y, int x1, int y1) {
		if (x1 == x+size && y1 == y+size) {
			array[x+size][y] = myChar;
			array[x][y+size] = myChar;
			array[x][y] = myChar;
			myChar++;
		}
		else if (x1 == x+size && y1 == y) {
			array[x][y+size] = myChar;
			array[x+1][y+size] = myChar;
			array[x][y] = myChar;
			myChar++;
		}
		else if (x1 == x && y1 == y+size) {
			array[x+size][y] = myChar;
			array[x+size][y+size] = myChar;
			array[x][y] = myChar;
			myChar++;
		}
		else {
			array[x+size][y+size] = myChar;
			array[x][y+size] = myChar;
			array[x+size][y] = myChar;
			myChar++;
		}
	}

	// finds what quadrant the empty space is supposed to be
	public int quadrant(int size, int x, int y, int x1, int y1) {
		if (x1 < (x+size) && y1 < (y+size)) {return 1;}
		if (x1 < (x+size) && y1 >= (y+size) ) {return 2;}
		if (x1 >= (x+size) && y1 < (y+size)) {return 3;}
		if (x1 >= x+size && y1 >= y+size) {return 4;}
		return 0;
	}

	// this will print out the board
	public String toString() {
		String temp = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j < size-1) {
					temp += array[i][j];
				}
				else {
					temp += array[i][j] + "\n";
				}
			}
		}
		return temp;
	}

	// test driver
	public static void main(String[] args) {
		// creates the object with the exponential size
		Recursion r = new Recursion(3);

		// solves the spot missing 2, 2
		r.solve(2,2);

		// prints out the array
		System.out.println(r.toString());
	}

	// instance variables
	private char myChar = 'a';
	private char[][] array;
	private int size;
}