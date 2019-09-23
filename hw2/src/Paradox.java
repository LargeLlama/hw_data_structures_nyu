import java.util.Random;

class Paradox {
	// MEMBER:========================================
		static Random rg;
		static int ss, nn, mm, pp;
	
	// METHOD:========================================
		static boolean test(int v){
			Bday[] bdays = new Bday[nn];
			for(int i = 0; i < bdays.length; i++) {
				bdays[i] = new Bday(rg);
			}
			if (v == 1) {
				for(int i = 0; i < bdays.length; i++) {
					for(int n = 0; n < bdays.length; n++) {
						if (bdays[i].isEqual(bdays[n]) && n != i) {
							System.out.println("Bday1: " + bdays[i] + "\nBday2: " + bdays[n]);
							return true;
						}
					}
				}
				for(Bday b : bdays)
					System.out.println(b);
				return false;
			} else if (v == 0) {

			}
			return false;
		}
		static String showArgs(){
			// Use String.format to show the values of ss, nn, mm, pp
			return String.format("Args: ss = %d, nn = %d, mm = %d, pp = %d", ss, nn, mm, pp);
		}
	// MAIN METHOD:===================================
		public static void main(String[] args){
			// Next, override these values with command line arguments for
			//			ss, nn, mm, pp
			// in this order!
			
			ss = args.length == 4 ? Integer.parseInt(args[0]) : 111;
			nn = args.length == 4 ? Integer.parseInt(args[1]) : 24;
			mm = args.length == 4 ? Integer.parseInt(args[2]) : 0;
			pp = args.length == 4 ? Integer.parseInt(args[3]) : 100;
			System.out.println(showArgs());

			
			// YOU MUST use the "seed" for rg in this way:
			rg = (ss==0) ? new Random() : new Random(ss);

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

