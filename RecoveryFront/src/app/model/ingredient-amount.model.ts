import { Ingredient } from "./ingredient.model";

export class IngredientAmount {

    constructor(
        public ingredient: Ingredient,
        public amount: number
    ) { }

}