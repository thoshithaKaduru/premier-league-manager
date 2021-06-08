package models;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub{
    private String schoolName;

    // default(no-arg) constructor
    public SchoolFootballClub() {
        super();
    }

    public SchoolFootballClub(
            String clubName,
            String clubLocation,
            String clubManger,
            String clubOwner,
            String schoolName
    ) {
        super(
                clubName,
                clubLocation,
                clubManger,
                clubOwner
        );
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return Objects.equals(schoolName, that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }

    @Override
    public String toString() {
        return "SchoolFootballClub [ with name" +
                "'" + schoolName + '\'' +
                " ] " + super.toString();
    }
}
