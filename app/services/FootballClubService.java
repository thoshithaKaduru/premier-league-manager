package services;
import models.FootballClub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// FootballClubsServices take care of all API FootballClub related HTTP methods

public class FootballClubService {

    private static FootballClubService instance;

    private Map<Integer, FootballClub> footballClubs = new HashMap<>();
    Serialize saveAndReadController = new Serialize();
    // save/read controller is used // location is '.app/' directory

    public static FootballClubService getInstance() {
        // Lazy initializing FootballClubService using singleton design pattern is used
        // Note that this is not Thread safe
        // making the method 'synchronized' will make it thread safe
        if (instance == null) {
            instance = new FootballClubService();
        }
        return instance;
    }

    public FootballClub addFootballClub(FootballClub footballClub) {
        // HashMap footballClub's key and its value's footballClub id property are the same.
        // This can be useful in future requirements
        int id = footballClubs.size()+1;
        footballClub.setClubId(id);
        footballClubs.put(id, footballClub);
        saveClubs(); // changes are saved
        return footballClub;
    }

    public FootballClub getFootballClub(int id) {
        loadClubs(); // load new clubs if exists
        return footballClubs.get(id);
    }

    public Set<FootballClub> getAllClubs() {
        loadClubs(); // load new clubs if any
        return new HashSet<>(footballClubs.values());
    }

    public FootballClub updateFootballClub(FootballClub footballClub) {
        int id = footballClub.getClubId();
        if (footballClubs.containsKey(id)) {
            footballClubs.put(id, footballClub);
            saveClubs(); // save club update
            return footballClub;
        }
        return null;
    }

    public boolean deleteClub(int id) {
        // This method is not used but, left here for future requirements
        return footballClubs.remove(id) != null;
    }

    public void saveClubs() {
        saveAndReadController.saveMapObject(footballClubs, ".\\app\\saveClubs.ser");
    }

    public void loadClubs() {
        Map<Integer, FootballClub> loadedClubs
                = (Map<Integer, FootballClub>) saveAndReadController.readMapObject(".\\app\\saveClubs.ser");
        if (loadedClubs != null)
            footballClubs = loadedClubs;
    }


}
