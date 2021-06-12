package com.centime.hierarchicaldataservice.dao;

import com.centime.hierarchicaldataservice.model.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResourceResultExtracter implements ResultSetExtractor<Map<Integer, Resource>> {
    @Override
    public Map<Integer, Resource> extractData(
            ResultSet rs) throws SQLException, DataAccessException {

        Map<Integer, Resource> map = new HashMap<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int parent_id = rs.getInt("parent_id");
            String name = rs.getString("name");
            String color = rs.getString("color");
            map.put(id, new Resource(id, parent_id, name, color));
        }
        return map;
    }
}
