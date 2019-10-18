import java.io.*;

public class MatchAB {
	//MEMBERS:
	static int ss, nn;
	static String path1, path2, path3;
	static File pattern, aa, bb;
	static String[] pat, AA, BB;

	//MAIN
	public static void main(String[] args) {
		//if command line arguments are present, set the approriate values
		//otherwise, set to default values
		ss = args.length > 0 ? Integer.parseInt(args[0]) : 0;
		nn = args.length > 1 ? Integer.parseInt(args[1]) : 9;
		path1 = args.length > 2 ? args[2] : "src/pp.txt";
		path2 = args.length > 3 ? args[3] : "src/aa.txt";
		path3 = args.length > 4 ? args[4] : "src/bb.txt";

		//try block to set the pattern
		try {
			//Create BufferedReader to count the number of lines in the file
			pattern = new File(path1);
			BufferedReader numLineReader = new BufferedReader(new FileReader(pattern));

			//Count the number of lines in the file
			String st;
			int length = 0;
			while((st = numLineReader.readLine()) != null)
				length++;
			numLineReader.close();
			//initialize the pat array to the appropriate size
			pat = new String[length];

			//Create new BufferedReader to populate array with values
			BufferedReader br = new BufferedReader(new FileReader(pattern));
			for(int i = 0; i < pat.length; i++)
				pat[i] = br.readLine();
			br.close();
			//Testing to make sure array is populated correctly
			//for(String s : pat)
			//	System.out.println(s);
		} catch (Exception e) {
			System.out.println("Pattern file not found, exiting!");
			System.exit(1);
		}

		//try block to set the AA
		try {
			//Create BufferedReader to count the number of lines in the file
			aa = new File(path2);
			BufferedReader numLineReader = new BufferedReader(new FileReader(aa));

			//Count the number of lines in the file
			String st;
			int length = 0;
			while((st = numLineReader.readLine()) != null)
				length++;
			numLineReader.close();
			//initialize the AA array to the appropriate size
			AA = new String[length];

			//Create new BufferedReader to populate array with values
			BufferedReader br = new BufferedReader(new FileReader(aa));
			for(int i = 0; i < AA.length; i++)
				AA[i] = br.readLine();
			br.close();
			//Testing to make sure array is populated correctly
			//for(String s : AA)
			//	System.out.println(s);
		} catch (Exception e) {
			System.out.println("AA file not found, exiting!");
			System.exit(1);
		}

		//try block to set the BB
		try {
			//Create BufferedReader to count the number of lines in the file
			bb = new File(path3);
			BufferedReader numLineReader = new BufferedReader(new FileReader(bb));

			//Count the number of lines in the file
			String st;
			int length = 0;
			while((st = numLineReader.readLine()) != null)
				length++;
			numLineReader.close();
			//initialize the BB array to the appropriate size
			BB = new String[length];

			//Create new BufferedReader to populate array with values
			BufferedReader br = new BufferedReader(new FileReader(bb));
			for(int i = 0; i < BB.length; i++)
				BB[i] = br.readLine();
			br.close();
			//Testing to make sure array is populated correctly
			//for(String s : BB)
			//	System.out.println(s);
		} catch (Exception e) {
			System.out.println("BB file not found, exiting!");
			System.exit(1);
		}

		//Printing out the two String arrays
		System.out.println("AA file contains...");
		for(String s : AA)
			System.out.print(s + " ");
		System.out.println("\n\nBB file contains...");
		for(String s: BB)
			System.out.print(s + " ");

		//Running the tests to check if the pattern is either a...
		//False Positive
		//False Negative
		//Solution
		for(int i = 0; i < pat.length; i++) {
			//booleans to keep track of status of each pattern
			boolean matchesAA = true;
			boolean matchesBB = false;

			//looping thru both AA and BB
			//Adjusts value of booleans as necessary
			System.out.println("\n\nTesting pattern " + pat[i]);
			for(int n = 0; n < AA.length; n++) {
				if(!AA[n].matches(pat[i])) {
					//System.out.println(pat[i] + " doesn't match with " + AA[n]);
					matchesAA = false;
				}
			}

			for(int m = 0; m < BB.length; m++) {
				if(BB[m].matches(pat[i])) {
					//System.out.println(pat[i] + " matches with " + BB[m]);
					matchesBB = true;
				}
			}
			//System.out.println("AA : " + matchesAA + "\nBB : " + matchesBB);

			//logic to determine what kind of pattern it is
			if(!matchesAA && !matchesBB)
				System.out.println("Pattern is a false negative");
			else if(matchesAA && matchesBB)
				System.out.println("Pattern is a false positive");
			else if(!matchesAA && matchesBB)
				System.out.println("Pattern is a false negative and a false positive");
			else
				System.out.println("Pattern is a solution");
		}
	}
}
