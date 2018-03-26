package com.creang.service.v1;

import com.creang.common.Util;
import com.creang.db.ConnectionPoolHelper;
import com.creang.model.v1.RaceDay;
import com.creang.model.v1.Track;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class FetchRaceDayService {

    private final Logger logger = Logger.getLogger(FetchRaceDayService.class.getName());
    private final ConnectionPoolHelper connectionPoolHelper = ConnectionPoolHelper.getInstance();

    public Collection<RaceDay> fetch(LocalDate date) {

        LinkedHashMap<LocalDate, RaceDay> raceDays = new LinkedHashMap<>();

        String sql = "select rc.RaceDayDate, r.AtgTrackId, r.AtgTrackCode, r.TrackName from racecard rc " +
                "inner join leg l on rc.Id = l.RaceCardId inner join race r on l.RaceId = r.Id " +
                "where rc.Activated = 1 and rc.RaceDayDate >= ? " +
                "group by rc.RaceDayDate, r.AtgTrackId, r.AtgTrackCode, r.TrackName " +
                "order by rc.RaceDayDate, min(r.PostTime)";


        try (Connection conn = connectionPoolHelper.getDataSource().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setDate(1, Date.valueOf(date));

                ResultSet rs = ps.executeQuery();

                while (rs != null && rs.next()) {

                    LocalDate raceDayDate = rs.getDate(1).toLocalDate();

                    if (!raceDays.containsKey(raceDayDate)) {
                        RaceDay raceDay = new RaceDay();
                        raceDay.setDateTimeUtc(Util.getPostTimeUtc(raceDayDate, Time.valueOf(LocalTime.MIN), Util.ZONE_ID_UTC));
                        raceDays.put(raceDayDate, raceDay);
                    }

                    Track track = new Track();
                    track.setId(rs.getInt(2));
                    track.setCode(rs.getString(3));
                    track.setName(rs.getString(4));
                    raceDays.get(raceDayDate).getTracks().add(track);
                }
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }

        return raceDays.values();
    }
}
