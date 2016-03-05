/*
    Bharat Kulkarni - bsk524
 */

package assignment4;

import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{	
    // dictionary and graph (underlying structures)
	protected Dictionary myDictionary;
	protected Graph myGraph;
	protected List<String> myWordLadder;
	
	
	// constructor
	WordLadderSolver (String fileName)
	{
		// create dictionary and graph of dictionary
		long beginTest;
		long endTest;
		myDictionary = new Dictionary();
    	myDictionary.createDictionary(fileName);
    	myGraph = new Graph();
    	beginTest = System.nanoTime();
		myGraph.createGraph(myDictionary.getDictionaryWords());
		endTest = System.nanoTime();
		System.out.println("Creating the graph took: " + (endTest - beginTest) + " ns");
	}
	
	
    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	List<String> wordLadder;
    	List<String> words = myDictionary.getDictionaryWords();
    	
    	if (!words.contains(startWord) || !words.contains(endWord))
    	{
    		
    		throw new NoSuchLadderException ("At least one of the words " + startWord +
    										 " and " + endWord + " is not a legitimate 5-letter word "
    										 + "from the dictionary");
    	}
    	
    	wordLadder = myGraph.computeBFS(startWord, endWord, words);
    	if (wordLadder.isEmpty()) throw new NoSuchLadderException ("For the input words, " + startWord + " and "
    			                                                    + endWord + "\nNo word ladder exists!");

		return wordLadder;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	List<String> words = myDictionary.getDictionaryWords();
    	
    	for (String entry : wordLadder)
    	{
    		if (!words.contains(entry))
    			return false;	    		
    	}
    	
    	myWordLadder = wordLadder;
    	printWordLadder (startWord, endWord);
    	
    	return true;
    }
    
    
    // prints the word ladder if it exists - (uses myWordLadder)
    public void printWordLadder (String start, String end)
    {
    	if (!myWordLadder.isEmpty())
    	{
    		System.out.println("For the input words, " + start + " and " + end + ", the following word ladder"
    							+ " was found:\n");
    		
    		for (int i = 0; i < myWordLadder.size(); i++)
    		{
    			System.out.println(myWordLadder.get(i));
    		}
    		
    		System.out.print("\n**********\n");
    	}
    }
}





















