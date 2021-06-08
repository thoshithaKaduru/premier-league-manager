package controllers;

import utilities.ApplicationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Result;
import play.mvc.Http;
import play.mvc.Controller;
import services.FootballMatchService;
import models.FootballMatch;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.util.Map;
import java.util.Set;

public class FootballMatchController extends Controller {

    public Result create(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            // empty request
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        // new match created
        FootballMatch footballMatch = FootballMatchService.getInstance().addFootballMatch(Json.fromJson(json, FootballMatch.class));
        // returning created match with an ok response
        JsonNode jsonObject = Json.toJson(footballMatch);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result listFootballMatches(Http.Request request) {
        // check if query string is empty or not // i.e NULL value
        if (request.queryString().size() != 0) {

            String matchDate = "";

            Set<Map.Entry<String,String[]>> entries = request.queryString().entrySet();
            // request is stored in a map
            for (Map.Entry<String,String[]> entry : entries) {
                matchDate = entry.getKey();
            }
            Set<FootballMatch> result = FootballMatchService.getInstance().getMatchesByDate(matchDate);
            ObjectMapper mapper = new ObjectMapper();

            JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));
        }
        Set<FootballMatch> result = FootballMatchService.getInstance().getAllMatches();
        // a set of results is returned
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }
}
