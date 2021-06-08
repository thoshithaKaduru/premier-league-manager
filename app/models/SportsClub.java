package models;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {

    private String clubName;
    private String clubLocation;
    private String clubManger;
    private String clubOwner;

    public SportsClub() {
        super();
    }

    public SportsClub(
            String clubName,
            String clubLocation,
            String clubManger,
            String clubOwner) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubManger = clubManger;
        this.clubOwner = clubOwner;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getClubManger() {
        return clubManger;
    }

    public void setClubManger(String clubManger) {
        this.clubManger = clubManger;
    }

    public String getClubOwner() {
        return clubOwner;
    }

    public void setClubOwner(String clubOwner) {
        this.clubOwner = clubOwner;
    }

    @Override
    public boolean equals(Object toEqual) {
        if (this == toEqual) return true;
        if (!(toEqual instanceof SportsClub)) return false;
        SportsClub otherClub = (SportsClub) toEqual;
        return Objects.equals(clubName, otherClub.clubName) && Objects.equals(clubLocation, otherClub.clubLocation) && Objects.equals(clubManger, otherClub.clubManger) && Objects.equals(clubOwner, otherClub.clubOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, clubLocation, clubManger, clubOwner);
    }

    @Override
    public String toString() {
        return " and is a SportsClub with " +
                "clubName '" + clubName + "' " +
                ", and clubLocation '" + clubLocation + "' " +
                ", clubManger = '" + clubManger + " '" +
                ", clubOwner = '" + clubOwner + " '" +
                ']';
    }
}
