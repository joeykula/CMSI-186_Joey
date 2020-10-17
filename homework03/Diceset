public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
     if(count <=0){
       System.out.println("We need more than 0 die to play this game!");
       System.exit(0);
     }
      this.count = count;
      this.sides = sides;
      this.ds = new Die[ count ];
      for(int i=0; i <count; i++)
      {
        this.ds[i] = new Die(sides);
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
     int result = 0;
     for(int i =0; i < this.count; i++)
     {
       result += this.ds[i].getValue();
     }
      return result;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for(int i =0; i < this.count; i++)
     {
       this.ds[i].roll();
     }
     
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
     this.ds[dieIndex].roll();
      return this.ds[dieIndex].getValue();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
     if(dieIndex>=this.count || dieIndex<0)
     {
       throw new IllegalArgumentException("Die Index is out of range");
     }
      return this.ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String result = "";
      for(int i =0; i < this.count; i++)
      {
        result += this.ds[i].toString();
      }
      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      String result = "";
      return result;
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds )
   {
     if(this.count==ds.count && this.sides== ds.sides)
     {
       return true;
     }
      return false;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      // You do
   }

}
