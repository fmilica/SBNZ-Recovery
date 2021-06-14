export class Ingredient {

    constructor(
        public name: string,
        public calories: number,
        public waterPercentage: number,
        public proteins: number,
        public carbohydrates: number,
        public sugars: number,
        public fibers: number,
        public fat: number,
        public id?: number
    ) { }

}