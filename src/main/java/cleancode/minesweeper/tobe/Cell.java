package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearByLandMindCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;


    public Cell(int nearByLandMindCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearByLandMindCount = nearByLandMindCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    // 이름을 줄 수 있는 정적 팩토리 메서드로 생서자 대신하기

    public static Cell create() {
        return of(0, false, false, false);
    }

    public static Cell of(int nearByLandMindCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearByLandMindCount, isLandMine, isFlagged, isOpened);
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearByLandMindCount = count;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }


    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearByLandMindCount != 0;
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }

            if (hasLandMineCount()) {
                return String.valueOf(nearByLandMindCount);
            }

            return EMPTY_SIGN;
        }

        if (isFlagged) {
             return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
