import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '@app/_services/alert.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { UserLoginDto } from '@app/dto/userLoginDto';

@Component({ selector: 'app-login',
             styleUrls: ['./login.component.scss'],
             templateUrl: './login.component.html' })
export class LoginComponent implements OnInit {
    public loginForm: FormGroup;
    public loading = false;
    public submitted = false;
    public returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService,
    ) {
        if (!this.authenticationService.isUserLoggedIn()) {
            this.router.navigate(['']);
        }
    }

    public ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
        });
    }

    get f() { return this.loginForm.controls; }

    public onSubmit() {
        this.submitted = true;

        this.alertService.clear();

        if (this.loginForm.invalid) {
            return;
        }
        const userLoginDto: UserLoginDto = this.loginForm.value;
        this.loading = true;
        this.authenticationService.login(userLoginDto)
            .subscribe(
                (data) => {
                    this.alertService.success('Sucessfully logged in', true);
                    sessionStorage.setItem('currentUserName', data.username);
                    this.router.navigate(['home']);
                },
                (error) => {
                    this.alertService.error('Wrong username or password', true);
                    this.loading = false;
                    this.router.navigate(['']);
                });
    }
}
