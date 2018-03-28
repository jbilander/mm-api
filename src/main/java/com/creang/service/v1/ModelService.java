package com.creang.service.v1;

import com.creang.common.Util;
import com.creang.model.v1.RaceCard;
import com.creang.model.v1.RaceDay;
import com.creang.model.v1.SimpleRaceCard;

import java.time.LocalDate;
import java.util.Collection;

public class ModelService {

    private final FetchRaceDayService fetchRaceDayService = new FetchRaceDayService();
    private final FetchRaceCardService fetchRaceCardService = new FetchRaceCardService();

    public Collection<SimpleRaceCard> fetchRaceCards(LocalDate date) {
        return fetchRaceCardService.fetch(date);
    }

    public RaceCard fetchRaceCard(int raceCardId) {
        return fetchRaceCardService.fetch(raceCardId);
    }

    public Collection<RaceDay> fetchRaceDays() {
        return fetchRaceDayService.fetch(LocalDate.now(Util.ZONE_ID_UTC_MINUS_TWO));
    }
}
