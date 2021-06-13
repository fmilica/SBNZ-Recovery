export class Injury {

    constructor(
        public name: string,
        public startDate: Date,
        public description: string,
        public injuryTypeId: number,
        public injuryBodyPart: string,
        public injuryTypeName?: string,
        public id?: number,
        public endDate?: Date
    ) { }

}
