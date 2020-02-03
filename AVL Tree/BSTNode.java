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
/**
 * Class which stores the information about the nodes in the Trees
 * 
 * @author Jason Sutanto
 *
 * @param <K> Key of a binary search tree
 * @param <V> Value the value that a node stores
 */
class BSTNode<K, V> {

    K key;
    V value;
    BSTNode<K, V> left;
    BSTNode<K, V> right;
    int balanceFactor;
    int height;

    /**
     * this method initializes and creates a node for the avl tree 
     * @param key the key to find the node in tree 
     * @param value the data that a node stores 
     * @param leftChild the leftChild of node
     * @param rightChild the right child of node 
     */
    BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
        this.key = key;
        this.value = value;
        this.left = leftChild;
        this.right = rightChild;
        this.height = 0;
        this.balanceFactor = 0;
    }
    /**
     * this method initializes and creates a node for the avl tree 
     * @param key the key to find node in bst
     * @param value the data that a node stores 
     */
    BSTNode(K key, V value) {
        this(key, value, null, null);
    }

}
