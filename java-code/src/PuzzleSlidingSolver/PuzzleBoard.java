package PuzzleSlidingSolver;

import java.util.ArrayList;

public class PuzzleBoard {

    private final int row, column;
    private final int[][] board;
    private int spaceRow;
    private int spaceCol;

    // Constructor for the starting puzzle board
    public PuzzleBoard(int row, int col, int[][] board) {
        this.row = row;
        this.column = col;
        this.board = board;
        if (this.board != null) {
            findSpacePosition();
        }
    }

    // Find out the position of empty space in the puzzle
    private void findSpacePosition() {
        for (int r = 0; r < getRow(); r++) {
            for (int c = 0; c < getColumn(); c++) {
                if (getPuzzleOn(r, c) == 0) {
                    setSpaceRow(r);
                    setSpaceCol(c);
                    break;
                }
            }
        }
    }

    // Set the space row location
    private void setSpaceRow(int row) {
        this.spaceRow = row;
    }

    // Set the space column location
    private void setSpaceCol(int col) {
        this.spaceCol = col;
    }

    // Get the space row
    public int getSpaceRow() {
        return this.spaceRow;
    }

    // Get the space column
    public int getSpaceCol() {
        return this.spaceCol;
    }

    // Return the number of Row
    public int getRow() {
        return this.row;
    }

    // Return the number of Column
    public int getColumn() {
        return this.column;
    }

    // Determine whether they are the same board
    public boolean equals(PuzzleBoard otherBoard) {
        // Compare will start if they have same row and column
        if (this.getRow() == otherBoard.getRow() && this.getColumn() == otherBoard.getColumn()) {
            for (int r = 0; r < getRow(); r++) {
                for (int c = 0; c < getColumn(); c++) {
                    if (this.getPuzzleOn(r, c) != otherBoard.getPuzzleOn(r, c)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // Return the puzzle based on the index of row and column
    public int getPuzzleOn(int row, int column) {
        return this.board[row][column];
    }

    // Generate the goal state based on its Row and Column
    public PuzzleBoard getGoalBoard() {
        int[][] goalBoard = new int[getRow()][getColumn()];
        int value = 0;
        goalBoard[getRow() - 1][getColumn() - 1] = value;
        for (int r = 0; r < getRow(); r++) {
            for (int c = 0; c < getColumn(); c++) {
                if (r == (getRow() - 1) && c == (getColumn() -1)) {
                    break;
                }
                value++;
                goalBoard[r][c] = value;
            }
        }
        return new PuzzleBoard(getRow(), getColumn(), goalBoard);
    }

    // Check whether current puzzle is at the goal board
    public boolean isGoalBoard() {
        return this.equals(getGoalBoard());
    }

    // This method use to create clone of this puzzle
    @Override
    public PuzzleBoard clone() {
        int[][] cloneBoard = new int[getRow()][getColumn()];
        for (int r = 0; r < getRow(); r++) {
            for (int c = 0; c < getColumn(); c++) {
                cloneBoard[r][c] = getPuzzleOn(r, c);
            }
        }
        return new PuzzleBoard(getRow(), getColumn(), cloneBoard);
    }

    // This method will be providing a new Puzzle Board based on new space location
    private PuzzleBoard changeSpacePosition(int row, int col) {
        PuzzleBoard newBoard = this.clone();
        newBoard.board[getSpaceRow()][getSpaceCol()] = newBoard.board[row][col];
        newBoard.board[row][col] = 0;
        newBoard.setSpaceRow(row);
        newBoard.setSpaceCol(col);
        return newBoard;
    }

    // Find out all the possible moves on the current puzzle
    public ArrayList<PuzzleBoard> nextPossibleMoves() {
        ArrayList<PuzzleBoard> listOfMoves = new ArrayList<>();
        if ((getSpaceRow() + 1) < getRow()) {
            listOfMoves.add(changeSpacePosition((getSpaceRow() + 1), getSpaceCol()));
        }
        if ((getSpaceRow() - 1) > -1) {
            listOfMoves.add(changeSpacePosition((getSpaceRow() - 1), getSpaceCol()));
        }
        if ((getSpaceCol() + 1) < getColumn()) {
            listOfMoves.add(changeSpacePosition(getSpaceRow(), (getSpaceCol() + 1)));
        }
        if ((getSpaceCol() - 1) > -1) {
            listOfMoves.add(changeSpacePosition(getSpaceRow(), (getSpaceCol() - 1)));
        }
        return listOfMoves;
    }

    // Calculate the approximate cost before it reach the goal state
    public int calculateCostToGoal() {
        int cost = 0;
        PuzzleBoard goal = getGoalBoard();
        for (int r = 0; r < getRow(); r++) {
            for (int c = 0; c < getColumn(); c++) {
                int puzzle = getPuzzleOn(r, c);
                for (int r2 = 0; r2 < getRow(); r2++) {
                    for (int c2 = 0; c2 < getColumn(); c2++) {
                        if (goal.getPuzzleOn(r2, c2) == puzzle) {
                            cost += Math.abs(r2 - r) + Math.abs(c2 - c);
                        }
                    }
                }
            }
        }
        return cost;
    }

    // Print out the board
    public String toString() {
        String str = "[";
        for (int r = 0; r < getRow(); r++) {
            for (int c = 0; c < getColumn(); c++) {
                str += getPuzzleOn(r, c);
                if (r == getRow() - 1 && c == getColumn() - 1) {
                    str += "]";
                } else {
                    str += ", ";
                }
            }
        }
        return str;
    }
}
