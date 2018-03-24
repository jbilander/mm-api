package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    private int id;
    private String code;
    private String name;

    @JsonProperty("Id")
    public int getId() {
        return id;
    }

    @JsonProperty("Code")
    public String getCode() {
        return code;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
