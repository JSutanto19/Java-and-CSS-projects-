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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * HashTable implementation that uses: chaining as my resolution
 * 
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 */
public class BookHashTable implements HashTableADT<String, Book> {

  // The initial capacity that is used if none is specified user
  static final int DEFAULT_CAPACITY = 101;

  // The load factor that is used if none is specified by user
  static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;
  // capacity of table
  private int capacity;
  // load factor threshold
  private double loadFactorThreshold;
  // number of keys or items in the hashTable
  private int numKeys;
  // the hashTable
  private ArrayList<LinkedList<Node>> hashTable;

  private class Node {
    String key;
    Book value;

    Node(String key, Book value) {
      this.key = key;
      this.value = value;
    }

  }

  /**
   * This constructor is a no argument constructor that uses the default constructor to initialze
   * loadFactorThreshold and capacity
   */
  public BookHashTable() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
  }

  /**
   * Creates an empty hash table with the specified capacity and load factor.
   * 
   * @param initialCapacity number of elements table should hold at start.
   * @param loadFactorThreshold the ratio of items/capacity that causes table to resize and rehash
   */
  public BookHashTable(int initialCapacity, double loadFactorThreshold) {
    // initialize all fields if capacity and loadFactorThreshold are greater than 0
    if (initialCapacity > 0 && loadFactorThreshold > 0) {
      // initialize capacity of hashTable
      this.capacity = initialCapacity;
      // initialize loadFactorThreshold
      this.loadFactorThreshold = loadFactorThreshold;
      //
      this.hashTable = new ArrayList<LinkedList<Node>>(this.capacity);
      //
      for (int i = 0; i < this.capacity; ++i) {
        hashTable.add(new LinkedList<Node>());
      }

    }
  }

  /**
   * This method inserts a book into hashTable
   * 
   * @param key the key of book that is deleted from hashTable
   * @throws IllegalNullKeyException when the key is null
   * @throws DuplicateKeyException when a book with the same key is in hashTable
   */
  @Override
  public void insert(String key, Book value) throws IllegalNullKeyException, DuplicateKeyException {
    // if key is null throw exception
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    // get loadFactor of hashTable
    double loadFactor = (double) this.numKeys / this.capacity;

    // Check if loadFactor is the same or greater than loadFactorThreshHold
    if (loadFactor >= this.loadFactorThreshold) {
      // call expand helper method
      this.hashTable = expandArrayList();
    }

    // find hashIndex using hashFunction
    int hashIndex = getHashKey(key);

    // check if LinkedList at hashIndex is empty
    if (this.hashTable.get(hashIndex).size() == 0) {
      // append book to LinkedList
      this.hashTable.get(hashIndex).add(new Node(key, value));
      // increment numKeys
      this.numKeys += 1;
    } else {
      // if list is not empty
      // save LinkedList at the hashIndex in a temporary variable
      LinkedList<Node> searchForDuplicate = this.hashTable.get(hashIndex);
      // check if book user wants to add is already in the linkedList
      for (int i = 0; i < searchForDuplicate.size(); ++i) {
        // check if any node in the list has the same key
        if (searchForDuplicate.get(i).key.equals(key)) {
          throw new DuplicateKeyException();
        }
      }
      // prepend book to list at hashIndex of hashTable
      this.hashTable.get(hashIndex).add(new Node(key, value));
      // increment numKeys
      this.numKeys += 1;
    }

  }

  /**
   * This method expands hashTable by capacity * 2 + 1 and changes capacity fields value to new
   * capacity
   * 
   * @return temp the expanded list
   */
  private ArrayList<LinkedList<Node>> expandArrayList() {
    // capacity of expanded arrayList
    int expandCapacity = this.capacity * 2 + 1;
    // variable saves new hashIndex in larger in array
    int rehash = 0;
    // create new hashTable that has new table size
    ArrayList<LinkedList<Node>> temp = new ArrayList<LinkedList<Node>>(expandCapacity);
    for (int i = 0; i < expandCapacity; ++i) {
      temp.add(new LinkedList<Node>());
    }

    // iterate through all indexes of smaller arrayList
    for (int i = 0; i < this.capacity; ++i) {
      // check if there are elements at hashTable index
      if (this.hashTable.get(i).size() != 0) {
        // copy of LinkedList in hashIndex
        LinkedList<Node> copyList = hashTable.get(i);
        for (int j = 0; j < copyList.size(); ++j) {
          // get every book in the LinkedList
          if (copyList.get(j) != null) {
            // rehash with new capacity every book
            rehash = Math.abs(copyList.get(j).key.hashCode() % expandCapacity);
            // save book that I want to insert to larger hashTable
            Node copy = copyList.get(j);
            // insert books in new hashIndex
            temp.get(rehash).add(copy);
          }
        }
      }
    }
    // multiply capacity by 2 and add 1 to make table size prime
    this.capacity = expandCapacity;
    // return the expanded list
    return temp;
  }

  /**
   * This method removes a book from hashTable if it is in hashTable
   * 
   * @param key the key of book that is deleted from hashTable
   * @throws IllegalNullKeyException when the key is null
   * @return true if book is remove is removed successfully otherwise return false
   */
  @Override
  public boolean remove(String key) throws IllegalNullKeyException {
    // if key is null throw exception
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // get hashIndex of key
    int hashIndex = getHashKey(key);
    // check if linkedList in the hasTable at hashIndex is not empty
    if (this.hashTable.get(hashIndex).size() != 0) {
      // save linked list in temporary variable
      LinkedList<Node> search = this.hashTable.get(hashIndex);
      for (int i = 0; i < search.size(); ++i) {
        // find if key is in LinkedList
        if (search.get(i).key.equals(key)) {
          // delete item from LinkedList
          search.remove(i);
          // decrement numKeys
          this.numKeys -= 1;
          // return true since book is deleted
          return true;
        }
      }
      return false;
    }
    // LinkedList at hashIndex is empty so book with key does not exist so return false
    return false;
  }

  /**
   * This method gets a book from hashTable if it is in hashTable
   * 
   * @param key the key of book that is deleted from hashTable
   * @throws IllegalNullKeyException when the key is null
   * @throws KeyNotFoundException when book with the same key is not in hashTable
   * @return the book that user wants to find
   */
  @Override
  public Book get(String key) throws IllegalNullKeyException, KeyNotFoundException {
    // check if key is null and if it is throw exception
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // get hashIndex of key
    int hashIndex = getHashKey(key);
    // check if hashTable and hashTable at hashIndex is null
    if (this.hashTable.get(hashIndex).size() != 0) {
      // save list in hashIndex in temporary variable
      LinkedList<Node> search = this.hashTable.get(hashIndex);
      // call get method in linked list to search for book with the same key
      for (int i = 0; i < search.size(); i++) {
        // check if every node in list has the same key
        // if there is a book with the same key return the book
        if (search.get(i).key.equals(key)) {
          return search.get(i).value;
        }
      }
    }
    // throw exception if key is not found
    throw new KeyNotFoundException();
  }

  /**
   * This method gets number of keys in hashTable
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * This method gets the load factor threshold of hashTable
   */
  @Override
  public double getLoadFactorThreshold() {
    return this.loadFactorThreshold;
  }

  /**
   * This method returns the capacity of the hashTable
   */
  @Override
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Gets the key and applies hashFunction to key and returns hashIndex
   * 
   * @param key the key of the object being inserted in the hashTable
   * @return the hashIndex
   */
  private int getHashKey(String key) {
    return Math.abs(key.hashCode() % this.capacity);
  }

  /**
   * This method returns the collision resolution scheme when a collision occurs
   */
  @Override
  public int getCollisionResolutionScheme() {
    return 5;
  }
}
