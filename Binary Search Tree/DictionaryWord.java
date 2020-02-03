//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryWord.java
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
/**
 * This class models a word in a dictionary
 * 
 * @author Bloomest Chandra and Jason Sutanto
 *
 */
public class DictionaryWord <k,v> {
  private final String word; // word that represents the search key for this dictionary word
  private final String meaning; // The meaning of the word that this dictionary node defines
  private DictionaryWord<k,v> leftChild; // The leftChild of the the current WebPageNode
  private DictionaryWord<k,v> rightChild; // The rightChild of the the current WebPageNode


  /**
   * This method creates a new dictionary word with the provided word and its meaning pair
   * 
   * @param word the word that is being added to dictionary
   * @param meaning definition of word
   * @throws IllegalArgumentException when the word or meaning are either references to an empty
   *         string or null references. .
   */
  public DictionaryWord(String word, String meaning) {
    // if word is null throw exception
    if (word == null)
      throw new IllegalArgumentException("WARNING: the word is null");
    else if (meaning == null)
      // if meaning is null throw exception
      throw new IllegalArgumentException("WARNING: the meaning is null");
    else if (word.equals(""))
      // if word is an empty string throw exception
      throw new IllegalArgumentException("WARNING: the word shouldn't be empty");
    // if meaning is an empty string throw exception
    else if (meaning.equals(""))
      throw new IllegalArgumentException("WARNING: the meaning shouldn't be empty");
    // otherwise initialize class field word with parameter word and meaning field with meaning
    // parameter
    this.word = word;
    this.meaning = meaning;
  }

  /**
   * This method gets the left child of this dictionary word
   * 
   * @return left child node of current node
   */
  public DictionaryWord getLeftChild() {
    return this.leftChild;
  }

  /**
   * This method sets left child of this dictionary word
   * 
   * @param leftChild the left child node of current node
   */
  public void setLeftChild(DictionaryWord leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * Getter for the right child of this dictionary word
   * 
   * @return rightChild returns
   */
  public DictionaryWord getRightChild() {
    return this.rightChild;
  }

  /**
   * Setter for the right child of this dictionary word
   * 
   * @param rightChild the right child of current node
   */
  public void setRightChild(DictionaryWord rightChild) {
    this.rightChild = rightChild;
  }

  /**
   * Getter for the word of this dictionary word
   * 
   * @return word the word that will be added to dictionary
   */
  public String getWord() {
    return this.word;
  }

  /**
   * Getter for the meaning of the word of this dictionary word
   * 
   * @return meaning the of this word
   */
  public String getMeaning() {
    return this.meaning;
  }

  /**
   * Returns a String representation of this DictionaryWord. This String should be formatted as
   * follows. "<word>: <meaning>" For instance, for a dictionaryWord that has the String "Awesome"
   * for the instance field word and the String "adj. Inspiring awe; dreaded." as value for meaning
   * field, the String representing that dictionaryWord is "Awesome: adj. Inspiring awe; dreaded."
   * 
   * @return the string representation of word and meaning
   */
  public String toString() {
    return this.word + ": " + this.meaning;
  }

}
