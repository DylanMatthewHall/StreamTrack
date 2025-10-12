package com.dylan.dao;

import com.dylan.entity.StreamingService;
import java.util.List;

public interface StreamingServiceDAO
{
    void insert(StreamingService service);

    void delete(StreamingService service);

    List<StreamingService> getAll();

    StreamingService findById(int id);
}