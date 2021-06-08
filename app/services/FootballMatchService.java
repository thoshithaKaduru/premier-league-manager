package services;

import models.FootballMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class FootballMatchService {

    private static FootballMatchService instance;

    // a logger is used for debugging purposes
    private static final Logger logger = LoggerFactory.getLogger("matchService");

    private Map<Integer, FootballMatch> footballMatches = new HashMap<>();
    Serialize saveAndReadController = new Serialize(); // serialize controller

    public static FootballMatchService getInstance() {
        // Lazy initializing FootballMatchService using singleton design pattern is used
        // Note that this is not Thread safe
        // making the method 'synchronized' will make it thread safe
        if (instance == null) {
            instance = new FootballMatchService();
        }
        return instance;
    }

    public FootballMatch addFootballMatch (FootballMatch footballMatch) {
        int id = footballMatches.size()+1;
        footballMatch.setId(id);
        // HashMap footballMatches key and it's value's footballMatch id property are the same.
        // This can be useful in future requirements
        footballMatches.put(id, footballMatch);
        saveMatches();
        return footballMatch;
    }

    // returns all matches
    public Set<FootballMatch> getAllMatches() {
        // returns all matches
        loadMatches();
        return new HashSet<>(footballMatches.values());
    }

    public Set<FootballMatch> getMatchesByDate(String date) {
        // returns matches by date
        loadMatches();
        Map<Integer, FootballMatch> footballMatchesByDate = new HashMap<>();
        // return a list of matches that match dates
        for (Map.Entry<Integer, FootballMatch> entry : footballMatches.entrySet()) {
            if (entry.getValue().getMatchDate().equals(date)) {
                footballMatchesByDate.put(entry.getKey(), entry.getValue());
            }
        }
        return new HashSet<>(footballMatchesByDate.values());
    }

    public void saveMatches() {
        saveAndReadController.saveMapObject(footballMatches, ".\\app\\saveMatches.ser");
    }

    public void loadMatches() {
        Map<Integer, FootballMatch> loadedMatches
                = (Map<Integer, FootballMatch>) saveAndReadController.readMapObject(".\\app\\saveMatches.ser");
        if (loadedMatches != null)
            footballMatches = loadedMatches;
    }
}












