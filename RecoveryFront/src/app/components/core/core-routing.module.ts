import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from 'src/app/guards/login-guard.service';
import { RoleGuard } from 'src/app/guards/role-guard.service';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
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
        // canActivate: [RoleGuard],
        // data: { expectedRoles: 'ROLE_PATIENT|ROLE_DOCTOR' },
    },
    // {
    //     path: '**',
    //     component: LoginComponent,
    // }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class CoreRoutingModule { }