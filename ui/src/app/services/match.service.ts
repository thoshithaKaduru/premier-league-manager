import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'api/matches';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient) { }

  public getAll() {
    return this.http.get(baseUrl);
  }
  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  public findByDate(date: string) {
    return this.http.get(`${baseUrl}?${date}`);
  }
  public createMatch(data: any): Observable<any> {
    return this.http.post(`${baseUrl}`, data);
  }
}
