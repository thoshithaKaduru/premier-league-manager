package models;

import java.io.Serializable;
import java.util.Objects;

public class FootballMatch implements Serializable {
    private static final long serialVersionUID = 411826941124055795L;

    private int id;
    private int homeTeamId;
    private int awayTeamId;
    private int homeTeamScore;
    private int awayTeamScore;
    private String matchDate;

    public FootballMatch() {
        super();
    }

    public FootballMatch(
            int homeTeamId,
            int awayTeamId,
            int homeTeamScore,
            int awayTeamScore,
            String matchDate
    ) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.setMatchDate(matchDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getMatchDate() {
        return this.matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    @Override
    public boolean equals(Object o) {
        // equals method. can be used to check if one FootballMatch equals another in everyway
        if (this == o) return true;
        // returns if this == passed in object
        if (o == null || getClass() != o.getClass()) return false;
        FootballMatch that = (FootballMatch) o;
        return id == that.id && homeTeamId == that.homeTeamId && awayTeamId == that.awayTeamId && homeTeamScore == that.homeTeamScore && awayTeamScore == that.awayTeamScore && matchDate.equals(that.matchDate);
    }

    @Override
    public int hashCode() {
        // checks for hashes
        // this Objects.hash is only available for java v7+
        return Objects.hash(id, homeTeamId, awayTeamId, homeTeamScore, awayTeamScore, matchDate);
    }

    @Override
    public String toString() {
        return "match with id " + id + " between team with homeId '" + homeTeamId +
                "' VS team with awayId '" +
                awayTeamId + "' " +
                "where home team scored '" + awayTeamScore + "' " +
                "and away team scored '" + awayTeamScore +
                "', played on \"" + matchDate +
                "\"";
    }
}