package Anagrams;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	/**
	 * Builds Letter Table for reference when assigning hash codes.
	 */
	public void buildLetterTable() {
		letterTable = new HashMap<Character, Integer>();
		letterTable.put('a', primes[0]);
		letterTable.put('b', primes[1]);
		letterTable.put('c', primes[2]);
		letterTable.put('d', primes[3]);
		letterTable.put('e', primes[4]);
		letterTable.put('f', primes[5]);
		letterTable.put('g', primes[6]);
		letterTable.put('h', primes[7]);
		letterTable.put('i', primes[8]);
		letterTable.put('j', primes[9]);
		letterTable.put('k', primes[10]);
		letterTable.put('l', primes[11]);
		letterTable.put('m', primes[12]);
		letterTable.put('n', primes[13]);
		letterTable.put('o', primes[14]);
		letterTable.put('p', primes[15]);
		letterTable.put('q', primes[16]);
		letterTable.put('r', primes[17]);
		letterTable.put('s', primes[18]);
		letterTable.put('t', primes[19]);
		letterTable.put('u', primes[20]);
		letterTable.put('v', primes[21]);
		letterTable.put('w', primes[22]);
		letterTable.put('x', primes[23]);
		letterTable.put('y', primes[24]);
		letterTable.put('z', primes[25]);
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	/**
	 * Adds new word to anagramTable
	 * @param s String to be added to the anagramTable
	 */
	public void addWord(String s) {
		if (anagramTable.containsKey(myHashCode(s))) {
			ArrayList<String> aT = anagramTable.get(myHashCode(s));
			aT.add(s);
		}
		else {
			ArrayList<String> newArray = new ArrayList<String>();
			newArray.add(s);
			anagramTable.put(myHashCode(s), newArray);
			}
	}
	/**
	 * Converts String s into hash code to be used as its key
	 * @param s String to be converted into a hash code.
	 * @return temp hash code key for hash table
	 */
	public long myHashCode(String s) {
		long temp = 1;
		for(int i = 0; i<s.length(); i++) {
			temp *= letterTable.get(s.charAt(i));
		}
		return temp;
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	/**
	 * Finds the hash codes with the maximum number of entries.
	 * @return tempList a list of hash codes with the maximum number of entries
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long, ArrayList<String>>> tempList = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		
		int maxValue = 0;
		for(Map.Entry<Long,ArrayList<String>> elem : anagramTable.entrySet()) {
			if (elem.getValue().size() > maxValue) {
				maxValue = elem.getValue().size();
			}
		}
		for (Map.Entry<Long,ArrayList<String>> elem : anagramTable.entrySet()) {
			if (elem.getValue().size() == maxValue) {
				tempList.add(elem);
			}
		}
		return tempList;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
