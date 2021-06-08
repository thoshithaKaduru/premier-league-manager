package models;

import java.util.Objects;

public class UniversityFootballClub extends FootballClub{
    private String universityName;

    // default(no-arg) constructor
    public UniversityFootballClub() {
        super();
    }

    // arg-constructor
    public UniversityFootballClub(
            String clubName,
            String clubLocation,
            String clubManger,
            String clubOwner,
            String universityName
    ) {
        super(
                clubName,
                clubLocation,
                clubManger,
                clubOwner
        );
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public boolean equals(Object otherUniversityClub) {
        if (this == otherUniversityClub) return true;
        if (otherUniversityClub == null || getClass() != otherUniversityClub.getClass()) return false;
        if (!super.equals(otherUniversityClub)) return false;
        UniversityFootballClub that = (UniversityFootballClub) otherUniversityClub;
        return universityName.equals(that.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName);
    }

    @Override
    public String toString() {
        return "UniversityFootballClub [ with name " +
                "'" + universityName + '\'' +
                " ] " + super.toString();
    }
}
