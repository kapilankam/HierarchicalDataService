package com.centime.hierarchicaldataservice.service;

import com.centime.hierarchicaldataservice.exception.NoDataPresentException;
import com.centime.hierarchicaldataservice.model.Resource;
import com.centime.hierarchicaldataservice.model.ResourceEntity;
import org.springframework.stereotype.Service;

@Service
public interface ResourceService {
    Resource getRootResource();

    Resource getResourceByID(int id) throws NoDataPresentException;

    void saveResource(ResourceEntity resource);
}
