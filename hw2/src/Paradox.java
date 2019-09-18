import java.util.Random;

class Paradox {
	// MEMBER:========================================
		static Random rg;
		static int ss, nn, mm, pp;
	
	// METHOD:========================================
		static boolean test(int v){
			return false;
		}
		static String showArgs(){
			// Use String.format to show the values of ss, nn, mm, pp
			return "Args:";
		}
	// MAIN METHOD:===================================
		public static void main(String[] args){
				ss=111;
				nn=24;
				mm=0;
				pp=100;
			// Next, override these values with command line arguments for
			//			ss, nn, mm, pp
			// in this order!
			
			// YOU MUST use the "seed" for rg in this way:
				rg = (ss==0)? new Random() : new Random(ss);

			switch(mm){
				case 0:
					if (test(1))
						System.out.printf("\n\nSuccess!\n\n");
					else
						System.out.printf("\n\nFailure!\n\n");
					break;
				case 1:
					int success=0;
					for (int i=0; i<pp; i++){
						boolean flag=test(0);
						if (flag) success++;
					}
					System.out.printf(
						"\nFraction of success=%d/%d\n\n", success,pp);
			}
			System.out.printf("%s\n", showArgs());
		}//main
}//Paradox

