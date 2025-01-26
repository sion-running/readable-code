package cleancode.minesweeper.tobe.cell;

public class LandMineCell extends Cell {
    private static final String LAND_MINE_SIGN = "*";
    private boolean isLandMine;

    @Override
    public boolean isLandMine() {
        return true; // 지뢰 cell이니까 true
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public String getSign() {
        if (isOpened) {
            return LAND_MINE_SIGN;
        }

        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
