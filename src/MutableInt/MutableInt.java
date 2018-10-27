package MutableInt;

public class MutableInt {
    private int i;

    public MutableInt(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            MutableInt that = (MutableInt) obj;
            return this.getI() == that.getI();
        } else {
            return false;
        }
    }
}
