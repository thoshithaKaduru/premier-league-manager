import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClubListComponent } from './components/club-list/club-list.component';
import { MatchesListComponent } from './components/matches-list/matches-list.component';
import { GenerateMatchComponent } from './components/generate-match/generate-match.component';

@NgModule({
  declarations: [
    AppComponent,
    ClubListComponent,
    MatchesListComponent,
    GenerateMatchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
