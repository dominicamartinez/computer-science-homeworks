package hangman.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hangman.model.*;
import factory.*;
import java.util.*;

/**
 * <p>Title: HangMan</p>
 * <p>Description: This is the game hangman on the computer
 * this class is singletoning the GUI</p>
 * @author Dominic Martinez
 */
public class HangmanGUI {
  public static void main(String[] args) {
    new HangmanFrame().show();
  }
}

/**
 * <p>Title: HangmanFrame</p>
 * <p>Description: This is the frame that extends JFrame and is the GUI</p>
 */
class HangmanFrame extends JFrame {

// variables
  private static final int H = 450;
  private static final int W = 350;
  private static final String TITLE = "C SC 335: Microstuff Hangman";
  private static final String MONO = "Monospaced";

  private HangmanModel model;
  private HangPanel hp;

  /**
   * constructor which sets all default settings
   */
  public HangmanFrame() {
    // setting settings
    setTitle(TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // making and adding necessary things
    hp = new HangPanel();
    model = HangmanModel.getInstance();
    model.addObserver(new myObserver());
    addKeyListener(new myKeyboard());
    JPanel jp = new JPanel();
    ButtonFactory.createJButton("New", new myListener(), jp);
    Container container = getContentPane();
    container.add(hp);
    container.add(jp, "South");
    hp.setRequestFocusEnabled(true);
    pack();
  }

  /**
   * <p>Title: HangPanel</p>
   * <p>Description: sets the default settings for the panel</p>
   */
  private class HangPanel extends JPanel {
    public HangPanel() {
      setPreferredSize(new Dimension(H, W));
      setBackground(Color.white);
    }

    /**
     * this is what draws out the GUI
     * @param g
     */
    public void paintComponent(Graphics g) {
      // always necessary
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;

      // getting properties
      int w = getWidth();
      int h = getHeight();
      Object o = model.getState();

      // making images and putting them
      String s = String.valueOf("images/hangman" + model.getNumGuessesLeft() + ".gif");
      Image img = ImageFactory.makeImage(s, this);
      int i = (w - img.getWidth(this)) / 2;
      g2.drawImage(img, i, 10, this);
      g2.setFont(new Font(MONO, 1, 18));
      String answer = model.getGuessWord();
      int j = g2.getFontMetrics().stringWidth(answer);
      g2.drawString(answer, (w - j) / 2, h - 60);

      // did we win, how much left, etc.
      String s2 = "";
      if (o == "Game over (lost)") {
        s2 = String.valueOf("You lost! (" + model.getSecretWord() + ")");
      }
      else if (o == "Game over (won)") {
        s2 = String.valueOf("You won with " + model.getNumGuessesLeft() + " guesses left!");
      }
      else {
        s2 = String.valueOf(model.getNumGuessesLeft() + " guesses left");
      }

      j = g2.getFontMetrics().stringWidth(s2);
      g2.drawString(s2, (w - j) / 2, h - 40);
      int k = (w - 364) / 2;
      int l = h - 20;

      // marking off as we go along
      for(char c = 'a'; c <= 'z'; c++) {
        if (model.hasGuessed(c)) {
          g2.setPaint(Color.blue);
        }
        else {
          g2.setPaint(Color.lightGray);
        }
        g2.drawString(String.valueOf(c), k, l);
        k += 14;
      }
    }
  }

  /**
   * <p>Title: myObserver</p>
   * <p>Description: this repaints the GUI</p>
   */
  private class myObserver implements Observer {
    public void update(Observable observe, Object o) {
      repaint();
    }
  }

  /**
   * <p>Title: myListener</p>
   * <p>Description: this resets the game when New is clicked</p>
   */
  private class myListener implements ActionListener {
    public void actionPerformed(ActionEvent e)  {
      model.reset();
    }
  }

  /**
   * <p>Title: myKeyboard</p>
   * <p>Description: this waits for the player to make a guess</p>
   */
  private class myKeyboard extends KeyAdapter {
    public void keyPressed(KeyEvent event) {
      char c = event.getKeyChar();
      if(c >= 'a' && c <= 'z') {
        model.guess(c);
      }
    }
  }
}