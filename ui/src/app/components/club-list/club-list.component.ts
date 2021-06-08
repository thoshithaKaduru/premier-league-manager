import { Component, OnInit } from '@angular/core';
import { Club } from 'src/app/models/club.model';
import { ClubService} from 'src/app/services/club.service';
import { Title } from '@angular/platform-browser';
import { MatchService } from 'src/app/services/match.service';


@Component({
  selector: 'app-club-list',
  templateUrl: './club-list.component.html',
  styleUrls: ['./club-list.component.css']
})
export class ClubListComponent implements OnInit {

  clubs?: Club[] = [];
  currentClub?: Club; // TODO: might need to remove this
  currentIndex = -1;
  title = '';

  constructor(private clubService: ClubService, private titleService: Title, private matchService: MatchService) { }

  ngOnInit(): void {
    this.titleService.setTitle("Premier League Club Table");    
    this.retrieveClubs();
  }

  retrieveClubs(): void {
    console.log("retrieveClubs");

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
            this.clubs = Object.values(data)[1].sort((a: { clubId: any; },b: { clubId: any; }) => (a.clubId! < b.clubId!) ? 1: -1); 
            console.log("data" ,typeof data);            
          }          
        }, 
        error => {
          console.log("error in retrieving clubs: ", error);          
        });
        
  }

  refreshList(): void {
    this.retrieveClubs();
    this.currentClub = undefined; // TODO: remove this
    this.currentIndex = -1; // TODO: remove this
  }
 
  sortListByPoints(): void {
    console.log("sorting by points");    
    if (this.clubs === undefined) {
      alert("no clubs to sort");
      return;
    }
    this.clubs.sort((a,b) => (a.noOfPointScored! < b.noOfPointScored!) ? 1: -1);       
  }

  sortListByGoalsScored(): void {
    console.log("sorting by goals scored");    
    if (this.clubs === undefined) {
      alert("no clubs to sort");
      return;
    }
    this.clubs.sort((a,b) => (a.noOfGoalsScored! < b.noOfGoalsScored!) ? 1: -1);    
  }

  sortListByWins (): void {
    console.log("sorting by wins");    
    if (this.clubs === undefined) {
      alert("no clubs to sort");
      return;
    }
    this.clubs.sort((a,b) => (a.noOfMatchesWon! < b.noOfMatchesWon!) ? 1: -1);
  }
}
