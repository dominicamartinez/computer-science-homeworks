import java.util.*;
import java.io.*;

public class Project {

	public static final int N = 25000;
	public static final int k = 2;
	public static final double p = .01;
	public static final int timeSteps = 1000;
	public static final int L = 10;
	public static final BitSet initialVirus = new BitSet(L);
	public static final double mu = .01;
	public static final int Hthr = 2;
	public static final int immDur = 150;

	public static void main(String[] args) {

		VertexNetwork vn = new VertexNetwork(N, k, initialVirus, timeSteps, mu, L, Hthr, immDur);
		System.out.println("Network created!");

		vn.randomizeNetwork(p);
		System.out.println("Randomization completed. Beginning simulation...");

		vn.runSimulation();

		ArrayList<Integer> r = vn.getResults();

		try {
			FileWriter fw = new FileWriter("output.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < r.size(); i++) {
				bw.write("t: " + i + " we have " + r.get(i) + " ppl infected");
				bw.newLine();
			}
			bw.close();
		} catch (IOException ioe) {ioe.printStackTrace(); }

	//	vn.networkInfo();

/*
		BitSet s = new BitSet(10);
		BitSet r = new BitSet(10);

		s.xor(r);

		System.out.println(s.cardinality() + " " + Math.min(10,0));
*/
	}

}

