package com.dylan.dao;

import com.dylan.model.StreamingService;
import java.util.List;

public interface StreamingServiceDAO
{
    // Create
    void addService(StreamingService service);

    // Update
    void updateService(StreamingService service);

    // Delete
    void deleteService(int id);

    // Read
    StreamingService getServiceById(int id);

    List<StreamingService> getAllServices();
}