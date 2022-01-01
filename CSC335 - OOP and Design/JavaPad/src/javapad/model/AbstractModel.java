package javapad.model;

import java.awt.*;
import java.io.*;
import java.util.*;

/** Models JavaPad's internal state. */
public abstract class AbstractModel implements Model
{
  protected String text;
  
  public String loadText(String filename) throws IOException
  {
    return loadText(new FileInputStream(filename));
  }
  
  public String loadText(InputStream instream) throws IOException
  {
    BufferedReader in = new BufferedReader(new InputStreamReader(instream));
    String text = "";
    while (in.ready())
      text += (char)in.read();
    in.close();
    return text;
  }
  
  public void saveText(String filename, String text) throws IOException
  {
    OutputStream ostream = new FileOutputStream(filename);
    PrintStream out = new PrintStream(ostream);
    out.print(text);
    out.close();
  }

  public abstract Object getOption(String name);
  public abstract void loadOptions(String filename) throws Exception;
  public abstract void saveOptions(String filename) throws IOException;
  public abstract void setOption(String name, Object obj);
}