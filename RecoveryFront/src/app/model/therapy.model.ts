export class Therapy {

    constructor(
        public name: string,
        public therapyType: string,
        public maximumMonthlyApplication: number,
        public temperature: number,
        public intensity: number,
        public id?: number
    ) { }

}
