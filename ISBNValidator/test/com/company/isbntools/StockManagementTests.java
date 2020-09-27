package com.company.isbntools;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StockManagementTests {
    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @Before
    public void setUp() {
        System.out.println("Setup running");
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCangetCorrectLocaterCode() {
        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "J. Steinbeck"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);
        String isbn = "0140177396";
        String locaterCode = stockManager.getLocaterCode(isbn);
        assertEquals("7396J4", locaterCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
        String isbn = "0140177396";
        String locaterCode = stockManager.getLocaterCode(isbn);
        verify(testDatabaseService, times(1)).lookup("0140177396");
//        verify(webService, times(0)).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        //when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
        String isbn = "0140177396";
        String locaterCode = stockManager.getLocaterCode(isbn);
        // verify(testDatabaseService, never()).lookup("0140177396");
        verify(testWebService, times(1)).lookup("0140177396");
    }
}