import models.FootballClub;
import models.FootballMatch;
import services.Serialize;
import utilities.OtherUtils;

import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    private static PremierLeagueManager instance;       // TODO: remove instance
    private Map<Integer, FootballClub> footballClubs = new HashMap<>();
    private Map<Integer, FootballMatch> footballMatches = new HashMap<>();
    Serialize saveAndReadController = new Serialize();

    public static PremierLeagueManager getInstance() {
        // Lazy initializing FootballMatchService using singleton design pattern is used
        // Note that this is not Thread safe
        // making the method 'synchronized' will make it thread safe
        if (instance == null) {
            instance = new PremierLeagueManager();
        }
        return instance;
    }

    @Override
    public void addFootballClub(FootballClub footballClub) {
        loadClubs();
        int id = footballClubs.size() + 1;
        footballClub.setClubId(id);
        footballClubs.put(id, footballClub);
        System.out.println("\n\"" + footballClub.toString() + "\nwas successfully added to Premier League \"\n");
        saveClubs();
    }

    @Override
    public void relegateFootballClub(int clubId) {
        loadClubs();
        FootballClub clubToRemove = footballClubs.get(clubId);
        System.out.println("\n\"" + clubToRemove.toString() + "\nwas successfully removed from Premier League\"\n");
        footballClubs.remove(clubId);
        saveClubs();
    }

    @Override
    public FootballClub getFootballClub(int id) {
        loadClubs();
        return footballClubs.get(id);
    }

    @Override
    public int getFootballClubId(String clubName) {
        loadClubs();
        for (Map.Entry<Integer, FootballClub> entry : footballClubs.entrySet()) {
            if (entry.getValue().getClubName().equals(clubName)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public Set<FootballClub> getAllFootballClubs() {
        loadClubs();
        return new HashSet<>(footballClubs.values());
    }

    @Override
    public boolean displayClubStats(String clubName) {
        loadClubs();
        for (Map.Entry<Integer, FootballClub> entry : footballClubs.entrySet()) {
            if (entry.getValue().getClubName().equals(clubName)) {
                // table-top
                System.out.println("|-----------------  Club Stats  ----------------|");
                System.out.println("|                 ______________                |");
                System.out.println("  \t\t\t" + entry.getValue().getClubName() + " - Club Details");
                // table-middle
                System.out.println("________________________________________________");
                System.out.println(
                        "| Club Id:            " + entry.getValue().getClubId() + "\n" +  // TODO: this was added later
                        "| Club Name:          " + entry.getValue().getClubName() + "\n" +
                        "| Club is located in: " +  entry.getValue().getClubLocation() + "\n" +
                        "| Club Mangers Name:  " +  entry.getValue().getClubManger() + "\n" +
                        "| Club Owner's Name:  " + entry.getValue().getClubOwner() + "\n"
                );
                System.out.println("=================================================");
                // table-bottom
                System.out.println(
                        "| No of Matches Played:       \t\t\t|\t" + entry.getValue().getNoOfMatchesPlayed() + "\t|\n" +
                        "| No of Matches Won:          \t\t\t|\t" + entry.getValue().getNoOfMatchesWon() + "\t|\n" +
                        "| No of Matches Drawn:        \t\t\t|\t" + entry.getValue().getNoOfDraws() + "\t|\n" +
                        "| No of Losses:               \t\t\t|\t" + entry.getValue().getNoOfLosses() + "\t|\n" +
                        "| No of Goals Scored:         \t\t\t|\t" + entry.getValue().getNoOfGoalsScored() + "\t|\n" +
                        "| No of Goals Scored Against: \t\t\t|\t" + entry.getValue().getNoOfGoalsAgainst() + "\t|\n" +
                        "| Goal Difference:            \t\t\t|\t" + entry.getValue().getGoalDifference() + "\t|\n" +
                        "| No of Points Scored:        \t\t\t|\t" + entry.getValue().getNoOfPointScored() + "\t|"
                );
                System.out.println("__________________________________________________\n\n");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean displayLeagueTable() {
        boolean flag = false;
        loadClubs();
        Map<Integer, FootballClub> sortedDraftFootballClubs = OtherUtils.sortMapByValue(footballClubs);
        System.out.println("\n\n");
        System.out.println("|----------------  Premier League Table  -----------------|");
        System.out.printf("%-20s%4s%4s%4s%4s%4s%4s%4s%4s%n", "| Club name          |", " MP |", "W |", "D |", " L | ", "GS | ", "GA |", "GD |", " PTS |");
        if (footballClubs.size() == 0) {
            System.out.println("\t\tNo clubs to display");
        } else {
            flag = true;
        }
        for (Map.Entry<Integer, FootballClub > entry : sortedDraftFootballClubs.entrySet()) {
            System.out.printf("%2s%-19s%4s%4s%5s%4s%4s%4s%4s%6s%3s%n",
                    "| ",
                    entry.getValue().getClubName(),
                    entry.getValue().getNoOfMatchesPlayed(),
                    entry.getValue().getNoOfMatchesWon(),
                    entry.getValue().getNoOfDraws(),
                    entry.getValue().getNoOfLosses(),
                    entry.getValue().getNoOfGoalsScored(),
                    entry.getValue().getNoOfGoalsAgainst(),
                    entry.getValue().getGoalDifference(),
                    entry.getValue().getNoOfPointScored(),
                    " |"
            );
        }
        System.out.println("|_________________________________________________________|");
        System.out.println("| Common Soccer Terms                                     |");
        System.out.println("| MP:  Matches Played                                     |");
        System.out.println("| W:   Matches Won                                        |");
        System.out.println("| D:   Matches Drawn                                      |");
        System.out.println("| L:   Matches Lost                                       |");
        System.out.println("| GS:  Goals Scored                                       |");
        System.out.println("| GA:  Goals Against(scored by opponents)                 |");
        System.out.println("| GD:  Goal Difference                                    |");
        System.out.println("| PTS: Points Scored during the league                    |");
        System.out.println("|_________________________________________________________|\n\n");
        return flag;
    }

    @Override
    public void addFootballMatch(FootballMatch footballMatch) {
        loadMatches();
        int id = footballMatches.size()+1;
        footballMatch.setId(id);
        footballMatches.put(id, footballMatch);
        System.out.println("\n\"" + footballMatch.toString() + "\nwas successfully added to Premier League\"\n");
        saveMatches();
        updateFootballClubs(footballMatch.getHomeTeamId(), footballMatch.getAwayTeamId(), footballMatch.getHomeTeamScore(), footballMatch.getAwayTeamScore());
        saveClubs();
    }

    @Override
    public void updateFootballClubs(int homeTeamId, int awayTeamId, int homeTeamScore, int awayTeamScore) {
        boolean isDraw = false;
        boolean homeWin = false;
        boolean awayWin = false;
        if (homeTeamScore == awayTeamScore) {
            isDraw = true;
        } else if (homeTeamScore > awayTeamScore) {
            homeWin = true;
        } else if (homeTeamScore < awayTeamScore) {
            awayWin = true;
        }
        System.out.println("\n\n");

        for (Map.Entry<Integer, FootballClub> entry : footballClubs.entrySet()) {
            if (entry.getKey().equals(homeTeamId)) {
                // home club
                entry.getValue().setNoOfMatchesPlayed(entry.getValue().getNoOfMatchesPlayed() + 1);
                entry.getValue().setNoOfGoalsScored(entry.getValue().getNoOfGoalsScored() + homeTeamScore);
                entry.getValue().setNoOfGoalsAgainst(entry.getValue().getNoOfGoalsAgainst() + awayTeamScore);
                entry.getValue().setGoalDifference();

                if (isDraw) {
                    entry.getValue().setNoOfDraws(entry.getValue().getNoOfDraws() + 1);
                    entry.getValue().setNoOfPointScored(entry.getValue().getNoOfPointScored() + 1);
                } else if (homeWin) {
                    entry.getValue().setNoOfMatchesWon(entry.getValue().getNoOfMatchesWon() + 1);
                    entry.getValue().setNoOfPointScored(entry.getValue().getNoOfPointScored() + 3);
                } else if (awayWin) {
                    entry.getValue().setNoOfLosses(entry.getValue().getNoOfLosses() + 1);
                }
            } else if (entry.getKey().equals(awayTeamId)) {
                // away club
                entry.getValue().setNoOfMatchesPlayed(entry.getValue().getNoOfMatchesPlayed() + 1);
                entry.getValue().setNoOfGoalsScored(entry.getValue().getNoOfGoalsScored() + awayTeamScore);
                entry.getValue().setNoOfGoalsAgainst(entry.getValue().getNoOfGoalsAgainst() + homeTeamScore);
                entry.getValue().setGoalDifference();

                if (isDraw) {
                    entry.getValue().setNoOfDraws(entry.getValue().getNoOfDraws() + 1);
                    entry.getValue().setNoOfPointScored(entry.getValue().getNoOfPointScored() + 1);
                } else if (homeWin) {
                    entry.getValue().setNoOfLosses(entry.getValue().getNoOfLosses() + 1);
                } else if (awayWin) {
                    entry.getValue().setNoOfMatchesWon(entry.getValue().getNoOfMatchesWon() + 1);
                    entry.getValue().setNoOfPointScored(entry.getValue().getNoOfPointScored() + 3);
                }
            }
        }
    }

    @Override
    public void saveClubs() {
        boolean saveSuccess =  saveAndReadController.saveMapObject(footballClubs, "saveClubs.ser");
    }

    @Override
    public void loadClubs() {
        Map<Integer, FootballClub> loadedClubs
                = (Map<Integer, FootballClub>) saveAndReadController.readMapObject("saveClubs.ser");
        if (loadedClubs != null)
            footballClubs = loadedClubs;
    }

    @Override
    public void saveMatches() {
        boolean saveSuccess =  saveAndReadController.saveMapObject(footballMatches, "saveMatches.ser");
    }

    @Override
    public void loadMatches() {
        Map<Integer, FootballMatch> loadedMatches
                = (Map<Integer, FootballMatch>) saveAndReadController.readMapObject("saveMatches.ser");
        if (loadedMatches != null)
            footballMatches = loadedMatches;
    }
}
