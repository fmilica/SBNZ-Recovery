import { Routes } from "@angular/router";
import { AddInjuryComponent } from "./components/add-injury/add-injury.component";
import { HomepageComponent } from "./components/homepage/homepage.component";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { LoginGuard } from "./guards/login-guard.service";
import { RoleGuard } from "./guards/role-guard.service";

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
        ],
        canActivate: [RoleGuard],
        data: { expectedRoles: 'ROLE_DOCTOR|ROLE_PATIENT' },
    },
    {
        path: '**',
        component: LoginComponent,
    }
];