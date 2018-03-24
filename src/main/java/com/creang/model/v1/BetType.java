package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BetType {

    private int code;
    private String description;

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Code")
    public int getCode() {
        return code;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }
}
