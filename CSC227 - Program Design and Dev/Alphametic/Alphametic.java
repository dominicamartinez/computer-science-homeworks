// Dominic Martinez
// 3/26/03

import java.util.*;

/*
   Class to represent an Alphametic.
*/
public class Alphametic
{

	private String operand1, operand2, result;

    /*
       Initializes the three strings which are the original operands and result.
       None of the processing of the solution is done in this method, it's all
       done in the print solutions method on each call to that method (so a
       second call to printSolutions would be inefficient, but this is okay).
    */
    public Alphametic(String operand1, String operand2, String result)
    {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
    }

    /*
       Calls a recursive solve method, which prints the
       solutions when the method arrives at the valid base cases

       For instance, on the original, MIKEY + LIKES + NOTIT,
       we would see this output:

       MIKEY + LIKES = NOTIT
       ---------------------
       21705 + 61709 = 83414
       21709 + 61705 = 83414
       61705 + 21709 = 83414
       61709 + 21705 = 83414

       Note that order does not matter for this output.  Speed does not
       matter for this method either.
    */
    public void printSolutions()
    {
        // print the header text
        String temp = operand1 + " + " + operand2 + " = " + result;
        int i = temp.length();
        System.out.println(temp);
        for (int j = 0; j < i; j++) {
			System.out.print("-");
		}
		System.out.print("\n");

        // call the private recrusive method
        solve(operand1, operand2, result);
    }

    /*
       Solves an alphametric for the three strings passed in. This is a recursive
       method.  The base case is if all the letters of the words have been replaced
       by numbers.  The recursive case is to replace every instance of one letter
       with a number not already in any the strings.  Then, these strings get passed
       on to a recursive call.  When that method returns, try another number, until
       all the numbers are tried.  The method prints out a valid solution when it finds
       one.  A unique solution should only be printed out once.
    */
    private void solve(String operand1, String operand2, String result)
    {
        // RECURSIVE CASE
        // check if there are letters left to try out
		if (!isAllNumbers(operand1, operand2, result)) {
            // make a string to build up
            // get all the numbers used inside the strings we currently have
			String digits = digitsInside(operand1, operand2, result);
            // get the first available letter from the strings, this will be
            // the letter to replace on this recursive call
			char c = firstLetter(operand1, operand2, result);
            // for every number from 0 to 9
			for (int i = 0; i < 10; i++) {
				// if the number is not in the strings
				if (digits.indexOf((char)(i + 48)) == -1) {
                    // get new strings with the letter replaced with j
					String o1 = operand1.replace(c, (char)(i + 48));
					String o2 = operand2.replace(c, (char)(i + 48));
					String r = result.replace(c, (char)(i + 48));
				    // make the recursive call with the new, more integerized strings
				    solve(o1, o2, r);
				}
			}
		}

        // BASE CASE
        // check if the solution is correct
		if (isCorrectSolution(operand1, operand2, result)) {
            // print solution
            System.out.println(operand1 + " + " + operand2 + " = " + result);
		}
    }

    /*
       Returns true if the solution is correct, and false if it is not.
       "Correct" means that all the strings are integers, none of the digits begin
       with a 0, and the operands add up to the result.
    */
    private boolean isCorrectSolution(String operand1, String operand2, String result)
    {
		if (!isAllNumbers(operand1, operand2, result)) { return false; }

		if (operand1.charAt(0) == '0' || operand2.charAt(0) == '0' || result.charAt(0) == '0') {
			return false;
		}

		int i = Integer.parseInt(operand1);
		int j = Integer.parseInt(operand2);
		int k = Integer.parseInt(result);

		if ((i+j) == k) { return true; }
		return false;

    }

    /*
       Returns true if all three strings can be parsed into integers
       and false if they do not represent integers.
    */
    private boolean isAllNumbers(String s1, String s2, String s3)
    {
        // try to parse the ints, if that works, return true,
        // else catch the exception and return false
        try {
			int i = Integer.parseInt(s1);
			int j = Integer.parseInt(s2);
			int k = Integer.parseInt(s3);
		}
		catch (Exception e) { return false; }
		return true;
    }

    /*
       Returns a string that contains all of the digits inside the
       strings passed to the the method.  So if s1 = "M3K9", s2 =
       "READ", and s3 = "R3K2", the returned string would be
       "3932".
    */
    private String digitsInside(String s1, String s2, String s3)
    {
        // initialize a string
        String temp = "";

        // append all the digits from s1 into the string
		int i = s1.length();
		for (int j = 0; j < i; j++) {
			if ( s1.charAt(j) >= '0' && s1.charAt(j) <= '9') {
				temp += s1.charAt(j);
			}
		}

        // append all the digits from s2 into the string
		i = s2.length();
		for (int j = 0; j < i; j++) {
			if ( s2.charAt(j) >= '0' && s2.charAt(j) <= '9') {
				temp += s2.charAt(j);
			}
		}

        // apend all the digits from s3 into the string
		i = s3.length();
		for (int j = 0; j < i; j++) {
			if ( s3.charAt(j) >= '0' && s3.charAt(j) <= '9') {
				temp += s3.charAt(j);
			}
		}

        // return the string
        return temp;
    }

    /*
       Returns the first letter in the strings passed to the
       method.  It first checks to see if s1 has a letter in it
       (as opposed to digits or non-alphabet characters), and returns
       the first one it encounters.  If s1 does not have a letter,
       it does the same check for s2, and then s3 if necessary.
       If no character is found, the null character is returned.
    */
    private char firstLetter(String s1, String s2, String s3)
    {
       // check s1
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) >= 'A' && s1.charAt(i) <= 'Z') {
				return s1.charAt(i);
			}
		}

        // no letter in s1, check s2
        for (int i = 0; i < s2.length(); i++) {
			if (s2.charAt(i) >= 'A' && s2.charAt(i) <= 'Z') {
				return s2.charAt(i);
			}
		}

        // no letter in s2, check s3
 		for (int i = 0; i < s3.length(); i++) {
 			if (s3.charAt(i) >= 'A' && s3.charAt(i) <= 'Z') {
 				return s3.charAt(i);
			}
		}
        // no letters found in the string, return '\0'
        return '\0';
	}
}