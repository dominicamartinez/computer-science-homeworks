// Dominic Martinez
// 9/3/02
//
// Class AddingMachine keeps track of the state of an adding
// machine.  It accumulates digits to form entire numbers and
// either adds or subtracts those numbers to a cumulative sum.
// It also has a reset option.

public class AddingMachine
{
    public void clear()
    // post: current number, current sum, display all cleared
    {
    myCurrent = 0;
    mySum = 0;
    myDisplay = 0;
    }

    public void addDigit(int digit)
    // post: digit is added to the end of the current number
    {
    myCurrent = myCurrent * 10 + digit;
    myDisplay = myCurrent;
    }

    public void plus()
    // post: current number is added to sum and cleared, current
    //       sum is displayed
    {
    mySum = mySum + myCurrent;
    myCurrent = 0;
    myDisplay = mySum;
    }

    public void minus()
    // post: current number is subtracted from sum and cleared,
    //       current sum is displayed
    {
    mySum = mySum - myCurrent;
    myCurrent = 0;
    myDisplay = mySum;
    }

    public int getDisplay()
    // post: returns current display
    {
    return myDisplay;
    }

    private int myCurrent;  // current number
    private int mySum;      // current sum
    private int myDisplay;  // current display
}
