import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Illness } from '../model/illness.model';

@Injectable({
    providedIn: 'root',
})
export class IllnessService {

    constructor(private http: HttpClient) { }

    findAll(): Observable<Illness[]> {
        return this.http.get<Illness[]>(environment.apiEndpoint + 'illness');
    }
}
