package calc;

import calc.util.*;

public class Equation implements Evaluable {
    private String operand1;
    private String operand2;
    private Character operator;

    public Equation(String keplet) {
        if (keplet.contains("+")) {
            operator = '+';
        } else if (keplet.contains("-")) {
            operator = '-';
        } else if (keplet.contains("*")) {
            operator = '*';
        } else if (keplet.contains("/")) {
            operator = '/';
        } else {
            throw new IllegalArgumentException("A keplet nem tartalmaz operatort!");
        }
        String[] temp = keplet.split("\\" + operator);
        String szam1 = temp[0];
        String szam2 = temp[1];

        if (!(checkNumInString(szam1) || CellName.isCellNameValid(szam1))) {
            throw new IllegalArgumentException("Az elso operandus nem megfelelo!");
        }
        if (!(checkNumInString(szam2) || CellName.isCellNameValid(szam2))) {
            throw new IllegalArgumentException("A masodik operandus nem megfelelo!");
        }
        
        operand1 = szam1;
        operand2 = szam2;

    }

    private boolean checkNumInString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int eval(Sheet table) throws SheetException {
        int szam1 = Sheet.constructIntFromOperandStr(this.operand1, table);
        int szam2 = Sheet.constructIntFromOperandStr(this.operand2, table);
        int eredmeny;
        switch (operator) {
            case '+':
                eredmeny = szam1 + szam2;
                break;
            case '-':
                eredmeny = szam1 - szam2;
                if (eredmeny < 0) {
                    throw new ArithmeticException("A szam nem lehet negativ!");
                }
                break;
            case '*':
                eredmeny = szam1 * szam2;
                break;
            case '/':
                if (szam2 == 0) {
                    throw new ArithmeticException("Az oszto nem lehet nulla!");
                }
                eredmeny = szam1 / szam2;
                break;
            default:
                eredmeny = 0;
                break;
        }
        return eredmeny;
    }
}
