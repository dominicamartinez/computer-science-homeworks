// Dominic Martinez
// Grader: Ehtesham Haque
// 10/9/02

import java.io.*;

public class PicrossGame {

	public static final int EMPTY = 0;  //int to represent an empty square in the attempt array
	public static final int HIT = 1;    //int to represent a hit square in the attempt array
	public static final int FLAG = 2;   //int to represent a flagged square in the attempt array
	public static final int MISS = 3;   //int to represent a missed square in the attempt array
	public int myRows;                  //the amount of rows in the gameboard
	public int myCols;                  //the amount of columns in the gameboard
	public int myAllowedmisses;         //number of allowed attempts to HIT
	public int myMisses;                //number of attempts to HIT
	private final char[][] gameBoard;   //the gameboard
	public int[][] markerBoard;         //also the gameboard but used to keep track of players progress

	//the Constructor will open the file chosen by the user and
	//then setup the gameBoard according to how the file reads in
	public PicrossGame(String filename) {
		TextReader gamefile = null;
				try {
					gamefile = new TextReader(new FileInputStream(filename));
				} catch(IOException e) {
					throw new RuntimeException(e.toString());
				  }

		myRows = gamefile.readInt();     //read the first integer in the file
		myCols = gamefile.readInt();     //read the second integer in the file
		myAllowedmisses = gamefile.readInt();   //read the third integer in the file

		gameBoard = new char[myRows][myCols];   //initialize the gameboard
		markerBoard = new int[myRows][myCols];  //initialize the board used for player-progression

		for (int i = 0; i < myRows; i++) {
			for (int j = 0; j < myCols; j++) {
				if(gamefile.peek() == '\n')
					gamefile.readChar();
				gameBoard[i][j] = gamefile.readChar();
			}
		}
	}

	// this will first calculate how many series of O's we have in the row
	// then calculate how many O's to those series
	public int[] getRowCalculation(int row) {
		int[] temp;
		int seriesNum = 0;
		int count = 0;

		//calculates the number of series of O's
		for (int i = 0; i < myCols; i++)
		{
			if (i != myCols-1)
				if (gameBoard[row][i] == 'O' && gameBoard[row][i+1] == 'X')
					seriesNum++;
				else { } //clarification purposes
			else
				if (gameBoard[row][i] == 'O')
					++seriesNum;
		}

		//uses the number of series to declare the array
		//as tight as possible
		if (seriesNum == 0)
		{
			temp = new int[1];
			temp[0] = 0;
		}
		else
		{
			temp = new int[seriesNum];
			seriesNum = 0;
		}

		//now finds the number of consecutive O's in each series
		for (int i = 0; i < myCols; i++) {
			if (i != myCols-1)
				if (gameBoard[row][i] == 'O' && gameBoard[row][i+1] == 'X')
				{
					++count;
					temp[seriesNum] = count;
					seriesNum++;
					count = 0;
				}
				else if (gameBoard[row][i] == 'O' && gameBoard[row][i+1] == 'O')
					++count;
				else { } //clarification purposes
			else
				if (gameBoard[row][i] == 'O')
				{
					++count;
					temp[seriesNum] = count;
				}

		}
		return temp;

	}

	// this will first calculate how many series of O's we have in the column
	// then calculate how many O's to those series
	public int[] getColCalculation(int col) {

		int seriesNum = 0;
		int[] temp;
		int count = 0;

		//calculates the number of series of O's
		for (int i = 0; i < myRows; i++) {
			if (i != myRows-1)
				if (gameBoard[i][col] == 'O' && gameBoard[i+1][col] == 'X')
					seriesNum++;
				else { } //clarification purposes
			else
				if (gameBoard[i][col] == 'O')
					++seriesNum;
		}

		//uses the number of series to declare the array
		//as tight as possible
		if (seriesNum == 0) {
			temp = new int[1];
			temp[0] = 0;
		}
		else
		{
			temp = new int[seriesNum];
			seriesNum = 0;
		}

		//now finds the number of consecutive O's in each series
		for (int i = 0; i < myRows; i++) {
			if (i != myRows-1)
				if (gameBoard[i][col] == 'O' && gameBoard[i+1][col] == 'X')
				{
					++count;
					temp[seriesNum] = count;
					count = 0;
					++seriesNum;
				}
				else if (gameBoard[i][col] == 'O' && gameBoard[i+1][col] == 'O')
					++count;
				else { } //clarification purposes
			else
				if (gameBoard[i][col] == 'O')
				{
				++count;
				temp[seriesNum] = count;
				}
		}
		return temp;
	}

	//this method will take in what the user is trying to do to a certain
	//part of the progress board and then make a decision upon what is
	//about to happen to what has already happened if at all
	public void flag(int row, int col) {
		if (markerBoard[row][col] == HIT || markerBoard[row][col] == MISS)
			{  } //clarification purposes
		else if (markerBoard[row][col] == FLAG)
			markerBoard[row][col] = EMPTY;
		else if (markerBoard[row][col] == EMPTY)
			markerBoard[row][col] = FLAG;

	}

	//this method will take in what the user is trying to do to a certain
	//part of the progress board and then make a decision upon what is
	//about to happen to what has already happened if at all
	public void hit(int row, int col) {
		if (markerBoard[row][col] == HIT || markerBoard[row][col] == MISS)
			{  } //clarification purposes
		else if (markerBoard[row][col] == EMPTY || markerBoard[row][col] == FLAG) {
			if (gameBoard[row][col] == 'O')
				markerBoard[row][col] = HIT;
			else if (gameBoard[row][col] == 'X') {
				myMisses++;
				markerBoard[row][col] = MISS;
			}
		}

	}

	//returns to main the progress board
	public int[][] getAttempt() {

		return markerBoard;
	}

	//compares the gameboard and the progress board to see if
	//they are the same...if so then the game is over
	public boolean isWon() {
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < myRows; i++)
			for (int j = 0; j < myCols; j++) {
				if(markerBoard[i][j] == HIT)
					count1++;
				if(gameBoard[i][j] == 'O')
					count2++;
			}
		if(count1 == count2)
			return true;
		else return false;

	}

	//returns to main true if number of attempts goes over
	//the number of allowed attempts
	public boolean isLost()  {
		if (myMisses >= myAllowedmisses)
			return true;
		else
			return false;
	}

	//returns to main the number of attempts
	public int getMisses() {
		return myMisses;
	}

	//returns to main the number of allowed attempts
	public int getEndgame() {
		return myAllowedmisses;
	}

}