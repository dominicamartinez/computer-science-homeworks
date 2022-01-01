package cs335finalproject.strategy;

import cs335finalproject.model.*;
import java.awt.*;

public interface Strategy {
  public Point makeMove(Model m);
}