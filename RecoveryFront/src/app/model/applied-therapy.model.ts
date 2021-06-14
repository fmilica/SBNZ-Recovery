import { Therapy } from "./therapy.model";

export class AppliedTherapy {

    constructor(
        public id: number,
        public applicationDate: Date,
        public therapy: Therapy
    ) { }

}
