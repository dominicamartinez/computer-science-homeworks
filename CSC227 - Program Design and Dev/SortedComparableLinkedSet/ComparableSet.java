/* Class to define methods that a set storing 
 * Comparable objects should have.
 */

public interface ComparableSet {
    /* Adds the Comparable object to the set if it
     * does not already exist in the set.  Returns
     * true if the object is added to the set, or
     * false if the object cannot be added (e.g. if
     * it's already in the set).
     */
    public boolean add(Comparable c);
    
    /* Removes the Comparable object from the set.
     * Returns true if the object was found and
     * removed, and false if the object was not
     * found in the set.
     */
    public boolean remove(Comparable c);
    
    /* Returns the number of elements in the list
     */
    public int size();
    
    /* Returns true if the element is in the list
     * and false if not.
     */
    public boolean contains(Comparable c); 
    
    /* Returns the Comparable object at the index
     * specified.  Throws IndexOutOfBoundsException
     * if the index is less than 0 or greater than
     * or equal to the size.
     */
    public Comparable get(int index);
    
    /* Adds every element in the object parameter
     * to the set.  For instance, if the paramenter
     * consisted of the elements [1, 2, 3], all 
     * [1, 2, 3] will be added to the set.  We do 
     * not want to add duplicate elements.
     */
    public void addAll(ComparableSet setToAdd);
    
    /* Removes all the objects in the list that are
     * greater than or equal to first AND less than
     * or equal to last. 
     */
    public void removeRange(Comparable lowLimit, 
                            Comparable highLimit);
                            
    /* Returns a String representation of the list.
     * The format should look like the following:
     *
     * size = 5 set = [item1, item2, item3, ..., itemn]
     */
    public String toString();
}