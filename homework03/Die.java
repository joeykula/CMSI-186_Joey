public class Die{

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
     if(MINIMUM_SIDES>nSides)
     {
       throw new IllegalArgumentException("Please choose at least 4 sides for each die.");
     }
     else{
       this.sides = nSides;
       this.pips = nSides;
     }
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll()
   {
     this.pips = (int)((Math.random()*this.sides +1));
     return this.pips;
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return this.pips;
   }
   public int getSides() {
      return this.sides;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides( int sides ) {
     if(MINIMUM_SIDES>sides)
     {
       throw new IllegalArgumentException("Please choose at least 4 sides for each die.");
     }
     else{
       this.sides = sides;
       this.pips = sides;
     }
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      return "["+this.pips+"]";
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   // public static String toString( Die d ) {
   //    return "";
   // }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println( "Hello world from the Die class..." );
   }
}

