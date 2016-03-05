package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph 
{
	protected HashMap <String, ArrayList<String>> graph;

	// contstructor 
	Graph() 
	{
		graph = new HashMap <String, ArrayList<String>> ();
	}
	
	
	// create the graph
	void createGraph (List<String> dictionary)
	{
		List<String> tempDict = new ArrayList<String> (dictionary);
		
		for (String entry : dictionary)
		{
			graph.put(entry, new ArrayList<String>());
		}
		
		for (int i = 0; i < tempDict.size(); i++)
		{
			String nodeA = tempDict.get(i);
			ArrayList<String> edgesOfNodeA = graph.get(nodeA);
			
			for (int j = i + 1; j < tempDict.size(); j++)
			{
				String nodeB = tempDict.get(j);
				ArrayList<String> edgesOfNodeB = graph.get(nodeB);
				
				if (validEdge (nodeA, nodeB))
				{
					if (!edgesOfNodeA.contains(nodeB))
					{
						edgesOfNodeA.add(nodeB);
					}
					
					if (!edgesOfNodeB.contains(nodeA))
					{
						edgesOfNodeB.add(nodeA);
					}
				}
				graph.put(nodeB, edgesOfNodeB);
			}
			graph.put(nodeA, edgesOfNodeA);
		}
	}

	
	// check to see if wordA to wordB differ by one letter
	boolean validEdge (String wordA, String wordB)
	{
		int length = wordA.length();
		int difference = 0;
		
		for (int i = 0; i < length; i++)
		{
			if (wordA.charAt(i) != wordB.charAt(i))
				difference++;
			
			if (difference > 1) return false;
		}
		
		if (difference == 1) return true;
		
		return false;
	}

	
	// graph operations
	// Breadth First Search to find the desired word
	protected List<String> computeBFS (String start, String end, List<String> dictionary)
	{
    	Queue<String> myQueue = new LinkedList<String>();
    	ArrayList<String> discoveredVertices = new ArrayList<String>();
    	HashMap<String, String> BFSTree = new HashMap<String, String>();
    	HashMap<String, Integer> layers = new HashMap<String, Integer>();
    	
    	List<String> ladder = new ArrayList<String>();
    	
    	if (dictionary.contains(start))
    	{    	
    		layers.put(start, 0);
    		discoveredVertices.add(start);
        	myQueue.add(start);
        	
        	while (!myQueue.isEmpty())
        	{
        		String vertex = myQueue.remove();
        		for (String adjacentVertex :  graph.get(vertex))
        		{
        			if (!discoveredVertices.contains(adjacentVertex))
        			{
        				int parentLayer = layers.get(vertex);
        				layers.put(adjacentVertex, parentLayer + 1);
        				discoveredVertices.add(adjacentVertex);
        				BFSTree.put(adjacentVertex, vertex);
        				myQueue.add(adjacentVertex);
        			}
        		}
        	}
        	
        	if (discoveredVertices.contains(end))
        	{
        		ladder = formWordLadder (end, BFSTree, layers);
        	}        	
    	}
  
		return ladder;
	}
	
	
	// form the word ladder using the BFS tree
	protected List<String> formWordLadder (String end, HashMap<String, String> tree, HashMap<String, Integer> layers)
	{
		List<String> result = new ArrayList<String>();
		Stack<String> wordPath = new Stack<String>();
		String i = end;
		
		while (layers.get(i) != null)
		{
			wordPath.push(i);
			i = tree.get(i);
		}
		
		while (!wordPath.isEmpty())
		{
			result.add(wordPath.pop());
		}
		
		return result;
	}
}
