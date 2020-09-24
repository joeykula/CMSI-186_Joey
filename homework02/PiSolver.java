
public class PiSolver {

	public static void main(String[] args) {
		
		double pi = 0;
		double s = 1;
		
		int n = 1000;
		
		for(int i = 1; i<n; i=i+2) {
			pi += s/i;
			s = -s;
		}
		
		System.out.println("Pi is " + 4*pi);
		
		
		}

	}


