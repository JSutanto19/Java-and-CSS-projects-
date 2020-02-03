//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P3a
// Files: Book.java, BookHashTable.java, BookHashTableTest.java,BookParser.java
// Course: CS 400 Fall 2019
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu email address
// Lecturer's Name: Debra Deppler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NOILLGEN@WISC.EDU
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Filename: TestHashTableDeb.java Project: p3 Authors: Debra Deppeler (deppeler@cs.wisc.edu)
 * 
 * Semester: Fall 2018 Course: CS400
 * 
 * Due Date: before 10pm on 10/29 Version: 1.0
 * 
 * Credits: None so far
 * 
 * Bugs: TODO: add any known bugs, or unsolved problems here
 */

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test HashTable class implementation to ensure that required functionality works for all cases.
 */
public class BookHashTableTest {

  // Default name of books data file
  public static final String BOOKS = "books.csv";

  // Empty hash tables that can be used by tests
  static BookHashTable bookObject;
  static ArrayList<Book> bookTable;

  static final int INIT_CAPACITY = 2;
  static final double LOAD_FACTOR_THRESHOLD = 0.49;

  static Random RNG = new Random(0); // seeded to make results repeatable (deterministic)

  /** Create a large array of keys and matching values for use in any test */
  @BeforeAll
  public static void beforeClass() throws Exception {
    bookTable = BookParser.parse(BOOKS);
  }

  /** Initialize empty hash table to be used in each test */
  @BeforeEach
  public void setUp() throws Exception {
    // TODO: change HashTable for final solution
    bookObject = new BookHashTable(INIT_CAPACITY, LOAD_FACTOR_THRESHOLD);
  }

  /** Not much to do, just make sure that variables are reset */
  @AfterEach
  public void tearDown() throws Exception {
    bookObject = null;
    bookTable.clear();
  }

  private void insertMany(ArrayList<Book> bookTable)
      throws IllegalNullKeyException, DuplicateKeyException {
    for (int i = 0; i < bookTable.size(); i++) {
      bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
    }
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon initialization
   */
  @Test
  public void test000_collision_scheme() {
    // test if bookObject is initialized after setup
    if (bookObject == null)
      fail("Gg");
    // save value returned by getCollisionResolutionScheme()
    int scheme = bookObject.getCollisionResolutionScheme();
    // scheme should return a integer between 1-9
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }


  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon initialization
   */
  @Test
  public void test000_IsEmpty() {
    // "size with 0 entries:"
    assertEquals(0, bookObject.numKeys());
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is not empty after adding one (key,book)
   * pair
   * 
   * @throws DuplicateKeyException when inserting books with duplicate keys
   * @throws IllegalNullKeyException when passing null key to insert method
   */
  @Test
  public void test001_IsNotEmpty() throws IllegalNullKeyException, DuplicateKeyException {
    // add book to book table
    bookTable
        .add(new Book("1", "Jk Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    // insert first book in book table
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));

    String expected = "" + 1;
    // "size with one entry:"
    assertEquals(expected, "" + bookObject.numKeys());
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Test if the hash table will be resized after adding two
   * (key,book) pairs given the load factor is 0.49 and initial capacity to be 2.
   */

  @Test
  public void test002_Resize() throws IllegalNullKeyException, DuplicateKeyException {
    // add two books to bookTable
    bookTable
        .add(new Book("1", "Jk Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    bookTable.add(new Book("2", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
    // insert first book in bookTable to hashTable and save capacity
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    int cap1 = bookObject.getCapacity();
    // insert second book and save capacity
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    int cap2 = bookObject.getCapacity();
    // compare capacities to see if expand worked
    assertTrue(cap2 > cap1 & cap1 == 2);
  }

  /**
   * This method tests if inserting duplicates will throw a DuplicateKeyException
   * 
   * @throws IllegalNullKeyException when key is null
   */
  @Test
  public void test003_insert_duplicate() throws IllegalNullKeyException {
    try {
      // add two of the same books to bookTable
      bookTable.add(
          new Book("1", "Jk Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
      // add two of the same books to hashTable
      bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
      bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
      // fails test because method should throw exception
      fail("method did not throw exception");
    } catch (DuplicateKeyException e) {
      // expected
    }
  }

  /**
   * This method test if inserting a book with null key throws exception
   * 
   * @throws DuplicateKeyException if you are adding two of the same books
   */
  @Test
  public void test004_insert_nullKey() throws DuplicateKeyException {
    try {
      // add book with null key to bookTable
      bookTable.add(
          new Book(null, "Jk Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
      // add book with null key to hashTable
      bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
      // test fails because key should throw exception
      fail("method should throw exception because key is null");
    } catch (IllegalNullKeyException e) {
      // expected
    }
  }

  /**
   * This method tests if adding and removing the same book won't cause DuplicateKeyException
   * 
   * @throws IllegalArguementException if key is null
   */
  @Test
  public void test005_insert_remove_same_book() throws IllegalNullKeyException {
    try {
      // add two books to bookTable
      bookTable.add(
          new Book("1", "J K Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
      bookTable.add(new Book("2", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
      // insert two books to hashTable
      bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
      bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
      // remove firstBook in hashTable
      bookObject.remove("1");
      // insert first book in hashTable again
      bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    } catch (DuplicateKeyException e) {
      // test fail because method should not throw DuplicateKeyException
      fail("Should not throw exception since book was removed and added again");
    }
  }

  /**
   * This method tests if remove throws IllegalNullKkeyException null is passed into the method
   */
  @Test
  public void test006_remove_null_key() {
    try {
      // pass null into method
      bookObject.remove(null);
      // fails if exception is not thrown
      fail("method should throw exception since key passed in is null");
    } catch (IllegalNullKeyException e) {
      // expected
    }

  }

  /**
   * This method tests if remove, removes the correct items in the hashTable
   * 
   * @throws IllegalNullKeyException when key is null
   * @throws DuplicateKeyException when inserting books with same key
   * @throws KeyNotFoundException when key isn't found
   */
  @Test
  public void test007_remove_items()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // add five books to bookTable
    bookTable.add(
        new Book("1", "J K Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    bookTable.add(new Book("6", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
    bookTable
        .add(new Book("11", "George Lucas", "1971", "Star Wars", "12", "9", "hard-cover", "250"));
    bookTable.add(new Book("2", "Stan Lee", "1990", "Avengers", "12", "10", "hard-cover", "80"));
    bookTable.add(new Book("3", "Stan Lee", "1985", "Spiderman", "12", "10", "hard-cover", "90"));
    // insert all books to hashTable
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

    // remove object in the same chain or hashIndex
    bookObject.remove("1");

    // check if book was removed
    if (bookObject.numKeys() != 4) {
      fail("book was not removed");
    }
    // remove book in different hashIndex
    bookObject.remove("2");

    // check if book was removed
    if (bookObject.numKeys() != 3) {
      fail("book was not removed");
    }

  }

  /**
   * This method tests that all books are inserted into the right hashIndex
   * 
   * @throws DuplicateKeyException when adding books with duplicate keys
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test009_insert_multiple_books()
      throws IllegalNullKeyException, DuplicateKeyException {
    // add five books to bookTable
    bookTable.add(
        new Book("1", "J K Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    bookTable.add(new Book("6", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
    bookTable
        .add(new Book("11", "George Lucas", "1971", "Star Wars", "12", "9", "hard-cover", "250"));
    bookTable.add(new Book("2", "Stan Lee", "1990", "Avengers", "12", "10", "hard-cover", "80"));
    bookTable.add(new Book("3", "Stan Lee", "1985", "Spiderman", "12", "10", "hard-cover", "90"));
    // insert all books to bookTable
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));

    // Check if all items were added
    if (bookObject.numKeys() != 4) {
      fail("should add 4 items to table");
    }
    // ensures insert resizes hashTable when loadFactor Threshold is reached
    if (bookObject.getCapacity() != 11) {
      fail("should change size to (2 * size) + 1");
    }
  }

  /**
   * This method checks the functionality of get and checks if get returns correct books and tests
   * that it throws keyNotFoundException when trying to get a book that is not inside the table
   * 
   * @throws DuplicateKeyException when trying to add book with duplicate key
   * @throws KeyNotFoundException when key is not found
   * @throws IllegalNullKeyException when key passed in is null
   */
  @Test
  public void test10_get_method()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // add five books to bookTable
    bookTable.add(
        new Book("1", "J K Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    bookTable.add(new Book("6", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
    bookTable
        .add(new Book("11", "George Lucas", "1971", "Star Wars", "12", "9", "hard-cover", "250"));
    bookTable.add(new Book("2", "Stan Lee", "1990", "Avengers", "12", "10", "hard-cover", "80"));
    bookTable.add(new Book("3", "Stan Lee", "1985", "Spiderman", "12", "10", "hard-cover", "90"));
    bookTable.add(new Book("9780061129740.0", "The Art of Loving", "Erich Fromm",
        "Peter D. Kramer   Rainer Funk", "1956.0", "eng", "1", "944"));
    // insert all books to bookTable
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));
    try {
      // get book with 4 as the key
      bookObject.get("4");
      // fail because exception is not thrown
      fail("Should throw KeyNotFoundException");
    } catch (KeyNotFoundException e) {
      // expected
    }
    // save book returned by get method
    Book b1 = bookObject.get("9780061129740.0");
    // check if b1 is the correct book returned
    if (!b1.getKey().equals("9780061129740.0")) {
      fail("returned wrong child ");
    }
  }

  /**
   * This method tests if get method returns IllegalNullKeyException when null is passed into method
   * 
   * @throws KeyNotFoundException when key that user wants to get from hashTable is not found
   * @throws IllegalNullKeyException when null key is passed into method
   */
  @Test
  public void test011_get_throws_IllegalNullKeyException() throws KeyNotFoundException {
    try {
      // pass null into get method
      bookObject.get(null);
      // test fails because method did not throw exception
      fail("get method should throw IllegallNullKeyException");
    } catch (IllegalNullKeyException e) {
      // expected
    }
  }

  /**
   * This method tests if inserting items into hashTable returns right number of keys
   * 
   * @throws IllegalNullKeyException when key passed in is null
   * @throws DuplicateKeyException when duplicate key is being added
   */
  @Test
  public void test013_correct_number_of_keys_after_insert()
      throws IllegalNullKeyException, DuplicateKeyException {
    // add five books to bookTable
    bookTable.add(
        new Book("1", "J K Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    bookTable.add(new Book("6", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
    bookTable
        .add(new Book("11", "George Lucas", "1971", "Star Wars", "12", "9", "hard-cover", "250"));
    bookTable.add(new Book("2", "Stan Lee", "1990", "Avengers", "12", "10", "hard-cover", "80"));
    bookTable.add(new Book("3", "Stan Lee", "1985", "Spiderman", "12", "10", "hard-cover", "90"));
    // insert all books to bookTable
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
    // there should be five keys after all inserts
    if (bookObject.numKeys() != 5) {
      fail("number of keys should return 5");
    }
  }

  /**
   * This method tests that node class stores the key at a certain hashIndex instead of using the
   * book's key
   * 
   * @throws DuplicateKeyException when inserting duplicate books in
   * @throws IllegalNullKeyException when null is passed into insert method
   * @throws KeyNotFoundException when node with the same key is not found
   * 
   */
  @Test
  public void test014_shared_value()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // add 3 books to bookTable
    bookTable.add(
        new Book("1", "J K Rowling", "1969", "Harry Potter", "12", "3.5", "hard-cover", "200"));
    bookTable.add(new Book("11", "CS Lewis", "1970", "Narnia", "12", "5", "soft-cover", "300"));
    bookTable
        .add(new Book("23", "George Lucas", "1971", "Star Wars", "12", "9", "hard-cover", "250"));
    // insert all books to bookObject
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

    // get book with key 11
    if (!bookObject.get("11").equals(bookTable.get(2))) {
      fail("method fails");
    }
    // get book with key 23
    if (!bookObject.get("23").equals(bookTable.get(2))) {
      fail("method fails");
    }
  }
}
