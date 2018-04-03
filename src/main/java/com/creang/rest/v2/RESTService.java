package com.creang.rest.v2;

import com.creang.common.Util;
import com.creang.model.v2.RaceCard;
import com.creang.model.v2.RaceCardKey;
import com.creang.service.v2.ModelService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

@Path("v2")
public class RESTService {

    private final Logger logger = Logger.getLogger(RESTService.class.getName());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final ModelService modelService = new ModelService();

    @GET
    @Path("/fetchracecard/{betType}/{trackId}/{trackCode}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRaceCard(@PathParam("betType") String betType, @PathParam("trackId") int atgTrackId,
                                  @PathParam("trackCode") String atgTrackCode, @PathParam("date") String dateString) {

        try {

            if (!(betType != null && Util.getBetTypeCode(betType) > 0)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            RaceCardKey raceCardKey = new RaceCardKey();
            raceCardKey.setBetType(betType);
            raceCardKey.setAtgTrackId(atgTrackId);
            raceCardKey.setAtgTrackCode(atgTrackCode);
            raceCardKey.setRaceDayDate(LocalDate.parse(dateString, formatter));

            RaceCard raceCard = modelService.fetchRaceCard(raceCardKey);

            if (raceCard == null) {
                return Response.status(Response.Status.NO_CONTENT).entity(null).build();
            } else {
                return Response.status(Response.Status.OK).entity(raceCard).build();
            }

        } catch (DateTimeParseException e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
