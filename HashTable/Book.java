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
import java.util.Objects;

/**
 * This class models how a book object is implemented
 * 
 * @author Jason Sutanto
 *
 */
public class Book {
  // key of book object
  private String isbn13;
  // other member variables in book object
  private String authors;
  private String original_publication_year;
  private String title;
  private String language_code;
  private String average_rating;
  private String cover_type;
  private String pages;

  /**
   * Initialize all fields of the book object
   * 
   * @param isbn13 the key of book
   * @param authors the person who wrote the book
   * @param original_publication_year the year book was published
   * @param title the title of book
   * @param language_code the code of language is written in
   * @param average_rating measures how much people like the book
   * @param cover_type the cover that a book has
   * @param pages the number of pages in a book
   */
  public Book(String isbn13, String authors, String original_publication_year, String title,
      String language_code, String average_rating, String cover_type, String pages) {
    this.isbn13 = isbn13;
    this.title = title;
    this.authors = authors;
    this.original_publication_year = original_publication_year;
    this.language_code = language_code;
    this.average_rating = average_rating;
    this.cover_type = cover_type;
    this.pages = pages;
  }

  /**
   * This method returns key of the book
   * 
   * @return isbn13 the key of the book
   */
  public String getKey() {
    return this.isbn13;
  }

  /**
   * sets new key of book
   * 
   * @param isbn13 key of the book
   */
  public void setKey(String isbn13) {
    this.isbn13 = isbn13;
  }

  @Override
  /**
   * This method prints all fields of a book
   */
  public String toString() {
    return "ISBN13: " + this.isbn13 + "; Book: " + this.title + ", Author: " + this.authors
        + ", Original Publication Year: " + this.original_publication_year + ", Language: "
        + this.language_code + ", Average Rating: " + this.average_rating + ", Cover Type: "
        + this.cover_type + ", Pages: " + this.pages;
  }
}

