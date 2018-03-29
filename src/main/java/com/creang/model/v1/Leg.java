package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Leg {

    private int legNumber;
    private String legName;
    private String reserveOrder;
    private int raceNumber;
    private int distance;
    private String postTimeUtc;
    private String raceName;
    private String shortDescription;
    private BigDecimal turnOverWinBets;
    private String currency = "SEK";
    private String startMethodCode;
    private Track track;
    private List<Participant> participants;

    public void setLegNumber(int legNumber) {
        this.legNumber = legNumber;
    }

    public void setLegName(String legName) {
        this.legName = legName;
    }

    public void setReserveOrder(String reserveOrder) {
        this.reserveOrder = reserveOrder;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPostTimeUtc(String postTimeUtc) {
        this.postTimeUtc = postTimeUtc;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setTurnOverWinBets(BigDecimal turnOverWinBets) {
        this.turnOverWinBets = turnOverWinBets;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setStartMethodCode(String startMethodCode) {
        this.startMethodCode = startMethodCode;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @JsonProperty("LegNumber")
    public int getLegNumber() {
        return legNumber;
    }

    @JsonProperty("LegName")
    public String getLegName() {
        return legName;
    }

    @JsonProperty("ReserveOrder")
    public String getReserveOrder() {
        return reserveOrder;
    }

    @JsonProperty("RaceNumber")
    public int getRaceNumber() {
        return raceNumber;
    }

    @JsonProperty("Distance")
    public int getDistance() {
        return distance;
    }

    @JsonProperty("PostTimeUtc")
    public String getPostTimeUtc() {
        return postTimeUtc;
    }

    @JsonProperty("RaceName")
    public String getRaceName() {
        return raceName;
    }

    @JsonProperty("ShortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("TurnOverWinBets")
    public BigDecimal getTurnOverWinBets() {
        return turnOverWinBets;
    }

    @JsonProperty("Currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("StartMethodCode")
    public String getStartMethodCode() {
        return startMethodCode;
    }

    @JsonProperty("Track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("Participants")
    public List<Participant> getParticipants() {

        if (participants == null) {
            participants = new ArrayList<>();
        }

        return participants;
    }
}
