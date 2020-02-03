//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Files: (a list of all source files used by that program)
// Course: (course number, term, and year)
//
// Author: Jason Sutanto
// Email: jsutanto2@wisc.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: Ryan Susilo (Programming Partner)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class MyLevels {
    /**
     * Character values for displaying the different statuses of the game board cells.
     */
    public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR = '#'; // Wall character
    public static final char WORKER_CHAR = '@'; // Worker character
    public static final char GOAL_CHAR = '.'; // Goal character
    public static final char BOX_GOAL_CHAR = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR = '+'; // Worker on a goal character

    /**
     * Constants for the random processes.
     */
    public static final long SEED = 1234; // The random seed

    /**
     * Initial configuration of the levels. Note that the location of the goals are defined in the
     * GOALS array which is a parallel array to LEVELS.
     */


    public static final char[][][] LEVELS = {{

        // {#, #, #, #, #},
        // {#, *, #, " ", #},
        // {#, " ", #, " ", #},
        // {#, " " , = , " ", #},
        // {#, , , , '@'}
        {WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
        {WALL_CHAR, GOAL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
        {WALL_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR},
        {WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR},
        {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR}

        }, {

            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, BOX_GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, BOX_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, BOX_GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR}



        },

        {{WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR}, // 1
            {WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, BOX_GOAL_CHAR, EMPTY_CHAR, WALL_CHAR,
                WALL_CHAR, WALL_CHAR}, // 2
            {WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
                EMPTY_CHAR, WALL_CHAR}, // 3
            {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
                WALL_CHAR, WALL_CHAR}, // 4a
            {BOX_GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,
                WALL_CHAR, WALL_CHAR}, // 5
            {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
                WALL_CHAR, BOX_GOAL_CHAR}, // 6
            {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
                EMPTY_CHAR, EMPTY_CHAR}, // 7
            {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR,
                EMPTY_CHAR, EMPTY_CHAR}, // 8
            {WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,
                WALL_CHAR, WORKER_CHAR}// 9


        }};


    /**
     * Position of the goals for each level. Every pair of values represents the row and column of a
     * goal. This is a parallel array to LEVELS.
     */
    public static final int[][] GOALS = {{1, 1}, {1, 1, 5, 2}, {1, 4, 4, 0, 5, 8}};

    /**
     * The characters for the different movement directions and for quiting a game.
     */
    public static final char UP_CHAR = '8';
    public static final char DOWN_CHAR = '2';
    public static final char LEFT_CHAR = '4';
    public static final char RIGHT_CHAR = '6';
    public static final char QUIT_CHAR = 'q';

}
