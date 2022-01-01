package cs335finalproject.strategy;

import java.awt.*;
import cs335finalproject.model.*;
import cs335finalproject.players.*;
import cs335finalproject.boat.*;

// Random strategy class
public class RandomStrategy implements Strategy {
  private Point move;
  private char[][] boats;

  // constructor - initialize boats, put boats onto the computer's array.
  public RandomStrategy(Model m) {
    boats = m.getPlayer().getComputerBoatLayout();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j <10; j++) {
        boats[i][j] = '.';
      }
    }
    Battleship bs = new Battleship();
    Carrier car = new Carrier();
    Cruiser cr = new Cruiser();
    Destroyer des = new Destroyer();
    Sub sub = new Sub();

    Boat[] boatarray = {bs, car, cr, des, sub};
    // checks boats positioning and places if valid.
    for (int i = 0; i < 5; i++) {
      String orient;
      int x, y;
      do {
        x = (int)(Math.random() * 9);
        y = (int)(Math.random() * 9);
        if (Math.random() > 0.5)
          orient = "Vertical";
        else
          orient = "Horizontal";
      } while (!(m.isValidArea(boatarray[i],orient,x,y,boats)));
      m.placeBoat(boatarray[i],orient,x,y,boats);
    }
  }

  // makes next computer move
  public Point makeMove(Model m) {
    do {
      int i = (int)(Math.random() * 10);
      int j = (int)(Math.random() * 10);
      move = new Point(i, j);
    } while (!m.possibleMove(m, move));
    return move;
  }
}