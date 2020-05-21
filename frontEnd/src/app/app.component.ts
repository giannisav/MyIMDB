import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './_services/authentication.service';

@Component({ selector: 'app',
             styleUrls: ['./app.component.scss'],
             templateUrl: './app.component.html' })
export class AppComponent {
    title = 'myApp';

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
    ) {}

    public logout() {
        this.authenticationService.logout();
        this.router.navigate(['']);
    }

    public addMovie() {
        this.router.navigate(['movies/add']);
    }
}
