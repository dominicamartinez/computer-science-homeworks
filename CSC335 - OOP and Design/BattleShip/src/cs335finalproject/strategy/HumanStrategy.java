package cs335finalproject.strategy;

import java.awt.*;
import cs335finalproject.model.*;

public class HumanStrategy implements Strategy {

  private Point move;

  public Point makeMove(Model m) { return move; }

  public void chooseMove(Point p) { move = p; }
}