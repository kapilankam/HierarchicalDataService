package com.centime.hierarchicaldataservice.dao.impl;

import com.centime.hierarchicaldataservice.aop.MethodParams;
import com.centime.hierarchicaldataservice.dao.ResourceDao;
import com.centime.hierarchicaldataservice.dao.ResourceResultExtracter;
import com.centime.hierarchicaldataservice.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ResourceDaoImpl implements ResourceDao {

    public static String RESOURCE_DAO$GET_CHILDSBY_PARENT_ID = "resource_dao$get_child_idsByParentID";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = String.format("select * from %s(?) ", RESOURCE_DAO$GET_CHILDSBY_PARENT_ID);

    @Override
    @MethodParams
    public Map<Integer, Resource> getResourceById(int groupId) {
        Map<Integer, Resource> output = jdbcTemplate.query(SQL_SELECT_BY_ID, new ResourceResultExtracter(), groupId);
        return output;
    }

    @Override
    public List<Resource> getAllResource() {
        return null;
    }
}