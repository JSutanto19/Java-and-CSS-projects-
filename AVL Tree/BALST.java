//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P2
// Files: BALST.java, BSTNode.java
// Course: CS 400 Fall 2019
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu email address
// Lecturer's Name: Debra
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
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * 
 * Class that implements avl tree
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

  private BSTNode<K, V> root;
  private int numKeys;

  public BALST() {

  }

  @Override
  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  public List<K> getPreOrderTraversal() {
    // make list for allNodes
    List<K> allNodes = new ArrayList<K>();
    if (root == null)
      return allNodes;
    // call helper
    preOrderHelper(root, allNodes);
    // return list
    return allNodes;
  }

  /**
   * this method returns a list of key in preOrder format
   * 
   * @param currentNode is the starting node to recurse, root
   * @param nodeList is the List which will store the key values in preOrder
   */
  public void preOrderHelper(BSTNode<K, V> currentNode, List<K> allNodes) {
    // throw exception if node passed in is null
    if (currentNode == null) {
      return;
    }
    allNodes.add(currentNode.key);
    preOrderHelper(currentNode.left, allNodes);
    preOrderHelper(currentNode.right, allNodes);
  }

  @Override
  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  public List<K> getPostOrderTraversal() {
    List<K> allNodes = new ArrayList<K>();
    if (root == null)
      return allNodes;
    postOrderHelper(root, allNodes);
    return allNodes;
  }

  /**
   * this method returns a list of key in postOrder format
   * 
   * @param currentNode is the starting node to recurse, root
   * @param allNodes is the List which will store the key values in postOrder
   */
  protected void postOrderHelper(BSTNode<K, V> currentNode, List<K> allNodes) {

    if (currentNode == null) {
      return;
    }

    postOrderHelper(currentNode.left, allNodes);

    postOrderHelper(currentNode.right, allNodes);

    allNodes.add(currentNode.key);
  }

  @Override
  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  public List<K> getLevelOrderTraversal() {
    List<K> allNodesInTree = new ArrayList<K>();
    if (root == null) {
      return allNodesInTree;
    }

    for (int i = 1; i <= getHeight(); i++) {
      levelOrderHelper(root, i, allNodesInTree);
    }
    return allNodesInTree;
  }

  /**
   * Helper method for the levelOrderTraversal which recursively calls the method to add the first
   * node, then traverse down a level and keep adding the nodes from left to right
   * 
   * @param allNodes is the starting node to recurse, root
   * @param listOfAllNodes is the List which will store the key values in preOrder
   * @param level is the level the current node is on in the BST
   */
  private void levelOrderHelper(BSTNode<K, V> allNodes, int level, List<K> listOfAllNodes) {
    if (allNodes == null) {
      return;
    }
    if (level == 1) {
      listOfAllNodes.add(allNodes.key);
    } else if (level > 1) {
      levelOrderHelper(allNodes.left, level - 1, listOfAllNodes);
      levelOrderHelper(allNodes.right, level - 1, listOfAllNodes);
    }
  }

  @Override
  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  public List<K> getInOrderTraversal() {
    List<K> listOfAllNodes = new ArrayList<K>();
    if (root == null) {
      return listOfAllNodes;
    }
    inOrderHelper(root, listOfAllNodes);
    return listOfAllNodes;
  }

  /**
   * Helper method for the inOrderTraversal which recursively calls the method to recurse left, add
   * the node, then recurse right
   * 
   * @param node is the starting node to recurse, root
   * @param nodeList is the List which will store the key values in preOrder
   */
  protected void inOrderHelper(BSTNode<K, V> node, List<K> nodeList) {
    if (node == null) {
      return;
    }
    inOrderHelper(node.left, nodeList);
    nodeList.add(node.key);
    inOrderHelper(node.right, nodeList);
  }



  @Override
  /**
   * Returns the value associated with the specified key
   * 
   * @param key is the desired key to be found
   * @return the value of the desired node to be found
   * @throws IllegalNullKeyException if the key given is null
   * @throws KeyNotFoundException if the key does not exist in the tree
   */
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // throw exception if key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // call helper
    return getHelper(root, key).value;
  }

  /**
   * This helper method finds node in tree with the same key
   * 
   * @param root point where search starts
   * @param key is the desired key to be found
   * @return node that is found in BST
   * @throws KeyNotFoundException if the node is not in the tree
   */
  private BSTNode<K, V> getHelper(BSTNode<K, V> currentNode, K key) throws KeyNotFoundException {
    // throw exception if key if not null
    if (currentNode == null) {
      throw new KeyNotFoundException();
    }
    // key is the same return node
    if (currentNode.key.compareTo(key) == 0) {
      return currentNode;
    } else if (currentNode.key.compareTo(key) > 0) {
      // key is is smaller than nodes key recurse to left child
      return getHelper(currentNode.left, key);
    } else if (currentNode.key.compareTo(key) < 0) {
      // recurse to right child
      return getHelper(currentNode.right, key);
    }
    throw new KeyNotFoundException();
  }

  @Override
  /**
   * This method checks if there is a node with the same key
   * 
   * @param key is the desired key to be found in the tree
   * @returns true if it is found, false if not
   * @throws IllegalNullKeyException if the given key is null
   */
  public boolean contains(K key) throws IllegalNullKeyException {
    // throw exception if key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // call contains helper
    if (containsHelper(root, key) == null) {
      return false;
    }
    return true;
  }

  /**
   * This method traverses through the tree to find the node with same key
   * 
   * @param currentNode is the starting point
   * @param key the node with key that user wants to find
   * @return the node if there is a node with the same key
   */
  protected BSTNode<K, V> containsHelper(BSTNode<K, V> currentNode, K key) {
    // return null when key is not found
    if (currentNode == null) {
      return null;
    }
    // if node with same key is found return 0
    if (currentNode.key.compareTo(key) == 0) {
      return currentNode;
    } else if (currentNode.key.compareTo(key) > 0) {
      // recurse to left child
      return containsHelper(currentNode.left, key);
    } else {
      // recures to right child
      return containsHelper(currentNode.right, key);
    }
  }

  @Override
  /**
   * Number of keys in the BST
   * 
   * @return the number of keys
   */
  public int numKeys() {
    return numKeys;
  }

  @Override
  /**
   * Gives the starting key
   * 
   * @return the root key
   */
  public K getKeyAtRoot() {
    return root.key;
  }

  @Override
  /**
   * Get the left Child of node with given tree
   * 
   * @param key is the parent key
   * @return the left child's key given the parent
   * @throw IllegalNullKeyException if the given key is null
   * @throw KeyNotFoundException if the key is not in tree
   */
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // throw exception if key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // throw exception if key is not found
    if (contains(key) == false) {
      throw new KeyNotFoundException();
    }
    // save first node in variable
    BSTNode<K, V> tempNode = root;
    // search tree for node with given key
    tempNode = containsHelper(root, key);
    // return left child of node
    return tempNode.left.key;
  }

  @Override
  /**
   * this method gets the right child of the given key
   * 
   * @param key is the key of parent
   * @return the right child's key given to the given parent
   * @throw IllegalNullKeyException if the given key is null
   * @throw KeyNotFoundException if the no node has the same key
   */
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // if key is null throw exception
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // if key is not found throw exception
    if (contains(key) == false) {
      throw new KeyNotFoundException();
    }
    // find node in tree and return its left child
    BSTNode<K, V> tempNode = root;
    tempNode = containsHelper(root, key);
    return tempNode.right.key;
  }

  @Override
  /**
   * gives the height of the tree
   * 
   * @return the tree's height
   */
  public int getHeight() {
    return getHeightHelper(root);
  }

  /**
   * this method returns height of tree
   * 
   * @param root is the starting point of search
   * @return the height or longest path from root to leaf
   */
  protected int getHeightHelper(BSTNode<K, V> root) {
    // if tree is empty return null
    if (root == null) {
      return 0;
    } else if (root.right == null && root.left == null) {
      // if there is only a root return 1
      return 1;
    } else {
      // get height of left subtree and right subtree
      int lHeight = getHeightHelper(root.left);
      int rHeight = getHeightHelper(root.right);
      // return the greater height + 1
      if (lHeight > rHeight) {
        return (lHeight + 1);
      } else {
        return (rHeight + 1);
      }
    }
  }

  /**
   * Finds balance factor of given node
   * 
   * @param currentNode find height balance of this node
   * @return the height balance of the node
   */
  public int getNodeHeight(BSTNode<K, V> currentNode) {
    return getHeightHelper(currentNode.left) - getHeightHelper(currentNode.right);
  }

  /**
   * This method does a right rotate to given node to maintain balance
   * 
   * @param node the node you want to rotate
   * @return reference of rotated nodes
   */
  private BSTNode<K, V> rightRotate(BSTNode<K, V> node) {
    // save node you want to rotates left child in temporary variable
    BSTNode<K, V> swap = node.left;
    // save right child of node's right child
    BSTNode<K, V> right = swap.right;
    // do right rotate
    swap.right = node;
    node.left = right;
    return swap;
  }

  /**
   * This method does a left rotate to preserve trees balance
   * 
   * @param node the node that you want to rotate
   * @return reference of rotated nodes
   */
  private BSTNode<K, V> leftRotate(BSTNode<K, V> node) {
    // save node's right child
    BSTNode<K, V> swap = node.right;
    // save node's right child's left child
    BSTNode<K, V> swapLeft = swap.left;
    // do left rotate
    swap.left = node;
    node.right = swapLeft;
    return swap;
  }

  @Override
  /**
   * This method inserts node into tree
   * 
   * @throws IllegalNullKeyException when key is null
   * @throws DuplicateKeyException when node with duplicate key is found
   */
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // throw exception
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // add node to root
    root = insertHelper(root, key, value);
    // add number of keys in tree
    numKeys++;
  }

  /**
   * this method ensures that tree remains balance after insertion
   * 
   * @param currentNode the node that is inserted
   * @param key key of node
   * @param value the data that each node stores
   * @return the reference of nodes added to tree
   * @throws IllegalNullKeyException when key is null
   * @throws DuplicateKeyException when duplicate key is found
   */
  private BSTNode<K, V> insertHelper(BSTNode<K, V> currentNode, K key, V value)
      throws IllegalNullKeyException, DuplicateKeyException {
    // throw exception
    if (key == null)
      throw new IllegalNullKeyException();
    //
    if (currentNode == null) {
      return new BSTNode<K, V>(key, value);
    }
    if (currentNode.key.compareTo(key) == 0) {
      throw new DuplicateKeyException();
    }
    if (currentNode.key.compareTo(key) > 0) {
      currentNode.left = insertHelper(currentNode.left, key, value);
    } else if (currentNode.key.compareTo(key) < 0) {
      currentNode.right = insertHelper(currentNode.right, key, value);
    }

    return balanceHelperInsert(currentNode, key);

  }

  /**
   * remove node with given key in tree
   * 
   * @param key of node you want to pass in
   * @throws IllegalNullKeyException if key is null throw exception
   * @throws KeyNotFoundException if key is not found throw exception
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (contains(key) == false) {
      throw new KeyNotFoundException();
    }
    root = removeHelper(root, key);
    numKeys--;
    if (contains(key) == true)
      return false;
    else
      return true;
  }

  /**
   * maintan balance of tree after remove
   * 
   * @param node the node that is removed
   * @param key the key of node
   * @return reference of tree after node is removed
   */
  private BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key) {
    if (node == null) {
      return node;
    }
    int comparison = node.key.compareTo(key);
    if (comparison > 0) {
      node.left = removeHelper(node.left, key);
    } else if (comparison < 0) {
      node.right = removeHelper(node.right, key);
    } else {
      if (node.right == null && node.left == null)
        return null;
      else if (node.left == null)
        return node.right;
      else if (node.right == null)
        return node.left;
      node.key = minVal(node.right).key;
      node.value = minVal(node.right).value;
      node.right = removeHelper(node.right, node.key);
    }
    return balanceHelperRemove(node);
  }

  /**
   * This method recursively traverses left down the tree to find the smallest value given a node
   * 
   * @param startNode is the starting place to traverse down
   * @return the smallest node given the param
   */
  private BSTNode<K, V> minVal(BSTNode<K, V> startNode) {
    if (startNode.left == null) {
      return startNode;
    }
    return minVal(startNode.left);
  }

  private BSTNode<K, V> balanceHelperRemove(BSTNode<K, V> node) {
    int balanceFactor = getNodeHeight(node);
    if (balanceFactor > 1 && getNodeHeight(node.left) >= 0)
      return rightRotate(node);
    else if (balanceFactor < -1 && getNodeHeight(node.right) <= 0)
      return leftRotate(node);
    else if (balanceFactor > 1 && getNodeHeight(node.left) < 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    } else if (balanceFactor < -1 && getNodeHeight(node.right) > 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    return node;
  }

  /**
   * maintains balance of insert with rotations
   * 
   * @param node the root
   * @param key the key of given root
   * @return
   */
  private BSTNode<K, V> balanceHelperInsert(BSTNode<K, V> node, K key) {
    int heightBalance = getNodeHeight(node);
    if (heightBalance > 1 && key.compareTo(node.left.key) < 0)
      return rightRotate(node);
    else if (heightBalance < -1 && key.compareTo(node.right.key) > 0)
      return leftRotate(node);
    else if (heightBalance > 1 && key.compareTo(node.left.key) > 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    } else if (heightBalance < -1 && key.compareTo(node.right.key) < 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    return node;
  }

  @Override
  /**
   * This method prints all nodes in tree by level
   * 
   */
  public void print() {
    // TODO Auto-generated method stub
    if (root == null) {
      return;
    }

    Queue<BSTNode<K, V>> allNodes = new LinkedList<BSTNode<K, V>>();
    allNodes.add(this.root);

    while (true) {
      int size = allNodes.size();
      if (size == 0) {
        break;
      }

      while (size > 0) {
        BSTNode node = allNodes.peek();
        System.out.print(node.key + "  ");
        allNodes.remove();
        if (node.left != null)
          allNodes.add(node.left);
        if (node.right != null)
          allNodes.add(node.right);
        size--;
      }
      System.out.println();
    }

  }

}
