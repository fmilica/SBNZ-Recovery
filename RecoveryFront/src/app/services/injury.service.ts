import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Injury } from '../model/injury.model';

@Injectable({
    providedIn: 'root',
})
export class InjuryService {

    constructor(private http: HttpClient) { }

    findAllForPatient(): Observable<Injury[]> {
        return this.http.get<Injury[]>(environment.apiEndpoint + 'injury/current');
    }

    addInjury(injury: Injury): Observable<Injury> {
        return this.http.post<Injury>(environment.apiEndpoint + 'injury', injury);
    }
}
