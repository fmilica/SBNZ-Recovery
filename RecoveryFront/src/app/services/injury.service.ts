import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppliedTherapy } from '../model/applied-therapy.model';
import { Injury } from '../model/injury.model';

@Injectable({
    providedIn: 'root',
})
export class InjuryService {

    constructor(private http: HttpClient) { }

    findAllForPatient(): Observable<Injury[]> {
        return this.http.get<Injury[]>(environment.apiEndpoint + 'injury/current');
    }

    findAllForGivenPatient(patientId: number): Observable<Injury[]> {
        return this.http.get<Injury[]>(environment.apiEndpoint + 'injury/' + patientId);
    }

    findTherapiesForPatientForInjury(injuryId: number): Observable<AppliedTherapy[]> {
        return this.http.get<AppliedTherapy[]>(environment.apiEndpoint + 'injury/applied-therapies/' + injuryId);
    }

    addInjury(injury: Injury): Observable<Injury> {
        return this.http.post<Injury>(environment.apiEndpoint + 'injury', injury);
    }
}
