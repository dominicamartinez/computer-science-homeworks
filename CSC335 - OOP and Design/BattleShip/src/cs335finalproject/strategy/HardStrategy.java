package cs335finalproject.strategy;

import java.awt.*;
import cs335finalproject.model.*;
import cs335finalproject.players.*;
import cs335finalproject.boat.*;
import java.util.*;

/**
 * @author Dominic Martinez and Ming Su
 * HardStrategy class implements Strategy interface
 */
public class HardStrategy implements Strategy {
  private Point move;
  private char[][] boats;
  private char[][] myShots;
  private int marked;
  private boolean lastMoveHit = false;
  private char[][] pseudoShots;
  private int stage;
  private static final int STAGE1 = 1, STAGE2 = 2;

  /**
   * Constructor for the hard strategy
   */
  public HardStrategy(Model m) {
    stage = STAGE1;
    marked = 0;
    pseudoShots = new char[10][10];
    boats = m.getPlayer().getComputerBoatLayout();
    myShots = m.getPlayer().getComputerShotLayout();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j <10; j++) {
        boats[i][j] = '.';
      }
    }

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (j%2 == 0 && i%2 == 1) {
         pseudoShots[i][j] = 'x';
        }
        else if (i%2 == 0 && j%2 ==1) {
          pseudoShots[i][j] = 'x';
        }
        else { pseudoShots[i][j] = 'o'; }
      }
    }

    Battleship bs = new Battleship();
    Carrier car = new Carrier();
    Cruiser cr = new Cruiser();
    Destroyer des = new Destroyer();
    Sub sub = new Sub();

    Boat[] boatarray = {bs, car, cr, des, sub};

    // checks boats positioning and places if valid.
    String orient;
    int x = 0, y = 0;
    int i = 0;
    double d = Math.random();
    if (d <= .20) { i = 0; }
    else if ( d <= .40) { i = 1; }
    else if ( d <= .60) { i = 2; }
    else if ( d <= .80) { i = 3; }
    else { i = 4; }

    if (Math.random() > 0.5) { orient = "Vertical"; }
    else { orient = "Horizontal"; }
    do {
      x = (int)(Math.random() * 4) + 5;
      y = (int)(Math.random() * 4) + 5;
    } while (!(m.isValidArea(boatarray[i],orient,x,y,boats)));
    m.placeBoat(boatarray[i],orient,x,y,boats);

    i++;
    if (i > 4) { i = 0; }
    if (orient == "Horizontal") { orient = "Vertical"; }
    else { orient = "Horizontal"; }
    do {
      x = (int)(Math.random() * 4);
      y = (int)(Math.random() * 4) + 5;
    } while (!(m.isValidArea(boatarray[1],orient,x,y,boats)));
    m.placeBoat(boatarray[1],orient,x,y,boats);

    i++;
    if (i > 4) { i = 0; }
    if (orient == "Horizontal") { orient = "Vertical"; }
    else { orient = "Horizontal"; }
    do {
      x = (int)(Math.random() * 4) + 5;
      y = (int)(Math.random() * 4);
    } while (!(m.isValidArea(boatarray[2],orient,x,y,boats)));
    m.placeBoat(boatarray[2],orient,x,y,boats);

    i++;
    if (i > 4) { i = 0; }
    if (orient == "Horizontal") { orient = "Vertical"; }
    else { orient = "Horizontal"; }
    do {
      x = (int)(Math.random() * 4);
      y = (int)(Math.random() * 4);
    } while (!(m.isValidArea(boatarray[3],orient,x,y,boats)));
    m.placeBoat(boatarray[3],orient,x,y,boats);

    i++;
    if (i > 4) { i = 0; }
    if (orient == "Horizontal") { orient = "Vertical"; }
    else { orient = "Horizontal"; }
    do {
      x = (int)(Math.random() * 9);
      y = (int)(Math.random() * 9);
    } while (!(m.isValidArea(boatarray[4],orient,x,y,boats)));
    m.placeBoat(boatarray[4],orient,x,y,boats);
  }

  /**
   * @param Model m
   * makes a strategic move for the computer
   * @return Point
   */
  public Point makeMove(Model m) {
    // initiliaze temp values for making move
    int x = 0, y = 0;
    if (stage == STAGE1) {
      do {
        x = (int)(Math.random() * 9);
        y = (int)(Math.random() * 9);
        move = new Point(x, y);
      } while (!m.possibleMove(m, move) && pseudoShots[x][y] == 'x');
    }

    return move;
  }
}