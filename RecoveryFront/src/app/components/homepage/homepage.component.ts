import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.sass']
})
export class HomepageComponent implements OnInit {

  role = '';

  constructor(
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.role = this.authService.getLoggedInUserAuthority();
  }

  logout(): void {
    this.authService.logout();
    this.toastr.info('Logged out successfully!');
  }

  toSuperAdmin(): void {
  }

}
