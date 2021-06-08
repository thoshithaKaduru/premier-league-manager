import models.FootballClub;
import models.FootballMatch;
import utilities.DateUtil;

import java.util.Scanner;

public class CommandLineApp {

    private final static Scanner inputReader = new Scanner(System.in);
    private final static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    public static void main(String[] args) {
        boolean cliIsRunning = true;
        boolean guiOpened = false;


        System.out.println("Student UOW id: w1714902");
        System.out.println("Student IIT id: 2018142");
        System.out.println("Student name:   R.M.T.S.N. Kaduruwewa");
        System.out.println("Module:         Object Oriented Programming (IIT Sri Lanka)");
        System.out.println("Module ID:      5COSCOO7C.1.2020");
        System.out.println("Unit:           CourseWork");
        System.out.println("Module Leader:  Guhanathan Poravi");
        System.out.println("\n\"I confirm that I understand what plagiarism  /collusion /contract \n" +
                           "cheating is and have read and understood the section on Assessment \n" +
                           "Offences in the Essential Information for Students. The work that \n" +
                           "I have submitted is entirely my own. And is free of errors, tested \n" +
                           "and have met the requirement to the best of my knowledge, Any work \n" +
                           "from other authors is duly referenced and acknowledged.");
        System.out.println("\n");
        System.out.println("=====================================================================");
        System.out.println("");
        System.out.println("=====================================================================");
        System.out.println("\n");


        System.out.println("\t\t~ Welcome to Premier League Manager ~");

        while (cliIsRunning) {
            System.out.println("");
            System.out.println("press 1 to add a club");
            System.out.println("press 2 to relegate a club");
            System.out.println("press 3 to display a club's stats");
            System.out.println("press 4 to display the premier league table");
            System.out.println("press 5 to add a played match");
            System.out.println("press 6 to open GUI");
            System.out.println("press 0 to exit the Premier League Manager program");

            System.out.print("\ninput : ");
            String choice = inputReader.nextLine();

            switch (choice) {
                case "0":
                    System.out.println("Are you sure you want to exit");
                    System.out.println("Enter (y/yes/exit) to proceed");
                    System.out.println("Press any other key if you want to go back");

                    System.out.print("\ninput : ");
                    String exitChoice = inputReader.nextLine();
                    switch (exitChoice.toLowerCase()) {
                        case "y":
                        case "yes":
                        case "exit":
                            cliIsRunning = false;
                            System.out.println("Exiting Premier League Manager...");
                            break;
                        default:
                            System.out.println("going back...");
                            System.out.println("\n\t\t\t~ Premier League Manger ~");
                            break;
                    }
                    if (exitChoice.equals("1")) {
                        System.exit(0);
                    } else if (exitChoice.equals("2")) {
                        cliIsRunning = false;
                    }
                    break;
                case "1":
                    System.out.println("You are about to add a new club. Press any to proceed. Enter (back/b) to go back");
                    System.out.print("\ninput : ");
                    String addClubB = inputReader.nextLine();
                    if (!"b".equalsIgnoreCase(addClubB) && !"back".equalsIgnoreCase(addClubB)) {
                        addClub();
                    }
                    break;
                case "2":
                    System.out.println("You are about to relegate a  club. Enter (back/b) to go back Press any other key to proceed.");
                    System.out.print("\ninput : ");
                    String deleteClubB = inputReader.nextLine();
                    if (!"b".equalsIgnoreCase(deleteClubB) && !"back".equalsIgnoreCase(deleteClubB)) {
                        relegateClub();
                    }
                    break;
                case "3":
                    System.out.println("displaying club");
                    displayClub();
                    break;
                case "4":
                    System.out.println("displaying table");
                    displayPremierTable();
                    break;
                case "5":
                    System.out.println("You are about to add a new Match. Enter (back/b) to go back. Press any other key to proceed. ");
                    System.out.print("\ninput : ");
                    String addMatchB = inputReader.nextLine();
                    if (!"b".equalsIgnoreCase(addMatchB) && !"back".equalsIgnoreCase(addMatchB)) {
                        addPlayedMatch();
                    }
                    break;
                case "6":
                    if (!guiOpened) {
                        openGUI(); // opens server
                        guiOpened = true;
                    } else {
                        System.out.println("GUI already opened");
                    }
                    break;
                default:
                    System.out.println("such an option does not exist... please choose one of the below options");
                    break;
            }
        }
    }

    private static void addClub() {
        System.out.println("addClub");
        String clubName, clubLocation, clubManager, clubOwner;

        while (true) {
            System.out.print("Enter Club Name: ");
            clubName = inputReader.nextLine();
            if (premierLeagueManager.getFootballClubId(clubName) != -1) {
                System.out.println(clubName + " already exists on the system. Press b/back to go back.");
                System.out.print("input: ");
                String addB = inputReader.nextLine();
                
                switch (addB.toLowerCase()) {
                    case "b":
                    case "back":
                        return;
                    default:
                        break;
                }
            } else if (clubName.trim().length() == 0) {
                System.out.println("Club name cannot be empty or only white spaces");
            } else break;
        }

        System.out.print("Enter Club Location Name: ");
        clubLocation = inputReader.nextLine();

        System.out.print("Enter Club Manager Name: ");
        clubManager = inputReader.nextLine();

        System.out.print("Enter Club Owner Name: ");
        clubOwner = inputReader.nextLine();

        System.out.println("\n\"A club with Club Name, Location Name, Manager Name, Owner Name :"+
                clubName + " " + clubLocation + " " + clubManager + " " + clubOwner + " (respectively) " +
                "is about to be added. Do you wish to proceed with the operation?\"" );
        System.out.print("\ninput: (type no/n to go back to premier league): ");
        String input = inputReader.nextLine().toLowerCase();
        if ("n".equals(input) || "no".equals(input)) {
            System.out.println("skipping operation and going back to league manager.\n");
            return;
        }
        System.out.println("");

        FootballClub footballClub = new FootballClub(clubName, clubLocation, clubManager, clubOwner);
        premierLeagueManager.addFootballClub(footballClub);
    }

    private static void relegateClub() {
        System.out.print("Enter club name to relegate: ");
        String clubName;
        clubName = inputReader.nextLine();

        if(premierLeagueManager.getFootballClubId(clubName) == -1 ) {
            System.out.println(clubName + " does not exist... Make sure you have " +
                    "spelt the name correctly");
        } else {
            System.out.println(
                    premierLeagueManager.getFootballClub(premierLeagueManager.getFootballClubId(clubName)).toString() +
                            "\n is about to be deleted. Do you wish to proceed? "
                    ) ;
            System.out.print("input: (type no/n to go back to premier league): ");
            String input = inputReader.nextLine().toLowerCase();
            if ("n".equals(input) || "no".equals(input)) {
                System.out.println("skipping operation and going back to league manager.\n");
                return;
            }
            System.out.println("");

            premierLeagueManager.relegateFootballClub(premierLeagueManager.getFootballClubId(clubName));
        }
    }

    private static void displayClub() {
        System.out.print("Enter club name to view its stats: ");
        String clubName = inputReader.nextLine();

        if(premierLeagueManager.getFootballClubId(clubName) == -1 ) {
            System.out.println(clubName + " does not exist... Make sure you have " +
                    "spelt the name correctly");
        } else {
            premierLeagueManager.displayClubStats(clubName);
        }
    }

    private static void displayPremierTable() {
        premierLeagueManager.displayLeagueTable();
    }

    private static void addPlayedMatch() {
        System.out.println("adding played match");

        int homeTeamId = 0, awayTeamId = 0, homeTeamScore, awayTeamScore;
        String homeTeamName = "", awayTeamName = "", matchDate;

        int count = 0;
        while (count < 4) {
            if (count == 3) {
                System.out.println("going back to premier league manager...\n");
                return;
            }
            System.out.print("Enter home Team name: ");
            homeTeamName = inputReader.nextLine();
            if(premierLeagueManager.getFootballClubId(homeTeamName) == -1 ) {
                System.out.println("\na Football club with name, '" + homeTeamName + "' does not exist... Make sure you have " +
                        "spelt the name correctly");

                System.out.println("you will go back to premier league manager in another "+
                        (2 - count)+ " incorrect attempts\n" );
                count ++;
                continue;
            }
            System.out.print("Enter away Team name: ");
            awayTeamName = inputReader.nextLine();
            if(premierLeagueManager.getFootballClubId(awayTeamName) == -1 ) {
                System.out.println("\nA football club with name, '" + awayTeamName + "' does not exist... Make sure you have " +
                        "spelt the name correctly");
                System.out.println("you will go back to premier league manager in another "+
                        (2 - count)+ " incorrect  attempts\n" );
                count ++;
                continue;
            }
            break;
        }
        homeTeamId = premierLeagueManager.getFootballClubId(homeTeamName);
        awayTeamId = premierLeagueManager.getFootballClubId(awayTeamName);
        do {
            try {
                System.out.print("Enter home team score: ");
                homeTeamScore = inputReader.nextInt();

                inputReader.nextLine();
                System.out.print("Enter away team score: ");
                awayTeamScore = inputReader.nextInt();
                inputReader.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Score can only be a whole number");
                inputReader.nextLine();
            }
        } while (true);
        //10-04-2020
        while (true) {
            System.out.println("Enter match date (DD-MM-YYYY) - date should be " +
                    "within the match season 01-01-2020 - 2021-12-31");
            matchDate = inputReader.nextLine();
            if (DateUtil.canParseDate(matchDate)) {
                break;
            } else {
                System.out.println("Invalid date entered. \n follow the given format and " +
                        "the year can be only 2020 and 2021");
            }
        }

        System.out.println("\"Football match with Home Team Name, Away Team Name, Home Team Score, Away Team Score, Played on Date ," +
                "\n" + homeTeamName + " " + awayTeamName + " " + homeTeamScore + " " + awayTeamScore + " " + matchDate + "" +
                " (respectively) \nDo you with to procced? \"\n");

        System.out.print("input: (type no/n to go back to premier league): ");
        String input = inputReader.nextLine().toLowerCase();
        if ("n".equals(input) || "no".equals(input)) {
            System.out.println("skipping operation and going back to league manager.\n");
            return;
        }
        System.out.println("");

        FootballMatch match = new FootballMatch(homeTeamId, awayTeamId, homeTeamScore, awayTeamScore, matchDate);
        premierLeagueManager.addFootballMatch(match);
    }

    public static void openGUI() {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c start server.bat");
            p.waitFor();
        } catch (Exception e) {
            System.out.println("error opening cmd \nYou will have to open it manually");
        }
    }
}