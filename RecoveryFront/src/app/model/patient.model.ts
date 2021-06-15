import { Illness } from "./illness.model";

export class Patient {

    constructor(
        public id: number,
        public email: string,
        public name: string,
        public surname: string,
        public dateOfBirth: Date,
        public gender: string,
        public height: number,
        public weight: number,
        public physicalActivity: string,
        public anamnesis: Illness[],
        public physicalActivityAfterInjury: string,
        public regularDailyCaloryIntake: number,
        public dailyCaloryIntakeAfterInjury: number
    ) { }

}
