// Dominic Martinez
// 3/6/03

import java.io.*;

public class RandomTextGenerator {

	private String source;
	private TextReader input;
/*
 Takes a String that is the filename of the original text to
 use to generate text off of.  Constructs a new text generator.
*/
	public RandomTextGenerator(String filename) {
    	try {
			File file = new File(filename);
			FileReader input = new FileReader(file);
			char[] chars = new char[(int)file.length()];
			input.read(chars);
			input.close();
			source = new String(chars);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}

/*
   This method generates text based on the text the class was
   constructed with.  It takes two parameters.  The first is the
   seed length (which is also the level from the description from
   above).  The second is the amount of characters to be generated.
   This method should output each character to System.out.
*/
	public void generate(int seedLength, int outputLength) {
		RandomCharGenerator rcg = new RandomCharGenerator(source, seedLength);
		for (int i = 0; i < outputLength; i++) {
			System.out.print(rcg.getNextChar());
		}
	}

/*
This is the same as the method above, only instead of the text
 being generated to System.out, it should go into the file
 specified by the third String parameter.
*/
	public void generate(int seedLength, int outputLength, String filename) {
		String temp = "";
		RandomCharGenerator rcg = new RandomCharGenerator(source, seedLength);

    	try {
			File file = new File(filename);
			FileWriter output = new FileWriter(file);
			char[] chars = new char[(int)file.length()];
			for (int i = 0; i < outputLength; i++) {
				output.write(rcg.getNextChar());
			}
			output.close();
			source = new String(chars);
		} catch (Exception e) {throw new RuntimeException(e.toString());}
	}
}