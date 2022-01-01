package powerpaint.model;

import java.util.*;
import java.io.*;

/**
 * <p>Title: ShapeList</p>
 * <p>Description: This is to keep track of the items being painted
 * on the board</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Dominic Martinez
 * @version 1.0
 */

public class ShapeList implements Serializable {

  private Vector list;

/**
 * constructor creates new list
 */
  public ShapeList() {
    list = new Vector();
  }

/**
 * adds item to list
 * @param s
 */
  public void add(ShapeItem s) { list.add(s); }

/**
 * removes last item put in the list
 */
  public void remove() {
    if (list.isEmpty()) {}
    else { list.remove((list.size()-1)); }
  }

/**
 * resets the list
 */
  public void clear() { list.removeAllElements(); }

/**
 * this returns the iterator for the list
 * @return iterator
 */
  public Iterator iterator() { return list.iterator(); }

/**
 * gets item at certain index
 * @param i
 * @return ShapeItem
 */
  public ShapeItem getAt(int i) { return (ShapeItem)list.get(i); }
/**
 * this returns true if list is empty
 * @return boolean
 */
  public boolean isEmpty() { return list.isEmpty(); }
/**
 * this returns the size
 * @return int
 */
  public int size() { return list.size(); }
}