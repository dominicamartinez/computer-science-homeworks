package factory;

import java.applet.*;
import java.awt.*;
import java.net.*;
import java.util.*;


/** A class for loading images from the hard disk or web sites for use in
 *  an applet or application.  An implementation of the Factory design pattern.
 *
 *  @author Martin Stepp (stepp@cs.arizona.edu)
 *  @version Aug 6, 2002
 */
public class ImageFactory {
  public static final double JAVA_VERSION = new Double(System.getProperty("java.version").substring(0, 3)).doubleValue();
  private static final Hashtable imageHash = new Hashtable();
  private static final boolean SHOULD_HASH = true;

  // private constructor to prohibit constructing factory
  private ImageFactory() {}

  /** Makes and returns an image by loading it from the given file name.
   *  In application context, this loads from the local disk.
   *  In applet context, this treats fileName as a relative URL from the
   *  applet's current code base URL directory.
   *
   *  @param fileName the fully-qualified filename, or relatively qualified URL,
   *  of the image resource to load.
   *  @param comp the graphical component or applet responsible for tracking
   *  the loading of this image.
   *
   *  @return the successfully loaded Image object.
   */
  public static Image makeImage(String fileName, Component comp) {
    return makeImage(fileName, null, comp);
  }

  /** Makes and returns an image by loading it from the given file name
   *  within the given JAR archive.
   *  In application context, this loads from the local disk.
   *  In applet context, this treats fileName as a relative URL from the
   *  applet's current code base URL directory.
   *
   *  This method and its jarFileName option exists to permit
   *  loading images from within a JAR; the method will construct a String
   *  that represents the JAR path to the image, such as:
   *  <p><code><pre>
   *  jar:file:/h:/java/myapplet/myapplet.jar!/images/someimage.gif
   *
   *  or
   *
   *  jar:http://www.mysite.com/myapplet/myapplet.jar!/images/someimage.gif
   *  </pre></code>
   *
   *  @param fileName the fully-qualified filename, or relatively qualified URL,
   *  of the image resource to load.
   *  @param comp the graphical component or applet responsible for tracking
   *  the loading of this image.
   *  If in applet context, the applet should be passed for this parameter.
   *  @param jarFileName loads as though the fileName is in the given jar file.
   *
   *  @return the successfully loaded Image object.
   */
  public static Image makeImage(String fileName, String jarFileName, Component comp) {
    Image img = null;

    // look up previously loaded image
    if (SHOULD_HASH  &&  imageHash.containsKey(fileName))
      img = (Image)imageHash.get(fileName);

    // check legality of loading image from a JAR
    if (JAVA_VERSION < 1.2  &&  jarFileName != null)
      throw new RuntimeException("Can't load from a JAR in pre-v1.2 Java!  Current version is: " + JAVA_VERSION);

    // if an applet, load image from the web
    if (comp instanceof Applet) {
      Applet applet = (Applet)comp;

      URL codeBase = applet.getCodeBase();
      if (codeBase == null)
        throw new IllegalArgumentException("Applet has null code base!\n\n" +
          "This error usually occurs when you try to get images in the constructor of an applet or one of the applet's child classes.\n\n" +
          "Instead, do all image-handling no earlier than in the init() method.");

      try {
        URL fileURL = (jarFileName != null)
                    ? new URL("jar:" + codeBase + jarFileName + "!/" + fileName)
                    : new URL(codeBase, fileName);
        img = applet.getImage(fileURL);
      } catch (MalformedURLException mfurle) {
        throw new IllegalArgumentException("Bad URL loading non-codebase image from " + fileName + " : " + mfurle);
      }
    }
    // if not an applet, load image from a file
    else {
      Toolkit tk = Toolkit.getDefaultToolkit();
      if (jarFileName != null) {
        // load from JAR as URL
        try {
          URL fileURL = new URL("jar:file:" + System.getProperty("user.dir") + jarFileName + "!/" + fileName);
          tk.getImage(fileURL);
        } catch (MalformedURLException mfurle) {
          throw new IllegalArgumentException("Bad URL loading JAR image from " + fileName + " : " + mfurle);
        }
      } else {
        // load as plain file
        img  = tk.getImage(fileName);
      }
    }

    if (img == null)
      throw new IllegalArgumentException("null; unable to load image from " + fileName);

    // wait for image to load using MediaTracker
    MediaTracker mt = new MediaTracker(comp);
    mt.addImage(img, 0);
    try {
      mt.waitForID(0);
    } catch (InterruptedException ie) {
      throw new IllegalArgumentException("Interrupted while loading image from " + fileName);
    }

    // check to see if image loaded properly
    if (img.getWidth(comp) == -1  ||  img.getHeight(comp) == -1)
      throw new IllegalArgumentException("Negative width/height; after media tracking, no valid image data was found in " + fileName);

    if (SHOULD_HASH)
      imageHash.put(fileName, img);

    return img;
  }
}






