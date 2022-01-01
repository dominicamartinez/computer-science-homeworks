package cs335finalproject.boat;

import java.awt.*;

public class Cruiser implements Boat {

  private static final int length = 3;
  private static final char name = 'R';
  private String orientation;
  private Point point;

  public Cruiser() {
    orientation = "Vertical";
    point = new Point(2,0);
  }


    /**
     * gets the point this ship is at
     * @return Point
     */
    public Point getPoint() { return point; }

    /**
     * returns the char this boat will use on the textview
     * @return char
     */
    public char getChar() { return name; }

    /**
     * this will return the length of the boat
     * @return int
     */
    public int getLength() { return length; }

    /**
     * this will return the orientation of the boat
     * @return String
     */
    public String getOrientation() { return orientation; }

    /**
     * this will set the orientation for this boat
     */
    public void setOrientation(String orientation) { this.orientation = orientation; }

    /**
     * this will set the point for this boat
     */
    public void setPoint(int xcord, int ycord) { this.point = new Point(xcord, ycord); }
}