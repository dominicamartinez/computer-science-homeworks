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

// this rat hugs the right wall until he reaches the
// end of the maze
public class WallHuggerRat extends Rat {

// constructor also setting the facingDirection to UP
  public WallHuggerRat(int row, int column) {
    super("Wally", row, column);
    facingDirection = UP;
  }

// many if statement's which follow the order as directed
// in the handout
  public void move(Maze maz) {
    int row = curRow;
    int col = curColumn;
    if (facingDirection == UP) {
      if (maz.canMove(row, col+1)) {
          curColumn += 1;
          numOfMoves++;
          facingDirection = RIGHT;
          return;
      }
      else if (maz.canMove(row-1, col)) {
        curRow -= 1;
        numOfMoves++;
        return;
      }
      else if (maz.canMove(row, col-1)) {
        curColumn -= 1;
        numOfMoves++;
        facingDirection = LEFT;
        return;
      }
      else {
        curRow  += 1;
        numOfMoves++;
        facingDirection = DOWN;
        return;
      }
    }
    else if (facingDirection == RIGHT) {
      if (maz.canMove(row+1, col)) {
          curRow += 1;
          numOfMoves++;
          facingDirection = DOWN;
          return;
      }
      else if (maz.canMove(row, col+1)) {
        curColumn += 1;
        numOfMoves++;
        return;
      }
      else if (maz.canMove(row-1, col)) {
        curRow -= 1;
        numOfMoves++;
        facingDirection = UP;
        return;
      }
      else {
        curColumn  -= 1;
        numOfMoves++;
        facingDirection = LEFT;
        return;
      }
    }
    else if (facingDirection == LEFT) {
      if (maz.canMove(row-1, col)) {
          curRow -= 1;
          numOfMoves++;
          facingDirection = UP;
          return;
      }
      else if (maz.canMove(row, col-1)) {
        curColumn -= 1;
        numOfMoves++;
        return;
      }
      else if (maz.canMove(row+1, col)) {
        curRow += 1;
        numOfMoves++;
        facingDirection = DOWN;
        return;
      }
      else {
        curColumn  += 1;
        numOfMoves++;
        facingDirection = RIGHT;
        return;
      }
    }
    else {
      if (maz.canMove(row, col-1)) {
          curColumn -= 1;
          numOfMoves++;
          facingDirection = LEFT;
          return;
      }
      else if (maz.canMove(row+1, col)) {
        curRow += 1;
        numOfMoves++;
        return;
      }
      else if (maz.canMove(row, col+1)) {
        curColumn += 1;
        numOfMoves++;
        facingDirection = RIGHT;
        return;
      }
      else {
        curRow  -= 1;
        numOfMoves++;
        facingDirection = UP;
        return;
      }
    }

  }

}