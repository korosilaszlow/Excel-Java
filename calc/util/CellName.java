package calc.util;

public class CellName {
    public static final String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isCellNameValid(String cellName){
        if (cellName.length() > 1) {
            for (int i = 1; i < cellName.length(); i++){
                if (!Character.isDigit(cellName.charAt(i))){
                    return false;
                }
            }
            return colIndexes.contains(cellName.charAt(0)+"");
        }
        return false;
    }

    public static int getRowIndexFromCellName(String cellName) {
        if (!isCellNameValid(cellName)){
            throw new SheetException("A cella hivatkozas nem megfelelo!");
        }
        StringBuilder row = new StringBuilder("");
        for (int i = 1; i < cellName.length(); i++){
            row.append(cellName.charAt(i));
        }
        return Integer.parseInt(row.toString());
    }

    public static int getColIndexFromCellName(String cellName) {
        if (!isCellNameValid(cellName)){
            throw new SheetException("A cella hivatkozas nem megfelelo!");
        }
        return colIndexes.indexOf(cellName.charAt(0));
    }

}