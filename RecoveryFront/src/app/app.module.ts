import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HomepageComponent } from './components/homepage/homepage.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatTreeModule } from '@angular/material/tree';
import { MatStepperModule } from '@angular/material/stepper';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from "@angular/material/card";
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { RegisterComponent } from './components/register/register.component';
import { AddInjuryComponent } from './components/add-injury/add-injury.component';
import { MedicalHistoryComponent } from './components/medical-history/medical-history.component';
import { CreateIngredientComponent } from './components/create-ingredient/create-ingredient.component';
import { CreateMealComponent } from './components/create-meal/create-meal.component';
import { ViewIngredientsComponent } from './components/view-ingredients/view-ingredients.component';
import { AddTherapyComponent } from './components/add-therapy/add-therapy.component';
import { ViewMealsComponent } from './components/view-meals/view-meals.component';
import { ViewTherapiesComponent } from './components/view-therapies/view-therapies.component';
import { ViewPatientsComponent } from './components/view-patients/view-patients.component';
import { PatientViewComponent } from './components/patient-view/patient-view.component';
import { InjuryTableComponent } from './components/injury-table/injury-table.component';
import { MealTableComponent } from './components/meal-table/meal-table.component';
import { ReportsComponent } from './components/reports/reports.component';
import { PatientTableComponent } from './components/tables/patient-table/patient-table.component';
import { AssignMealComponent } from './components/assign-meal/assign-meal.component';
import { MenuListItemComponent } from './components/menu-list-item/menu-list-item.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    RegisterComponent,
    AddInjuryComponent,
    MedicalHistoryComponent,
    CreateIngredientComponent,
    CreateMealComponent,
    ViewIngredientsComponent,
    AddTherapyComponent,
    ViewMealsComponent,
    ViewTherapiesComponent,
    ViewPatientsComponent,
    PatientViewComponent,
    InjuryTableComponent,
    MealTableComponent,
    ReportsComponent,
    PatientTableComponent,
    AssignMealComponent,
    MenuListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatToolbarModule,
    MatTreeModule,
    MatStepperModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatPaginatorModule,
    MatCardModule,
    MDBBootstrapModule.forRoot(),
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
      timeOut: 2500,
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      // ako multi nije true ovo bi bio jedini interceptor i pregazio bi sve defaultne interceptore
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
