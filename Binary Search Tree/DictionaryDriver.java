//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryDrive.java
// Files: Dictionary.java, DictionaryBST.java, DictionaryDriver.java, DictionnaryTests.java
// Course: CS 300 Spring 2019
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu email address
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Bloomest Janesen Chandra
// Partner Email: bjchandra@wisc.edu
// Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
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
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class makes use of all methods in DictionaryBST
 * 
 * @author Bloomest Chandra, Jason Sutanto
 *
 */

public class DictionaryDriver {
  /**
   * This method checks if commands is the correct length based on command
   * 
   * @param commands the commands the user will enter to driver like adding a word
   * @param validArgCount the correct number of arguments of a command
   * @return true if command has correct number of arguments, otherwise return false
   */
  private static boolean checkCommandArgumentsCount(String[] commands, int validArgCount) {
    // if command entered by user's length is not the same as validArgCount return false
    if (commands.length != validArgCount) {
      return false;
    }
    // otherwise return true
    return true;
  }

  /**
   * This method prints prompt for user to enter input
   */
  private static void printEnter() {
    // print prompt
    System.out.print("Please enter your command: ");
  }

  /**
   * prints the menu of commands
   */
  private static void printEnd() {
    // print menu
    System.out.println("============================== END ===================================");
  }

  private static void printMenu() {
    String userCommands = "\n=========================== Dictionary ============================\n"
        + "Enter one of the following options:\n"
        + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
        + "[L <word>] to search a word in the dictionary and display its definition\n"
        + "[G] to print all the words in the dictionary in sorted order\n"
        + "[S] to get the count of all words in the dictionary\n"
        + "[H] to get the height of this dictionary implemented as a binary search tree\n"
        + "[Q] to quit the program\n"
        + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * This method tests if all methods in DictionaryBST work properly
   * 
   * @param args
   */
  public static void main(String[] args) {
    // create scanner to read user input
    Scanner scnr = new Scanner(System.in);
    // create new dictionary
    DictionaryBST dict = new DictionaryBST();
    // variable ensures loop is running until user quits program
    boolean repeat = true;
    while (repeat) {
      // print menu
      printMenu();
      // print prompt
      printEnter();
      // store command as a string
      String rawCommand = scnr.nextLine();
      // create an array which has elements that are split by the string
      String[] commands = rawCommand.split(" ");
      // check first element in commands
      String first = commands[0];
      // make first element in commands lower case
      switch (first.toLowerCase()) {
        // if first element is a
        case "a":
          // check if length of commands is three
          if (commands.length < 3) {
            System.out.println("WARNING: Syntax Error for [A <word> <meaning>] command line.");
            continue;
          }
          // store meaning of word in a string variable
          String meaning = "";

          for (int i = 2; i < commands.length; i++) {
            // if meaning is not an empty
            if (!commands[i].equals("")) {
              // get last element of commands and store it in a string variable
              meaning += commands[i];
              if (i != commands.length - 1) {
                meaning += " ";
              }
            }
          }
          try {
            // add word to dictionary if adding is failed throw an exception because word is a
            // duplicate
            if (!dict.addWord(commands[1], meaning)) {
              System.out.println("WARNING: failed to add duplicate word: " + commands[1] + ".");
            }
          } catch (IllegalArgumentException e) {
            // print error message if word is a duplicate
            System.out.println(e.getMessage());
          }
          break;

        // if first element equals to l
        case "l":
          // check if commands contains correct arguments
          if (!checkCommandArgumentsCount(commands, 2)) {
            System.out.println("WARNING: Syntax Error for [L <word>] command line.");
            continue;
          }
          // Check if dictionary is empty
          if (dict.isEmpty()) {
            System.out.println("There are no definitions in this empty dictionary.");
            continue;
          }
          // store word in string variable
          String word = commands[1];
          try {
            // store meaning of word
            String mean = dict.lookup(word);
            // print word and meaning
            System.out.println(word + ": " + mean);
          } catch (NoSuchElementException e) {
            // if word is not found in dictionary throw exception
            System.out.println("No definition found for the word " + word);
          }
          break;
        // if first element equals g
        case "g":
          // check if commands contains correct number of arguments
          if (!checkCommandArgumentsCount(commands, 1)) {
            System.out.println("WARNING: Syntax Error for [G] command line.");
            continue;
          }
          // check if dictionary is empty
          if (dict.isEmpty()) {
            System.out.println("Dictionary is empty.");
            continue;
          }
          // create new arraylist that contains all words in dictionary
          ArrayList<String> words = dict.getAllWords();
          // print all words in dictionary
          for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i));
            // print comma after word if not the last word in dictionary
            if (i != words.size() - 1) {
              System.out.print(", ");
            }
          }
          System.out.println();
          break;
        // if first element is s
        case "s":
          // check if commands contains correct number of arguments
          if (!checkCommandArgumentsCount(commands, 1)) {
            System.out.println("WARNING: Syntax Error for [S] command line.");
            continue;
          }
          // print number of words in dictionary
          System.out.println(dict.size());
          break;
        // if first element is h
        case "h":
          // check if commands has correct number of arguments
          if (!checkCommandArgumentsCount(commands, 1)) {
            System.out.println("WARNING: Syntax Error for [H] command line.");
            continue;
          }
          // print height of dictionary tree
          System.out.println(dict.height());
          break;
        // if first element is q
        case "q":
          // check if commands contains correct number of arguments
          if (!checkCommandArgumentsCount(commands, 1)) {
            System.out.println("WARNING: Syntax Error for [Q] command line.");
            continue;
          }
          // end loop
          repeat = false;
          break;
        default:
          System.out.println("WARNING: Unrecognized command.");
          break;
      }
    }
    // print ending prompt
    printEnd();
    // close scanner
    scnr.close();
  }

}
