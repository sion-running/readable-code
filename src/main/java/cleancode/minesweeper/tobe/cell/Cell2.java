package cleancode.minesweeper.tobe.cell;

public abstract class Cell2 {
    // 하위 자식 Cell에서 사용하는 값은 protected로 열어준다.

    protected static final String FLAG_SIGN = "⚑";
    protected static final String UNCHECKED_SIGN = "□";

    protected boolean isFlagged;
    protected boolean isOpened;


    public abstract void turnOnLandMine();

    public abstract void updateNearbyLandMineCount(int count);

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public abstract boolean isLandMine();
    public boolean isOpened() {
        return isOpened;
    }

    public abstract boolean hasLandMineCount();

    public abstract String getSign();

//    {
//        if (isOpened) {
//            if (isLandMine) {
//                return LAND_MINE_SIGN;
//            }
//
//            if (hasLandMineCount()) {
//                return String.valueOf(nearByLandMindCount);
//            }
//
//            return EMPTY_SIGN;
//        }
//
//        if (isFlagged) {
//            return FLAG_SIGN;
//        }
//
//        return UNCHECKED_SIGN;
//    }
}
