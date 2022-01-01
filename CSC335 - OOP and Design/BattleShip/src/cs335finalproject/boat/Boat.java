package cs335finalproject.boat;

import java.awt.*;

public interface Boat {

  public int getLength();

  public Point getPoint();

  public char getChar();

  public String getOrientation();

  public void setOrientation(String orientation);

  public void setPoint(int xcord, int ycord);
}