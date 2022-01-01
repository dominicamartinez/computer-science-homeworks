package ratrace.rats;

import ratrace.*;
import java.util.Random;
/**
 * <p>Title: RatRace</p>
 * <p>Description </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Dominic Martinez
 * @version 1.0
 */

// this rat teleports to different parts of the maze
// that it already hasn't been to
public class TeleporterRat extends Rat {

  private Random rnd;
  private boolean[][] twoD;


// constructor, then seeds the random object
  public TeleporterRat(int row, int col) {
    super("Tiffany", row, col);
    rnd = new Random(42);
  }

// this method will keep track of where the rat
// has already been to and decided where to move
// based on that information and the random number
  public void move(Maze maz) {
    int row = 0;
    int col = 0;

	if (twoD == null) {
		twoD = new boolean[maz.getNumRows()][maz.getNumColumns()];
	}

    while(!maz.canMove(row, col) || twoD[row][col]) {
       row = rnd.nextInt(maz.getNumRows());
       col = rnd.nextInt(maz.getNumColumns());
    }

    twoD[row][col] = true;
    curRow = row;
    curColumn = col;
    numOfMoves++;
  }

// overriding the reset method from the superclass
// since this one is kind of personal due to the random
  public void reset() {
    curRow = startRow;
    curColumn = startColumn;
    numOfMoves = 0;
    rnd.setSeed(42);
  }
}