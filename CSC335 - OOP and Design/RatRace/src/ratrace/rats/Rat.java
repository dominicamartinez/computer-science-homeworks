package ratrace.rats;

import ratrace.*;

/**
 * <p>Title: RatRace</p>
 * <p>Description </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Dominic Martinez
 * @version 1.0
 */

// this is the class that all the subrats will inherit
// it defines the methods that would be common to all
// individual rats
public abstract class Rat implements Animal {

  protected static final Object UP = "Up";
  protected static final Object DOWN = "Down";
  protected static final Object LEFT = "Left";
  protected static final Object RIGHT = "Right";
  protected String ratName;
  protected int startRow;
  protected int startColumn;
  protected int numOfMoves;
  protected int curRow;
  protected int curColumn;
  protected Object facingDirection;

  // constructor for GUI
  public Rat(int row, int column) {
    startRow = row;
    startColumn = column;
  }

  // constructor for each rat, sets the rat' name also
  public Rat(String s, int row, int column) {
    ratName = s;
    startRow = row;
    startColumn = column;
  }

  // return rats name
  public String getName() {return ratName;}

  // retuns the letter to displayed in the GUI
  // which is also the first letter of rat's name
  public char getLetter() {return ratName.charAt(0);}

  // keep track of how many moves made
  public int getNumMoves() {return numOfMoves;}

  // returns current row
  public int getRow() {return curRow;}

  // returns current column
  public int getColumn() {return curColumn;}

  // returns where in the maze the rat starts(row)
  public int getStartColumn() {return startRow;}

  // returns where in the maze the rat starts(column)
  public int getStartRow() {return startColumn;}

  // declared abstract because each individual rat
  // moves differently
  public abstract void move(Maze maz);

  // resets the rat to the beginning
  public void reset() {
    curRow = startRow;
    curColumn = startColumn;
    numOfMoves = 0;
  }
}