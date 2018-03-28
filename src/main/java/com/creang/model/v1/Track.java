package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id &&
                Objects.equals(code, track.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
