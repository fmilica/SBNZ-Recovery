import { Routes } from "@angular/router";
import { HomepageComponent } from "./components/homepage/homepage.component";
import { LoginComponent } from "./components/login/login.component";
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
        path: 'homepage',
        component: HomepageComponent,
        children: [],
        canActivate: [RoleGuard],
        data: { expectedRoles: 'ROLE_DOCTOR|ROLE_PATIENT' },
    },
    {
        path: '**',
        component: LoginComponent,
    }
];