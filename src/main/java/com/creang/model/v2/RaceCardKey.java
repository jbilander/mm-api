package com.creang.model.v2;

import java.time.LocalDate;

public class RaceCardKey {

    private String betType;
    private int atgTrackId;
    private String atgTrackCode;
    private LocalDate raceDayDate;

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

    public LocalDate getRaceDayDate() {
        return raceDayDate;
    }

    public void setRaceDayDate(LocalDate raceDayDate) {
        this.raceDayDate = raceDayDate;
    }
}
