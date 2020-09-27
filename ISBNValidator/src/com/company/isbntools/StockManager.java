package com.company.isbntools;

public class StockManager {
    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public void setDatabaseService(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }
    public void setWebService(ExternalISBNDataService webService){
        this.webService = webService;
    }
    public String getLocaterCode(String isbn) {

        Book book = databaseService.lookup(isbn);
        if (book == null)
            book = webService.lookup(isbn);

        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuther().substring(0, 1));
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
