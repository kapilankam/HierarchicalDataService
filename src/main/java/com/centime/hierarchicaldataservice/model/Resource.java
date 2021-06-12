package com.centime.hierarchicaldataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Resource {
    private int id;
    private int parentId;
    private String name;
    private String color;

    private List<Resource> childern = new ArrayList<>();

    public List<Resource> getChildern() {
        return childern;
    }

    public void addChildren(Resource r) {
        this.childern.add(r);
    }

    public void setChildern(List<Resource> childern) {
        this.childern = childern;
    }


    public Resource() {
    }

    public Resource(int id, int parentId, String name, String color) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.color = color;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
