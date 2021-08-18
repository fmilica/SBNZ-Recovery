import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatNativeDateModule, MatPseudoCheckboxModule } from "@angular/material/core";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatProgressBarModule } from "@angular/material/progress-bar";
import { MatSelectModule } from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatTreeModule } from "@angular/material/tree";
import { MatStepperModule } from '@angular/material/stepper';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule } from "@angular/router";
import { MDBBootstrapModule } from "angular-bootstrap-md";
import { ToastrModule } from "ngx-toastr";
import { AssignMealComponent } from "./assign-meal/assign-meal.component";
import { PatientTableComponent } from "./patient-table/patient-table.component";
import { PatientViewComponent } from "./patient-view/patient-view.component";
import { PatientsRoutingModule } from "./patients-routing.module";
import { ViewPatientsComponent } from "./view-patients/view-patients.component";
import { MealTableComponent } from "../meals/meal-table/meal-table.component";
import { PatientModule } from "../../patient/patient.module";
import { MealsModule } from "../meals/meals.module";
import { CoreModule } from "../../core/core.module";
import { SharedModule } from "../../shared.module";

@NgModule({
    declarations: [
        AssignMealComponent,
        PatientTableComponent,
        PatientViewComponent,
        ViewPatientsComponent
    ],
    imports: [
        MealsModule,
        PatientModule,
        CoreModule,
        SharedModule,
        PatientsRoutingModule,
        MDBBootstrapModule.forRoot(),
        ToastrModule.forRoot({
            positionClass: 'toast-top-right',
            timeOut: 2500,
        }),
    ],
    exports: [ PatientTableComponent ]
})
export class PatientsModule { }