package com.company.isbntools;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int LONG_ISBN_MULTIPLIER = 10;
    public static final int SHORT_ISBN_MULTIPLER = 11;

    public boolean checkISBN(String isbn) {

        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isValidLongDigitISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isValidShortDigitISBN(isbn);
        }
        throw new NumberFormatException("ISBN must be 10 or 13 digits!!");
    }

    private boolean isValidShortDigitISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else
                    throw new NumberFormatException("Non numerics not allowed!!");
            } else
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
        }
        return (total % SHORT_ISBN_MULTIPLER == 0);
    }

    private boolean isValidLongDigitISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            if (i % 2 == 0)
                total += Character.getNumericValue(isbn.charAt(i));
            else
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
        }
        return (total % LONG_ISBN_MULTIPLIER == 0);
    }
}
