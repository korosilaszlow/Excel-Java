package calc;

import calc.util.SheetException;

interface Evaluable {
    public int eval(Sheet table) throws SheetException;
}
