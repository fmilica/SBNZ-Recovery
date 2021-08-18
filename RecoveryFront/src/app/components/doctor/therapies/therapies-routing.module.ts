import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTherapyComponent } from './add-therapy/add-therapy.component';
import { ViewTherapiesComponent } from './view-therapies/view-therapies.component';

const routes: Routes = [
    {
        path: 'view-therapies',
        component: ViewTherapiesComponent,
    },
    {
        path: 'create-therapy',
        component: AddTherapyComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class TherapiesRoutingModule { }