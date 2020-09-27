package com.company.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTests {
    @Test
    public void checkValid10DigitISBNTest(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0140449116");
        assertTrue("First Value", result);
    }

    @Test
    public void checkValid13DigitISBNTest(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("9783161484100");
        assertTrue(result);
    }
    @Test
    public void checkInValid13DigitISBNTest(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("9783161484105");
        assertFalse(result);
    }

    @Test
    public void TenDigitendingWithXisValidISBNTest(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("012000030X");
        assertTrue(result);
    }
    @Test
    public void checkInValid10DigitISBNTest(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0140449112");
        assertFalse(result);
    }
    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNNotAllowed(){
        ValidateISBN validateISBN = new ValidateISBN();
        validateISBN.checkISBN("140449112");
    }
    @Test(expected = NumberFormatException.class)
    public void nonNumericISBNNotAllowed(){
        ValidateISBN validateISBN = new ValidateISBN();
        validateISBN.checkISBN("Helloworld");
    }

}
