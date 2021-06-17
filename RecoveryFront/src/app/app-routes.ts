import { Routes } from "@angular/router";
import { AddInjuryComponent } from "./components/add-injury/add-injury.component";
import { HomepageComponent } from "./components/homepage/homepage.component";
import { LoginComponent } from "./components/login/login.component";
import { MedicalHistoryComponent } from "./components/medical-history/medical-history.component";
import { RegisterComponent } from "./components/register/register.component";
import { LoginGuard } from "./guards/login-guard.service";
import { RoleGuard } from "./guards/role-guard.service";
import { CreateIngredientComponent } from "./components/create-ingredient/create-ingredient.component";
import { CreateMealComponent } from "./components/create-meal/create-meal.component";
import { ViewIngredientsComponent } from "./components/view-ingredients/view-ingredients.component";
import { AddTherapyComponent } from "./components/add-therapy/add-therapy.component";
import { ViewMealsComponent } from "./components/view-meals/view-meals.component";
import { ViewTherapiesComponent } from "./components/view-therapies/view-therapies.component";
import { ViewPatientsComponent } from "./components/view-patients/view-patients.component";
import { PatientViewComponent } from "./components/patient-view/patient-view.component";
import { ReportsComponent } from "./components/reports/reports.component";
import { AssignMealComponent } from "./components/assign-meal/assign-meal.component";

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
                path: 'add-injury',
                component: AddInjuryComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_PATIENT' },
            },
            {
                path: 'medical-history',
                component: MedicalHistoryComponent,
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
                data: { expectedRoles: 'ROLE_DOCTOR|ROLE_PATIENT' },
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
];