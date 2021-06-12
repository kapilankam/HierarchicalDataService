package com.centime.hierarchicaldataservice.dao;

import com.centime.hierarchicaldataservice.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResourceDao {

    Map<Integer, Resource> getResourceById(int groupId);

    List<Resource> getAllResource();
}
