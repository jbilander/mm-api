package com.creang.rest.v1;

import com.creang.model.v1.RaceCard;
import com.creang.model.v1.RaceDay;
import com.creang.model.v1.SimpleRaceCard;
import com.creang.service.v1.ModelService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

@Path("v1")
public class RESTService {

    private final Logger logger = Logger.getLogger(RESTService.class.getName());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final ModelService modelService = new ModelService();

    @GET
    @Path("/fetchracecards/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRaceCards(@PathParam("date") String dateString) {

        try {
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            List<SimpleRaceCard> simpleRaceCards = modelService.fetchRaceCards(localDate);
            return Response.status(Response.Status.OK).entity(simpleRaceCards).build();

        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/fetchracecard/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRaceCard(@PathParam("id") int raceCardId) {

        try {
            RaceCard raceCard = modelService.fetchRaceCard(raceCardId);
            return Response.status(Response.Status.OK).entity(raceCard).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/fetchracedays")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRaceDays() {

        try {
            List<RaceDay> raceDays = modelService.fetchRaceDays();
            return Response.status(Response.Status.OK).entity(raceDays).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
