public class Skateramp {
	
	
	double alpha; 
	double beta;
	double total;
	
	
	/**
	@param 
	**/
	
	public Skateramp (double alpha, double beta, double percent) {
		
		if (percent >= 100.0 || percent<= 0.0) {
			throw new IllegalArgumentException("Looks like your percent isn't in the correct bounds");
			
			
			}
				
			this.alpha = alpha;
			this.beta = beta;
			total = percent/100.0;
			
		
	}
	
	public double poly (double[] coeff, int n) {
		
		if (coeff.length == 0) {
			throw new IllegalArgumentException("Your args/coeffecients are causing an error, check length");
			
			//Calculating integrals through Simpsons Method 
			
			double Count;
			int Chocula;
			double x;
			double deltaX= (beta-alpha)/n;
			
			
			
			double final_integration = 0.0;
			for (Count=0.5; Count<x; Count += 1.0) {
				x = alpha + Count*deltaX;
				
				for (Chocula = 0; Chocula < coeff.length; Chocula++) {
					final_integration += coeff[Chocula]*Math.pow(x, Chocula);
					
				}
			}
			
			final_integration *= deltaX;
			return final_integration;
			}
		}
		
		public double sin (int n) {                                                                                      
			
			double Captain;
			double Crunch;
			double deltax = (beta-alpha)/n;
			double integral = 0;
			
			// Integration
			for (Captain = 0.5; Captain < n; Captain += 1.0) {
				Crunch = alpha + Captain*deltax;
				integral += Math.sin(Crunch);
			}

			
			integral *= deltax;
			return integral;
		
			
	}
		public double cos (int n) {                                                                                      
			
			// Local variables
			double Fruit;
			double Loops;
			double dx = (beta-alpha)/n;
			double integral = 0;
			
			// Integration
			for (Fruit = 0.5; Fruit < n; Fruit += 1.0) {
				Loops = alpha + Fruit*dx;
				integral += Math.cos(Loops);
			}

			// Final result
			integral *= dx;
			return integral;
		}
		
		public static boolean containsPercent (String entry) {                                                           // 9
			boolean result = false;
			for (int i = 0; i < entry.length(); i++) {
				if (entry.charAt(i) == '%') {
					result = true;
			}	}
			return result;
		}
		
		public static void help() {                                                                                     // 10
			System.out.println("Welcome to the help section! What this program does is calculte the area under a curve" 
		+ "and calculates how much wood one would need to build a skateramp under that curve.");
			System.out.println("The program is called upon like this: java SkateRamp <function name> <start point> <end point> <tolerance>%\n");
			System.out.println("The available functions are:\npoly (polynomial)\nsin (sine)\ncos (cosine)\nYour start point and end point"
			+ " may be any real number, but your tolerance must be a value between 0 and 100.\nAlso, the polynomial " + 
			"function takes in additional arguments describing its coefficients.\nFor example, \"poly 1 2 3\" is the " +
			"function 1 + 2x + 3x^2.\n");
			
			

		}
		private static void runTests() {                                                                                 
			
			SkateRamp test = new SkateRamp(1.0, 3.0, 1.0);
			
			
			int n;
			double error;
			double[] coefs = new double[3];
			coefs[0] = 4.0;
			coefs[1] = 0.0;
			coefs[2] = -1.0;
			double poly1 = 0.0; double poly2 = 0.0;
			double sin1 = 0.0;  double sin2 = 0.0;
			double cos1 = 0.0;  double cos2 = 0.0;
			
			System.out.println("Testing integral approximations from -2 to 2 with tolerance 1%\n");

			// Poly
			n = 1;
			error = test.tol + 1.0;
			while (error > test.tol) {
				n++;
				poly1 = test.poly(coefs, n);
				poly2 = test.poly(coefs, n+1);
				error = Math.abs(poly2 - poly1);
			} System.out.println("Poly:     " + Double.toString(poly2));
			
			// Sine 
			n = 1;
			error = test.tol + 1.0;
			while (error > test.tol) {
				n++;
				sin1 = test.sin(n);
				sin2 = test.sin(n+1);
				error = Math.abs(sin2 - sin1);
			} System.out.println("Sin:      " + Double.toString(sin2));
			
			// Cosine 
			n = 1;
			error = test.tol + 1.0;
			while (error > test.tol) {
				n++;
				cos1 = test.cos(n);
				cos2 = test.cos(n+1);
				error = Math.abs(cos2 - cos1);
			} System.out.println("Cos:      " + Double.toString(cos2));
		}
		

	public static void main(String[] args) {
		
		int len = args.length;
		double g; double h;
		double percent = 1.0;
		SkateRamp myRamp;
		double result1 = 0.0; double result2 = 0.0;
		double[] coefs;
		int n = 0;
		double error;
		
		
		if (len == 0) {
			System.out.println("I don't think you understand the program, let me help you out with it! ");
			SkateRamp.help();
		} else if (len < 3) {
			System.out.println("Whoops! You didn't enter enough arguments.");
			SkateRamp.help();
		} else {
			
			if ( containsPercent(args[len-1]) ) {
				int stringLength = args[len-1].length();
				String percentString = args[len-1].substring(0, stringLength-1);
				percent = Double.parseDouble(percentString);
				len -= 1;
		}
			try {
			    g = Double.parseDouble(args[len-2]);
				h = Double.parseDouble(args[len-1]);
				myRamp = new SkateRamp(g, h, percent);
				} catch (IllegalArgumentException iae) {
					System.out.println("Looks like you're having some problems with your arguments.");
					SkateRamp.help();
					return;
				}
			
			if (args[0].equals("poly")) {
				n = 0;
				error = myRamp.tol + 1.0;
				coefs = new double[len-3];
				for (int i = 1; i < len-2; i++) {
					coefs[i-1] = Double.parseDouble(args[i]);
				}
				try {
					while (error> myRamp.tol) {
						n++;
						result1 = myRamp.poly(coefs, n);
						result2 = myRamp.poly(coefs, n+1);
						error = Math.abs(result2 - result1);
					}
				} catch (IllegalArgumentException iae) {
					System.out.println("Whoops! Remember, the polynomial function takes in more arguments,"
					 + "as it needs coefficients.");
					return;
				}
				
			} else if (args[0].equals("sin")) {
				n = 0;
				error = myRamp.tol + 1.0;
				while (error > myRamp.tol) {
					n++;
					result1 = myRamp.sin(n);
					result2 = myRamp.sin(n+1);
					error = Math.abs(result2 - result1);
				}
			} else if (args[0].equals("cos")) {
				n = 0;
				error = myRamp.tol + 1.0;
				while (error > myRamp.tol) {
					n++;
					result1 = myRamp.cos(n);
					result2 = myRamp.cos(n+1);
					error = Math.abs(result2 - result1);
				}
				
			} else if (args[0].equals("runTests")) {
				SkateRamp.runTests();
			} else {
				System.out.println("Whoops! The function you entered was not valid.");
				SkateRamp.help();
				System.out.print(args[0]);
				return;
				
				
			}
			
			if (Double.isNaN(result2)) {
				System.out.println("Uhhhh hate to be a bother but I don't think your answer is a real number.");
			} else {
				System.out.println("The total wood needed is " + Double.toString(3*result2) + " square feet.");
				System.out.println("The program took " + Integer.toString(n) + " Iterations to complete.");
				
			}
			
		}
		
		
	}

}

