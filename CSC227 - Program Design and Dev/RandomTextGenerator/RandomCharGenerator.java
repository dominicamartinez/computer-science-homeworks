/* Dominic Martinez
 * 3/6/03
*/

import java.util.*;

public class RandomCharGenerator {

	private char myChar;
	private String source, mySub;
	private int seedLength;
	Random rnd;


/*
 Takes a String that is the source text to generate characters
 off of.  It also takes the seed length of the seed to use to
 generate characters from.
*/
	public RandomCharGenerator(String source, int seedLength) {
		this.source = source;
		this.seedLength = seedLength;


		// this initializes the first substring
		rnd = new Random();
		int i = rnd.nextInt(source.length()-seedLength);
		mySub = source.substring(i, i+seedLength);
	}

/*
  Gets the next random character from the text.  Should be based
  on the last seedLength characters returned.
*/
	public char getNextChar() {

		// if level is 0
		if (seedLength == 0) {
			int i = rnd.nextInt(source.length());
			return source.charAt(i);
		}

		// will keep breaking down the source string into size of
		// of seedLength, if one is found that matches will add
		// next char to array
		ArrayList array = new ArrayList();
		String temp = "";
		for (int i = 0; i < source.length()-seedLength; i++) {
			temp = source.substring(i, i+seedLength);
			if (temp.equals(mySub)) {
				array.add("" + (i+seedLength+1));
			}
		}

		// if no matches were found pick random char
		if (array.isEmpty()) {
			int j = rnd.nextInt(source.length());
			myChar = source.charAt(j);
			mySub = mySub.substring(1);
			mySub += myChar;
			return myChar;
		}
		else {  // or randomly pick one from array of found chars
			int k = rnd.nextInt(array.size());
			temp = (String)array.get(k);
			myChar = temp.charAt(0);
			mySub = mySub.substring(1);
			mySub += myChar;
			return myChar;
		}
	}
}