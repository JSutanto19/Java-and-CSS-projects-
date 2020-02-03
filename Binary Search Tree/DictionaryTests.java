//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryTest.java
// Files: Dictionary.java, DictionaryBST.java, DictionaryDriver.java, DictionnaryTests.java
// Course: CS 300 Spring 2019
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu email address
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Bloomest Janesen Chandra
// Partner Email: bjchandra@wisc.edu
// Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
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
import java.util.NoSuchElementException;

/**
 * This class tests that all methods in DictionaryBST are working properly
 * 
 * @author Bloomest Chandra and Jason Sutanto
 *
 */

public class DictionaryTests {
  /**
   * This method tests if isEmpty method is working properly
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testIsEmpty() {
    // create new dictionary
    DictionaryBST d1 = new DictionaryBST();
    // if method does not return true return false
    if (!d1.isEmpty()) {
      return false;
    }
    // add one word to dictionary
    d1.addWord("pridisposition", "liabillity or tendency to suffer from a particular condition");
    // if method returns true return false because dictionary is not empty
    if (d1.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * This method tests that the size method is working properly
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testSize() {
    // create new dictionary
    DictionaryBST d2 = new DictionaryBST();
    // test if method returns zero on an empty dictionary
    if (d2.size() != 0) {
      return false;
    }
    // add 4 words to dictionary
    d2.addWord("Fun", "enjoyment or amusement");
    d2.addWord("Dissappointment",
        "a sad feeling that occurs when one's expectations are not fulfilled");
    d2.addWord("Regret", "a feeling of sadness and repentenance");
    d2.addWord("Forgiveness", "the action or process of being forgiven");
    // the size of dictionary is 4 so the method should return 4
    if (d2.size() != 4) {
      return false;
    }
    return true;
  }

  /**
   * This method tests addWord method works properly
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testAddWord() {
    // create new dictionary
    DictionaryBST d3 = new DictionaryBST();
    // add two words
    d3.addWord("Fun", "enjoyment or amusement");
    d3.addWord("Regret", "a feeling of sadness and repentenance");
    // check if two words are successfully added to dictionary
    if (d3.size() != 2) {
      return false;
    }
    // check if duplicate was successfully not added
    if (d3.addWord("Fun", "enjoyment or amusement")) {
      return false;
    }
    return true;
  }

  /**
   * This method tests if lookup method is working properly
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testLookup() {
    // create new dictionary
    DictionaryBST d4 = new DictionaryBST();
    // add 4 words to dictionary
    d4.addWord("Fun", "enjoyment or amusement");
    d4.addWord("Dissappointment",
        "a sad feeling that occurs when one's expectations are not fulfilled");
    d4.addWord("Regret", "a feeling of sadness and repentenance");
    d4.addWord("Forgiveness", "the action or process of being forgiven");
    // check if method returns correct definition
    if (!d4.lookup("regret").equals("a feeling of sadness and repentenance")) {
      return false;
    }
    try {
      // lookup for word that is not in a dictionary
      d4.lookup("dumb");
      return false;

    } catch (NoSuchElementException e) {
      // exception is thrown because word is not found
      return true;
    }
  }

  /**
   * This method test if height method works properly
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testHeight() {
    // create new dictionary
    DictionaryBST d5 = new DictionaryBST();
    // height returned by method should be zero since dictionary is empty
//    if (d5.height() != 0) {
//      return false;
//    }
    // add 4 words to dictionary
    d5.addWord("Fun", "enjoyment or amusement");
    d5.addWord("Dissappointment",
        "a sad feeling that occurs when one's expectations are not fulfilled");
    d5.addWord("Regret", "a feeling of sadness and repentenance");
    d5.addWord("Forgiveness", "the action or process of being forgiven");
    // height of tree should be three when adding 4 words
    if (d5.height() != 3) {
      return false;
    }
    return true;
  }

  /**
   * This method tests calls all tests method
   * 
   * @param args
   */

  public static void main(String[] args) {
    // Call all test methods
//    System.out.println(testIsEmpty());
//    System.out.println(testSize());
//    System.out.println(testAddWord());
//    System.out.println(testLookup());
    System.out.println(testHeight());
  }
}

