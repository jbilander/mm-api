package com.creang.service.v1;

import com.creang.common.Util;
import com.creang.model.v1.RaceCard;
import com.creang.model.v1.RaceDay;
import com.creang.model.v1.SimpleRaceCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelService {

    private final FetchRaceDayService fetchRaceDayService = new FetchRaceDayService();

    public List<SimpleRaceCard> fetchRaceCards(LocalDate localDate) {
        List<SimpleRaceCard> simpleRaceCards = new ArrayList<>();
        return simpleRaceCards;
    }

    public RaceCard fetchRaceCard(int raceCardId) {
        return new RaceCard();
    }

    public List<RaceDay> fetchRaceDays() {
        return fetchRaceDayService.fetch(LocalDate.now(Util.ZONE_ID_UTC_MINUS_TWO));
    }
}
