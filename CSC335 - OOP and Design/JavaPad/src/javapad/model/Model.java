package javapad.model;

import java.io.InputStream;
import java.io.IOException;

public interface Model
{
  Object getOption(String name);
  void loadOptions(String filename) throws Exception;
  String loadText(String filename) throws IOException;
  String loadText(InputStream instream) throws IOException;
  void saveOptions(String filename) throws IOException;
  void saveText(String filename, String text) throws IOException;
  void setOption(String name, Object obj);
}