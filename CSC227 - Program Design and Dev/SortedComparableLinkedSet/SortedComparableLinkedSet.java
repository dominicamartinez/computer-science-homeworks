// Dominic Martinez
// 3/11/03

// linked list that contains objects of type comparable
// in sorted order and multiple methods of adding and
// removing
public class SortedComparableLinkedSet implements ComparableSet {

// inner private linked list
	private class LinkNode {

		public Comparable data;
		public LinkNode next;

		public LinkNode() {
			this(null, null);
		}

		public LinkNode(Comparable data) {
			this(data, null);
		}

		public LinkNode(Comparable data, LinkNode next) {
			this.data = data;
			this.next = next;
		}
	}

// header variable
	private LinkNode front;

// constructs the list with the head being null
  	public SortedComparableLinkedSet() {
		front = null;
	}

  /* Adds the Comparable object to the set if it
     * does not already exist in the set.  Returns
     * true if the object is added to the set, or
     * false if the object cannot be added (e.g. if
     * it's already in the set).
    */
    public boolean add(Comparable c) {

		boolean flag = true;
		LinkNode cur = front;
		while (cur != null) {
			if (c.compareTo(cur.data) == 0) {
				flag = false;
				return flag;
			}
			else { cur = cur.next; }
		}

		if (front == null) {
			front = new LinkNode(c);
			return flag;
		}
		else if (front.data.compareTo(c) > 0) {
			front = new LinkNode(c, front);
			return flag;
		}
		else {
			cur = front;
			LinkNode prev = front;
			while (c.compareTo(cur.data) > 0 && cur.next != null) {
				prev = cur;
				cur = cur.next;
			}
			if (cur.data.compareTo(c) < 0 && cur.next == null) {
				cur.next = new LinkNode(c);
				return flag;
			}
			else {
				prev.next = new LinkNode(c, cur);
				return flag;
			}
		}
	}

    /* Returns true if the element is in the list
     * and false if not.
     */
    public boolean contains(Comparable c) {
		LinkNode cur = front;

		while (cur != null) {
			if (c.compareTo(cur.data) == 0) {
				return true;
			}
			else { cur = cur.next; }
		}

		return false;
	}

    /* Returns the Comparable object at the index
     * specified.  Throws IndexOutOfBoundsException
     * if the index is less than 0 or greater than
     * or equal to the size.
     */
    public Comparable get(int index) {
		LinkNode cur = front;
		int count = 0;
		while (cur.next != null) {   // counting elements
			cur = cur.next;
			++count;
		}

		if (index > count || index < 0) // is it over or under?
			throw new IndexOutOfBoundsException();
		else
			cur = front;
			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}
			return cur.data;
	}

    /* Removes the Comparable object from the set.
     * Returns true if the object was found and
     * removed, and false if the object was not
     * found in the set.
     */
   public boolean remove(Comparable c) {
		if (front != null) {
			if (front.data.compareTo(c) == 0) {
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
				while (cur.data.compareTo(c) != 0 && cur.next != null) {
					prev = cur;
					cur = cur.next;
				}
				if (cur.data.compareTo(c) != 0)
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

    /* Returns the number of elements in the list
     */
    public int size() {
		LinkNode cur = front;
		int count = 0;
		while (cur != null) {   // counting elements
			cur = cur.next;
			++count;
		}
		return count;
	}

    /* Adds every element in the object parameter
     * to the set.  For instance, if the paramenter
     * consisted of the elements [1, 2, 3], all
     * [1, 2, 3] will be added to the set.  We do
     * not want to add duplicate elements.
     */
    public void addAll(ComparableSet setToAdd) {
		int size = setToAdd.size();

		for (int i = 0; i < size; i++) {
			boolean doesntMatter = add(setToAdd.get(i));
		}
	}

    /* Removes all the objects in the list that are
     * greater than or equal to first AND less than
     * or equal to last.
     */
    public void removeRange(Comparable lowLimit, Comparable highLimit) {

		if (lowLimit.compareTo(front.data) <= 0 &&
 		   highLimit.compareTo(front.data) >= 0) {
			   while (highLimit.compareTo(front.data) >= 0) {
			   front = front.next;
			   }
	    }
		else {
			LinkNode cur = front;
			while (cur != null) {
				if (lowLimit.compareTo(cur.data) <= 0 &&
 		   		    highLimit.compareTo(cur.data) >= 0) {
					remove(cur.data);
				}
				cur = cur.next;
			}
		}
	}

    /* Returns a String representation of the list.
     * The format should look like the following:
     */
    public String toString() {
		int i = size();
		String listString = "size = " + i + " = [ ";
		LinkNode cur = front;
		while (cur != null) {
			listString += cur.data + " ";
			cur = cur.next;
		}
		listString += "]";

		return listString;
	}
}