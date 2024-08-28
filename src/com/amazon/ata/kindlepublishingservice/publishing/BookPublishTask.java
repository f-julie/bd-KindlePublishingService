package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;

import javax.inject.Inject;

public class BookPublishTask implements Runnable { // Implements Runnable
    BookPublishRequestManager bookPublishRequestManager;
    PublishingStatusDao publishingStatusDao;
    CatalogDao catalogDao;

    @Inject
    public BookPublishTask(BookPublishRequestManager bookPublishRequestManager, PublishingStatusDao publishingStatusDao, CatalogDao catalogDao) {
        this.bookPublishRequestManager = bookPublishRequestManager;
        this.publishingStatusDao = publishingStatusDao;
        this.catalogDao = catalogDao;
    }

    @Override
    public void run() { // Override the run method
        BookPublishRequest request = bookPublishRequestManager.getBookPublishRequestToProcess(); // Use manager to fetch latest book to publish
        if (request == null) return; // If no books to publish, thread will end here

        publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.IN_PROGRESS, request.getBookId());

        try {
            CatalogItemVersion item = catalogDao.createOrUpdateBook(KindleFormatConverter.format(request)); // Create new book or increase version +1
            publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.SUCCESSFUL, item.getBookId()); // Use item.getBookId() to be safe, rather than request.getBookId()
        } catch (BookNotFoundException e) {
            publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.FAILED, request.getBookId());
        }
    }
}