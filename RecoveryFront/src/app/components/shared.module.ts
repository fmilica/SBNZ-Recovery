import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatNativeDateModule, MatPseudoCheckboxModule } from "@angular/material/core";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatProgressBarModule } from "@angular/material/progress-bar";
import { MatSelectModule } from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatTreeModule } from "@angular/material/tree";
import { MatStepperModule } from '@angular/material/stepper';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { RouterModule } from "@angular/router";
import { MDBBootstrapModule } from "angular-bootstrap-md";
import { ToastrModule } from "ngx-toastr";
import { CommonModule } from "@angular/common";

@NgModule({
    declarations: [],
    imports: [
        RouterModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatSelectModule,
        MatInputModule,
        MatButtonModule,
        MatTableModule,
        MatIconModule,
        MatSidenavModule,
        MatListModule,
        MatToolbarModule,
        MatTreeModule,
        MatStepperModule,
        MatPseudoCheckboxModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatProgressBarModule,
        MatPaginatorModule,
        MatCardModule,
        MDBBootstrapModule.forRoot(),
        ToastrModule.forRoot({
            positionClass: 'toast-top-right',
            timeOut: 2500,
        }),
    ],
    exports: [
        RouterModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatSelectModule,
        MatInputModule,
        MatButtonModule,
        MatTableModule,
        MatIconModule,
        MatSidenavModule,
        MatListModule,
        MatToolbarModule,
        MatTreeModule,
        MatStepperModule,
        MatPseudoCheckboxModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatProgressBarModule,
        MatPaginatorModule,
        MatCardModule
    ]
})
export class SharedModule { }