package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class RaceDay {

    private String dateTimeUtc;
    private Set<Track> tracks;

    public void setDateTimeUtc(String dateTimeUtc) {
        this.dateTimeUtc = dateTimeUtc;
    }

    @JsonProperty("DateTimeUtc")
    public String getDateTimeUtc() {
        return dateTimeUtc;
    }

    @JsonProperty("Tracks")
    public Set<Track> getTracks() {

        if (tracks == null) {
            tracks = new LinkedHashSet<>();
        }

        return tracks;
    }
}
