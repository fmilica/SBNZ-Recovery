import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Therapy } from '../model/therapy.model';

@Injectable({
    providedIn: 'root',
})
export class TherapyService {

    constructor(private http: HttpClient) { }

    createTherapy(therapy: Therapy): Observable<Therapy> {
        return this.http.post<Therapy>(environment.apiEndpoint + 'therapy', therapy);
    }
}
