export class Ingredient {

    constructor(
        public name: string,
        public calories: number,
        public waterPercentage: number,
        public proteins: number,
        public carbonhydrates: number,
        public sugars: number,
        public fiber: number,
        public fat: number,
        public id?: number
    ) { }

}