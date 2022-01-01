// Dominic Martinez
// 11/4/02

import java.util.*;

public class Expression {

	private String myInfix;
	private String myPostfix = "";
	private StringTokenizer myTokens;

	// constructor recieves the string to be postfixed and calculated
	// also breaks that string up into tokens
	public Expression(String s) {
		myInfix = s;
		myTokens = new StringTokenizer(s);
	}

	// just returns the infixed expression back to main
	public String toInfix() {
		return myInfix;
	}

	// will turn the infixed expression into postfixed
	public String toPostfix() {
		Stack tempStack = new Stack();
		int i = 0;
		int j = 0;
		int count = myTokens.countTokens();
		String[] temp = new String[count];
		// breaking the infixed expression up into an
		// array of tokens for later comparison
		while(myTokens.hasMoreTokens()) {
			temp[i] = myTokens.nextToken();
			i++;
		}

		// goes through the array, compares array element and makes
		// decision accordingly
		while(j != i) {
			if(temp[j].equals("("))
				tempStack.push("(");
			else if(temp[j].equals(")")) {
				while(!tempStack.peek().equals("(")) {
				   myPostfix = myPostfix + tempStack.pop() + " ";
				}
				tempStack.pop();
			}
			else if(temp[j].equals("+") || temp[j].equals("-")) {
				if(tempStack.empty() == false) {
				   if(tempStack.peek().equals("*") || tempStack.peek().equals("/") || tempStack.peek().equals("%")) {
					  myPostfix = myPostfix + tempStack.pop() + " ";
					  if(tempStack.empty() == false) {
					  		myPostfix = myPostfix + tempStack.pop() + " ";
					  		tempStack.push(temp[j]);
					  }
					  else
					  	tempStack.push(temp[j]);
				   }
				   else
				   	  tempStack.push(temp[j]);
				}
				else
					tempStack.push(temp[j]);
			}
			else if(temp[j].equals("*") || temp[j].equals("/") || temp[j].equals("%")) {
				if(tempStack.empty() == false) {
				   if(tempStack.peek().equals("*") || tempStack.peek().equals("/") || tempStack.peek().equals("%")) {
					  myPostfix = myPostfix + tempStack.pop() + " ";
					  tempStack.push(temp[j]);
				   }
				   else
				   	  tempStack.push(temp[j]);
				}
				else
					tempStack.push(temp[j]);
			}
			else
				myPostfix = myPostfix + temp[j] + " ";

			j++;
		}

		while(tempStack.empty() == false) {
			 myPostfix = myPostfix + tempStack.pop() + " ";
		}

		return myPostfix;
	}

	// this will solve the expression using the postfix expression
	public int calculate() {
        Stack tempStack = new Stack();
        Integer temp1, temp2, temp3;
        int num1, num2, num3, last;
        String s;
        int i = 0;
        int j = 0;
        StringTokenizer tempTokens = new StringTokenizer(myPostfix);
        int count = tempTokens.countTokens();
        String[] temp = new String[count];
        // this time we are breaking up the postfixed expression
        // into an array for later usage
        while(tempTokens.hasMoreTokens()) {
           temp[i] = tempTokens.nextToken();
           i++;
        }

	    // this goes through and calculates accordingly
        while(j != i) {
          if(temp[j].compareTo("+") == 0) {
              temp2 = (Integer)tempStack.pop();
              temp1 = (Integer)tempStack.pop();
              num2 = temp2.intValue();
              num1 = temp1.intValue();
              num3 = num1 + num2;
              s = Integer.toString(num3);
              temp3 = Integer.valueOf(s);
              tempStack.push(temp3);
          }
          else if(temp[j].compareTo("-") == 0) {
              temp2 = (Integer)tempStack.pop();
              temp1 = (Integer)tempStack.pop();
              num2 = temp2.intValue();
              num1 = temp1.intValue();
              num3 = num1 - num2;
              s = Integer.toString(num3);
              temp3 = Integer.valueOf(s);
              tempStack.push(temp3);
          }
          else if(temp[j].compareTo("/") == 0) {
              temp2 = (Integer)tempStack.pop();
              temp1 = (Integer)tempStack.pop();
              num2 = temp2.intValue();
              num1 = temp1.intValue();
              num3 = num1 / num2;
              s = Integer.toString(num3);
              temp3 = Integer.valueOf(s);
              tempStack.push(temp3);
          }
          else if(temp[j].compareTo("*") == 0) {
              temp2 = (Integer)tempStack.pop();
              temp1 = (Integer)tempStack.pop();
              num2 = temp2.intValue();
              num1 = temp1.intValue();
              num3 = num1 * num2;
              s = Integer.toString(num3);
              temp3 = Integer.valueOf(s);
              tempStack.push(temp3);
          }
          else if(temp[j].compareTo("%") == 0) {
		      temp2 = (Integer)tempStack.pop();
		      temp1 = (Integer)tempStack.pop();
		      num2 = temp2.intValue();
		      num1 = temp1.intValue();
		      num3 = num1 % num2;
		      s = Integer.toString(num3);
		      temp3 = Integer.valueOf(s);
		      tempStack.push(temp3);
          }
          else {
            Integer t = Integer.valueOf(temp[j]);
            tempStack.push(t);
          }
          j++;
        }

        //popping off the last thing in the stack
        temp1 = (Integer)tempStack.pop();
        last = temp1.intValue();
        return last;

	}
}