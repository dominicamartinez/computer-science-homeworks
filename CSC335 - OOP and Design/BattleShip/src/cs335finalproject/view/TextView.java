package cs335finalproject.view;

import cs335finalproject.model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import cs335finalproject.*;
import cs335finalproject.boat.*;
import factory.*;

public class TextView extends View {
  private Layout main;
  private InteractionPanel ip;
  private JComboBox shipList, cordX, cordY, orientation;
  private JButton fire;
  private Battleship bs;
  private Carrier car;
  private Cruiser cr;
  private Destroyer des;
  private Sub sub;
  private char[][] myBoats;
  private char[][] myShots;

  /**
   * @param Model m
   * Textview's constructor
   */
    public TextView(Model m) {
      // setting default options
      super(m);

      // initialize player layouts
      if (m.getState() == Model.NOT_STARTED) {
        myShots = model.getHuman().getHumanShotLayout();
        for (int i = 0; i < 10; i++) {
          for (int j = 0; j < 10; j++) {
            myShots[i][j] = '.';
          }
        }
        myBoats = model.getHuman().getHumanBoatLayout();
        for (int i = 0; i < 10; i++) {
          for (int j = 0; j < 10; j++) {
            myBoats[i][j] = '.';
          }
        }
      }
      else {
        myBoats = model.getHuman().getHumanBoatLayout();
        myShots = model.getHuman().getHumanShotLayout();
      }

      Boat[] modelBoats = model.getHuman().getBoats();
      bs = (Battleship)(modelBoats[0]);
      car = (Carrier)(modelBoats[1]);
      cr = (Cruiser)(modelBoats[2]);
      des = (Destroyer)(modelBoats[3]);
      sub = (Sub)(modelBoats[4]);

      // draw view
      myListener ml = new myListener();
      setPreferredSize(new Dimension(600, 450));
      setBackground(Color.gray);

      JPanel main = new JPanel();
      JPanel north = new JPanel();

      BoatLayout bl = new BoatLayout();
      ShotLayout sl = new ShotLayout();

      north.setLayout(new GridLayout());
      north.add(new JLabel(model.getHuman().getName()+ "'s Boats"));
      north.add(new JLabel(model.getHuman().getName() +"'s Shots"));

      main.setLayout(new BorderLayout());

      // draw interaction panel
      ip = new InteractionPanel();
      Object[] boats = {"Cruiser", "Carrier", "Destroyer", "Submarine", "Battleship"};
      shipList = new JComboBox(boats);
      ip.add(shipList);
      Object[] choices = {"Vertical", "Horizontal"};
      orientation = new JComboBox(choices);
      ip.add(orientation);
      Object[] x = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
      Object[] y = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
      cordX = new JComboBox(x);
      cordY = new JComboBox(y);
      fire = new JButton("Place");
      fire.addActionListener(ml);
      ip.add(cordY);
      ip.add(cordX);
      ip.add(fire);

      main.add(ip, BorderLayout.SOUTH);
      main.add(north, BorderLayout.NORTH);
      main.add(sl, BorderLayout.EAST);
      main.add(bl, BorderLayout.WEST);

      this.add(main);
      this.setBackground(Color.lightGray);
   }

    /* Code for the action listener. Only need it on the button becuase we can just
    get the values of all the other selections. */
    private class myListener implements ActionListener {
      char[][] opponentBoats = model.getPlayer().getComputerBoatLayout();
      char[][] opponentShots = model.getPlayer().getComputerShotLayout();
      private boolean place = false;

      public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        // actions if still setting boats. Checks to see if the selected coordinates
        // is a valid area to place boats. Then places them
        if (source == fire && model.getState() == Model.CHOOSING_BOATS) {
          String orient = orientation.getSelectedItem().toString();
          int xcord = Integer.parseInt(cordX.getSelectedItem().toString()) - 1;
          int ycord = cordY.getSelectedIndex();

          if (shipList.getSelectedItem().toString().equals("Battleship")) {
            place = model.isValidArea((Boat)bs, orient, xcord, ycord, myBoats);
            if (place) {
              model.removeBoat((Boat)bs, myBoats);
              model.placeBoat((Boat)bs, orient, xcord, ycord, myBoats);
            }
          }
          else if (shipList.getSelectedItem().toString().equals("Carrier")) {
            place = model.isValidArea((Boat)car, orient, xcord, ycord, myBoats);
            if (place) {
              model.removeBoat((Boat)car, myBoats);
              model.placeBoat((Boat)car, orient, xcord, ycord, myBoats);
            }
          }
          else if (shipList.getSelectedItem().toString().equals("Cruiser")) {
            place = model.isValidArea((Boat)cr, orient, xcord, ycord, myBoats);
            if (place) {
              model.removeBoat((Boat)cr, myBoats);
              model.placeBoat((Boat)cr, orient, xcord, ycord, myBoats);
            }
          }
          else if (shipList.getSelectedItem().toString().equals("Destroyer")) {
            place = model.isValidArea((Boat)des, orient, xcord, ycord, myBoats);
            if (place) {
              model.removeBoat((Boat)des, myBoats);
              model.placeBoat((Boat)des, orient, xcord, ycord, myBoats);
            }
          }
          else if (shipList.getSelectedItem().toString().equals("Submarine")) {
            place = model.isValidArea((Boat)sub, orient, xcord, ycord, myBoats);
            if (place) {
              model.removeBoat((Boat)sub, myBoats);
              model.placeBoat((Boat)sub, orient, xcord, ycord, myBoats);
            }
          }

          if (!place) {
            JOptionPane.showConfirmDialog(TextView.this,"Not a good place","Error",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
          }
        }

        // if the game is in progress, then it will determine the shot coordinates; if
        // it is, then shoot there and update arrays accordingly. Ends game if either player
        // scores 17 hits (all ships sunk).
        else if (source == fire && model.getState() == Model.IN_PROGRESS) {
          int xshot = Integer.parseInt(cordX.getSelectedItem().toString()) - 1;
          int yshot = cordY.getSelectedIndex();
          if (myShots[xshot][yshot] != '.')
            return;
          else {
            if ( opponentBoats[xshot][yshot] == '.')
              myShots[xshot][yshot] = 'M';
            else {
              myShots[xshot][yshot] = 'H';
              model.getHuman().incrementHumanHits();
              if (model.getHuman().getHumanHits() == 17) {
                JOptionPane.showMessageDialog(TextView.this, "GAME OVER", "YOU WIN!", JOptionPane.PLAIN_MESSAGE);
                model.setState(Model.END);
              }
            }
          Point move = model.getPlayer().getStrategy().makeMove(model);
            if (myBoats[(int)(move.getX())][(int)(move.getY())] != '.') {
              myBoats[(int)(move.getX())][(int)(move.getY())] = 'H';
              opponentShots[(int)(move.getX())][(int)(move.getY())] = 'H';
              model.getPlayer().setLastMove('H');
              model.getPlayer().incrementComputerHits();
              if (model.getPlayer().getComputerHits() == 17) {
                JOptionPane.showMessageDialog(TextView.this, "GAME OVER", "YOU LOSE!", JOptionPane.PLAIN_MESSAGE);
                model.setState(Model.END);
              }
            }
            else {
              myBoats[(int)(move.getX())][(int)(move.getY())] = 'M';
              opponentShots[(int)(move.getX())][(int)(move.getY())] = 'M';
              model.getPlayer().setLastMove('M');
            } repaint();
          }
        }
      }
    }

    /**
     * returns the string "TextView"
     * @return String
     */
    public String getView() { return "TextView"; }

    // paints the TextView. Enables certain buttons during different states
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      int state = model.getState();
      if (state == Model.CHOOSING_BOATS) {
        shipList.setEnabled(true);
        orientation.setEnabled(true);
          cordX.setEnabled(true);
          cordY.setEnabled(true);
          fire.setEnabled(true);
      }
      else if (state == Model.IN_PROGRESS) {
          fire.setText("Fire");
          shipList.setEnabled(false);
          orientation.setEnabled(false);
          cordX.setEnabled(true);
          cordY.setEnabled(true);
          fire.setEnabled(true);
      }
      else if (state == Model.END) {
          shipList.setEnabled(false);
          orientation.setEnabled(false);
          cordX.setEnabled(false);
          cordY.setEnabled(false);
          fire.setEnabled(false);
      }
    }

    private class Layout extends JPanel {
      public Layout(int x, int y) {
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.lightGray);
      }
    }

    // shotlayout; shots player shoots
    private class ShotLayout extends JPanel {
      int state = model.getState();

      public ShotLayout() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(0, 166, 255));
        setBorder(BorderFactory.createBevelBorder(1));
      }

      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // makes the Numbers on the top
        int xCounter = 45;
        int yCounter = 20;
        for (int i = 0; i <= 10; i++) {
          if ( i == 0) { }
          else { g2.drawString("" + i, xCounter, yCounter); xCounter += 25; }
        }

        xCounter = 20;
        yCounter = 45;
        for (char c = 'A'; c <= 'J'; c++) {
          g2.drawString("" + c, xCounter, yCounter); yCounter += 25;
        }

        // actual code to display shots of human player
        for (int i = 0; i < 10; i++) {
          for (int j = 0; j < 10; j++) {
            char temp = myShots[i][j];
              if (temp == 'H') {
                g2.setPaint(Color.red);
                g2.drawString("" + temp, (i*25) + 45, (j*25) + 45);
                g2.setPaint(Color.black);
              }
              else
                g2.drawString("" + temp, (i*25) + 45, (j*25) + 45);
          }
        }
      }
    }

    // boat layout; shows player boats and shots taken by computer
    private class BoatLayout extends JPanel {
        public BoatLayout() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(0, 166, 255));
        setBorder(BorderFactory.createBevelBorder(1));

        // initial positions of boats
        if (model.getState() == Model.NOT_STARTED) {
          for (int j = 0; j < bs.getLength(); j++) {
            myBoats[ (int) (bs.getPoint().getX())][j] = bs.getChar();
          }
          for (int j = 0; j < cr.getLength(); j++) {
            myBoats[ (int) (cr.getPoint().getX())][j] = cr.getChar();
          }
          for (int j = 0; j < car.getLength(); j++) {
            myBoats[ (int) (car.getPoint().getX())][j] = car.getChar();
          }
          for (int j = 0; j < des.getLength(); j++) {
            myBoats[ (int) (des.getPoint().getX())][j] = des.getChar();
          }
          for (int j = 0; j < sub.getLength(); j++) {
            myBoats[ (int) (sub.getPoint().getX())][j] = sub.getChar();
          }
        }
      }

      // paints boat panel
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        int state = model.getState();

        // makes the Numbers on the top
        int xCounter = 45;
        int yCounter = 20;
        for (int i = 0; i <= 10; i++) {
          if ( i == 0) { }
          else { g2.drawString("" + i, xCounter, yCounter); xCounter += 25; }
        }

        xCounter = 20;
        yCounter = 45;
        for (char c = 'A'; c <= 'J'; c++) {
          g2.drawString("" + c, xCounter, yCounter); yCounter += 25;
        }

        for (int i = 0; i < 10; i++) {
          for (int j = 0; j < 10; j++) {
            char temp = myBoats[i][j];
              if (temp == 'H') {
                g2.setPaint(Color.red);
                g2.drawString("" + temp, (i*25) + 45, (j*25) + 45);
                g2.setPaint(Color.black);
              }
              else
                g2.drawString("" + temp, (i*25) + 45, (j*25) + 45);
          }
        }
      }
    }

    // panel player uses to interact with the game.
    private class InteractionPanel extends JPanel {
      public InteractionPanel() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setBackground(Color.lightGray);

        int state = model.getState();
      }

      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        /*if (shipList.getSelectedItem().toString().equals("Cruiser")) {
          g2.setFont(new Font("Arial",Font.BOLD,12));
          g2.drawString("Cruiser",30,100);
          g2.drawString("Length: 3", 130, 100);
          g2.drawString("\"R\"", 90, 150);
          Image image = ImageFactory.makeImage("images/cruiser_h4.gif",this);
          g2.drawImage(image,50,100,100,30,null);
        }
        else if (shipList.getSelectedItem().toString().equals("Destroyer")) {
          g2.setFont(new Font("Arial",Font.BOLD,12));
          g2.drawString("Destroyer",30,100);
          g2.drawString("Length: 2", 130, 100);
          g2.drawString("\"D\"", 90, 150);
          Image image = ImageFactory.makeImage("images/destroyer_h4.gif",this);
          g2.drawImage(image,50,100,100,30,null);
        }
        else if (shipList.getSelectedItem().toString().equals("Carrier")) {
          g2.setFont(new Font("Arial",Font.BOLD,12));
          g2.drawString("Carrier",30,100);
          g2.drawString("Length: 5", 130, 100);
          g2.drawString("\"C\"", 90, 150);
          Image image = ImageFactory.makeImage("images/carrier_h4.gif",this);
          g2.drawImage(image,50,100,100,30,null);
        }
        else if (shipList.getSelectedItem().toString().equals("Battleship")) {
          g2.setFont(new Font("Arial",Font.BOLD,12));
          g2.drawString("Battleship",30,100);
          g2.drawString("Length: 4", 130, 100);
          g2.drawString("\"B\"", 90, 150);
          Image image = ImageFactory.makeImage("images/battleship_h4.gif",this);
          g2.drawImage(image,50,100,100,30,null);
        }
        else if (shipList.getSelectedItem().toString().equals("Submarine")) {
          g2.setFont(new Font("Arial",Font.BOLD,12));
          g2.drawString("Submarine",30,100);
          g2.drawString("Length: 3", 130, 100);
          g2.drawString("\"S\"", 90, 150);
          Image image = ImageFactory.makeImage("images/sub_h4.gif",this);
          g2.drawImage(image,50,100,100,30,null);
        }
        repaint();
      */}
    }
  }