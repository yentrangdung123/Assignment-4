/*
    Project   :  Assignment 4
    Written by:  Bharat Kulkarni - bsk524
    			 Dung Le - dkl524
    Completed :	 03/06/16
 */

package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assign4Driver
{
    public static void main(String[] args)
    {
    	String startWord;
        String endWord;
		if (args.length != 2) {
			System.err.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
    	String dictFile = args[0];
    	String inputFile = args[1];
    	
        Assignment4Interface wordLadderSolver = new WordLadderSolver (dictFile);
        List<String> inputs = extractWordPairs (inputFile);
        
        for (int i = 0; i < inputs.size(); i++) 
        {
        	long startTime = 0;
            long endTime = 0;
            
            try {
            	String line = inputs.get(i);
            	String[] entries = line.split("[\\s]+");
            	if(entries.length != 2){
            		throw new TooFewInputException("There are too few or too many inputs in line " + line + "\n**********\n");
            	}
            	startWord = entries[0];
            	endWord = entries[1];
            	
            	startTime = System.nanoTime();
            	List<String> result = wordLadderSolver.computeLadder(startWord, endWord);
                boolean correct = wordLadderSolver.validateResult(startWord, endWord, result);
                endTime = System.nanoTime();
                if (correct) printWordLadder (startWord, endWord, result, startTime, endTime);
            }
            catch (NoSuchLadderException e) 
            {
                //e.printStackTrace();
                System.err.println(e);
            }
            catch (TooFewInputException e) 
            {
                //e.printStackTrace();
            	System.out.flush();
            	System.err.flush();
                System.err.println(e);
            }
        } 
    }
    
    
    public static List<String> extractWordPairs (String file)
    {
    	List<String> result = new ArrayList<String>();
    	
    	try 
		{
			FileReader freader = new FileReader(file);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				
				result.add(line);
			}
			reader.close();
		}
    	
		catch (FileNotFoundException e) 
		{
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} 
    	
		catch (IOException e) 
		{
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
    	
    	return result;
    }
    
    // prints the word ladder if it exists 
    public static void printWordLadder (String start, String end, List<String> myWordLadder, long startTime, long endTime)
    {
    	if (!myWordLadder.isEmpty())
    	{
    		System.out.println("For the input words, " + start + " and " + end + ", the following word ladder"
    							+ " was found:\n");
    		String word = "";
    		for (int i = 0; i < myWordLadder.size(); i++)
    		{
    			word = word.concat(myWordLadder.get(i) + " ");
    		}
    		System.out.println(word);
            System.out.println("\nTotal runtime in (ns): " + (endTime - startTime) + "\n"); 	
    		System.out.print("**********\n\n");
    	}
    }
}
