import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/models/match.model';
import { MatchService } from 'src/app/services/match.service';
import { Club } from 'src/app/models/club.model';
import { ClubService } from 'src/app/services/club.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-generate-match',
  templateUrl: './generate-match.component.html',
  styleUrls: ['./generate-match.component.css']
})
export class GenerateMatchComponent implements OnInit {
  matchGenerated: boolean = false;
  clubs?: Club[] = []; 
  homeTeamClub: Club = {};
  awayTeamClub: Club = {};
  isDraw: boolean = false;  
  winnerName: string = "";  
  match: Match = { };
  
  constructor(private matchService: MatchService, private clubService: ClubService, private titleService: Title) { }
  
  ngOnInit(): void {
    this.titleService.setTitle("Generate New Match"); 
    this.retrieveClubs();     
  }

  ngOnDestroy(): void {}

  generateNewMatch(): void {      
    if (this.clubs?.length! < 2) {
      alert("not enough clubs to generate new match. Please add new clubs to proceed");
      return;
    }

    // generate 2 random clubs 
    let homeIndex = this.generateRandomInt(1, ((this.clubs!.length)));
    let awayIndex = this.generateRandomInt(1, ((this.clubs!.length)));
    while (homeIndex === awayIndex) {
      awayIndex = this.generateRandomInt(1, ((this.clubs!.length)));
    }
    // assigning retrieved clubs
    this.clubs?.find(club => {
      if (club.clubId === homeIndex) {
        this.homeTeamClub = club;        
      } else if (club.clubId === awayIndex) {
        this.awayTeamClub = club;
      }
    })

    // generating match
    this.match = {
      homeTeamId: this.homeTeamClub.clubId,
      awayTeamId: this.awayTeamClub.clubId,
      homeTeamScore: this.generateRandomInt(0, 10),
      awayTeamScore: this.generateRandomInt(0, 10),
      matchDate: this.generateMatchDate()
    }

    this.matchGenerated = true;

    // updating team details
    // matches played
    this.homeTeamClub.noOfMatchesPlayed! += 1;
    this.awayTeamClub.noOfMatchesPlayed! += 1;
    // goals scored
    this.homeTeamClub.noOfGoalsScored! += this.match.homeTeamScore!;
    this.awayTeamClub.noOfGoalsScored! += this.match.awayTeamScore!;
    // goals against
    this.homeTeamClub.noOfGoalsAgainst! += this.match.awayTeamScore!;
    this.awayTeamClub.noOfGoalsAgainst! += this.match.homeTeamScore!;
    // goal difference
    this.homeTeamClub.goalDifference! += ( this.match.homeTeamScore! - this.match.awayTeamScore! );
    this.awayTeamClub.goalDifference! += ( this.match.awayTeamScore! - this.match.homeTeamScore! );

    // points, wins, draws and losses
    if (this.match.homeTeamScore === this.match.awayTeamScore) {
      this.isDraw = true;
      this.homeTeamClub.noOfPointScored! += 1;      
      this.awayTeamClub.noOfPointScored! += 1;
      
      this.homeTeamClub.noOfDraws! += 1;      
      this.awayTeamClub.noOfDraws! += 1;
    } else {
      if (this.match.homeTeamScore! > this.match.awayTeamScore!) {
        // home is winner 
        this.homeTeamClub.noOfPointScored! += 3;      
        this.homeTeamClub.noOfMatchesWon! += 1;
        this.awayTeamClub.noOfLosses! += 1;
        this.winnerName = this.homeTeamClub.clubName!;
      } else {
        // away is winner
        this.awayTeamClub.noOfPointScored! += 3;      
        this.awayTeamClub.noOfMatchesWon! += 1;
        this.homeTeamClub.noOfLosses! += 1;
        this.winnerName = this.homeTeamClub.clubName!;
      }
    }


    // add generated match
    this.addGeneratedMatch(this.match);

    // update clubs with new data
    this.updateClubs(this.homeTeamClub);
    this.updateClubs(this.awayTeamClub);
  }

  retrieveClubs(): void {
    console.log("retrieving clubs");    
    this.clubService.getAll()
      .subscribe(
        data => {       
          // no clubs    
          if (JSON.stringify(data) === "{\"status\":true,\"response\":[]}") {            
            console.log("no clubs to display");
          }
          // has clubs in response
          else {
            console.log("clubs retrieved", data);  
            this.clubs = Object.values(data)[1].sort((a: { clubId: any; },b: { clubId: any; }) => (a.clubId! < b.clubId!) ? 1: -1); // TODO: working                                              
            console.log("data" ,typeof data);                        
          }          
        }, 
        error => {
          console.log("error in retrieving clubs: ", error);          
        });
  }

  generateMatchDate() : string {
    let monthEnds = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    let year : number = this.generateRandomInt(2020, 2021);
    
   
    if (this.isLeapYear(year)) {
      monthEnds[1] = 29;
    }

    let month : number = this.generateRandomInt(1, 12);    
    let day : number = this.generateRandomInt(1, monthEnds[month-1]);
    return `${ ('0000' + day).slice(-2) }-${ ('0000' + month).slice(-2) }-${year}`;    
  }

  generateRandomInt(min: number, max: number) : number {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }

  isLeapYear(year: number) : boolean {
    return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
  }

  addGeneratedMatch(newMatch: Match) : void {
    this.matchService.create(newMatch)
    .subscribe(
      response => {
        console.log("generated match", response);
        this.matchGenerated = true;          
      },
      error => {
        console.log("error generating match", error);
        alert("failed to add generated match\n generated match is as follows")
        alert(`generated match: \nMatch played on ${this.match.matchDate} \n` +
        `home team ID: ${this.match.homeTeamId} ` +  
        `vs away team ID: ${this.match.awayTeamId} \nwhere home team scored ${this.match.awayTeamScore}` +
        ` and away scored ${this.match.awayTeamScore}` );
        return;          
      });
  }

  updateClubs(updatedClub: Club) : void {
    this.clubService.update(updatedClub)
    .subscribe(
      response => {
        console.log(response);
        console.log(`Club: ${updatedClub.clubName} with ID: ${updatedClub.clubId} updated in database`);        
      },
      error => {
        console.log(`Error updating Club: ${updatedClub.clubName} with ID: ${updatedClub.clubId}`,error);
      });
  }
}