package factory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** A factory for easy loading and initialization of AWT and Swing buttons.
 *
 *  @author Martin Stepp (stepp@cs.arizona.edu)
 *  @version February 28, 2003
 */
public class ButtonFactory
{
  // factory; can't be constructed
  private ButtonFactory() {}

  /** Constructs and returns a new JButton with the given text, using the text's
   *  first letter as the mnemonic (underlined shortcut letter),
   *  attaching the given listener, and putting it in the given container.
   *
   *  @param text The button's text.
   *  @param al The action listener to attach to the button.
   *  @param panel The container to put the button in.
   *  @return The finished button.
   */
  public static JButton createJButton(String text, ActionListener al, Container panel)
  {
    return createJButton(text, text.charAt(0), al, panel);
  }

  /** Constructs and returns a new JButton with the given text and mnemonic,
   *  attaching the given listener and putting it in the given container.
   *
   *  @param text The button's text.
   *  @param mnemonic The underlined shortcut letter on the button (pass a char!).
   *  @param al The action listener to attach to the button.
   *  @param panel The container to put the button in.
   *  @return The finished button.
   */
  public static JButton createJButton(String text, int mnemonic, ActionListener al,
                                      Container panel)
  {
    JButton jb = new JButton(text);
    jb.setMnemonic(mnemonic);
    jb.addActionListener(al);
    panel.add(jb);
    return jb;
  }

  /** Constructs and returns a new JButton with the given text and icon,
   *  using the text's first letter as the mnemonic (underlined shortcut letter),
   *  attaching the given listener and putting it in the given container.
   *
   *  @param text The button's text.
   *  @param iconFile The filename from which to load this button's icon.
   *  @param al The action listener to attach to the button.
   *  @param panel The container into which to put the button.
   *  @return The finished button.
   */
  public static JButton createJButton(String text, String iconFile,
                                      ActionListener al, Container panel)
  {
    return createJButton(text, text.charAt(0), iconFile, al, panel);
  }
  
  /** Constructs and returns a new JButton with the given text, icon and mnemonic,
   *  attaching the given listener and putting it in the given container.
   *
   *  @param Text the button's text.
   *  @param mnemonic The underlined shortcut letter on the button (pass a char!).
   *  @param iconFile The filename from which to load this button's icon.
   *  @param al The action listener to attach to the button.
   *  @param panel The container into which to put the button.
   *  @return The finished button.
   */
  public static JButton createJButton(String text, int mnemonic, String iconFile,
                                      ActionListener al, Container panel)
  {
    ImageIcon icon = new ImageIcon(iconFile);
    JButton jb = createJButton(text, mnemonic, al, panel);
    jb.setIcon(icon);
    return jb;
  }

  /** Constructs and returns a new JCheckBox with the given text and icon,
   *  checked if so desired, attaching the given listener and putting it in the 
   *  given container.
   *
   *  @param text The check box's text.
   *  @param selected Whether or not the check box should be initially checked.
   *  @param al The action listener to attach to the check box.
   *  @param panel The container into which to put the check box.
   *  @return The finished check box.
   */
  public static JCheckBox createJCheckBox(String text, boolean selected, 
                                          ActionListener al, Container panel)
  {
    return createJCheckBox(text, text.charAt(0), selected, al, panel);
  }

  /** Constructs and returns a new JCheckBox with the given text and icon,
   *  checked if so desired, attaching the given listener and putting it in the 
   *  given container.
   *
   *  @param text The check box's text.
   *  @param selected Whether or not the check box should be initially checked.
   *  @param al The action listener to attach to the check box.
   *  @param panel The container into which to put the check box.
   *  @return The finished check box.
   */
  public static JCheckBox createJCheckBox(String text, int mnemonic, 
      boolean selected, ActionListener al, Container panel)
  {
    JCheckBox jb = new JCheckBox(text, selected);
    jb.setMnemonic(mnemonic);
    jb.addActionListener(al);
    panel.add(jb);
    return jb;
  }

  /** Constructs and returns a new JCheckBox with the given text and icon,
   *  checked if so desired, attaching the given listener and putting it in the 
   *  given container.
   *
   *  @param text The check box's text.
   *  @param iconFile The filename from which to load this check box's icon.
   *  @param selected Whether or not the check box should be initially checked.
   *  @param al The action listener to attach to the check box.
   *  @param panel The container into which to put the check box.
   *  @return The finished check box.
   */
  public static JCheckBox createJCheckBox(String text, String iconFile,
    boolean selected, ActionListener al, Container panel)
  {
    return createJCheckBox(text, text.charAt(0), iconFile, selected, al, panel);
  }

  /** Constructs and returns a new JCheckBox with the given text, icon and mnemonic,
   *  checked if so desired, attaching the given listener and putting it in the 
   *  given container.
   *
   *  @param text The check box's text.
   *  @param mnemonic The underlined shortcut letter on the button (pass a char!).
   *  @param iconFile The filename from which to load this check box's icon.
   *  @param selected Whether or not the check box should be initially checked.
   *  @param al The action listener to attach to the check box.
   *  @param panel The container into which to put the check box.
   *  @return The finished check box.
   */
  public static JCheckBox createJCheckBox(String text, int mnemonic, String iconFile,
    boolean selected, ActionListener al, Container panel)
  {
    ImageIcon icon = new ImageIcon(iconFile);
    JCheckBox jb = new JCheckBox(text, icon, selected);
    jb.setMnemonic(mnemonic);
    jb.addActionListener(al);
    panel.add(jb);
    return jb;
  }

  /** Constructs and returns a new JRadioButton with the given text,
   *  using the text's first letter as its mnemonic, attaching the given listener
   *  and putting it in the given container and button group.
   *
   *  @param text The button's text.
   *  @param al The action listener to attach to the button.
   *  @param panel The container into which to put the button.
   *  @param bg The button group into which to put the button.
   *  @return The finished radio button.
   */
  public static JRadioButton createJRadioButton(String text, 
      ActionListener al, Container panel, ButtonGroup bg)
  {
    return createJRadioButton(text, text.charAt(0), al, panel, bg);
  }
  
  /** Constructs and returns a new JRadioButton with the given text and mnemonic,
   *  attaching the given listener and putting it in the given container and button group.
   *
   *  @param text The button's text.
   *  @param mnemonic The underlined shortcut letter on the button (pass a char!).
   *  @param al The action listener to attach to the button.
   *  @param panel The container into which to put the button.
   *  @param bg The button group into which to put the button.
   *  @return The finished radio button.
   */
  public static JRadioButton createJRadioButton(String text, int mnemonic,
      ActionListener al, Container panel, ButtonGroup bg)
  {
    JRadioButton jb = new JRadioButton(text);
    jb.setMnemonic(mnemonic);
    jb.addActionListener(al);
    panel.add(jb);
    bg.add(jb);
    return jb;
  }

  /** Constructs and returns a new JRadioButton with the given text and icon file,
   *  using the text's first letter as the mnemonic, attaching the given listener 
   *  and putting it in the given container and button group.
   *
   *  @param text The button's text.
   *  @param iconFile The filename from which to load this button's icon.
   *  @param al The action listener to attach to the button.
   *  @param panel The container to put the button in.
   *  @param bg The button group into which to put the button.
   *  @return The finished radio button.
   */
  public static JRadioButton createJRadioButton(String text,
      String iconFile, ActionListener al, Container panel, ButtonGroup bg)
  {
    return createJRadioButton(text, text.charAt(0), iconFile, al, panel, bg);
  }

  /** Constructs and returns a new JRadioButton with the given text, icon and mnemonic,
   *  attaching the given listener and putting it in the given container and button group.
   *
   *  @param text The button's text.
   *  @param mnemonic The underlined shortcut letter on the button (pass a char!).
   *  @param iconFile The filename from which to load this button's icon.
   *  @param al The action listener to attach to the button.
   *  @param panel The container to put the button in.
   *  @param bg The button group into which to put the button.
   *  @return The finished radio button.
   */
  public static JRadioButton createJRadioButton(String text, int mnemonic, 
      String iconFile, ActionListener al, Container panel, ButtonGroup bg)
  {
    ImageIcon icon = new ImageIcon(iconFile);
    JRadioButton jb = new JRadioButton(text, icon);
    jb.setMnemonic(mnemonic);
    jb.addActionListener(al);
    panel.add(jb);
    bg.add(jb);
    return jb;
  }

  /** Constructs and returns a new AWT Button with the given text,
   *  attaching the given listener and putting it in the given container;
   *  do not use this method in a Swing program.
   *
   *  @param text The button's text.
   *  @param al the action listener to attach to the button.
   *  @param panel the container to put the button in.
   *  @return The finished button.
   */
  public static Button createButton(String text, ActionListener al, Container panel) {
    Button jb = new Button(text);
    jb.addActionListener(al);
    panel.add(jb);
    return jb;
  }

  /** Constructs and returns a new AWT Checkbox (radio button) with the given text,
   *  attaching the given listener and putting it in the given container and checkbox 
   *  group; do not use this method in a Swing program.
   *
   *  <p>
   *  This method converts al into an ItemListener for compatibility.
   *
   *  @param text the button's text.
   *  @param al the action listener to attach to the button.
   *  @param panel the container to put the button in.
   *  @param bg the checkbox group into which to put the button.
   *  @return The finished check box.
   */
  public static Checkbox createCheckbox(String text, ActionListener al, Container panel, CheckboxGroup bg) {
    Checkbox jb = new Checkbox(text, false, bg);
    jb.addItemListener(new ActionItemListener(al, text));
    panel.add(jb);
    return jb;
  }

  /** Constructs and returns a new AWT Checkbox (radio button) with the given text,
   *  attaching the given listener and putting it in the given container and checkbox 
   *  group; do not use this method in a Swing program.
   *
   *  @param text the button's text.
   *  @param il the item listener to attach to the button.
   *  @param panel the container to put the button in.
   *  @param bg the checkbox group into which to put the button.
   *  @return The finished check box.
   */
  public static Checkbox createCheckbox(String text, ItemListener il, Container panel, CheckboxGroup bg) {
    Checkbox jb = new Checkbox(text, false, bg);
    jb.addItemListener(il);
    panel.add(jb);
    return jb;
  }

  // converts ItemEvents to ActionEvents (ItemListener is lame AWT crap!)
  private static class ActionItemListener implements ItemListener {
    private ActionListener al;
    private String command;

    public ActionItemListener(ActionListener al, String command) {
      this.al = al;
      this.command = command;
    }

    public void itemStateChanged(ItemEvent event) {
      ActionEvent ae = new ActionEvent(event.getSource(), ActionEvent.ACTION_PERFORMED, command);
      al.actionPerformed(ae);
    }
  }
}





