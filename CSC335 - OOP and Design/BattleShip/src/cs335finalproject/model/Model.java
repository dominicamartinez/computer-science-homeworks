package cs335finalproject.model;

import java.util.*;
import cs335finalproject.players.*;
import java.awt.*;
import cs335finalproject.strategy.*;
import cs335finalproject.boat.*;

/**
 * @author Dominic Martinez and Ming Su
 * This is the underlying model class for the battleboat game
 */
public class Model extends Observable {

  public static final int NOT_STARTED = 0,
  CHOOSING_BOATS = 1,
  IN_PROGRESS = 2,
  END = 3;


  private Player computer, human;
  private int state;

  /**
   * Constructor for the model
   */
  public Model() {
    state = NOT_STARTED;
  }

  /**
   * @param Object name
   * setting the name typed in from the GUI
   */
  public void setName(Object name) {
    String s = (String)name;
    if ( s == null ) {
      human = new Player("Player 1");
    }
    else {
      human = new Player(s);
    }
  }

  /**
   * @param Object selection
   * choosing the strategy inputted from the GUI
   */
  public void setStrategy(Object selection) {
    String s = (String)selection;
    if ( s == "Easy") {
      computer = new Player(new RandomStrategy(this));
    }
    else if ( s == "Medium" ) {
      computer = new Player(new MediumStrategy(this));
    }
    else if (s == "Hard" ) {
      computer = new Player(new HardStrategy(this));
    }
  }

  /**
   * returns the human player
   * @return Player
   */
  public Player getHuman() { return human; }

  /**
   * returns computer player
   * @return Player
   */
  public Player getPlayer() { return computer; }

  /**
   * returns the state of the game
   * @return int
   */
  public int getState() { return state; }

  /**
   * @param int i
   * sets the state accordingly
   */
  public void setState(int i) { state = i; }

  /**
   * @param Boat boat, char[][] boats
   * remove the boat; only during the choosing boat state.
   */
  public void removeBoat(Boat boat, char[][] boats) {
    int xcord = (int)(boat.getPoint().getX());
    int ycord = (int)(boat.getPoint().getY());
    if (boat.getOrientation().equals("Vertical")) {
      for (int i = 0; i < boat.getLength(); i++) {
        boats[xcord][ycord + i] = '.';
      }
    }
    else  if (boat.getOrientation().equals("Horizontal")) {
      for (int i = 0; i < boat.getLength(); i++) {
        boats[xcord + i][ycord] = '.';
      }
    }
  }

  /**
   * @param Boat boat, String orientation, int xcord, int ycord, char[][] boats
   * checks to see if the shot selected can actually be used to put a boat
   * @return boolean
   */
  public boolean isValidArea(Boat boat, String orientation, int xcord, int ycord, char[][] boats) {
    if ( boats[xcord][ycord] != '.' && boats[xcord][ycord] != boat.getChar())
      return false;
    for (int i = 0; i < boat.getLength(); i++) {
      if (orientation.equals("Vertical")) {
        if ( boat.getLength() + ycord > 10 )
          return false;
        if ( boats[xcord][ycord+i] != '.' && boats[xcord][ycord+i] != boat.getChar())
          return false;
      }
      else if (orientation.equals("Horizontal")) {
        if ( boat.getLength() + xcord > 10)
          return false;
        if ( boats[xcord+i][ycord] != '.' && boats[xcord+i][ycord] != boat.getChar())
          return false;
      }
    }
    return true;
  }

  /**
   * @param Boat boat, String orient, int xcord, int ycord, char[][] boats
   * places boats on the human's array
   */
  public void placeBoat(Boat boat, String orient, int xcord, int ycord, char[][] boats) {
    if (orient.equals("Vertical")) {
      for (int i = 0; i < boat.getLength(); i++) {
        boats[xcord][ycord+i] = boat.getChar();
      }
      boat.setPoint(xcord, ycord);
      boat.setOrientation("Vertical");
    }
    else if (orient.equals("Horizontal")) {
      for (int i = 0; i < boat.getLength(); i++) {
        boats[xcord+i][ycord] = boat.getChar();
      }
      boat.setPoint(xcord, ycord);
      boat.setOrientation("Horizontal");
    }
    this.setChanged();
    this.notifyObservers();
  }

  /**
   * @param Model m, Point move
   * determines if the next move is valid
   * @return boolean
   */
  public boolean possibleMove(Model m, Point move) {
    char[][] shot = m.getPlayer().getComputerShotLayout();
    int x = (int)(move.getX());
    int y = (int)(move.getY());

    if ( x < 0 || x > 9 || y < 0 || y > 9)
      return false;
    else if ( shot[x][y] == 'H' || shot[x][y] == 'M') {
      return false;
    }
    else {
      return true;
    }
  }
}