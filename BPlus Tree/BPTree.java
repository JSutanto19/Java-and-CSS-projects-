/**
 * Implementation of BPlus Tree
 * 
 * @author Jason Sutanto (jsutanto2@wisc.edu)
 */
import java.util.ArrayList;

import java.util.Arrays;

import java.util.Collections;

import java.util.Iterator;

import java.util.LinkedList;

import java.util.List;

import java.util.Queue;

import java.util.Random;



/**
 * 
 * Implementation of a B+ tree to allow efficient access to many different indexes of a large data
 * 
 * set. BPTree objects are created for each type of index needed by the program. BPTrees provide an
 * 
 * efficient range search as compared to other types of data structures due to the ability to
 * 
 * perform log_m N lookups and linear in-order traversals of the data items.
 * 
 * 
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * 
 * 
 * @param <K> key - expect a string that is the type of id for each item
 * 
 * @param <V> value - expect a user-defined type that stores all data for a food item
 * 
 */

public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {



  // Root of the tree

  private Node root;

  // size of tree

  private int size;



  // Branching factor is the number of children nodes

  // for internal nodes of the tree

  private int branchingFactor;



  /**
   * 
   * Constructor of BPlusTree
   * 
   * 
   * 
   * @param branchingFactor
   * 
   */

  public BPTree(int branchingFactor) {

    if (branchingFactor <= 2) {

      throw new IllegalArgumentException("Illegal branching factor: " + branchingFactor);

    } else {

      this.branchingFactor = branchingFactor;

    }

    this.root = new LeafNode();

  }



  /**
   * Inserts the key and value in the appropriate nodes in the tree If the key is null, throw
   * IllegalArgumentException
   * 
   * Note: key-value pairs with duplicate keys can be inserted into the tree.
   * 
   * @param key
   * @param value
   */

  @Override

  public void insert(K key, V value) throws IllegalArgumentException {

    if (key == null) {

      throw new IllegalArgumentException();

    }

    this.size += 1;

    root.insert(key, value);

  }



  /**
   * Gets the values that satisfy the given range search arguments.
   * 
   * Value of comparator can be one of these: "<=", "==", ">="
   * 
   * Example: If given key = 2.5 and comparator = ">=": return all the values with the corresponding
   * keys >= 2.5
   * 
   * If key is null or not found, return empty list. If comparator is null, empty, or not according
   * to required form, return empty list.
   * 
   * @param key to be searched
   * @param comparator is a string
   * @return list of values that are the result of the range search; if nothing found, return empty
   *         list
   */

  @Override

  public List<V> rangeSearch(K key, String comparator) {

    return root.rangeSearch(key, comparator);

  }



  /**
   * Returns the value of the first leaf with a matching key. If key is null, return null. If key is
   * not found, return null.
   *
   * @param key to find
   * @return value of the first leaf matching key
   */

  @Override

  public V get(K key) {

    // check if value is in the

    return root.getValue(key);

  }



  /**
   * Return the number of leaves in the tree.
   *
   * @return number of leaves
   */

  @Override

  public int size() {

    // TODO : Complete

    return this.size;

  }



  /**
   * Returns a string representation for the tree This method is provided to students in the
   * implementation.
   * 
   * @return a string representation
   */

  @Override

  public String toString() {

    Queue<List<Node>> queue = new LinkedList<List<Node>>();

    queue.add(Arrays.asList(root));

    StringBuilder sb = new StringBuilder();

    while (!queue.isEmpty()) {

      Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();

      while (!queue.isEmpty()) {

        List<Node> nodes = queue.remove();

        sb.append('{');

        Iterator<Node> it = nodes.iterator();

        while (it.hasNext()) {

          Node node = it.next();

          sb.append(node.toString());

          if (it.hasNext())

            sb.append(", ");

          if (node instanceof BPTree.InternalNode)

            nextQueue.add(((InternalNode) node).children);

        }

        sb.append('}');

        if (!queue.isEmpty())

          sb.append(", ");

        else {

          sb.append('\n');

        }

      }

      queue = nextQueue;

    }

    return sb.toString();

  }



  /**
   * 
   * This abstract class represents any type of node in the tree This class is a super class of the
   * 
   * LeafNode and InternalNode types.
   * 
   * 
   * 
   * @author sapan
   * 
   */

  private abstract class Node {



    // List of keys

    List<K> keys;



    /**
     * 
     * constructor for node in BPlus Tree
     * 
     */

    Node() {

      keys = new ArrayList<K>();

    }


    /**
     * returns size of keys list of a node
     * 
     * @return size of node's key list
     */
    int numKeys() {

      return keys.size();

    }


    /**
     * gets a value of specified key
     * 
     * @param firstLeafKey go to first leaf node
     * @return value of specified key
     */
    abstract V getValue(K firstLeafKey);



    /**
     * 
     * Inserts key and value in the appropriate leaf node and balances the tree if required by
     * 
     * splitting
     * 
     * 
     * 
     * @param key
     * 
     * @param value
     * 
     */

    abstract void insert(K key, V value);



    /**
     * 
     * Gets the first leaf key of the tree
     * 
     * 
     * 
     * @return key
     * 
     */

    abstract K getFirstLeafKey();



    /**
     * 
     * Gets the new sibling created after splitting the node
     * 
     * 
     * 
     * @return Node
     * 
     */

    abstract Node split();



    /*
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree#rangeSearch(java.lang.Object, java.lang.String)
     * 
     */

    abstract List<V> rangeSearch(K key, String comparator);



    /**
     * Checks if children or leaf nodes have to split
     * 
     * 
     * @return boolean indicates if leaf or internal node is full
     * 
     */

    abstract boolean isOverflow();


    /**
     * Returns string representation of a node's keys
     */
    public String toString() {

      return keys.toString();

    }



  } // End of abstract class Node



  /**
   * 
   * This class represents an internal node of the tree. This class is a concrete sub class of the
   * 
   * abstract Node class and provides implementation of the operations required for internal
   * 
   * (non-leaf) nodes.
   * 
   * 
   * 
   * @author sapan
   * 
   */

  private class InternalNode extends Node {



    // List of children nodes

    List<Node> children;
    // parent of child nodes
    Node parent;



    /**
     * 
     * Package constructor
     * 
     */

    InternalNode() {

      this.keys = new ArrayList<K>();

      this.children = new ArrayList<Node>();

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#getFirstLeafKey()
     * 
     */

    K getFirstLeafKey() {

      return children.get(0).getFirstLeafKey();

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#isOverflow()
     * 
     */

    boolean isOverflow() {

      // check if overflow

      return this.children.size() > branchingFactor;

    }



    void insertToChildList(K key, Node child) {

      int insertionIndex = Collections.binarySearch(keys, key);

      int childIndex = 0;

      if (insertionIndex >= 0) {
        childIndex = insertionIndex + 1;
      } else {
        childIndex = -insertionIndex - 1;
      }

      if (insertionIndex >= 0) {

        keys.add(childIndex, key);
        children.add(childIndex + 1, child);

      } else {

        keys.add(childIndex, key);

        children.add(childIndex + 1, child);

      }

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#insert(java.lang.Comparable, java.lang.Object)
     * 
     */

    void insert(K key, V value) {



      Node child = getChild(key);

      child.insert(key, value);

      if (child.isOverflow()) {

        Node sibling = child.split();

        insertToChildList(sibling.getFirstLeafKey(), sibling);

      }

      if (root.isOverflow()) {

        Node sibling = split();

        InternalNode rootAfterSplit = new InternalNode();

        rootAfterSplit.keys.add(sibling.getFirstLeafKey());

        rootAfterSplit.children.add(this);

        rootAfterSplit.children.add(sibling);

        root = rootAfterSplit;

      }



    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#split()
     * 
     */

    Node split() {

      int copyFrom = numKeys();
      copyFrom /= 2;
      copyFrom += 1;

      int copyTo = numKeys();

      InternalNode sibling = new InternalNode();

      sibling.keys.addAll(keys.subList(copyFrom, copyTo));

      sibling.children.addAll(children.subList(copyFrom, copyTo + 1));



      keys.subList(copyFrom - 1, copyTo).clear();

      children.subList(copyFrom, copyTo + 1).clear();



      return sibling;

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#rangeSearch(java.lang.Comparable, java.lang.String)
     * 
     */

    List<V> rangeSearch(K key, String comparator) {

      // recurse to leaf node and get range

      return getChild(key).rangeSearch(key, comparator);



    }



    /**
    
     * 
    
     */

    V getValue(K key) {
      return getChild(key).getValue(key);

    }



    Node getChild(K key) {

      int loc = Collections.binarySearch(keys, key);

      int childIndex = 0;

      if (loc >= 0) {
        childIndex = loc + 1;
      } else {
        childIndex = -loc - 1;
      }


      return children.get(childIndex);

    }



  } // End of class InternalNode



  /**
   * 
   * This class represents a leaf node of the tree. This class is a concrete sub class of the
   * 
   * abstract Node class and provides implementation of the operations that required for leaf nodes.
   * 
   * 
   * 
   * @author sapan
   * 
   */

  private class LeafNode extends Node {



    // List of values

    List<V> values;



    // Reference to the next leaf node

    LeafNode next;



    // Reference to the previous leaf node

    LeafNode previous;



    /**
     * 
     * initialize all fields in internal node
     * 
     */

    LeafNode() {

      keys = new ArrayList<K>();

      values = new ArrayList<V>();

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#getFirstLeafKey()
     * 
     */

    K getFirstLeafKey() {

      if (keys.size() != 0)

        return keys.get(0);

      else {

        return null;

      }

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#isOverflow()
     * 
     */

    boolean isOverflow() {

      return values.size() > branchingFactor;

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#insert(Comparable, Object)
     * 
     */

    void insert(K key, V value) {

      int index = Collections.binarySearch(keys, key);

      int valueIndex = 0;
      if (index >= 0) {
        valueIndex = index;
      } else {
        valueIndex = -index - 1;
      }

      if (index >= 0) {
        keys.add(valueIndex + 1, key);
        values.add(valueIndex + 1, value);

      } else {

        keys.add(valueIndex, key);

        values.add(valueIndex, value);

      }

      if (root.isOverflow()) {

        Node rightSibling = split();

        InternalNode rootAfterSplit = new InternalNode();

        rootAfterSplit.keys.add(rightSibling.getFirstLeafKey());

        rootAfterSplit.children.add(this);

        rootAfterSplit.children.add(rightSibling);

        root = rootAfterSplit;

      }

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#split()
     * 
     */

    Node split() {

      LeafNode right = new LeafNode();

      int copyFrom = numKeys();
      copyFrom++;
      copyFrom /= 2;

      int copyTo = numKeys();

      right.keys.addAll(keys.subList(copyFrom, copyTo));

      right.values.addAll(values.subList(copyFrom, copyTo));



      this.keys.subList(copyFrom, copyTo).clear();

      this.values.subList(copyFrom, copyTo).clear();



      right.next = next;

      next = right;

      if (right.next != null) {

        right.next.previous = right;

      }

      right.previous = this;

      return right;

    }



    /**
     * 
     * (non-Javadoc)
     * 
     * 
     * 
     * @see BPTree.Node#rangeSearch(Comparable, String)
     * 
     */

    List<V> rangeSearch(K key, String comparator) {

      // List that stores range of values

      List<V> range = new LinkedList<V>();

      // pointer to current node

      LeafNode current = this;


       while (current.previous != null) {
       current = current.previous;
       }
      
       while (current != null) {
      
       // iterate through current node's key's list and value list with iterators
      
       Iterator<K> keysListIterator = current.keys.iterator();
      
       Iterator<V> valueListIterator = current.values.iterator();
      
       while (keysListIterator.hasNext()) {
      
       // store key and value in
      
       V v1 = valueListIterator.next();
      
       K k1 = keysListIterator.next();
      
       // compare key in current nodes key list to bounded key
      
       // if comparator and comparison match add to range list
      
       int compare = k1.compareTo(key);
      
       if (comparator.equals(">=") && compare >= 0) {
      
       range.add(v1);
      
       } else if (comparator.equals("<=") && compare <= 0) {
      
       range.add(v1);
      
       } else if (comparator.equals("==") && compare == 0) {
      
       range.add(v1);
      
       }
      
       }
      
       // go to next key in list
      
       current = current.next;
      
       }
      
       // return range list
      
       return range;

    }



    V getValue(K key) {

      int locationOfNode = Collections.binarySearch(keys, key);

      if (locationOfNode >= 0) {

        return values.get(locationOfNode);

      } else {

        return null;

      }



    }



  } // End of class LeafNode



  /**
   * 
   * Contains a basic test scenario for a BPTree instance. It shows a simple example of the use of
   * 
   * this class and its related types.
   * 
   * 
   * 
   * @param args
   * 
   */

  public static void main(String[] args) {

    // create empty BPTree with branching factor of 3

    BPTree<Double, Double> bpTree = new BPTree<>(3);



    // create a pseudo random number generator

    Random rnd1 = new Random();



    // some value to add to the BPTree

    Double[] dd = {0.0d, 0.5d, 0.2d, 0.8d};



    // build an ArrayList of those value and add to BPTree also

    // allows for comparing the contents of the ArrayList

    // against the contents and functionality of the BPTree

    // does not ensure BPTree is implemented correctly

    // just that it functions as a data structure with

    // insert, rangeSearch, and toString() working.


    bpTree.insert(0.0, 0.0);
    bpTree.insert(0.1, 0.1);
    bpTree.insert(0.2, 0.2);
    bpTree.insert(0.3, 0.3);
    bpTree.insert(0.4, 0.4);
    bpTree.insert(0.15, 0.15);
    bpTree.insert(0.15, 0.15);
    bpTree.insert(0.5, 0.5);

    // List<Double> list = new ArrayList<>();
    //
    // for (int i = 0; i < 800; i++) {
    //
    // Double j = dd[rnd1.nextInt(4)];
    //
    // list.add(j);
    //
    // bpTree.insert(j, j);
    //
    System.out.println("\n\nTree structure:\n" + bpTree.toString());
    //
    // }

    List<Double> filteredValues = bpTree.rangeSearch(0.2d, ">=");

    System.out.println("Filtered values: " + filteredValues.toString());



    // for (int j = 0; j < 200; ++j) {
    //
    // Double k = dd[rnd1.nextInt(4)];
    //
    // System.out.print("path " + bpTree.get(k).toString());
    //
    // }

  }



}// End of class BPTree
