//==============================================================
// A3Q2template.java
//
// COMP 2140 SUMMER 2020 D01
// ASSIGNMENT 3 QUESTION 2
// PROVIDED TEMPLATE
//
// PURPOSE: Compare the performace of three dictionaries (ordered
//          array, open addressing hash table, separate chaining
//          hash table).
//==============================================================

import java.io.*;
import java.util.*;

//==========================================================
// A3Q2template class (main) --- MODIFY TO INCLUDE YOUR NAME BUT OTHERWISE DO NOT CHANGE THIS CLASS
//
// PURPOSE: Compare performance of three dictionaries. Given
//          a text file, time how long it takes to fill each
//          dictionary, one word at a time. Time how long it
//          takes to search each dictionary for a given set
//          of words.
//==========================================================

public class A3Q2template {

    public static void main(String[] args){

		String inputFile = "GreatExpectations.txt";
		String searchFile = "A3Q2TestWords.txt";

		DictionaryOrdered allWordsOrdered; //complete dictionary, using an ordered array
		DictionaryOpen allWordsOpen; //complete dictionary, using open addressing
		DictionaryChain allWordsChain; //complete dictionary, using separate chaining

		long startTime, endTime, elapsedTime;

		//Fill the dictionaries
		System.out.println("\nFilling ordered array dictionary...");
		startTime = System.nanoTime();
		allWordsOrdered = new DictionaryOrdered(100);
		buildOrdered(allWordsOrdered, inputFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to fill the ordered array dictionary with " + allWordsOrdered.getSize() + " words was: " + elapsedTime + " ns.");

		System.out.println("\nFilling open addressing dictionary...");
		allWordsOpen = new DictionaryOpen(100);
		startTime = System.nanoTime();
		buildOpen(allWordsOpen, inputFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to fill the open addressing dictionary with " + allWordsOpen.getSize() + " words was: " + elapsedTime + " ns.");

		System.out.println("\nFilling separate chaining dictionary...");
		allWordsChain = new DictionaryChain(100);
		startTime = System.nanoTime();
		buildChain(allWordsChain, inputFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to fill the open addressing dictionary with " + allWordsChain.getSize() + " words was: " + elapsedTime + " ns.");

		//Search the dictionaries
		System.out.println("\nSearching ordered array dictionary...");
		startTime = System.nanoTime();
		searchOrdered(allWordsOrdered, searchFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to search the ordered array dictionary was: " + elapsedTime + " ns.");

		System.out.println("\nSearching open addressing dictionary...");
		startTime = System.nanoTime();
		searchOpen(allWordsOpen, searchFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to search the open addressing dictionary was: " + elapsedTime + " ns.");

		System.out.println("\nSearching separate chaining dictionary...");
		startTime = System.nanoTime();
		searchChain(allWordsChain, searchFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to search the separate chaining dictionary was: " + elapsedTime + " ns.");

		System.out.println("\nEnd of Processing.");
    }//end main

    //==========================================================
    // buildOrdered
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    //   allWordsOrdered - the dictionary to be filled
    //   inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    //   none
    //==========================================================
    static void buildOrdered(DictionaryOrdered allWordsOrdered, String inputFile){
		String temp;
		String[] words;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

			temp = buff.readLine();
		    while(temp != null){
				words = temp.split("[.,;: --\"?]");

				for (int i=0; i<words.length; i++)
			      allWordsOrdered.insert(words[i]);

				temp = buff.readLine();
	    	}

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + inputFile);
		}

    }//end buildOrdered

    //==========================================================
    // buildOpen
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    //   allWordsOpen - the dictionary to be filled
    //   inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    //   none
    //==========================================================
    static void buildOpen(DictionaryOpen allWordsOpen, String inputFile){
		String temp;
		String[] words;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

			temp = buff.readLine();
		    while(temp != null){
				words = temp.split("[.,;: --\"?]");

				for (int i=0; i<words.length; i++)
			      allWordsOpen.insert(words[i]);

				temp = buff.readLine();
		    }

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + inputFile);
		}

    }//end buildOpen

    //==========================================================
    // buildChain
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    //   allWordsChain - the dictionary to be filled
    //   inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    //   none
    //==========================================================
    static void buildChain(DictionaryChain allWordsChain, String inputFile){
		String temp;
		String[] words;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

			temp = buff.readLine();
		    while(temp != null){
				words = temp.split("[.,;: --\"?]");

				for (int i=0; i<words.length; i++)
			      allWordsChain.insert(words[i]);

				temp = buff.readLine();
		    }

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + inputFile);
		}

    }//end buildChain

    //==========================================================
    // searchOrdered
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    //   allWordsOrdered - the dictionary to be searched
    //   searchFile - the file containing words to look for
    //
    // RETURNS:
    //   none (prints number of words found)
    //==========================================================
    static void searchOrdered(DictionaryOrdered allWordsOrdered, String searchFile){
		String temp;
		int numFound=0;
		int numMissing=0;

		try{

		    Scanner scnner = new Scanner(new File(searchFile));
		    while(scnner.hasNext()){

				temp = scnner.next();
				if (allWordsOrdered.search(temp))
				    numFound++;
				else
				    numMissing++;
		    }
		    System.out.println("Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + searchFile);
		}

    }//end searchOrdered

    //==========================================================
    // searchOpen
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    //   allWordsOpen - the dictionary to be searched
    //   searchFile - the file containing words to look for
    //
    // RETURNS:
    //   none (prints number of words found)
    //==========================================================
    static void searchOpen(DictionaryOpen allWordsOpen, String searchFile){
		String temp;
		int numFound=0;
		int numMissing=0;

		try{

		    Scanner scnner = new Scanner(new File(searchFile));
		    while(scnner.hasNext()){

				temp = scnner.next();
				if (allWordsOpen.search(temp))
				    numFound++;
				else
				    numMissing++;
		    }
		    System.out.println("Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + searchFile);
		}

    }//end searchOpen

    //==========================================================
    // searchChain
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    //   allWordsChain - the dictionary to be searched
    //   searchFile - the file containing words to look for
    //
    // RETURNS:
    //   none (prints number of words found)
    //==========================================================
    static void searchChain(DictionaryChain allWordsChain, String searchFile){
		String temp;
		int numFound=0;
		int numMissing=0;

		try{

		    Scanner scnner = new Scanner(new File(searchFile));
		    while(scnner.hasNext()){

				temp = scnner.next();
				if (allWordsChain.search(temp))
				    numFound++;
				else
				    numMissing++;
		    }
		    System.out.println("Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + searchFile);
		}

    }//end searchChain

}//end class A3Q2template

//==============================================================
// DictionaryOrdered class
//
// PURPOSE: Store a list of words, in an ordered array.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
//                 - public int getSize() - return the current number of words
//                 - public void insert(String newWord) - insert new word in list
//                 - public boolean search(String wordToFind) - search for
//                   given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//==============================================================
class DictionaryOrdered{


}//end class DictionaryOrdered


//==============================================================
// DictionaryOpen class
//
// PURPOSE: Store a list of words, in a hash table using open addressing.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
//                 - public int getSize() - return the current number of words
//                 - public void insert(String newWord) - insert new word in list
//                 - public boolean search(String wordToFind) - search for
//                   given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//
//==============================================================
class DictionaryOpen{


}//end class DictionaryOpen


//==============================================================
// DictionaryChain class
//
// PURPOSE: Store a list of words, in a hash table using separate
//          chaining. Words are converted to lowercase as inserted.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
//                 - public int getSize() - return the current number of words
//                 - public void insert(String newWord) - insert new word in list
//                 - public boolean search(String wordToFind) - search for
//                   given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//
//==============================================================
class DictionaryChain{


}//end class DictionaryChain


