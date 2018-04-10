package com.creang.service.v2;

import com.creang.common.Util;
import com.creang.db.ConnectionPoolHelper;
import com.creang.model.v2.Driver;
import com.creang.model.v2.*;
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
        String sql2 = "select Id, RaceId, LegNumber, LegName, HasResult, LuckyHorse, ReserveOrder, MarksQuantity, SystemsLeft, Value from leg where RaceCardId = ? order by LegNumber";
        String sql3 = "select RaceDayDate, PostTime, RaceNumber, AtgTrackCode, TrackName, Updated, RaceName, LongDesc, ShortDesc, Distance, StartMethod, TrackSurface, TrackState, Monte, Gallop, WinTurnOver from race where Id = ?";
        String sql4 = "select p.StartNumber, p.Distance, p.StartPosition, p.Scratched, p.ScratchedReason, p.DriverChanged, p.DriverColor, p.WinnerOdds, p.InvestmentWinner, p.CardWeight, p.CardWeightChanged, p.ConditionWeight, p.ParWeight1, p.ParWeight2, p.PlusNumberWeight, " +
                "h.Name, h.Age, h.Gender, h.ForeShoes, h.HindShoes, h.Sire, h.Dam, h.DamSire, h.HomeTrack, h.Color, h.BlinkersType, h.Rating, " +
                "d.FirstName, d.LastName, d.ShortName, d.Amateur, d.ApprenticeAmateur, d.ApprenticePro, " +
                "t.FirstName, t.LastName, t.ShortName, t.Amateur, t.ApprenticeAmateur, t.ApprenticePro, " +
                "lp.Percentage, lp.Quantity, lp.LegWinner " +
                "from participant p inner join horse h on p.Id = h.Id " +
                "inner join driver d on p.Id = d.Id " +
                "inner join trainer t on p.Id = t.Id " +
                "inner join legparticipant lp on lp.ParticipantId = p.Id and lp.LegId = ? " +
                "where p.RaceId = ? order by p.StartNumber";

        try (Connection conn = connectionPoolHelper.getDataSource().getConnection()) {
            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                    try (PreparedStatement ps3 = conn.prepareStatement(sql3)) {
                        try (PreparedStatement ps4 = conn.prepareStatement(sql4)) {

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

                                            ps4.setInt(1, leg.getId());
                                            ps4.setInt(2, leg.getRaceId());

                                            ResultSet rs4 = ps4.executeQuery();

                                            while (rs4 != null && rs4.next()) {

                                                Participant p = new Participant();
                                                p.setStartNumber(rs4.getInt(1));
                                                p.setDistance(rs4.getInt(2));
                                                p.setStartPosition(rs4.getInt(3));
                                                p.setScratched(rs4.getBoolean(4));
                                                p.setScratchedReason(rs4.getString(5) != null ? rs4.getString(5) : "");
                                                p.setDriverChanged(rs4.getBoolean(6));
                                                p.setDriverColor(rs4.getString(7) != null ? rs4.getString(7) : "");
                                                p.setWinnerOdds(rs4.getBigDecimal(8));
                                                p.setInvestmentWinner(rs4.getBigDecimal(9));
                                                p.setCardWeight(rs4.getString(10) != null ? rs4.getString(10) : "");
                                                p.setCardWeightChanged(rs4.getBoolean(11));
                                                p.setConditionWeight(rs4.getString(12) != null ? rs4.getString(12) : "");
                                                p.setParWeight1(rs4.getString(13) != null ? rs4.getString(13) : "");
                                                p.setParWeight2(rs4.getString(14) != null ? rs4.getString(14) : "");
                                                p.setPlusNumberWeight(rs4.getString(15) != null ? rs4.getString(15) : "");

                                                Horse horse = new Horse();
                                                horse.setName(rs4.getString(16));
                                                horse.setAge(rs4.getInt(17));
                                                horse.setGender(rs4.getString(18) != null ? rs4.getString(18) : "");
                                                horse.setForeShoes(rs4.getInt(19));
                                                horse.setHindShoes(rs4.getInt(20));
                                                horse.setSire(rs4.getString(21) != null ? rs4.getString(21) : "");
                                                horse.setDam(rs4.getString(22) != null ? rs4.getString(22) : "");
                                                horse.setDamSire(rs4.getString(23) != null ? rs4.getString(23) : "");
                                                horse.setHomeTrack(rs4.getString(24) != null ? rs4.getString(24) : "");
                                                horse.setColor(rs4.getString(25) != null ? rs4.getString(25) : "");
                                                horse.setBlinkerType(rs4.getString(26) != null ? rs4.getString(26) : "");
                                                horse.setRating(rs4.getString(27) != null ? rs4.getString(27) : "");

                                                Driver driver = new Driver();
                                                driver.setFirstName(rs4.getString(28) != null ? rs4.getString(28) : "");
                                                driver.setLastName(rs4.getString(29) != null ? rs4.getString(29) : "");
                                                driver.setShortName(rs4.getString(30) != null ? rs4.getString(30) : "");
                                                driver.setAmateur(rs4.getBoolean(31));
                                                driver.setApprenticeAmateur(rs4.getBoolean(32));
                                                driver.setApprenticePro(rs4.getBoolean(33));
                                                horse.setDriver(driver);

                                                Trainer trainer = new Trainer();
                                                trainer.setFirstName(rs4.getString(34) != null ? rs4.getString(34) : "");
                                                trainer.setLastName(rs4.getString(35) != null ? rs4.getString(35) : "");
                                                trainer.setShortName(rs4.getString(36) != null ? rs4.getString(36) : "");
                                                trainer.setAmateur(rs4.getBoolean(37));
                                                trainer.setApprenticeAmateur(rs4.getBoolean(38));
                                                trainer.setApprenticePro(rs4.getBoolean(39));
                                                horse.setTrainer(trainer);

                                                p.setPercentage(rs4.getBigDecimal(40));
                                                p.setQuantity(rs4.getInt(41));
                                                p.setLegWinner(rs4.getBoolean(42));

                                                p.setHorse(horse);


                                                leg.getParticipants().add(p);
                                            }
                                        }
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
