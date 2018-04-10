package com.creang.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Leg {

    private int id;
    private int raceId;
    private int legNumber;
    private String legName;
    private boolean hasResult;
    private String luckyHorse;
    private String reserveOrder;
    private int marksQuantity;
    private int systemsLeft;
    private int value;
    private String raceDayPostTime; //yyyy-MM-ddTHH:mm:SS
    private int raceNumber;
    private String atgTrackCode;
    private String trackName;
    private String updated; //yyyy-MM-ddTHH:mm:SS
    private String raceName;
    private String longDesc;
    private String shortDesc;
    private int distance;
    private String startMethod;
    private String trackSurface;
    private String trackState;
    private boolean monte;
    private boolean gallop;
    private BigDecimal winTurnOver;
    private List<Participant> participants;

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getLegNumber() {
        return legNumber;
    }

    public void setLegNumber(int legNumber) {
        this.legNumber = legNumber;
    }

    public String getLegName() {
        return legName;
    }

    public void setLegName(String legName) {
        this.legName = legName;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

    public String getLuckyHorse() {
        return luckyHorse;
    }

    public void setLuckyHorse(String luckyHorse) {
        this.luckyHorse = luckyHorse;
    }

    public String getReserveOrder() {
        return reserveOrder;
    }

    public void setReserveOrder(String reserveOrder) {
        this.reserveOrder = reserveOrder;
    }

    public int getMarksQuantity() {
        return marksQuantity;
    }

    public void setMarksQuantity(int marksQuantity) {
        this.marksQuantity = marksQuantity;
    }

    public int getSystemsLeft() {
        return systemsLeft;
    }

    public void setSystemsLeft(int systemsLeft) {
        this.systemsLeft = systemsLeft;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRaceDayPostTime() {
        return raceDayPostTime;
    }

    public void setRaceDayPostTime(String raceDayPostTime) {
        this.raceDayPostTime = raceDayPostTime;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public String getAtgTrackCode() {
        return atgTrackCode;
    }

    public void setAtgTrackCode(String atgTrackCode) {
        this.atgTrackCode = atgTrackCode;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getStartMethod() {
        return startMethod;
    }

    public void setStartMethod(String startMethod) {
        this.startMethod = startMethod;
    }

    public String getTrackSurface() {
        return trackSurface;
    }

    public void setTrackSurface(String trackSurface) {
        this.trackSurface = trackSurface;
    }

    public String getTrackState() {
        return trackState;
    }

    public void setTrackState(String trackState) {
        this.trackState = trackState;
    }

    public boolean isMonte() {
        return monte;
    }

    public void setMonte(boolean monte) {
        this.monte = monte;
    }

    public boolean isGallop() {
        return gallop;
    }

    public void setGallop(boolean gallop) {
        this.gallop = gallop;
    }

    public BigDecimal getWinTurnOver() {
        return winTurnOver;
    }

    public void setWinTurnOver(BigDecimal winTurnOver) {
        this.winTurnOver = winTurnOver;
    }

    public List<Participant> getParticipants() {

        if (participants == null) {
            participants = new ArrayList<>();
        }

        return participants;
    }
}
