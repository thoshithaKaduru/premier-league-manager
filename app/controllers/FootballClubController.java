package controllers;

import play.mvc.Http;
import play.libs.Json;
import play.mvc.Controller;
import services.FootballClubService;
import utilities.ApplicationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FootballClub;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Result;



import java.util.Set;

// FootballClub Controller uses FootballMatchService

public class FootballClubController extends Controller {

    public Result listFootballClubs() {
        // returns a Set of football club values
        Set<FootballClub> result = FootballClubService.getInstance().getAllClubs();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }



    public Result update(Http.Request request) {
        // Request body is converted to JSON
        JsonNode json = request.body().asJson();
        if (json == null) {
            // PUT request contained non JSON values
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        // new football club instance is created and is updated with request body
        FootballClub footballClub = FootballClubService.getInstance().updateFootballClub(Json.fromJson(json, FootballClub.class));
        // instance of FootballClub service is used to updateFootballClubs
        if (footballClub == null) {
            // if requested club was not found
            return notFound(ApplicationUtil.createResponse("FootballClub not found", false));
        }
        // updated football club is returned as a response with 'ok' status
        JsonNode jsonObject = Json.toJson(footballClub);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result create(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            // POST request contained non JSON values
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        // new football club is created
        FootballClub footballClub = FootballClubService.getInstance().addFootballClub(Json.fromJson(json, FootballClub.class));
        JsonNode jsonObject = Json.toJson(footballClub);
        // returns created football club with an ok response
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

}












































