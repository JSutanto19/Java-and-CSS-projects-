//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HelpDesk.java
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
 * This class represents a help desk implemented by a max heap
 * 
 * @author Jason Sutanto
 *
 */
public class HelpDesk implements HelpDeskInterface {
  protected SupportTicket[] array; // zero-indexed max-heap
  protected int size; // number of tickets in array

  /**
   * This method intializes all fieds of the HelpDesk class
   * 
   * @param capacity
   */
  public HelpDesk(int capacity) {
    // create array of type HelpDesk that contains capacity number of tickets
    this.array = new SupportTicket[capacity];
    // size is set to zero since there are no tickets in the array
    this.size = 0;
  }

  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * 
   * @param message names the client and describes their need for support.
   * @throws NullPointerException when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  @Override
  public void createNewTicket(String message) {
    // When adding a ticket with a null message throw a NullPointerException
    if (message == null) {
      throw new NullPointerException();
    }
    // if array is full throw IndexOutOfBoundsException
    if (size == this.array.length) {
      throw new IndexOutOfBoundsException();
    }
    // if array is empty add ticket to first empty position of list
    if (size == 0) {
      this.array[size] = new SupportTicket(message);
      // increment size
      this.size += 1;
    } else {
      // if there is more than one element and new ticket to next empty index of array
      this.array[size] = new SupportTicket(message);
      // increment array size
      this.size += 1;
      // compare the new ticket with parents and if the ticket has a higher priority swap parent
      // with child
      this.propagateUp(size - 1);
    }
  }

  /**
   * Returns the message within this HelpDesk that has the highest priority. This method does not
   * change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String checkNextTicket() {
    // if array is empty throw an exception
    if (this.size == 0) {
      throw new IllegalStateException();
    }
    // return message of ticket with highest priority in heap
    return this.array[0].toString();
  }

  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String closeNextTicket() {
    // if heap is empty throw exception
    if (this.size == 0) {
      throw new IllegalStateException();
    }
    // remove highest priority element in the heap
    SupportTicket remove = this.array[0];
    // add least important element to the root of the heap
    this.array[0] = this.array[this.size - 1];
    // remove element at the end of the heap
    this.array[this.size - 1] = null;
    // decrement size
    this.size -= 1;
    // compare the root to its children and swap elements with child that has a higher priority
    this.propagateDown(0);
    // return the element that was removed from the heap
    return remove.toString();
  }

  /**
   * Given an index into the heap array, this method returns that index's parent index.
   * 
   * @param index the index of the array that the method uses to calculate index of the parent
   * @return the index of the parent
   */
  protected static int parentOf(int index) {
    // index of parent is calculated with this formula
    return (index - 1) / 2;
  }

  /**
   * Given an index into the heap array, this method returns that index's left child index.
   * 
   * @param index the index of the array that is used to calculate its left child
   * @return index of left child
   */
  protected static int leftChildOf(int index) {
    // index of left child is calculated with this formula
    return index * 2 + 1;
  }

  /**
   * Given an index into the heap array, this method returns that index's right child index.
   * 
   * @param index the index of the array that is used to calculate its right child
   * @return index of right child
   */
  protected static int rightChildOf(int index) {
    return (index * 2) + 2;
  }

  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes.
   * 
   * @param indexA index of the array that you want to swap
   * @param indexB another index of the array that you want to swap
   */
  protected void swap(int indexA, int indexB) {
    // store ticket in indexA a temporary variable
    SupportTicket swapped = this.array[indexA];
    // store the the ticket in indexB at indexA
    this.array[indexA] = this.array[indexB];
    // then store ticket at temporary variable to indexB
    this.array[indexB] = swapped;
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and the heap's root.
   * 
   */

  protected void propagateUp(int index) {
    // stores the value returned by compareTo method when comparing the ticket at index to its
    // parent
    int comparison = this.array[index].compareTo(this.array[HelpDesk.parentOf(index)]);
    // if the ticket at index has a higher priority than its parent swap the ticket at index with
    // its parent
    if (comparison == 1) {
      this.swap(index, HelpDesk.parentOf(index));
      // then if the index is not zero compare the ticket that has been swapped to its parent index
      // to the parent index's parent
      if (index != 0) {
        this.propagateUp(HelpDesk.parentOf(index));
      }
    }
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and it's children.
   * 
   * @param index the index of the root node
   */

  protected void propagateDown(int index) {
    // stores the value returned by compareTo method when comparing the tickets at the index's right
    // and left child
    int comparison = 0;
    // if there is a right and left child compare the left child to the right child
    if (HelpDesk.leftChildOf(index) < this.size && HelpDesk.rightChildOf(index) < this.size) {
      comparison = this.array[HelpDesk.leftChildOf(index)]
          .compareTo(this.array[HelpDesk.rightChildOf(index)]);
    }
    // if the left child is greater and is a valid index
    if (comparison == 1 && HelpDesk.leftChildOf(index) < this.size) {
      // check if index if valid
      if (HelpDesk.leftChildOf(index) < this.size) {
        // swap the tickets at index and left child
        this.swap(index, HelpDesk.leftChildOf(index));
        // compare the ticket's of the new index's left and right children
        this.propagateDown(leftChildOf(index));
      }
    }
    // if the right child has a higher priority and has a valid index
    if (comparison == -1 && HelpDesk.rightChildOf(index) < this.size) {
      // check if right child index is valid
      if (HelpDesk.rightChildOf(index) < this.size) {
        // swap ticket at index with ticket at right child index
        this.swap(index, HelpDesk.rightChildOf(index));
        // compare the tickets of the new index's left and right children
        this.propagateDown(HelpDesk.rightChildOf(index));
      }
    }
    // if there is no right child
    if (comparison == 0 && HelpDesk.leftChildOf(index) < this.size) {
      // check if the root has a higher priority than the left child
      if (this.array[index].compareTo(this.array[HelpDesk.leftChildOf(index)]) == -1) {
        // if the left child has higher priority swap ticket in root with ticket in left child
        this.swap(index, HelpDesk.leftChildOf(index));
      }
    }
  }
}
