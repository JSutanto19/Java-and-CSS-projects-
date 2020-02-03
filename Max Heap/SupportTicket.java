//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: SupportTicket.java
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
 * this class represents a ticket object which will be added to a HelpDesk
 * 
 * @author Jason Sutanto
 *
 */
public class SupportTicket implements Comparable<SupportTicket> {
  // the message of a ticket
  private String message;

  /**
   * This method initializes the fields of this object
   * 
   * @param message the message of a ticket
   * @throws NullPointerException if message is null
   */
  public SupportTicket(String message) {
    // if message is null throw an exception
    if (message == null) {
      throw new NullPointerException();
    }
    // initialize field with message
    this.message = message;
  }

  /**
   * This method returns the message of ticket
   * 
   * @return message the message of ticket
   */
  @Override
  public String toString() {
    return this.message;
  }

  /**
   * This method compares the messages of two tickets and returns an int value based on the
   * comparison
   * 
   * @param otherTicket the other ticket that you want to compare your message too
   */
  @Override
  public int compareTo(SupportTicket otherTicket) {
    if (this.message.length() > otherTicket.message.length()) {
      return 1;
    } else if (this.message.length() < otherTicket.message.length()) {
      return -1;
    } else {
      return this.message.compareTo(otherTicket.message);
    }
  }

  public static void main(String[] args) {
    SupportTicket s1 = new SupportTicket("ZZZZ");
    SupportTicket s2 = new SupportTicket("AAAAAAA");
    System.out.println(s1.compareTo(s2));

  }

}
