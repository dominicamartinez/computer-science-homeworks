import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TilesMain
{
    public static void main(String[] args)
    {
        TilesFrame frame = new TilesFrame();
        frame.show();
    }
}

class TilesFrame extends CloseableFrame
{
    public TilesFrame()
    {
        setSize(400, 400);
        setTitle("CS227 fun with Tiles");
        myPanel = new TilePanel(Color.red);
        Container contentPane = getContentPane();

        contentPane.add(myPanel, "Center");
        addColorButtons(contentPane);
    }

    private void addColorButtons(Container contentPane)
    {
        CheckboxGroup g = new CheckboxGroup();
        final Checkbox greenButton = new Checkbox("Green", g, false);
        final Checkbox blueButton = new Checkbox("Blue", g, false);
        final Checkbox redButton = new Checkbox("Red", g, true);

        JPanel p = new JPanel();
        p.setBackground(Color.cyan);
        p.add(greenButton);
        p.add(blueButton);
        p.add(redButton);
        contentPane.add(p, "North");

        ItemListener colorListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.DESELECTED)
                    return;
                Object source = e.getSource();
                if (source == greenButton)
                    myPanel.setColor(Color.green);
                else if (source == blueButton)
                    myPanel.setColor(Color.blue);
                else if (source == redButton)
                    myPanel.setColor(Color.red);
                else
                    System.out.println("error in color listener");
            }
        };
        greenButton.addItemListener(colorListener);
        blueButton.addItemListener(colorListener);
        redButton.addItemListener(colorListener);
    }

    private TilePanel myPanel;
}

class TilePanel extends JPanel
{
    public TilePanel(Color c)
    {
        myTiles = new TileList();
        myColor = c;
        TileListener listener = new TileListener(myTiles, this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public Color getColor()
    {
        return myColor;
    }

    public void setColor(Color c)
    {
        myColor = c;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Iterator iterator = myTiles.getIterator();
        while (iterator.hasNext())
            ((Tile)iterator.next()).draw(g);
    }

    private TileList myTiles;
    private Color myColor;
}

class TileListener extends MouseAdapter implements MouseMotionListener
{
    public TileListener(TileList tiles, TilePanel parent)
    {
        myTiles = tiles;
        myParent = parent;
    }

    public void mouseClicked(MouseEvent e)
    {
        myParent.repaint();
    }

    public void mousePressed(MouseEvent e)
    {
        myFirstSpot = myLastSpot = e.getPoint();
        myCurrent = myTiles.moveToBack(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e)
    {
        int deltaX = myLastSpot.x - myFirstSpot.x;
        int deltaY = myLastSpot.y - myFirstSpot.y;
        Point newSpot = e.getPoint();
        if (myCurrent == null) {
            if (deltaX != 0 && deltaY != 0)
                myTiles.insertBack(new Tile(myFirstSpot.x, myFirstSpot.y, deltaX, deltaY, myParent.getColor()));
        } else
            myCurrent.translate(newSpot.x - myFirstSpot.x, newSpot.y - myFirstSpot.y);
        myParent.repaint();
    }

    private void drawBorder(Graphics g)
    {
        int deltaX = myLastSpot.x - myFirstSpot.x;
        int deltaY = myLastSpot.y - myFirstSpot.y;
        if (myCurrent != null)
            g.drawRect(myCurrent.getX() + deltaX, myCurrent.getY() + deltaY, myCurrent.getWidth(), myCurrent.getHeight());
        else {
            int cornerX, cornerY;
            if (deltaX < 0)
                cornerX = myFirstSpot.x + deltaX;
            else
                cornerX = myFirstSpot.x;
            if (deltaY < 0)
                cornerY = myFirstSpot.y + deltaY;
            else
                cornerY = myFirstSpot.y;
            g.drawRect(cornerX, cornerY, Math.abs(deltaX), Math.abs(deltaY));
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        Graphics g = myParent.getGraphics();
        g.setXORMode(myParent.getBackground());
        drawBorder(g);
        myLastSpot = e.getPoint();
        drawBorder(g);
        g.dispose();
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    private TileList myTiles;
    private TilePanel myParent;
    private Point myFirstSpot;
    private Point myLastSpot;
    private Tile myCurrent;
}

class CloseableFrame extends JFrame
{
    public CloseableFrame()
    {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
}
