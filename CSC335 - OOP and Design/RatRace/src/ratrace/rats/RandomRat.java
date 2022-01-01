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

// the random rat which moves around randomly
public class RandomRat extends Rat {

  private double myRandom;

  public RandomRat(int row, int column) {
    super("Randy", row, column);
    facingDirection = UP;
  }

// move about randomly
  public void move(Maze maz) {
    myRandom = Math.random();

    while (true) {
      if (myRandom < .26 && maz.canMove(curRow - 1, curColumn)) {
		  curRow--;
		  numOfMoves++;
		  return;
	  }
      else if (myRandom < .51 && maz.canMove(curRow +1, curColumn)) {
		  curRow++;
		  numOfMoves++;
		  return;
	  }
      else if (myRandom < .76 && maz.canMove(curRow, curColumn + 1)) {
		  curColumn++;
		  numOfMoves++;
		  return;
	  }
      else if (myRandom < 1.0 && maz.canMove(curRow, curColumn - 1)) {
		  curColumn--;
		  numOfMoves++;
		  return;
	  }
	  myRandom = Math.random();
	}
  }
}