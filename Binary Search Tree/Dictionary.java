//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary.java
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
 * This interface contains methods that will be implemented in DictionaryBST
 * 
 * @author Bloomest Chandra and Jason Sutanto
 *
 */
public interface Dictionary {
  /**
   * this method checks whether the dictionary is empty or not
   * 
   */
  public boolean isEmpty();

  /**
   * This method adds a word definition (word and the provided meaning) to the dictionary // Returns
   * true if the word was successfully added to this dictionary.
   * 
   * @param word the new word being added to dictionary
   * @param meaning definition of word being added to dictionary
   * @return false if word is already in dictionary, otherwise return true
   * @throws IllegalArgumentException if either word or meaning is null or an empty String
   */
  public boolean addWord(String word, String meaning);

  /**
   * This method looks up meaning of the word passed into method
   * 
   * @param s the word that user wants to look up in the dictionary
   * @return meaning of word s if it is in the dictionary
   * @throws NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s);

  /**
   * This method counts the size of words in the dictionary
   * 
   * @return the size of dictionary
   */
  public int size();
}
