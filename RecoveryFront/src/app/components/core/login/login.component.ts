import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserLogin } from 'src/app/model/user-login.model';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide = true;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private authService: AuthenticationService) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    // check if form is valid
    if (!this.loginForm.valid) {
      return;
    }

    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;

    const userLoginDto = new UserLogin(email, password);

    this.authService.login(userLoginDto)
      .subscribe(
        (response) => {
          this.toastr.success('Logged in successfully!');
          // postavljanje u session storage
          this.authService.setLoggedInUser(response);
          // reset forme
          this.loginForm.reset();
          // preusmerenje
          if (this.authService.getLoggedInUserAuthority() === 'ROLE_PATIENT') {
            this.router.navigate(['patient/about-me']);
          } else {
            this.router.navigate(['doctor/patients/view-patients']);
          }
          this.authService.role.next(this.authService.getLoggedInUserAuthority());
        },
        (error) => {
          if (error.status === 400) {
            this.toastr.error('Incorrect email or password.');
          } else if (error.status === 403) {
            this.toastr.error('Too many unsuccesfull logins!');
          } else {
            this.toastr.error('503 Server Unavailable');
          }
          this.loginForm.reset();
        }
      );

  }

  toRegister(): void {
    this.router.navigate(['register']);
  }

  getEmailErrorMessage(): string {
    if (this.loginForm.controls.email.touched) {
      if (this.loginForm.controls.email.hasError('required')) {
        return 'Required field';
      }

      return this.loginForm.controls.email.hasError('email') ? 'Not a valid email' : '';
    }
    return '';
  }

  getRequiredFieldErrorMessage(fieldName: string): string {
    if (this.loginForm.controls[fieldName].touched) {
      return this.loginForm.controls[fieldName].hasError('required') ? 'Required field' : '';
    }

    return '';
  }

}
