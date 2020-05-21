import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
    ) {}

    public canActivate() {
        if (this.authenticationService.isUserLoggedIn()) {
            return true;
        }
        this.router.navigate(['']);
        return false;
    }
}
