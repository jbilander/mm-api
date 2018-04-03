package com.creang.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
}
