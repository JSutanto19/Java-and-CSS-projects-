//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DessertSolvers.java
// Files: ServingQueue.java, Guest.java, QueueTests.java
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
 * This class implements firstVariableSkips and firstDessertVariableCourses methods
 * 
 * @author Jason Sutanto
 *
 */
public class DessertSolvers {
  /**
   * This method creates a table array with numberOfGuests capactity and elements. Then this method
   * will serve one course in a table depending on number of guestsSkipped
   * 
   * @param numberOfGuests length of array and number of objects in array
   * @param guestsSkipped number of objects skip when serving
   * @return IllegalArgumentException when numbeOfGuests or guestSkipped is less than 0
   * @return delete the last guest served
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    // throw exception because numberOfGuests must be more than one
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException();
    }
    // throw exception because guestsSkipped must be non negative
    if (guestsSkipped < 0) {
      throw new IllegalArgumentException();
    }
    // stores number of guests in table
    ServingQueue guests = new ServingQueue(numberOfGuests);
    // this queue keeps track of guests that are served
    ServingQueue served = new ServingQueue(numberOfGuests);
    // temporary variable to return last guest served
    Guest delete = null;
    // create and add numberOfGuests amount of guests to guest queue
    for (int i = 0; i < numberOfGuests; ++i) {
      guests.add(new Guest());
    }
    // countinue looping until guests queue is empty
    while (!guests.isEmpty()) {
      // remove head from guests list
      delete = guests.remove();
      // add head to served queue because guest has been served
      served.add(delete);
      // skip guestsSkipped amount of indexes
      for (int i = 0; i < guestsSkipped; i++) {
        // skip occurs by adding guests to back of queue
        guests.add(guests.remove());
      }
    }
    // return last guest served
    return delete;
  };

  /**
   * This method simulates serving of guests with courses and dessert
   * 
   * @param numberOfGuests capacity of array and number of objects in array
   * @param coursesServed number of courses being served
   * @return IllegalArgumentException when numberOfGuests and coursesServed is invalid
   * @return delete the first guest to be served dessert
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    // if numberOfGuests is invalid throw exception
    if (numberOfGuests < 0) {
      throw new IllegalArgumentException();
    }
    // if coursesServed is invalid throw exception
    if (coursesServed < 0) {
      throw new IllegalArgumentException();
    }
    // stores number of guests in table
    ServingQueue guests = new ServingQueue(numberOfGuests);
    // stores number of guests served
    ServingQueue served = new ServingQueue(numberOfGuests);
    Guest delete = null;
    int guestsSkipped = 1;
    // add and create numberOfGuests amount of object elements
    for (int i = 0; i < numberOfGuests; ++i) {
      guests.add(new Guest());
    }
    // simulate serving guests courses and dessert
    for (int i = coursesServed - 1; i >= 0; i--) {
      // continue running loop until guests queue is empty
      while (!guests.isEmpty()) {
        // remove head of guests queue
        delete = guests.remove();
        // add removed element from guests queue to served
        served.add(delete);
        // skip one guest in array
        for (int j = 0; j < guestsSkipped; j++) {
          // skip guest by adding guest to end of list
          guests.add(guests.remove());
        }
      }
      // check if i not equal to zero because that is when dessert is served
      if (i != 0) {
        // remove all guest objects from served to guests queue
        for (int h = 0; h < numberOfGuests; h++) {
          guests.add(served.remove());
        }
        // rearrange elements of list until the tail as head of list
        for (int h = 0; h < numberOfGuests - 1; h++) {
          // adds head to tail of list and shifts element at head + 1 to become head
          guests.add(guests.remove());
        }
      }
    }
    // return guest that is served dessert first
    return served.remove();
  }

  /**
   * this method runs tests on the two methods above
   * 
   * @param args
   */
  public static void main(String[] args) {
    // these print statements test the functionality of the two methods above
    System.out.println(firstDessertVariableSkips(8, 1));
    System.out.println(firstDessertVariableCourses(8, 3));
  }

}
