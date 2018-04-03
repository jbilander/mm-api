package com.creang.service.v2;


import com.creang.model.v2.RaceCard;
import com.creang.model.v2.RaceCardKey;

public class ModelService {

    private final FetchRaceCardService fetchRaceCardService = new FetchRaceCardService();

    public RaceCard fetchRaceCard(RaceCardKey raceCardKey) {
        return fetchRaceCardService.fetch(raceCardKey);
    }
}
