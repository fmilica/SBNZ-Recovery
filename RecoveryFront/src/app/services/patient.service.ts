import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { PatientRegister } from "../model/patient-register.model";

@Injectable({
    providedIn: 'root',
  })
  export class PatientService {
      
    constructor(
        private http: HttpClient
    ) { }

    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    getPatients(): Observable<PatientRegister[]> {
        return this.http.get<PatientRegister[]>(environment.apiEndpoint + 'patient/get-patients');
    }
  }