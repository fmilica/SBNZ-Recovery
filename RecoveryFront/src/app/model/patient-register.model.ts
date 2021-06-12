export class PatientRegister {

    constructor(
        public email: string,
        public password: string,
        public name: string,
        public surname: string,
        public dateOfBirth: Date,
        public gender: string,
        public height: number,
        public weight: number,
        public physicalActivity: string,
        public anamnesis: string[]
    ) { }

}
