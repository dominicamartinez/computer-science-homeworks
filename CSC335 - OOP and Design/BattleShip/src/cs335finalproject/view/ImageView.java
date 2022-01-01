package cs335finalproject.view;

import cs335finalproject.model.*;
import factory.*;
import java.net.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ImageView extends View {

    // TargetPanel constructor
    public ImageView(Model m) {
      super(m);
      setPreferredSize(new Dimension(600, 600));
      setBackground(Color.blue);
      TargetMouseAdapter tma = new TargetMouseAdapter();
      addMouseListener(tma);
      addMouseMotionListener(tma);
    }

    public String getView() { return "ImageView"; }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      int state = model.getState();
      if (state == Model.NOT_STARTED) {
       Image splash = ImageFactory.makeImage("images/splash.jpg", this);
       g2.drawImage(splash, 0, 0, this);
      }
      else if (state == Model.CHOOSING_BOATS) {

      }
      else if (state == Model.IN_PROGRESS) {

      }
    }

    // Mouse input adapter
    private class TargetMouseAdapter
        extends MouseInputAdapter {
      // actions for mouse pressed
      public void mousePressed(MouseEvent event) {
        repaint();
      }

      // actions for mouse dragged
      public void mouseDragged(MouseEvent event) {
        repaint();
      }

      // actions for mouse released
      public void mouseReleased(MouseEvent event) {
        repaint();
      }
    }
}