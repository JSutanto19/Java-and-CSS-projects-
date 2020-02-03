//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: QueueTests.java
// Files: ServingQueue.java, Guest.java, DessertSolvers.java
// Course: CS 300 Spring 2019
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu 
// Lecturer's Name: Gary Dahl
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
 * This class contains methods that will test the functionality of methods in the ServingQueue class
 * 
 * @author Jason Sutanto
 *
 */
public class QueueTests {
  /**
   * This method tests the functionality of the to string method
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testToString() {
    // create queue
    ServingQueue q1 = new ServingQueue(4);
    // add new guests to queue
    for (int i = 0; i < 4; i++) {
      q1.add(new Guest());
    }
    // System.out.println(q1.toString());
    // check if toString prints the correct values
    if (!q1.toString().equals("[#1, #2, #3, #4]")) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the functionality of peek method in ServingQueue
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testPeek() {
    // reset indexes of guests created
    Guest.resetNextGuestIndex();
    // create new queue add 3 guests to queue
    ServingQueue q3 = new ServingQueue(3);
    for (int i = 0; i < 3; ++i) {
      q3.add(new Guest());
    }
    // check if method removes guest that is in front of queue
    if (q3.peek() != q3.remove()) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the functionality of add method in ServingQueue
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testAdd() {
    // create new queue with capacity of 5
    ServingQueue g4 = new ServingQueue(5);
    // add 4 guests to queue
    for (int i = 0; i < 4; i++) {
      g4.add(new Guest());
    }
    // check if correct index of guests were added to queue
    if (!g4.toString().equals("[#1, #2, #3, #4]") && g4.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the functionality of remove method in ServingQueue
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testRemove() {
    // create new queue with capacity 4
    ServingQueue q5 = new ServingQueue(4);
    for (int i = 0; i < 4; i++) {
      q5.add(new Guest());
    }
    // save guest in front of queue
    Guest delete = q5.peek();
    // check if remove method removes the element in front of queue
    if (!q5.remove().equals(delete)) {
      return false;
    }
    return true;
  }

  /**
   * This method calls the test methods above
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Call all test methods
    System.out.println(testToString());
    System.out.println(testPeek());
    System.out.println(testAdd());
    System.out.println(testRemove());



  }
}
