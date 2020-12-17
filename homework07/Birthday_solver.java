public class Birthday_solver {

	
	
	int N;
    int experiments_x;
    int[] birthdayArray;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /*This constructor takes a certain amount of people and does an experiment x number of times 
     * and then figures out how many people have the same birthday to a certain percentage
     */
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    public Birthday_solver (int N, int experiments_x) {
        if (N > 365 || experiments_x > 10000000) {
            throw new IllegalArgumentException("Values too large");
        }
        this.N = N;
        this.experiments_x = experiments_x;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /** this give the probability between 1 and 0 for how unique a birthday might be
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double realProbability() {
        double twoSame;
        double noneSame = 1.0;
        double birthdaysLeft = 365.0;
        for (int i = 0; i < N; i++) {
            noneSame *= birthdaysLeft/365.0;
            birthdaysLeft -= 1.0;
        }
        twoSame = 1.0 - noneSame;
        return twoSame;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /** Creates a random list possible birthdays between 1 and 365 each being a day of the year                                           
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void generateBirthdays() {
        birthdayArray = new int[N];
        for (int i = 0; i < N; i++ ) {
            birthdayArray[i] = (int)Math.ceil(Math.random()*365);
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /** Calculate an estimation of the probability by running a given number of
      * trials. Divides the number of times two shared a birthday by the number
      * of trials total.
      * @return double value describing the fake probability   
      * This creates a calculation of the likelihood of birthdays matching 
      * and then finds the overall probability                  **/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double fakeProbability() {
        double sharedBirthday = 0.0;

        for (int i = 0; i < experiments_x; i++) {
            generateBirthdays();

            // Comparison loop
            boolean shouldBreak = false;
            for (int j = 0; j < birthdayArray.length; j++) {
                for (int k = 0; k < birthdayArray.length; k++) {
                    if ( (j != k) && (birthdayArray[j] == birthdayArray[k]) )  {
                        sharedBirthday += 1.0;
                    shouldBreak = true;
                    }
                    if (shouldBreak) {break;}
                }
                if (shouldBreak) {break;}
            }
        }
        double probability = sharedBirthday/experiments_x;
        return probability;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /** Main method of the class. Takes user inputs and generates a birthday class
      * to tell us the probability of at least two people sharing a birthday in a
      * group. But that's not all! We also calculate an estimate of the probability,
      * using a given number of simulations.
      * @param args String array of command line arguments.                      **/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main (String[] args) {
        int N = 0;
        int experiments = 100000;
        Birthday_solver birthday = null;

        try {
            N = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Whoops! Looks like you didn't enter your arguments in correctly.");
            System.out.println("\nThe correct syntax is: Java BirthdaySolver <number of people> <number of trials (optional)>");
            return;
        }

        if (args.length > 1) {
            try {
                experiments = Integer.parseInt(args[1]);
            } catch (Exception e) {
                System.out.println("Whoops! Looks like the parameter for the number of experiments.");
                System.out.println("\nThe correct syntax is: Java BirthdaySolver <number of people> <number of trials (optional)>");
                return;
            }
        }
        try {
            birthday = new Birthday_solver(N, experiments);
        } catch (Exception e) {
            System.out.println("Woah there, your inputs are way, way, way too big! Think a bit smaller.");
        }

        System.out.println("Calculating.... This may take some time.");
        Double realProb = 100*birthday.realProbability();
        Double fakeProb = 100*birthday.fakeProbability();

        String realProbF = String.format(("%2.2f"), realProb);
        String fakeProbF = String.format(("%2.2f"), fakeProb);

        System.out.println("In a group of " + Integer.toString(N) + " people, there is a " + realProbF + "% chance that at least two people will share a birthday.");
        System.out.println("Our simulation, however, tells us that there is a " + fakeProbF + "% chance.");
    }
}



