import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/models/match.model';
import { MatchService } from 'src/app/services/match.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-matches-list',
  templateUrl: './matches-list.component.html',
  styleUrls: ['./matches-list.component.css']
})
export class MatchesListComponent implements OnInit {

  matches?: Match[] = [];
  currentMatch?: Match;  
  date = '';

  constructor(private matchService: MatchService, private titleService: Title) { }

  ngOnInit(): void {
    this.titleService.setTitle("Premier League Match Table");      
    this.retrieveMatches();
  }

  searchDate(): void {
    this.matchService.findByDate(this.date)
      .subscribe(
        data => {
          this.matches = Object.values(data)[1];
          console.log("find by date received");          
          console.log("find by date", data);          
        },
        error => {
          alert("couldn't retrieve matches by date")
          console.log("error finding matches by data", error);          
        });
  }

  retrieveMatches(): void { 
    console.log("retrieveMatches");
    
    this.matchService.getAll()
      .subscribe(
        data => {
          // no matches    
          if (JSON.stringify(data) === "{\"status\":true,\"response\":[]}") {            
            console.log("no matches to display");
          }
          // has matches in response 
          else {
            console.log("matches retrieved", data);
            this.matches = Object.values(data)[1].sort((a: { matchDate: any; },b: { matchDate: any; }) => (this.dateToNum(a.matchDate) - this.dateToNum(b.matchDate)));            
          }  
          console.log("matches retrieved: ",data);          
        }, 
        error => {
          console.log("error in retrieving matches: ", error);          
        });
  }

  // date is converted into a number then reversed
  dateToNum(d: any): number {
    // Convert date "26/06/2016" to 20160626
    // i.e: 31-12-2020 is turned into 20201231
    d = d.split("-"); 
    return Number(d[2]+d[1]+d[0]);
  }
}
