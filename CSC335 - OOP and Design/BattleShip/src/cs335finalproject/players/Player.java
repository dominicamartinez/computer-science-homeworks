package cs335finalproject.players;

import cs335finalproject.strategy.*;
import java.awt.*;
import cs335finalproject.model.*;
import cs335finalproject.boat.*;

/**
 * @author Dominic Martinez and Ming Su
 * This is the player class
 */
public class Player {

  private Point move;
  private String name;
  private char lastMove;
  private Strategy strategy;
  private static int humanHits;
  private static int computerHits;
  private static char[][] humanBoatLayout;
  private static char[][] humanShotLayout;
  private static char[][] computerBoatLayout;
  private static char[][] computerShotLayout;
  private Boat[] myBoats;

  /**
   * @param String name
   * constructor for human player
   */
  public Player(String name) {
    lastMove = 'M';
    humanHits = 0;
    computerHits = 0;
    humanBoatLayout = new char[10][10];
    humanShotLayout = new char[10][10];
    computerBoatLayout = new char[10][10];
    computerShotLayout = new char[10][10];
    this.name = name;

    Battleship bs = new Battleship();
    Carrier car = new Carrier();
    Cruiser cr = new Cruiser();
    Destroyer des = new Destroyer();
    Sub sub = new Sub();

    myBoats = new Boat[5];
    myBoats[0] = bs;
    myBoats[1] = car;
    myBoats[2] = cr;
    myBoats[3] = des;
    myBoats[4] = sub;
  }

  /**
   * @param Strategy s
   * constructor for computer player
   */
  public Player(Strategy s) {
    name = "Computer";
    strategy = s;
 }

 /**
  * increments the amount of hits that the human player
  * has gained against his opponent
  */
  public static void incrementHumanHits() { humanHits++; }

  /**
   * increments the amount of hits that the computer player
   * has gained against his the human
   */
  public static void incrementComputerHits() { computerHits++; }

  // returns human hits
  public static int getHumanHits() { return humanHits; }
  // returns computer hits
  public static int getComputerHits() { return computerHits; }
  // making the move
  public void play(Model m) { move = strategy.makeMove(m); }
  // returning the move
  public Point getMove() { return move; }
  // set the last move
  public void setLastMove (char c) { this.lastMove = c; }
  // returning the last move result
  public char computerLastMove() { return lastMove; }
  // returning the boatlayout
  public static char[][] getHumanBoatLayout() { return humanBoatLayout; }
  // returning the shotlayout
  public static char[][] getHumanShotLayout() { return humanShotLayout; }
  // returning the boatlayout
  public static char[][] getComputerBoatLayout() { return computerBoatLayout; }
  // returning the shotlayout
  public static char[][] getComputerShotLayout() { return computerShotLayout; }
  // returning the name
  public String getName() { return name; }
  // returning the strategy
  public Strategy getStrategy() { return strategy; }
  // get the array of boats
  public Boat[] getBoats() {return myBoats;}
}