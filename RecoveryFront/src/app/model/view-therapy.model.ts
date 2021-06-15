import { Therapy } from "./therapy.model";

export class ViewTherapy {

    constructor(
        public therapyInfo: Therapy,
        public applicableIllness: string[],
        public applicableInjury: string[]
    ) { }

}
