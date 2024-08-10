package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;
import com.amazon.ata.kindlepublishingservice.models.Book;
import com.amazon.ata.kindlepublishingservice.models.requests.RemoveBookFromCatalogRequest;
import com.amazon.ata.kindlepublishingservice.models.response.RemoveBookFromCatalogResponse;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;

public class RemoveBookFromCatalogActivity {
    @Inject
    RemoveBookFromCatalogActivity() {}
    public RemoveBookFromCatalogResponse execute(RemoveBookFromCatalogRequest removeBookFromCatalogRequest) {
        // Step 1: Retrieve the book using the ID from the request
        String bookId = removeBookFromCatalogRequest.getBookId();
        CatalogDao catalogItem = getBookFromCatalog(bookId);

        // Step 2: Check if the book exists
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }

        // Step 3: Mark the book as inactive
        book.setActive(false); // Set the isActive field to false
        CatalogDao.save(book); // Save the updated book back to the catalog

        // Step 4: Create and return the response
        return new RemoveBookFromCatalogResponse("Book marked as inactive successfully.");


        //return null;
    }
}
