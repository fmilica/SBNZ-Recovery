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
import { CreateMealComponent } from "./create-meal/create-meal.component";
import { MealTableComponent } from "./meal-table/meal-table.component";
import { MealsRoutingModule } from "./meals-routing.module";
import { ViewMealsComponent } from "./view-meals/view-meals.component";
import { SharedModule } from "../../shared.module";
import { CoreModule } from "../../core/core.module";

@NgModule({
    declarations: [
        CreateMealComponent,
        MealTableComponent,
        ViewMealsComponent
    ],
    imports: [
        CoreModule,
        SharedModule,
        MealsRoutingModule,
        MDBBootstrapModule.forRoot(),
        ToastrModule.forRoot({
            positionClass: 'toast-top-right',
            timeOut: 2500,
        }),
    ],
    exports: [MealTableComponent]
})
export class MealsModule { }