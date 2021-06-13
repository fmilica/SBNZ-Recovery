import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HomepageComponent } from './components/homepage/homepage.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatTreeModule } from '@angular/material/tree';
import { MatStepperModule } from '@angular/material/stepper';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from "@angular/material/card";
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { RegisterComponent } from './components/register/register.component';
import { AddInjuryComponent } from './components/add-injury/add-injury.component';
import { MedicalHistoryComponent } from './components/medical-history/medical-history.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    RegisterComponent,
    AddInjuryComponent,
    MedicalHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
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
    MatCheckboxModule,
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
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      // ako multi nije true ovo bi bio jedini interceptor i pregazio bi sve defaultne interceptore
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
