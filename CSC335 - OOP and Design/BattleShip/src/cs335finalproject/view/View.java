package cs335finalproject.view;


import java.util.*;
import javax.swing.*;
import cs335finalproject.model.*;


public abstract class View extends JPanel implements Observer {
  protected Model model;

  public View(Model model) {
    this.model = model;
  }

  public void update(Observable o, Object arg)  {
    repaint();
  }
}