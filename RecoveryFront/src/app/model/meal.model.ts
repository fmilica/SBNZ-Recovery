import { IngredientAmount } from "./ingredient-amount.model";

export class Meal {

    constructor(
        public name: string,
        public ingredients: Array<IngredientAmount>,
        public mealDescription: string
    ) { }

}