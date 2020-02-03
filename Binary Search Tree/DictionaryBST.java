//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryBST.java
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
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class contains implementation of a binary search tree which is used to create a dictionary
 * 
 * @author Bloomest Chandra and Jason Sutanto
 *
 */
public class DictionaryBST implements Dictionary {
  // root node of Dictionary Binary Search tree
  private DictionaryWord root;

  /**
   * This method Creates an empty dictionaryBST.
   */
  public DictionaryBST() {
    this.root = null;
  }

  /**
   * this method checks whether the dictionary is empty or not
   * 
   */
  public boolean isEmpty() {
    return this.root == null;
  }

  /**
   * This method adds a word definition (word and the provided meaning) to the dictionary // Returns
   * true if the word was successfully added to this dictionary.
   * 
   * @param word the new word being added to dictionary
   * @param meaning definition of word being added to dictionary
   * @return false if word is already in dictionary, otherwise return true
   * @throws IllegalArgumentException if either word or meaning is null or an empty String
   */
  public boolean addWord(String word, String meaning) {
    // check if dictionary is empty
    if (isEmpty()) {
      // if dictionary is empty add word and make word the root node and return true
      this.root = new DictionaryWord(word, meaning);
      return true;
    }
    // if list is not empty recursively call addWordHelper to find first empty node in Binary Search
    // tree and add word to that empty node
    return addWordHelper(new DictionaryWord(word, meaning), root);
  }

  /**
   * This method looks up meaning of the word passed into method
   * 
   * @param s the word that user wants to look up in the dictionary
   * @return meaning of word s if it is in the dictionary
   * @throws NoSuchElementException if the word s was not found in this dictionary
   */

  public String lookup(String s) {
    // if dictionary is empty throw exception
    if (isEmpty()) {
      throw new NoSuchElementException("WARNING: The dictionary is empty");
    }
    // otherwise call lookupHelper and traverse through BST to find meaning of S in the dictionary
    return lookupHelper(s, root);
  }

  /**
   * This method counts the size of words in the dictionary
   * 
   * @return the size of dictionary
   */
  public int size() {
    // if list is empty return 0
    if (isEmpty()) {
      return 0;
    }
    // otherwise call helper method sizeHelper that iterate through all node of BST to count size of
    // dictionary
    return sizeHelper(root);
  }

  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */

  public int height() {
    // call helper method that counts height of tree
    return heightHelper(root);
  }

  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words within this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
    // check if dictionary is empty
    if (isEmpty()) {
      // if dictionary is empty return empty ArrayList
      return new ArrayList<>();
    }
    // otherwise call helper method that gets all words in dictionary
    return getAllWordsHelper(root);
  }

  /**
   * Recursive helper method to add newWord in the subtree rooted at node
   * 
   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
   * @param current the current DictionaryWord that is the root of the subtree where newWord will be
   *        inserted
   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
   */

  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {
    // this variable stores the value from comparing the word that is being added to BST and words
    // inside the dictioinary
    int comparison = newWordNode.getWord().compareToIgnoreCase(current.getWord());
    // if comparison is zero word that user wants to add is inside dictionary so return false
    if (comparison == 0) {
      return false;
    } else if (comparison < 0) {
      // if new word is lexicographically smaller than current node
      // add word to first empty left node
      if (current.getLeftChild() == null) {
        current.setLeftChild(newWordNode);
        // return true because word was successfully added to list
        return true;
        // if current left node is not empty traverse to next left node
      } else {
        return addWordHelper(newWordNode, current.getLeftChild());
      }
    } else {
      // if new word is lexicographically greater than current word
      // add to first empty right child node
      if (current.getRightChild() == null) {
        current.setRightChild(newWordNode);
        return true;
        // if current right child is not empty go to next right child of binary tree
      } else {
        return addWordHelper(newWordNode, current.getRightChild());
      }
    }
  }


  /**
   * Recursive helper method to lookup a word s in the subtree rooted at current
   * 
   * @param s String that represents a word
   * @param current pointer to the current DictionaryWord within this dictionary
   * @return the meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if s is not found in this dictionary
   */
  private static String lookupHelper(String s, DictionaryWord current) {
    // if dictionary is empty throw exception
    if (current == null) {
      throw new NoSuchElementException("WARNING: the given word couldn't be found");
    }
    // variable stores value from comparing current word to S
    int comparison = s.compareToIgnoreCase(current.getWord());
    // if comparison is zero word that is the same as S is found and return the word's definition
    if (comparison == 0) {
      return current.getMeaning();
    } else if (comparison < 0) {
      // if S lexicographically less than current word in dictionary go to first left child node and
      // compare word to the current word in left child node
      return lookupHelper(s, current.getLeftChild());
    } else {
      // if S is lexicographically larger than current word in dictionary go to first right child
      // and compare S to the word in the current right child node
      return lookupHelper(s, current.getRightChild());
    }
  }


  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
    // Variable stores number of words in dictionary
    int returnVal = 0;
    // if there is a word in left child node recursively call helper method until there is no left
    // child node and increment value of returnVal
    if (current.getLeftChild() != null) {
      returnVal += sizeHelper(current.getLeftChild());
    }
    returnVal++;
    // if there is a word in right child node recursively call helper method until there is no right
    // child node and increment value of returnVal if there is a right child node
    if (current.getRightChild() != null) {
      returnVal += sizeHelper(current.getRightChild());
    }
    return returnVal;
  }


  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
    // if dictionary is empty return 0
    if (current == null) {
      return 0;
    }
    // stores height of left subtree
    int leftHeight = heightHelper(current.getLeftChild());
    // store height of right subtree
    int rightHeight = heightHelper(current.getRightChild());
    // if left subtree is greater than right subtree return height of left subtree and add with one
    // because root node is counted as well
    if (leftHeight >= rightHeight) {
      return 1 + leftHeight;
    } else {
      // if right subtree is greater than left subtree return height of left subtree and add with
      // one
      // because root node is counted as well
      return 1 + rightHeight;
    }
  }



  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
    // ArrayList that stores all words in dictionary
    ArrayList<String> returnVal = new ArrayList<>();
    // if left child node is not null recursively call method to go to last left child node and add
    // child node to arraylist
    if (current.getLeftChild() != null) {
      returnVal.addAll(getAllWordsHelper(current.getLeftChild()));
    }
    // add most left or right child node to list
    returnVal.add(current.getWord());
    // if right child node is not null recursively call method to go to last right child node and
    // add
    // child node to array list
    if (current.getRightChild() != null) {
      returnVal.addAll(getAllWordsHelper(current.getRightChild()));
    }
    return returnVal;
  }
}
