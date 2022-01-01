// Dominic Martinez
// 2/20/03
package JavaPad.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javapad.model.*;
import java.io.*;

/**
 *
 * <p>Title: JavaPadGUI</p>
 * <p>Description: This is the JavaPad's GUI, singleton style</p>
 * @author Dominic Martinez
 * @version 1.0
 */
public class JavaPadGUI {
  public static void main(String[] args) {
    new GUI().show();
  }
}

/**
 *
 * <p>Title: GUI</p>
 * <p>Description: This is the GUI class</p>
 * @author Dominic Martinez
 * @version 1.0
 */
  class GUI extends JFrame {

  /**
   *
   * <p>Title: myListener</p>
   * <p>Description: This is the class that waits for the response
   * from the GUI</p>
   * @author Dominic Martinez
   * @version 1.0
   */
      class myListener implements ActionListener {

        /**
         * This method gets the actionPerformed
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
          Object source = e.getSource();
          // background being set
          if (source == bBackground) {
            JColorChooser choose =  new JColorChooser();
            Color c = choose.showDialog(GUI.this,"Choose Background", Color.black);
            if (c != null) {
              myText.setBackground(c);
              bBackground.setForeground(c);
            }
          }
          // foreground being set
          else if (source == bForeground) {
            JColorChooser choose =  new JColorChooser();
            Color c = choose.showDialog(GUI.this,"Choose Foreground", Color.black);
            if (c != null) {
              myText.setForeground(c);
              bForeground.setForeground(c);
            }
          }
          // asking where to quit with/out saving
          else if (source == bQuit) {
            Object[] options = {"Yes", "No"};
            int i = JOptionPane.showOptionDialog(myText, "Quitting; Save?", "Save",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            options, options[1]);
            if (i == JOptionPane.YES_OPTION) {
              JFileChooser choose = new JFileChooser();
              int k = choose.showSaveDialog(GUI.this);
              if (k == choose.APPROVE_OPTION) {
                try { String filename = choose.getSelectedFile().toString();
                g.saveText(filename, myText.getText());
                String s = myField.getText();
                int a = Integer.parseInt(s);
                Integer c = new Integer(a);
                g.setOption("font", c);
                g.setOption("background", myText.getBackground());
                g.setOption("foreground", myText.getForeground());
                g.saveOptions("options.dat");
                System.exit(0);
                }
                catch (IOException ioe) { }
                String s = myField.getText();
                int a = Integer.parseInt(s);
                Integer c = new Integer(a);
                g.setOption("font", c);
                g.setOption("background", myText.getBackground());
                g.setOption("foreground", myText.getForeground());
                try{g.saveOptions("options.dat");}
                catch(Exception f) {}
                System.exit(0);
              }
              else { }
            }
            else {
              String s = myField.getText();
              int a = Integer.parseInt(s);
              Integer c = new Integer(a);
              g.setOption("font", c);
              g.setOption("background", myText.getBackground());
              g.setOption("foreground", myText.getForeground());
              try{g.saveOptions("options.dat");}
              catch(Exception l) {}
              System.exit(0);
            }
          }
          // cleaning up the text
          else if (source == bNew) {
            myText.setText("");
          }
          // opening the text from a file
          else if (source == bOpen) {
            String filename = "";
            JFileChooser choose = new JFileChooser();
            int result = choose.showOpenDialog(GUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
              try {
              filename = choose.getSelectedFile().toString();
              myText.setText(g.loadText(filename));
              }
              catch(IOException ioe) {
                JOptionPane.showMessageDialog(GUI.this,
                "Could not open text from " + filename + "\n"
                + ioe.getMessage(), "I/O Error",JOptionPane.ERROR_MESSAGE);
              }
            }
            else {
            }
          }
          // resetting the font size
          else if (source == myField) {
            try {
              String s = myField.getText();
              int i = Integer.parseInt(s);
              if (i < 1) {
                JOptionPane.showMessageDialog(GUI.this, "Invalid font size",
                "Error", JOptionPane.ERROR_MESSAGE);
              }
              else {
                Font oldFont = myText.getFont();
                int j = oldFont.getStyle();
                Font newFont = oldFont.deriveFont(j, i);
                myText.setFont(newFont);
              }
            }
            catch(Exception except) {
              JOptionPane.showMessageDialog(GUI.this, "Invalid font size",
              "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
          // saving the file
          else if (source == bSave) {
              String filename = "";
              JFileChooser choose = new JFileChooser();
              int k = choose.showSaveDialog(GUI.this);
              if (k == choose.APPROVE_OPTION) {
                try { filename = choose.getSelectedFile().toString();
                g.saveText(filename, myText.getText());
                }
                catch (IOException ioe) {
                JOptionPane.showMessageDialog(GUI.this, "Could not save text to " + filename + "\n"
                + ioe.getMessage(),"I/O Error", JOptionPane.ERROR_MESSAGE);
                }
             }
          }
        }
      }

    // variables for items on GUI
    private JButton bNew, bOpen, bSave, bQuit, bForeground, bBackground;
    private JTextArea myText;
    private JLabel myLabel;
    private JTextField myField;
    private GUIModel g = new GUIModel();

     /**
     *  Constructs GUI
     */
    public GUI() {
      // setting default operations
      setTitle("Microstuff JavaPad XP");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // initializing the buttons and stuff
      bNew = new JButton("New");
      bNew.addActionListener(new myListener());
      bOpen = new JButton("Open");
      bOpen.addActionListener(new myListener());
      bSave = new JButton("Save");
      bSave.addActionListener(new myListener());
      bQuit = new JButton("Quit");
      bQuit.addActionListener(new myListener());
      bForeground = new JButton("Foreground");
      bForeground.addActionListener(new myListener());
      bBackground = new JButton("Background");
      bBackground.addActionListener(new myListener());
      myText = new JTextArea(15, 40);
      myText.setLineWrap(true);
      myText.setWrapStyleWord(true);
      myLabel = new JLabel("Font Size:");
      myField = new JTextField(4);
      myField.addActionListener(new myListener());
      myText.setForeground((Color)g.getOption("foreground"));
      myText.setBackground((Color)g.getOption("background"));
      bForeground.setForeground((Color)g.getOption("foreground"));
      bBackground.setForeground((Color)g.getOption("background"));
      Integer integer = (Integer)g.getOption("font");
      int i = integer.intValue();
      Font font = myText.getFont();
      myText.setFont(font.deriveFont(font.getStyle(), i));
      myField.setText(integer.toString());

      // making sure i got the necessary containers
      Container top = getContentPane();
      Container north = new JPanel(new GridLayout(1, 4));
      Container south = new JPanel(new GridLayout(2, 1));
      Container subSouth1 = new JPanel(new FlowLayout());
      Container subSouth2 = new JPanel(new FlowLayout());

      // adding everything to it's specified container
      north.add(bNew);
      north.add(bOpen);
      north.add(bSave);
      north.add(bQuit);
      subSouth1.add(bForeground);
      subSouth1.add(bBackground);
      subSouth2.add(myLabel);
      subSouth2.add(myField);

      south.add(subSouth1);
      south.add(subSouth2);

      // adding to main winow
      top.add(north, BorderLayout.NORTH);
      top.add(new JScrollPane(myText), BorderLayout.CENTER);
      top.add(south, BorderLayout.SOUTH);
      pack();
    }
  }