import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule } from "@angular/router";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { CoreModule } from './components/core/core.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    RouterModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CoreModule
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
