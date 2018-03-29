package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Participant {

    private int startNumber;
    private BigDecimal percentage;
    private BigDecimal winnerOdds;
    private boolean scratched;
    private int distance;
    private int startPosition;
    private Horse horse;

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public void setWinnerOdds(BigDecimal winnerOdds) {
        this.winnerOdds = winnerOdds;
    }

    public void setScratched(boolean scratched) {
        this.scratched = scratched;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    @JsonProperty("StartNumber")
    public int getStartNumber() {
        return startNumber;
    }

    @JsonProperty("Percentage")
    public BigDecimal getPercentage() {
        return percentage;
    }

    @JsonProperty("WinnerOdds")
    public BigDecimal getWinnerOdds() {
        return winnerOdds;
    }

    @JsonProperty("Scratched")
    public boolean isScratched() {
        return scratched;
    }

    @JsonProperty("Distance")
    public int getDistance() {
        return distance;
    }

    @JsonProperty("StartPosition")
    public int getStartPosition() {
        return startPosition;
    }

    @JsonProperty("Horse")
    public Horse getHorse() {
        return horse;
    }
}
