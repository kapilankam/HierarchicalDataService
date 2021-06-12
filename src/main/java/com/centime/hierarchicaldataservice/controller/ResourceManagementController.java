package com.centime.hierarchicaldataservice.controller;

import com.centime.hierarchicaldataservice.exception.NoDataPresentException;
import com.centime.hierarchicaldataservice.model.Resource;
import com.centime.hierarchicaldataservice.model.ResourceEntity;
import com.centime.hierarchicaldataservice.service.ResourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URISyntaxException;

@RestController
@Validated
public class ResourceManagementController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResourceService resourceService;
    String response = "";

    @GetMapping(path = "/getAllData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getData() throws JsonProcessingException {
        logger.info("Getting information from database");
        Resource resource = resourceService.getRootResource();

        logger.info("Response processing..");
        response = processResponse(resource, true);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping(path = "getDataById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataById(@RequestParam("id") @Min(1) int id) throws JsonProcessingException, NoDataPresentException {
        logger.info("Getting information from database");
        Resource resource = resourceService.getResourceByID(id);

        logger.info("Response processing..");
        response = processResponse(resource, false);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity saveResource(@Valid @RequestBody ResourceEntity resourceEntity) throws URISyntaxException {
        resourceService.saveResource(resourceEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String processResponse(Resource resource, boolean isRoot) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = isRoot ? ow.writeValueAsString(resource.getChildern()) : ow.writeValueAsString(resource);
        return json;
    }
}
