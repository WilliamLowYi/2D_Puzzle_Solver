package PuzzleSlidingSolver;

import java.util.ArrayList;

public class Solver {
    // Class Variable
    private final ArrayList<PuzzleStage> expandedPuzzleList = new ArrayList<>();
    private final ArrayList<PuzzleStage> unexpandedPuzzleList = new ArrayList<>();

    // Constructor
    public Solver(int[][] initialBoard, int row, int column) {
        PuzzleStage rootPuzzle = new PuzzleStage(new PuzzleBoard(row, column, initialBoard));
        this.unexpandedPuzzleList.add(rootPuzzle);
    }

    // The core method of the computation on the algorithm
    public void puzzleSearch() {
        while(! unexpandedPuzzleList.isEmpty()) {
            int indexOfLowestCost = getLowestCostStageIndex();
            PuzzleStage currentStage = unexpandedPuzzleList.get(indexOfLowestCost);
            PuzzleBoard currentBoard = currentStage.getBoard();
            expandedPuzzleList.add(currentStage);
            unexpandedPuzzleList.remove(indexOfLowestCost);

            if (currentBoard.isGoalBoard()) {
                reportSolution(currentStage);
                return ;
            } else {
                ArrayList<PuzzleBoard> listOfMoves = currentBoard.nextPossibleMoves();
//                System.out.println("Current: " + currentBoard.toString());
//                System.out.println("Possible: " + listOfMoves.size());
//                System.out.println("Before: " + unexpandedPuzzleList.size());
                for (PuzzleBoard puzzleBoard : listOfMoves) {
//                    System.out.println(puzzleBoard.toString());
                    PuzzleStage stageFound = checkBoardInTheList(unexpandedPuzzleList, puzzleBoard);
                    // If the possible move has already in unexpanded list and the possible move's cost is lower than the one in the list
                    if ((stageFound != null) && ((currentStage.getTotalCost() + 1) < stageFound.getTotalCost() )) {
                        stageFound.setParent(currentStage);
                        stageFound.setExpandLevel(currentStage.getExpandLevel() + 1);
                    }
                    // If the possible move is new in both of the list
                    if ( (checkBoardInTheList(expandedPuzzleList, puzzleBoard) == null) && (stageFound == null)) {
                        unexpandedPuzzleList.add(new PuzzleStage(puzzleBoard, currentStage, currentStage.getExpandLevel() + 1));
                    }
                }
//                System.out.println("After: " + unexpandedPuzzleList.size());
            }
        }
        System.out.println("No Solution Found !!");
    }

    // Method to find the highest return with the lowest cost of puzzle sequence
    public int getLowestCostStageIndex() {
        int index = 0;
        int cost = unexpandedPuzzleList.get(index).getTotalCost();
        for (int i = 0; i < unexpandedPuzzleList.size(); i++) {
            if (unexpandedPuzzleList.get(i).getTotalCost() < cost) {
                cost = unexpandedPuzzleList.get(i).getTotalCost();
                index = i;
            }
        }
        return index;
    }

    // Method to check out whether the given puzzle stage has already in the record
    public PuzzleStage checkBoardInTheList(ArrayList<PuzzleStage> list, PuzzleBoard board) {
        for (PuzzleStage puzzleStage : list) {
            if (board.equals(puzzleStage.getBoard())) {
                return puzzleStage;
            }
        }
        return null;
    }

    // Method to report the solution when there is a solution found
    public void reportSolution(PuzzleStage puzzleStage) {
        System.out.println("Solution Found!!");
        printParent(puzzleStage);
        System.out.println("Moves: " + puzzleStage.getExpandLevel());
        System.out.println("Expanded: " + this.expandedPuzzleList.size());
        System.out.println("Unexpanded:" + this.unexpandedPuzzleList.size());
    }

    // Method to print out all the sequence of puzzle before it reach the goal
    public void printParent(PuzzleStage puzzleStage) {
        if (puzzleStage.getParent() != null) {
            printParent(puzzleStage.getParent());
        }
        System.out.println(puzzleStage.getBoard().toString());
    }

}
