// Dominic Martinez
// 2/12/02

// this is the polynomial class which will use a linked list
// to do various things on polynomials

// it is also missing the methods (multiply, power, composition)

public class Polynomial {

/*************************************************
*This is the LinkNode class to Polynomial        *
*it has 3 data fields: coefficient and exponent  *
*and the "next" node on the link                 *
*************************************************/
	class LinkNode {

		public int coefficient;
		public int exponent;
		public LinkNode next;

		public LinkNode() {
			this(0, 0, null);
		}

		public LinkNode(int c) {
			this(c, 0, null);
		}

		public LinkNode(int c, int e) {
			this(c, e, null);
		}

		public LinkNode(int c, int e, LinkNode next) {
			coefficient = c;
			exponent = e;
			this.next = next;
		}
	}

// head link to the Polynomial class
	public LinkNode front;

// default constructor: will construct the exponent and
// coefficient to 0
	public Polynomial() {
		front = new LinkNode();
	}

/*****************************************************************/
// constructs a polynomial linked list out of an array
	public Polynomial(int[] a) {
		boolean coeff = true;
		for (int i = 0; i < a.length; i++) {
			if (coeff == true) {
				if (i == a.length - 1) {
					addAid(a[i], 0);
				}
				else {
					addAid(a[i], a[i+1]);
					coeff = false;
				}
			}
			else {coeff = true;}
		}
	}

// this method is to aid the constructor in keeping the
// linked list in order according to the exponents degree

   public void addAid(int c, int e) {
		if (front == null)
			front = new LinkNode(c, e);
		else if (front.exponent < e)
			front = new LinkNode(c, e, front);
		else if (front.exponent == e) {
			front.coefficient += c;
		}
		else
		{
			LinkNode cur = front;
			LinkNode prev = front;
			while (e < cur.exponent && cur.next != null) {
				prev = cur;
				cur = cur.next;
			}
			if (cur.exponent == e) {
				cur.coefficient += c;
			}
			else if (cur.exponent > e && cur.next == null)
				cur.next = new LinkNode(c, e);
			else
				prev.next = new LinkNode(c, e, cur);
		}
	}
/*****************************************************************/
// this will return the coefficient of the said exponent
// this is also O(n) since it runs the whole linked list
	public int coefficient(int exp) {
		int coeff = 0;
		LinkNode cur = front;
		while (cur.next != null) {
			if (cur.exponent == exp) {
				coeff = cur.coefficient;
			}
			cur = cur.next;
		}
		if (cur.exponent == exp) {
			return cur.coefficient;
		}
		else
			return coeff;
	}

// this will return the first exponent in the linked list
// since the first exponent is the highest degree of the
// polynomial. Runtime: O(1)
	public int degree() { return front.exponent; }

// this will substitute whatever value inputted and
// evaluate the expression. Runtime: O(n)
// because the while loop will run through
// the whole linked list
	public double eval(double value) {
		LinkNode cur = front;
		double evaluation = 0.0;
		double temp;
		while (cur.next != null) {
			temp = Math.pow(value, cur.exponent);
			evaluation += (cur.coefficient * temp);
			cur = cur.next;
		}
		if (cur.exponent == 0) {
			evaluation += cur.coefficient;
		}
		else {
			temp = Math.pow(value, cur.exponent);
			evaluation += (cur.coefficient * temp);
		}
		return evaluation;
	}

// this will take the derivative of the polynomial
// Runtime: O(n) because it has to go through the
// whole linked list deriving each monomial
	public void differentiate() {
		LinkNode cur = front;
		LinkNode prev = front;
		while (cur.next != null) {
			cur.coefficient *= cur.exponent;
			cur.exponent -= 1;
			prev = cur;
			cur = cur.next;
		}
		if (cur.exponent == 1) {
			cur.exponent -= 1;
		}
		else if (cur.exponent == 0) {
			prev.next = null;
		}
		else {
			cur.coefficient *= cur.exponent;
			cur.exponent -= 1;
		}
	}

// this will search through the entire linked list
// looking for the exponent which matches requested
// exponent and setting the coefficient if found
// making this Runtime: O(n)
	public void setCoefficient(int c, int e) {
		boolean find = false;
		LinkNode cur = front;
		LinkNode prev = front;
		while (cur.next != null) {
			if (cur.exponent == e) {
				cur.coefficient = c;
				find = true;
			}
			cur = cur.next;
		}
		if (cur.exponent == e) {
			cur.coefficient = c;
			find = true;
		}

		if (find == false) {
			cur = front;
			while (cur.next != null) {
				if (cur.exponent < e) {
					prev.next = new LinkNode(c, e, cur);
					break;
				}
				prev = cur;
				cur = cur.next;
			}
		}
	}

// this method will use the addAid method that helped the constructor
// in adding LinkNodes to the list
// Runtime: O(n) since it has to go through all the nodes in passed
// in polynomial
	public void add(Polynomial p) {
		LinkNode cur = p.front;
		while (cur != null) {
			addAid(cur.coefficient, cur.exponent);
			cur = cur.next;
		}
	}

/*********************************************************************/
// this is almost the same as the add method except it's subtract
// also had to rewrite the addAid to subtract instead of add
// Runtime: O(n) since it also has to go through the passed in polynomial
	public void subtract(Polynomial p) {
		LinkNode cur = p.front;
		while (cur != null) {
			subtractAid(cur.coefficient, cur.exponent);
			cur = cur.next;
		}

	}

// this is the helper for the above method
	public void subtractAid(int c, int e) {
		if (front == null)
			front = new LinkNode(c, e);
		else if (front.exponent < e)
			front = new LinkNode(c, e, front);
		else if (front.exponent == e) {
			front.coefficient -= c;
		}
		else
		{
			LinkNode cur = front;
			LinkNode prev = front;
			while (e < cur.exponent && cur.next != null) {
				prev = cur;
				cur = cur.next;
			}
			if (cur.exponent == e) {
				cur.coefficient -= c;
			}
			else if (cur.exponent > e && cur.next == null)
				cur.next = new LinkNode(c, e);
			else
				prev.next = new LinkNode(c, e, cur);
		}
	}

/*******************************************************************/
// this will print out the representation of the LinkedList
// Runtime: O(n) since it has to go through all the nodes in
// the list
    public String toString() {
		String listString = "";
		LinkNode cur = front;
		while (cur.next != null) {
			listString += cur.coefficient + "x^" + cur.exponent;
			if (cur.next.coefficient > 0) {
				listString += "+";
			}
			cur = cur.next;
		}
		listString += cur.coefficient + "x^" + cur.exponent;
		return listString;
	}

// this is the test driver
	public static void main(String[] args) {
		int[] a = {1, 98, -3, 2, 8, 7};
		Polynomial p = new Polynomial(a);
		System.out.println("Test: Making Polynomial..");
		System.out.println(p.toString());
		System.out.println(" ");

		int[] b = { 1, 1, 2, 2, 7, 8, 4 ,4, 10, 10};
		Polynomial p1 = new Polynomial(b);
		System.out.println("Test: Making it sorted... ");
		System.out.println(p1.toString());
		System.out.println(" ");

		int[] c = {1, 98, -3, 2, 8, 7, 5};
		Polynomial p2 = new Polynomial(c);
		System.out.println("Test: making odd numbered polynomial... ");
		System.out.println(p2.toString());
		System.out.println(" ");

		int[] d = {2, 2, 4, 1, 4};
		Polynomial p3 = new Polynomial(d);
		System.out.println("just making one for fun... ");
		System.out.println(p3.toString());
		System.out.println(" ");

		int t = p.coefficient(98);
		System.out.println("returning coefficient of exponent 98..");
		System.out.println(t);
		System.out.println(" ");

		int t1 = p1.coefficient(8);
		System.out.println("same test: exponent 8 ");
		System.out.println(t1);
		System.out.println(" ");

		int t2 = p.degree();
		System.out.println("returning highest degree..");
		System.out.println(t2);
		System.out.println(" ");

		int t3 = p1.degree();
		System.out.println("return highest degree..");
		System.out.println(t3);
		System.out.println(" ");

		double t4 = p3.eval(2);
		System.out.println("evaluating with 2..");
		System.out.println(t4);
		System.out.println(" ");

		p2.setCoefficient(3, 0);
		System.out.println("setting coefficient..");
		System.out.println(p2.toString());
		System.out.println(" ");

		p1.differentiate();
		System.out.println("differentiating ");
		System.out.println(p1.toString());
		System.out.println(" ");

		p.differentiate();
		System.out.println("damn this looks good..");
		System.out.println(p.toString());
		System.out.println(" ");

		p2.differentiate();
		System.out.println("another differentiating..making sure it drops constant.. ");
		System.out.println(p2.toString());
		System.out.println(" ");

		p1.setCoefficient(12, 9);
		System.out.println("Setting the coefficient of exponent 9..");
		System.out.println(p1.toString());
		System.out.println(" ");

		int[] e = {5,5,4,4,3,3,2,2};
		Polynomial p4 = new Polynomial(e);
		System.out.println("making list for addition ");
		System.out.println(p4.toString());
		System.out.println(" ");

		int[] f = {5,5,6,4,7,3,8,2};
		Polynomial p5 = new Polynomial(f);
		System.out.println("another list for addition ");
		System.out.println(p5.toString());
		System.out.println(" ");

		p4.add(p5);
		System.out.println("adding...");
		System.out.println(p4.toString());
		System.out.println(" ");

		p4.subtract(p5);
		System.out.println("subtracting..");
		System.out.println(p4.toString());
		System.out.println(" ");

	}
}