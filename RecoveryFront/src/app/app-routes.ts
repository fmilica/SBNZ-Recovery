/* import { Routes } from "@angular/router";
import { AddInjuryComponent } from "./components/patient/add-injury/add-injury.component";
import { HomepageComponent } from "./components/common/homepage/homepage.component";
import { LoginComponent } from "./components/common/login/login.component";
import { MedicalHistoryComponent } from "./components/patient/medical-history/medical-history.component";
import { RegisterComponent } from "./components/common/register/register.component";
import { LoginGuard } from "./guards/login-guard.service";
import { RoleGuard } from "./guards/role-guard.service";
import { CreateIngredientComponent } from "./components/doctor/meals/create-ingredient/create-ingredient.component";
import { CreateMealComponent } from "./components/doctor/meals/create-meal/create-meal.component";
import { ViewIngredientsComponent } from "./components/doctor/meals/view-ingredients/view-ingredients.component";
import { AddTherapyComponent } from "./components/doctor/therapies/add-therapy/add-therapy.component";
import { ViewMealsComponent } from "./components/doctor/meals/view-meals/view-meals.component";
import { ViewTherapiesComponent } from "./components/doctor/therapies/view-therapies/view-therapies.component";
import { ViewPatientsComponent } from "./components/doctor/patients/view-patients/view-patients.component";
import { PatientViewComponent } from "./components/doctor/patients/patient-view/patient-view.component";
import { ReportsComponent } from "./components/doctor/reports/reports.component";
import { AssignMealComponent } from "./components/doctor/meals/assign-meal/assign-meal.component";
import { AboutPatientComponent } from "./components/patient/about-patient/about-patient.component";
import { PatientTodoComponent } from "./components/patient/patient-todo/patient-todo.component";

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full',
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [LoginGuard]
    },
    {
        path: 'register',
        component: RegisterComponent,
        canActivate: [LoginGuard]
    },
    {
        path: 'homepage',
        component: HomepageComponent,
        children: [
            {
                path: 'patient/add-injury',
                component: AddInjuryComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_PATIENT' },
            },
            {
                path: 'patient/about-patient',
                component: AboutPatientComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_PATIENT' },
            },
            {
                path: 'patient/medical-history',
                component: MedicalHistoryComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_PATIENT' },
            },
            {
                path: 'daily/patient-todo',
                component: PatientTodoComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_PATIENT' },
            },
            {
                path: 'ingredient/view-ingredients',
                component: ViewIngredientsComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'ingredient/create-ingredient',
                component: CreateIngredientComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'meal/view-meals',
                component: ViewMealsComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'meal/create-meal',
                component: CreateMealComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'therapy/view-therapies',
                component: ViewTherapiesComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'therapy/create-therapy',
                component: AddTherapyComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'patient/view-patients',
                component: ViewPatientsComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'patient/patient-view',
                component: PatientViewComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'reports',
                component: ReportsComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'patient/assign-meal',
                component: AssignMealComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
        ],
        canActivate: [RoleGuard],
        data: { expectedRoles: 'ROLE_DOCTOR|ROLE_PATIENT' },
    },
    {
        path: '**',
        component: LoginComponent,
    }
]; */