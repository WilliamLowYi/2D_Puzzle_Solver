package PuzzleSlidingSolver;

import java.io.*;
import java.util.Scanner;

public class FileReader {

    private String fileName;
    private Scanner scanner;
    private int rowNumber, columnNumber;
    private int[][] puzzleArray;

    public FileReader() {
        startReading();
    }

    private void startReading() {
        getUserInput();
        if (runScanner()) {
            readRowColumn();
            if (rowNumber > -1 || columnNumber > -1) {
                initializePuzzleArray();
                readPuzzleBoard();
            }
        } else {
            startReading();
        }
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String getFileName() {
        return this.fileName;
    }

    private boolean runScanner() {
        try {
            this.scanner = new Scanner(new File(getFileName()));
            System.out.println("File Scanner pass!");
            return true;
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("File is not found!");
            return false;
        }
    }

    private void readPuzzleBoard() {
        for (int r = 0; r < getRowNumber(); r++) {
            for (int c = 0; c < getColumnNumber(); c++) {
                int value = Integer.parseInt(scanner.next().replaceAll("[^0-9]", ""));
                addPuzzle(r, c, value);
            }
        }
    }

    private void readRowColumn() {
        int row = Integer.parseInt(scanner.next().replaceAll("[^0-9]", ""));
        int column = Integer.parseInt(scanner.next().replaceAll("[^0-9]", ""));
        setRowNumber(row);
        setColumnNumber(column);
    }

    private void setRowNumber(int row) {
        this.rowNumber = row;
    }

    private void setColumnNumber(int column) {
        this.columnNumber = column;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    private void initializePuzzleArray() {
        puzzleArray = new int[getRowNumber()][getColumnNumber()];
    }

    private void addPuzzle(int row, int col, int value) {
        puzzleArray[row][col] = value;
    }

    public int[][] getPuzzleArray() {
        return puzzleArray;
    }

    public void getUserInput() {
        // Enter file name using Buffer Reader
        System.out.println("Please enter the file name with .txt at the end of it: ");
        BufferedReader readUserInput = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = readUserInput.readLine();
            setFileName(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            getUserInput();
        }
    }
}
