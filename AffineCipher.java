// helped by TA Dom Giandinoto
// Helped by TA Emily
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class AffineCipher {

	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			//catches exceptions if 3 arguments are not given
			System.err.println("ERROR: expecting 3 arguments");
			System.exit(1);
		}
		
		Integer a = null;
		Integer b = null;
		
		try {
			a = Integer.parseInt(args[0]);
			b = Integer.parseInt(args[1]);
			//sets the input numbers as the first and second arguments, then catches exceptions if inputs are not integers
		} catch (NumberFormatException nfe) {
			System.err.println("ERROR: expecting a single integer argument");
			System.exit(1);
		}
		
		File f = new File(args[2]);
		//sets the input file as the third argument 
		
		Scanner in = new Scanner(f);
		//sets scanner

		String myText = in.nextLine();
		//sets line in file as string
		
		
	    
	    ArrayList<Integer> cipherList = new ArrayList<Integer>();
	    ArrayList<Integer> compareList = new ArrayList<Integer>();
	    //assigns letters into appropriate integers in an ArrayList
	    //second array List helps distinguish between characters I need to modify vs ones I do not need to
	    for (int i = 0; i < myText.length(); i++) {
	    	if (Character.isLowerCase(myText.charAt(i))) {
	    		cipherList.add(myText.charAt(i) - 97);
	    		cipherList.set(i, (a * cipherList.get(i) + b) % 53);
	    		compareList.add(1);
	    		//for each lower-case integer, it is modified using its ASCII number -97 to set it to the values of (0-25)
	    	} else if (Character.isUpperCase(myText.charAt(i))) {
	    		cipherList.add(myText.charAt(i) - 39);
	    		cipherList.set(i, (a * cipherList.get(i) + b) % 53);
	    		compareList.add(1);
	    		//for each upper-case integer, it is modified using its ASCII number -97 to set it to the values of (26-51)
	    	} else if (myText.charAt(i) == ' ') {
	    		cipherList.add(52);
	    		cipherList.set(i, (a * cipherList.get(i) + b) % 53);
	    		compareList.add(1);
	    		//for each white space integer, it is set to the value of 52
	    	} else {
	    		cipherList.add((int) myText.charAt(i));
	    		compareList.add(0);
	    	}
	    	
	    }
		
  
		StringBuilder cipherBuilder = new StringBuilder();
		//I used string builder to change the modified integers back into characters
		for (int i = 0; i < cipherList.size(); i++) {
			if(compareList.get(i) ==1) {
				if (cipherList.get(i) >= 0 && cipherList.get(i) <= 26) {
					cipherBuilder.append((char) (cipherList.get(i) + 97));
					//reassigns lower-case letters from the corresponding values of (0-25)
				} else if (cipherList.get(i) >= 27 && cipherList.get(i) <= 51) {
					cipherBuilder.append((char) (cipherList.get(i) + 39));
					//reassigns upper-case letters from the corresponding values of (26-51)
				} else if (cipherList.get(i) == 52) {
					cipherBuilder.append(' ');
					//reassigns white space integer from the corresponding value of 52
				}
			}
			else {
				int num = cipherList.get(i);
				cipherBuilder.append((char) num);
			}
		}
		
		System.out.println(cipherBuilder.toString());
		//print the string left my String Builder
		
		in.close();
		//closes Scanner
	
	}

}

