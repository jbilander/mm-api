package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Horse {

    private String name;
    private int age;
    private int horseGender;
    private int foreShoes;
    private int hindShoes;
    private Driver driver;
    private Trainer trainer;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHorseGender(int horseGender) {
        this.horseGender = horseGender;
    }

    public void setForeShoes(int foreShoes) {
        this.foreShoes = foreShoes;
    }

    public void setHindShoes(int hindShoes) {
        this.hindShoes = hindShoes;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Age")
    public int getAge() {
        return age;
    }

    @JsonProperty("HorseGender")
    public int getHorseGender() {
        return horseGender;
    }

    @JsonProperty("ForeShoes")
    public int getForeShoes() {
        return foreShoes;
    }

    @JsonProperty("HindShoes")
    public int getHindShoes() {
        return hindShoes;
    }

    @JsonProperty("Driver")
    public Driver getDriver() {
        return driver;
    }

    @JsonProperty("Trainer")
    public Trainer getTrainer() {
        return trainer;
    }
}
