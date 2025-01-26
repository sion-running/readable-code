package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private final Cell[][] board;
    private final int landMineCount;

    public GameBoard(GameLevel gameLevel) {
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSize();
        board = new Cell[rowSize][colSize];

        landMineCount = gameLevel.getLandMineCount();
    }

    public void flag(int rowIndex, int coleIndex) {
        Cell cell = findCell(rowIndex, coleIndex);
        cell.flag();
    }

    public void open(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        cell.open();
    }

    public void openSurroundedCells(int row, int col) {
        if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
            return;
        }
        if (isOpenedCell(row, col)) {
            return;
        }
        if (isLandMineCell(col, row)) {
            return;
        }

        open(row, col);

        if (doesCellHaveLandMineCount(row, col)) {
            return;
        }

        openSurroundedCells(row - 1, col - 1);
        openSurroundedCells(row - 1, col);
        openSurroundedCells(row - 1, col + 1);
        openSurroundedCells(row, col - 1);
        openSurroundedCells(row, col + 1);
        openSurroundedCells(row + 1, col - 1);
        openSurroundedCells(row + 1, col);
        openSurroundedCells(row + 1, col + 1);
    }

    public boolean isOpenedCell(int row, int col) {
        return findCell(row, col).isOpened();
    }

    public boolean isLandMineCell(int selectedColIndex, int selectedRowIndex) {
        Cell cell = findCell(selectedRowIndex, selectedColIndex);
        return cell.isLandMine();
    }


    public boolean isAllCellChecked() {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .allMatch(Cell::isChecked);
    }

    private boolean doesCellHaveLandMineCount(int row, int col) {
        return findCell(row, col).hasLandMineCount();
    }


    public void initializeGame() {
        int rowSize = getRowSize();
        int colSize = getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = new EmptyCell();
            }
        }

        for (int i = 0; i < landMineCount; i++) {
            int landMineCol = new Random().nextInt(colSize);
            int landMineRow = new Random().nextInt(rowSize);
            board[landMineRow][landMineCol] = new LandMineCell();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(col, row)) {
                    continue;
                }

                int count = countNearbyLandMines(row, col);
                if (count == 0) {
                    continue;
                }

                board[row][col] = new NumberCell(count);
            }
        }
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }


    public String getSign(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        return cell.getSign();
    }

    private Cell findCell(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    private int countNearbyLandMines(int row, int col) {
        int count = 0;
        int rowSize = getRowSize();
        int colSize = getColSize();

        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(col - 1, row - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(col, row - 1)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < 10 && isLandMineCell(col + 1, row - 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(col - 1, row)) {
            count++;
        }
        if (col + 1 < colSize && isLandMineCell(col + 1, row)) {
            count++;
        }
        if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(col - 1, row + 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMineCell(col, row + 1)) {
            count++;
        }
        if (row + 1 < rowSize && col + 1 < 10 && isLandMineCell(col + 1, row + 1)) {
            count++;
        }
        return count;
    }
}
