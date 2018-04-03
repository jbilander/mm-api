package com.creang.model.v2;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RaceCard {

    private String betType;
    private int atgTrackId;
    private String atgTrackCode;
    private String raceDayDate; //yyyy-MM-dd
    private BigDecimal jackpotSum;
    private BigDecimal turnOver;
    private String trackName;
    private String updated; //yyyy-MM-ddTHH:mm:SS
    private int madeBetsQuantity;
    private String boostNumber;
    private boolean hasResult;
    private boolean hasCompleteResult;

    private List<Leg> legs;
    //private Set<Track> tracks;

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public int getAtgTrackId() {
        return atgTrackId;
    }

    public void setAtgTrackId(int atgTrackId) {
        this.atgTrackId = atgTrackId;
    }

    public String getAtgTrackCode() {
        return atgTrackCode;
    }

    public void setAtgTrackCode(String atgTrackCode) {
        this.atgTrackCode = atgTrackCode;
    }

    public String getRaceDayDate() {
        return raceDayDate;
    }

    public void setRaceDayDate(String raceDayDate) {
        this.raceDayDate = raceDayDate;
    }

    public BigDecimal getJackpotSum() {
        return jackpotSum;
    }

    public void setJackpotSum(BigDecimal jackpotSum) {
        this.jackpotSum = jackpotSum;
    }

    public BigDecimal getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(BigDecimal turnOver) {
        this.turnOver = turnOver;
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

    public int getMadeBetsQuantity() {
        return madeBetsQuantity;
    }

    public void setMadeBetsQuantity(int madeBetsQuantity) {
        this.madeBetsQuantity = madeBetsQuantity;
    }

    public String getBoostNumber() {
        return boostNumber;
    }

    public void setBoostNumber(String boostNumber) {
        this.boostNumber = boostNumber;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

    public boolean isHasCompleteResult() {
        return hasCompleteResult;
    }

    public void setHasCompleteResult(boolean hasCompleteResult) {
        this.hasCompleteResult = hasCompleteResult;
    }

    public List<Leg> getLegs() {

        if (legs == null) {
            legs = new ArrayList<>();
        }

        return legs;
    }
}
