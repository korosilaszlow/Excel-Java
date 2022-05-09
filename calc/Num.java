package calc;

import calc.util.SheetException;

public class Num implements Evaluable {
    private int num;

    public Num(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("A szam nem lehet negativ!");
        }
        this.num = num;
    }

    @Override
    public int eval(Sheet table) throws SheetException {
        return num;
    }
}
