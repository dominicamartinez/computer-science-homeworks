package webbrowser;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;

public class WebBrowser extends JFrame
{
  public static void main(String[] args)
  {
    new WebBrowser().show();
  }
  
  
  private JEditorPane webpane;
  private JTextField field;
  
  public WebBrowser()
  {
    setTitle("Internet Exploiter");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // editor pane shows web pages
    HyperlinkListener wll = new WebLinkListener();
    webpane = new JEditorPane();
    webpane.setEditable(false);
    webpane.addHyperlinkListener(wll);
    
    // text field to type in web site addresses
    ActionListener waal = new WebAddressActionListener();
    field = new JTextField();
    field.addActionListener(waal);
    
    // layout
    Container north = new JPanel(new BorderLayout());
    north.add(new JLabel("Address: "), BorderLayout.WEST);
    north.add(field, BorderLayout.CENTER);
    
    Container center = new JScrollPane(webpane);  // make web page scroll
    
    Container cp = getContentPane();
    cp.add(north, BorderLayout.NORTH);
    cp.add(center, BorderLayout.CENTER);
    setSize(800, 600);
  }
  
  private void showPage(String pageName)
  {
    try
    {
      webpane.setPage(pageName);
    }
    catch (IOException ioe)
    {
      JOptionPane.showMessageDialog(this, 
        "Could not load page!\n\n" + ioe.getMessage(),
        "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  private class WebAddressActionListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      showPage(field.getText());
    }
  }
  
  private class WebLinkListener implements HyperlinkListener
  {
    public void hyperlinkUpdate(HyperlinkEvent event)
    {
      // if user clicks link (ACTIVATED), show page
      if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
      {
        String pageName = event.getURL().toString();
        showPage(pageName);
      }
    }
  }
}