// Dominic Martinez
// 11/10/02

import java.util.*;

public class Cryptarithm {
	private int myNumSolutions = 0;
	private String mySolutions;
	private String myOperand1;
	private String myOperand2;
	private String myResult;
	private char[] symbols;
	private boolean[][] board;
	private char[] assign;
	private int assignIndex = 0;

    public Cryptarithm(String op1, String op2, String res) {
		myOperand1 = op1;
		myOperand2 = op2;
		myResult = res;
		mySolutions = "";
        symbols = getSymbolsSorted(op1, op2, res);
        board = new boolean[10][symbols.length];
        assign = new char[10];
    }

    public String getSolutions() {
        String temp = "";
		placeMarker(board,0);
        return mySolutions;
    }

    public int getNumSolutions() {
        return myNumSolutions;
    }

    public String toString() {
        String temp = "";
        temp += getFormattedEquation(myOperand1, myOperand2, myResult) + '\n';
        temp += getSolutions() + '\n';
        temp += Integer.toString(myNumSolutions) + " " + "solution(s) total";
        return temp;
    }

/*******************************************/
	// Just like the EightQueens, baby!
	private boolean placeMarker(boolean[][] board, int col) {
        // base case
        if (col > symbols.length - 1) {
			if (checkSolution(symbols, assign))
            	return false;
			return false;
		}

        // check until we find something
        for (int row = 0; row < 10; row++) {
            if (isValidRow(board, row, col)) { // check if we can add something here
            	assign[assignIndex] = (char)(row + 48);
            	assignIndex++;
            	for (int i = 0; i < symbols.length; i++) {
					board[row][i] = true;
				}
                if (placeMarker(board, col+1)) // recursive call, check the rest of the cols
                    return true; // found a solution down in the recursion, return true
                else {
            		for (int i = 0; i < symbols.length; i++) {
						board[row][i] = false;
					}
					assignIndex--;
				}
            }
        }
        return false; // couldn't find anything here,
    }                 // backtrack to the last column and come back later

    private boolean isValidRow(boolean[][] board, int row, int col) {
        if (board[row][col])
            return false; // found a number to the left
        return true; // didn't find anything
    }
/********************************************/
    private static String getFormattedEquation(String s1, String s2, String s3) {
        String temp = "";
        int width = Math.max(s1.length(), Math.max(s2.length() + 2, s3.length()));
        temp += getCharacterRepeated(' ', width - s1.length()) + s1 + '\n';
        temp += "+ " + getCharacterRepeated(' ', width - s2.length() - 2) + s2 + '\n';
        temp += getCharacterRepeated('-', width) + '\n';
        temp += getCharacterRepeated(' ', width - s3.length()) + s3 + '\n';
        return temp;
    }

    private static String getCharacterRepeated(char c, int x) {
        String s = "";
        for (int i = 0; i < x; i++)
            s += c;
        return s;
    }
/*******************************************/
    private static char[] getSymbolsSorted(String s1, String s2, String s3) {
        String sfull = s1 + s2 + s3;
        char[] arr = sfull.toCharArray();
        Arrays.sort(arr);
        char cur = 0;
        String temp = "";
        for (int i = 0; i < arr.length; i++) {
            if (cur != arr[i]) {
                temp += arr[i];
                cur = arr[i];
            }
        }
        return temp.toCharArray();
    }

    private boolean checkSolution(char[] symbols, char[] assign) {
		String op1 = myOperand1;
		String op2 = myOperand2;
		String sol = myResult;

		for (int i = 0; i < symbols.length; i++) {
			op1 = op1.replace(symbols[i], assign[i]);
			op2 = op2.replace(symbols[i], assign[i]);
			sol = sol.replace(symbols[i], assign[i]);
		}

		// solutions that start with 0 are very bad...
		if (op1.charAt(0) == '0' || op2.charAt(0) == '0' || sol.charAt(0) == '0')
			return false;


		if (Double.parseDouble(op1) + Double.parseDouble(op2) == Double.parseDouble(sol)) {
			myNumSolutions++;
			mySolutions += getFormattedEquation(op1, op2, sol);
			return true;
		}
		return false;
	}
}
