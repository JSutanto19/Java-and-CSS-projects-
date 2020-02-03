//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ServingQueue.java
// Files: Guest.java, QueueTests.java, DessertSolvers.java
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
 * This class shows a Queue that is implemented by circular array
 * 
 * @author Jason Sutanto
 *
 */
public class ServingQueue {
  // array that stores guests
  private Guest[] array;
  // tracks number of guests in array
  private int size;
  // maximum number of guests the array can store
  private int capacity;
  // element that has been added to the list latest
  private int head;
  // index where you can store a new object next
  private int tail;

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    // initialize all fields
    this.capacity = seatsAtTable;
    this.array = new Guest[this.capacity];
    this.head = 0;
    this.tail = 0;
  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    // check if array is empty
    return size == 0;
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously added
   * to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    // throw excpetion if array is full
    if (this.size >= this.capacity) {
      throw new IllegalStateException();
    }
    // add new guest to tail index
    this.array[this.tail] = newGuest;
    // increment size
    this.size++;
    // update position of tail
    this.tail = (this.tail + 1) % this.capacity;
  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add or
   * remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    // checks if array is empty and if true throw exception
    if (this.isEmpty()) {
      throw new IllegalStateException();
    }
    return this.array[this.head];
  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    // make copy of element of head
    Guest tmp = this.array[this.head];
    // remove element
    this.array[this.head] = null;
    // decrement size
    this.size--;
    // update index of head
    this.head = (this.head + 1) % this.capacity;
    // return deleted element in array
    return tmp;
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    // stores string representation of guests in list
    String s = "[";
    // if array is empty print this statement
    if (size == 0) {
      System.out.println("Queue is empty");
    }
    // if index of tail is greater than head
    if (this.tail > this.head) {
      System.out.println("case 1");
      for (int i = this.head; i < this.tail; i++) {
        // if i is equal to tail concatenate guest data to variable S without a comma
        if (i == this.tail -1) {
          s += this.array[i].toString();
        } else {
          // concatenate data of guest to s with a comma preceding
          s += this.array[i].toString() + ", ";
        }
      }
      // add closing bracket to s
      s += "]";
      // return string
      return s;
    } else {
      // if tail is at an earlier index then size
      System.out.println("case 2");
      for (int i = this.head; i < size; i++) {
        System.out.println("loop 1");
        // if i is equal to size - 1 concatenate data of array in index i to variable s without comma
        if (i == this.size - 1) {
          s += this.array[i].toString();
        } else {
        //otherwise concatenate data at index i of array with comma 
          s += this.array[i].toString() + ", ";
        }
      }
    }
    //print data from index 0 to tail
    for (int i = 0; i < this.tail; i++) {
      System.out.println("loop 2");
      //if i is tail than concatenate data to variable s without comma
      if (i == tail - 1) {
        System.out.println("condition 1");
        s += ", " + this.array[i].toString();
      } else {
        System.out.println("condition 2");

        //otherwise concatenate data to variable s with comma
        s += this.array[i].toString() + ", ";
      }
    }
    s += "]";
    return s;
  }
}


