package com.centime.hierarchicaldataservice.service.impl;

import com.centime.hierarchicaldataservice.aop.MethodParams;
import com.centime.hierarchicaldataservice.dao.ResourceDao;
import com.centime.hierarchicaldataservice.exception.NoDataPresentException;
import com.centime.hierarchicaldataservice.model.Resource;
import com.centime.hierarchicaldataservice.model.ResourceEntity;
import com.centime.hierarchicaldataservice.repository.ResourceRepository;
import com.centime.hierarchicaldataservice.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class which interacts with dao and jpa repository layers and fetches data.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ResourceDao resourceDao;

    @Autowired
    ResourceRepository resourceRepository;

    Map<Integer, Resource> resourceMap;

    /**
     * Fetches the root resource hierarchy.
     *
     * @return
     */
    @Override
    @MethodParams
    public Resource getRootResource() {
        Resource topResource = new Resource();
        topResource.setId(0);
        resourceMap = resourceDao.getResourceById(topResource.getId());
        buildParentChildData(topResource);
        return topResource;
    }

    @Override
    @MethodParams
    public Resource getResourceByID(int id) throws NoDataPresentException {
        Optional<ResourceEntity> parentResourceEntity = resourceRepository.findById(id);
        if (!parentResourceEntity.isPresent()) {
            throw new NoDataPresentException(String.format("No data present for id %s in the database", id));
        }
        resourceMap = resourceDao.getResourceById(id);
        if (resourceMap.isEmpty()) {
            throw new NoDataPresentException(String.format("No children are present for parent with id %s in the database", id));
        }

        ResourceEntity entity = parentResourceEntity.get();
        Resource parentResource = new Resource();
        parentResource.setName(entity.getName());
        parentResource.setId(id);
        buildParentChildData(parentResource);
        return parentResource;
    }

    @Override
    public void saveResource(ResourceEntity resource) {
        resourceRepository.save(resource);
    }

    /**
     * Below method is to build a resource object with parent child hierarchy.
     *
     * @param topResource
     */
    private void buildParentChildData(Resource topResource) {
        LOG.info("Preparing parent Child Data for parent id {}", topResource.getId());
        Resource resource = topResource;
        List<Resource> childResouces = listAllChildsForParent(topResource.getId());
        resource.setChildern(childResouces);
        if (childResouces.isEmpty()) {
            return;
        }
        childResouces.forEach(child -> {
            buildParentChildData(child);
        });
        LOG.info("Preparing parent Child Data for parent id {} completed.", topResource.getId());
    }

    /**
     * Fetches all the children resource objects for a given parentID.
     *
     * @param parent_id
     * @return List of resources with given parentID
     */
    private List<Resource> listAllChildsForParent(int parent_id) {
        List<Resource> sameParentResources = new ArrayList<>();
        resourceMap.values().forEach(v -> {
            if (v.getParentId() == parent_id) {
                sameParentResources.add(v);
            }
        });
        return sameParentResources;
    }
}
