package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class SimpleRaceCard {

    private int id;
    private int raceCardTrackId;
    private String firstLegPostTimeUtc;
    private BetType betType;
    private Set<Track> tracks;

    public void setId(int id) {
        this.id = id;
    }

    public void setRaceCardTrackId(int raceCardTrackId) {
        this.raceCardTrackId = raceCardTrackId;
    }

    public void setFirstLegPostTimeUtc(String firstLegPostTimeUtc) {
        this.firstLegPostTimeUtc = firstLegPostTimeUtc;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    @JsonProperty("Id")
    public int getId() {
        return id;
    }

    @JsonProperty("RaceCardTrackId")
    public int getRaceCardTrackId() {
        return raceCardTrackId;
    }

    @JsonProperty("FirstLegPostTimeUtc")
    public String getFirstLegPostTimeUtc() {
        return firstLegPostTimeUtc;
    }

    @JsonProperty("BetType")
    public BetType getBetType() {
        return betType;
    }

    @JsonProperty("Tracks")
    public Set<Track> getTracks() {

        if (tracks == null) {
            tracks = new LinkedHashSet<>();
        }

        return tracks;
    }
}
