//Helped by TA Emily Ewalt
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

	public static void main(String[] args) throws IOException{
		if(args.length != 2) {
			//catches exceptions if 2 arguments are not given
			System.err.println("ERROR: expecting 2 arguments");
			System.exit(1);
		}
		
		Integer n = null;
		
		try {
			n = Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			//catches exception if single integer argument is not given
			System.err.println("ERROR: expecting a single integer argument");
			System.exit(1);
		}
		

		String word = args[1];
		//error message will show if the word contains a character other than a letter
		if (word.matches("^.*[^a-zA-Z].*$")){
			System.out.println("Invalid Character Used, the program will only take upper-case or lower-case letters");
			System.exit(1);

		}
		
		Map<Character, Integer> wordMap = new HashMap<Character, Integer>();
		for(int i = 0; i < word.length(); i++){
			//converts each character in input word to a hash map that counts each time it occurs
			if(wordMap.get(word.charAt(i)) == null){
			       wordMap.put(word.toLowerCase().charAt(i), 1);
			   } else {
				   //if the character has appeared before in the word, the count goes up
			      int num = wordMap.get(word.charAt(i));
			      wordMap.put(word.charAt(i), num+1);
			}
		}
		
		BufferedReader reader = new BufferedReader(new FileReader("/Users/annakrause/american")); //path I used for local file
		//BufferedReader reader = new BufferedReader(new FileReader("/usr/share/dict/american"));
		//Make bufferedReader object to read the file

		
		ArrayList<String> dict = new ArrayList<String>();
		//open dictionary and read each word, convert to lower-case and store in a StringArrayList (Dictionary)
		String line;
		while ((line = reader.readLine()) != null) {
			dict.add(line.toLowerCase());
		}
		reader.close();
		
		ArrayList<String> smallDict = new ArrayList<String>();
		//read through dictionary, if n matches with n in word, put in new small Dictionary
		for (int i=0; i < dict.size(); i++) {
			if(dict.get(i).length() == n) {
				smallDict.add(dict.get(i));
			}
		}
	 
		ArrayList<Map<Character, Integer>> mapList = new ArrayList<Map<Character, Integer>>();
		//Make Array List to use for counting characters the small dictionary 
		for(int i=0; i < smallDict.size(); i++) {
			mapList.add(new HashMap<Character, Integer>());
			//for each character that appears in the words in small dictionary, count them  
			for(int j = 0; j < smallDict.get(i).length(); j++){
				if(mapList.get(i).get(smallDict.get(i).charAt(j)) == null){
				       mapList.get(i).put(smallDict.get(i).charAt(j), 1);
				   } else {
					   //for each character that appears in the words in small dictionary already, the count goes up
				      int num = mapList.get(i).get(smallDict.get(i).charAt(j));
				      mapList.get(i).put(smallDict.get(i).charAt(j), num+1);
				}
			}
		}
		for(int i=0; i < smallDict.size(); i++) {
			boolean valid = true;
			//assigns the new char curr (current) to the current one the code is focusing on
			for (int j = 0; j < smallDict.get(i).length(); j++) {
				char curr = smallDict.get(i).charAt(j);
				//if the character count of words in small dictionary = the character count in the input word, the boolean will remain true
				if (!wordMap.containsKey(curr)) {
					valid = false;
				}
				//if the letter appears more in the small dictionary word than the amount it appears in the input word, return false
				else if (mapList.get(i).get(curr) > wordMap.get(curr)){
					valid = false;
					
				}
			}
			//prints all the "true" values or the matching amounts in the words in small dictionary and input word/words 
			if (valid) {
				System.out.println(smallDict.get(i));
			}
		}
	}

}
