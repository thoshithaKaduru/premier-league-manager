import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = '/api/clubs';

@Injectable({
  providedIn: 'root'
})
export class ClubService {
  constructor(private http: HttpClient) { }

  public getAll() {
    return this.http.get(baseUrl);
  }
  public update(data: any) {
    return this.http.put(baseUrl, data);
  }
  public createClub(data: any): Observable<any> {
    return this.http.post(`${baseUrl}`, data);
  }
}
