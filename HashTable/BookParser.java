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
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// learn how Scanner instances that are connected to the keyboard work.
/**
 * This class parses books csv file
 * 
 * @author Debra Deppler
 *
 */
public class BookParser {

  // @param booksfilename - a csv file with book database information

  // Parse the csv file into a list of book object
  public static ArrayList<Book> parse(String booksfilename) throws FileNotFoundException {
    ArrayList<Book> bookList = new ArrayList<Book>();
    try {
      Scanner scanner = new Scanner(new File(booksfilename));

      Scanner valueScanner = null;
      int idx = 0;

      // try different methods of the Scanner STDIN
      while (scanner.hasNext()) {
        valueScanner = new Scanner(scanner.nextLine());
        valueScanner.useDelimiter(",");
        ArrayList<String> book = new ArrayList<String>();
        while (valueScanner.hasNext()) {
          String data = valueScanner.next();
          book.add(data);
        }

        Book bookobj = new Book(book.get(0), book.get(1), book.get(2), book.get(3), book.get(4),
            book.get(5), book.get(6), book.get(7));
        // System.out.println(bookobj.toString());
        bookList.add(bookobj);


      }
      bookList.remove(0);
      scanner.close();

    } catch (FileNotFoundException e) {
    }
    return bookList;

  }

}

