export class Therapy {

    constructor(
        public id: number,
        public name: string,
        public therapyType: string,
        public maximumMonthlyApplication: number,
        public temperature: number,
        public intensity: number
    ) { }

}
