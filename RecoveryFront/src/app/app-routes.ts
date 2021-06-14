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
                path: 'create-ingredient',
                component: CreateIngredientComponent,
                canActivate: [RoleGuard],
                data: { expectedRoles: 'ROLE_DOCTOR' },
            },
            {
                path: 'create-meal',
                component: CreateMealComponent,
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