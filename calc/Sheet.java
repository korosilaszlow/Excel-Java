package calc;

import calc.util.CellName;

public class Sheet {
    private Evaluable[][] sheet;
    public Sheet(int numOfRows, int numOfCols) {
        if (numOfRows < 1 || numOfCols < 1 || numOfCols > 26) {
            throw new IllegalArgumentException("Nem megfelelo sor vagy oszlop szam!");
        }
        sheet = new Evaluable[numOfRows][numOfCols];
    }

    public void insertToSheet(String cellName, Evaluable ref) {
        sheet[CellName.getRowIndexFromCellName(cellName)][CellName.getColIndexFromCellName(cellName)] = ref;
    }

    public Evaluable getFromSheet(String cellName){
        return sheet[CellName.getRowIndexFromCellName(cellName)][CellName.getColIndexFromCellName(cellName)];
    }

    public static int constructIntFromOperandStr(String operandStr, Sheet ref) {
        if (CellName.isCellNameValid(operandStr)){
            Evaluable tmp = ref.getFromSheet(operandStr);
            return tmp.eval(ref);
        }
        else if (Character.isDigit(operandStr.charAt(0))){
            return Integer.parseInt(operandStr);
        }
        return 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        try {
        for (int i = 0; i < sheet.length; i++){
            for (int j = 0; j < sheet[i].length; j++){
                if (sheet[i][j] instanceof Num){
                    sb.append(sheet[i][j].eval(null));
                }
                else if (sheet[i][j] instanceof Equation) {
                    sb.append(sheet[i][j].eval(this));
                }
                else {
                    sb.append("null");
                }
                if (j == sheet[i].length-1 && i != sheet.length-1){
                    sb.append(System.lineSeparator());
                }
                else if (j != sheet[i].length-1){
                    sb.append(" ");
                }
            }
        }
        
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
