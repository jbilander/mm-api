package com.creang.service.v1;

import com.creang.common.Util;
import com.creang.db.ConnectionPoolHelper;
import com.creang.model.v1.*;
import com.creang.model.v1.Driver;

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
        String sql1 = "select BetType, AtgTrackId, TurnOver, Updated, MadeBetsQuantity from racecard where Id = ?";
        String sql2 = "select l.Id, l.LegNumber, l.LegName, l.ReserveOrder, r.RaceNumber, r.Distance, r.RaceDayDate, r.PostTime, " +
                "r.RaceName, r.ShortDesc, r.WinTurnOver, r.StartMethod, r.AtgTrackId, r.AtgTrackCode, r.TrackName from leg l " +
                "inner join race r on l.RaceId = r.Id where RaceCardId = ? order by LegNumber";
        String sql3 = "select p.StartNumber, p.Distance, p.StartPosition, p.Scratched, p.WinnerOdds, lp.Percentage, h.Name, h.Age, h.Gender, " +
                "h.ForeShoes, h.HindShoes, d.FirstName, d.LastName, d.ShortName, d.Amateur, t.FirstName, t.LastName, t.ShortName, t.Amateur " +
                "from Participant p inner join Horse h on h.Id = p.Id inner join Driver d on d.Id = p.Id inner join Trainer t on t.Id = p.Id " +
                "inner join LegParticipant lp on lp.ParticipantId = p.Id and lp.LegId = ? order by p.StartNumber";

        try (Connection conn = connectionPoolHelper.getDataSource().getConnection()) {
            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                    try (PreparedStatement ps3 = conn.prepareStatement(sql3)) {

                        ps1.setInt(1, raceCardId);
                        ResultSet rs1 = ps1.executeQuery();

                        if (rs1 != null && rs1.next()) {

                            raceCard = new RaceCard();
                            raceCard.setId(raceCardId);
                            BetType betType = new BetType();
                            betType.setCode(Util.getBetTypeCode(rs1.getString(1)));
                            betType.setDescription(rs1.getString(1));
                            raceCard.setBetType(betType);
                            raceCard.setRaceCardTrackId(rs1.getInt(2));
                            raceCard.setTurnOver(rs1.getBigDecimal(3));
                            raceCard.setTimeStampLatestUpdateUtc(Util.getDateTimeUtc(rs1.getTimestamp(4), Util.ZONE_ID_STHLM));
                            raceCard.setMadeBetsQuantity(rs1.getInt(5));

                            ps2.setInt(1, raceCardId);
                            ResultSet rs2 = ps2.executeQuery();

                            //add legs
                            while (rs2 != null && rs2.next()) {

                                Leg leg = new Leg();
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

                                //add participants
                                ps3.setInt(1, rs2.getInt(1));
                                try (ResultSet rs3 = ps3.executeQuery()) {

                                    while (rs3 != null && rs3.next()) {

                                        Participant p = new Participant();
                                        p.setStartNumber(rs3.getInt(1));
                                        p.setDistance(rs3.getInt(2));
                                        p.setStartPosition(rs3.getInt(3));
                                        p.setScratched(rs3.getBoolean(4));
                                        p.setWinnerOdds(rs3.getBigDecimal(5));
                                        p.setPercentage(rs3.getBigDecimal(6));
                                        Horse horse = new Horse();
                                        horse.setName(rs3.getString(7));
                                        horse.setAge(rs3.getInt(8));
                                        horse.setHorseGender(Util.getHorseGenderCode(rs3.getString(9) != null ? rs3.getString(9) : ""));
                                        horse.setForeShoes(rs3.getInt(10));
                                        horse.setHindShoes(rs3.getInt(11));
                                        p.setHorse(horse);
                                        Driver driver = new Driver();
                                        driver.setFirstName(rs3.getString(12) != null ? rs3.getString(12) : "");
                                        driver.setLastName(rs3.getString(13) != null ? rs3.getString(13) : "");
                                        driver.setShortName(rs3.getString(14) != null ? rs3.getString(14) : "");
                                        driver.setAmateur(rs3.getBoolean(15));
                                        horse.setDriver(driver);
                                        Trainer trainer = new Trainer();
                                        trainer.setFirstName(rs3.getString(16) != null ? rs3.getString(16) : "");
                                        trainer.setLastName(rs3.getString(17) != null ? rs3.getString(17) : "");
                                        trainer.setShortName(rs3.getString(18) != null ? rs3.getString(18) : "");
                                        trainer.setAmateur(rs3.getBoolean(19));
                                        horse.setTrainer(trainer);
                                        leg.getParticipants().add(p);
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
