package factory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * A factory for creating AWT and Swing menus, menu items, and menu bars.
 *
 *  @author Martin Stepp (stepp@cs.arizona.edu)
 *  @version February 28, 2003
 */
public class MenuFactory
{
  // factory; can't be constructed
  private MenuFactory() {}
  
  /** Pass this constant to createJMenu in the array of items to get a separator. */
  public static final JMenuItem SEPARATOR = null;
  
  /** Creates a new JMenu with the given text, with the text's first
   *  character as its mnemonic.
   * 
   *  @param text The text to display on the menu.
   *  @return The new empty menu.
   */
  public static JMenu createJMenu(String text)
  {
    return createJMenu(text, text.charAt(0));
  }

  /** Creates a new JMenu with the given text and mnemonic.
   * 
   *  @param text The text to display on the menu.
   *  @param mnemonic The mnemonic (underlined shortcut letter) for the menu.
   *  @return The new empty menu.
   */
  public static JMenu createJMenu(String text, int mnemonic)
  {
    JMenu jm = new JMenu(text);
    jm.setMnemonic(mnemonic);
    return jm;
  }
  
  /** Creates a new JMenuItem with the given text, with the text's first
   *  character as its mnemonic, attaches the given listener to it, and
   *  adds it to the given menu.
   *  
   *  @param text The text to display on the menu item.
   *  @param al The listener to be notified when the user selects the menu item.
   *  @param menu The menu to which to add the newly created menu item.
   *  @return The new menu item.
   */
  public static JMenuItem createJMenuItem(String text, ActionListener al, JMenu menu)
  {
    return createJMenuItem(text, text.charAt(0), al, menu);
  }
  
  /** Creates a new JMenuItem with the given text and mnemonic,
   *  attaches the given listener to it, and adds it to the given menu.
   *  
   *  @param text The text to display on the menu item.
   *  @param mnemonic The mnemonic (underlined shortcut letter) for the menu item.
   *  @param al The listener to be notified when the user selects the menu item.
   *  @param menu The menu to which to add the newly created menu item.
   *  @return The new menu item.
   */
  public static JMenuItem createJMenuItem(String text, int mnemonic, 
                          ActionListener al, JMenu menu)
  {
    JMenuItem jmi = new JMenuItem(text, mnemonic);
    if (al != null)
      jmi.addActionListener(al);
    if (menu != null)
      menu.add(jmi);
    return jmi;
  }

  /** Creates a new JMenuItem with the given text and mnemonic,
   *  and an icon located in the file whose name is given by iconFile,
   *  attaches the given listener to it, and adds it to the given menu.
   *  
   *  @param text The text to display on the menu item.
   *  @param mnemonic The mnemonic (underlined shortcut letter) for the menu item.
   *  @param iconFile The name of the file to be used as the menu item's icon.
   *  @param al The listener to be notified when the user selects the menu item.
   *  @param menu The menu to which to add the newly created menu item.
   *  @return The new menu item.
   */
  public static JMenuItem createJMenuItem(String text, int mnemonic, String iconFile, 
                          ActionListener al, JMenu menu)
  {
    JMenuItem jmi = new JMenuItem(text, mnemonic);
    ImageIcon icon = new ImageIcon(iconFile);
    jmi.setIcon(icon);
    if (al != null)
      jmi.addActionListener(al);
    if (menu != null)
      menu.add(jmi);
    return jmi;
  }

  /** Creates a new JMenuItem with the given text and mnemonic,
   *  and an icon located in the file whose name is given by iconFile,
   *  attaches the given listener to it, and adds it to the given menu.
   *  
   *  @param text The text to display on the menu item.
   *  @param mnemonic The mnemonic (underlined shortcut letter) for the menu item.
   *  @param icon The menu item's icon.
   *  @param al The listener to be notified when the user selects the menu item.
   *  @param menu The menu to which to add the newly created menu item.
   *  @return The new menu item.
   */
  public static JMenuItem createJMenuItem(String text, int mnemonic, Icon icon,
                          ActionListener al, JMenu menu)
  {
    JMenuItem jmi = new JMenuItem(text, mnemonic);
    jmi.setIcon(icon);
    if (al != null)
      jmi.addActionListener(al);
    if (menu != null)
      menu.add(jmi);
    return jmi;
  }

  /** Creates a new AWT Menu with the given text and mnemonic; don't use this 
   *  method in a Swing program.
   * 
   *  @param text The text to display on the menu.
   *  @param mnemonic The mnemonic (underlined shortcut letter) for the menu.
   *  @return The new empty menu.
   */
  public static Menu createMenu(String text, int mnemonic)
  {
    Menu m = new Menu(text);
    m.setShortcut(new MenuShortcut(mnemonic));
    return m;
  }

  /** Creates a new AWT MenuItem with the given text, with the text's first
   *  character as its mnemonic, attaches the given listener to it, and
   *  adds it to the given menu; don't use this method in a Swing program.
   *  
   *  @param text The text to display on the menu item.
   *  @param al The listener to be notified when the user selects the menu item.
   *  @param menu The menu to which to add the newly created menu item.
   *  @return The new menu item.
   */
  public static MenuItem createMenuItem(String text,
                          ActionListener al, Menu menu)
  {
    return createMenuItem(text, text.charAt(0), al, menu);
  }

  /** Creates a new AWT MenuItem with the given text and mnemonic,
   *  attaches the given listener to it, and adds it to the given menu;
   *  don't use this method in a Swing program.
   *  
   *  @param text The text to display on the menu item.
   *  @param mnemonic The mnemonic (underlined shortcut letter) for the menu item.
   *  @param al The listener to be notified when the user selects the menu item.
   *  @param menu The menu to which to add the newly created menu item.
   *  @return The new menu item.
   */
  public static MenuItem createMenuItem(String text, int mnemonic, 
                          ActionListener al, Menu menu)
  {
    MenuItem mi = new MenuItem(text, new MenuShortcut(mnemonic));
    mi.addActionListener(al);
    menu.add(mi);
    return mi;
  }
  
  /** Creates an AWT PopupMenu with the given title text, which pops up when
   *  the given component is right-clicked; don't use this method in a Swing
   *  program.
   * 
   *  @param text The text to display on the popup menu.
   *  @param comp The component to which to attach the popup menu.
   *  @return The completed menu.
   */
  public static PopupMenu createPopupMenu(String text, Component comp)
  {
    PopupMenu popup = new PopupMenu(text);
    comp.addMouseListener(new PopupMenuMouseAdapter(popup, comp));
    comp.add(popup);
    return popup;
  }
  
  // Private special mouse adapter for the popup menu thing above.
  // I had to write this because AWT sucks.
  // Not public == Marty no Javadocky
  private static class PopupMenuMouseAdapter extends MouseAdapter
  {
    private PopupMenu popup;
    private Component comp;
    
    public PopupMenuMouseAdapter(PopupMenu popup, Component comp)
    {
      this.popup = popup;
      this.comp  = comp;
    }
    
    // Makes the menu pop up when not-left-button is pressed
    public void mousePressed(MouseEvent event)
    {
      if ((event.getModifiers() & MouseEvent.BUTTON1_MASK) == 0)
        popup.show(comp, event.getX(), event.getY());
    }
  }
}





