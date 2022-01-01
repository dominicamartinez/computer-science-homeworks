package javapad.model;

import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * <p>Title: GUIModel</p>
 * <p>Description: This is the class that saves and loads the options
 * for the GUI</p>
 * @author Dominic Martinez
 * @version 1.0
 */

public class GUIModel extends AbstractModel implements Serializable {

  private Hashtable hash;
  private static GUIModel g = null;

  /**
   * Constructor: Loads the options
   */
  public GUIModel() {
    try {loadOptions("options.dat");}
    catch(Exception f) {def();}
  }

  /**
   * singleton'ing the GUIModel
   * @return GUIModel g
   */
  private static GUIModel singleton() {
    if (g == null) {
      g = new GUIModel();
    }
    return g;
  }

  /**
   * loads default options if option file is not found
   */
  public void def() {
    hash = new Hashtable();
    setOption("foreground", Color.black);
    setOption("background", Color.white);
    setOption("font", new Integer(12));
  }

/**
 * this will get the option with a specified name
 * @param name
 * @return Object
 */
  public Object getOption(String name) {
    if (hash.containsKey(name)) {
      return hash.get(name);
    }
    else {return null;}
  }

  /**
   * this will load the options from the file
   * @param filename
   * @throws Exception
   */
  public void loadOptions(String filename) throws Exception {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        hash = (Hashtable)ois.readObject();
  }


  /**
   * this will save the options to the file
   * @param filename
   * @throws IOException
   */
  public void saveOptions(String filename) throws IOException {
       FileOutputStream fos = new FileOutputStream(filename);
       ObjectOutputStream oos = new ObjectOutputStream(fos);
       oos.writeObject(hash);
       oos.close();
  }

  /**
   * this sets the options
   * @param name
   * @param obj
   */
  public void setOption(String name, Object obj) {
    hash.put(name, obj);
  }
}