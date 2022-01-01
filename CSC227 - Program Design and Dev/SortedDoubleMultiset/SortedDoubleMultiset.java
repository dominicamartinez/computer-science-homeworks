// Dominic Martinez
// 10/22/02

public class SortedDoubleMultiset implements DoubleList {

	private class LinkNode {

		public double data;
		public LinkNode next;

		public LinkNode() {
			this(0.0, null);
		}

		public LinkNode(double data) {
			this(data, null);
		}

		public LinkNode(double data, LinkNode next) {
			this.data = data;
			this.next = next;
		}
	}

	private LinkNode front;

	public SortedDoubleMultiset() {
		front = null;
	}

	// Returns the number of elements in the list.
	public int size() {
		LinkNode cur = front;
		int size = 0;
		while (cur != null) {
			cur = cur.next;
			size++;
		}
		return size;
	}

    // Adds a double to the list and in sorted order.
    public void add(double doubleToAdd) {
		if (front == null)
			front = new LinkNode(doubleToAdd);
		else if (front.data > doubleToAdd)
			front = new LinkNode(doubleToAdd, front);
		else
		{
			LinkNode cur = front;
			LinkNode prev = front;
			while (doubleToAdd > cur.data && cur.next != null) {
				prev = cur;
				cur = cur.next;
			}
			if (cur.data < doubleToAdd && cur.next == null)
				cur.next = new LinkNode(doubleToAdd);
			else
				prev.next = new LinkNode(doubleToAdd, cur);
		}
	}

    // this will first go through the linked list
    // and count the number of elements then check
    // to see if the requesting index number is over
    // or below any elemental number. assuming the
    // requesting index is within reason
    // it will return that element at the desired index.
    public double get(int index) {
		LinkNode cur = front;
		int count = 0;
		while (cur.next != null) {   // counting elements
			cur = cur.next;
			++count;
		}

		if (index > count || index < 0) // is it over or under?
			return Double.NaN;
		else
			cur = front;
			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}
			return cur.data;
	}

   // this will go through and find the first occurence of the desired
   // double to be removed and remove it, returning true if it removes it
   // and false if it can't find it.
   public boolean removeSingle(double doubleToRemove) {
		if (front != null) {
			if (front.data == doubleToRemove) {
				if (front.next == null) {
					front = null;
					return true;
				}
				else {
					front = front.next;
					return true;
				}
			}
			else {
				LinkNode cur = front;
				LinkNode prev = front;
				while (cur.data != doubleToRemove && cur.next != null) {
					prev = cur;
					cur = cur.next;
				}
				if (cur.data != doubleToRemove)
					return false;
				else if (cur.next == null) {
					prev.next = null;
					return true;
				}
				else {
					prev.next = cur.next;
					return true;
				}
			}
		}
		else
			return false;
	}

    // this will first go through and count the amount of
    // occurences of doubleToRemove and then use that as
    // a counter to rid the links. since any multiple doubleToRemove
    // occurences will be in order, it will start at the first and
    // erase according to how many occurences it found the first time around
    public boolean removeAll(double doubleToRemove) {
		LinkNode cur = front;
		int found = 0;

		while (true) {
			if (cur.data == doubleToRemove)
				found++;
			if (cur.next == null)
				break;
			cur = cur.next;
		}

		if (found == 0)
			return false;
		else {
			cur = front;  // reseting pointer
			LinkNode prev = front;

			for (int i = 0; i < found; i++) {
				while (cur.data != doubleToRemove) {
					prev = cur;
					cur = cur.next;
				}
				if (front.data == doubleToRemove && front.next != null) {
					front = front.next;
					cur = front;
					prev = front;
				}
				else if (cur.next == null) {
					prev.next = null;
				}
				else {
					prev.next = cur.next;
					cur = prev.next;
				}
			}
		return true;
		}
	}

	// this will go through and count up the number of elements
	// then compare to see if the desired range is within reason.
	// if the range is, it will then continue to remove that range.
    public boolean removeRange(int first, int last) {
		LinkNode cur = front;
		int count = 0;
		while (cur.next != null) {
			cur = cur.next;
			++count;
		}
		if (first > last || first > count || first < 0 || last > count || first == last)
			return false;
		else {
			cur = front;
			for (int i = 0; i < first; i++) {
				cur = cur.next;
			}
			last = last - first; // this will get number of doubles between the range
			for (int i = 0; i < last; i++) {
				removeSingle(cur.data);
				cur = cur.next;
			}
		return true;
		}
	}

	// this will first go through the linked list
	// and count the number of elements then check
	// to see if the requesting index numbers is over
    // or below any elemental number. if everything
    // seems to be in order then it will go ahead and
    // go to the first element of the sublist and then
    // start adding all the way up to the last element
    // desired
    public DoubleList sublist(int first, int last) {
		DoubleList sub = new SortedDoubleMultiset();
		LinkNode cur = front;
		int count = 0;
		while (cur.next != null) {
			cur = cur.next;
			++count;
		}

		if (first > last || first > count || first < 0 || last > count)
			return null;
		else if (first == last)
			return sub;
		else {
			cur = front;
			for (int i = 0; i < first; i++) {
				cur = cur.next;
			}
			last = last - first;
			for (int i = 0; i < last; i++) {
				sub.add(cur.data);
				cur = cur.next;
			}
			return sub;
		}
	}

    // This will take the numbers in the linked list
    // and add them to a string for viewing purposes
    public String toString() {
		String listString = "[ ";
		LinkNode cur = front;
		while (cur != null) {
			listString += cur.data + " ";
			cur = cur.next;
		}
		listString += "]";

		return listString;
	}
}