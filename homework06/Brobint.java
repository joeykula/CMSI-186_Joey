public class BrobInt {
	
	// Static fields describing all single digit integers in terms of BrobInts.
	   public static final BrobInt Cero     = new BrobInt( "0" );
	   public static final BrobInt Uno      = new BrobInt( "1" );
	   public static final BrobInt Dos      = new BrobInt( "2" );
	   public static final BrobInt Tres    = new BrobInt( "3" );
	   public static final BrobInt Quatro     = new BrobInt( "4" );
	   public static final BrobInt Cinco     = new BrobInt( "5" );
	   public static final BrobInt Seis      = new BrobInt( "6" );
	   public static final BrobInt Siete    = new BrobInt( "7" );
	   public static final BrobInt Ocho    = new BrobInt( "8" );
	   public static final BrobInt Nueve     = new BrobInt( "9" );
	   public static final BrobInt Deis      = new BrobInt( "10" );

	   
	   public  String   internalValue;
	   public  Boolean  positive;
	   public  int[]    number;

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * This Constructor is able to take an extremely long number aka a Brobint and store it as a string
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	   public BrobInt (String value) {

	      /* ===================================================
	      ERROR HANDLING
	      We can only move on with construction iff:
	      1. All characters (past the first one) are digits.
	      2. The first character is a digit or a sign (+ or -).
	      =================================================== */
	      int  index = 0;
	      char sign  = '+';
	      if (value.charAt(0) == '+' || value.charAt(0) == '-') {
	          index++;
	          sign = value.charAt(0);
	      }
	      if ( (value.substring(index).matches("[^0-9]+")) ){
	         throw new IllegalArgumentException("Value entered not a valid big integer.");
	      }

	      /* ===================================================
	      The initial values are stored here
	      =================================================== */
	      this.internalValue = value;
	      this.positive      = true;
	      if (sign == '-')
	         {this.positive = false;}

	      /* ===================================================
	      CHUNKIFICATION
	      This creates smaller chunkcs from the original long number
	      =================================================== */
	      // Checks for leading zeros.
	      while (value.charAt(index) == '0') {
	         index++;
	         if (index == value.length()) {
	            break;
	         }
	      }

	      // Setting up variables.
	      String  digits     = "0";
	      if (index < value.length() )
	         {   digits     = value.substring(index);}
	      int     entries    = digits.length()/9;
	      int     remainder  = digits.length()%9;
	      int[]   chunks     = new int[entries];
	      if (remainder > 0)
	         {   chunks     = new int[entries+1];   }
	      String  currentNum = "";

	      // Creating array of numbers in reverse order.
	      for (int i = 0; i < entries; i++) {
	         currentNum = digits.substring(remainder + (entries-1-i)*9, remainder + (entries-i)*9);
	         chunks[i]  = Integer.parseInt(currentNum);
	      }
	      if (remainder != 0) {
	         currentNum = digits.substring(0, remainder);
	         chunks[entries] = Integer.parseInt(currentNum);
	      }
	      // Finishimo!
	      this.number = chunks;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Takes in a BrobInt and adds it to this.
	   * This is the addition part of BrobInt
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public BrobInt add (BrobInt bint) {

	      // Local variable creation...
	      int     compare     = compareTo(bint);
	      int     absCompare  = make(number, true).compareTo( make(bint.number, true) );
	      int     smallSize   = number.length;
	      int     bigSize     = bint.number.length;
	      if (absCompare == 1)
	         {   smallSize   = bint.number.length;
	             bigSize     = number.length;      }
	      int     tempSum     = 0;
	      int     carry       = 0;
	      boolean sumPositive = true;
	      int[]   sum         = new int[bigSize+1];

	      // Adding two of the same sign...
	      if ( (positive && bint.positive) || (!positive && !bint.positive)) {
	         // Forcing our sign to be what we want.
	         if (!positive && !bint.positive) {
	            sumPositive = false;
	         }
	         // Addition.
	         for (int i = 0; i <= bigSize; i++) {
	            if (i < smallSize) {
	               tempSum = number[i] + bint.number[i] + carry;
	            } else if (i < bigSize) {
	               if (compare == 1) {
	                  tempSum = number[i] + carry;
	               } else {
	                  tempSum = bint.number[i] + carry;
	               }
	            } else {
	               tempSum = carry;
	            }

	            if (tempSum >= 1e9) {
	               tempSum -= 1e9;
	               carry = 1;
	            } else {
	               carry = 0;
	            }
	            sum[i] = tempSum;
	            tempSum = 0;
	         }

	      // Adding numbers of different signs...
	      } else {
	         // Forcing our sign to be what we want.
	         if (compare == 1) {
	            if (absCompare == -1) {
	               sumPositive = false;
	            }
	         } else {
	            if (absCompare == 1) {
	               sumPositive = false;
	            }
	         }
	         if (absCompare == 0) {
	            sum = new int[1]; sum[0] = 0;
	         // This is SUBTRACTION. We subtract the value with the
	         // smaller MAGNITUDE from the bigger one. Remember, we've
	         // already changed our sign.
	         } else {
	            for (int i = 0; i <= bigSize; i++) {
	               if (i < smallSize) {
	                  if (absCompare == 1) {
	                  tempSum = number[i] - bint.number[i] - carry;
	                  } else if (absCompare == -1) {
	                     tempSum = bint.number[i] - number[i] - carry;
	                  }
	               } else if (i < bigSize) {
	                  if (absCompare == 1) {
	                     tempSum = number[i] - carry;
	                  } else {
	                     tempSum = bint.number[i] - carry;
	                  }
	               } else {
	                  tempSum = 0;
	               }

	               if (tempSum < 0) {
	                  tempSum += 1e9;
	                  carry = 1;
	               } else {
	                  carry = 0;
	               }
	               sum[i] = tempSum;
	               tempSum = 0;
	            }
	         }
	      }
	      return BrobInt.make(sum, sumPositive);
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Subtraction part of BrobInt
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public BrobInt subtract (BrobInt bint) {

	      // Create an oppositely-signed BrobInt...
	      BrobInt switched = new BrobInt(bint.internalValue);
	      if (bint.positive) {
	         switched.positive = false;
	      } else {
	         switched.positive = true;
	      }

	      // Basic addition...
	      BrobInt difference = add(switched);

	      // Done.
	      return difference;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Multiplication part of BrobInt
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public BrobInt multiply (BrobInt bint) {

	      // Local variable creation...
	      BrobInt left        = new BrobInt(internalValue);
	      BrobInt right       = new BrobInt(bint.internalValue);
	      BrobInt product     = new BrobInt("0");

	      /* ====================================================
	      RUSSIAN PEASANT ALGORITHM
	      We recursively half the "left" BrobInt (removing the
	      remainder) while doubling the right BrobInt. If the
	      left BrobInt is ODD, we add the correspoding right
	      BrobInt to our total "product" BrobInt. Once the
	      left BrobInt = 1, after one final loop, we are done!
	      ==================================================== */
	      left.positive = true; right.positive = true;
	      while (!left.toString().equals("0")) {
	         if (left.number[0]%2 == 1) {
	            product = product.add(right);
	         }
	         left = left.divideByInt(2);
	         right = right.add(right);
	      }

	      // Done!
	      if (this.positive != bint.positive) {
	         product.positive = false;
	      }
	      return product;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Division part of BrobInt
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public BrobInt divide (BrobInt bint) {

	      // Weeding out the easy option...
	      if (bint.number.length == 1) {
	         return divideByInt(bint.number[0]);
	      }

	      // Local variable creation...
	      BrobInt numerator   = new BrobInt(internalValue); numerator.positive = true;
	      BrobInt denominator = new BrobInt(bint.internalValue); denominator.positive = true;
	      BrobInt quotient    = new BrobInt("0");

	      // Dividing...
	      while (!numerator.toString().equals("0")) {
	         numerator = numerator.subtract(denominator);
	         if (!numerator.positive)
	            {break;}
	         quotient  = quotient.add(BrobInt.Uno);
	      }

	      // We're done!
	      quotient.positive = (positive == bint.positive);
	      return quotient;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Function that divides this by an integer.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public BrobInt divideByInt (int n) {

	      // Local variable creation...
	      int   len      = number.length;
	      int   tempQuot = 0;
	      int   carry    = 0;
	      int[] quotient = new int[len];

	      // Dividing...
	      if (n == 0) {
	         throw new ArithmeticException("Cannot divide by zero");
	      } else if (n == 1){
	         return new BrobInt(internalValue);
	      } else {
	         for (int i = len-1; i >= 0; i--) {
	            tempQuot = (int)( (carry*1e9 + number[i])/Math.abs(n) );
	            if (number[i]%Math.abs(n) != 0) {
	               carry = 1;
	            } else {
	               carry = 0;
	            }
	            quotient[i] = tempQuot;
	            tempQuot = 0;
	         }
	      }
	      return BrobInt.make( quotient, (positive == n>0) );
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Returns the remainder of this when dividing by int.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public BrobInt remainder (BrobInt bint) {

	      // We take the remainderlesss equation A = B/C, the get the remainder by solving
	      // r = B - AC
	      BrobInt divided = divide(bint);
	      BrobInt remainder = subtract(bint.multiply(divided));

	      // Done.
	      return remainder;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Compares this to another BrobInt. Checks if it is bigger, smaller, or equals.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public int compareTo (BrobInt bint) {

	      // Checking signs...
	      if (positive  && !bint.positive)        {return  1;}
	      if (!positive &&  bint.positive)        {return -1;}

	      // Checking the array magnitude...
	      if (positive)
	      {
	      if (number.length > bint.number.length) {return  1;}
	      if (number.length < bint.number.length) {return -1;}
	      }
	      if (!positive)
	      {
	      if (number.length > bint.number.length) {return -1;}
	      if (number.length < bint.number.length) {return  1;}
	      }

	      // Manually checking, bit by bit...
	      int len = number.length;
	      if (positive) {
	         for (int i = len-1; i >= 0; i--) {
	            if (number[i] > bint.number[i]) {return  1;}
	            if (number[i] < bint.number[i]) {return -1;}
	         }
	      }
	      if (!positive) {
	         for (int i = len-1; i >= 0; i--) {
	            if (number[i] > bint.number[i]) {return -1;}
	            if (number[i] < bint.number[i]) {return  1;}
	         }
	      }
	      /*If all else fails, they are equal.*/   return 0;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Evaluates the equality of the NUMERIC value of this and another BrobInt.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public boolean equals (BrobInt bint) {
	      if (compareTo(bint) == 0) {
	         return true;
	      }
	      return false;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Returns a String representation of this BrobInt.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public String toString() {
	      int    index = 0;
	      String sign  = "";
	      if (internalValue.charAt(0) == '+' || internalValue.charAt(0) == '-') {
	         index++;
	      }
	      if (!positive) {
	         sign = "-";
	      }
	      while (internalValue.charAt(index) == '0') {
	         index++;
	         if (index == internalValue.length()) {
	            break;
	            }
	      }
	      String output = sign + internalValue.substring(index);
	      if (output.length() == 0) {
	         return "0";
	      }
	      return output;
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Classwide version of the toString method.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public static String toString (BrobInt bint) {
	      return bint.toString();
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Manually creates a BrobInt from scratch. 
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public static BrobInt make (int[] number, boolean positive) {
	      String brobString   = "";
	      String currentChunk = "";
	      if (!positive) {
	         brobString = "-";
	      }
	      for (int i = number.length-1; i >= 0; i--) {
	         currentChunk = Integer.toString(number[i]);
	         while (currentChunk.length() < 9) {
	            currentChunk = "0" + currentChunk;
	         }
	         brobString += currentChunk;
	      }
	      return new BrobInt(brobString);
	   }

	   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   * Main method of the class. All it does is call the main method of the BrobInt test file.
	   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ **/
	   public static void main (String[] args) {
	      System.out.println("Next time, try running files from the BrobIntTemplate file....");
	      BrobIntTester.main(args);
	   }
	}



