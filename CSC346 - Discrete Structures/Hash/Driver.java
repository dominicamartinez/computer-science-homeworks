import java.io.*;

public class Driver {
	public static void main(String[] args) {

		// creating both the hash tables
		OpenAddressingHashTable o = new OpenAddressingHashTable();
		SeparateChainingHashTable s = new SeparateChainingHashTable();

		Object a = "bob";
		Object b = "oob";
		Object c = "hob";
		Object d = "job";
		Object e = "slob";
		Object f = "tob";
		Object g = "kob";
		Object i = "lob";


		// adding some elements
		o.insert("bob", a);
		o.insert("oob", b);
		o.insert("hob", c);
		o.insert("job", d);

		s.insert("bob", a);
		s.insert("lob", i);
		s.insert("tob", f);
		s.insert("slob", e);
		s.insert("kob", g);
		s.insert("job", d);

		// removing one that will be found and
		// one that will not
		o.remove("hob");
		o.remove("poo");

		s.remove("tob");
		s.remove("you");

		// finding some that will be found and
		// some that will not
		Object aa = o.find("oob");
		Object bb = o.find("lll");

		Object tt = s.find("slob");
		Object pp = s.find("rind");

		System.out.println(aa);
		System.out.println(bb);
		System.out.println(tt);
		System.out.println(pp);

		// changing strategy for openaddress hash
		o = new OpenAddressingHashTable();
		o.changeStrategy(4);

		// inserting some elements
		o.insert("bob", a);
		o.insert("oob", b);
		o.insert("hob", c);
		o.insert("job", d);

		o.remove("hob");
		o.remove("poo");

		aa = o.find("oob");
		bb = o.find("lll");
		System.out.println(aa);
		System.out.println(bb);

		// resetting the hash again
		s = new SeparateChainingHashTable();

		// this is going to read the text file
		TextReader input;
		try {
           input = new TextReader(new FileInputStream("jokes.txt"));
        }
        catch(IOException ioe) {
            throw new RuntimeException(ioe.toString());
        }

        // actually readin the file
		while(input.ready()) {
			String temp = input.readWord();
			if (temp.length() < 3) { }
			else { s.insert(temp, e); }
		}

		System.out.println("Separate Chaining: Number of collisions:");
		System.out.println(s.getCollisions());

		// resetting the hash again
		o = new OpenAddressingHashTable();

		// reading the file again
		try {
           input = new TextReader(new FileInputStream("jokes.txt"));
        }
        catch(IOException ioe) {
            throw new RuntimeException(ioe.toString());
        }

        // actually readin the file
		while(input.ready()) {
			String temp = input.readWord();
			if (temp.length() < 3) { }
			else { o.insert(temp, e); }
		}

		// this will get few collisions cuz once the hash is full
		// it stops inserting items.
		System.out.println("Open Addressing: Number of collisions:");
		System.out.println(o.getCollisions());
	}
}