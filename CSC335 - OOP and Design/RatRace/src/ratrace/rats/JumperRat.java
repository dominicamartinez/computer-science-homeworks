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

// this rat tries to jump two spaces in front if it can't
// then it follows the right wall
public class JumperRat extends Rat {
  public JumperRat(int row, int col) {
    super("Jessica", row, col);
    facingDirection = UP;
  }

// many many if statements in deciding to jump or not
  public void move(Maze maz) {
    int row = curRow;
    int col = curColumn;
    if(facingDirection == UP) {
      if(maz.canMove(row - 2, col)) {
        curRow -= 2;
        numOfMoves++;
        facingDirection = UP;
      }
      else if (maz.canMove(row, col + 1)) {
        curColumn++;
        numOfMoves++;
        facingDirection = RIGHT;
      }
      else if (maz.canMove(row - 1, col)) {
        curRow--;
        numOfMoves++;
        facingDirection = UP;
      }
      else if (maz.canMove(row, col - 1)) {
        curColumn--;
        numOfMoves++;
        facingDirection = LEFT;
      }
      else if (maz.canMove(row + 1, col)) {
        curRow++;
        numOfMoves++;
        facingDirection = DOWN;
      }
    }
    else if(facingDirection == RIGHT) {
      if(maz.canMove(row, col + 2)) {
        curColumn += 2;
        numOfMoves++;
        facingDirection = RIGHT;
      }
      else if (maz.canMove(row + 1, col)) {
        curRow++;
        numOfMoves++;
        facingDirection = DOWN;
      }
      else if (maz.canMove(row, col + 1)) {
        curColumn++;
        numOfMoves++;
        facingDirection = RIGHT;
      }
      else if (maz.canMove(row - 1, col)) {
        curRow--;
        numOfMoves++;
        facingDirection = UP;
      }
      else if (maz.canMove(row, col - 1)) {
        curColumn--;
        numOfMoves++;
        facingDirection = LEFT;
      }
    }
    else if(facingDirection == DOWN) {
      if(maz.canMove(row + 2, col)) {
        curRow += 2;
        numOfMoves++;
        facingDirection = DOWN;
      }
      else if (maz.canMove(row, col - 1)) {
        curColumn--;
        numOfMoves++;
        facingDirection = LEFT;
      }
      else if (maz.canMove(row + 1, col)) {
        curRow++;
        numOfMoves++;
        facingDirection = DOWN;
      }
      else if (maz.canMove(row, col + 1)) {
        curColumn++;
        numOfMoves++;
        facingDirection = RIGHT;
      }
      else if (maz.canMove(row - 1, col)) {
        curRow--;
        numOfMoves++;
        facingDirection = UP;
      }
    }
    else if(facingDirection == LEFT) {
      if (maz.canMove(row, col - 2)) {
        curColumn -= 2;
        numOfMoves++;
        facingDirection = LEFT;
      }
      else if (maz.canMove(row - 1, col)) {
        curRow--;
        numOfMoves++;
        facingDirection = UP;
      }
      else if (maz.canMove(row, col - 1)) {
        curColumn--;
        numOfMoves++;
        facingDirection = LEFT;
      }
      else if (maz.canMove(row + 1, col)) {
        curRow++;
        numOfMoves++;
        facingDirection = DOWN;
      }
      else if (maz.canMove(curRow, curColumn + 1)) {
        curColumn++;
        numOfMoves++;
        facingDirection = RIGHT;
      }
    }
  }
}