//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban
// Files: TestSokoban.Java, Config.Java
// Course: CS 200 Fall 2018
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu email address
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza
 * class as they are developed. These methods are private since they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author TODO add your name here when you add tests and comment the tests
 *
 */
public class ElizaTests {
    
    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
    public static void main(String[] args) {

        //Milestone 1: Process User Input
        //M1: main  nothing to do   
        testSeparatePhrases();  
        testFoundQuitWord();    
        testSelectPhrase();     
        testReplaceWord();      
        testAssemblePhrase();   

        //Milestone 2: 
        //M2: implement parts of main as described in main method comments
        testSwapWords();
        testPrepareInput();
        testLoadResponseTable();
        
        //Milestone 3: 
        //main: implement the rest of main as described in the main method comments
        testFindKeyWordsInPhrase();  
        testSelectResponse();      
        testInputAndResponse();     
        testSaveDialog();           
    }
    
    /**
     * This runs some tests on the separatePhrases method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */ 
    private static void testSeparatePhrases() {
        boolean error = false;

        // test 1 part 1
        ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("no");
        expected.add("i'm talking to my dog");
        expected.add("bye");

        if (phrases.size() != expected.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected.size() + " but found " + phrases.size() + " phrases.");
        }

        // test 1 part 2.
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(phrases.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected.get(i));
                System.out.println("  " + phrases.get(i));
            }
        }
        
        //test 2 part 1 
        ArrayList<String> phrases2 = Eliza.separatePhrases("What? This isn't the 4th messy-sentence!");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add("What");
        expected2.add("This isn't the 4th messy sentence");
        

        if (phrases.size() != expected.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected2.size() + " but found " + phrases2.size() + " phrases.");
        }
        
        //Test 2 part 2
        for (int i = 0; i < expected2.size(); i++) {
            if (!expected.get(i).equals(phrases.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected2.get(i));
                System.out.println("  " + phrases2.get(i));
            }
        }
        
        //test 3 part 1 
        ArrayList<String> phrases3 = Eliza.separatePhrases("No");
        ArrayList<String> expected3 = new ArrayList<>();
        expected3.add("no");
        
        

        if (phrases3.size() != expected3.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected3.size() + " but found " + phrases3.size() + " phrases.");
        }
        
        //Test 3 part 2
        for (int i = 0; i < expected3.size(); i++) {
            if (!expected3.get(i).equals(phrases3.get(i))){
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected3.get(i));
                System.out.println("  " + phrases3.get(i));
            }
        }
        
        //test 3 part 1 
        ArrayList<String> phrases4 = Eliza.separatePhrases("this tab");
        ArrayList<String> expected4 = new ArrayList<>();
        expected4.add("this tab");
       
        

        if (phrases4.size() != expected4.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected4.size() + " but found " + phrases4.size() + " phrases.");
        }
        
        //Test 3 part 2
        for (int i = 0; i < expected4.size(); i++) {
            if (!expected4.get(i).equals(phrases4.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected4.get(i));
                System.out.println("  " + phrases4.get(i));
            }
        }
        
      //test 4 part 1 
        ArrayList<String> phrases5 = Eliza.separatePhrases("What?");
        ArrayList<String> expected5 = new ArrayList<>();
        expected5.add("what");
       
        if (phrases5.size() != expected5.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected5.size() + " but found " + phrases5.size() + " phrases.");
        }
        
        //Test 4 part 2
        for (int i = 0; i < expected5.size(); i++) {
            if (!expected5.get(i).equals(phrases5.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected5.get(i));
                System.out.println("  " + phrases5.get(i));
            }
        }
        
        // test 5 part 1
        ArrayList<String> phrases6 = Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
        ArrayList<String> expected6 = new ArrayList<>();
        expected6.add("thank you");
        expected6.add("but no");
        expected6.add("i have to go");
        expected6.add("goodbye");

        if (phrases6.size() != expected6.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected6.size() + " but found " + phrases6.size() + " phrases.");
        }

        // test 5 part 2.
        for (int i = 0; i < expected6.size(); i++) {
            if (!expected6.get(i).equals(phrases6.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected6.get(i));
                System.out.println("  " + phrases6.get(i));
            }
        }
        
        
        
        
        
       
        
        

        
       
        
        // "Thank you, but no, I have to go. Goodbye!!!" should result in?

        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    // TODO should QUIT_WORDS be embedded within foundQuitWord?
    private static void testFoundQuitWord() {
        boolean error = false;

        // 1.
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("thank you for talking");
        phrases.add("bye");

        boolean quit = Eliza.foundQuitWord(phrases);
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 1: failed");
        }

        // additional test suggestions:
        // should foundQuitWord return true if bye is a part of a phrase rather than
        // separate?
        // should foundQuitWord return true if goodbye is the first or only phrase?
        // should foundQuitWord return true if there are no quit words in the phrases
        // list.

        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */ 
    private static void testSelectPhrase() {
        boolean error = false;

        // choose the longest
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("no");
        phrases.add("sometimes I remember being on a boat");
        phrases.add("not often");
        String choice = Eliza.selectPhrase(phrases);
        if (!choice.equals("sometimes I remember being on a boat")) {
            error = true;
            System.out.println("testSelectPhrase 1: failed");
        }

        // additional test suggestions:
        // What should happen when there are 2 choices of the same length?
        // What should happen if an empty list is passed to selectPhrase?

        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testAssemblePhrase() {
        boolean error = false;

        String[] words = { "This", "is a", "sentence" };
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This is a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }

        // additional test suggestions:
        // what should happen if an array with no strings in it is passed in?
        // what should happen if just a list of words, with no internal spaces, is
        // passed in?

        if (error) {
            System.err.println("testAssemblePhrase failed");
        } else {
            System.out.println("testAssemblePhrase passed");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {// block so each test has its own variable scope.
            // 1.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "are", "you", "a", "computer" };

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("are you a") || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 2.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "computer", "are", "you" };

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("") || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 3.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "does", "that", "computer", "on", "your", "desk", "work" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                    || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");            
            keywords.add("me");         
            String[] phrase = { "why", "don't", "you", "like", "me" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't") || !matches[1].equals("like")
                    || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");            
            keywords.add("me");             
            String[] sentence = { "me", "don't", "like", "you" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        // additional tests?

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }
    
    /**
     * This runs some tests on the saveDialog method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */ 
    private static void testSaveDialog()  {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog( list, TEST_FILENAME);
                String readFromFile = readFile( TEST_FILENAME);
                if ( !readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile( TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
        // 2. test multiple lines of output written to the file.
        // additional tests?

        if (error) {
            System.err.println("testSaveDialog failed");
        } else {
            System.out.println("testSaveDialog passed");
        }
    }
    
    /**
     * Supporting method for testSaveDialog
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     * 
     * @param filename  file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This runs some tests on the replaceword method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */ 
    private static void testReplaceWord()  {
        boolean error = false;
        
        {    //1. 
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }   
        
        {    //2. 
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        {
            //3
            String word = "machine";
            String expected = "machine";
            String wordMap [][] = null;
            String result = Eliza.replaceWord(word, wordMap);
            
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
            
        }
        
        //additional suggestions:
        //Do these tests meet all the requirements described in the replaceWord header comment,
        //such as handling a null wordMap?
        
        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }
    
    /**
     * This runs some tests on the swapWords method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */ 
    private static void testSwapWords()  {
        boolean error = false;
        
        {    //1. 
            String someWords = "i'm cant recollect";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }       

        {    //2. 
            String someWords = "i'm happy";
            String expected = "you are happy";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        {    //3. 
            String someWords = "about my dog";
            String expected = "about your dog";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        //additional tests?
        
        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }       
    }   

    /**
     * This runs some tests on the selectResponse method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */ 
    private static void testSelectResponse() {
        boolean error = false;
        
        {  //1.
            // is one of the 3 strings chosen?
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");         
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }
        
        { // 2.
            // if called 1000 times, are the choices approximately random?
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int [] actualCount = new int[strList.size()];
            int [] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for ( int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if ( choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }
            //since we set a seed on the random number generator we should know the expected 
            //outcome
            for ( int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if ( actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString( expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString( actualCount));
                }
            }

        }
        
        //additional test suggestions: 
        //What should happen when a list a single string is provided? 
        //What should happen when null is passed to selectResponse?
        
        
        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }           
    }   

    /**
     * This runs some tests on the prepareInput method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */     
    private static void testPrepareInput() {
        boolean error = false;
        
        { // 1.
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("input: " + input);
                System.out.println("result: " + Arrays.toString(result));
            }
        }
        
        { //2.
            String input = "I said goodbye";
            String [] result = null;
            result = Eliza.prepareInput("I said goodbye");
            String [] expected = {"i" , "said" , "goodbye"};
            if (!Arrays.equals(result,expected)) {
                error = true;
                System.out.println("testPrepareInput 2: failed");
                System.out.println("input: " + input);
                System.out.println("result: " + Arrays.toString(result));
            }
   
        }
        
        { //3.
            String input = "I can't do that";
            String [] result = null;
            result = Eliza.prepareInput("I can't do that");
            String [] expected = {"i" , "cannot" , "do" , "that"};
            if (!Arrays.equals(result,expected)) {
                error = true;
                System.out.println("testPrepareInput 3: failed");
                System.out.println("input: " + input);
                System.out.println("result: " + Arrays.toString(result));
            }
   
        }
        
        
        
        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }   
    }
    
    /**
     * This runs some tests on the loadResponseTable method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */     
    private static void testLoadResponseTable() {
        boolean error = false;
        
        { // 1.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals( expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " +table.get(0));
            }
            
            // 2.
            if ( table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println("  expected an even number of elements in table but found: " + table.size());
            }
        }
        
        //additional test suggestions
        //Are the right number of keywords and responses read in for a keyword/response pair?
        //Are blank lines (containing no spaces or only whitespace) ignored?
        //Are the last rows of the file read in?
        //Are the right number of rows read in?
        
        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }   
    }   
    
    /*
     * Supporting method for testInputAndResponse.
     * Returns 1 if the test passed and 0 if the test failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand, ArrayList<ArrayList<String>> table) {
        
        String [] words = Eliza.prepareInput(input);
        if ( words == null) {
            if ( expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }
        
        String response = Eliza.prepareResponse(words, rand, table);
        if ( !response.equals( expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response +"'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }
    
    /*
     * Runs tests on a bunch of example sentences using a Random number generator with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        //since we are using a seed in the random number generator, the responses should be fixed for the provided response table.
        Random randGen = new Random( 923);
        ArrayList<ArrayList<String>> responseTable = Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.", "What do you think machines have to do with your problem?", randGen, responseTable);
        
        numPassed += checkResponse("What is your name?", "I don't care about names -- please continue.", randGen, responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.", "What other connections do you see?", randGen, responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?", randGen, responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?", randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.", "Why do you think you wish to believe me?", randGen, responseTable);
        numPassed += checkResponse("I dreamed of going into space.", "Have you ever dreamed of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.", "Why do you think you apologize for boring me?", randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.", "Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.", "What is the connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.", "Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.", "Tell me more about your family.", randGen, responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen, responseTable);
        numPassed += checkResponse("Are you a Packers fan?", "Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?", randGen, responseTable);
        numPassed += checkResponse("I cannot explain this.", "Do you really want to be able to explain this?", randGen, responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.", "You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.", "How long have you been talking to your dog?", randGen, responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);
        
        numPassed += checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);
        
        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }   
    }
    
}
