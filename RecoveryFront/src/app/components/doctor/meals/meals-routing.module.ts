import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMealComponent } from './create-meal/create-meal.component';
import { ViewMealsComponent } from './view-meals/view-meals.component';

const routes: Routes = [
    {
        path: 'view-meals',
        component: ViewMealsComponent,
    },
    {
        path: 'create-meal',
        component: CreateMealComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class MealsRoutingModule { }