package models;

import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {
    private static final long serialVersionUID = 918386991104075395L;

    private int id;
    private int noOfMatchesPlayed;//
    private int noOfMatchesWon;
    private int noOfDraws;
    private int noOfLosses;
    private int noOfGoalsScored;
    private int noOfGoalsAgainst;
    private int goalDifference;
    private int noOfPointScored;

    // zero argument constructor
    FootballClub() { }

    // arg-constructor to initialize fields
    public FootballClub(
            String clubName,
            String clubLocation,
            String clubManger,
            String clubOwner
    ) {
        super(clubName,clubLocation,clubManger,clubOwner);
    }

    public int getClubId() {
        return id;
    }

    public void setClubId(int clubId) {
        this.id = clubId;
    }

    public int getNoOfMatchesPlayed() {
        return noOfMatchesPlayed;
    }

    public void setNoOfMatchesPlayed(int noOfMatchesPlayed) {
        this.noOfMatchesPlayed = noOfMatchesPlayed;
    }

    public int getNoOfMatchesWon() {
        return noOfMatchesWon;
    }

    public void setNoOfMatchesWon(int noOfMatchesWon) {
        this.noOfMatchesWon = noOfMatchesWon;
    }

    public int getNoOfDraws() {
        return noOfDraws;
    }

    public void setNoOfDraws(int noOfDraws) {
        this.noOfDraws = noOfDraws;
    }

    public int getNoOfLosses() {
        return noOfLosses;
    }

    public void setNoOfLosses(int noOfLosses) {
        this.noOfLosses = noOfLosses;
    }

    public int getNoOfGoalsScored() {
        return noOfGoalsScored;
    }

    public void setNoOfGoalsScored(int noOfGoalsScored) {
        this.noOfGoalsScored = noOfGoalsScored;
    }

    public int getNoOfGoalsAgainst() {
        return noOfGoalsAgainst;
    }

    public void setNoOfGoalsAgainst(int noOfGoalsAgainst) {
        this.noOfGoalsAgainst = noOfGoalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference() {
        this.goalDifference = this.noOfGoalsScored - this.noOfGoalsAgainst;
    }


    public int getNoOfPointScored() {
        return noOfPointScored;
    }

    public void setNoOfPointScored(int noOfPointScored) {
        this.noOfPointScored = noOfPointScored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballClub)) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return getClubId() == that.getClubId() && getNoOfMatchesPlayed() == that.getNoOfMatchesPlayed() && getNoOfMatchesWon() == that.getNoOfMatchesWon() && getNoOfDraws() == that.getNoOfDraws() && getNoOfLosses() == that.getNoOfLosses() && getNoOfGoalsScored() == that.getNoOfGoalsScored() && getNoOfGoalsAgainst() == that.getNoOfGoalsAgainst() && getGoalDifference() == that.getGoalDifference() && getNoOfPointScored() == that.getNoOfPointScored();
    }

    @Override
    public int hashCode() {
        // compares the two hash values of two football clubs
        return Objects.hash(super.hashCode(), getClubId(), getNoOfMatchesPlayed(), getNoOfMatchesWon(), getNoOfDraws(), getNoOfLosses(), getNoOfGoalsScored(), getNoOfGoalsAgainst(), getGoalDifference(), getNoOfPointScored());
    }

    @Override
    public int compareTo(FootballClub other) {
        // return negative if higher to make it descending
        if (this.noOfPointScored == other.noOfPointScored)
            return (this.goalDifference > other.goalDifference) ? -1 : 1;
        return (this.noOfPointScored > other.noOfPointScored) ? -1 : 1;
    }

    @Override
    public String toString() {
        return "footballClub with" +
                " an id of '" + id +
                "', where, noOfMatchesPlayed=" + noOfMatchesPlayed +
                ", noOfMatchesWon=" + noOfMatchesWon +
                ", noOfDraws=" + noOfDraws +
                ", noOfLosses=" + noOfLosses +
                ", noOfGoalsScored=" + noOfGoalsScored +
                ", noOfGoalsAgainst=" + noOfGoalsAgainst +
                ", goalDifference=" + goalDifference +
                ", noOfPointScored=" + noOfPointScored +
                "] " + super.toString();
    }
}