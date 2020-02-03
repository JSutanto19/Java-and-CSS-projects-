//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Test Sokoban
// Files:           Sokoban.Java, Config.Java
// Course:          CS 200 Fall 2018
//
// Author:          Jason Sutanto
// Email:           jsutanto2@wisc.edu email address
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Ryan Susilo
// Partner Email:   susilo2@wisc.edu
// Lecturer's Name: Marc Renault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 *
 */
import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as they are developed.
 * These methods are all private as they are only intended for use within this class.
 * 
 * @author Jason Sutanto and Ryan Susilo
 * 
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();

        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();

        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }

    /**
     * This method tests the checkLevel method in Sokoban class.
     */
    private static void testCheckLevel() {
        int numTests = 4; // number of tests I have
        int passed = numTests; // the number of tests that pass
        int res;// stores value returned from checkLevel
        // Test 1
        if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }

        // Test 2
        char[][][] lvl = new char[2][][];
        if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }

        // Test 3
        int[][] goal = new int[2][];
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, goal)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -2, but value returned " + res);
            passed--;
        }



        // Test 4

        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) != 1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected 1, but value returned " + res);
            passed--;
        }


        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     * 
     * @param char a one board that will be compared by user
     * @param char b another board that will be compare to char a the first board
     * @return returns true if boards are the same and are not empty. if either one of the boards is
     *         empty method returns false and if both boards are not equal it will be false as well
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if (a == null || b == null)
            return false;
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method tests the initBoard method in the Sokoban class.
     */
    private static void testInitBoard() {
        int numTests = 3;// number of tests in this method
        int passed = numTests;// number of tests passed

        // Test 1
        int[] pTest1 = new int[2]; // this variable stores position of worker
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);// creates first
                                                                                    // board to test
        if (!Arrays.equals(pTest1, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(pTest1));
            passed--;
        } else {
            char[][] bCompTest1 = new char[][] {
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                    Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.WORKER_CHAR}};
            if (!compBoards(bTest1, bCompTest1)) {
                System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
                System.out.println("Generated:");
                Sokoban.printBoard(bTest1);
                System.out.println("Expected:");
                Sokoban.printBoard(bCompTest1);
                passed--;
            }
        }

        // Test 2
        int[] pTest2 = new int[2];// variable stores position of worker on the new board
        char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);// creates
                                                                                    // second board
                                                                                    // to test

        if (!Arrays.equals(pTest2, new int[] {7, 10})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 2. Expected initial position: {7, 10} , but value after call "
                    + Arrays.toString(pTest2));
            passed--;
        } else {
            char[][] bCompTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);


            if (!compBoards(bTest2, bCompTest2)) {
                System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
                System.out.println("Generated:");
                Sokoban.printBoard(bTest2);
                System.out.println("Expected:");
                Sokoban.printBoard(bCompTest2);
                passed--;
            }
        }

        // End of Test 2

        // Test 3
        int[] pTest3 = new int[2];// variable stores positon of worker on new board
        char[][] bTest3 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest3);
        if (!Arrays.equals(pTest3, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 3. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(pTest3));
            passed--;
        } else {
            char[][] bCompTest3 = new char[][] {
                {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                    Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.GOAL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                        Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                            Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR,
                                Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                                    Config.WORKER_CHAR}};
            if (!compBoards(bTest3, bCompTest3)) {
                System.out.println("FAILED: Sokoban.initBoard Test 3. Board not as expected!");
                System.out.println("Generated:");
                Sokoban.printBoard(bTest3);
                System.out.println("Expected:");
                Sokoban.printBoard(bCompTest3);
                passed--;
            }
        }


        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testCheckWin() {
        // Test 1
        int numTests = 2;// number of tests in the test method
        int passed = numTests;// number of tests passes in the method
        int[] pTest1 = new int[2];// stores position of worker
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);// stores
                                                                                        // the
                                                                                        // created
                                                                                        // board
        boolean result = false;// stores value returned by checkWin method
        result = Sokoban.checkWin(bTest1);

        if (result) {
            System.out.println(
                "FAILED: Sokoban.checkWin Test 1. Expected false , but value after call " + result);
            passed--;
        }

        // Test 2
        int[] pTest2 = new int[2];// stores position of worker
        char[][] btest2 = {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};// stores the created board
        boolean result2 = Sokoban.checkWin(btest2);// stores value returned by checkWin method

        if (!result2) {
            System.out.println(
                "FAILED: Sokoban.checkWin Test 2. Expected true, but value after call " + result2);
            passed--;
        }

        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method tests the calcDelta method in Sokoban class.
     */
    private static void testCalcDelta() {
        // FIXME
        int numTests = 2;// number of tests inside the method
        int passed = numTests;// number of tests passed inside this method

        // test 1
        int[] testVector1 = new int[2];// stores value returned from calcDelta method
        String moveStr = "8400";// user input that is converted to valid elements in the one
                                // dimensional movement array
        Scanner scnr = new Scanner(System.in);
        testVector1 = Sokoban.calcDelta(moveStr);

        if (testVector1[0] != 400 && testVector1[1] != 0) {
            System.out.println(
                "FAILED: Sokoban.calcDelta test 1. entered 8400, expected -400 0, but value after call "
                    + Arrays.toString(testVector1));
            passed--;
        }



        // test 2
        int[] testVector2 = new int[2];// stores value returned from calcDelta method
        String moveStr2 = "6200";// user input that is converted to valid elements in the one
                                 // dimensional movement array
        testVector2 = Sokoban.calcDelta(moveStr2);

        if (testVector1[0] != 400 && testVector1[1] != 0) {
            System.out.println(
                "FAILED: Sokoban.calcDelta test 1. entered 6200, expected 200 0, but value after call "
                    + Arrays.toString(testVector2));
            passed--;
        }

        System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");


    }

    /**
     * This method tests the checkDelta method in Sokoban class.
     */
    private static void testCheckDelta() {
        // Test 1
        int numTests = 2;// number of test in the program
        int passed = numTests;// number of tests passed
        int res;// stores value returned in checkDelta

        char[][] bTest = Config.LEVELS[0];// stores a board
        int[] posTest1 = {1};// stores position of worker
        int[] deltaTest1 = {0, -1};// stores the magnitude of how the worker character will move
        char[] valid = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR,
            Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.EMPTY_CHAR}; // array that contains all
                                                                         // possible characters in a
                                                                         // level/board

        res = Sokoban.checkDelta(bTest, posTest1, deltaTest1, valid);



        if ((res = Sokoban.checkDelta(bTest, posTest1, deltaTest1, valid)) != -1) {
            System.out.println(
                "FAILED: Sokoban.checkDelta Test 1. Expected -1, but value returned " + res);
            passed--;
        }

        // Test 2
        int[] posTest2 = {4, 4}; // new postion of worker
        char[] valid2 = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR, Config.WALL_CHAR, Config.GOAL_CHAR,
            Config.WORK_GOAL_CHAR, Config.EMPTY_CHAR};// array that contains all possible characters
                                                      // in a level/board

        if ((res = Sokoban.checkDelta(bTest, posTest2, deltaTest1, valid2)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkDelta Test 2. Expected -2, but value returned " + res);
            passed--;
        }


        System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");


    }

    /**
     * This method tests the togglePos method in Sokoban class.
     */
    private static void testTogglePos() {
        // Test 1
        int numTests = 2;// number of tests in the method
        int passed = numTests;// number of tests passed

        char board[][] = Config.LEVELS[0];// stores the board/level
        int[] pos = {3, 4};// stores position of character user changes after a move
        Sokoban.togglePos(board, pos, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR);
        if (board[pos[0]][pos[1]] != Config.WORKER_CHAR) {
            System.out
                .println("FAILED: Sokoban.togglePos test 1. Expected \"@\", but value returned "
                    + board[pos[0]][pos[1]]);
            passed--;
        }

        // Test 2
        int[] pos2 = {0, 4};// stores position of character user changes after a move
        Sokoban.togglePos(board, pos2, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR);
        if (board[pos2[0]][pos2[1]] != Config.BOX_CHAR) {
            System.out
                .println("FAILED: Sokoban.togglePos test 1. Expected \"@\", but value returned "
                    + board[pos2[0]][pos2[1]]);
            passed--;
        }

        System.out.println("testTogglePos: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method tests the shiftBox method in Sokoban class.
     */
    private static void testShiftBox() {
        int numTests = 2;// number of tests in the method
        int passed = numTests;// number of tests that pass in the method
        int[] pos = {2, 3};// position of box character on the board
        char[][] board = Config.LEVELS[0];// stores the board or level
        int[] delta = {0, 1};// stores the magnitude of the character is shifted
        int shiftBox1 = Sokoban.shiftBox(board, pos, delta);// stores value of shiftBox method

        if (shiftBox1 != 1) {
            System.out.println(
                "FAILED: Sokoban.shiftBox test 1. expected 1, but value after call " + shiftBox1);
            passed--;
        }

        // Test 2
        int[] pos2 = {1};// stores position of box character on the board
        if ((shiftBox1 = Sokoban.shiftBox(board, pos2, delta)) != -1) {
            System.out.println(
                "FAILED: Sokoban.shiftBox test 2. expected -1, but value after call " + shiftBox1);
            passed--;
        }

        System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method tests the doMove method in Sokoban class.
     */
    private static void testDoMove() {
        int numTests = 1;// number of tests in the method
        int passed = numTests;;// number of tests passed
        char[][] board = Config.LEVELS[0];// stores board or current level
        int pos[] = {4, 4};// stores position of worker
        int delta[] = {-1, 0};// store the magnitude of each object that is shifted
        int doMove = Sokoban.doMove(board, pos, delta);// stores value returned in doMove.

        // Test 1

        if (doMove != 1) {
            System.out.println(
                "FAILED: Sokoban. doMove test 2. expected -1, but value after call " + doMove);
            passed--;
        }



        System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method tests the processMove method in Sokoban class.
     */
    private static void testProcessMove() {
        int numTests = 1;// number of tests in the method
        int passed = numTests;// number of tests passed

        // Test 1
        int[] pos = {7, 10};// stores position of worker
        int[] delta = {-1, 0};// store the magnitude of how the character is shifted
        char[][] board = Config.LEVELS[1];// stores board or level
        int processMove;// stores value returned by process move
        processMove = Sokoban.processMove(board, pos, delta);



        if (processMove != 1) {
            System.out
                .println("FAILED: Sokoban.processMove test 1. expected 1, but value after call "
                    + processMove);
            passed--;
        }



        System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests.");
    }

}
