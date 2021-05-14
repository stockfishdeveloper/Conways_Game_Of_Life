// our update gameboard class
class Update_GameBoard {

    // uodate the gameboard
    static void Next_Generation() {
        // these are how many cells live and die, to keep track
        int numtodie = 0;
        int numtolive = 0;
        // and the arrays of corresponding coordinates
        int[] cellstodiex = new int[10000];
        int[] cellstodiey = new int[10000];
        int[] cellstolivex = new int[10000];
        int[] cellstolivey = new int[10000];
        // for each cell on the board
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                // check how many alive cells border the current cell
                int f = Popcount(j, i);
                // if the current cell is dead
                if (Draw_Board.GameBoard[j][i] == false) {
                    // and it has exactly three alive cells touching it, then it becomes alive
                    if (f == 3) {
                        cellstolivex[numtolive] = j;
                        cellstolivey[numtolive++] = i;
                    }
                }
                // if the current cell is alive
                if (Draw_Board.GameBoard[j][i] == true) {
                    // and it has more than 3 or less than 2 alive neighbors then it dies
                    if ((f > 3) || (f < 2)) {
                        cellstodiex[numtodie] = j;
                        cellstodiey[numtodie++] = i;
                    }
                }
            }
        }
        // now update the board with the alive and dead cells
        for (int i = 0; i < numtodie; i++) {
            Draw_Board.GameBoard[cellstodiex[i]][cellstodiey[i]] = false;
        }
        for (int i = 0; i < numtolive; i++) {
            Draw_Board.GameBoard[cellstolivex[i]][cellstolivey[i]] = true;
        }
    }
    
    // our method to check how many alive cells border a given cell
    static int Popcount(int x, int y) {
        int count = 0;
        // if we are on the top row
        if (x == 0) {
            // and the first column
            if (y == 0) {
                // check individual cells
                if (Draw_Board.GameBoard[0][1] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[1][1] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[1][0] == true) {
                    count++;
                }
                return count;
            }
            // or the last column
            if (y == 99) {
                if (Draw_Board.GameBoard[0][98] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[1][99] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[1][98] == true) {
                    count++;
                }
                return count;
            }
            // now check the cells in relation to the current one now that we're in the center
            if (Draw_Board.GameBoard[0][y + 1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[0][y - 1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[1][y] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[1][y + 1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[1][y - 1] == true) {
                count++;
            }
            return count;
        }
        // or the last row
        if (x == 99) {
            // first column
            if (y == 0) {
                if (Draw_Board.GameBoard[99][1] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[98][1] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[98][0] == true) {
                    count++;
                }
                return count;
            }
            // last column
            if (y == 99) {
                if (Draw_Board.GameBoard[99][98] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[98][99] == true) {
                    count++;
                }
                if (Draw_Board.GameBoard[98][98] == true) {
                    count++;
                }
                return count;
            }
            // check cells in relation to this one
            if (Draw_Board.GameBoard[99][y + 1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[99][y - 1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[98][y] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[98][y + 1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[98][y - 1] == true) {
                count++;
            }
            return count;
        }
        // more cases
        if ((y == 0) && (x != 0) && (x != 99)) {
            if (Draw_Board.GameBoard[x - 1][0] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x + 1][0] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x][1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x - 1][1] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x + 1][1] == true) {
                count++;
            }
            return count;
        }
        if ((y == 99) && (x != 0) && (x != 99)) {
            if (Draw_Board.GameBoard[x - 1][99] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x + 1][99] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x][98] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x - 1][98] == true) {
                count++;
            }
            if (Draw_Board.GameBoard[x + 1][98] == true) {
                count++;
            }
            return count;
        }
        if (Draw_Board.GameBoard[x + 1][y] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x - 1][y] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x][y + 1] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x][y - 1] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x + 1][y + 1] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x + 1][y - 1] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x - 1][y + 1] == true) {
            count++;
        }
        if (Draw_Board.GameBoard[x - 1][y - 1] == true) {
            count++;
        }
        return count;
    }
}
