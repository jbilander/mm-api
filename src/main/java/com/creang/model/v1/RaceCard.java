package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RaceCard extends SimpleRaceCard {

    private String timeStampLatestUpdateUtc;
    private BigDecimal turnOver;
    private String currency = "SEK";
    private int madeBetsQuantity;
    private List<Leg> legs;

    public void setTimeStampLatestUpdateUtc(String timeStampLatestUpdateUtc) {
        this.timeStampLatestUpdateUtc = timeStampLatestUpdateUtc;
    }

    public void setTurnOver(BigDecimal turnOver) {
        this.turnOver = turnOver;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMadeBetsQuantity(int madeBetsQuantity) {
        this.madeBetsQuantity = madeBetsQuantity;
    }

    @JsonProperty("TimeStampLatestUpdateUtc")
    public String getTimeStampLatestUpdateUtc() {
        return timeStampLatestUpdateUtc;
    }

    @JsonProperty("TurnOver")
    public BigDecimal getTurnOver() {
        return turnOver;
    }

    @JsonProperty("Currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("MadeBetsQuantity")
    public int getMadeBetsQuantity() {
        return madeBetsQuantity;
    }

    @JsonProperty("Legs")
    public List<Leg> getLegs() {

        if (legs == null) {
            legs = new ArrayList<>();
        }

        return legs;
    }
}
