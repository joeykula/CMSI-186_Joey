public class Neptune {
	
	// Some Constants that will be used 
		public static final BrobInt LIGHTSPEED     = new BrobInt("299792458");
		public static final BrobInt MAX_SPEED      = new BrobInt("149896528");
		public static final BrobInt EARTH_ESCAPE   = new BrobInt("11186");
		public static final BrobInt NEPTUNE_ESCAPE = new BrobInt("23500");
		public static final BrobInt DISTANCE       = new BrobInt("4400000000000");

		// Instance fields (starting from zero)
		public int     hours    = 0;
		public int     minutes  = 0;
		public int     seconds  = 0;
		public BrobInt distance = BrobInt.Cero;
		public BrobInt speed    = EARTH_ESCAPE;

		// Instance fields (initialized)
		public BrobInt acceleration;
		public BrobInt decelTimeNeptune;
		public BrobInt decelDistNeptune;
		public BrobInt decelTimeEarth;
		public BrobInt decelDistEarth;

		/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		* Constructor takes in a a BrobInt and creates a new space ship, using the BrobInt as its acceleration
		* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		public Neptune (BrobInt acceleration) {
			// Errors
			if (!acceleration.positive) {
				throw new IllegalArgumentException("Value must be positive");
			}
			if (acceleration.compareTo(MAX_SPEED) == 1) {
				throw new IllegalAccessError("Acceleration cannot exceed max speed.");
			}
			// Instantiation
			this.acceleration   = acceleration;
			this.decelTimeEarth = (MAX_SPEED.subtract(EARTH_ESCAPE)).divide(acceleration);
			this.decelDistEarth = (
				MAX_SPEED.multiply(decelTimeEarth).subtract( 
					acceleration.multiply(
						decelTimeEarth.multiply(decelTimeEarth).divideByInt(2)
			)	)	);
			this.decelTimeNeptune = (MAX_SPEED.subtract(NEPTUNE_ESCAPE)).divide(acceleration);
			this.decelDistNeptune = (
				MAX_SPEED.multiply(decelTimeNeptune).subtract( 
					acceleration.multiply(
						decelTimeNeptune.multiply(decelTimeNeptune).divideByInt(2)
			)	)	);
		}

		/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		* Moves our clock forward one second.
		* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		public void tic() {
			seconds++;
			if (seconds >= 60) {seconds -= 60; minutes++;}
			if (minutes >= 60) {minutes -= 60; hours++;}
		}

		public String tellTime() {
			String result = 
				Integer.toString(hours) + " hours, " + 
				Integer.toString(minutes) + " minutes, and " +
				Integer.toString(seconds) + " seconds";
			return result;
		}

		/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		* Accellerates our ship until we hit the speed of light.
		* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		public void speedUp() {
			while (speed.compareTo(MAX_SPEED) == -1) {
				tic();
				speed = speed.add(acceleration);
				distance = distance.add(speed);
			} 
			speed = MAX_SPEED;
		}

		/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		* Cruises at 1/2 the speed of light until we reach a certain deceleration threshold.
		* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		public void toThreshold(BrobInt threshold) {

			BrobInt distanceLeft = DISTANCE.subtract(distance).subtract(threshold);
			if (!distanceLeft.positive) {
				throw new IllegalCallerException("Missed Neptune");
			}
			while (distanceLeft.positive && !distanceLeft.equals(BrobInt.Cero)) {
				tic();
				distance = distance.add(speed);
				distanceLeft = distanceLeft.subtract(speed);
			}
		}

		/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		* Decelerates our ship until we hit a certain speed.
		* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		public void slowDown (BrobInt threshold) {
			while (speed.compareTo(threshold) == 1) {
				tic();
				speed = speed.subtract(acceleration);
				distance = distance.add(speed);
			}
			speed = threshold;
		}

		public static void main (String[] args) {
			Neptune ship = null;
			try {
			ship = new Neptune( new BrobInt(args[0]));
			} catch (IllegalArgumentException iae) {
				System.out.println("Hello? Looks like you're having some trouble entering your arguments.\nThe accelleration of your ship must be a whole nonnegative number.");
				return;
			} catch (IllegalAccessError iae) {
				System.out.println("Wooooooaaaaaahhhhh now. Slow down there Buckeroo you're going a little too fast\nTry something slightly smaller that the ship's max speed.");
				return;
			}

				System.out.println("========================================================================================");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   LIFT OFF   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("========================================================================================");
				System.out.println("    Captain, our thrusters are activated at full and we have started our course to Neptune.\n We will reach lightspeed soon...I also have started your albums Captain, we shall reach Neptune soon");
				
			try {
				ship.speedUp();
				System.out.println(" ========================================================================================");
				System.out.println("    Captain, We've picked up speed.\n    We'll be cruising at the speed of light for a short while.");
				System.out.println("    -> So far, we've traveled " + ship.distance.toString() + " meters.");
				System.out.println("    -> We've been in the air for " + ship.tellTime() + ".");
				ship.toThreshold(ship.decelDistNeptune);
				System.out.println(" ========================================================================================");
				System.out.println("    Captain, we are approaching Neptune very shorthly.\n I will contact you once we are in landing distance. \n I have started Country Road by John Denver as well.");
				System.out.println("    -> So far, we've traveled " + ship.distance.toString() + " meters.");
				System.out.println("    -> We've been in space for " + ship.tellTime() + ".");
				ship.slowDown(NEPTUNE_ESCAPE);

				System.out.println(" ========================================================================================");
				System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   NEPTUNE is in sight   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(" ========================================================================================");

				System.out.println("    Captain, we are beginning our jounrey back to Earth. \n I will begin your ablums again starting with Demon Days by the Gorillaz");
				System.out.println("    -> So far, we've traveled " + ship.distance.toString() + " meters.");
				System.out.println("    -> We've been in space for " + ship.tellTime() + ".");
				ship.distance = BrobInt.Cero;
				ship.speedUp();
				System.out.println(" ========================================================================================");
				System.out.println("    We are now cruising at light speed Captain");
				System.out.println("    -> So far, we've traveled " + ship.distance.add(DISTANCE).toString() + " meters.");
				System.out.println("    -> We've been in space for " + ship.tellTime() + ".");
				ship.toThreshold(ship.decelDistEarth);
				System.out.println(" ========================================================================================");
				System.out.println("    We have begun the landng process Captain.\n I have now started Homecoming by Kanye West");
				System.out.println("    -> So far, we've traveled " + ship.distance.add(DISTANCE).toString() + " meters.");
				System.out.println("    -> We've been in space for " + ship.tellTime() + ".");
				ship.slowDown(NEPTUNE_ESCAPE);
			} catch (IllegalCallerException ice) {
				System.out.println("\n   Captain there is a problem, we have missed Neptune. Please contact Houston.");
				return;
			}

			System.out.println(" ========================================================================================");
			System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   TOUCHDOWN   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(" ========================================================================================");
			System.out.println("    We were in outer space for a grand total of " + ship.tellTime() + ".\n    Hope everyone had an excellent time up there.");
		}
}
