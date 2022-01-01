import java.util.*;

public class Virus {
	private int halfLife;
	private BitSet b;
	private Random r;
	private int L;

	public Virus(int L) {
		halfLife = 0;
		b = new BitSet(L);
		r = new Random();
		this.L = L;
	}

	public void setVirus(BitSet bs) { b = (BitSet)bs.clone(); }

	public void mutate() { b.flip(r.nextInt(L)); }

	public int hamming(Virus v) {
		BitSet temp = (BitSet)b.clone();
		temp.xor(v.viralSeq());
		return temp.cardinality();
	}

	public BitSet viralSeq() { return b; }

	public void runCounter() { halfLife++; }

	public int getHL() { return halfLife; }

}