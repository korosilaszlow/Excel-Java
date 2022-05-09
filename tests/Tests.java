package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import calc.util.*;
import calc.*;

public class Tests {
    @Test
    public void cellNameTest(){
        assertFalse(CellName.isCellNameValid("A 1"));
        assertFalse(CellName.isCellNameValid("a1"));
        assertFalse(CellName.isCellNameValid("AC"));
        assertTrue(CellName.isCellNameValid("A1"));
        assertTrue(CellName.isCellNameValid("K3"));
    }

    @Test
    public void rowIndexTest(){
        assertTrue(CellName.getRowIndexFromCellName("A9") == 9);
        assertTrue(CellName.getRowIndexFromCellName("A99") == 99);
    }

    @Test
    public void colIndexTest(){
        assertTrue(CellName.getColIndexFromCellName("A1") == 0);
        assertTrue(CellName.getColIndexFromCellName("B41") == 1);
    }

    @Test
    public void numTest(){
        Num a = new Num(5);
        assertTrue(a.eval(null) == 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void equationTestSpace(){
        Equation a = new Equation("5 + 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void equationTestLowerCase(){
        Equation a = new Equation("c2+4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void equationTestInvalid(){
        Equation a = new Equation("C2+a");
    }

    @Test
    public void testSheet(){
        Sheet sh = new Sheet(3,2);
        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));
        String expected = "6 5" + System.lineSeparator() + "2 6" + System.lineSeparator() + "2 9";
        assertEquals(sh.toString(), expected);
    }

    @Test
    public void testSheetSecond(){
        Sheet sh = new Sheet(3,3);
        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));
        sh.insertToSheet("C0", new Equation("A0+B0"));
        sh.insertToSheet("C1", new Equation("A1+B1"));
        sh.insertToSheet("C2", new Equation("A2+B2"));
        String expected = "6 5 11" + System.lineSeparator() + "2 6 8" + System.lineSeparator() + "2 9 11";
        assertEquals(sh.toString(), expected);
    }

    @Test
    public void TestSheetThird(){
        Sheet sh = new Sheet(3,4);
        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));
        sh.insertToSheet("C0", new Equation("A0+B0"));
        sh.insertToSheet("C1", new Equation("A1+B1"));
        sh.insertToSheet("C2", new Equation("A2+B2"));
        sh.insertToSheet("D0", new Equation("C0/2"));
        sh.insertToSheet("D1", new Equation("C1/2"));
        sh.insertToSheet("D2", new Equation("C2/2"));
        String expected = "6 5 11 5" + System.lineSeparator() + "2 6 8 4" + System.lineSeparator() + "2 9 11 5";
        assertEquals(sh.toString(), expected);
    }

    @Test
    public void testSheetForth(){
        Sheet sh = new Sheet(3,3);
        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));
        sh.insertToSheet("C0", new Equation("10*B0"));
        sh.insertToSheet("C1", new Equation("10*B1"));
        sh.insertToSheet("C2", new Equation("10*B2"));
        String expected = "6 5 50" + System.lineSeparator() + "2 6 60" + System.lineSeparator() + "2 9 90";
        assertEquals(sh.toString(), expected);
    }
}
