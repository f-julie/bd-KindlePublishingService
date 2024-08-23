package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.converters.BookPublishRequestConverter;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.exceptions.PublishingStatusNotFoundException;
import com.amazon.ata.kindlepublishingservice.models.requests.GetPublishingStatusRequest;
import com.amazon.ata.kindlepublishingservice.models.response.GetPublishingStatusResponse;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetPublishingStatusActivity {
    private final PublishingStatusDao publishingStatusDao; // Step 1: Declare DAO

    @Inject
    public GetPublishingStatusActivity(PublishingStatusDao publishingStatusDao) { // Step 2: Inject DAO
        this.publishingStatusDao = publishingStatusDao;
    }
    public GetPublishingStatusResponse execute(GetPublishingStatusRequest publishingStatusRequest) {
        String id = publishingStatusRequest.getPublishingRecordId();

        List<PublishingStatusItem> list = publishingStatusDao.getPublishingStatus(id);
        List<PublishingStatusRecord> r = new ArrayList<>();

        for (PublishingStatusItem item : list) {
            r.add(BookPublishRequestConverter.toPublishingStatusRecord(item));
        }

        return GetPublishingStatusResponse.builder()
                .withPublishingStatusHistory(r)
                .build();
    }
}