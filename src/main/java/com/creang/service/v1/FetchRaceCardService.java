package com.creang.service.v1;

import com.creang.common.Util;
import com.creang.db.ConnectionPoolHelper;
import com.creang.db.DbUtil;
import com.creang.model.v1.BetType;
import com.creang.model.v1.SimpleRaceCard;
import com.creang.model.v1.Track;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

public class FetchRaceCardService {

    private final Logger logger = Logger.getLogger(FetchRaceDayService.class.getName());
    private final ConnectionPoolHelper connectionPoolHelper = ConnectionPoolHelper.getInstance();
    private final DataSource ds = connectionPoolHelper.getDataSource();

    public List<SimpleRaceCard> fetch(LocalDate date) {

        LinkedHashMap<Integer, SimpleRaceCard> raceCards = new LinkedHashMap<>();

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement prepStmnt = null;

        try {

            String sql = "select rc.Id, rc.RaceDayDate, rc.BetType, rc.AtgTrackId, r.AtgTrackId, r.AtgTrackCode, r.TrackName, min(r.PostTime) as PostTime from racecard rc " +
                    "inner join leg l on rc.Id = l.RaceCardId " +
                    "inner join race r on l.RaceId = r.Id " +
                    "where rc.Activated = 1 and rc.RaceDayDate = ? " +
                    "group by rc.RaceDayDate, rc.BetType, rc.AtgTrackId, r.AtgTrackId, r.AtgTrackCode, r.TrackName " +
                    "order by rc.RaceDayDate, min(r.PostTime)";

            conn = ds.getConnection();

            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setDate(1, Date.valueOf(date));

            rs = prepStmnt.executeQuery();

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
                    rc.setFirstLegPostTimeUtc(Util.getPostTimeUtc(rs.getDate(2).toLocalDate(), rs.getTime(8), Util.ZONE_ID_STHLM));
                    raceCards.put(raceCardId, rc);
                }

                Track track = new Track();
                track.setId(rs.getInt(5));
                track.setCode(rs.getString(6));
                track.setName(rs.getString(7));
                raceCards.get(raceCardId).getTracks().add(track);
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        } finally {
            DbUtil.closeResultSet(rs);
            DbUtil.closeStatement(prepStmnt);
            DbUtil.closeConnection(conn);
        }

        return new ArrayList<>(raceCards.values());
    }
}
