package com.amazon.ata.kindlepublishingservice.publishing;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Singleton
public class BookPublishRequestManager {
    // Step 1: Define a collection to hold the requests
    private Queue<BookPublishRequest> requests;

    // Step 2
    @Inject
    public BookPublishRequestManager() {
        // Initialize the collection in the constructor
        // Accept any dependencies of our class into our constructor
        this.requests = new LinkedList<>();
    }

    // Step 3: Implement the addBookPublishRequest method
    public void addBookPublishRequest(BookPublishRequest request) {
        // Add the given BookPublishRequest to the collection
        if (request != null) {
            requests.add(request);
        }
    }

    // Step 4
    public BookPublishRequest getBookPublishRequestToProcess() {
        // Retrieve the next BookPublishRequest in line for publishing and return it.
        // If there are no requests to publish, return null.
        return requests.poll();
    }
}
