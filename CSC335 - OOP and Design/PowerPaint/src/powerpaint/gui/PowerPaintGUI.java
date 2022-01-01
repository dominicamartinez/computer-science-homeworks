package powerpaint.gui;

import factory.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import powerpaint.model.*;

// Dominic Martinez
// 3/25/03

/**
 *
 * <p>Title: PowerPaintGUI</p>
 * <p>Description: this is the paint program with simple features</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Dominic Martinez
 * @version 1.0
 */

// Singletonin' it
 public class PowerPaintGUI {
  public static void main(String[] args) {
    new PPFrame().show();
  }
}

/**
 * <p>Title: PPFRame</p>
 * <p>Description: this is the main gui class</p>
 */
class PPFrame extends JFrame {

  private drawingBoard myCanvas;
  private JColorChooser colorChooser;
  private JMenuItem myUndo;
  private String choice;
  private Color myColor;
  private ShapeList list;
  private JRadioButton line, pencil, ellipse, rectangle;

/**
 * Constructor that initiliazes all GUI components
 */
  public PPFrame() {
    // initializing default values
    setTitle("C SC 335: Microstuff PowerPaint");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myCanvas = new drawingBoard();
    choice = "Pencil";
    colorChooser = new JColorChooser();
    myColor = Color.black;
    list = new ShapeList();
    myUndo = null;
    menuListener menuL = new menuListener();
    myListener mL = new myListener();

    //creating the menus
    JMenu menu = MenuFactory.createJMenu("File");
    MenuFactory.createJMenuItem("Clear", menuL, menu);
    menu.addSeparator();
    MenuFactory.createJMenuItem("Exit", menuL, menu);
    JMenu menu2 = MenuFactory.createJMenu("Options");
    myUndo = MenuFactory.createJMenuItem("Undo", menuL, menu2);
    myUndo.setEnabled(false);
    JMenu menu3 = MenuFactory.createJMenu("Help");
    MenuFactory.createJMenuItem("About...", menuL, menu3);
    JMenuBar mb = new JMenuBar();
    mb.add(menu);
    mb.add(menu2);
    mb.add(menu3);
    setJMenuBar(mb);

    //creating the toolbar
    JToolBar tb = new JToolBar("Tools", 0);
    ButtonGroup bg = new ButtonGroup();
    JButton jbutton = ButtonFactory.createJButton("Color...", menuL, tb);
    jbutton.setBackground(myColor);

    pencil = ButtonFactory.createJRadioButton("Pencil", mL, tb, bg);
    Object obj = (Object)this;
    Image img = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/pencil.gif")))), ((Component)obj));
    Image img2 = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/pencil_bw.gif")))), ((Component)obj));
    pencil.setSelectedIcon(new ImageIcon(img));
    pencil.setIcon(new ImageIcon(img2));


    line = ButtonFactory.createJRadioButton("Line", mL, tb, bg);
    img = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/line.gif")))), ((Component)obj));
    img2 = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/line_bw.gif")))), ((Component)obj));
    line.setSelectedIcon(new ImageIcon(img));
    line.setIcon(new ImageIcon(img2));

    rectangle = ButtonFactory.createJRadioButton("Rectangle", mL, tb, bg);
    img = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/rectangle.gif")))), ((Component)obj));
    img2 = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/rectangle_bw.gif")))), ((Component)obj));
    rectangle.setSelectedIcon(new ImageIcon(img));
    rectangle.setIcon(new ImageIcon(img2));

    ellipse = ButtonFactory.createJRadioButton("Ellipse", mL, tb, bg);
    img = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/ellipse.gif")))), ((Component)obj));
    img2 = ImageFactory.makeImage(String.valueOf(String.valueOf((new StringBuffer("images/ellipse_bw.gif")))), ((Component)obj));
    ellipse.setSelectedIcon(new ImageIcon(img));
    ellipse.setIcon(new ImageIcon(img2));

    //adding everything in (preferred size)
    Container container = getContentPane();
    container.add(myCanvas, "Center");
    container.add(tb, "South");
    pack();
    // center of the screen
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension d2 = getSize();
    setLocation((d.width - d2.width) / 2, (d.height - d2.height) / 2);
  }

/**
 * this is the drawing board class
 */
    private class drawingBoard extends JPanel {

    private Shape shape;

    /**
     * constructor of drawing boards initializes component preference
     */
    public drawingBoard() {
      shape = null;
      setPreferredSize(new Dimension(400, 300));
      setBackground(Color.white);
      mouseListener m = new mouseListener();
      addMouseListener(m);
      addMouseMotionListener(m);
    }

    /**
     * paints the component
     * @param g
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      ShapeItem item;
      for(Iterator iterator = list.iterator(); iterator.hasNext(); g2.draw(item.getShape())) {
        item = (ShapeItem)iterator.next();
        g2.setPaint(item.getColor());
      }

      if (shape != null) {
        g2.setPaint(myColor);
        g2.draw(shape);
      }
    }

  /**
   * this is the mouse listener for the drawingBoard
   *
   */
      private class mouseListener extends MouseInputAdapter {

        private Point pt = null;

        /**
         * this decides what to do when the mouse is pressed down
         * @param event
         */
        public void mousePressed(MouseEvent event) {
          Point tempPoint = event.getPoint();
          int x = event.getX();
          int y = event.getY();

          if (choice == "Pencil") {
            GeneralPath path = new GeneralPath();
            path.moveTo(x, y);
            path.lineTo(x, y);
            shape = path;
          }
          else if (choice == "Line") {
            shape = new Line2D.Double(tempPoint, tempPoint);
          }
          else if (choice == "Rectangle") {
            shape = new Rectangle2D.Double(x, y, 0.0D, 0.0D);
            pt = tempPoint;
          }
          else if (choice == "Ellipse") {
            shape = new Ellipse2D.Double(x, y, 0.0D, 0.0D);
            pt = tempPoint;
          }
          repaint();
        }

        /**
         * decides what to do when mouse button is released
         * @param e
         */
        public void mouseReleased(MouseEvent e) {
          if (shape != null)  {
            ShapeItem item = new ShapeItem(shape, myColor);
            shape = null;
            list.add(item);
            myUndo.setEnabled(true);
          }
        }

        /**
         * this decides what to do when mouse button is held down
         * and being moved around
         * @param event
         */
        public void mouseDragged(MouseEvent event) {
          int x = event.getX();
          int y = event.getY();
         if (choice == "Pencil") {
            GeneralPath path = (GeneralPath)shape;
            path.lineTo(x, y);
          }
          else if (choice == "Line") {
            Line2D line2d = (Line2D)shape;
            line2d.setLine(line2d.getP1(), new Point2D.Double(x, y));
          }
          else if (choice == "Rectangle") {
            Rectangle2D rectangle2d = (Rectangle2D)shape;
            double x2 = pt.getX();
            double y2 = pt.getY();
            double x3 = Math.min(x, x2);
            double y3 = Math.min(y, y2);
            double w = Math.abs((double)x - x2);
            double h = Math.abs((double)y - y2);
            rectangle2d.setRect(x3, y3, w, h);
          }
          else if (choice == "Ellipse") {
            Ellipse2D ellipse2d = (Ellipse2D)shape;
            double x2 = pt.getX();
            double y2 = pt.getY();
            double x3 = Math.min(x, x2);
            double y3 = Math.min(y, y2);
            double w = Math.abs((double)x - x2);
            double h = Math.abs((double)y - y2);
            ellipse2d.setFrame(x3, y3, w, h);
          }
          repaint();
        }
      }
  }

/**
 * this is the listener for the radio buttons
 */
    private class myListener implements ActionListener {
       public void actionPerformed(ActionEvent actionevent) {
          choice = actionevent.getActionCommand();
       }
    }

/**
 * this is the listener for the menu and the lone Jbutton
 *
 */
    private class menuListener implements ActionListener {
    /**
     * this decides what do when a menu item is chosen
     * @param event
     */
      public void actionPerformed(ActionEvent event) {
          Object o = event.getSource();
          String s = event.getActionCommand().intern();
          if (s == "Clear") {
            list.clear();
            myUndo.setEnabled(false);
            repaint();
          }
          else if(s == "Exit") {
             System.exit(0);
          }
          else if (s == "Undo") {
            list.remove();
            if (list.isEmpty()) {
              myUndo.setEnabled(false);
            }
            repaint();
          }
          else if (s == "About...") {
              JOptionPane.showMessageDialog(PPFrame.this, "Microstuff PowerPaint, v1.0");
          }
          else if (s == "Color...") {
              Color c = JColorChooser.showDialog(PPFrame.this, "Select a Color", myColor);
              if(c != null) {
                  myColor = c;
                  JButton jb = (JButton)o;
                  jb.setBackground(c);
              }
          }
          else {
            repaint();
          }
       }
    }
}
