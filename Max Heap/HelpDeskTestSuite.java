//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HelpDeskTestSuite.java
// Files: SupportTicket.java, HelpDesk.java, HelpDeskTestSuite.java, HelpDeskInterFace.java
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
 * This class calls test methods to test methods in HelpDesk.java
 * 
 * @author Jason Sutanto
 *
 */
public class HelpDeskTestSuite {
  /**
   * This method tests the functionality of parentOf method
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testParentOf() {
    // check if it returns the correct parent of index 0
    if (HelpDesk.parentOf(0) != 0) {
      return false;
    }
    // check if it returns the correct parent of index 2
    if (HelpDesk.parentOf(2) != 0) {
      return false;
    }
    // check if it returns the correct parent of index 1
    if (HelpDesk.parentOf(1) != 0) {
      return false;
    }
    // check if it returns the correct parent of index 3
    if (HelpDesk.parentOf(3) != 1) {
      return false;
    }
    // check if it returns the correct parent of index 4
    if (HelpDesk.parentOf(4) != 1) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the functionality of rightChildOf method in HelpDesk
   * 
   * @return true if all tests pass
   */
  public static boolean testRightChildOf() {

    // check if method returns the correct right child of index 0
    if (HelpDesk.rightChildOf(0) != 2) {
      return false;
    }

    // check if method returns the correct right child of index 1
    if (HelpDesk.rightChildOf(1) != 4) {
      return false;
    }

    // check if method returns the correct right child of index 2
    if (HelpDesk.rightChildOf(2) != 6) {
      return false;
    }

    // check if method returns the correct right child of index 3
    if (HelpDesk.rightChildOf(3) != 8) {
      return false;
    }

    // check if method returns the correct right child of index 4
    if (HelpDesk.rightChildOf(4) != 10) {
      return false;
    }
    return true;
  }

  /**
   * This method checks the functionality of leftChildOf method
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testLeftChildOf() {
    // check if method returns the correct left child of index 0
    if (HelpDesk.leftChildOf(0) != 1) {
      return false;
    }

    // check if method returns the correct left child of index 1
    if (HelpDesk.leftChildOf(1) != 3) {
      return false;
    }

    // check if method returns the correct left child of index 0
    if (HelpDesk.leftChildOf(2) != 5) {
      return false;
    }
    return true;
  }

  /**
   * This method checks the functionality of checkNextTicket
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testCheckNextTicket() {
    // create new helpdesk and add 4 tickets to desk
    HelpDesk d1 = new HelpDesk(4);
    d1.createNewTicket("AAA");
    d1.createNewTicket("ZZZ");
    d1.createNewTicket("BBB");
    d1.createNewTicket("CCC");

    // check if the highest priority element is in the head of the heap
    if (!d1.checkNextTicket().equals("ZZZ")) {
      return false;
    }
    // remove best element in desk
    d1.closeNextTicket();
    // check if next best element is the head of the heap
    if (!d1.checkNextTicket().equals("CCC")) {
      return false;
    }
    try {
      // try checking the head of the heap with the method
      HelpDesk desk2 = new HelpDesk(2);
      desk2.checkNextTicket();
    } catch (IllegalStateException e) {
      // check if the exception is thrown
      return true;
    }
    // if exception is not thrown return false
    return false;
  }

  /**
   * This method tests the functionality of createNewTicket method
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testCreateNewTicket() {
    // create new desk
    HelpDesk desk1 = new HelpDesk(5);
    // add a ticket to heap
    desk1.createNewTicket("AAA");
    // check if added ticket is root of heap
    if (!desk1.checkNextTicket().equals("AAA")) {
      return false;
    }
    // add two more tickets to heap
    desk1.createNewTicket("BBB");
    desk1.createNewTicket("DDD");
    // check if highest priority element is in the head of list
    if (!desk1.checkNextTicket().equals("DDD")) {
      return false;
    }
    // add two more tickets
    desk1.createNewTicket("GGG");
    desk1.createNewTicket("HHH");

    try {
      // try adding another ticket when heap is full
      desk1.createNewTicket("Jason");
    } catch (IndexOutOfBoundsException e) {
      // when heap is full an exception should be thrown
      return true;
    }
    // if exception was not thrown return false
    return false;
  }

  /**
   * This method tests if createNewTickets method throws an exception when add a ticket with null
   * message
   * 
   * @return true if all test pass, otherwise return false
   */
  public static boolean testCreateNewTicketException() {
    // create a new desk
    HelpDesk desk3 = new HelpDesk(10);

    try {
      // add a ticket with a null message
      desk3.createNewTicket(null);
    } catch (NullPointerException e) {
      // if exception is thrown return true
      return true;
    }
    // return false if exception is not thrown
    return false;
  }

  /**
   * This method test if closeNextTicket method throws an exception when trying to a close a ticket
   * in an empty list
   * 
   * @return true if tests pass, otherwise return false
   */
  public static boolean testCloseNextTicketException() {
    // create new desk
    HelpDesk desk4 = new HelpDesk(10);
    try {
      // remove ticket from an empty heap
      desk4.closeNextTicket();
    } catch (IllegalStateException e) {
      // check if method throws an exception when trying to close ticket on empty heap
      return true;
    }
    return false;
  }

  /**
   * This method tests the functionality of closeNextTicket method
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testCloseNextTicket() {
    HelpDesk desk5 = new HelpDesk(5);
    // base case 1 where there's only a left child
    desk5.createNewTicket("AAA");
    desk5.createNewTicket("BBB");
    desk5.createNewTicket("CCC");
    // remove highest priority element of heap
    desk5.closeNextTicket();
    // check if root of list is highest priority element on heap
    if (!desk5.checkNextTicket().equals("BBB")) {
      return false;
    }
    // add three more tickets to desk
    desk5.createNewTicket("DDD");
    desk5.createNewTicket("EEE");
    desk5.createNewTicket("FFF");
    // delete element that has priority on heap
    desk5.closeNextTicket();

    // after removing check if the highest priority object is on the root of heap
    if (!desk5.checkNextTicket().equals("EEE")) {
      return false;
    }

    return true;

  }

  /**
   * This method tests the swap method of HelpDesk
   * 
   * @return true if all tests pass, otherwise return false
   */
  public static boolean testSwap() {
    // create new heap
    HelpDesk Desk = new HelpDesk(3);
    // add three tickets to heap
    Desk.createNewTicket("AAA");
    Desk.createNewTicket("ZZZ");
    Desk.createNewTicket("EEE");
    // swap tickets in index 0 and 1
    Desk.swap(0, 1);
    // the element that was previously in index 1 should be at the head of list because they were
    // swapped
    if (!Desk.checkNextTicket().equals("AAA")) {
      return false;
    }
    // swap tickets at index 0 and 3 of heap
    Desk.swap(0, 2);
    // check if tickets at index 3 and 0 are swapped successfully
    if (!Desk.checkNextTicket().equals("EEE")) {
      return false;
    }
    return true;
  }

  /**
   * This method calls all the test methods above
   * 
   * @param args
   */
  public static void main(String[] args) {
    // call all test methods
    System.out.println("testParentOf(): " + testParentOf());
    System.out.println("testRightChildOf(): " + testRightChildOf());
    System.out.println("testLeftChildOf(): " + testLeftChildOf());
    System.out.println("testCheckNextTicket(): " + testCheckNextTicket());
    System.out.println("testCreateNewTicket(): " + testCreateNewTicket());
    System.out.println("testCloseNextTicketException(): " + testCloseNextTicketException());
    System.out.println("testCloseNextTicket(): " + testCloseNextTicket());
    System.out.println("testSwap(): " + testSwap());
  }
}
