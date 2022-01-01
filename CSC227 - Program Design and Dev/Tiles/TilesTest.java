// Stuart Reges
// 9/30/99
//
// This program tests the TileList class.  All of the test tiles
// have a null color because this program is just testing the manipulation
// of coordinates.

import java.awt.*;
import java.util.*;

public class TilesTest
{
    public static void main(String[] args)
    {
        // construct two arrays with test data
        Tile[] tiles = {new Tile(10, 10, 40, 40, null),
                        new Tile(60, 0, 100, 50, null),
                        new Tile(15, 5, 50, 50, null),
                        new Tile(5, 52, 100, 152, null),
                        new Tile(8, 8, 10, 10, null),
                        new Tile(20, 20, 20, 20, null),
                        new Tile(0, 0, 100, 200, null)};
        Point[] points = {new Point(51, 50),
                          new Point(55, 30),
                          new Point(11, 15),
                          new Point(30, 30),
                          new Point(20, 53),
                          new Point(15, 15),
                          new Point(50, 205)};

        // construct a TileList and insert tiles at the back and
        // request move to back, reporting the list each time
        TileList list = new TileList();
        Iterator iter;
        for (int i = 0; i < tiles.length; i++) {
            list.insertBack(tiles[i]);
            iter = list.getIterator();
            System.out.println("after insert back:");
            while (iter.hasNext())
                System.out.println(iter.next());
            Tile result = list.moveToBack(points[i].x, points[i].y);
            iter = list.getIterator();
            System.out.println("moveToBack returns " + result + ", yielding:");
            while (iter.hasNext())
                System.out.println(iter.next());
            System.out.println("hit enter to continue");
            System.out.println();
            try {
                while (System.in.read() != '\n');
            } catch (Exception e) {}

        }
    }
}