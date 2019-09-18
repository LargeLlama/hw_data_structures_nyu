import java.util.Random;

class Bday {
	// MEMBERS:
		int day;
	// CONSTRUCTOR:
			Bday (Random rg){
				day = rg.nextInt(365) + 1;
			}
	// METHODS:
		int[] convert(int n){
			int[] pair = new int[2]; 
			if (n <= 31) {
				pair[0] = 1;
				pair[1] = n;
			} else if (n <= 59) {
				pair[0] = 2;
				pair[1] = n - 31;
			} else if (n <= 90) {
				pair[0] = 3;
				pair[1] = n - 59;
			} else if (n <= 120) {
				pair[0] = 4;
				pair[1] = n - 90;
			} else if (n <= 151) {
				pair[0] = 5;
				pair[1] = n - 120;
			} else if (n <= 181) {
				pair[0] = 6;
				pair[1] = n - 151;
			} else if (n <= 212) {
				pair[0] = 7;
				pair[1] = n - 181;;
			} else if(n <= 243) {
				pair[0] = 8;
				pair[1] = n - 212;
			} else if (n <= 273) {
				pair[0] = 9;
				pair[1] = n - 243;
			} else if (n <= 304) {
				pair[0] = 10;
				pair[1] = n - 273;
			} else if (n <= 334) {
				pair[0] = 11;
				pair[1] = n - 304;
			} else if (n <= 365) {
				pair[0] = 12;
				pair[1] = n - 334;
			}
			return pair;
		}
		public String toString(){
			int[] pair = convert(day);
			switch(pair[0]) {
				case 1:
					return "Jan " + pair[1];
				case 2:
					return "Feb " + pair[1];
				case 3:
					return "Mar " + pair[1];
				case 4:
					return "Apr " + pair[1];
				case 5:
					return "May " + pair[1];
				case 6:
					return "Jun " + pair[1];
				case 7:
					return "Jul " + pair[1];
				case 8:
					return "Aug " + pair[1];
				case 9:
					return "Sep " + pair[1];
				case 10:
					return "Oct " + pair[1];
				case 11:
					return "Nov " + pair[1];
				case 12:
					return "Dec " + pair[1];
			}
			return "Error, no pair found - " + pair[0] + ", " + pair[1];
		}

		boolean isEqual(Bday bd) {
			return bd.day == this.day;
		}
}//Bday class
