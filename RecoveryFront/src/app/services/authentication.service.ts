import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PatientRegister } from '../model/patient-register.model';
import { UserLogin } from '../model/user-login.model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.jwtService = new JwtHelperService();
  }

  public role: BehaviorSubject<string> = new BehaviorSubject<string>('');

  role$ = this.role.asObservable();

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  private jwtService: JwtHelperService;

  // varira od browser-a u kom pozivamo
  private refreshTokenTimeout: any;
  private refreshedToken = '';
  private expiresInNum = 0;

  login(userLoginDto: UserLogin): Observable<UserLogin> {
    return this.http.post<UserLogin>(environment.apiEndpoint + 'auth/log-in', userLoginDto, {
      headers: this.headers
    });
  }

  logout(): void {
    sessionStorage.removeItem('jwtToken');
    sessionStorage.removeItem('expiresIn');
    this.router.navigate(['login']);
    this.role.next('');
    //this.stopRefreshTokenTimer();
  }

  register(patient: PatientRegister): Observable<PatientRegister> {
    return this.http.post<PatientRegister>(environment.apiEndpoint + 'auth/register', patient, {
      headers: this.headers,
    });
  }

  // geteri i seteri
  // poziva se i za updatePasswordDto i sa loginDto -> response je any
  setLoggedInUser(response: any): void {
    // ekstrakcija tokena
    const jwtToken = response.authenticationToken;
    const expiresIn = response.expiresAt;
    // postavljanje tokena
    sessionStorage.setItem('jwtToken', jwtToken);
    sessionStorage.setItem('expiresIn', expiresIn);
    // pokretanje tajmera za refresh tokena
    //this.startRefreshTokenTimer(jwtToken);
  }

  // sadrzaj jwt tokena moze biti bilo sta
  getLoggedInUser(): any {
    const token = sessionStorage.getItem('jwtToken');
    if (!token) {
      return '';
    }
    const info = this.jwtService.decodeToken(token);
    return info;
  }

  getLoggedInUserAuthority(): string {
    const info = this.getLoggedInUser();
    if (info) {
      return info.authority[0].authority;
    }
    else {
      return '';
    }
  }

  getLoggedInUserEmail(): string {
    const info = this.getLoggedInUser();
    if (info) {
      return info.sub;
    }
    else {
      return '';
    }
  }
  /*
  // pomocne metode
  private refreshToken(): Observable<HttpResponse<UserLogin>> {
    return this.http.post<UserLogin>(
      environment.apiEndpoint + 'refresh',
      {},
      { observe: 'response' }
    );
  }

  public startRefreshTokenTimer(token: string): void {
    // parse json object from base64 encoded jwt token
    const jwtToken = this.jwtService.decodeToken(token);
    // set a timeout to refresh the token a minute before it expires
    const expires = new Date(jwtToken.exp * 1000);
    const timeout = expires.getTime() - Date.now() - 60 * 1000;

    this.refreshedToken = token;

    this.refreshTokenTimeout = setInterval(
      () =>
        this.refreshToken().subscribe((response) => {
          const jwtTokenBearer = response.get('Authorization');
          if (jwtTokenBearer) {
            const jwtResponseToken = jwtTokenBearer.split(' ')[1];
            this.refreshedToken = jwtResponseToken;
            // postavljanje tokena
            sessionStorage.setItem('jwtToken', this.refreshedToken);
          }
        }),
      timeout
    );
  }

  // poziv pri logout-u
  public stopRefreshTokenTimer(): void {
    clearInterval(this.refreshTokenTimeout);
  }

  public startAutoLoginRefreshTokenTimer(): void {
    const token = sessionStorage.getItem('jwtToken');
    if (!token) {
      return;
    }
    const expiresIn = sessionStorage.getItem('expiresIn');
    if (!expiresIn) {
      return;
    } else {
      this.expiresInNum = (expiresIn as unknown as number);
    }
    // parse json object from base64 encoded jwt token
    const jwtToken = this.jwtService.decodeToken(token);
    // set a timeout to refresh the token a minute before it expires
    const expires = new Date(jwtToken.exp * 1000);
    const timeout = expires.getTime() - Date.now() - 60 * 1000;

    this.refreshedToken = token;
    this.refreshTokenTimeout = setTimeout(
      () => {
        this.intervalFunction();
        setInterval(
          () => {
            this.intervalFunction();
          }, this.expiresInNum - 60 * 1000);
      }, timeout);
  }

  private intervalFunction(): void {
    this.refreshToken().subscribe((response) => {
      const jwtTokenBearer = response.get('Authorization');
      if (jwtTokenBearer) {
        const jwtToken = jwtTokenBearer.split(' ')[1];
        this.refreshedToken = jwtToken;
        // postavljanje tokena
        sessionStorage.setItem('jwtToken', this.refreshedToken);
      }
    });
  }
  */
}
