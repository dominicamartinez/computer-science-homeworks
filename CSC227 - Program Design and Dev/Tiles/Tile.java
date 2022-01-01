// Stuart Reges
// 11/7/99
//
// Class Tile stores information about a colored tile:
// position, size and color.

import java.awt.*;

public class Tile
{
	public Tile(int x, int y, int width, int height, Color c)
	{
		myX = x;
		myY = y;
		myWidth = width;
		myHeight = height;
		if (myWidth < 0) {
			myWidth = -myWidth;
			myX = myX - myWidth;
		}
		if (myHeight < 0) {
			myHeight = -myHeight;
			myY = myY - myHeight;
		}
		myColor = c;
	}

	public int getX()
	{
		return myX;
	}

	public int getY()
	{
		return myY;
	}

	public int getWidth()
	{
		return myWidth;
	}

	public int getHeight()
	{
		return myHeight;
	}

	public void translate(int deltaX, int deltaY)
	{
		myX += deltaX;
		myY += deltaY;
	}

	public void draw(Graphics g)
	{
		g.setColor(myColor);
		g.fillRect(myX, myY, myWidth, myHeight);
		g.setColor(Color.black);
		g.drawRect(myX, myY, myWidth, myHeight);
	}

	public boolean inside(int x, int y)
	{
		if (x < myX || x > myX + myWidth)
			return false;
		else if (y < myY || y > myY + myHeight)
			return false;
		else
			return true;
	}

	public String toString()
	{
		return "Tile with upper-left (" + myX + ", " + myY + ") and size ("
		       + myWidth + ", " + myHeight + ")";
	}

	private int myX;
	private int myY;
	private int myWidth;
	private int myHeight;
	private Color myColor;
}