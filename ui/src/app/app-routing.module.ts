import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClubListComponent } from './components/club-list/club-list.component';
import { GenerateMatchComponent } from './components/generate-match/generate-match.component';
import { MatchesListComponent } from './components/matches-list/matches-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'clubs', pathMatch: 'full' }, 

// club routes 
  { path: 'clubs', component: ClubListComponent},
// matche routes
  { path: 'matches', component: MatchesListComponent},
  { path: 'generate-match', component: GenerateMatchComponent},

// redirect routes  
  { path: '', redirectTo: '/clubs', pathMatch: 'full' },
  { path: '**', redirectTo: '/clubs', pathMatch: 'full' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
