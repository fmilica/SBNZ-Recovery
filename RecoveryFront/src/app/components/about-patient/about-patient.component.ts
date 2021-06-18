import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient.model';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-about-patient',
  templateUrl: './about-patient.component.html',
  styleUrls: ['./about-patient.component.sass']
})
export class AboutPatientComponent implements OnInit {

  patientDetails: Patient = {} as Patient;
  
  constructor(
    private patientService: PatientService
  ) { }

  ngOnInit(): void {
    this.patientService.getPatientDetails().subscribe(
      response => {
        this.patientDetails = response;
      },
      error => {
        console.log(error)
      }
    )
  }

  getFullName(): string {
    return this.patientDetails.name + " " + this.patientDetails.surname
  }

  getFullAmnessis(): string {
    let amesisList = "";
    if(this.patientDetails.anamnesis.length === 0){
      return "Patient has no illnesses."
    }
    this.patientDetails.anamnesis.forEach((element, index) => {
      amesisList += element.name;
      if(index != this.patientDetails.anamnesis.length-1){
        amesisList += ", "
      }
    });
    return amesisList;
  }

  getPhysicalActivity(activity: string): string {
    if(activity === "SEDENTARY"){
      return "Sedentary";
    }
    else if(activity === "LIGHT_ACTIVE"){
      return "Light active";
    }
    else if(activity === "MODERATE"){
      return "Moderate";
    }
    else if(activity === "VERY_ACTIVE"){
      return "Very active"
    } else if(activity === "EXTRA_ACTIVE"){
      return "Extra active";
    }
    return "No active injuries";
  }

}
