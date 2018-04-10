package com.creang.model.v2;

import java.math.BigDecimal;

public class Participant {

    private int startNumber;
    private int distance;
    private int startPosition;
    private boolean scratched;
    private String scratchedReason;
    private boolean driverChanged;
    private String driverColor;
    private BigDecimal winnerOdds;
    private BigDecimal investmentWinner;
    private String cardWeight;
    private boolean cardWeightChanged;
    private String conditionWeight;
    private String parWeight1;
    private String parWeight2;
    private String plusNumberWeight;
    private BigDecimal percentage;
    private int quantity;
    private boolean legWinner;
    private Horse horse;

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public boolean isScratched() {
        return scratched;
    }

    public void setScratched(boolean scratched) {
        this.scratched = scratched;
    }

    public String getScratchedReason() {
        return scratchedReason;
    }

    public void setScratchedReason(String scratchedReason) {
        this.scratchedReason = scratchedReason;
    }

    public boolean isDriverChanged() {
        return driverChanged;
    }

    public void setDriverChanged(boolean driverChanged) {
        this.driverChanged = driverChanged;
    }

    public String getDriverColor() {
        return driverColor;
    }

    public void setDriverColor(String driverColor) {
        this.driverColor = driverColor;
    }

    public BigDecimal getWinnerOdds() {
        return winnerOdds;
    }

    public void setWinnerOdds(BigDecimal winnerOdds) {
        this.winnerOdds = winnerOdds;
    }

    public BigDecimal getInvestmentWinner() {
        return investmentWinner;
    }

    public void setInvestmentWinner(BigDecimal investmentWinner) {
        this.investmentWinner = investmentWinner;
    }

    public String getCardWeight() {
        return cardWeight;
    }

    public void setCardWeight(String cardWeight) {
        this.cardWeight = cardWeight;
    }

    public boolean isCardWeightChanged() {
        return cardWeightChanged;
    }

    public void setCardWeightChanged(boolean cardWeightChanged) {
        this.cardWeightChanged = cardWeightChanged;
    }

    public String getConditionWeight() {
        return conditionWeight;
    }

    public void setConditionWeight(String conditionWeight) {
        this.conditionWeight = conditionWeight;
    }

    public String getParWeight1() {
        return parWeight1;
    }

    public void setParWeight1(String parWeight1) {
        this.parWeight1 = parWeight1;
    }

    public String getParWeight2() {
        return parWeight2;
    }

    public void setParWeight2(String parWeight2) {
        this.parWeight2 = parWeight2;
    }

    public String getPlusNumberWeight() {
        return plusNumberWeight;
    }

    public void setPlusNumberWeight(String plusNumberWeight) {
        this.plusNumberWeight = plusNumberWeight;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isLegWinner() {
        return legWinner;
    }

    public void setLegWinner(boolean legWinner) {
        this.legWinner = legWinner;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}
