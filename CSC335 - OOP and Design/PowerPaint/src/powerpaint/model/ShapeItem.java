package powerpaint.model;

import java.awt.*;

/**
 * <p>Title: ShapeItem</p>
 * <p>Description: this is the class that holds the shapeitems information</p>
 */

public class ShapeItem {

  private Shape shape;
  private Color color;
/**
 * constructs the shapeitem accordingly with passed in information
 * @param s
 * @param c
 */
  public ShapeItem(Shape s, Color c) {
    shape = s;
    color = c;
  }
/**
 * returns a shape
 * @return shape
 */
  public Shape getShape() { return shape; }

  /**
   * returns the color
   * @return color
   */
  public Color getColor() { return color; }

}