import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './_models/user';
import { AuthenticationService } from './_services/authentication.service';

@Component({ selector: 'app', templateUrl: 'app.component.html' })
export class AppComponent {
    public currentUser: User;

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
