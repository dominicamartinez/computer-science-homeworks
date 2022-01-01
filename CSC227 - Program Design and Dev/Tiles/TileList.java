// Dominic Martinez
// Grader: Ehtesham Haque
// 10/25/02

import java.util.*;

public class TileList {

	private LinkNode front = null;

	// constructs an empty tile list
	public TileList() {
		LinkNode front = new LinkNode();
	}

	// this is what goes through and moves the linknode
	// to the back if the coordinates are inside
	// the tile
	public Tile moveToBack(int x, int y) {
		LinkNode temp = null;
		LinkNode tempprev = null;
		LinkNode cur = front;
		LinkNode prev = front;
		while (cur.next != null) {
			if (cur.tile.inside(x, y)) {
				temp = cur;
				tempprev = prev;
			}
			prev = cur;
			cur = cur.next;
		}

		if (temp != null) {
			cur.next = new LinkNode(temp.tile);
			if (tempprev == temp) {
				front = tempprev.next;
				return cur.next.tile;
			}
			else {
				tempprev.next = temp.next;
				return temp.tile;
			}
		}
		else return null;
	}

	// when a new tile is made it gets put
	// in the back of the list, this does that
	public void insertBack(Tile t) {
		if (front == null)
			front = new LinkNode(t);
		else {
			LinkNode cur = front;
			while (cur.next != null)
				cur = cur.next;
			cur.next = new LinkNode(t);
        }
	}

	// returns an iterator for this list
	public Iterator getIterator() {
		return new TileListIterator();
	}

	////////////////////////////////////////////
	// the private classes
	///////////////////////////////////////////
	private class LinkNode {

		public Tile tile;
		public LinkNode next;

		public LinkNode() {
			this(null, null);
		}

		public LinkNode(Tile tile) {
			this(tile, null);
		}

		public LinkNode(Tile tile, LinkNode next) {
			this.tile = tile;
			this.next = next;
		}
	}

	private class TileListIterator implements Iterator {
		public LinkNode dummy;
		private LinkNode cur;

		public TileListIterator() {
			dummy = new LinkNode(null, front);
			cur = dummy;
		}

		public boolean hasNext() {
			if (cur.next != null)
				return true;
			else
				return false;
		}

		public Object next() {
			if (cur.next == null)
				return front;
			else {
				cur = cur.next;
				return cur.tile;
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
