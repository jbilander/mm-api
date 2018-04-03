package com.creang.service.v2;

import com.creang.common.Util;
import com.creang.db.ConnectionPoolHelper;
import com.creang.model.v2.Leg;
import com.creang.model.v2.RaceCard;
import com.creang.model.v2.RaceCardKey;
import com.creang.service.v1.FetchRaceDayService;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class FetchRaceCardService {

    private final Logger logger = Logger.getLogger(FetchRaceDayService.class.getName());
    private final ConnectionPoolHelper connectionPoolHelper = ConnectionPoolHelper.getInstance();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public RaceCard fetch(RaceCardKey raceCardKey) {

        RaceCard raceCard = null;

        String sql1 = "select Id, BetType, AtgTrackId, AtgTrackCode, RaceDayDate, JackpotSum, TurnOver, TrackName, Updated, MadeBetsQuantity, BoostNumber, HasResult, HasCompleteResult from racecard where BetType = ? and AtgTrackId = ? and AtgTrackCode = ? and RaceDayDate = ? and Activated = 1";
        String sql2 = "select Id, RaceId, LegNumber, LegName, HasResult, LuckyHorse, ReserveOrder, MarksQuantity, SystemsLeft, Value from Leg where RaceCardId = ? order by LegNumber";
        String sql3 = "select RaceDayDate, PostTime, RaceNumber, AtgTrackCode, TrackName, Updated, RaceName, LongDesc, ShortDesc, Distance, StartMethod, TrackSurface, TrackState, Monte, Gallop, WinTurnOver from race where Id = ?";

        try (Connection conn = connectionPoolHelper.getDataSource().getConnection()) {
            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                    try (PreparedStatement ps3 = conn.prepareStatement(sql3)) {

                        ps1.setString(1, raceCardKey.getBetType());
                        ps1.setInt(2, raceCardKey.getAtgTrackId());
                        ps1.setString(3, raceCardKey.getAtgTrackCode());
                        ps1.setDate(4, Date.valueOf(raceCardKey.getRaceDayDate()));

                        ResultSet rs1 = ps1.executeQuery();

                        if (rs1 != null && rs1.next()) {

                            raceCard = new RaceCard();
                            raceCard.setBetType(rs1.getString(2));
                            raceCard.setAtgTrackId(rs1.getInt(3));
                            raceCard.setAtgTrackCode(rs1.getString(4));
                            raceCard.setRaceDayDate(sdf.format(rs1.getDate(5)));
                            raceCard.setJackpotSum(rs1.getBigDecimal(6));
                            raceCard.setTurnOver(rs1.getBigDecimal(7));
                            raceCard.setTrackName(rs1.getString(8));
                            raceCard.setUpdated(Util.getDateTimeLocal(rs1.getTimestamp(9), Util.ZONE_ID_STHLM));
                            raceCard.setMadeBetsQuantity(rs1.getInt(10));
                            raceCard.setBoostNumber(rs1.getString(11) != null ? rs1.getString(11) : "");
                            raceCard.setHasResult(rs1.getBoolean(12));
                            raceCard.setHasCompleteResult(rs1.getBoolean(13));

                            ps2.setInt(1, rs1.getInt(1));
                            ResultSet rs2 = ps2.executeQuery();

                            while (rs2 != null && rs2.next()) {

                                Leg leg = new Leg();
                                leg.setId(rs2.getInt(1));
                                leg.setRaceId(rs2.getInt(2));
                                leg.setLegNumber(rs2.getInt(3));
                                leg.setLegName(rs2.getString(4));
                                leg.setHasResult(rs2.getBoolean(5));
                                leg.setLuckyHorse(rs2.getString(6) != null ? rs2.getString(6) : "");
                                leg.setReserveOrder(rs2.getString(7) != null ? rs2.getString(7) : "");
                                leg.setMarksQuantity(rs2.getInt(8));
                                leg.setSystemsLeft(rs2.getInt(9));
                                leg.setValue(rs2.getInt(10));

                                raceCard.getLegs().add(leg);

                                ps3.setInt(1, leg.getRaceId());
                                try (ResultSet rs3 = ps3.executeQuery()) {

                                    if (rs3 != null && rs3.next()) {

                                        leg.setRaceDayPostTime(Util.getDateTimeLocal(rs3.getDate(1), rs3.getTime(2), Util.ZONE_ID_STHLM));
                                        leg.setRaceNumber(rs3.getInt(3));
                                        leg.setAtgTrackCode(rs3.getString(4));
                                        leg.setTrackName(rs3.getString(5));
                                        leg.setUpdated(Util.getDateTimeLocal(rs3.getTimestamp(6), Util.ZONE_ID_STHLM));
                                        leg.setRaceName(rs3.getString(7));
                                        leg.setLongDesc(rs3.getString(8));
                                        leg.setShortDesc(rs3.getString(9));
                                        leg.setDistance(rs3.getInt(10));
                                        leg.setStartMethod(rs3.getString(11));
                                        leg.setTrackSurface(rs3.getString(12) != null ? rs3.getString(12) : "");
                                        leg.setTrackState(rs3.getString(13) != null ? rs3.getString(13) : "");
                                        leg.setMonte(rs3.getBoolean(14));
                                        leg.setGallop(rs3.getBoolean(15));
                                        leg.setWinTurnOver(rs3.getBigDecimal(16));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }

        return raceCard;
    }
}
