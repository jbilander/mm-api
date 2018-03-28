package com.creang.service.v1;

import com.creang.common.Util;
import com.creang.db.ConnectionPoolHelper;
import com.creang.model.v1.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class FetchRaceCardService {

    private final Logger logger = Logger.getLogger(FetchRaceDayService.class.getName());
    private final ConnectionPoolHelper connectionPoolHelper = ConnectionPoolHelper.getInstance();

    public Collection<SimpleRaceCard> fetch(LocalDate date) {

        LinkedHashMap<Integer, SimpleRaceCard> raceCards = new LinkedHashMap<>();

        String sql = "select rc.Id, rc.RaceDayDate, rc.BetType, rc.AtgTrackId, r.AtgTrackId, r.AtgTrackCode, r.TrackName, min(r.PostTime) as PostTime from racecard rc " +
                "inner join leg l on rc.Id = l.RaceCardId " +
                "inner join race r on l.RaceId = r.Id " +
                "where rc.Activated = 1 and rc.RaceDayDate = ? " +
                "group by rc.RaceDayDate, rc.BetType, rc.AtgTrackId, r.AtgTrackId, r.AtgTrackCode " +
                "order by rc.RaceDayDate, min(r.PostTime)";


        try (Connection conn = connectionPoolHelper.getDataSource().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setDate(1, Date.valueOf(date));

                ResultSet rs = ps.executeQuery();

                while (rs != null && rs.next()) {

                    int raceCardId = rs.getInt(1);

                    if (!raceCards.containsKey(raceCardId)) {
                        SimpleRaceCard rc = new SimpleRaceCard();
                        rc.setId(raceCardId);
                        BetType betType = new BetType();
                        betType.setCode(Util.getBetTypeCode(rs.getString(3)));
                        betType.setDescription(rs.getString(3));
                        rc.setBetType(betType);
                        rc.setRaceCardTrackId(rs.getInt(4));
                        rc.setFirstLegPostTimeUtc(Util.getDateTimeUtc(rs.getDate(2).toLocalDate(), rs.getTime(8).toLocalTime(), Util.ZONE_ID_STHLM));
                        raceCards.put(raceCardId, rc);
                    }

                    Track track = new Track();
                    track.setId(rs.getInt(5));
                    track.setCode(rs.getString(6));
                    track.setName(rs.getString(7));

                    if (!raceCards.get(raceCardId).getTracks().contains(track)) {
                        raceCards.get(raceCardId).getTracks().add(track);
                    }
                }
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }

        return raceCards.values();
    }

    public RaceCard fetch(int raceCardId) {

        RaceCard raceCard = null;
        String sql1 = "select Id, BetType, AtgTrackId, TurnOver, Updated, MadeBetsQuantity  from racecard where id = ?";
        String sql2 = "select l.Id, l.LegNumber, l.LegName, l.ReserveOrder, r.RaceNumber, r.Distance, r.RaceDayDate, r.PostTime, " +
                "r.RaceName, r.ShortDesc, r.WinTurnOver, r.StartMethod, r.AtgTrackId, r.AtgTrackCode, r.TrackName from leg l " +
                "inner join race r on l.RaceId = r.Id where RaceCardId = ? order by LegNumber";

        try (Connection conn = connectionPoolHelper.getDataSource().getConnection()) {
            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {

                    ps1.setInt(1, raceCardId);
                    ResultSet rs1 = ps1.executeQuery();

                    if (rs1 != null && rs1.next()) {
                        raceCard = new RaceCard();
                        raceCard.setId(rs1.getInt(1));
                        BetType betType = new BetType();
                        betType.setCode(Util.getBetTypeCode(rs1.getString(2)));
                        betType.setDescription(rs1.getString(2));
                        raceCard.setBetType(betType);
                        raceCard.setRaceCardTrackId(rs1.getInt(3));
                        raceCard.setTurnOver(rs1.getBigDecimal(4));
                        raceCard.setTimeStampLatestUpdateUtc(Util.getDateTimeUtc(rs1.getTimestamp(5), Util.ZONE_ID_STHLM));
                        raceCard.setMadeBetsQuantity(rs1.getInt(6));

                        ps2.setInt(1, raceCardId);
                        ResultSet rs2 = ps2.executeQuery();

                        while (rs2 != null && rs2.next()) {

                            Leg leg = new Leg();
                            int legId = rs2.getInt(1);
                            leg.setLegNumber(rs2.getInt(2));
                            leg.setLegName(rs2.getString(3));
                            leg.setReserveOrder(rs2.getString(4) != null ? rs2.getString(4) : "");
                            leg.setRaceNumber(rs2.getInt(5));
                            leg.setDistance(rs2.getInt(6));
                            leg.setPostTimeUtc(Util.getDateTimeUtc(rs2.getDate(7).toLocalDate(), rs2.getTime(8).toLocalTime(), Util.ZONE_ID_STHLM));
                            leg.setRaceName(rs2.getString(9));
                            leg.setShortDescription(rs2.getString(10));
                            leg.setTurnOverWinBets(rs2.getBigDecimal(11));
                            leg.setStartMethodCode(rs2.getString(12) != null ? rs2.getString(12) : "");
                            Track track = new Track();
                            track.setId(rs2.getInt(13));
                            track.setCode(rs2.getString(14));
                            track.setName(rs2.getString(15));
                            leg.setTrack(track);

                            if (leg.getLegNumber() == 1) {
                                raceCard.setFirstLegPostTimeUtc(leg.getPostTimeUtc());
                            }

                            if (!raceCard.getTracks().contains(track)) {
                                raceCard.getTracks().add(track);
                            }

                            raceCard.getLegs().add(leg);

                            //TODO: add participants
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
