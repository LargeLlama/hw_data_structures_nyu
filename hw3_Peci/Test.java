public class Test {
	public static void main(String[] args) {
	/*	//String "[^a].[bc]+" = "[^a].[bc]+";
		System.out.println("[^a].[bc]+");
		System.out.println("1. abc " + "abc".matches("[^a].[bc]+"));
		System.out.println("2. 1bbbbbbbb " + "1bbbbbbbb".matches("[^a].[bc]+"));
		System.out.println("3. abbbbbbbb " + "abbbbbbbb".matches("[^a].[bc]+"));
		System.out.println("4. 1zc " + "1zc".matches("[^a].[bc]+"));
		System.out.println("5. abcbcbcbc " + "abcbcbcbc".matches("[^a].[bc]+"));
		System.out.println("6. 1c " + "1c".matches("[^a].[bc]+"));
		System.out.println("7. asccbbbbcbcccc " + "asccbbbbcbcccc".matches("[^a].[bc]+"));
		System.out.println("Test: 1bcbcbc " + "1bcbcbc".matches("[^a].[bc]+")); */

//		/*

		String[] AA = {"spot", "pit", "spate", "slaptwo", "respite"};
		String[] BB = {"Pot", "part", "pt", "peat"};
		
		String pat = ".*"; 
		for(int i = 0; i < AA.length; i++) {
			if(!AA[i].matches(pat)) {
				System.out.println("DOESN'T MATCH with " + AA[i]);
			}
		}

		for(int i = 0; i < BB.length; i++) {
			if(BB[i].matches(pat)) {
				System.out.println("IT DO MATCH with " + BB[i]);
			}

		}
//		*/
	}
}
