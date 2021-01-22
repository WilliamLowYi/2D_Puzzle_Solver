package PuzzleSlidingSolver;

public class PuzzleStage {
    // Class Variable
    private final PuzzleBoard board;
    private final int costToGoal;
    private PuzzleStage parent;
    private int expandLevel;
    // Constructor
    public PuzzleStage(PuzzleBoard board, PuzzleStage parent, int expandLevel) {
        this.board = board;
        this.parent = parent;
        this.expandLevel = expandLevel;
        this.costToGoal = board.calculateCostToGoal();
    }
    // Constructor for first puzzle sequence
    public PuzzleStage(PuzzleBoard board) {
        this.board = board;
        this.parent = null;
        this.expandLevel = 0;
        this.costToGoal = 0;
    }
    // Method to retrieve total cost of the board sequence
    public int getTotalCost() {
        return (this.expandLevel + this.costToGoal);
    }
    // Method to retrieve expand level
    public int getExpandLevel() {
        return this.expandLevel;
    }
    // Method to retrieve the parent stage
    public PuzzleStage getParent() {
        return this.parent;
    }
    // Method to retrieve puzzle board
    public PuzzleBoard getBoard() {
        return this.board;
    }
    // Method to set parent
    public void setParent(PuzzleStage parent) {
        this.parent = parent;
    }
    // Method to set expand level
    public void setExpandLevel(int expandLevel) {
        this.expandLevel = expandLevel;
    }
}
