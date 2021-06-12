import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { InjuryType } from '../model/injury-type.model';

@Injectable({
    providedIn: 'root',
})
export class InjuryTypeService {

    constructor(private http: HttpClient) { }

    findAll(): Observable<InjuryType[]> {
        return this.http.get<InjuryType[]>(environment.apiEndpoint + 'injury-type');
    }
}
