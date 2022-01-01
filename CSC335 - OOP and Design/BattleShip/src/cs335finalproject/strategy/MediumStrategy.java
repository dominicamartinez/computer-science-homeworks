package cs335finalproject.strategy;

import java.awt.*;
import cs335finalproject.model.*;
import cs335finalproject.players.*;
import cs335finalproject.boat.*;
import java.util.*;

public class MediumStrategy implements Strategy {
  private Point move;
  private char[][] boats;
  private char[][] myShots;
  private Stack points;
  private static final int FIRST_HALF = 1, SECOND_HALF = 2;
  private int locationX, locationY;
  private boolean lastMoveHit = false;

  public MediumStrategy(Model m) {
    points = new Stack();
    locationX = this.FIRST_HALF;
    locationY = this.FIRST_HALF;
    boats = m.getPlayer().getComputerBoatLayout();
    myShots = m.getPlayer().getComputerShotLayout();
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

  public Point makeMove(Model m) {
    boolean placed = false;
    if (m.getPlayer().computerLastMove() == 'H') {
      lastMoveHit = true;
    }
    else lastMoveHit = false;
    int x = 0, y = 0;
    if (lastMoveHit == false && points.isEmpty()) {
      do {
        x = ((int) (Math.random() * 5) + ((locationX-1) * 5) ) % 10;
        y = ((int) (Math.random() * 5) + ((locationY-1) * 5) ) % 10;
      } while (!m.possibleMove(m, new Point(x,y)));
      if (locationX == this.SECOND_HALF) {
        locationX--;
        if (locationY == this.SECOND_HALF)
          locationY--;
        else locationY++;
      }
      else locationX++;
    }

    else {
      if (lastMoveHit)
        points.push(move);
      else
        move = (Point) (points.peek());
      do {
        int tempX = (int) (move.getX());
        int tempY = (int) (move.getY());
        if (m.possibleMove(m, new Point(tempX - 1, tempY))) {
          x = tempX - 1;
          y = tempY;
          placed = true;
        }
        else if (m.possibleMove(m, new Point(tempX, tempY - 1))) {
          x = tempX;
          y = tempY - 1;
          placed = true;
        }
        else if (m.possibleMove(m, new Point(tempX + 1, tempY))) {
          x = tempX + 1;
          y = tempY;
          placed = true;
        }
        else if (m.possibleMove(m, new Point(tempX, tempY + 1))) {
          x = tempX;
          y = tempY + 1;
          placed = true;
        }
        else {
          move = (Point) (points.pop());
          placed = false;
          if (points.isEmpty()) {
            Point p = this.makeMove(m);
            x = (int)(p.getX());
            y = (int)(p.getY());
            placed = true;
          }
        }
      }while (!placed);
    }
    move = new Point(x, y);
    return move;
  }
}