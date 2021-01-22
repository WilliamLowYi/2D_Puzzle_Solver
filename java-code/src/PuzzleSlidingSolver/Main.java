package PuzzleSlidingSolver;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Solver solve = new Solver(fileReader.getPuzzleArray(), fileReader.getRowNumber(), fileReader.getColumnNumber());
        solve.puzzleSearch();
    }

}
