import { Meal } from "./meal.model";

export class DailyMeal {

    constructor(
        public day: Date,
        public meals: Array<Meal>,
        public id?: number
    ) { }

}