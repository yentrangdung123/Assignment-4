/*
    Project   :  Assignment 4
    Written by:  Bharat Kulkarni - bsk524
    			 Dung Le - dkl524
    Completed :	 03/06/16
 */

package assignment4;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Dictionary
{
	// member variables
	private List<String> allWords;

	
	// constructor
	Dictionary()
	{
		allWords = new ArrayList<String>();
	}
	
	
	// create dictionary by reading from file
	void createDictionary (String filename)
	{
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				
				if (line.charAt(0) != '*')
				{
					allWords.add(line.substring(0, 5)); 
				}
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
	}
	
	
	// getter
	List<String> getDictionaryWords ()
	{
		return allWords;
	}
}

