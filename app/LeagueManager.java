import models.FootballClub;
import models.FootballMatch;

import java.util.Map;
import java.util.Set;

public interface LeagueManager {
    public void addFootballClub(FootballClub footballClub);
    public void relegateFootballClub(int clubId);
    public FootballClub getFootballClub(int clubId);
    public int getFootballClubId(String clubName);
    public Set<FootballClub> getAllFootballClubs();
    public boolean displayClubStats(String clubName);
    public boolean displayLeagueTable();
    public void addFootballMatch(FootballMatch footballMatch);
    public void updateFootballClubs(int homeTeamId, int awayTeamId, int homeTeamScore, int awayTeamScore);
    public void saveClubs();
    public void loadClubs();
    public void saveMatches();
    public void loadMatches(); //TODO: loadMatches() is not needed here but leave it for future implementation
}