package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkFileParser {
    // Parse the csv file into a list of book object 
    public static ArrayList<ArrayList<String>> parse(String inputfilename) throws FileNotFoundException{
    	ArrayList<ArrayList<String>> friendList = new ArrayList<ArrayList<String>>(); 
        
        
        try {
            Scanner scnr = new Scanner(new File(inputfilename));

            Scanner valueScanner = null;

            // try different methods of the Scanner STDIN
            while (scnr.hasNext()) {
                valueScanner = new Scanner(scnr.nextLine());
                valueScanner.useDelimiter(" "); 
                ArrayList<String> friendNode = new ArrayList<String>();
                
                while (valueScanner.hasNext()) {
                    String data = valueScanner.next();
                    friendNode.add(data); 
                } 
                
                friendList.add(friendNode);
            }
                    
            friendList.remove(0);
            scnr.close();

        } catch(FileNotFoundException e) {
        	
        }
        
        return friendList;
    }
}
