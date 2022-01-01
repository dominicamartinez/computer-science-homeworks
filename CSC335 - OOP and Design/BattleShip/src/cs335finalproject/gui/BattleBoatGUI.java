package cs335finalproject.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import factory.*;
import cs335finalproject.model.*;
import cs335finalproject.view.*;
import java.io.IOException;

/**
 * @author Ming Su and Dominic Martinez
 * This is the class that singleton's the battleboat display
 */
public class BattleBoatGUI {
  public static void main(String[] args){
    new BattleBoatFrame().show();
  }
}

/**
 * @author Ming Su and Dominic Martinez
 *  This is the actual battle boat GUI
 */
class BattleBoatFrame extends JFrame {

  // Creates the Menubar for PowerPaint
  private JMenu menu, options, help;
  private JMenuItem newGame, startGame, exit, textView, graphicView, about, howTo;
  // Menubar listener
  private View myView;
  private Model myModel;

  /**
   * Constructor for BattleBoat
   */
  public BattleBoatFrame() {
    setTitle("BattleBoat:: Ver 0.0");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container main = getContentPane();
    myModel = new Model();
    myView = new ImageView(myModel);
    myModel.addObserver(myView);
    setJMenuBar(makeMenuBar());
    main.add(myView, BorderLayout.CENTER);
    pack();
  }

  /**
   * this is just a method to change the view
   * @param v
   */
  private void setView(View v) {
    Container cp = getContentPane();
    cp.remove(myView);
    myModel.deleteObserver(myView);
    myView = v;

    myModel.addObserver(myView);
    cp.add(myView, BorderLayout.CENTER);
    cp.validate();
  }
  /**
   * this makes the menubar
   * @return JMenuBar
   */
  private JMenuBar makeMenuBar() {
    menu = MenuFactory.createJMenu("Menu");
    options = MenuFactory.createJMenu("Options");
    help = MenuFactory.createJMenu("Help");
    MenuItemListener mil = new MenuItemListener();
    newGame = MenuFactory.createJMenuItem("New Game", mil, menu);
    menu.addSeparator();
    startGame = MenuFactory.createJMenuItem("Start Game", mil, menu);
    exit = MenuFactory.createJMenuItem("Exit", mil, menu);
    graphicView = MenuFactory.createJMenuItem("Graphic View", mil, options);
    textView = MenuFactory.createJMenuItem("Text View", mil, options);
    about = MenuFactory.createJMenuItem("About", mil, help);
    howTo = MenuFactory.createJMenuItem("How To Play", mil, help);
    JMenuBar bar = new JMenuBar();
    bar.add(menu);
    bar.add(options);
    bar.add(help);
    return bar;
  }


  /**
   * this is the listener for the menu buttons
   */
  private class MenuItemListener implements ActionListener {
    /**
     * this performs the action depending on which menu
     * button was pressed
     */
    public void actionPerformed(ActionEvent event) {
      Object source = event.getSource();
      if (source == exit) {
         System.exit(0);
      }
      else if (source == newGame) {
        // letting the user set their name (default == player1)
        Object n = JOptionPane.showInputDialog(BattleBoatFrame.this, "What is your name?", "Your name",
        JOptionPane.QUESTION_MESSAGE);
        if ( n == null || n.equals("")) {
          myModel.setName("Player 1");
        }
        else { myModel.setName(n); }

        // letting the user choose the difficulty
        Object[] options = { "Easy", "Medium", "Hard" };
        myModel.setStrategy(JOptionPane.showInputDialog(BattleBoatFrame.this, "Choose your might", "Select Strategy",
        JOptionPane.OK_CANCEL_OPTION, null, options, options[0]));

        // let user choose their view and start setting up boats
        Object[] v = { "Text", "Image" };
        Object v2 = JOptionPane.showInputDialog(BattleBoatFrame.this, "Choose your view", "Do you want to see pretty pictures or ugly text?",
        JOptionPane.OK_CANCEL_OPTION, null, v, v[0]);
        myModel.setState(Model.NOT_STARTED);
        if ( v2 == "Text" ) {
          setView(new TextView(myModel));

        }
        else { setView(new ImageView(myModel)); }
        myModel.setState(Model.CHOOSING_BOATS);
      }
      else if (source == startGame) {
        // this is where the actual game starts
        myModel.setState(Model.IN_PROGRESS);

      }
      else if (source == graphicView) {
        // switches views
        setView(new ImageView(myModel));
      }
      else if (source == textView) {
        // switches views
        setView(new TextView(myModel));
      }
      else if (source == about) {
        JOptionPane.showMessageDialog(BattleBoatFrame.this, "");
      }
      else if (source == howTo) {
        howToPlay h = new howToPlay();
        h.show();
      }
    }
  }

  /**
   * @author Dominic Martinez
   * this is the class for the rules on battleboat
   */
  private class howToPlay extends JFrame {

    private JEditorPane webpane;

    public howToPlay() {
      setTitle("How To Play the Game: BATTLESHIP");
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      // editor pane shows web pages
      webpane = new JEditorPane();
      webpane.setEditable(false);

      Container center = new JScrollPane(webpane);  // make web page scroll

      Container cp = getContentPane();
      cp.add(center, BorderLayout.CENTER);
      setSize(800, 600);

      try {webpane.setPage("http://www.centralconnector.com/GAMES/battleship.html");} catch (IOException ioe) {
          JOptionPane.showMessageDialog(this,
          "Could not load page!\n\n" + ioe.getMessage(),
          "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}