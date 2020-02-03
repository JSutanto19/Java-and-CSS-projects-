import java.util.Arrays;

public class test {
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        // FIX ME


        // test 1
        if (pos == null || pos.length != 2) {
            return -1;
        }

        int x = pos[0];
        int y = pos[1];

        if (x > board.length || x < 0 || y > board[x].length || y < 0) {
            return -1;
        }


        // test 2


        int match = 0;
        
        for (int i = 0; i < valid.length; ++i) {
            if (board[pos[0]][pos[1]] == Config.WORKER_CHAR || board[pos[0]][pos[1]] == Config.WORK_GOAL_CHAR) {
                if(board[pos[0]][pos[1]] == valid[i]) {
                    match += 0;
                }
            }

        }

        if (match == 0) {
            return -2;
        }

      
        // test 3
        if (delta == null || delta.length != 2) {
            return -3;
        }

        // test 4
        int[] newPos = new int[2];
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];
        if (newPos[0] >= board.length || newPos[0] < 0 || newPos[1] >= board[pos[0]].length
            || newPos[1] < 0 || board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {

            return -4;
        }



        // test 5
        if (board[newPos[0]][newPos[1]] == Config.BOX_CHAR) {
            return -5;
        }

        return 1;
    }


    public static void main(String[] args) {
        
        int[] pos = {1,1};
        char[][] board =  Sokoban.initBoard(1, MyLevels.LEVELS, MyLevels.GOALS, pos);
        int[] delta = {-1,0};
        char [] valid = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.EMPTY_CHAR};

        System.out.println(checkDelta(board, pos, delta, valid));
        System.out.println(Arrays.toString(pos));
        System.out.println(Arrays.toString(delta));
        Sokoban.printBoard(board);
       

    }
}



	



