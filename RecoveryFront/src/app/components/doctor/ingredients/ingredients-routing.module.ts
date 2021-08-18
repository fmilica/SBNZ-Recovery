import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { ViewIngredientsComponent } from './view-ingredients/view-ingredients.component';

const routes: Routes = [
    {
        path: 'view-ingredients',
        component: ViewIngredientsComponent,
    },
    {
        path: 'create-ingredient',
        component: CreateIngredientComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class IngredientsRoutingModule { }