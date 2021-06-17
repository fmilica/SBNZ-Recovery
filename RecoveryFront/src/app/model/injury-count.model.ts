export class InjuryCount {

    constructor(
        public startDate: Date,
        public endDate: Date,
        public injuryTypeId?: number,
        public startAge?: number,
        public endAge?: number,
    ) { }

}
