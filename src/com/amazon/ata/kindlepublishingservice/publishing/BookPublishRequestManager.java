package com.amazon.ata.kindlepublishingservice.publishing;

import java.util.ArrayList;
import java.util.List;

public class BookPublishRequestManager {
    // Step 1: Define a collection to hold the requests
    private List<BookPublishRequest> requests;

    // Step 2
    public BookPublishRequestManager() {
        // Initialize the collection in the constructor
        // Accept any dependencies of our class into our constructor
        this.requests = new ArrayList<>();
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
        if (!requests.isEmpty()) {
            return requests.remove(0); // Returns and removes the first request
        }
        return null;
    }
}
