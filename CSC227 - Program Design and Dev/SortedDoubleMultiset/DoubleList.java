public interface DoubleList {
    /** Returns the number of elements in the list.
      */
    public int size();

    /** Adds doubleToAdd to the list.
      */
    public void add(double doubleToAdd);

    /** Returns the double at index.  This method should return
      * Double.NaN if the index is out of bounds.
      */
    public double get(int index);

    /** Removes a single doubleToRemove from the list.  If 
      * the double was found and removed, true is 
      * returned.  If the double was not found in the list, 
      * false is returned.  
      */
    public boolean removeSingle(double doubleToRemove);

    /** Removes all of doubleToRemove from the list.  If 
      * the double(s) was/were found and removed, true is 
      * returned.  If the double was not found in the list, 
      * false is returned.  
      */
    public boolean removeAll(double doubleToRemove);

    /** Removes all the elements from index first (inclusive)
      * to index last (exclusive) from the list.  If the range
      * is a valid range, the elements should be removed and
      * true should be returned.  If the range is invalid, then
      * false should be returned.  The range is considered
      * invalid if either index is out of bounds or if first
      * is greater than or equal to last.
      */
    public boolean removeRange(int first, int last);

    /** Copies the values from this list at index first inclusive
      * to index last exclusive into a new DoubleList which is 
      * returned.  If the range is invalid, then
      * null should be returned.  The range is considered
      * invalid if either index is out of bounds or if first
      * is greater than last.
      */
    public DoubleList sublist(int first, int last);

    /** Returns a string representation of the list.
      */
    public String toString();
}