import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppliedTherapy } from '../model/applied-therapy.model';
import { Therapy } from '../model/therapy.model';
import { ViewTherapy } from '../model/view-therapy.model';

@Injectable({
    providedIn: 'root',
})
export class TherapyService {

    constructor(private http: HttpClient) { }

    getTherapies(): Observable<ViewTherapy[]> {
        return this.http.get<ViewTherapy[]>(environment.apiEndpoint + 'therapy');
    }

    filterTherapies(illnessId: number, injuryTypeId: number): Observable<ViewTherapy[]> {
        return this.http.get<ViewTherapy[]>(environment.apiEndpoint + 'therapy/filter?illnessId=' + illnessId + '&injuryTypeId=' + injuryTypeId);
    }

    getAllDailyTherapiesCurrent() {
        return this.http.get<AppliedTherapy[]>(environment.apiEndpoint + 'therapy/current/daily-applied');
    }

    createTherapy(therapy: Therapy): Observable<Therapy> {
        return this.http.post<Therapy>(environment.apiEndpoint + 'therapy', therapy);
    }

    assignTherapy(patientId: number): Observable<HttpResponse<void>> {
        return this.http.get<void>(environment.apiEndpoint + 'therapy/assign/' + patientId,
            { observe: 'response' });
    }
}
