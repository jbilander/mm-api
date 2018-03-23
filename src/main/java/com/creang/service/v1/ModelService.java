package com.creang.service.v1;

import com.creang.model.v1.RaceCard;
import com.creang.model.v1.RaceDay;
import com.creang.model.v1.SimpleRaceCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelService {

    private final DbService dbService = new DbService();

    public List<SimpleRaceCard> fetchRaceCards(LocalDate localDate) {
        List<SimpleRaceCard> simpleRaceCards = new ArrayList<>();
        return simpleRaceCards;
    }

    public RaceCard fetchRaceCard(int raceCardId) {
        return new RaceCard();
    }

    public List<RaceDay> fetchRaceDays() {
        List<RaceDay> raceDays = new ArrayList<>();
        return raceDays;
    }
}
