import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/_services/authentication.service';
import { SnackBarService } from '@app/_services/snack-bar.service';
import { UserLoginDto } from '@app/dto/userLoginDto';

@Component({ selector: 'app-login',
             styleUrls: ['./login.component.scss'],
             templateUrl: './login.component.html' })
export class LoginComponent implements OnInit {
    public loginForm: FormGroup;
    public loading = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private snackBar: SnackBarService,
    ) {
        if (!this.authenticationService.isUserLoggedIn()) {
            this.router.navigate(['']);
        }
    }

    public ngOnInit() {
        this.loginForm = this.formBuilder.group({
            password: ['', Validators.required],
            username: ['', Validators.required],
        });
    }

    get f() { return this.loginForm.controls; }

    public onSubmit() {

        if (this.loginForm.invalid) {
            return;
        }
        const userLoginDto: UserLoginDto = this.loginForm.value;
        this.loading = true;
        this.authenticationService.login(userLoginDto)
            .subscribe(
                (data) => {
                    this.snackBar.openSnackBar('Welcome ' + data.username, 'Ok');
                    sessionStorage.setItem('currentUserName', data.username);
                    this.router.navigate(['home']);
                },
                (error) => {
                    this.snackBar.openSnackBar('Wrong username or password', 'Ok');
                    this.loading = false;
                    this.router.navigate(['']);
                });
    }
}
